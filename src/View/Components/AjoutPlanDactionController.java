package View.Components;

import DataBase.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ResourceBundle;

public class AjoutPlanDactionController implements Initializable {

    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private ListView<String> targetRegulationsListView;
    @FXML
    private TextArea actionsTextArea;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField responsableTextField;

    private ObservableList<String> regulationsList = FXCollections.observableArrayList();
    private ObservableList<Integer> selectedRegulationIds = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTargetRegulations(); // Load the list of regulations into the ListView
        saveButton.setOnAction(event -> onSaveButtonClicked()); // Handle save button click
        cancelButton.setOnAction(event -> onCancelButtonClicked()); // Handle cancel button click

        targetRegulationsListView.setOnMouseClicked(this::onRegulationSelected); // Handle regulation selection
    }

    /**
     * Load all available regulations into the ListView.
     */
    private void loadTargetRegulations() {
        regulationsList.clear();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT id, nom FROM Reglementation";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("nom");

                // Add regulation to the list
                regulationsList.add("ID: " + id + " | " + name);
            }

            targetRegulationsListView.setItems(regulationsList);
        } catch (SQLException e) {
            showErrorDialog("Database Error", "Error loading regulations from the database.");
            e.printStackTrace();
        }
    }

    /**
     * Handle saving the new corrective action plan.
     */
    private void onSaveButtonClicked() {
        String actions = actionsTextArea.getText();
        java.sql.Date date = (datePicker.getValue() != null) ? Date.valueOf(datePicker.getValue()) : null;
        String responsable = responsableTextField.getText();

        if (actions.isEmpty() || date == null || responsable.isEmpty() || selectedRegulationIds.isEmpty()) {
            showErrorDialog("Validation Error", "All fields and at least one regulation must be filled.");
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            // Insert the new corrective action plan
            String insertPlanQuery = "INSERT INTO PlanDActionCorrectif (date, responsable) VALUES (?, ?)";
            PreparedStatement stmtPlan = connection.prepareStatement(insertPlanQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtPlan.setDate(1, date);
            stmtPlan.setString(2, responsable);
            stmtPlan.executeUpdate();

            // Get the generated ID for the new plan
            ResultSet generatedKeys = stmtPlan.getGeneratedKeys();
            if (generatedKeys.next()) {
                int planId = generatedKeys.getInt(1);

                // Associate the selected regulations with the new plan
                for (int regulationId : selectedRegulationIds) {
                    String insertAssociationQuery = "INSERT INTO PlanDActionCorrectif_Reglementation (plan_id, reglementation_id) VALUES (?, ?)";
                    PreparedStatement stmtAssociation = connection.prepareStatement(insertAssociationQuery);
                    stmtAssociation.setInt(1, planId);
                    stmtAssociation.setInt(2, regulationId);
                    stmtAssociation.executeUpdate();
                }

                showInfoDialog("Success", "Corrective action plan created successfully.");
                clearForm();
                closeWindow();
            } else {
                showErrorDialog("Database Error", "Failed to retrieve the generated plan ID.");
            }
        } catch (SQLException e) {
            showErrorDialog("Database Error", "Error saving the corrective action plan.");
            e.printStackTrace();
        }
    }

    private void onCancelButtonClicked() {
        clearForm();
    }

    private void clearForm() {
        actionsTextArea.clear();
        datePicker.setValue(null);
        responsableTextField.clear();
        selectedRegulationIds.clear();
        targetRegulationsListView.getSelectionModel().clearSelection();
    }

    /**
     * Handle regulation selection from the ListView.
     */
    private void onRegulationSelected(MouseEvent event) {
        String selectedItem = targetRegulationsListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            int regulationId = Integer.parseInt(selectedItem.split(":")[1].split("\\|")[0].trim());
            if (!selectedRegulationIds.contains(regulationId)) {
                selectedRegulationIds.add(regulationId);
            } else {
                selectedRegulationIds.remove(Integer.valueOf(regulationId));
            }
        }
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
        private void closeWindow() {
    cancelButton.getScene().getWindow().hide();
}
}

package Controller;

import DataBase.DatabaseConnection;
import Utils.ViewLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PlanDactionController implements Initializable {

    @FXML
    private Button addNewPlanButton;
    @FXML
    private Button closeButton;
    @FXML
    private ListView<String> actionPlansListView;
    @FXML
    private VBox regulationsContainer;  // VBox to display regulations for a selected plan

    // ObservableList to hold the plans
    private ObservableList<String> actionPlans = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadActionPlans();  // Load the action plans into the ListView
        addNewPlanButton.setOnAction(event -> onAddNewPlanClicked());  // Handle new plan creation
        closeButton.setOnAction(event -> onCloseClicked());  // Handle closing the window

        actionPlansListView.setOnMouseClicked(this::onPlanSelected);  // Handle plan selection
    }

    // Loads the list of action plans from the database
    private void loadActionPlans() {
        actionPlans.clear();  // Clear the current list

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT id, responsable FROM PlanDActionCorrectif";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int planId = rs.getInt("id");
                String responsable = rs.getString("responsable");

                // Add the plan to the observable list
                actionPlans.add("Plan ID: " + planId + " | Responsable: " + responsable);
            }

            // Bind the ListView to the observable list
            actionPlansListView.setItems(actionPlans);

        } catch (SQLException e) {
            showErrorDialog("Database Error", "Error loading action plans from the database.");
            e.printStackTrace();
        }
    }

    // Event handler for selecting a plan from the ListView
    private void onPlanSelected(MouseEvent event) {
        String selectedPlanText = actionPlansListView.getSelectionModel().getSelectedItem();
        if (selectedPlanText != null) {
            int planId = Integer.parseInt(selectedPlanText.split(":")[1].split("\\|")[0].trim());  // Extract Plan ID
            loadRegulationsForPlan(planId);
        }
    }

    // Loads the regulations for the selected plan
    private void loadRegulationsForPlan(int planId) {
        regulationsContainer.getChildren().clear();  // Clear previous regulations

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT r.nom,r.descriptionExigence,r.dateMiseEnApplication FROM Reglementation r "
                         + "JOIN PlanDActionCorrectif_Reglementation p ON r.id = p.reglementation_id "
                         + "WHERE p.plan_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, planId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String regulationName = rs.getString("nom");
                String regulationDescp = rs.getString("descriptionExigence");
                String regulationDate = rs.getString("dateMiseEnApplication");
                String regulation="Name: "+regulationName+", Description: "+regulationDescp+", Date: "+regulationDate;
                TextArea regulationText = new TextArea(regulation);
                regulationText.setEditable(false);
                regulationText.setWrapText(true);

                regulationsContainer.getChildren().add(regulationText);
            }

        } catch (SQLException e) {
            showErrorDialog("Database Error", "Error loading regulations for the selected plan.");
            e.printStackTrace();
        }
    }

    private void onAddNewPlanClicked() {
        try {
            ViewLoader.loadPopup("../View/Components/ajoutPlanDaction.fxml");
            loadActionPlans();
        } catch (Exception e) {
            e.printStackTrace();
            Utils.Alert.showErrorAlert("Erreur", "Erreur lors de l'ouverture de la fenêtre d'ajout : " + e.getMessage());
        }
    }

    private void onCloseClicked() {
        System.out.println("Window closed.");
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
}

package Controller;

import DataBase.DatabaseConnection;
import Model.Reglementation.Reglementation;
import DataBase.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuditEnvironnementaleController {

    @FXML
    private VBox regulationsContainer; // This is where the CheckBoxes will go

    private int planId; // Assuming planId is passed to the controller (set this as needed)

    @FXML
    public void initialize() {
        loadRegulations();
    }

    // Fetch all regulations from the database and add them as CheckBoxes
    private void loadRegulations() {
        regulationsContainer.getChildren().clear(); // Clear any existing checkboxes

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT id, nom FROM Reglementation";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int reglementationId = rs.getInt("id");
                String regulationName = rs.getString("nom");
                
                // Create a new CheckBox for each regulation
                CheckBox checkBox = new CheckBox(regulationName);
                checkBox.setUserData(reglementationId); // Store the ID as user data
                regulationsContainer.getChildren().add(checkBox);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onSaveClicked() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Remove existing entries for the current plan_id (to reset before inserting)
            String deleteQuery = "DELETE FROM plandactioncorrectif_reglementation WHERE plan_id = ?";
            try (PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery)) {
                deleteStmt.setInt(1, planId);
                deleteStmt.executeUpdate();
            }

            // Insert both selected and unchecked checkboxes into the table
            String insertQuery = "INSERT INTO plandactioncorrectif_reglementation (plan_id, reglementation_id) VALUES (?, ?)";
            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                List<CheckBox> checkBoxesToRemove = new ArrayList<>();
                
                // Loop through all checkboxes
                for (var node : regulationsContainer.getChildren()) {
                    if (node instanceof CheckBox) {
                        CheckBox checkBox = (CheckBox) node;
                        int reglementationId = (int) checkBox.getUserData(); // Get the regulation ID
                        
                        // Insert the regulation, whether selected or unchecked
                        insertStmt.setInt(1, planId); // Set the plan_id
                        insertStmt.setInt(2, reglementationId); // Set the reglementation_id
                        insertStmt.addBatch();

                        // If the checkbox is selected, add it to the list to remove from the view later
                        if (checkBox.isSelected()) {
                            checkBoxesToRemove.add(checkBox);
                        }
                    }
                }

                insertStmt.executeBatch(); // Execute batch insert
                System.out.println("Regulations saved successfully!");

                // Remove selected checkboxes from the ListView
                regulationsContainer.getChildren().removeAll(checkBoxesToRemove);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onCancelClicked() {
        // You can either close the window, clear fields, or just reset the checkboxes.
        System.out.println("Action cancelled!");
        loadRegulations(); // Optionally, reload regulations if you want to reset everything
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }
}

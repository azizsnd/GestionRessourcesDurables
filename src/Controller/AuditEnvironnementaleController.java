package Controller;

import DataBase.DatabaseConnection;
import Utils.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

import java.sql.*;
import java.util.ArrayList;

public class AuditEnvironnementaleController {

    @FXML
    private VBox regulationsContainer;

    private int planId;

    @FXML
    public void initialize() {
        loadRegulations();
    }

    private void loadRegulations() {
        regulationsContainer.getChildren().clear();

        try (Connection connection = DatabaseConnection.getConnection()) {
            // Modified query to exclude regulations already assigned in either PlanDActionCorrectif_Reglementation or AuditEnviromental_Resultat
            String query = "SELECT r.id, r.nom FROM Reglementation r "
                    + "LEFT JOIN PlanDActionCorrectif_Reglementation p ON r.id = p.reglementation_id "
                    + "LEFT JOIN AuditEnviromental_Resultat a ON r.id = a.reglementation_id "
                    + "WHERE p.reglementation_id IS NULL AND a.reglementation_id IS NULL"; // Exclude regulations assigned in either table

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int reglementationId = rs.getInt("id");
                String regulationName = rs.getString("nom");
                // Create a checkbox for each regulation that isn't assigned to any plan or audit
                CheckBox checkBox = new CheckBox(regulationName);
                checkBox.setUserData(reglementationId);
                regulationsContainer.getChildren().add(checkBox);
            }
        } catch (SQLException e) {
            showErrorDialog("Database Error", "Error loading regulations from the database.");
            e.printStackTrace();
        }
    }

    @FXML
    private void onSaveClicked() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Step 1: Insert the new PlanDActionCorrectif record
            String insertPlanQuery = "INSERT INTO PlanDActionCorrectif (date, responsable) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(insertPlanQuery, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setDate(1, Date.valueOf("2024-12-15"));
                stmt.setString(2, SessionManager.getInstance().getNom());

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            planId = generatedKeys.getInt(1);
                        }
                    }
                } else {
                    showErrorDialog("Database Error", "Failed to insert PlanDActionCorrectif.");
                    return;
                }
            }

            // Step 2: Insert selected regulations into PlanDActionCorrectif_Reglementation
            String insertRegulationQuery = "INSERT INTO PlanDActionCorrectif_Reglementation (plan_id, reglementation_id) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(insertRegulationQuery)) {
                for (javafx.scene.Node node : regulationsContainer.getChildren()) {
                    if (node instanceof CheckBox) {
                        CheckBox checkBox = (CheckBox) node;
                        if (!checkBox.isSelected()) {
                            int reglementationId = (Integer) checkBox.getUserData();
                            stmt.setInt(1, planId);
                            stmt.setInt(2, reglementationId);
                            stmt.addBatch();
                        }
                    }
                }
                stmt.executeBatch();
                showInfoDialog("Success", "Regulations successfully added to the Plan.");
                // Step 1: Insert into AuditEnviromental (creating a new audit)
                String insertAuditQuery = "INSERT INTO AuditEnviromental (entite) VALUES (?)";
                int auditId = 0;
                try (PreparedStatement stmt2 = connection.prepareStatement(insertAuditQuery, Statement.RETURN_GENERATED_KEYS)) {
                    stmt2.setString(1, "Some Entity"); // Assuming "Some Entity" for the entite; you can replace this with actual value
                    int rowsAffected = stmt2.executeUpdate();
                    if (rowsAffected > 0) {
                        try (ResultSet generatedKeys = stmt2.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                auditId = generatedKeys.getInt(1); // Retrieve the generated audit_id
                            }
                        }
                    } else {
                        showErrorDialog("Database Error", "Failed to insert AuditEnviromental.");
                        return;
                    }
                }
                // Step 3: Insert into AuditEnviromental_Resultat with conforme = TRUE
                String insertAuditResultQuery = "INSERT INTO AuditEnviromental_Resultat (audit_id, reglementation_id, conforme) VALUES (?, ?, ?)";
                try (PreparedStatement auditStmt = connection.prepareStatement(insertAuditResultQuery)) {

                    for (javafx.scene.Node node : regulationsContainer.getChildren()) {
                        if (node instanceof CheckBox) {
                            CheckBox checkBox = (CheckBox) node;
                            if (checkBox.isSelected()) {
                                int reglementationId = (Integer) checkBox.getUserData();
                                auditStmt.setInt(1, auditId);
                                auditStmt.setInt(2, reglementationId);
                                auditStmt.setBoolean(3, true);
                                auditStmt.addBatch();
                            }
                        }
                    }
                    auditStmt.executeBatch();
                }

                loadRegulations();
            } catch (SQLException e) {
                showErrorDialog("Database Error", "Error inserting into PlanDActionCorrectif_Reglementation.");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            showErrorDialog("Database Error", "Error connecting to the database.");
            e.printStackTrace();
        }
    }

    @FXML
    private void onCancelClicked() {
        System.out.println("Action cancelled!");
        loadRegulations(); // Optionally, reload regulations if you want to reset everything
    }

    public void setPlanId(int planId) {
        this.planId = planId;
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

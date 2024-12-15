package controller;

import DataBase.DatabaseConnection;
import Model.Reglementation.NormeIso;
import Model.Reglementation.Reglementation;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;

public class PopupISOController {

    @FXML
    private TextField numIsoField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private ListView<Reglementation> exigencesListView;

    @FXML
    private Button addExigenceButton;

    @FXML
    private Button deleteExigenceButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField exigenceInputField;

    public PopupISOController() {
        // Default constructor
    }
        // Add a reference to the callback function
    private OnIsoSavedCallback onIsoSavedCallback;

    // Interface for callback
    public interface OnIsoSavedCallback {
        void onIsoSaved();  // This will be triggered when a new ISO is saved
    }

    // Set the callback function (called from the main controller)
    public void setOnIsoSavedCallback(OnIsoSavedCallback callback) {
        this.onIsoSavedCallback = callback;
    }
    @FXML
    public void initialize() {
        addExigenceButton.setOnAction(e -> addExigence());
        deleteExigenceButton.setOnAction(e -> deleteExigence());
        saveButton.setOnAction(e -> saveISOToDatabase());
        cancelButton.setOnAction(e -> closePopup());
    }

    private void addExigence() {
        try {
            // Charger le FXML de la popup
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Components/AddExigencePopup.fxml"));
            Stage popupStage = new Stage();
            popupStage.setScene(new Scene(loader.load()));
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Ajouter Exigence");
            popupStage.showAndWait();

            // Récupérer la nouvelle exigence ajoutée
            Controller.AddExigenceController popupController = loader.getController();
            Reglementation nouvelleExigence = popupController.getExigence();

            // Ajouter à la ListView si non null
            if (nouvelleExigence != null && !nouvelleExigence.isEmpty()) {
                exigencesListView.getItems().add(nouvelleExigence);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteExigence() {
        Reglementation selectedExigence = exigencesListView.getSelectionModel().getSelectedItem();
        if (selectedExigence != null) {
            exigencesListView.getItems().remove(selectedExigence);
        }
    }

    private void saveISOToDatabase() {
        String isoNumber = numIsoField.getText();
        String description = descriptionField.getText();

        if (isoNumber.isEmpty() || description.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez remplir tous les champs.");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Insert ISO Norm into `normes`
            String insertISO = "INSERT INTO normeiso (numISO, descriptionNorme) VALUES (?, ?)";
            PreparedStatement isoStmt = conn.prepareStatement(insertISO, PreparedStatement.RETURN_GENERATED_KEYS);
            isoStmt.setInt(1, Integer.parseInt(isoNumber));
            isoStmt.setString(2, description);
            isoStmt.executeUpdate();

            // Get the generated ISO ID
            int normeId = 0;
            var generatedKeys = isoStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                normeId = generatedKeys.getInt(1);
            }

            // Insert Exigences into `reglementations` and link them
            String insertExigence = "INSERT INTO reglementation (nom, descriptionExigence, dateMiseEnApplication) VALUES (?, ?, ?)";
            String linkExigence = "INSERT INTO normeiso_reglementation (norme_id, reglementation_id) VALUES (?, ?)";

            for (Reglementation exigence : exigencesListView.getItems()) {
                PreparedStatement regStmt = conn.prepareStatement(insertExigence, PreparedStatement.RETURN_GENERATED_KEYS);
                regStmt.setString(1, exigence.nom());  // Use .nom() to get the name
                regStmt.setString(2, exigence.descriptionExigence());  // Use .descriptionExigence() to get the description
                regStmt.setDate(3, new java.sql.Date(System.currentTimeMillis())); // Example date
                regStmt.executeUpdate();

                // Get the generated Exigence ID
                int reglementationId = 0;
                var regKeys = regStmt.getGeneratedKeys();
                if (regKeys.next()) {
                    reglementationId = regKeys.getInt(1);
                }

                // Link ISO and Exigence
                PreparedStatement linkStmt = conn.prepareStatement(linkExigence);
                linkStmt.setInt(1, normeId);
                linkStmt.setInt(2, reglementationId);
                linkStmt.executeUpdate();
            }

            showAlert(Alert.AlertType.INFORMATION, "Succès", "Norme ISO et exigences enregistrées avec succès!");
            closePopup();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Une erreur est survenue lors de l'enregistrement.");
        }
    }

    private void closePopup() {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void populateForm(NormeIso norme) {
    if (norme != null) {
        numIsoField.setText(String.valueOf(norme.getNumISO())); // Assuming NormeIso has a getNumISO() method
        descriptionField.setText(norme.getDescriptionNorme()); // Assuming NormeIso has a getDescriptionNorme() method
    }
}

}

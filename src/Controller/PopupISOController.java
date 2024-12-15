package controller;

import DataBase.DatabaseConnection;
import Model.Reglementation.NormeIso;
import Model.Reglementation.Reglementation;
import Services.NormeIsoService;
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

        try {
            // Check if the ISO already exists (check based on numISO)
            NormeIso existingNorme = NormeIsoService.getNormeByNumISO(Integer.parseInt(isoNumber));

            if (existingNorme != null) {
                // Update the existing ISO norm
                existingNorme.setDescriptionNorme(description);
                NormeIsoService.updateNorme(existingNorme);  // Use the service to update

                // Update the exigences (link them properly to the updated ISO norm)
                updateExigencesForNorme(existingNorme);

                showAlert(Alert.AlertType.INFORMATION, "Succès", "Norme ISO mise à jour avec succès!");
            } else {
                // Insert a new ISO norm
                NormeIso newNorme = new NormeIso(Integer.parseInt(isoNumber), description);
                NormeIsoService.addNorme(newNorme);  // Use the service to insert

                // Add the exigences to the new ISO norm
                addExigencesForNorme(newNorme);

                showAlert(Alert.AlertType.INFORMATION, "Succès", "Norme ISO et exigences enregistrées avec succès!");
            }

            closePopup();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Une erreur est survenue lors de l'enregistrement.");
        }
    }
    private void updateExigencesForNorme(NormeIso norme) throws SQLException {
        for (Reglementation exigence : exigencesListView.getItems()) {
            Reglementation updatedExigence = new Reglementation(
                exigence.id(),
                exigence.nom(),
                exigence.descriptionExigence(),
                exigence.dateMiseEnApplication()
            );
            NormeIsoService.updateExigence(updatedExigence);
        }
    }

    private void addExigencesForNorme(NormeIso norme) throws SQLException {
        for (Reglementation exigence : exigencesListView.getItems()) {
            Reglementation updatedExigence = new Reglementation(
                exigence.id(),
                exigence.nom(),
                exigence.descriptionExigence(),
                exigence.dateMiseEnApplication()
            );
            NormeIsoService.addExigence(updatedExigence);
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
            numIsoField.setText(String.valueOf(norme.getNumISO()));
            descriptionField.setText(norme.getDescriptionNorme());
        }
    }

}

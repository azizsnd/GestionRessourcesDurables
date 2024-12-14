package Controller;

import Model.entiteDurable.ObjectifDurabilite;
import Model.entiteDurable.ObjectifInvalideException;
import Model.entiteDurable.Ressource;
import Model.serviceSuivi.ServiceSuiviRessource;
import Services.SuiviRessource;
import Utils.Alert;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.time.ZoneId;
import java.util.Date;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class AjoutRessourceController implements Initializable {

    @FXML
    private TextField nomField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private DatePicker dateCreationPicker;
    @FXML
    private TextField objectifField;
    @FXML
    private TextField uniteDeMesureField;
    @FXML
    private TextField utilisationReferenceField;
    @FXML
    private TextField utilisationActuelleField;
    @FXML
    private TextField coutParUniteField;
    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;

    private ServiceSuiviRessource suiviRessource = new ServiceSuiviRessource("Ressource", 30, new Date(), "Actif");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization if needed
    }

    // Style Effects for Buttons
    @FXML
    public void handleMouseEnteredAddButton(MouseEvent event) {
        addButton.setStyle("-fx-background-color: #619E4F; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 15;");
    }

    @FXML
    public void handleMouseExitedAddButton(MouseEvent event) {
        addButton.setStyle("-fx-background-color: #6BDB48; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 15;");
    }

    @FXML
    public void handleMouseEnteredCancelButton(MouseEvent event) {
        cancelButton.setStyle("-fx-background-color: #2B3329; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 15;");
    }

    @FXML
    public void handleMouseExitedCancelButton(MouseEvent event) {
        cancelButton.setStyle("-fx-background-color: #505C4C; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 15;");
    }

    @FXML
    private void ajouterRessource() {
        try {
            // Retrieve user input
            String nom = nomField.getText().trim();
            String description = descriptionField.getText().trim();
            LocalDate localDate = dateCreationPicker.getValue(); // LocalDate from DatePicker
            if (localDate == null) {
                throw new IllegalArgumentException("La date de création est obligatoire.");
            }
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()); // Convert to Date
                    
            double objectifReduction = Double.parseDouble(objectifField.getText().trim());
            String uniteDeMesure = uniteDeMesureField.getText().trim();
            double utilisationReference = Double.parseDouble(utilisationReferenceField.getText().trim());
            double utilisationActuelle = Double.parseDouble(utilisationActuelleField.getText().trim());
            double coutParUnite = Double.parseDouble(coutParUniteField.getText().trim());

            // Validate inputs
            if (nom.isEmpty() || uniteDeMesure.isEmpty()) {
                throw new IllegalArgumentException("Tous les champs obligatoires doivent être remplis.");
            }

            // Create ObjectifDurabilite and Ressource objects
            ObjectifDurabilite objectif = new ObjectifDurabilite(new Date(), objectifReduction, 5, "Objectif de réduction");
            Ressource ressource = new Ressource(nom, description, date, objectif, uniteDeMesure, utilisationReference, utilisationActuelle, coutParUnite);

            // Add resource to service
            SuiviRessource.addRessource(ressource);

            // Clear all fields after adding
            clearFields();

            Alert.showInfoAlert("Succès", "Ressource ajoutée avec succès.");
            closeWindow();

        } catch (NumberFormatException e) {
            Alert.showErrorAlert("Erreur de format", "Veuillez entrer des valeurs numériques valides pour les champs correspondants.");
        } catch (IllegalArgumentException e) {
            Alert.showErrorAlert("Champs obligatoires", e.getMessage());
        } catch (SQLException | ObjectifInvalideException e) {
            Alert.showErrorAlert("Erreur", e.getMessage());
        }
    }

    @FXML
    private void cancelAction() {
        clearFields();
        closeWindow();

    }

    private void clearFields() {
        nomField.clear();
        descriptionField.clear();
        dateCreationPicker.setValue(null);
        objectifField.clear();
        uniteDeMesureField.clear();
        utilisationReferenceField.clear();
        utilisationActuelleField.clear();
        coutParUniteField.clear();
    }
    private void closeWindow() {
    // Get the current window and close it
    cancelButton.getScene().getWindow().hide();
}
}

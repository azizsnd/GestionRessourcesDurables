package Controller;

import Model.entiteDurable.ObjectifDurabilite;
import Model.entiteDurable.ObjectifInvalideException;
import Model.entiteDurable.Energie;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.Date;
import Model.serviceSuivi.ServiceSuiviEnergie;
import Services.SuiviEnergie;
import Utils.Alert;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class AjoutEnergieController {
    private ServiceSuiviEnergie suiviEnergie = new ServiceSuiviEnergie("Energie", 30, null, "Actif");
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
    private CheckBox estRenouvelableField;
    @FXML
    private TextField typeField;
    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;


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
    private void ajouterEnergie() {
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
            Boolean estRenouvelable = estRenouvelableField.isSelected();
            String type = typeField.getText().trim();

            if (nom.isEmpty() || description.isEmpty() || type.isEmpty()) {
                throw new IllegalArgumentException("Tous les champs sont obligatoires.");
            }

            // Create ObjectifDurabilite and Energie objects
            ObjectifDurabilite objectif = new ObjectifDurabilite(new Date(), objectifReduction, 5, "Objectif de réduction");
            Energie ernergie = new Energie(nom, description, date, objectif,estRenouvelable,type, uniteDeMesure, utilisationReference, utilisationActuelle, coutParUnite);

            SuiviEnergie.addEnergie(ernergie);
            clearFields();

            Alert.showInfoAlert("Succès", "Energie ajoutée avec succès.");
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
        estRenouvelableField.setSelected(false);
        typeField.clear();
    }
    private void closeWindow() {
    cancelButton.getScene().getWindow().hide();
}
}

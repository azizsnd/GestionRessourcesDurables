package Controller;

import Model.entiteDurable.ObjectifDurabilite;
import Model.entiteDurable.ObjectifInvalideException;
import Model.entiteDurable.Dechet;
import Model.serviceSuivi.ServiceSuiviDechet;
import Services.SuiviDechet;
import Utils.Alert;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class AjoutDechetController implements Initializable{
    private ServiceSuiviDechet suiviDechet = new ServiceSuiviDechet("Déchet", 50, null, "Actif");

    @FXML
    private TextField nomField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private DatePicker dateCreationPicker;
    @FXML
    private TextField objectifField;
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private TextField quantiteProduiteField;
    @FXML
    private TextField quantiteRecycleField;
    @FXML
    private TextArea methodeEliminationField;
    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typeComboBox.getItems().addAll("Plastique", "Métal", "Papier", "Verre", "Organique");
    } 
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

    // Ajouter Déchet
    @FXML
    private void ajouterCarbone() {
        try {
            // Retrieve user input
            String nom = nomField.getText().trim();
            String description = descriptionField.getText().trim();
            LocalDate localDate = dateCreationPicker.getValue();
            if (localDate == null) {
                throw new IllegalArgumentException("La date de création est obligatoire.");
            }
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            double objectifReduction = Double.parseDouble(objectifField.getText().trim());
            String type = typeComboBox.getValue();
            double quantiteProduite = Double.parseDouble(quantiteProduiteField.getText().trim());
            double quantiteRecyclee = Double.parseDouble(quantiteRecycleField.getText().trim());
            String methodeElimination = methodeEliminationField.getText().trim();

            if (nom.isEmpty() || description.isEmpty() || type == null || methodeElimination.isEmpty()) {
                throw new IllegalArgumentException("Tous les champs sont obligatoires.");
            }

            // Create ObjectifDurabilite and Déchet objects
            ObjectifDurabilite objectif = new ObjectifDurabilite(new Date(), objectifReduction, 5, "Objectif de réduction");
            Dechet dechet = new Dechet(nom, description, date, objectif, type, quantiteProduite, quantiteRecyclee, methodeElimination);

            SuiviDechet.addDechet(dechet);
            clearFields();

            Alert.showInfoAlert("Succès", "Déchet ajouté avec succès.");
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
        typeComboBox.setValue(null);
        quantiteProduiteField.clear();
        quantiteRecycleField.clear();
        methodeEliminationField.clear();
    }

    private void closeWindow() {
        cancelButton.getScene().getWindow().hide();
    }
}

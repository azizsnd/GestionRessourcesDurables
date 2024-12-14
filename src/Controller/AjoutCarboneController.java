package Controller;

import Model.entiteDurable.EmpreinteCarbone;
import Model.entiteDurable.ObjectifDurabilite;
import Model.entiteDurable.ObjectifInvalideException;
import Model.serviceSuivi.ServiceSuiviCarbone;
import Services.SuiviCarbone;
import Utils.Alert;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.time.ZoneId;
import java.time.LocalDate;
import java.util.Date;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.SQLException;

public class AjoutCarboneController implements Initializable {

    @FXML
    private TextField nomField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private DatePicker dateCreationPicker;
    @FXML
    private TextField objectifField;
    @FXML
    private TextField sourceEmissionField;
    @FXML
    private TextField emissionAnnuellesField;
    @FXML
    private TextField emissionActuelleField;
    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;

    private ServiceSuiviCarbone suiviCarbone = new ServiceSuiviCarbone("Carbone", 30, new Date(), "Actif");

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
    private void ajouterCarbone() {
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
            String sourceEmission = sourceEmissionField.getText().trim();
            double emissionAnnuelles = Double.parseDouble(emissionAnnuellesField.getText().trim());
            double emissionActuelle = Double.parseDouble(emissionActuelleField.getText().trim());

            // Validate inputs
            if (nom.isEmpty() || sourceEmission.isEmpty()) {
                throw new IllegalArgumentException("Tous les champs obligatoires doivent être remplis.");
            }

            // Create ObjectifDurabilite and EmpreinteCarbone objects
            ObjectifDurabilite objectif = new ObjectifDurabilite(new Date(), objectifReduction, 5, "Objectif de réduction");
            EmpreinteCarbone empreinteCarbone = new EmpreinteCarbone(nom, description, date, objectif, sourceEmission, emissionAnnuelles, emissionActuelle);

            // Add carbone to service
            SuiviCarbone.addEmpreinteCarbone(empreinteCarbone);

            // Clear all fields after adding
            clearFields();

            Alert.showInfoAlert("Succès", "Empreinte carbone ajoutée avec succès.");
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
        sourceEmissionField.clear();
        emissionAnnuellesField.clear();
        emissionActuelleField.clear();
    }

    private void closeWindow() {
        // Get the current window and close it
        cancelButton.getScene().getWindow().hide();
    }
}

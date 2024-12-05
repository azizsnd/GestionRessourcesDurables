package Controller;


import Model.serviceSuivi.ServiceSuiviObjectif;

import java.text.SimpleDateFormat;
import Model.entiteDurable.ObjectifDurabilite;
import Model.entiteDurable.ObjectifInvalideException;
import Model.serviceSuivi.GestionRapportSuivi;
import Services.SuiviObjectif;
import Utils.Alert;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

public class VueSuiviObjectifController {
    @FXML
    private ListView<String> listViewObjectifs;
    @FXML
    private TextField txtDateCible;
    @FXML
    private TextField txtReductionCible;
    @FXML
    private TextField txtProgresActuel;
    @FXML
    private TextField txtDescription;

    private ServiceSuiviObjectif suiviObjectif = new ServiceSuiviObjectif("Objectif", 30, new Date(), "Actif");
   
    @FXML
    public void initialize() {
        loadObjectifs();
    }

    private void loadObjectifs() {
        try {
            listViewObjectifs.getItems().clear();
            suiviObjectif.setObjectifsSuivis(SuiviObjectif.getAllObjectifs());

            for (ObjectifDurabilite objectif : suiviObjectif.getObjectifsSuivis()) {
                listViewObjectifs.getItems().add(objectif.toString());
            }
        } catch (SQLException | ObjectifInvalideException | ParseException e) {
            e.printStackTrace();
            Alert.showErrorAlert("Erreur de Chargement", e.getMessage());
        }
    }

    @FXML
    public void ajouterObjectif() throws ParseException {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date dateCible = formatter.parse(txtDateCible.getText());
            double reductionCible = Double.parseDouble(txtReductionCible.getText());
            double progresActuel = Double.parseDouble(txtProgresActuel.getText());
            String description = txtDescription.getText();

            ObjectifDurabilite objectif = new ObjectifDurabilite(dateCible, reductionCible, progresActuel, description);
            SuiviObjectif.addObjectif(objectif);

            loadObjectifs();
            clearFields();
        } catch (NumberFormatException e) {
            Alert.showErrorAlert("Champs invalide","Une erreur s'est produite lors de la saisie des données.");
        } catch (SQLException e) {
            Alert.showErrorAlert("Erreur",e.getMessage());
        } 
    }

    @FXML
    public void genererRapport() {
        try {
            // Create an instance of GestionRapportSuivi
            GestionRapportSuivi gestionRapportSuivi = new GestionRapportSuivi(
                suiviObjectif.getFrequenceRapport(),
                "Objectif",                          
                suiviObjectif                  
            );

            gestionRapportSuivi.genererRapportPourService();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String dateFormatted = dateFormat.format(new Date());
            String fileName = "rapport_Objectif_" + dateFormatted + ".txt";

            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
            java.io.File file = new java.io.File(fileName);
            if (file.exists()) {
                desktop.open(file);
            } else {
                Alert.showErrorAlert("Erreur", "Le fichier rapport n'a pas été trouvé.");
            }
            Alert.showInfoAlert("Succès", "Rapport généré et ouvert avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
            Alert.showErrorAlert("Erreur", "Une erreur s'est produite lors de la génération ou de l'ouverture du rapport : " + e.getMessage());
        }
    }

    private void clearFields() {
        txtDateCible.clear();
        txtReductionCible.clear();
        txtProgresActuel.clear();
        txtDescription.clear();
    }
}

package Controller;

import Model.entiteDurable.Dechet;
import Model.entiteDurable.ObjectifDurabilite;
import Model.entiteDurable.ObjectifInvalideException;
import Model.serviceSuivi.GestionRapportSuivi;
import Model.serviceSuivi.ServiceSuiviDechet;
import Services.SuiviDechet;
import Utils.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.util.Date;
import javafx.fxml.FXML; 
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class VueSuiviDechetController {
    private ServiceSuiviDechet servicesuiviDechet = new ServiceSuiviDechet("Dechet", 30, null, "Actif");

    @FXML
    private ListView<String> listViewDechets;
    @FXML
    private TextField txtTypeDechet;
    @FXML
    private TextField txtQuantite;
    @FXML
    private TextField txtQuantiteRecycle;

    
    @FXML
    public void initialize() {
        loadDechets();
    }

    private void loadDechets() {
        try {
            listViewDechets.getItems().clear();
            servicesuiviDechet.setDechetSuivis(SuiviDechet.getAllDechets());
            for (Dechet dechet : servicesuiviDechet.getDechetSuivis()) {
                listViewDechets.getItems().add(dechet.toString());
            }
        } catch (SQLException | ObjectifInvalideException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void ajouterDechet() throws ObjectifInvalideException {
        try {
            // Récupérer les valeurs entrées par l'utilisateur
            String type = txtTypeDechet.getText();
            double quantiteProduite = Double.parseDouble(txtQuantite.getText());
            double quantiteRecycle = Double.parseDouble(txtQuantiteRecycle.getText());
            // Créer un objectif de recyclage pour le déchet
            ObjectifDurabilite objectif = new ObjectifDurabilite(new Date(), 100, 0, "Objectif de recyclage");
            // Créer un nouvel objet Dechet avec le constructeur fourni
            Dechet dechet = new Dechet("Déchet de " + type, "Description du déchet", new Date(), objectif, type, quantiteProduite, quantiteRecycle, "Recyclage");
            SuiviDechet.addDechet(dechet);
            loadDechets();

            txtTypeDechet.clear();
            txtQuantite.clear();
            txtQuantiteRecycle.clear();
        } catch (NumberFormatException e) {
            Alert.showErrorAlert("Champs invalide","Veuillez entrer des valeurs numériques pour les quantités.");
        } catch (SQLException | ObjectifInvalideException e) {
            Alert.showErrorAlert("Erreur",e.getMessage());
        }   
    }

    @FXML
    public void genererRapport() {
        try {
            GestionRapportSuivi gestionRapportSuivi = new GestionRapportSuivi(
                servicesuiviDechet.getFrequenceRapport(), 
                "Dechet",                    
                servicesuiviDechet                   
            );
            gestionRapportSuivi.genererRapportPourService();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String dateFormatted = dateFormat.format(new Date());
            String fileName = "rapport_Dechet_" + dateFormatted + ".txt";

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
}
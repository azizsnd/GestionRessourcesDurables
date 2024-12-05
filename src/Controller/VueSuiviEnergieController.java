package Controller;

import Model.entiteDurable.ObjectifDurabilite;
import Model.entiteDurable.Energie;
import Model.entiteDurable.ObjectifInvalideException;
import Model.serviceSuivi.GestionRapportSuivi;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Date;
import Model.serviceSuivi.ServiceSuiviEnergie;
import Services.SuiviEnergie;
import Utils.Alert;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class VueSuiviEnergieController {
    private ServiceSuiviEnergie suiviEnergie = new ServiceSuiviEnergie("Energie", 30, null, "Actif");

    @FXML
    private ListView<String> listViewEnergie;
    @FXML
    private TextField txtTypeEnergie;
    @FXML
    private TextField txtUtilisationReference;    
    @FXML
    private TextField txtUtilisationActuelle;
    @FXML
    private CheckBox chkRenouvelable;
    @FXML
    public void initialize() {
        loadEnergies();
    }

    private void loadEnergies() {
        try {
            listViewEnergie.getItems().clear();
            suiviEnergie.setSourcesEnergie(SuiviEnergie.getAllEnergies());

            for (Energie energie : suiviEnergie.getSourcesEnergie()) {
                listViewEnergie.getItems().add(energie.toString());
            }

        } catch (SQLException | ObjectifInvalideException e) {
            e.printStackTrace();
            Alert.showErrorAlert("Erreur de Chargement", e.getMessage());
        }
    }
    @FXML
    private void ajouterEnergie() throws ObjectifInvalideException, SQLException {
        try {
            // Récupérer les valeurs entrées par l'utilisateur
            String type = txtTypeEnergie.getText();
            double utilisationReference = Double.parseDouble(txtUtilisationReference.getText());
            double utilisationActuelle = Double.parseDouble(txtUtilisationActuelle.getText());            
            boolean estRenouvelable = chkRenouvelable.isSelected();

            // Créer un objectif d'énergie
            ObjectifDurabilite objectif = new ObjectifDurabilite(new Date(), 1000, 500, "Objectif de production");

            // Créer un nouvel objet Energie
            Energie energie = new Energie(
                    type,
                    "Énergie de type " + type, 
                    new Date(),
                    objectif,
                    estRenouvelable,
                    type,
                    "unité", 
                    utilisationReference, 
                    utilisationActuelle,
                    1.0
            );
            // Ajouter l'énergie au service de suivi
            SuiviEnergie.addEnergie(energie);
            loadEnergies();
            // Mettre à jour la vue
            txtTypeEnergie.clear();
            txtUtilisationReference.clear();
            txtUtilisationActuelle.clear();
            chkRenouvelable.setSelected(false);
        } catch (NumberFormatException e) {
            Alert.showErrorAlert("Champs invalide","Veuillez entrer des valeurs numériques pour l'utilisation.");
        } catch (SQLException | ObjectifInvalideException e) {
            Alert.showErrorAlert("Erreur",e.getMessage());
        }
    }

    @FXML
    public void genererRapport() {
        try {
            GestionRapportSuivi gestionRapportSuivi = new GestionRapportSuivi(
                suiviEnergie.getFrequenceRapport(), 
                "Energie",                    
                suiviEnergie                   
            );
            gestionRapportSuivi.genererRapportPourService();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String dateFormatted = dateFormat.format(new Date());
            String fileName = "rapport_Energie_" + dateFormatted + ".txt";

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

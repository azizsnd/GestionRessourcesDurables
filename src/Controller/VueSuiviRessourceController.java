package Controller;

import Model.entiteDurable.ObjectifDurabilite;
import Model.entiteDurable.ObjectifInvalideException;
import Model.entiteDurable.Ressource;
import Model.serviceSuivi.GestionRapportSuivi;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.util.Date;
import Model.serviceSuivi.ServiceSuiviRessource;
import Services.SuiviRessource;
import Utils.Alert;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class VueSuiviRessourceController {
    private ServiceSuiviRessource suiviRessource = new ServiceSuiviRessource("Ressource", 30, null, "Actif");

    @FXML
    private ListView<String> listViewRessources;
    @FXML
    private TextField txtTypeRessource;
    @FXML
    private TextField txtUtilisationReference;
    @FXML
    private TextField txtUtilisationActuelle;

    @FXML
    public void initialize() {
        loadRessources();
    }

    private void loadRessources() {
        try {
            listViewRessources.getItems().clear();
            suiviRessource.setResourcesSuivis(SuiviRessource.getAllRessources());

            for (Ressource ressource : suiviRessource.getResourcesSuivis()) {
                listViewRessources.getItems().add(ressource.toString());
            }

        } catch (SQLException | ObjectifInvalideException e) {
            e.printStackTrace();
            Alert.showErrorAlert("Erreur de Chargement", e.getMessage());
        }
    }
    @FXML
    private void ajouterRessource() throws ObjectifInvalideException {
        try {
            // Récupérer les valeurs entrées par l'utilisateur
            String type = txtTypeRessource.getText();
            double utilisationReference = Double.parseDouble(txtUtilisationReference.getText());
            double utilisationActuelle = Double.parseDouble(txtUtilisationActuelle.getText());

            // Créer un objectif de réduction pour la ressource
            ObjectifDurabilite objectif = new ObjectifDurabilite(new Date(), 500, 100, "Objectif de réduction");

            // Créer un nouvel objet Ressource
            Ressource ressource = new Ressource("Ressource de type " + type, "Description du déchet",  new Date(), objectif,"unité", utilisationReference, utilisationActuelle,1.0);
            // Ajouter la ressource au service de suivi
            SuiviRessource.addRessource(ressource);
            loadRessources();
            txtTypeRessource.clear();
            txtUtilisationReference.clear();
            txtUtilisationActuelle.clear();
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
                suiviRessource.getFrequenceRapport(), 
                "Ressource",                    
                suiviRessource                   
            );
            gestionRapportSuivi.genererRapportPourService();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String dateFormatted = dateFormat.format(new Date());
            String fileName = "rapport_Ressource_" + dateFormatted + ".txt";

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

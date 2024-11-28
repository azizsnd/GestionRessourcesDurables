package Controller;

import Model.entiteDurable.ObjectifDurabilite;
import Model.entiteDurable.ObjectifInvalideException;
import Model.entiteDurable.Ressource;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Date;
import Model.serviceSuivi.ServiceSuiviRessource;

public class VueSuiviRessourceController {
    private ServiceSuiviRessource suiviRessource = new ServiceSuiviRessource("Suivi Ressources", 30, null, "Actif");

    @FXML
    private ListView<String> listViewRessources;
    @FXML
    private TextField txtTypeRessource;
    @FXML
    private TextField txtUtilisationReference;
    @FXML
    private TextField txtUtilisationActuelle;

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
            Ressource ressource = new Ressource(1,type,"Ressource de type " + type, new Date(), objectif, "unité", utilisationReference, utilisationActuelle,1.0);

            // Ajouter la ressource au service de suivi
            suiviRessource.addRessource(ressource);

            // Mettre à jour la vue
            listViewRessources.getItems().add("Type: " + type + ", Utilisation Référence: " + utilisationReference + ", Utilisation Actuelle: " + utilisationActuelle);
            txtTypeRessource.clear();
            txtUtilisationReference.clear();
            txtUtilisationActuelle.clear();
        } catch (NumberFormatException e) {
            System.out.println("Erreur : Veuillez entrer des valeurs numériques pour l'utilisation.");
        }
    }

    public String genererRapport() {
        return suiviRessource.genererRapport();
    }
}

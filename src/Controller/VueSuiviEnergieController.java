package Controller;

import Model.entiteDurable.ObjectifDurabilite;
import Model.entiteDurable.Energie;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Date;
import Model.serviceSuivi.ServiceSuiviEnergie;

public class VueSuiviEnergieController {
    private ServiceSuiviEnergie suiviEnergie = new ServiceSuiviEnergie("Suivi Énergie", 30, null, "Actif");

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
    private void ajouterEnergie() {
        try {
            // Récupérer les valeurs entrées par l'utilisateur
            String type = txtTypeEnergie.getText();
            double utilisationReference = Double.parseDouble(txtUtilisationReference.getText());
            double utilisationActuelle = Double.parseDouble(txtUtilisationActuelle.getText());            boolean estRenouvelable = chkRenouvelable.isSelected();

            // Créer un objectif d'énergie
            ObjectifDurabilite objectif = new ObjectifDurabilite(new Date(), 1000, 500, "Objectif de production");

            // Créer un nouvel objet Energie
            Energie energie = new Energie(1, type, "Énergie de type " + type, new Date(),objectif,estRenouvelable,type,"unité", utilisationReference, utilisationActuelle,1.0);

            // Ajouter l'énergie au service de suivi
            suiviEnergie.addEnergie(energie);

            // Mettre à jour la vue
            listViewEnergie.getItems().add("Type: " + type + ", utilisation Reference: " + utilisationReference + ", Utilisation Actuelle: " + utilisationActuelle + ", Renouvelable: " + estRenouvelable);
            txtTypeEnergie.clear();
            txtUtilisationReference.clear();
            txtUtilisationActuelle.clear();
            chkRenouvelable.setSelected(false);
        } catch (NumberFormatException e) {
            System.out.println("Erreur : Veuillez entrer une valeur numérique pour l'énergie produite.");
        }
    }

    public String genererRapport() {
        return suiviEnergie.genererRapport();
    }
}

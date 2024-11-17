package Controller;

import Model.entiteDurable.Dechet;
import Model.entiteDurable.ObjectifDurabilite;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.util.Date;
import javafx.fxml.FXML;
import Model.serviceSuivi.ServiceSuiviDechet;

public class VueSuiviDechetController {
    private ServiceSuiviDechet suiviDechet = new ServiceSuiviDechet("Suivi Déchets", 30, null, "Actif");

    @FXML
    private ListView<String> listViewDechets;
    @FXML
    private TextField txtTypeDechet;
    @FXML
    private TextField txtQuantite;
    @FXML
    private TextField txtQuantiteRecycle;

    @FXML
    private void ajouterDechet() {
        try {
            // Récupérer les valeurs entrées par l'utilisateur
            String type = txtTypeDechet.getText();
            double quantiteProduite = Double.parseDouble(txtQuantite.getText());
            double quantiteRecycle = Double.parseDouble(txtQuantiteRecycle.getText());

            // Créer un objectif de recyclage pour le déchet
            ObjectifDurabilite objectif = new ObjectifDurabilite(new Date(), 100, 0, "Objectif de recyclage");

            // Créer un nouvel objet Dechet avec le constructeur fourni
            Dechet dechet = new Dechet(1, "Déchet de " + type, "Description du déchet", new Date(), objectif, type, quantiteProduite, quantiteRecycle, "Recyclage");

            // Ajouter le déchet au service de suivi
            suiviDechet.mettreAJourDechet(dechet, quantiteProduite);

            // Mettre à jour la vue
            listViewDechets.getItems().add("Type: " + type + ", Quantité Produite: " + quantiteProduite + ", Quantité Recyclée: " + quantiteRecycle);
            txtTypeDechet.clear();
            txtQuantite.clear();
            txtQuantiteRecycle.clear();
        } catch (NumberFormatException e) {
            System.out.println("Erreur : Veuillez entrer des valeurs numériques pour les quantités.");
        }
    }

    public String genererRapport() {
        return suiviDechet.genererRapport();
    }
}
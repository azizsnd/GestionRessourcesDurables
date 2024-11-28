package Controller;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import Model.serviceSuivi.GestionRapportSuivi;
import Model.serviceSuivi.ServiceSuiviDechet;
import java.util.Date;

public class VueGestionRapport {
    ServiceSuiviDechet serviceDechet = new ServiceSuiviDechet(1,"Service de Suivi des Déchets",7, new Date(), "Actif");
    private GestionRapportSuivi gestionRapportSuivi = new GestionRapportSuivi(30, "Rapport de Déchets",serviceDechet);
    private VBox layout;
    private ListView<String> listViewRapports;
    private TextArea txtContenuRapport;

    public VueGestionRapport() {
        layout = new VBox(10);
        layout.setPadding(new Insets(10));
        Label lblTitre = new Label("Gestion des Rapports");

        listViewRapports = new ListView<>();
        txtContenuRapport = new TextArea();
        txtContenuRapport.setPromptText("Contenu du rapport");

        Button btnGenererRapport = new Button("Générer Rapport");

        // Ajouter les éléments dans le layout
        layout.getChildren().addAll(lblTitre, listViewRapports, txtContenuRapport, btnGenererRapport);

        // Action pour générer un rapport
        btnGenererRapport.setOnAction(e -> genererRapport());
    }

    private void genererRapport() {
        String contenu = txtContenuRapport.getText();
        gestionRapportSuivi.genererRapport(contenu);

        // Mettre à jour la vue avec le nouveau rapport
        listViewRapports.getItems().add("Rapport généré : " + contenu);
        txtContenuRapport.clear();
    }

    public VBox getLayout() {
        return layout;
    }
}

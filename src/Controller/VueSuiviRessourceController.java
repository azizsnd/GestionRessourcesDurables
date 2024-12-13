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
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

public class VueSuiviRessourceController {
    private ServiceSuiviRessource suiviRessource = new ServiceSuiviRessource("Ressource", 30, null, "Actif");
     @FXML
    private BarChart<String, Number> barChart;
    
    @FXML
    private BarChart<String, Number> barChart1;
    
    @FXML
    private LineChart<Number, Number> lineChart;
    
    @FXML
    private CategoryAxis barXAxis;
    
    @FXML
    private NumberAxis barYAxis;
    
    @FXML
    private CategoryAxis barXAxis1;
    
    @FXML
    private NumberAxis barYAxis1;
    
    @FXML
    private NumberAxis lineXAxis;
    
    @FXML
    private NumberAxis lineYAxis;

    @FXML
    public void initialize() {
        loadRessources();
    }

    private void loadRessources() {
        try {
            //listViewRessources.getItems().clear();
            suiviRessource.setResourcesSuivis(SuiviRessource.getAllRessources());

            for (Ressource ressource : suiviRessource.getResourcesSuivis()) {
                //listViewRessources.getItems().add(ressource.toString());
            }

        } catch (SQLException | ObjectifInvalideException e) {
            e.printStackTrace();
            Alert.showErrorAlert("Erreur de Chargement", e.getMessage());
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

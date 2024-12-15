package Controller;

import Model.entiteDurable.ObjectifInvalideException;
import Model.entiteDurable.Ressource;
import Model.serviceSuivi.GestionRapportSuivi;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Model.serviceSuivi.ServiceSuiviRessource;
import Services.SuiviRessource;
import Utils.Alert;
import Utils.ViewLoader;
import javafx.scene.control.Tooltip;

public class SuiviRessourceController {
    private ServiceSuiviRessource suiviRessource = new ServiceSuiviRessource("Ressource", 30, new Date(), "Actif");

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
        setupBarChartRessourceQuantity();
        setupBarChartRessourceSuivi();
        setupLineChartCoutTotal();
    }

    private void loadRessources() {
        try {
            suiviRessource.setResourcesSuivis(SuiviRessource.getAllRessources());
        } catch (SQLException | ObjectifInvalideException e) {
            e.printStackTrace();
            Alert.showErrorAlert("Erreur de Chargement", e.getMessage());
        }
    }
    @FXML
    public void ajouterRessource() {
        try {
            ViewLoader.loadPopup("../View/Components/ajoutRessource.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            Alert.showErrorAlert("Erreur", "Une erreur s'est produite : " + e.getMessage());
        }
    }
private void setupBarChartRessourceQuantity() {
    barChart.setTitle("Quantité de Ressources");
    barXAxis.setLabel("Ressource");
    barYAxis.setLabel("Quantité");
    
    XYChart.Series<String, Number> series = new XYChart.Series<>();
    series.setName("Utilisation Actuelle");

    for (Ressource ressource : suiviRessource.getResourcesSuivis()) {
        String shortName = ressource.getNom();
        if (shortName.length() > 10) {
            shortName = shortName.substring(0, 10) + "...";
        }
        
        XYChart.Data<String, Number> data = new XYChart.Data<>(shortName, ressource.getUtilisationActuelle());
        
        // Create tooltip
        Tooltip tooltip = new Tooltip(ressource.getNom());
        
        // Add listener to install tooltip when node is created
        data.nodeProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Install tooltip on the node
                Tooltip.install(newValue, tooltip);
            }
        });
        
        series.getData().add(data);
    }
    
    barChart.getData().clear();
    barChart.getData().add(series);
}
    private void setupBarChartRessourceSuivi() {
        barChart1.setTitle("Nombre de Ressources Suivies");
        barXAxis1.setLabel("Période");
        barYAxis1.setLabel("Nombre");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("2021",15));
        series.getData().add(new XYChart.Data<>("2022",20));
        series.getData().add(new XYChart.Data<>("2023",30));
        series.getData().add(new XYChart.Data<>("Actuel", suiviRessource.getResourcesSuivis().size()));

        barChart1.getData().clear();
        barChart1.getData().add(series);
    }

private void setupLineChartCoutTotal() {
    lineChart.setTitle("Cout Total de Ressources et Objectifs de Réduction");
    lineXAxis.setLabel("Temps (Mois)");
    lineYAxis.setLabel("Cout");

    XYChart.Series<Number, Number> coutTotalSeries = new XYChart.Series<>();
    coutTotalSeries.setName("Cout Total");

    XYChart.Series<Number, Number> objectifSeries = new XYChart.Series<>();
    objectifSeries.setName("Objectif de Réduction");

    double coutTotal = suiviRessource.getResourcesSuivis().stream()
        .mapToDouble(Ressource::calculerCoutTotal)
        .sum();

    for (int mois = 1; mois <= 6; mois++) {
        final int currentMonth = mois;
        double calculatedCout = coutTotal * (1 - 0.1 * currentMonth);
        coutTotalSeries.getData().add(new XYChart.Data<>(currentMonth, calculatedCout));

        double totalReduction = suiviRessource.getResourcesSuivis().stream()
            .mapToDouble(ressource -> {
                double resourceCout = ressource.calculerCoutTotal();
                double reductionPercentage = ressource.getObjectif().getReductionCible() / 100.0;
                return resourceCout * reductionPercentage * (1 - 0.1 * currentMonth); // Adjust reduction by month
            })
            .sum();

        double calculatedReduction = coutTotal - totalReduction;
        objectifSeries.getData().add(new XYChart.Data<>(currentMonth, calculatedReduction));

    }

    lineChart.getData().clear();
    lineChart.getData().addAll(coutTotalSeries, objectifSeries);
}




    @FXML
    public void genererRapport() {
        try {
            GestionRapportSuivi gestionRapportSuivi = new GestionRapportSuivi(3,
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
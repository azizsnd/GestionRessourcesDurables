package Controller;

import Model.entiteDurable.Dechet;
import Model.entiteDurable.ObjectifInvalideException;
import Model.serviceSuivi.GestionRapportSuivi;
import Model.serviceSuivi.ServiceSuiviDechet;
import Services.SuiviDechet;
import Utils.Alert;
import Utils.ViewLoader;

import javafx.fxml.FXML;
import javafx.scene.chart.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SuiviDechetController {
    private ServiceSuiviDechet serviceSuiviDechet = new ServiceSuiviDechet("Dechet", 30, new Date(), "Actif");

    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private PieChart pieChart;
    @FXML
    private LineChart<Number, Number> lineChart;
    @FXML
    private CategoryAxis barXAxis;
    @FXML
    private NumberAxis barYAxis;
    @FXML
    private NumberAxis lineXAxis;
    @FXML
    private NumberAxis lineYAxis;

    @FXML
    public void initialize() {
        loadDechets();
        setupBarChartQuantite();
        setupPieChartTauxRecyclage();
        setupLineChartObjectifs();
    }

    private void loadDechets() {
        try {
            serviceSuiviDechet.setDechetSuivis(SuiviDechet.getAllDechets());
        } catch (SQLException | ObjectifInvalideException e) {
            e.printStackTrace();
            Alert.showErrorAlert("Erreur de Chargement", e.getMessage());
        }
    }

    private void setupBarChartQuantite() {
        barChart.setTitle("Quantité de Déchets");
        barXAxis.setLabel("Type de Déchet");
        barYAxis.setLabel("Quantité");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Quantité Produite");

        for (Dechet dechet : serviceSuiviDechet.getDechetSuivis()) {
            series.getData().add(new XYChart.Data<>(dechet.getType(), dechet.getQuantiteProduite()));
        }

        barChart.getData().clear();
        barChart.getData().add(series);
    }

    private void setupPieChartTauxRecyclage() {
        pieChart.setTitle("Taux Recyclage Moyen");

        pieChart.getData().clear();
        for (Dechet dechet : serviceSuiviDechet.getDechetSuivis()) {
            double tauxRecyclage = (dechet.getQuantiteRecycle() / dechet.getQuantiteProduite()) * 100;
            pieChart.getData().add(new PieChart.Data(dechet.getType(), tauxRecyclage));
        }
    }

    private void setupLineChartObjectifs() {
        lineChart.setTitle("Quantité Totale de Déchets et Objectifs de Réduction");
        lineXAxis.setLabel("Temps (Mois)");
        lineYAxis.setLabel("Quantité Totale");

        XYChart.Series<Number, Number> totalSeries = new XYChart.Series<>();
        totalSeries.setName("Quantité Totale");

        XYChart.Series<Number, Number> objectifSeries = new XYChart.Series<>();
        objectifSeries.setName("Objectif de Réduction");

        double totalQuantite = serviceSuiviDechet.getDechetSuivis()
                .stream().mapToDouble(Dechet::getQuantiteProduite).sum();

        for (int mois = 1; mois <= 6; mois++) {
            double quantiteReduite = totalQuantite * (1 - 0.1 * mois);
            totalSeries.getData().add(new XYChart.Data<>(mois, totalQuantite));
            objectifSeries.getData().add(new XYChart.Data<>(mois, quantiteReduite));
        }

        lineChart.getData().clear();
        lineChart.getData().addAll(totalSeries, objectifSeries);
    }

    @FXML
    public void ajouterDechet() {
        try {
            ViewLoader.loadPopup("../View/Components/ajoutDechet.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            Alert.showErrorAlert("Erreur", "Une erreur s'est produite : " + e.getMessage());
        }
    }

    @FXML
    public void genererRapport() {
        try {
            GestionRapportSuivi rapport = new GestionRapportSuivi(
                2, 
                serviceSuiviDechet.getFrequenceRapport(),
                "Dechet",
                serviceSuiviDechet
            );
            rapport.genererRapportPourService();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String fileName = "rapport_Dechet_" + dateFormat.format(new Date()) + ".txt";
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
            java.io.File file = new java.io.File(fileName);

            if (file.exists()) {
                desktop.open(file);
            } else {
                Alert.showErrorAlert("Erreur", "Le fichier rapport n'a pas été trouvé.");
            }
            Alert.showInfoAlert("Succès", "Rapport généré avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
            Alert.showErrorAlert("Erreur", "Une erreur s'est produite lors de la génération du rapport : " + e.getMessage());
        }
    }
}

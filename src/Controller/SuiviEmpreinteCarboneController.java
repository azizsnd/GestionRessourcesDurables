package Controller;

import Model.entiteDurable.EmpreinteCarbone;
import Model.entiteDurable.ObjectifInvalideException;
import Model.serviceSuivi.GestionRapportSuivi;
import Model.serviceSuivi.ServiceSuiviCarbone;
import Services.SuiviCarbone;
import Utils.Alert;
import Utils.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SuiviEmpreinteCarboneController {

    private final ServiceSuiviCarbone suiviCarbone = new ServiceSuiviCarbone("EmpreinteCarbone", 30, new Date(), "Actif");

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis barXAxis;

    @FXML
    private NumberAxis barYAxis;

    @FXML
    private LineChart<Number, Number> lineChart;

    @FXML
    private NumberAxis lineXAxis;

    @FXML
    private NumberAxis lineYAxis;

    @FXML
    private PieChart pieChart;

    @FXML
    public void initialize() {
        loadEmpreintesCarbone();
        setupBarChartEmission();
        setupLineChartReduction();
        setupPieChartRepartition();
    }

    /**
     * Loads EmpreinteCarbone data into the controller.
     */
    private void loadEmpreintesCarbone() {
        try {
            suiviCarbone.setEmpreintesSuivis(SuiviCarbone.getAllEmpreintes());
        } catch (SQLException | ObjectifInvalideException e) {
            e.printStackTrace();
            Alert.showErrorAlert("Erreur de Chargement", "Impossible de charger les données des empreintes carbone : " + e.getMessage());
        }
    }

    /**
     * Opens the "Ajouter Empreinte Carbone" popup.
     */
    @FXML
    public void ajouterEmpreinteCarbone() {
        try {
            ViewLoader.loadPopup("../View/Components/ajoutEmpreinteCarbone.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            Alert.showErrorAlert("Erreur", "Erreur lors de l'ouverture de la fenêtre d'ajout : " + e.getMessage());
        }
    }

    /**
     * Opens the "Modifier Empreinte Carbone" popup.
     */
    @FXML
    public void modifierEmpreinteCarbone() {
        try {
            ViewLoader.loadPopup("../View/Components/modifierEmpreinteCarbone.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            Alert.showErrorAlert("Erreur", "Erreur lors de l'ouverture de la fenêtre de modification : " + e.getMessage());
        }
    }

    /**
     * Sets up the bar chart displaying annual emissions by source.
     */
    private void setupBarChartEmission() {
        barChart.setTitle("Émission Carbone Annuelle");
        barXAxis.setLabel("Sources d'Émissions");
        barYAxis.setLabel("Quantité (tonnes CO₂)");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Émissions Actuelles");

        for (EmpreinteCarbone empreinte : suiviCarbone.getEmpreintesSuivis()) {
            series.getData().add(new XYChart.Data<>(empreinte.getSourceEmission(), empreinte.getEmissionActuelle()));
        }

        barChart.getData().clear();
        barChart.getData().add(series);
    }

    /**
     * Sets up the line chart to display total emissions over time and reduction
     * goals.
     */
    private void setupLineChartReduction() {
        lineChart.setTitle("Tendance des Émissions Totales et Objectifs");
        lineXAxis.setLabel("Temps (Années)");
        lineYAxis.setLabel("Émissions (tonnes de CO₂)");

        XYChart.Series<Number, Number> totalEmissionSeries = new XYChart.Series<>();
        totalEmissionSeries.setName("Émissions Totales");

        XYChart.Series<Number, Number> reductionGoalSeries = new XYChart.Series<>();
        reductionGoalSeries.setName("Objectif de Réduction");

        double totalEmissions = suiviCarbone.getEmpreintesSuivis().stream()
                .mapToDouble(EmpreinteCarbone::getEmissionActuelle)
                .sum();

        for (int year = 1; year <= 5; year++) {
            double projectedEmissions = totalEmissions * (1 - 0.05 * year);
            totalEmissionSeries.getData().add(new XYChart.Data<>(year, projectedEmissions));

            double reductionGoal = totalEmissions * 0.8; // Goal: 20% reduction
            reductionGoalSeries.getData().add(new XYChart.Data<>(year, reductionGoal));
        }

        lineChart.getData().clear();
        lineChart.getData().addAll(totalEmissionSeries, reductionGoalSeries);
    }

    /**
     * Sets up the pie chart to show emission distribution by source.
     */
    private void setupPieChartRepartition() {
        pieChart.setTitle("Répartition des Émissions par Activité");
        pieChart.getData().clear();

        for (EmpreinteCarbone empreinte : suiviCarbone.getEmpreintesSuivis()) {
            pieChart.getData().add(new PieChart.Data(empreinte.getSourceEmission(), empreinte.getEmissionActuelle()));
        }
    }

    /**
     * Generates a report summarizing the carbon emission tracking data.
     */
    @FXML
    public void genererRapport() {
        try {
            suiviCarbone.suivi();
            GestionRapportSuivi gestionRapportSuivi = new GestionRapportSuivi(5,
                    suiviCarbone.getFrequenceRapport(),
                    "EmpreinteCarbone",
                    suiviCarbone
            );
            gestionRapportSuivi.genererRapportPourService();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String fileName = "rapport_EmpreinteCarbone_" + dateFormat.format(new Date()) + ".txt";

            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
            java.io.File file = new java.io.File(fileName);
            if (file.exists()) {
                desktop.open(file);
            } else {
                Alert.showErrorAlert("Erreur", "Le fichier rapport n'a pas été trouvé.");
            }
            Alert.showInfoAlert("Rapport Généré", "Le rapport a été généré avec succès : " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            Alert.showErrorAlert("Erreur", "Erreur lors de la génération du rapport : " + e.getMessage());
        }
    }
}

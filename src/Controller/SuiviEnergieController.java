package Controller;

import Model.entiteDurable.Energie;
import Model.entiteDurable.ObjectifInvalideException;
import Model.serviceSuivi.GestionRapportSuivi;
import Model.serviceSuivi.ServiceSuiviEnergie;
import Services.SuiviEnergie;
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

public class SuiviEnergieController {

    private final ServiceSuiviEnergie suiviEnergie = new ServiceSuiviEnergie("Energie", 30, new Date(), "Actif");

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
        loadEnergies();
        setupBarChartEnergieQuantity();
        setupLineChartConsommation();
        setupPieChartEnergieRenouvelable();
    }

    private void loadEnergies() {
        try {
            suiviEnergie.setSourcesEnergie(SuiviEnergie.getAllEnergies());
        } catch (SQLException | ObjectifInvalideException e) {
            e.printStackTrace();
            Alert.showErrorAlert("Erreur de Chargement", "Impossible de charger les données des énergies : " + e.getMessage());
        }
    }

    /**
     * Opens the "Ajouter Energie" popup.
     */
    @FXML
    public void ajouterEnergie() {
        try {
            ViewLoader.loadPopup("../View/Components/ajoutEnergie.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            Alert.showErrorAlert("Erreur", "Une erreur s'est produite lors de l'ouverture de la fenêtre d'ajout : " + e.getMessage());
        }
    }

    /**
     * Opens the "Modifier Energies" popup.
     */
    @FXML
    public void modifierEnergies() {
        try {
            ViewLoader.loadPopup("../View/Components/ajoutEnergie.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            Alert.showErrorAlert("Erreur", "Une erreur s'est produite lors de l'ouverture de la fenêtre de modification : " + e.getMessage());
        }
    }

    /**
     * Sets up the bar chart displaying the quantities of each energy.
     */
    private void setupBarChartEnergieQuantity() {
        barChart.setTitle("Quantité d'Énergies");
        barXAxis.setLabel("Énergie");
        barYAxis.setLabel("Quantité");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Quantité Actuelle");

        for (Energie energie : suiviEnergie.getSourcesEnergie()) {
            series.getData().add(new XYChart.Data<>(energie.getNom(), energie.getUtilisationActuelle()));
        }

        barChart.getData().clear();
        barChart.getData().add(series);
    }

    /**
     * Sets up the line chart to show energy consumption trends and reduction objectives.
     */
    private void setupLineChartConsommation() {
        lineChart.setTitle("Consommation d'Énergie et Objectifs");
        lineXAxis.setLabel("Temps (Mois)");
        lineYAxis.setLabel("Consommation");

        XYChart.Series<Number, Number> consommationSeries = new XYChart.Series<>();
        consommationSeries.setName("Consommation Totale");

        XYChart.Series<Number, Number> objectifSeries = new XYChart.Series<>();
        objectifSeries.setName("Objectif de Réduction");

        double totalConsommation = suiviEnergie.getSourcesEnergie().stream()
            .mapToDouble(Energie::calculerCoutTotal)
            .sum();

        for (int mois = 1; mois <= 6; mois++) {
            double consommationMois = totalConsommation * (1 - 0.1 * mois);
            consommationSeries.getData().add(new XYChart.Data<>(mois, consommationMois));

            double objectif = totalConsommation * 0.8; // Assuming a 20% reduction goal
            objectifSeries.getData().add(new XYChart.Data<>(mois, objectif));
        }

        lineChart.getData().clear();
        lineChart.getData().addAll(consommationSeries, objectifSeries);
    }

    /**
     * Sets up the pie chart to display the percentage of renewable energy usage.
     */
    private void setupPieChartEnergieRenouvelable() {
        pieChart.setTitle("Taux Energie Renouvelable");
        pieChart.getData().clear();

        double total = suiviEnergie.getSourcesEnergie().stream()
            .mapToDouble(Energie::getUtilisationActuelle)
            .sum();

        double renouvelable = suiviEnergie.getSourcesEnergie().stream()
            .filter(Energie::estRenouvelable)
            .mapToDouble(Energie::getUtilisationActuelle)
            .sum();

        double nonRenouvelable = total - renouvelable;

        pieChart.getData().addAll(
            new PieChart.Data("Renouvelable", renouvelable),
            new PieChart.Data("Non Renouvelable", nonRenouvelable)
        );
    }

    /**
     * Generates a report for the energy tracking data.
     */
    @FXML
    public void genererRapport() {
        try {
            GestionRapportSuivi gestionRapportSuivi = new GestionRapportSuivi(4,
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

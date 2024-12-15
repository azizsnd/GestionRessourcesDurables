package Controller;

import Model.serviceSuivi.GestionRapportSuivi;
import Model.serviceSuivi.Rapport;
import Services.RapportDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class GestionRapportController implements Initializable {

    @FXML
    private Button createReportButton;
    @FXML
    private Button editReportButton;
    @FXML
    private Button deleteReportButton;
    @FXML
    private Button viewReportButton;
    @FXML
    private Button exportReportButton;

    @FXML
    private TextField searchField;
    @FXML
    private TreeView<String> rapportTreeView;

    @FXML
    private TextField titleField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextArea summaryArea;

    private Rapport selectedRapport;
    GestionRapportSuivi gestionRapportSuivi = new GestionRapportSuivi(7, "Export", null); // Example frequency and type, service is null for simplicity

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadRapportsFromDatabase();
        setupButtons();
        rapportTreeView.setOnMouseClicked(this::handleTreeItemClick);

    }

    private void loadRapportsFromDatabase() {
        try {
            TreeItem<String> root = new TreeItem<>("Rapports");

            // Create child nodes for each service type
            TreeItem<String> ressourceNode = new TreeItem<>("Ressource");
            TreeItem<String> energieNode = new TreeItem<>("Energie");
            TreeItem<String> empreinteCarboneNode = new TreeItem<>("Empreinte Carbone");
            TreeItem<String> dechetsNode = new TreeItem<>("Déchets");

            root.getChildren().addAll(ressourceNode, energieNode, empreinteCarboneNode, dechetsNode);

            // Load reports from database
            List<Rapport> rapports = RapportDAO.loadAllRapports();
            gestionRapportSuivi.setListeRapports(rapports);
            for (Rapport rapport : rapports) {
                String displayText = rapport.getTypeService() + " - " + rapport.getDateRapport();
                TreeItem<String> item = new TreeItem<>(displayText);

                // Add the item to the appropriate category
                switch (rapport.getTypeService()) {
                    case "Ressource":
                        ressourceNode.getChildren().add(item);
                        break;
                    case "Energie":
                        energieNode.getChildren().add(item);
                        break;
                    case "EmpreinteCarbone":
                        empreinteCarboneNode.getChildren().add(item);
                        break;
                    case "Dechet":
                        dechetsNode.getChildren().add(item);
                        break;
                    default:
                        // Optionally handle unknown service types
                        break;
                }
            }

            rapportTreeView.setRoot(root);
            root.setExpanded(true); // Expand the root node by default
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de charger les rapports depuis la base de données.");
        }
    }


private void handleTreeItemClick(MouseEvent event) {
    TreeItem<String> selectedItem = rapportTreeView.getSelectionModel().getSelectedItem();

    if (selectedItem != null && selectedItem.isLeaf()) { // Ensure it's a leaf node
        String displayText = selectedItem.getValue();

        try {
            // Match the display text to fetch the correct report
            selectedRapport = gestionRapportSuivi.getListeRapports().stream()
                .filter(rapport -> (rapport.getTypeService() + " - " + rapport.getDateRapport()).equals(displayText))
                .findFirst()
                .orElse(null);

            if (selectedRapport != null) {
                displayRapportDetails(selectedRapport);
            } else {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Le rapport sélectionné est introuvable.");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la sélection du rapport.");
        }
    } else {
        System.out.println("Selected a category, not a report.");
    }
}



    private void displayRapportDetails(Rapport rapport) {
        titleField.setText(rapport.getTypeService());
        datePicker.setValue(new java.sql.Date(rapport.getDateRapport().getTime()).toLocalDate());
        summaryArea.setText(rapport.getContenuRapport());
    }

    private void setupButtons() {
        createReportButton.setOnAction(event -> createReport());
        editReportButton.setOnAction(event -> editReport());
        deleteReportButton.setOnAction(event -> deleteReport());
        viewReportButton.setOnAction(event -> viewReport());
        exportReportButton.setOnAction(event -> exportReport());
        searchField.setOnKeyTyped(event -> searchReports());

    }

    private void createReport() {
        String title = titleField.getText();
        String summary = summaryArea.getText();
        java.sql.Date date = java.sql.Date.valueOf(datePicker.getValue());

        if (title.isEmpty() || summary.isEmpty() || date == null) {
            showAlert(Alert.AlertType.WARNING, "Champs manquants", "Veuillez remplir tous les champs.");
            return;
        }

        Rapport newRapport = new Rapport(title, summary);
        newRapport.setDateRapport(date);

        try {
            RapportDAO.saveRapportToDatabase(newRapport);
            showAlert(Alert.AlertType.INFORMATION, "Succès", "Rapport créé avec succès.");
            loadRapportsFromDatabase();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la création du rapport.");
        }
    }

    private void editReport() {
        if (selectedRapport == null) {
            showAlert(Alert.AlertType.WARNING, "Aucun rapport sélectionné", "Veuillez sélectionner un rapport à modifier.");
            return;
        }

        selectedRapport.setTypeService(titleField.getText());
        selectedRapport.setContenuRapport(summaryArea.getText());
        selectedRapport.setDateRapport(java.sql.Date.valueOf(datePicker.getValue()));

        try {
            RapportDAO.updateRapport(selectedRapport);
            showAlert(Alert.AlertType.INFORMATION, "Succès", "Rapport modifié avec succès.");
            loadRapportsFromDatabase();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la modification du rapport.");
        }
    }

    private void deleteReport() {
        if (selectedRapport == null) {
            showAlert(Alert.AlertType.WARNING, "Aucun rapport sélectionné", "Veuillez sélectionner un rapport à supprimer.");
            return;
        }
        try {
            RapportDAO.deleteRapport(selectedRapport.getId());
            showAlert(Alert.AlertType.INFORMATION, "Succès", "Rapport supprimé avec succès.");
            loadRapportsFromDatabase();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la suppression du rapport.");
        }
    }

    private void viewReport() {
        if (selectedRapport != null) {
            showAlert(Alert.AlertType.INFORMATION, "Détails du Rapport", selectedRapport.toString());
        } else {
            showAlert(Alert.AlertType.WARNING, "Aucun rapport sélectionné", "Veuillez sélectionner un rapport à afficher.");
        }
    }

    private void exportReport() {
        if (selectedRapport == null) {
            showAlert(Alert.AlertType.WARNING, "Aucun rapport sélectionné", "Veuillez sélectionner un rapport à exporter.");
            return;
        }

        try {
            // Use GestionRapportSuivi's method to save the report locally
            gestionRapportSuivi.sauvegarderRapportFichier(selectedRapport);

            showAlert(Alert.AlertType.INFORMATION, "Exportation réussie", "Le rapport a été exporté avec succès.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de l'exportation du rapport : " + e.getMessage());
        }
    }
    private void searchReports() {
        String query = searchField.getText().toLowerCase();

        try {
            TreeItem<String> root = new TreeItem<>("Filtered Rapports");

            for (Rapport rapport : gestionRapportSuivi.getListeRapports()) {
                if (rapport.getTypeService().toLowerCase().contains(query) || 
                    rapport.getContenuRapport().toLowerCase().contains(query)) {

                    TreeItem<String> item = new TreeItem<>(rapport.getTypeService() + " - " + rapport.getDateRapport());
                    root.getChildren().add(item);
                }
            }

            rapportTreeView.setRoot(root);
            root.setExpanded(true);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de filtrer les rapports.");
        }
    }


    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

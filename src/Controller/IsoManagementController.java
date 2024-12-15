package Controller;

import Model.Reglementation.NormeIso;
import Services.NormeIsoService;
import Utils.Alert; // Import the custom Alert class
import controller.PopupISOController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class IsoManagementController {

    @FXML
    private TextField searchBar;

    @FXML
    private Button searchButton, addIsoButton, editIsoButton, deleteIsoButton;

    @FXML
    private ListView<String> isoListView;

    private List<NormeIso> isoNorms;  // List of ISO Norms

    public void initialize() {
        loadIsoNorms();
    }

    private void loadIsoNorms() {
        try {
            isoNorms = NormeIsoService.getAllNormes();
            updateIsoListView();
        } catch (Exception e) {
            e.printStackTrace();
            Alert.showErrorAlert("Erreur", "Impossible de charger les normes ISO.");
        }
    }

    private void updateIsoListView() {
        isoListView.getItems().clear();
        for (NormeIso norme : isoNorms) {
            isoListView.getItems().add(norme.toString());
        }
    }

    @FXML
    private void onSearchClicked(ActionEvent event) {
        String query = searchBar.getText().trim().toLowerCase();
        isoListView.getItems().clear();
        for (NormeIso norme : isoNorms) {
            if (norme.getDescriptionNorme().toLowerCase().contains(query)) {
                isoListView.getItems().add(norme.toString());
            }
        }
    }

    @FXML
    private void ajoutIso() {
        openIsoPopup(null);
        loadIsoNorms();
    }

    @FXML
    private void onEditIsoClicked(ActionEvent event) {
        int selectedIndex = isoListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            NormeIso selectedNorme = isoNorms.get(selectedIndex);
            openIsoPopup(selectedNorme);
            loadIsoNorms();
        } else {
            Alert.showWarningAlert("Avertissement", "Veuillez sélectionner une norme à modifier.");
        }
    }


    private void openIsoPopup(NormeIso norme) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Components/popupISO.fxml"));
            Parent root = loader.load();

            PopupISOController popupController = loader.getController();
            popupController.setOnIsoSavedCallback(this::loadIsoNorms); // Refresh list on save
            
            // Pass existing data if editing
            if (norme != null) {
                popupController.populateForm(norme); // Implement this method in PopupISOController
            }

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            Alert.showErrorAlert("Erreur", "Une erreur est survenue lors de l'ouverture de la popup.");
        }
    }

    @FXML
    private void onDeleteIsoClicked(ActionEvent event) {
        int selectedIndex = isoListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
                NormeIso normeToDelete = isoNorms.get(selectedIndex);
                try {
                    NormeIsoService.deleteNorme(normeToDelete.getId());
                    loadIsoNorms();
                } catch (Exception e) {
                    e.printStackTrace();
                    Alert.showErrorAlert("Erreur", "Impossible de supprimer la norme ISO.");
                }
        } else {
            Alert.showWarningAlert("Avertissement", "Veuillez sélectionner une norme à supprimer.");
        }
    }

}

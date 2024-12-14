package View.Components;

import Model.Reglementation.NormeIso;
import Utils.Alert;
import Utils.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IsoManagementController {

    @FXML
    private TextField searchBar;

    @FXML
    private Button searchButton;

    @FXML
    private Button addIsoButton;

    @FXML
    private Button editIsoButton;

    @FXML
    private Button deleteIsoButton;

    @FXML
    private ListView<String> isoListView;

    // Temporary list to simulate ISO norms
    private List<NormeIso> isoNorms;

    public void initialize() {
        // Initialize the list of ISO norms
        isoNorms = new ArrayList<>();
        isoListView.getItems().clear();
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
    public void ajoutIso() {
        try {
            ViewLoader.loadPopup("../View/Components/popupISO.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            Alert.showErrorAlert("Erreur", "Une erreur s'est produite : " + e.getMessage());
        }
    }

    @FXML
    private void onEditIsoClicked(ActionEvent event) {
        int selectedIndex = isoListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            NormeIso selectedNorme = isoNorms.get(selectedIndex);
            System.out.println("Modifier: " + selectedNorme);
            // Add logic to open a popup for editing the selected ISO
        }
    }

    @FXML
    private void onDeleteIsoClicked(ActionEvent event) {
        int selectedIndex = isoListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            isoNorms.remove(selectedIndex);
            isoListView.getItems().remove(selectedIndex);
        }
    }
}

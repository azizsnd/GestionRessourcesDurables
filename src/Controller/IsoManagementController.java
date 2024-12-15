package Controller;

import Model.Reglementation.NormeIso;
import Services.NormeIsoService;
import Utils.Alert;
import Utils.ViewLoader;
import controller.PopupISOController;
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

    // Update the ListView with fetched ISO norms
    private void updateIsoListView() {
        isoListView.getItems().clear();
        if (isoNorms != null) {
            for (NormeIso norme : isoNorms) {
                isoListView.getItems().add(norme.toString()); // Assuming `toString()` provides a meaningful description
            }
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
// This method will be triggered when a new ISO is saved from the popup
    private void refreshIsoList() {
        // Fetch the latest ISO norms from the database
        try {
            List<NormeIso> isoNorms = NormeIsoService.getAllNormes(); // Fetch data from your service
            isoListView.getItems().clear();

            // Add each ISO norm to the ListView
            for (NormeIso norme : isoNorms) {
                isoListView.getItems().add(norme.toString()); // You can customize what to display
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the error
        }
    }

    // Triggered when the "Ajouter ISO" button is clicked
    @FXML
    public void ajoutIso() {
        try {
            // Load the popup and pass the callback
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Components/popupISO.fxml"));
            Parent root = loader.load();

            PopupISOController popupController = loader.getController();
            popupController.setOnIsoSavedCallback(this::refreshIsoList);  // Set the callback
            
            // Show the popup
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);  // Make it modal
            stage.setScene(new Scene(root));
            stage.showAndWait();  // Wait until the popup is closed
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the error
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

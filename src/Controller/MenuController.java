package Controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class MenuController {

    @FXML
    private Button deconnecter;

    @FXML
    private HBox SuiviObjectif;

    @FXML
    private HBox suiviDechet;

    @FXML
    private HBox SuiviCarbone;

    @FXML
    private HBox SuiviEnergie;

    @FXML
    private HBox SuiviRessource;

    @FXML
    private void deconnecter() throws IOException {
        loadPage("../View/Login.fxml", deconnecter);
    }

    @FXML
    private void navigateToSuiviRessource() throws IOException {
        loadPage("../View/VueSuiviRessource.fxml", SuiviRessource);
    }

    @FXML
    private void navigateToSuiviEnergie() throws IOException {
        loadPage("../View/VueSuiviEnergie.fxml", SuiviEnergie);
    }

    @FXML
    private void navigateToSuiviCarbone() throws IOException {
        loadPage("../View/VueSuiviCarbone.fxml", SuiviCarbone);
    }

    @FXML
    private void navigateToSuiviDechet() throws IOException {
        loadPage("../View/VueSuiviDechet.fxml", suiviDechet);
    }

    @FXML
    private void navigateToSuiviObjectif() throws IOException {
        loadPage("../View/VueSuiviObjectif.fxml", SuiviObjectif);
    }

    private void loadPage(String fxmlPath, Button button) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent newPage = loader.load();
        Stage currentStage = (Stage) button.getScene().getWindow();
        currentStage.setScene(new Scene(newPage));
    }
    private void loadPage(String fxmlPath, HBox button) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent newPage = loader.load();
        Stage currentStage = (Stage) button.getScene().getWindow();
        currentStage.setScene(new Scene(newPage));
    }
    @FXML
    public void initialize() {
        // Initialization code if needed
    }
}

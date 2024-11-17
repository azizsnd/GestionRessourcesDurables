package Controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.control.Button;

public class MenuController {

    @FXML
    private Button deconnecter;

    @FXML
    private Button SuiviObjectif;

    @FXML
    private Button suiviDechet;

    @FXML
    private Button SuiviCarbone;

    @FXML
    private Button SuiviEnergie;

    @FXML
    private Button SuiviRessource;

    @FXML
    private Button profil;

    @FXML
    private void deconnecter() throws IOException {
        loadPage("../View/Login.fxml", deconnecter);
    }

    @FXML
    private void navigateToProfil() throws IOException {
       //
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

    @FXML
    public void initialize() {
        // Initialization code if needed
    }
}

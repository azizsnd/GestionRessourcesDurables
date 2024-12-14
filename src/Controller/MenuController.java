package Controller;

import Utils.ViewLoader;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
    private VBox sidebar;

    @FXML
    private void deconnecter() throws IOException {
        navigateTo("../View/Login.fxml", deconnecter);
    }

    @FXML
    private void navigateToSuiviRessource() throws IOException {
        navigateTo("../View/VueSuiviRessource.fxml", SuiviRessource);
    }

    @FXML
    private void navigateToSuiviEnergie() throws IOException {
        navigateTo("../View/VueSuiviEnergie.fxml", SuiviEnergie);
    }

    @FXML
    private void navigateToSuiviCarbone() throws IOException {
        navigateTo("../View/VueSuiviCarbone.fxml", SuiviCarbone);
    }

    @FXML
    private void navigateToSuiviDechet() throws IOException {
        navigateTo("../View/VueSuiviDechet.fxml", suiviDechet);
    }

    @FXML
    private void navigateToSuiviObjectif() throws IOException {
        navigateTo("../View/VueSuiviObjectif.fxml", SuiviObjectif);
    }

        private void navigateTo(String fxmlPath, Node node) {
            Stage stage = (Stage) node.getScene().getWindow();
            ViewLoader.load(fxmlPath, stage);
        }
    public void initialize() {
    }
}

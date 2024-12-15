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

    private HBox SuiviObjectif;

    private HBox suiviDechet;

    private HBox SuiviCarbone;

    private HBox SuiviEnergie;

    private HBox SuiviRessource;
    @FXML
    private VBox sidebar;
    @FXML
    private HBox ManagementISO;
    @FXML
    private HBox Audit;
    @FXML
    private HBox PAC;

    @FXML
    private void deconnecter() throws IOException {
        navigateTo("../View/Login.fxml", deconnecter);
    }

    private void navigateToSuiviRessource() throws IOException {
        navigateTo("../View/VueSuiviRessource.fxml", SuiviRessource);
    }

    private void navigateToSuiviEnergie() throws IOException {
        navigateTo("../View/VueSuiviEnergie.fxml", SuiviEnergie);
    }

    private void navigateToSuiviCarbone() throws IOException {
        navigateTo("../View/VueSuiviCarbone.fxml", SuiviCarbone);
    }

    private void navigateToSuiviDechet() throws IOException {
        navigateTo("../View/VueSuiviDechet.fxml", suiviDechet);
    }

    private void navigateToSuiviObjectif() throws IOException {
        navigateTo("../View/VueGestionRapport.fxml", SuiviObjectif);
    }

        private void navigateTo(String fxmlPath, Node node) {
            Stage stage = (Stage) node.getScene().getWindow();
            ViewLoader.load(fxmlPath, stage);
        }
    public void initialize() {
    }
}

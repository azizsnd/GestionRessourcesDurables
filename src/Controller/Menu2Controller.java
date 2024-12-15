package Controller;

import Utils.ViewLoader;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Menu2Controller {

    @FXML
    private Button deconnecter;


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
    @FXML
    private void navigateToManagementISO() throws IOException {
        navigateTo("../View/Components/isoManagement.fxml", ManagementISO);
    }
    @FXML

    private void navigateToAudit() throws IOException {
        navigateTo("../View/Components/Audit Enviromental.fxml", Audit);
    }
    @FXML

    private void navigateToPAC() throws IOException {
        navigateTo("../View/Components/planDaction.fxml", PAC);
    }
        private void navigateTo(String fxmlPath, Node node) {
            Stage stage = (Stage) node.getScene().getWindow();
            ViewLoader.load(fxmlPath, stage);
        }
    public void initialize() {
    }
}

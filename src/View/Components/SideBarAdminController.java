/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package View.Components;

import Utils.ViewLoader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jasser
 */
public class SideBarAdminController implements Initializable {

    @FXML
    private VBox sidebar;
    @FXML
    private HBox Profil;
    @FXML
    private HBox GestionUtilisateur;
    @FXML
    private Button deconnecter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML

    private void navigateToProfile() throws IOException {
        navigateTo("../View/VueProfilAdmin.fxml", Profil);
    }
        @FXML

        private void navigateToUsers() throws IOException {
        navigateTo("../View/VueGestionUser.fxml", GestionUtilisateur);
    }

            private void navigateTo(String fxmlPath, Node node) {
            Stage stage = (Stage) node.getScene().getWindow();
            ViewLoader.load(fxmlPath, stage);
        }
    @FXML
    private void deconnecter() throws IOException {
        navigateTo("../View/Login.fxml", deconnecter);
    }
}

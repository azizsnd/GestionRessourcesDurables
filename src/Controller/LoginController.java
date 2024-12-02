
package Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import com.gluonhq.charm.glisten.control.TextField;
import com.jfoenix.controls.JFXButton;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private JFXButton loginButton;


    @FXML
    private void handleLoginButton(ActionEvent event) throws IOException {
        // Création d'une instance de FXMLLoader et chargement du fichier FXML

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/VueSuiviRessource.fxml"));
        Parent newPage = loader.load();

        // Obtenir la scène actuelle et changer la page affichée
        Stage currentStage = (Stage) loginButton.getScene().getWindow();
        currentStage.setScene(new Scene(newPage));
    }
}

package Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;


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

package Controller;
import Model.Utilisateurs.Utilisateur;
import Services.ServiceUser;
import Utils.ViewLoader;
import Utils.Alert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
        String username = userField.getText();
        String password = passwordField.getText();
        Utilisateur user = ServiceUser.authenticateUser(username, password);
        
    if (user != null) {
            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            if (user instanceof Model.Utilisateurs.Administrateur) {
                ViewLoader.load("../View/VueSuiviRessource.fxml", currentStage);
            } else {
                ViewLoader.load("../View/VueSuiviRessource.fxml", currentStage);
            }
          } else {
                   Alert.showErrorAlert("Login Failed", "Invalid username or password.");
               }
        }
   }
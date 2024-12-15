package View.Components;

import Model.Utilisateurs.Utilisateur;
import Services.ServiceUser;
import Utils.Alert;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddUserPopupController implements Initializable {

    @FXML
    private TextField Nom;

    @FXML
    private PasswordField password;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private Button addButton;

    private Utilisateur editableUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeComboBox.getItems().addAll("Administrateur", "Auditeur", "Responsable RSE");

        addButton.setOnAction(event -> handleSave());
    }

    private void handleSave() {
        String nom = Nom.getText();
        String pwd = password.getText();
        String type = typeComboBox.getValue();

        if (nom.isEmpty() || pwd.isEmpty() || type == null) {
            Alert.showWarningAlert("Champs requis", "Veuillez remplir tous les champs.");
            return;
        }

        if (editableUser == null) {
            ServiceUser.addUser(nom, pwd, type);
            Alert.showInfoAlert("Succès", "Utilisateur ajouté avec succès !");
        } else {
            editableUser.setNom(nom);
            editableUser.setMotDePasse(pwd);
            editableUser.setType(type);
            ServiceUser.updateUser(editableUser);
            Alert.showInfoAlert("Succès", "Utilisateur mis à jour avec succès !");
        }

        closePopup();
    }

    public void setUserData(Utilisateur user) {
        this.editableUser = user;
        Nom.setText(user.getNom());
        password.setText(user.getMotDePasse());
        typeComboBox.setValue(user.getType());
        addButton.setText("Modifier");     }

    private void closePopup() {
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package View.Components;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class AddUserPopupController implements Initializable {

    @FXML
    private TextField Nom;
    @FXML
    private PasswordField password;
    @FXML
    private TextField Poste;
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private Button addButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeComboBox.getItems().addAll("Administrateur", "Auditeur", "Responsable RSE");
    }    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package View.Jasser;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jasser
 */
public class AjoutEnergieController implements Initializable {

    @FXML
    private TextField nomField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private DatePicker dateCreationPicker;
    @FXML
    private TextField objectifField;
    @FXML
    private TextField uniteDeMesureField;
    @FXML
    private TextField utilisationReferenceField;
    @FXML
    private TextField utilisationActuelleField;
    @FXML
    private TextField coutParUniteField;
    @FXML
    private CheckBox estRenouvelableField;
    @FXML
    private TextField typeField;
    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

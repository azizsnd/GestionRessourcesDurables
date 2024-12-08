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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;

/**
 * FXML Controller class
 *
 * @author Jasser
 */
public class GestionRapportController implements Initializable {

    @FXML
    private TextField searchField;
    @FXML
    private Button createReportButton;
    @FXML
    private Button editReportButton;
    @FXML
    private Button deleteReportButton;
    @FXML
    private Button exportReportButton;
    @FXML
    private TreeView<?> entityTreeView;
    @FXML
    private TextField titleField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextArea summaryArea;
    @FXML
    private Button viewReportButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

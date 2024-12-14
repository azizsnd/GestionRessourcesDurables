package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AjoutRessourceController implements Initializable {

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
    private Button addButton;
    @FXML
    private Button cancelButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO: Initialize if needed
    }

    // Mouse Entered Event for addButton
    @FXML
    public void handleMouseEnteredAddButton(MouseEvent event) {
        addButton.setStyle("-fx-background-color: #619E4F; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 15;");
    }

    // Mouse Exited Event for addButton
    @FXML
    public void handleMouseExitedAddButton(MouseEvent event) {
        addButton.setStyle("-fx-background-color: #6BDB48; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 15;");
    }

    // Mouse Entered Event for cancelButton
    @FXML
    public void handleMouseEnteredCancelButton(MouseEvent event) {
        cancelButton.setStyle("-fx-background-color: #2B3329; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 15;");
    }

    // Mouse Exited Event for cancelButton
    @FXML
    public void handleMouseExitedCancelButton(MouseEvent event) {
        cancelButton.setStyle("-fx-background-color: #505C4C; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 15;");
    }
}

package Controller;

import Model.Reglementation.Reglementation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddExigenceController {

    @FXML
    private TextField nomExigenceField;

    @FXML
    private TextArea descriptionExigenceField;

    @FXML
    private Button addButton;

    private Reglementation exigence; // Objet à retourner à la fenêtre principale
    

    @FXML
    public void initialize() {
        addButton.setOnAction(event -> ajouterExigence());
    }

    private void ajouterExigence() {
        String nom = nomExigenceField.getText();
        String description = descriptionExigenceField.getText();

        if (!nom.isEmpty() && !description.isEmpty()) {
            exigence = new Reglementation(0, nom, description, new java.util.Date());
            fermerPopup();
        }
    }

    public Reglementation getExigence() {
        return exigence;
    }

    private void fermerPopup() {
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }

}

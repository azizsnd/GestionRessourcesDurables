package Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;
import javafx.stage.Modality;

public class ViewLoader {
    public static void load(String fxmlPath, Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(ViewLoader.class.getResource(fxmlPath));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load FXML file: " + fxmlPath);
        }
    }
    public static void loadPopup(String fxmlPath)  {
        try {
            // Load the FXML file for the 'Ajout Ressource' interface
            FXMLLoader fxmlLoader = new FXMLLoader(ViewLoader.class.getResource(fxmlPath));
            Parent root = fxmlLoader.load();

            // Create a new stage for the popup window
            Stage stage = new Stage();
            stage.setTitle("Ajouter Ressource");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // Ensures this is a modal window
            stage.showAndWait(); // Wait for the window to close before returning
        } catch (IOException e) {
            e.printStackTrace();
            Alert.showErrorAlert("Erreur", "Impossible de charger l'interface Ajout Ressource : " + e.getMessage());
        }
    }

    public static void loadPopup(String fxmlPath, Consumer<Object> controllerInitializer) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ViewLoader.class.getResource(fxmlPath));
            Parent root = fxmlLoader.load();

            // Initialize the controller
            Object controller = fxmlLoader.getController();
            controllerInitializer.accept(controller);

            Stage stage = new Stage();
            stage.setTitle("Modifier Ressource");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            Alert.showErrorAlert("Erreur", "Impossible de charger l'interface : " + e.getMessage());
        }
    }
}

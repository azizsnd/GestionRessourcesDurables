package serviceSuivi;

import serviceSuivi.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Services extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Services.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Services");
       
        primaryStage.setScene(scene);

        primaryStage.setWidth(800); // largeur souhaitée
        primaryStage.setHeight(600); // hauteur souhaitée
        primaryStage.setResizable(true); // rendre la fenêtre non redimensionnable (si nécessaire)
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

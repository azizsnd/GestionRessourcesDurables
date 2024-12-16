
package GestionRessourcesDurables;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GestionRessourcesDurables extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../View/Login.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Login");
       
        primaryStage.setScene(scene);

        primaryStage.setWidth(1024.0); 
        primaryStage.setHeight(730); 
        primaryStage.setResizable(false); 
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}

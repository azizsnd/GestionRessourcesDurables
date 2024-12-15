
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
        Parent root = FXMLLoader.load(getClass().getResource("../View/VueProfilAuditeur.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Login");
       
        primaryStage.setScene(scene);

        primaryStage.setWidth(1024.0); // largeur souhaitée
        primaryStage.setHeight(730); // hauteur souhaitée
        primaryStage.setResizable(false); // rendre la fenêtre non redimensionnable (si nécessaire)
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}

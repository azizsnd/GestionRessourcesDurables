package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Jasser
 */
public class PlanDactionController implements Initializable {

    @FXML
    private Button addNewPlanButton;
    @FXML
    private Button closeButton;
    @FXML
    private ListView<?> actionPlansListView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

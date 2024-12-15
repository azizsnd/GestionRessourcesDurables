package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import Model.Utilisateurs.Utilisateur;
import Utils.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProfileController implements Initializable {

    @FXML
    private ImageView profileImage;

    @FXML
    private TextField nomField;

    @FXML
    private TextField IdField;

    @FXML
    private TextField posteField;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SessionManager session = SessionManager.getInstance();
        Utilisateur currentUser = session.getCurrentUser();

        if (currentUser != null) {
            IdField.setText(String.valueOf(currentUser.getId())); 
            nomField.setText(currentUser.getNom()); 
            posteField.setText(currentUser.getType());

            loadProfileImage();
        } else {
            System.out.println("No user logged in.");
        }
        applyRoundedImageStyle();

    }

    private void loadProfileImage() {
        try {
            URL imageUrl = null;
            if ("Auditeur".equals(SessionManager.getInstance().getRole())) {
                imageUrl = getClass().getResource("/images/Auditeurprofil.jpg");
            } else {
                imageUrl = getClass().getResource("/images/profil.jpg");
            }
            if (imageUrl != null) {
                Image image = new Image(imageUrl.toExternalForm());
                profileImage.setImage(image);
            } else {
                System.out.println("Image not found at: /images/profil.jpg");
            }
        } catch (Exception e) {
            System.out.println("Failed to load profile image: " + e.getMessage());
        }
    }

    private void applyRoundedImageStyle() {
        javafx.scene.shape.Rectangle clip = new javafx.scene.shape.Rectangle(
                profileImage.getFitWidth(), profileImage.getFitHeight()
        );
        clip.setArcWidth(50); 
        clip.setArcHeight(50);
        profileImage.setClip(clip);
    }
}

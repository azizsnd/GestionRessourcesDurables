package Controller;

import Model.Utilisateurs.Utilisateur;
import Services.ServiceUser;
import Utils.Alert;
import Utils.ViewLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserManagementController {

    @FXML
    private TableView<Utilisateur> userTable;

    @FXML
    private TableColumn<Utilisateur, Integer> idColumn; // ID column added

    @FXML
    private TableColumn<Utilisateur, String> nameColumn;

    @FXML
    private TableColumn<Utilisateur, String> roleColumn;

    @FXML
    public void initialize() {
        userTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id")); 
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom")); 
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("type")); 
        loadUsers();
    }

    private void loadUsers() {
        ObservableList<Utilisateur> users = FXCollections.observableArrayList(ServiceUser.getAllUtilisateurs());
        userTable.setItems(users);
        
    }

    @FXML
    private void handleEditUser() {
        Utilisateur selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            System.out.println("Editing user: " + selectedUser.getNom());
            // Implement edit functionality
        }
    }

    @FXML
    private void handleDeleteUser() {
        Utilisateur selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            System.out.println("Deleting user: " + selectedUser.getNom());
            // Implement delete functionality
        }
    }

    @FXML
    private void handleAddUser() {
        try {
            ViewLoader.loadPopup("../View/Components/AddUserPopup.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            Alert.showErrorAlert("Erreur", "Une erreur s'est produite : " + e.getMessage());
        }
    }

    @FXML
    private void handleBack() {
        System.out.println("Returning to the previous screen.");
        // Implement back functionality
    }
}

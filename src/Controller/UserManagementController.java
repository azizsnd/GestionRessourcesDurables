package Controller;

import Model.Utilisateurs.Utilisateur;
import Services.ServiceUser;
import Utils.Alert;
import Utils.ViewLoader;
import View.Components.AddUserPopupController;
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
    private TableColumn<Utilisateur, Integer> idColumn; 
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
        try {
            ViewLoader.loadPopup("../View/Components/AddUserPopup.fxml", controller -> {
                AddUserPopupController popupController = (AddUserPopupController) controller;
                popupController.setUserData(selectedUser); // Pass selected user to the popup controller
            });
            loadUsers();
        } catch (Exception e) {
            e.printStackTrace();
            Alert.showErrorAlert("Erreur", "Une erreur s'est produite : " + e.getMessage());
        }
    } else {
        Alert.showWarningAlert("Sélection requise", "Veuillez sélectionner un utilisateur à modifier.");
    }
}

    @FXML
    private void handleDeleteUser() {
        Utilisateur selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
                ServiceUser.deleteUser(selectedUser.getId());
                loadUsers();
        } else {
            Alert.showWarningAlert("Sélection requise", "Veuillez sélectionner un utilisateur à supprimer.");
        }
    }

    @FXML
    private void handleAddUser() {
        try {
            ViewLoader.loadPopup("../View/Components/AddUserPopup.fxml");
            loadUsers();
        } catch (Exception e) {
            e.printStackTrace();
            Alert.showErrorAlert("Erreur", "Une erreur s'est produite : " + e.getMessage());
        }
    }

    @FXML
    private void handleBack() {
        System.out.println("Returning to the previous screen.");
    }
}

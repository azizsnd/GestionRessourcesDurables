package Utils;


public class Alert {

    public static void showErrorAlert(String title, String message) {
        showAlert(title, message, javafx.scene.control.Alert.AlertType.ERROR);
    }

    public static void showInfoAlert(String title, String message) {
        showAlert(title, message, javafx.scene.control.Alert.AlertType.INFORMATION);
    }

    public static void showWarningAlert(String title, String message) {
        showAlert(title, message, javafx.scene.control.Alert.AlertType.WARNING);
    }

    public static void showConfirmationAlert(String title, String message) {
        showAlert(title, message, javafx.scene.control.Alert.AlertType.CONFIRMATION);
    }

    private static void showAlert(String title, String message, javafx.scene.control.Alert.AlertType alertType) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

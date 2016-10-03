package model;

/**
 * Created by chitramahajani on 10/3/16.
 */
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
public class AlertMessage {
    public static void sendMessage(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

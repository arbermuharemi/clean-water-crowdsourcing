package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 * Created by Yash on 9/28/2016.
 */
public class RegistrationScreenContoller {

    private Main myApp;

    @FXML
    private Button registerSubmitButton;

    @FXML
    private Button cancelButton;

    private ObservableList<String> typeList = FXCollections.observableArrayList("User", "Worker", "Manager", "Admin");

    @FXML
    private ComboBox<String> typeBox;

    @FXML
    private void initialize() {
        typeBox.setItems(typeList);
    }

    public void setMainApp(Main mainApp) {
        myApp = mainApp;
    }

    @FXML
    private void handleRegisterSubmitPressed() {
        myApp.loadApplication();
    }

    @FXML
    private void handleCancelPressed() {
        myApp.loadWelcome();
    }



}

package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.User;

/**
 * Created by Yash on 9/28/2016.
 */
public class RegistrationScreenContoller {

    private Main myApp;

    @FXML
    private TextField firstField;

    @FXML
    private TextField lastField;

    @FXML
    private TextField userField;

    @FXML
    private TextField passField;

    @FXML
    private ComboBox<String> typeBox;

    @FXML
    private Button registerSubmitButton;

    @FXML
    private Button cancelButton;

    private ObservableList<String> typeList = FXCollections.observableArrayList("User", "Worker", "Manager", "Admin");

    @FXML
    private void initialize() {
        typeBox.setItems(typeList);
        typeBox.setValue("User");
    }

    public void setMainApp(Main mainApp) {
        myApp = mainApp;
    }

    @FXML
    private void handleRegisterSubmitPressed() {
        User newUser = new User(firstField.getText(), lastField.getText(), userField.getText(), passField.getText(), typeBox.getValue());
        myApp.addUser(newUser);
        myApp.loadApplication(newUser);

    }

    @FXML
    private void handleCancelPressed() {
        myApp.loadWelcome();
    }



}

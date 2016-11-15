package main.java.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.java.model.AlertMessage;
import main.java.model.User;

/**
 * Created by Yash on 9/28/2016.
 * This class's purpose is to: <DESCRIBE PURPOSE>
 */
public class RegistrationScreenController {

    private Main myApp;

    @FXML
    private TextField firstField;

    @FXML
    private TextField lastField;

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passField;

    @FXML
    private ComboBox<String> typeBox;

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
        String validNameRegex = "^[a-zA-Z]+$";
        String firstName = firstField.getText();
        String lastName = lastField.getText();
        String userName = userField.getText();
        if (!firstName.matches(validNameRegex)
                || !lastName.matches(validNameRegex)) {
            AlertMessage.sendMessage("Invalid Name", "Your first and last " +
                    "name can only contain letters");
        } else {
            User newUser = new User(firstName, lastName, userName,
                    passField.getText(), typeBox.getValue());
            myApp.addUser(newUser);
            myApp.loadApplication(newUser);
        }
    }

    @FXML
    private void handleCancelPressed() {
        myApp.loadWelcome();
    }



}

package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.java.model.Profile;
import main.java.model.User;

/**
 * Created by Yash on 10/3/2016.
 * This class's purpose is to: <DESCRIBE PURPOSE>
 */
public class CreateProfileScreenController {
    private User currentUser;

    private Main myApp;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField streetField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField stateField;

    @FXML
    private TextField zipField;


    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public void setMainApp(Main mainApp) {myApp = mainApp;}

    @FXML
    private void handleCancelPressed() {
        myApp.loadApplication(currentUser);
    }

    @FXML
    private void handleDonePressed() {
        Profile profile = new Profile(emailField.getText(), phoneField.getText(), streetField.getText(), cityField.getText(), stateField.getText(), zipField.getText());
        currentUser.addProfile(profile);
        myApp.loadApplication(currentUser);
    }
}

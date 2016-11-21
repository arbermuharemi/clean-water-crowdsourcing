package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import main.java.model.User;

import java.util.ArrayList;

/**
 * Created by Yash on 9/20/2016.
 * This class's purpose is to: <DESCRIBE PURPOSE>
 */
public class LoginScreenController {
    private User currentUser;
    private Main myApp;

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passField;

    @FXML Button submitButton;

    public void setMainApp(Main mainApp) {
        myApp = mainApp;
    }

    @FXML
    private void handleHomePressed() {
        myApp.loadWelcome();
    }

    @FXML
    private void handleSubmitPressed() {
        boolean loadApp;
        currentUser = new User(null, null, null, null, User.Type.USER.toString());
        String username = userField.getText();
        String password = passField.getText();
        ArrayList<User> userList = myApp.getUserList();
        loadApp = isValidLogin(username, password, userList);
        if (loadApp) {
            myApp.loadApplication(currentUser);
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Credentials Error");
            alert.setContentText("No user with these credentials exists. Please reenter your information, or if you have no made an account, click 'Sign Up'.");
            alert.showAndWait();
        }
    }

    /**
     * tests to make sure the user is valid
     * @param username the username you're checking against the list
     * @param password the password you're checking against the list
     * @param userList the List of Users from the database
     * @return whether the login is valid or not.
     */
    public boolean isValidLogin(String username, String password, ArrayList<User> userList) {
        boolean loadApp = false;
        String logUser = username;
        String toLog = "";
        for(int j = 0; j < userList.size() && !loadApp; j++) {
            User user = userList.get(j);
            toLog = "LOGIN ATTEMPT ";
            if(user.getUserName().equals(username) && user.getPassword().equals(password)) {
                loadApp = true;
                currentUser = user;
                toLog += "by " + username + ": Success";
            } else {
                loadApp = false;
                toLog += "Incorrect username or password";
            }
        }
        myApp.writeSecurity(toLog);
        return loadApp;
    }

    @FXML
    private void handleSignUpPressed() {
        myApp.loadRegister();
    }
}

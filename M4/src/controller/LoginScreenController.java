package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.User;

import java.util.ArrayList;

/**
 * Created by Yash on 9/20/2016.
 */
public class LoginScreenController {

    private Main myApp;

    @FXML
    private TextField userField;

    @FXML
    private TextField passField;

    @FXML
    private Button homeButton;

    @FXML
    private Button signupButton;

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
        boolean loadApp = false;
        User currentUser = new User(null, null, null, null, null);
        String username = userField.getText();
        String password = passField.getText();
        ArrayList<User> userList = myApp.getUserList();
        for(int j = 0; j < userList.size() && !loadApp; j++) {
            User user = userList.get(j);
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                loadApp = true;
                currentUser = user;

            }
        }
        if (loadApp) {
            myApp.loadApplication(currentUser);
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Credentials Error");
            alert.setContentText("No user with these credentials exists. Please reenter your information, or if you have no made an account, click 'Sign Up'.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleSignUpPressed() {
        myApp.loadRegister();
    }

}

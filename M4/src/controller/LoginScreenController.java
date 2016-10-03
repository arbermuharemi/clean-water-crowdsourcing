package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

/**
 * Created by Yash on 9/20/2016.
 */
public class LoginScreenController {

    private Main myApp;

    @FXML
    private TextField userField;

    @FXML
    private TextField passField;

    /*@FXML
    private Button*/

    public void setMainApp(Main mainApp) {
        myApp = mainApp;
    }

    @FXML
    private void handleHomePressed() {
        myApp.loadWelcome();
    }

    @FXML
    private void handleSubmitPressed() {
        if (checkCredentials(userField.getText(), passField.getText())) {
            myApp.loadApplication();
        } else {
            model.AlertMessage.sendMessage("Credentials Error", "You have entered invalid credentials so you cannot login to the application");
        }
    }

    private boolean checkCredentials(String username, String password) {
        boolean output = false;
        try {
            Scanner scanCredentials = new Scanner(new File("credentials.txt"));
            while (scanCredentials.hasNext()) {
                String credentialsLine = scanCredentials.nextLine();
                String[] credentialsArr = credentialsLine.split(",");
                if (credentialsArr[0].equals(username) && credentialsArr[1].equals(password)) {
                    output = true;
                }
            }
        } catch(FileNotFoundException e) {
            model.AlertMessage.sendMessage("File Not Found Error", "The file containing valid credentials was not found so credentials couldn't be validated");
        }
        return output;
    }

    @FXML
    private void handleSignUpPressed() {
        myApp.loadRegister();
    }




}

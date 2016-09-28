package controller;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Yash on 9/20/2016.
 */
public class WelcomeController implements Initializable {

    private Main myApp;

    @FXML
    private Button registerButton;

    @FXML
    private Button loginButton;

    public void initialize(URL location, ResourceBundle resources) {}

    public void setMainApp(Main mainApp) {
        myApp = mainApp;
    }

    @FXML
    private void handleLoginPressed() {
        myApp.loadLogin();
    }

    @FXML
    private void handleRegisterPressed() {
        myApp.loadRegister();
    }




}

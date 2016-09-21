package controller;

import javafx.fxml.FXML;

/**
 * Created by Yash on 9/20/2016.
 */
public class LoginScreenController {

    private Main myApp;

    public void setMainApp(Main mainApp) {
        myApp = mainApp;
    }

    @FXML
    private void handleHomePressed() {
        myApp.loadWelcome();
    }

    @FXML
    private void handleSubmitPressed() {
        myApp.loadApplication();
    }


}

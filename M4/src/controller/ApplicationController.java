package controller;

import javafx.fxml.FXML;

/**
 * Created by chitramahajani on 9/20/16.
 */
public class ApplicationController {

    private Main myApp;

    public void setMainApp(Main mainApp) {
        myApp = mainApp;
    }

    @FXML
    private void handleLogoutPressed() {
        myApp.loadWelcome();
    }

}

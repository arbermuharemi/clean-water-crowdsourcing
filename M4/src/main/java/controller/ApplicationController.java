package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.java.model.Report;
import main.java.model.SourceReport;
import main.java.model.User;

import java.util.ArrayList;

/**
 * Created by chitramahajani on 9/20/16.
 */
public class ApplicationController {

    private Main myApp;

    private static User currentUser;

    @FXML
    private Label applicationMessage;

    @FXML
    private Button createButton;

    @FXML
    private Button editButton;

    @FXML
    private Button viewMapButton;

    @FXML
    private Button submitReportButton;

    @FXML
    private Button viewSourceReportsButton;

    @FXML
    private Button viewHistoryGraphButton;

    @FXML
    private Button viewPurityReportsButton;

    @FXML Button getViewHistoryGraphButton;

    public void setMainApp(Main mainApp) {
        myApp = mainApp;
    }

    @FXML
    private void handleLogoutPressed() {
        myApp.loadWelcome();
    }

    @FXML
    public void setCurrentUser(User user) {
        currentUser = user;
        User.Type userType = currentUser.getType();

        if (userType == User.Type.USER || userType == User.Type.ADMIN) {
            submitReportButton.setDisable(true);
        }
        if (userType == User.Type.MANAGER){
            viewPurityReportsButton.setDisable(false);
            viewHistoryGraphButton.setDisable(false);
        }else {
            viewPurityReportsButton.setDisable(true);
            viewHistoryGraphButton.setDisable(true);
        }
        applicationMessage.setText("Welcome " + currentUser.getFirstName());
        if (currentUser.hasProfile()) {
            createButton.setDisable(true);
        } else {
            editButton.setDisable(true);
        }
        if (currentUser.getType().equals("Worker")) {
            // show water purity report button
            // button.setVisible(true)
        } else {
            // make the button INVISIBLE
            // button.setVisible(false)
        }
        if (myApp.getSourceReportList() == null || myApp.getSourceReportList().size() == 0) {
            viewMapButton.setDisable(true);
        }
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    @FXML
    private void handleEditPressed() {
        if(currentUser.getProfile() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Profile to Edit");
            alert.setContentText("You have to make a profile to edit it. Click 'Create Profile' to make a profile.");
            alert.showAndWait();
        } else {
            myApp.loadEditProfile(currentUser);
        }
    }

    @FXML
    private void handleSubmitSourcePressed() {
        myApp.loadSourceReportPage(currentUser);
    }

    @FXML
    private void handleViewSourceReportsPressed() {
        //ArrayList<Report> list = myApp.getSourceReportList();
        //for (Report report: list) {
        //    System.out.println(report.toString() + "\n");
        //}
        myApp.loadViewSourceReport(currentUser);
    }

    @FXML
    private void handleViewPurityReportsPressed() {
        //ArrayList<Report> list = myApp.getSourceReportList();
        //for (Report report: list) {
        //    System.out.println(report.toString() + "\n");
        //}
        myApp.loadViewPurityReport(currentUser);
    }

    @FXML
    private void handleSubmitReportPressed() {
        myApp.loadWaterReport(currentUser);
    }

    @FXML
    private void handleViewMapPressed() {
        myApp.loadMap();
    }

    @FXML
    private void handleCreatePressed() { myApp.loadCreateProfile(currentUser);}

    @FXML
    private void handleHistoryGraphPressed() {
        myApp.loadHistoryGraph(currentUser);
    }

}
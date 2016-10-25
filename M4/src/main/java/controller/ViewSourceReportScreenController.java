package main.java.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import main.java.model.SourceReport;
import main.java.model.User;

import java.util.ArrayList;

/**
 * Created by chitramahajani on 10/25/16.
 */
public class ViewSourceReportScreenController {

    @FXML
    private TextArea viewSourceReports;

    private Main myApp;

    private User currentUser;

    private ArrayList<SourceReport> mySourceReports;

    @FXML
    private void initialize() {
        viewSourceReports.setEditable(false);
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public void setMainApp(Main mainApp) {
        myApp = mainApp;
        mySourceReports = myApp.getSourceReportList();
        String output = "";
        for (SourceReport s : mySourceReports) {
            output += s + "\n\n";
        }
        viewSourceReports.setText(output);
    }

    @FXML
    private void handleDonePressed() {
        myApp.loadApplication(currentUser);
    }
}

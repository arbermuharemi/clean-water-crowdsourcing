package main.java.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import main.java.model.Report;
import main.java.model.SourceReport;
import main.java.model.User;

import java.util.ArrayList;

/**
 * Created by chitramahajani on 10/25/16.
 */
public class ViewSourceReportScreenController {

    @FXML
    private TextArea viewSourceReports;

    @FXML
    private Label viewReportsLabel;

    private Main myApp;

    private User currentUser;

    private ArrayList<Report> mySourceReports;

    @FXML
    private void initialize() {
        viewSourceReports.setEditable(false);
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public void setMainApp(Main mainApp, boolean isSourceReport) {
        //boolean value is true for source reports false for purity reports
        myApp = mainApp;

        if (isSourceReport) {
            viewReportsLabel.setText("Source Reports");
            mySourceReports = myApp.getSourceReportList();
            String output = "";
            for (Report s : mySourceReports) {
                output += s + "\n\n";
            }
            viewSourceReports.setText(output);
        } else {
            viewReportsLabel.setText("Purity Reports");
            mySourceReports = myApp.getPurityReportList();
            String output = "";
            for (Report s : mySourceReports) {
                output += s + "\n\n";
            }
            viewSourceReports.setText(output);
        }
    }

    @FXML
    private void handleDonePressed() {
        myApp.loadApplication(currentUser);
    }
}

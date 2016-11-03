package main.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import main.java.model.Report;
import main.java.model.SourceReport;
import main.java.model.User;

import javax.swing.table.TableColumn;
import java.util.ArrayList;

/**
 * Created by chitramahajani on 10/25/16.
 */
public class ViewSourceReportScreenController {

    @FXML
    private TableView<Report> reportTable;

    private Main myApp;

    private User currentUser;

    private ArrayList<Report> mySourceReports;

    @FXML
    private void initialize() {
        reportTable.setEditable(false);
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public void setMainApp(Main mainApp, boolean isSourceReport) {
        //boolean value is true for source reports false for purity reports
        myApp = mainApp;

        if (isSourceReport) {
            //viewReportsLabel.setText("Source Reports");
            //mySourceReports = myApp.getSourceReportList();
            ObservableList<Report> mySourceReports = FXCollections
                    .observableArrayList(myApp.getSourceReportList());
            //String output = "";
            //for (Report s : mySourceReports) {
            //    output += s + "\n\n";
            //}
            //viewSourceReports.setText(output);
            //reportOverallConditionCol.setWidth(0);
            //reportWaterConditionCol.setWidth(0);
            //reportVirusCol.setWidth(0);
            //reportContaminantCol.setWidth(0);
            //reportTable.lookup("Water Condition").setVisible(false);
            //reportTable.autosize();
            reportTable.setItems(mySourceReports);
        } else {
            ObservableList<Report> mySourceReports = FXCollections
                    .observableArrayList(myApp.getSourceReportList());
            //viewReportsLabel.setText("Purity Reports");
            //mySourceReports = myApp.getPurityReportList();
            //reportWaterTypeCol.setWidth(0);
            reportTable.setItems(mySourceReports);
            //String output = "";
            //for (Report s : mySourceReports) {
            //    output += s + "\n\n";
            //}
            //viewSourceReports.setText(output);
        }
    }

    @FXML
    private void handleDonePressed() {
        myApp.loadApplication(currentUser);
    }
}

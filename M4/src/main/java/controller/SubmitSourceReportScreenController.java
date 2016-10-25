package main.java.controller;

import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.java.model.Report;
import main.java.model.SourceReport;
import main.java.model.User;
import java.util.Date;

/**
 * Created by Yash on 10/12/2016.
 */
public class SubmitSourceReportScreenController {

    private User currentUser;

    private Date date;

    private Main myApp;

    @FXML
    private TextField latitudeField;

    @FXML
    private TextField longitudeField;

    @FXML
    private TextField typeField;

    @FXML
    private TextField conditionField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button doneButton;

    @FXML
    private void initialize() {
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public void setMainApp(Main mainApp) {
        myApp = mainApp;
    }

    @FXML
    private void handleCancelPressed() {
        myApp.loadApplication(currentUser);
    }

    @FXML
    private void handleDonePressed() {
        date = new Date();
        String name = currentUser.getFirstName() + " " + currentUser.getLastName();
        SourceReport report = new SourceReport(Report.generateReportNumber(), name, date
                .toString(), Double.parseDouble(longitudeField.getText()),
                Double.parseDouble(latitudeField.getText()),
                typeField.getText(), conditionField.getText());
        myApp.addSourceReport(report);
        myApp.loadApplication(currentUser);
    }




}

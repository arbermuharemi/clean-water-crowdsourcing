package main.java.controller;

import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.java.model.*;

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
        double longitude;
        double latitude;
        try {
            longitude = Double.parseDouble(longitudeField.getText());
            latitude = Double.parseDouble(latitudeField.getText());
            if (longitude > 90 || longitude < -90
                    || longitude > 180 || longitude < -180) {
                AlertMessage.sendMessage("Invalid Coordinates", "The latitude " +
                        "must be between -90 and 90; longitude must be " +
                        "between -180 and 180.");
            } else {
                SourceReport report = new SourceReport(
                        Report.generateReportNumber(),
                        name,
                        date.toString(),
                        longitude,
                        latitude,
                        typeField.getText(),
                        conditionField.getText());
                myApp.addSourceReport(report);
                myApp.loadApplication(currentUser);
            }
        } catch (NumberFormatException | NullPointerException e) {
            AlertMessage.sendMessage("Invalid Coordinates", "The latitude " +
                    "and/or longitude you entered are not in valid format.");
        }
    }

}

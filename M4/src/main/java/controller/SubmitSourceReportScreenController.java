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
import java.util.Random;

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
    private ComboBox<String> typeField;

    @FXML
    private ComboBox<String> conditionField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button doneButton;

    //private int reportNumber = myApp.getSourceReportList().size();

    private ObservableList<String> myTypes = FXCollections
            .observableArrayList("Bottled", "Stream", "Lake", "Other");

    private ObservableList<String> myConditions = FXCollections
            .observableArrayList("Waste", "Treatable", "Clean", "Other");

    @FXML
    private void initialize() {
        typeField.setItems(myTypes);
        typeField.setValue("Bottled");
        conditionField.setItems(myConditions);
        conditionField.setValue("Waste");
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
            if (latitude > 90 || latitude < -90
                    || longitude > 180 || longitude < -180) {
                AlertMessage.sendMessage("Invalid Coordinates", "The latitude " +
                        "must be between -90 and 90; longitude must be " +
                        "between -180 and 180.");
            } else {
                Random random = new Random();
                SourceReport report = new SourceReport(
                        random.nextInt(100),
                        name,
                        date,
                        longitude,
                        latitude,
                        typeField.getValue(),
                        conditionField.getValue());
                myApp.addSourceReport(report);
                myApp.loadApplication(currentUser);
            }
        } catch (NumberFormatException | NullPointerException e) {
            AlertMessage.sendMessage("Invalid Coordinates", "The latitude " +
                    "and/or longitude you entered are not in valid format.");
        }
    }

}

package main.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.java.model.AlertMessage;
import main.java.model.PurityReport;
import main.java.model.Report;
import main.java.model.User;

import java.util.Date;
import java.util.Random;

/**
 * Created by chitramahajani on 10/25/16.
 */
public class SubmitWaterReportScreenController {

    @FXML
    private ComboBox<String> conditionValue;

    @FXML
    private TextField longitudeField;

    @FXML
    private TextField latitudeField;

    @FXML
    private TextField virusField;

    @FXML
    private TextField contaminantField;

    private User currentUser;

    private Main myApp;

    private Date date;

    private ObservableList<String> myConditions = FXCollections
            .observableArrayList("Safe", "Treatable", "Unsafe");


    @FXML
    private void initialize() {
        conditionValue.setItems(myConditions);
        conditionValue.setValue("Safe");
    }

    @FXML
    private void handleCancelPressed() {
        myApp.loadApplication(ApplicationController.getCurrentUser());
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public void setMainApp(Main mainApp) {
        myApp = mainApp;
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
                PurityReport report = new PurityReport(
                        random.nextInt(100),
                        name, date.toString(),
                        longitude,
                        latitude,
                        conditionValue.getValue().toString(),
                        Double.parseDouble(virusField.getText()),
                        Double.parseDouble(contaminantField.getText()));

                myApp.addPurityReport(report);
                myApp.loadApplication(currentUser);
            }
        } catch (NumberFormatException | NullPointerException e) {
            AlertMessage.sendMessage("Invalid Coordinates", "The latitude " +
                    "and/or longitude you entered are not in valid format.");
        }
    }
}

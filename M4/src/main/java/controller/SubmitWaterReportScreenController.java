package main.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.java.model.PurityReport;
import main.java.model.User;

import java.util.Date;

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

    private static int reportCounter = 0;

    private User currentUser;

    private Main myApp;

    private Date date;

    ObservableList<String> myConditions = FXCollections.observableArrayList("Safe", "Treatable", "Unsafe");


    @FXML
    private void initialize() {
        conditionValue.setItems(myConditions);
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
        PurityReport report = new PurityReport(reportCounter, name, date
                .toString(), Double.parseDouble(longitudeField.getText()),
                Double.parseDouble(latitudeField.getText()), conditionValue.getValue().toString(),
                Double.parseDouble(virusField.getText()), Double.parseDouble(contaminantField.getText()));
        reportCounter++;
        myApp.addPurityReport(report);
        myApp.loadApplication(currentUser);
    }
}

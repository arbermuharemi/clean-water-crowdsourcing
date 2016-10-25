package main.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

/**
 * Created by chitramahajani on 10/25/16.
 */
public class SubmitWaterReportScreenController {

    @FXML
    private ComboBox<String> conditionValue;

    private Main myApp;

    ObservableList<String> myConditions = FXCollections.observableArrayList("Safe", "Treatable", "Unsafe");


    @FXML
    private void initialize() {
        conditionValue.setItems(myConditions);
    }
}

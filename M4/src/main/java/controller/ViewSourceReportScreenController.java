package main.java.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/**
 * Created by chitramahajani on 10/25/16.
 */
public class ViewSourceReportScreenController {

    @FXML
    private TextArea viewSourceReports;

    @FXML
    private void initialize() {
        viewSourceReports.setEditable(false);
    }
}

package main.java.controller;

import com.lynden.gmapsfx.javascript.object.LatLong;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import main.java.model.PurityReport;
import main.java.model.Report;
import main.java.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Yash on 10/30/2016.
 */
public class HistoryGraphController {

    private Main myApp;

    private User currentUser;

    @FXML
    private ComboBox<String> locationBox;

    @FXML
    private ComboBox<String> dataBox;

    @FXML
    private ComboBox<String> yearBox;

    private ObservableList<String> dataList = FXCollections.observableArrayList("Virus", "Contaminant");

    /*@FXML
    private void initialize() {
        typeBox.setItems(typeList);
        typeBox.setValue("User");

    }*/

    @FXML
    public void setMainApp(Main mainApp) {
        myApp = mainApp;
        ArrayList<String> locationList = myApp.getPurityLocationsList();
        ArrayList<String> yearList = myApp.getPurityYearList();
        ObservableList<String> graphLocationList = FXCollections.observableArrayList(locationList);
        ObservableList<String> graphYearList = FXCollections.observableArrayList(yearList);
        System.out.println(locationList);
        System.out.println(yearList);
        locationBox.setItems(graphLocationList);
        dataBox.setItems(dataList);
        yearBox.setItems(graphYearList);
        dataBox.setValue("Virus");
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    @FXML
    public void handleCancelPressed() {
        myApp.loadApplication(currentUser);

    }

    @FXML
    public void handleSubmitPressed() {
        String position = locationBox.getValue();
        String year = yearBox.getValue();
        String data = dataBox.getValue();
        ArrayList<HashMap<String, Object>> reportList = myApp.getPurityReportList();
        ArrayList<PurityReport> toGraph = new ArrayList<>();
        for(HashMap<String, Object> dummy : reportList) {
            HashMap<String, Integer> dateMap = (HashMap<String, Integer>)dummy.get("_date");
            Date date = new Date(dateMap.get("year"), dateMap.get("month"), dateMap.get("date"));
            int reportNumber = (Integer)dummy.get("_reportNumber");
            String reporterName = (String)dummy.get("_nameOfWorker");
            double contaminantPPM = (Double)dummy.get("_contaminantPPM");
            double virusPPM = (Double)dummy.get("_virusPPM");
            double latitude = (Double)dummy.get("_latitude");
            double longitude = (Double)dummy.get("_longitude");
            String condition = (String)dummy.get("_waterOverallCondition");
            String description = (String)dummy.get("description");
            String title = (String)dummy.get("title");
            PurityReport report = new PurityReport(reportNumber, reporterName, date, longitude, latitude, condition, virusPPM, contaminantPPM);
            if(report.includeInGraph(position, year)) {
                toGraph.add(report);
            }
        }
        myApp.loadGraph(toGraph, data, position, year, currentUser);
    }
}

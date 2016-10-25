package main.java.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Arber on 10/24/2016.
 */
public class PurityReport extends Report {
    private StringProperty _nameOfWorker = new SimpleStringProperty();
    private StringProperty _waterOverallCondition = new SimpleStringProperty();
    private DoubleProperty _virusPPM = new SimpleDoubleProperty();
    private DoubleProperty _contaminantPPM = new SimpleDoubleProperty();

    public String get_nameOfWorker() {
        return _nameOfWorker.get();
    }

    public StringProperty _nameOfWorkerProperty() {
        return _nameOfWorker;
    }

    public String get_waterOverallCondition() {
        return _waterOverallCondition.get();
    }

    public StringProperty _waterOverallConditionProperty() {
        return _waterOverallCondition;
    }

    public double get_virusPPM() {
        return _virusPPM.get();
    }

    public DoubleProperty _virusPPMProperty() {
        return _virusPPM;
    }

    public double get_contaminantPPM() {
        return _contaminantPPM.get();
    }

    public DoubleProperty _contaminantPPMProperty() {
        return _contaminantPPM;
    }

    public PurityReport(int reportNumber, String reporterName, String date,
                        double longitude, double latitude,
                        String waterCondition, double virusPPM,
                        double contaminantPPM){
        super(reportNumber, date, latitude, longitude);
        _nameOfWorker.set(reporterName);
        _waterOverallCondition.set(waterCondition);
    }

    public String toString(){
        return  "Report Number: " +  get_reportNumber() + "\n" +
                "Worker Name: " + get_nameOfWorker() + "\n" +
                "Date: " +  get_date() + "\n" +
                "Longitude: " + get_longitude() + "\n" +
                "Latitude: " + get_latitude() +"\n" +
                "Water Condition: " + get_waterOverallCondition();
    }

    public String getTitle() {
        return "<h2>Report " + this.getReportNumber() + "</h2>";
    }

    public String getDescription() {
        return  "<h2>Report " +  this.getReportNumber() + "</h2>" + "\n\n" +
                "Worker Name: " + get_nameOfWorker() + "\n" +
                "<br> Date: " +  get_date() + "\n" +
                "<br> Longitude: " + get_longitude() + "\n" +
                "<br> Latitude: " + get_virusPPM() +"\n" +
                "<br> Overall Water Condition: " + get_waterOverallCondition() +
                "<br> Virus PPM: " + get_virusPPM() +
                "<br> Contaminant PPM: " + get_contaminantPPM() + "</h2>";
    }

    public enum WaterCondition {
        SAFE, TREATABLE, UNSAFE
    }
}

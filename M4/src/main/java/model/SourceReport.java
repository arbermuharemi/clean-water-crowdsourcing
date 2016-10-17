package main.java.model;

import javafx.beans.property.*;

/**
 * Created by vijay on 10/12/2016.
 */
public class SourceReport {
    private StringProperty _date = new SimpleStringProperty();
    private StringProperty _reporterName = new SimpleStringProperty();
    private StringProperty _waterType = new SimpleStringProperty();
    private StringProperty _waterCondition = new SimpleStringProperty();
    private IntegerProperty _reportNumber = new SimpleIntegerProperty();
    private DoubleProperty _latitude = new SimpleDoubleProperty();
    private DoubleProperty _longitude = new SimpleDoubleProperty();

    public String get_date() {
        return _date.get();
    }

    public StringProperty _dateProperty() {
        return _date;
    }

    public String get_reporterName() {
        return _reporterName.get();
    }

    public StringProperty _reporterNameProperty() {
        return _reporterName;
    }

    public String get_waterType() {
        return _waterType.get();
    }

    public StringProperty _waterTypeProperty() {
        return _waterType;
    }

    public String get_waterCondition() {
        return _waterCondition.get();
    }

    public StringProperty _waterConditionProperty() {
        return _waterCondition;
    }

    public int get_reportNumber() {
        return _reportNumber.get();
    }

    public IntegerProperty _reportNumberProperty() {
        return _reportNumber;
    }

    public double get_latitude() {
        return _latitude.get();
    }

    public DoubleProperty _latitudeProperty() {
        return _latitude;
    }

    public double get_longitude() {
        return _longitude.get();
    }

    public DoubleProperty _longitudeProperty() {
        return _longitude;
    }

    public int getReportNumber() { return _reportNumber.getValue(); }


    public SourceReport(int reportNumber, String reporterName, String date,
                        double longitude, double latitude, String waterType,
                        String waterCondition){
        _date.set(date);
        _reporterName.set(reporterName);
        _waterType.set(waterType);
        _waterCondition.set(waterCondition);
        _reportNumber.set(reportNumber);
        _latitude.set(latitude);
        _longitude.set(longitude);

    }

    public String toString(){
        return "Report Number: " +  get_reportNumber() + "\n" +
                "Reporter Name: " + get_reporterName() + "\n" +
                "Date: " +  get_date() + "\n" +
                "Longitude: " + get_longitude() + "\n" +
                "Latitude: " + get_latitude() +"\n" +
                "Water Type: " + get_waterType() + "\n" +
                "Water Condition: " + get_waterCondition();
    }

    public String getTitle() {
        return "<h2>Report " + this.getReportNumber() + "</h2>";
    }

    public String getDescription() {
        return  "<h2>Report " +  this.getReportNumber() + "</h2>" + "\n\n" +
                "Reporter Name: " + get_reporterName() + "\n" +
                "<br> Date: " +  get_date() + "\n" +
                "<br> Longitude: " + get_longitude() + "\n" +
                "<br> Latitude: " + get_latitude() +"\n" +
                "<br> Water Type: " + get_waterType() + "\n" +
                "<br> Water Condition: " + get_waterCondition() + "</h2>";
    }

}

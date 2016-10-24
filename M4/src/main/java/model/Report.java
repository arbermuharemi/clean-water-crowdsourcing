package main.java.model;

import javafx.beans.property.*;

/**
 * Created by Arber on 10/24/2016.
 */
public abstract class Report {

    private StringProperty _date = new SimpleStringProperty();
    private IntegerProperty _reportNumber = new SimpleIntegerProperty();
    private DoubleProperty _latitude = new SimpleDoubleProperty();
    private DoubleProperty _longitude = new SimpleDoubleProperty();

    public Report(int reportNumber, String date,
                  double longitude, double latitude){
        _date.set(date);
        _reportNumber.set(reportNumber);
        _latitude.set(latitude);
        _longitude.set(longitude);
    }

    public String get_date() {
        return _date.get();
    }

    public StringProperty _dateProperty() {
        return _date;
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

    public abstract String toString();

    public abstract String getTitle();

    public abstract String getDescription();

}

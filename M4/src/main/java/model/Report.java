package main.java.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.Date;

/**
 * Created by Arber on 10/24/2016.
 * This class's purpose is to: <DESCRIBE PURPOSE>
 */
public abstract class Report {

    //private StringProperty _date = new SimpleStringProperty();
    private Date _date = new Date();
    private IntegerProperty _reportNumber = new SimpleIntegerProperty();
    private DoubleProperty _latitude = new SimpleDoubleProperty();
    private DoubleProperty _longitude = new SimpleDoubleProperty();

    public Report() {
    }

    public Report(int reportNumber, Date date,
                  double longitude, double latitude){
        _date = date;
        _reportNumber.set(reportNumber);
        _latitude.set(latitude);
        _longitude.set(longitude);
    }

    public Date get_date() {
        return _date;
    }

    public int get_reportNumber() {
        return _reportNumber.getValue();
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

    public abstract String toString();

    public abstract String getTitle();

    public abstract String getDescription();

}

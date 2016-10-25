package main.java.model;

import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Created by Arber on 10/24/2016.
 */
public abstract class Report {

    private StringProperty _date = new SimpleStringProperty();
    private static IntegerProperty _reportNumber = new SimpleIntegerProperty();
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

    public static int get_reportNumber() {
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

    public static int generateReportNumber() {
        _reportNumber.add(1);
        return get_reportNumber();
    }

    public abstract String toString();

    public abstract String getTitle();

    public abstract String getDescription();

}

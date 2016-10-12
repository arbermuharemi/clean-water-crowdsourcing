package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;

/**
 * Created by vijay on 10/12/2016.
 */
public class SourceReport {
    private StringProperty _date = new SimpleStringProperty();
    private StringProperty _reporterName = new SimpleStringProperty();
    private StringProperty _waterType = new SimpleStringProperty();
    private StringProperty _waterCondition = new SimpleStringProperty();
    private IntegerProperty _reportNumber = new SimpleIntegerProperty();
    private StringProperty _latitude = new SimpleStringProperty();
    private StringProperty _longitude = new SimpleStringProperty();

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

    public String get_latitude() {
        return _latitude.get();
    }

    public StringProperty _latitudeProperty() {
        return _latitude;
    }

    public String get_longitude() {
        return _longitude.get();
    }

    public StringProperty _longitudeProperty() {
        return _longitude;
    }

    public SourceReport(int reportNumber, String reporterName, String date, String longitude, String latitude, String waterType, String waterCondition){
        _date.set(date);
        _reporterName.set(reporterName);
        _waterType.set(waterType);
        _waterCondition.set(waterCondition);
        _reportNumber.set(reportNumber);
        _latitude.set(latitude);
        _longitude.set(longitude);

    }

    public String toString(){
        return "Report Number: " +  _reportNumber + "\n" + "Reporter Name: " +  _reporterName + "\n" + "Date: " +  _date + "\n" + "Longitude: " +  _longitude + "\n" + "Latitude: " + _latitude +"\n" +"Water Type: " + _waterType + "\n" + "Water Condition: " + _waterCondition;
    }
}

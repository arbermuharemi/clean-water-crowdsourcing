package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Yash on 10/3/2016.
 */
public class Profile {

    private StringProperty _email = new SimpleStringProperty();
    private StringProperty _phoneNumber = new SimpleStringProperty();
    private StringProperty _streetAddress = new SimpleStringProperty();
    private StringProperty _city = new SimpleStringProperty();
    private StringProperty _state = new SimpleStringProperty();
    private StringProperty _zipcode = new SimpleStringProperty();

    public Profile(String email, String phoneNumber, String streetAddress, String city, String state, String zipCode) {
        _email.set(email);
        _phoneNumber.set(phoneNumber);
        _streetAddress.set(streetAddress);
        _city.set(city);
        _state.set(state);
        _zipcode.set(zipCode);
    }

    public String get_email() {
        return _email.get();
    }

    public void set_email(String _email) {
        this._email.set(_email);
    }

    public String get_phoneNumber() {
        return _phoneNumber.get();
    }

    public void set_phoneNumber(String _phoneNumber) {
        this._phoneNumber.set(_phoneNumber);
    }

    public String get_streetAddress() {
        return _streetAddress.get();
    }

    public void set_streetAddress(String _streetAddress) {
        this._streetAddress.set(_streetAddress);
    }

    public String get_city() {
        return _city.get();
    }

    public void set_city(String _city) {
        this._city.set(_city);
    }

    public String get_state() {
        return _state.get();
    }

    public void set_state(String _state) {
        this._state.set(_state);
    }

    public String get_zipcode() {
        return _zipcode.get();
    }

    public void set_zipcode(String _zipcode) {
        this._zipcode.set(_zipcode);
    }

    public String toString() {
        return "Email: " + _email.get() + "\n" + "Phone Number: " + _phoneNumber.get() + "\n" +
                "Address: " + _streetAddress.get() + ", " + _city.get() + ", " + _state.get() + " " + _zipcode.get();
    }



}

package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private StringProperty _firstName = new SimpleStringProperty();
    private StringProperty _lastName = new SimpleStringProperty();
    private StringProperty _username = new SimpleStringProperty();
    private StringProperty _password = new SimpleStringProperty();
    private StringProperty _type = new SimpleStringProperty();
    private Profile userProfile;
    private boolean hasProfile;

    public User(String firstName, String lastName, String username, String password, String type) {
        _firstName.set(firstName);
        _lastName.set(lastName);
        _username.set(username);
        _password.set(password);
        _type.set(type);
    }

    public String getUserName() { return _username.get(); }
    public void setUserName(String uName) { _username.set(uName); }

    public String getPassword() {return _password.get(); }
    public void setPassword(String pass) { _password.set(pass); }

    public String getFirstName() {return _firstName.get(); }
    public void setFirstName(String first) { _firstName.set(first); }

    public String getLastName() {return _lastName.get(); }
    public void setLastName(String last) { _lastName.set(last); }

    public String getType() {return _type.get(); }
    public void setType(String userType) { _type.set(userType); }

    public void addProfile(Profile profile) {
        userProfile = profile;
        hasProfile = true;
    }

    public Profile getProfile() {return userProfile;}

    public String toString() {
        if(userProfile == null) {
            return "Name: " + _firstName.get() + " " + _lastName.get() + "\n" +
                    "Username: " + _username.get() + "\n" + "Password: " + _password.get() +
                    "\n" + "Type: " + _type.get() + "\n";
        } else {
            return "Name: " + _firstName.get() + " " + _lastName.get() + "\n" +
                    "Username: " + _username.get() + "\n" + "Password: " + _password.get() +
                    "\n" + "Type: " + _type.get() + "\n" + userProfile.toString() + "\n";
        }

    }

    public boolean hasProfile() {
        return hasProfile;
    }
}
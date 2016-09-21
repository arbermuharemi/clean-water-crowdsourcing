package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private StringProperty _username = new StringProperty();
    private StringProperty _password = new StringProperty();

    public String getUserName() { return _username.get(); }
    public void setUserName(String name) { _username.set(name); }

    public String getPassword() {return _password.get(); }
    public void setPassword(String major) { _password.set(major); }

    public User(String username, String password) {
        _username.set(username);
        _password.set(password);
    }
}
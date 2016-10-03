package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Yash on 9/28/2016.
 */
public class RegistrationScreenContoller {

    private Main myApp = new Main();
    File newFile = myApp.getFile();

    @FXML
    private Button registerSubmitButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField userField;

    @FXML
    private TextField passField;

    private ObservableList<String> typeList = FXCollections.observableArrayList("User", "Worker", "Manager", "Admin");

    @FXML
    private ComboBox<String> typeBox;

    @FXML
    private void initialize() {
        typeBox.setItems(typeList);
    }

    public void setMainApp(Main mainApp) {
        myApp = mainApp;
    }

    @FXML
    private void handleRegisterSubmitPressed() {
        try {
            FileWriter writer = new FileWriter("credentials.txt", true);
            BufferedWriter myWriter = new BufferedWriter(writer);
            myWriter.write(userField.getText() + "," + passField.getText() + "\n");
            myWriter.close();
        } catch (IOException e) {
            model.AlertMessage.sendMessage("File Not Found Error", "The file containing valid credentials was not found so registration failed");
        }
        myApp.loadApplication();
    }

    @FXML
    private void handleCancelPressed() {
        myApp.loadWelcome();
    }



}

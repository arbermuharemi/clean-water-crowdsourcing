package main.java.controller;

import com.google.firebase.database.FirebaseDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.model.SourceReport;
import main.java.model.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.google.firebase.*;

public class Main extends Application {
    private Stage window;
    private GridPane welcomeLayout;
    private AnchorPane loginLayout;
    private AnchorPane registrationLayout;
    private VBox applicationLayout;
    private AnchorPane editProfileLayout;
    private AnchorPane createProfileLayout;
    private AnchorPane sourceReportLayout;
    private static ArrayList<User> userArr = new ArrayList<User>();
    private static ArrayList<SourceReport> sourceReportList = new ArrayList<>();


    private static FirebaseApp firebase;
    private static FirebaseDatabase database;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        loadWelcome();
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setServiceAccount(new FileInputStream("cs2340-software-smiths-52b69024dd13.json"))
                .setDatabaseUrl("https://cs2340-software-smiths.firebaseio.com/")
                .build();
        firebase = FirebaseApp.initializeApp(options);
        database = FirebaseDatabase.getInstance(firebase);
        database.getReference("server/users");
    }

    public void loadWelcome() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/Welcome.fxml"));
            welcomeLayout = loader.load();

            WelcomeController controller = loader.getController();
            controller.setMainApp(this);

            window.setTitle("Welcome Page");
            Scene welcomeScene = new Scene(welcomeLayout);
            window.setScene(welcomeScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadApplication(User currentUser) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/ApplicationScreen.fxml"));
            applicationLayout = loader.load();

            ApplicationController controller = loader.getController();
            controller.setMainApp(this);
            controller.setCurrentUser(currentUser);

            System.out.println(currentUser.toString());

            window.setTitle("Login Page");
            Scene applicationScene = new Scene(applicationLayout);
            window.setScene(applicationScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadLogin() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/LoginScreen.fxml"));
            loginLayout = loader.load();

            LoginScreenController controller = loader.getController();
            controller.setMainApp(this);

            window.setTitle("Login Page");
            Scene loginScene = new Scene(loginLayout);
            window.setScene(loginScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void loadRegister() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/RegistrationScreen.fxml"));
            registrationLayout = loader.load();

            RegistrationScreenContoller controller = loader.getController();
            controller.setMainApp(this);

            window.setTitle("Registration Page");
            Scene registrationScene = new Scene(registrationLayout);
            window.setScene(registrationScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadEditProfile(User user) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/EditProfileScreen.fxml"));
            editProfileLayout = loader.load();

            EditProfileScreenController controller = loader.getController();
            controller.setMainApp(this);
            controller.setCurrentUser(user);

            window.setTitle("Edit Profile");
            Scene editProfileScene = new Scene(editProfileLayout);
            window.setScene(editProfileScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadCreateProfile(User user) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/CreateProfileScreen.fxml"));
            createProfileLayout = loader.load();

            CreateProfileScreenController controller = loader.getController();
            controller.setMainApp(this);
            controller.setCurrentUser(user);

            window.setTitle("Create Profile");
            Scene createProfileScene = new Scene(createProfileLayout);
            window.setScene(createProfileScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadSourceReportPage(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/SubmitSourceReportScreen.fxml"));
            sourceReportLayout = loader.load();

            SubmitSourceReportScreenController controller = loader.getController();
            controller.setMainApp(this);
            controller.setCurrentUser(user);

            window.setTitle("Submit Source Report");
            Scene createProfileScene = new Scene(sourceReportLayout);
            window.setScene(createProfileScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public FirebaseDatabase getDatabase () {
        return database;
    }

    public void addUser(User user) {
        userArr.add(user);
    }

    public void addSourceReport(SourceReport report) { sourceReportList.add(report); }

    public ArrayList<User> getUserList() {
        return userArr;
    }

    public ArrayList<SourceReport> getSourceReportList() { return sourceReportList; }

    public static void main(String[] args) {
        launch(args);
    }
}

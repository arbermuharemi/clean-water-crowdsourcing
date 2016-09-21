package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.io.IOException;

public class Main extends Application {

    private Stage welcomeStage;
    private GridPane welcomeLayout;

    private Stage loginStage;
    private AnchorPane loginLayout;

    @Override
    public void start(Stage primaryStage) throws Exception{
        welcomeStage = primaryStage;
        loadWelcome();
        loginStage = new Stage();
    }

    public void loadWelcome() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/Welcome.fxml"));
            welcomeLayout = loader.load();

            WelcomeController controller = loader.getController();
            controller.setMainApp(this);

            welcomeStage.setTitle("Welcome Page");
            Scene welcomeScene = new Scene(welcomeLayout);
            welcomeStage.setScene(welcomeScene);
            welcomeStage.show();
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

            loginStage.setTitle("Login Page");
            Scene loginScene = new Scene(loginLayout);
            loginStage.setScene(loginScene);
            loginStage.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}

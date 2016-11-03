package main.java.controller;
import com.lynden.gmapsfx.javascript.object.LatLong;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import main.java.model.PurityReport;
import main.java.model.Report;
import main.java.model.SourceReport;
import main.java.model.User;
import com.google.firebase.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

import com.google.firebase.database.*;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;


public class Main extends Application {
    private Stage window;
    private GridPane welcomeLayout;
    private AnchorPane loginLayout;
    private AnchorPane registrationLayout;
    private HBox applicationLayout;
    private AnchorPane editProfileLayout;
    private AnchorPane createProfileLayout;
    private AnchorPane sourceReportLayout;
    private AnchorPane waterReportLayout;
    private AnchorPane viewReportLayout;
    private BorderPane mapReportLayout;
    private AnchorPane historyGraphLayout;
    private static ArrayList<User> userArr = new ArrayList<>();
    private static ArrayList<Report> sourceReportList = new ArrayList<>();
    private static ArrayList<Report> purityReportList = new ArrayList<>();
    private static ArrayList<String> purityLocationsList = new ArrayList<>();
    private static ArrayList<String> purityYearList = new ArrayList<>();
    private static double maxVirus = 0;
    private static double maxContaminant = 0;
    FirebaseOptions options;
    FirebaseDatabase database;
    DatabaseReference userRef;
    DatabaseReference sourceRef;
    DatabaseReference purityRef;
    DatabaseReference purityLocationRef;
    DatabaseReference purityYearRef;

    /*private static ArrayList<LatLong> purityLocationsList = new ArrayList<>();
    private static ArrayList<String> purityYearList = new ArrayList<>();*/

    @Override
    public void start(Stage primaryStage) throws Exception {
        options = new FirebaseOptions.Builder()
                .setServiceAccount(new FileInputStream("M4/src/main/java/model/cs2340-software-smiths-4665dd93b180.json"))
                .setDatabaseUrl("https://cs2340-software-smiths.firebaseio.com/")
                .build();
        FirebaseApp.initializeApp(options);
        database = database.getInstance();
        userRef = database.getReference("users");
        sourceRef = database.getReference("sourceReports");
        purityRef = database.getReference("purityReports");
        purityLocationRef = database.getReference("purityLocations");
        purityYearRef = database.getReference("purityYears");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //GenericTypeIndicator<List<User>> t = new GenericTypeIndicator<List<User>>() {};
                if (dataSnapshot.getValue() != null) {
                    userArr = (ArrayList<User>) dataSnapshot.getValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        sourceRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //GenericTypeIndicator<List<Report>> t = new GenericTypeIndicator<List<Report>>() {};
                if (dataSnapshot.getValue() != null) {
                    sourceReportList = (ArrayList<Report>) dataSnapshot.getValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        purityRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //GenericTypeIndicator<List<Report>> t = new GenericTypeIndicator<List<Report>>() {};
                if (dataSnapshot.getValue() != null) {
                    purityReportList = (ArrayList<Report>) dataSnapshot.getValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        purityLocationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //GenericTypeIndicator<List<String>> t = new GenericTypeIndicator<List<String>>() {};
                if (dataSnapshot.getValue() != null) {
                    purityLocationsList = (ArrayList<String>) dataSnapshot.getValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        purityYearRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //GenericTypeIndicator<List<String>> t = new GenericTypeIndicator<List<String>>() {};
                if (dataSnapshot.getValue() != null) {
                    purityYearList = (ArrayList<String>) dataSnapshot.getValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        window = primaryStage;
        loadWelcome();
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
            Scene createReportScene = new Scene(sourceReportLayout);
            window.setScene(createReportScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadWaterReport(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/SubmitWaterReportScreen.fxml"));
            waterReportLayout = loader.load();

            SubmitWaterReportScreenController controller = loader.getController();
            controller.setMainApp(this);
            controller.setCurrentUser(user);

            window.setTitle("Submit Source Report");
            Scene createReportScene = new Scene(waterReportLayout);
            window.setScene(createReportScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadViewSourceReport(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/ViewSourceReportTableScreen.fxml"));
            viewReportLayout = loader.load();
            ViewSourceReportScreenController controller = loader.getController();
            controller.setMainApp(this, true);
            controller.setCurrentUser(user);
            window.setTitle("View Source Reports");
            Scene createReportScene = new Scene(viewReportLayout);
            window.setScene(createReportScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadViewPurityReport(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/ViewSourceReportTableScreen.fxml"));
            viewReportLayout = loader.load();
            ViewSourceReportScreenController controller = loader.getController();
            controller.setMainApp(this, false);
            controller.setCurrentUser(user);
            window.setTitle("View Purity Report");
            Scene createReportScene = new Scene(viewReportLayout);
            window.setScene(createReportScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/GMap.fxml"));
            mapReportLayout = loader.load();

            GMapController controller = loader.getController();
            controller.setMainApp(this);

            window.setTitle("Water Report Map");
            Scene createMapScene = new Scene(mapReportLayout);
            window.setScene(createMapScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void closeMapView() {
        loadApplication(ApplicationController.getCurrentUser());
    }

    public void loadHistoryGraph(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/HistoryGraph.fxml"));
            historyGraphLayout = loader.load();

            HistoryGraphController controller = loader.getController();
            controller.setMainApp(this);
            controller.setCurrentUser(user);

            window.setTitle("History Graph");
            Scene createGraphScene = new Scene(historyGraphLayout);
            window.setScene(createGraphScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadGraph(ArrayList<PurityReport> reportList, String data, String position, String year, User user) {
        double[] monthSums  = new double[12];
        double[] monthCount = new double[12];

        System.out.println(reportList.size());
        Axis xAxis = new NumberAxis(1, 12, 1);
        Axis yAxis;
        ScatterChart<NumberAxis, NumberAxis> graph;
        XYChart.Series series = new XYChart.Series();
        if(data.equals("Virus")) {
            int virus = (int) maxVirus;
            int count = 0;
            while (virus != 0) {
                virus = virus/10;
                count++;
            }
            double upperYBound = Math.pow(10, count);
            yAxis = new NumberAxis(0, upperYBound, upperYBound/10);

            xAxis.setLabel("Month (January = 1)");
            yAxis.setLabel(data + " (ppm)");
            graph = new ScatterChart<>(xAxis, yAxis);
            graph.setTitle(year + " " + data + " Trend For Location " + position);
            series.setName("Virus vs. Month");
            for(PurityReport report: reportList) {
                System.out.println("test");
                Date date = report.get_date();
                Calendar cal = new GregorianCalendar();
                cal.setTime(date);
                System.out.println(Calendar.MONTH);

                int month = cal.get(Calendar.MONTH);
                double virusPPM = report.get_virusPPM();
                monthSums[month] += virusPPM;
                monthCount[month]++;
                month++;
                System.out.println(virusPPM);
                series.getData().add(new XYChart.Data((double)month, monthSums[month--]/monthCount[month--]));
            }
        } else {
            int contaminant = (int) maxVirus;
            int count = 0;
            while (contaminant != 0) {
                contaminant = contaminant/10;
                count++;
            }
            double upperYBound = Math.pow(10, count);
            yAxis = new NumberAxis(0, upperYBound, upperYBound/10);
            graph = new ScatterChart<>(xAxis, yAxis);
            xAxis.setLabel("Month (January = 1");
            yAxis.setLabel(data + " (ppm)");
            series.setName("Contaminant vs. Month");
            for(PurityReport report: reportList) {

                Date date = report.get_date();
                Calendar cal = new GregorianCalendar();
                cal.setTime(date);
                System.out.println(Calendar.MONTH);
                int month = cal.get(Calendar.MONTH);
                System.out.println(month);
                double contaminantPPM = report.get_contaminantPPM();
                series.getData().add(new XYChart.Data(month, contaminantPPM));
            }
        }
        graph.getData().addAll(series);
        Button goBack = new Button("Return");
        goBack.setOnAction(new EventHandler<ActionEvent> () {
            @Override public void handle(ActionEvent e) {
                loadApplication(user);
            }
        });
        VBox graphLayout = new VBox();
        graphLayout.setAlignment(Pos.CENTER);
        graphLayout.getChildren().addAll(graph, goBack);
        Scene graphScene = new Scene(graphLayout, 500, 400);
        window.setScene(graphScene);
        window.show();
    }

    public void addUser(User user) {
        userArr.add(user);
        ArrayList<User> myUserList = getUserList();
        userRef.setValue(myUserList);
    }

    public void addSourceReport(SourceReport report) {
        sourceReportList.add(report);
        ArrayList<Report> mySourceReportList = getSourceReportList();
        sourceRef.setValue(mySourceReportList);
    }

    public void addPurityReport (PurityReport report) {
        purityReportList.add(report);
        ArrayList<Report> myPurityReportList = getPurityReportList();
        purityRef.setValue(myPurityReportList);
    }

    public void addPurityLocation (String position) {
        if( !(purityLocationsList.contains(position)) ) {
            purityLocationsList.add(position);
        }
        ArrayList<String> myPurityLocationsList = getPurityLocationsList();
        purityLocationRef.setValue(myPurityLocationsList);
    }

    public void addPurityYear (String year) {
        if ( !(purityYearList.contains(year))) {
            purityYearList.add(year);
        }
        ArrayList<String> myPurityYearsList = getPurityYearList();
        purityYearRef.setValue(myPurityYearsList);
    }

    public ArrayList<User> getUserList() {
        return userArr;
    }

    public ArrayList<Report> getSourceReportList() { return sourceReportList; }

    public ArrayList<Report> getPurityReportList() { return purityReportList; }

    public ArrayList<String> getPurityLocationsList() { return purityLocationsList; }

    public ArrayList<String> getPurityYearList() {return purityYearList; }

    public void setMaxVirus(double virus) {
        if(virus >= maxVirus) {
            maxVirus = virus;
        }
    }

    public void setMaxContaminant(double contaminant) {
        if(contaminant >= maxContaminant) {
            maxContaminant = contaminant;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

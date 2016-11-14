package main.java.controller;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import main.java.model.PurityReport;
import main.java.model.SourceReport;
import main.java.model.User;
import com.google.firebase.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import com.google.firebase.database.*;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;


public class Main extends Application {
    private Stage window;
    private AnchorPane viewReportLayout;
    private static ArrayList<User> userArr = new ArrayList<>();
    private static ArrayList<SourceReport> sourceReportList = new ArrayList<>();
    private static ArrayList<PurityReport> purityReportList = new ArrayList<>();
    private static ArrayList<String> purityLocationsList = new ArrayList<>();
    private static ArrayList<String> purityYearList = new ArrayList<>();
    private static ArrayList<HashMap<String, String>> userArrh = new ArrayList<>();
    private static ArrayList<HashMap<String, Object>> sourceReportListh = new ArrayList<>();
    private static ArrayList<HashMap<String, Object>> purityReportListh = new ArrayList<>();
    private static double maxVirus = 0;
    private static double maxContaminant = 0;
    private boolean isStarted1 = false;
    private boolean isStarted2 = false;
    private boolean isStarted3 = false;
    private boolean isStarted4 = false;
    private boolean isStarted5 = false;
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
        System.out.println("STARTED");
        options = new FirebaseOptions.Builder()
                .setServiceAccount(new FileInputStream("src/main/java/model/cs2340-software-smiths-4665dd93b180.json"))
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

                if (dataSnapshot.getValue() != null && !isStarted1) {
                    isStarted1=true;
                    userArrh = (ArrayList<HashMap<String, String>>) dataSnapshot.getValue();
                    for(HashMap<String, String> a : userArrh){
                        userArr.add(new User(a.get("firstName"), a.get("lastName"), a.get("userName"), a.get("password"), a.get("type")));
                    }
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
                if (dataSnapshot.getValue() != null&& !isStarted2) {
                    isStarted2=true;
                    sourceReportListh = (ArrayList<HashMap<String, Object>>) dataSnapshot.getValue();
                    for(HashMap<String, Object> a : sourceReportListh){
                        HashMap<String, Object> dateh = (HashMap<String, Object>) a.get("_date");
                        Date date = new Date();
                        date.setDate(((Long)dateh.get("date")).intValue());
                        date.setHours(((Long)dateh.get("hours")).intValue());
                        date.setMinutes(((Long)dateh.get("minutes")).intValue());
                        date.setMonth(((Long)dateh.get("month")).intValue());
                        date.setSeconds(((Long)dateh.get("seconds")).intValue());
                        date.setYear(((Long)dateh.get("year")).intValue());
                        sourceReportList.add(new SourceReport(((Long)a.get("_reportNumber")).intValue(),(String) a.get("_reporterName"), date, ((Number)a.get("_longitude")).doubleValue(),((Number) a.get("_latitude")).doubleValue(), (String)a.get("_waterType"), (String) a.get("_waterCondition")));
                    }
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
                if (dataSnapshot.getValue() != null&& !isStarted3) {
                    isStarted3=true;
                    purityReportListh = (ArrayList<HashMap<String, Object>>) dataSnapshot.getValue();
                    for(HashMap<String, Object> a : purityReportListh){
                        HashMap<String, Object> dateh = (HashMap<String, Object>) a.get("_date");
                        Date date = new Date();
                        date.setDate(((Long)dateh.get("date")).intValue());
                        date.setHours(((Long)dateh.get("hours")).intValue());
                        date.setMinutes(((Long)dateh.get("minutes")).intValue());
                        date.setMonth(((Long)dateh.get("month")).intValue());
                        date.setSeconds(((Long)dateh.get("seconds")).intValue());
                        date.setYear(((Long)dateh.get("year")).intValue());
                        purityReportList.add(new PurityReport(((Long)a.get("_reportNumber")).intValue(),(String) a.get("_nameOfWorker"), date, ((Number)a.get("_longitude")).doubleValue(),((Number) a.get("_latitude")).doubleValue(), (String)a.get("_waterOverallCondition"), ((Number) a.get("_virusPPM")).doubleValue(),((Number) a.get("_contaminantPPM")).doubleValue()));
                        setMaxVirus(((Number) a.get("_virusPPM")).doubleValue());
                        setMaxContaminant(((Number) a.get("_contaminantPPM")).doubleValue());
                    }
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
                if (dataSnapshot.getValue() != null&& !isStarted4) {
                    isStarted4=true;
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
                if (dataSnapshot.getValue() != null&& !isStarted5) {
                    isStarted5=true;
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
            GridPane welcomeLayout = loader.load();

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
            HBox applicationLayout = loader.load();

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
            AnchorPane loginLayout = loader.load();

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
            AnchorPane registrationLayout = loader.load();

            RegistrationScreenController controller = loader.getController();
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
            AnchorPane editProfileLayout = loader.load();

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
            AnchorPane createProfileLayout = loader.load();

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
            AnchorPane sourceReportLayout = loader.load();

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
            AnchorPane waterReportLayout = loader.load();

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
            BorderPane mapReportLayout = loader.load();

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
            AnchorPane historyGraphLayout = loader.load();

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
//        for(PurityReport pu: reportList){
//            System.out.println(pu.toString());
//        }
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
                System.out.println(virusPPM);
                //series.getData().add(new XYChart.Data((double)month, monthSums[month--]/monthCount[month--]));
            }
            for(int j = 0; j < monthSums.length; j++) {
                System.out.println(monthSums[j]+" "+j);
                int c = j+1;
                series.getData().add(new XYChart.Data((double)c, monthSums[j]/monthCount[j]));
            }
        } else {
            int contaminant = (int) maxContaminant;
            int count = 0;
            while (contaminant != 0) {
                contaminant = contaminant/10;
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
                double contaminantPPM = report.get_contaminantPPM();
                monthSums[month] += contaminantPPM;
                monthCount[month]++;
                System.out.println(contaminantPPM);
                //series.getData().add(new XYChart.Data((double)month, monthSums[month--]/monthCount[month--]));
            }
            for(int j = 0; j < monthSums.length; j++) {
                System.out.println(monthSums[j]+" "+j);
                int c = j+1;
                series.getData().add(new XYChart.Data((double)c, monthSums[j]/monthCount[j]));
            }
        }
        graph.getData().addAll(series);
        Button goBack = new Button("Return");
        goBack.setOnAction((ActionEvent e) ->
                loadApplication(user));
        VBox graphLayout = new VBox();
        graphLayout.setAlignment(Pos.CENTER);
        graphLayout.getChildren().addAll(graph, goBack);
        Scene graphScene = new Scene(graphLayout, 500, 400);
        window.setScene(graphScene);
        window.show();
    }

    public void addUser(User user) {
        userArr.add(user);
       // ArrayList<HashMap<String, String>> myUserList = getUserList();
        userRef.setValue(userArr);
    }

    public void addSourceReport(SourceReport report) {
        sourceReportList.add(report);
       // ArrayList<HashMap<String, Object>> mySourceReportList = getSourceReportList();
        sourceRef.setValue(sourceReportList);
    }

    public void addPurityReport (PurityReport report) {
        purityReportList.add(report);
        //ArrayList<HashMap<String, Object>> myPurityReportList = getPurityReportList();
        purityRef.setValue(purityReportList);
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

    public ArrayList<SourceReport> getSourceReportList() { return sourceReportList; }

    public ArrayList<PurityReport> getPurityReportList() { return purityReportList; }

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

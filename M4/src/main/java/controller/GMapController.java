package main.java.controller;

import com.lynden.gmapsfx.GoogleMapView;
        import com.lynden.gmapsfx.MapComponentInitializedListener;
        import com.lynden.gmapsfx.javascript.object.GoogleMap;
        import com.lynden.gmapsfx.javascript.object.LatLong;
        import com.lynden.gmapsfx.javascript.object.MapOptions;
        import com.lynden.gmapsfx.javascript.object.Marker;
        import com.lynden.gmapsfx.javascript.object.MarkerOptions;
        import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.tools.javac.code.Source;
import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.Button;
        import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import main.java.model.SourceReport;


public class GMapController implements Initializable, MapComponentInitializedListener {

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;

    private Main myApp;

    public void setMainApp(Main mainApp) {
        myApp = mainApp;
    }

    @Override
    public void mapInitialized() {
        MapOptions mapOptions = new MapOptions();
        LatLong center = new LatLong(34, -88);
        mapOptions.center(center)
                .zoom(9)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .mapType(MapTypeIdEnum.TERRAIN);
        map = mapView.createMap(mapOptions);

        ArrayList<SourceReport> myReports = myApp.getSourceReportList();
        for (SourceReport report : myReports) {
            MarkerOptions option = new MarkerOptions();
            double latitude = report.get_latitude();
            double longitude = report.get_longitude();
            LatLong posPair = new LatLong(latitude, longitude);
            option.position(posPair);
            Marker myMarker = new Marker(option);
            map.addMarker(myMarker);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mapView.addMapInializedListener(this);
    }

    @FXML
    public void onCloseMenu() {
        myApp.closeMapView();
    }
}

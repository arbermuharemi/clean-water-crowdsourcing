package main.java.controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.Button;
import netscape.javascript.JSObject;
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
        ArrayList<SourceReport> myReports = myApp.getSourceReportList();
        double latDefault = myReports.get(myReports.size() - 1).get_latitude();
        double longDefault = myReports.get(myReports.size() - 1).get_longitude();
        LatLong center = new LatLong(latDefault, longDefault);
        mapOptions.center(center).zoom(8)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .mapType(MapTypeIdEnum.TERRAIN);
        map = mapView.createMap(mapOptions);
        for (SourceReport report : myReports) {
            MarkerOptions option = new MarkerOptions();
            double latitude = report.get_latitude();
            double longitude = report.get_longitude();
            LatLong posPair = new LatLong(latitude, longitude);
            option.position(posPair)
                    .visible(Boolean.TRUE)
                    .title(report.getTitle());
            mapOptions.center(posPair);

            Marker myMarker = new Marker(option);
            map.addMarker(myMarker);
            map.addUIEventHandler(myMarker,
                    UIEventType.click,
                    (JSObject obj) -> {
                        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                        infoWindowOptions.content(report.getDescription());

                        InfoWindow window = new InfoWindow(infoWindowOptions);
                        window.open(map, myMarker);
                    });
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

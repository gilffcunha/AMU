package com.example.lightmonitor;

import android.graphics.Color;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.OnClick;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;


/**
 * Reference: https://github.com/googlesamples/android-play-location/tree/master/LocationUpdates
 */

public class MapDisplay extends AppCompatActivity implements OnMapReadyCallback {

    private HashMap<Integer, Sample> samples;

    // MAP
    private MapView mapView;
    private GoogleMap gmap;

    private static final String TAG = MapDisplay.class.getSimpleName();

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_display);
        ButterKnife.bind(this);


        // initializate map view
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = findViewById(R.id.map_view);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);

    }

    // SHOW ROUTE

    @OnClick(R.id.btn_show_route)
    public void showMapRoute() {

        // Samples from experiment
        samples = (HashMap<Integer, Sample> ) getIntent().getExtras().get("Samples");

        // ROUTE ON MAP

        // PLOT
        PolylineOptions plo =  new PolylineOptions();

        LatLng coord = new LatLng(40.0332629, -7.8896263) ;

        for( Sample s : samples.values()) {
            coord = new LatLng(s.getLatitude(), s.getLongitude());

            System.out.print("COORDENADAS:\n" + coord);

            plo.add(coord);
        }

        gmap.setMinZoomPreference(11);

        gmap.moveCamera(CameraUpdateFactory.newLatLng(coord));

        plo.color(Color.GREEN);
        plo.geodesic(true);
        plo.startCap(new RoundCap());
        plo.width(20);
        plo.jointType(JointType.BEVEL);

        gmap.addPolyline(plo);
    }


    // SHOW LIGHT AREAS

    @OnClick(R.id.btn_show_areas)
    public void showLightAreas() { }

    // CLOSE

    @OnClick(R.id.btn_close)
    public void closeActivity() {
        finish();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        // MAP
        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);

    }

    @Override
    public void onResume() {
        super.onResume();

        // MAP
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // MAP
        mapView.onStop();
    }

    // MAP
    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }
    // MAP
    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }
    // MAP
    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }
    // MAP
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    // MAP
    @Override
    public void onMapReady(GoogleMap googleMap) {


        // MAP
        gmap = googleMap;
        gmap.setMinZoomPreference(7);

        LatLng portugal = new LatLng(40.0332629, -7.8896263);
        gmap.moveCamera(CameraUpdateFactory.newLatLng(portugal));

    }
}
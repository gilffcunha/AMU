package com.example.luxapp.Activities;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.luxapp.R;
import com.example.luxapp.Classes.*;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

import java.util.HashMap;
import java.util.Map;

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


    private int userID;
    private int experimentID;
    private double avg_lux;
    private HashMap<Integer, Sample> samples;
    private TextView quality;
    private TextView rank;

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

        experimentID = (Integer) getIntent().getExtras().get("experimentID");
        userID = (Integer) getIntent().getExtras().get("userID");
        quality = findViewById(R.id.quality);
        rank = findViewById(R.id.rank);
        avg_lux = 0;

        // initializate map view
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = findViewById(R.id.map_view);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);


    }

    // SHOW INFO

    @OnClick(R.id.btn_show_info)
    public void showInfo() {
        Toast.makeText(MapDisplay.this, "A obter informação...", Toast.LENGTH_SHORT).show();

    // SHOW MAP
        // Samples from experiment
        samples = (HashMap<Integer, Sample>) getIntent().getExtras().get("Samples");

        // ROUTE ON MAP

        // PLOT
        PolylineOptions plo =  new PolylineOptions();

        LatLng coord = new LatLng(40.0332629, -7.8896263) ;

        for( Sample s : samples.values()) {
            coord = new LatLng(s.getLatitude(), s.getLongitude());


            plo.add(coord);
        }

        gmap.setMinZoomPreference(16);

        gmap.moveCamera(CameraUpdateFactory.newLatLng(coord));

        plo.color(Color.GREEN);
        plo.geodesic(true);
        plo.startCap(new RoundCap());
        plo.width(20);
        plo.jointType(JointType.BEVEL);

        gmap.addPolyline(plo);


    // SHOW STATISTICS (LUX QUALITY + RANK)

        // CALCULATE AVG_LUX

        double al = 0;

        for(Sample s : samples.values())
            al+=s.getLuminosity();

        avg_lux = al/(samples.size());

        setExpLux();

        // QUALITY
        getExpQuality();


    }

    void setExpLux(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.LUX_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // GET RANK
                getExpRank();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError) {
                    Toast.makeText(MapDisplay.this, "Timeout Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NoConnectionError) {
                    Toast.makeText(MapDisplay.this, "No Connection Error", Toast.LENGTH_SHORT).show();
                    Log.v("Error",error.toString());
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(MapDisplay.this, "Authentication Failure Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(MapDisplay.this, "Network Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(MapDisplay.this, "Server Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(MapDisplay.this, "JSON Parse Error", Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Constants.KEY_EXP_ID, String.valueOf(experimentID));
                params.put(Constants.KEY_LUX,String.valueOf(avg_lux));
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("User-Agent", "LuxApp");
                return headers;
            }
        };

        MySingleton.getInstance(MapDisplay.this).addToRequestQueue(stringRequest);
    }


    void getExpQuality(){
        if (avg_lux<= 10) {
            quality.setText("Terrible");
            quality.setTextColor(Color.RED);
        }
        if (avg_lux>10 && avg_lux <=20) {
            quality.setText("Bad");
            quality.setTextColor(Color.RED);
        }
        if (avg_lux>20 && avg_lux <=30) {
            quality.setText("Average");
            quality.setTextColor(Color.YELLOW);
        }
        if (avg_lux>30 && avg_lux <50) {
            quality.setText("Good");
            quality.setTextColor(Color.GREEN);
        }
        if (avg_lux>=50) {
            quality.setText("Excelent");
            quality.setTextColor(Color.GREEN);
        }

    }

    void getExpRank(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.RANK_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.contains("Falha na conexão...")){
                    int rank_temp = Integer.parseInt(response);
                    if (rank_temp<=10)
                        rank.setTextColor(Color.GREEN);
                    if (rank_temp>10 && rank_temp<20)
                        rank.setTextColor(Color.YELLOW);
                    else
                        rank.setTextColor(Color.RED);

                    rank.setText(response);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError) {
                    Toast.makeText(MapDisplay.this, "Timeout Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NoConnectionError) {
                    Toast.makeText(MapDisplay.this, "No Connection Error", Toast.LENGTH_SHORT).show();
                    Log.v("Error",error.toString());
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(MapDisplay.this, "Authentication Failure Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(MapDisplay.this, "Network Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(MapDisplay.this, "Server Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(MapDisplay.this, "JSON Parse Error", Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Constants.KEY_EXP_ID, String.valueOf(experimentID));
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("User-Agent", "LuxApp");
                return headers;
            }
        };

        MySingleton.getInstance(MapDisplay.this).addToRequestQueue(stringRequest);
    }

    // CLOSE

    @OnClick(R.id.btn_menu)
    public void menu(){
        // Menu Activity transition
        try {
            Intent intent = new Intent(MapDisplay.this, MenuActivity.class);
            intent.putExtra("userID", userID);
            startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(MapDisplay.this).toBundle());
        } catch(Exception e) {
            e.printStackTrace();
        }
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
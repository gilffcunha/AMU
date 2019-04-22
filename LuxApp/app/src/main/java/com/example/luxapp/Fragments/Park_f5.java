package com.example.luxapp.Fragments;

import com.example.luxapp.Activities.MapDisplay;
import com.example.luxapp.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.Toast;


import com.example.luxapp.BuildConfig;
import com.example.luxapp.Classes.*;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Service;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

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
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




public class Park_f5 extends Fragment implements SensorEventListener{

    public Park_f5() {}
    // ENTITIES
    private Experiment experiment;
    private Sample sample;
    private HashMap<Integer, Sample> samples;

    // LIGHT SENSOR
    private SensorManager sensorManager;
    private Sensor sensor;
    private  SensorEvent event;

    private int sample_N;
    private  int index;
    private ProgressBar pBar;

    private static final String TAG = Park_f5.class.getSimpleName();


    @BindView(R.id.updated_on)
    TextView txtUpdatedOn;

    @BindView(R.id.btn_start_park)
    Button btnStartUpdates;

    @BindView(R.id.btn_stop_park)
    Button btnStopUpdates;

    // location last updated time
    private String mLastUpdateTime;

    // location updates interval - 1sec
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 1000;

    // fastest updates interval - 1 sec
    // location updates will be received if another app is requesting the locations
    // than your app can handle
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 1000;

    private static final int REQUEST_CHECK_SETTINGS = 100;


    // bunch of location related apis
    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;
    private Location mCurrentLocation;

    // boolean flag to toggle the ui
    private Boolean mRequestingLocationUpdates;

    private Activity activity;
    private int userID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        userID = (Integer) activity.getIntent().getExtras().get("userID");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.park_step5, container, false);
        ButterKnife.bind(this,view);

        // initialize the necessary libraries
        init();

        // restore the values from saved instance state
        restoreValuesFromBundle(savedInstanceState);

        // initialize light sensor
        sensorManager = (SensorManager) activity.getSystemService(Service.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        // progress bar
        pBar = activity.findViewById(R.id.progressBar);
        btnStopUpdates.setVisibility(View.INVISIBLE);


        return view;

    }


    private void init() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
        mSettingsClient = LocationServices.getSettingsClient(activity);

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                // location is received
                mCurrentLocation = locationResult.getLastLocation();
                mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());

                updateLocationUI();
            }
        };

        mRequestingLocationUpdates = false;

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();


        index = 0;

        // EXPERIMENT
        experiment = new Experiment();

        experiment.setAndroidVersion(Build.VERSION.RELEASE);
        experiment.setBrand(Build.BRAND);
        experiment.setModel(Build.MODEL);
        experiment.setProtocolId(3);
        experiment.setUserId(userID);
    }

    /**
     * Restoring values from saved instance state
     */
    private void restoreValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("is_requesting_updates")) {
                mRequestingLocationUpdates = savedInstanceState.getBoolean("is_requesting_updates");
            }

            if (savedInstanceState.containsKey("last_known_location")) {
                mCurrentLocation = savedInstanceState.getParcelable("last_known_location");
            }

            if (savedInstanceState.containsKey("last_updated_on")) {
                mLastUpdateTime = savedInstanceState.getString("last_updated_on");
            }
        }

        updateLocationUI();
    }


    /**
     * Update the UI displaying the location data
     * and toggling the buttons
     */
    private void updateLocationUI() {


        if (mCurrentLocation != null) {

            // location last updated time
            txtUpdatedOn.setText("Última amostra: " + mLastUpdateTime);


            // SAMPLE
            sample = new Sample();

            // Coordinates
            sample.setLatitude(mCurrentLocation.getLatitude());
            sample.setLongitude(mCurrentLocation.getLongitude());

            // Light - level of luminosity
            if (event.sensor.getType() == Sensor.TYPE_LIGHT)
                sample.setLuminosity(event.values[0]);

            experiment.addSample(sample);
        }

        toggleButtons();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("is_requesting_updates", mRequestingLocationUpdates);
        outState.putParcelable("last_known_location", mCurrentLocation);
        outState.putString("last_updated_on", mLastUpdateTime);
    }

    private void toggleButtons() {
        if (mRequestingLocationUpdates) {
            btnStartUpdates.setEnabled(false);
            btnStopUpdates.setEnabled(true);
        } else {
            btnStartUpdates.setEnabled(true);
            btnStopUpdates.setEnabled(false);
        }
    }

    /**
     * Starting location updates
     * Check whether location settings are satisfied and then
     * location updates will be requested
     */
    private void startLocationUpdates() {

        mSettingsClient
                .checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(activity, new OnSuccessListener<LocationSettingsResponse>() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        Log.i(TAG, "All location settings are satisfied.");

                        Toast.makeText(activity.getApplicationContext(), "A experiência começou!", Toast.LENGTH_SHORT).show();

                        //noinspection MissingPermission
                        mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                                mLocationCallback, Looper.myLooper());

                        updateLocationUI();
                    }
                })
                .addOnFailureListener(activity, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        int statusCode = ((ApiException) e).getStatusCode();
                        switch (statusCode) {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                Log.i(TAG, "Location settings are not satisfied. Attempting to upgrade " +
                                        "location settings ");
                                try {
                                    // Show the dialog by calling startResolutionForResult(), and check the
                                    // result in onActivityResult().
                                    ResolvableApiException rae = (ResolvableApiException) e;
                                    rae.startResolutionForResult(activity, REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException sie) {
                                    Log.i(TAG, "PendingIntent unable to execute request.");
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                String errorMessage = "Location settings are inadequate, and cannot be " +
                                        "fixed here. Fix in Settings.";
                                Log.e(TAG, errorMessage);

                                Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG).show();
                        }

                        updateLocationUI();
                    }
                });
    }

    // START EXPERIMENT
    @OnClick(R.id.btn_start_park)
    public void startLocationButtonClick() {

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        btnStartUpdates.setVisibility(View.INVISIBLE);
        btnStopUpdates.setVisibility(View.VISIBLE);

        // IF IT'S NIGHT
        //if( (timeOfDay >= 21 && timeOfDay <= 24) || (timeOfDay >= 0 && timeOfDay <= 5)) {
            // Requesting ACCESS_FINE_LOCATION using Dexter library
            Dexter.withActivity(activity)
                    .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {
                            mRequestingLocationUpdates = true;
                            startLocationUpdates();
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse response) {
                            if (response.isPermanentlyDenied()) {
                                // open device settings when the permission is
                                // denied permanently
                                openSettings();
                            }
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    }).check();
       /* }else{ // IF ITS DAY
            Toast.makeText(activity, "A experiência apenas pode ser realizada à noite (21h às 5h)", Toast.LENGTH_LONG).show();
        }*/
    }

    // STOP EXPERIMENT
    @OnClick(R.id.btn_stop_park)
    public void stopLocationButtonClick() {
        mRequestingLocationUpdates = false;
        stopLocationUpdates(); // STOP
        sendData(); // SEND DATA
    }

    public void stopLocationUpdates() {
        // Removing location updates
        mFusedLocationClient
                .removeLocationUpdates(mLocationCallback)
                .addOnCompleteListener(activity, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(activity.getApplicationContext(), "A experiência foi concluida!", Toast.LENGTH_SHORT).show();
                        toggleButtons();
                    }
                });
    }


    // SHOW MAP
    public void showMapRoute() {

        samples = experiment.getSamples();

        if (!samples.isEmpty()) {
            try {
                Intent intent = new Intent(activity, MapDisplay.class);
                intent.putExtra("Samples", samples);
                intent.putExtra("userID", userID);
                intent.putExtra("experimentID", experiment.getId());
                startActivity(intent,
                        ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        else {
            Toast.makeText(activity.getApplicationContext(), "Experiência tem de ser concluida", Toast.LENGTH_SHORT).show();
        }

    }


    // SEND DATA
    public void sendData() {

        samples = experiment.getSamples();
        pBar = activity.findViewById(R.id.progressBar);

        // SEND EXPERIMENT AND SAMPLES TO DB
        if (experiment != null && !samples.isEmpty()) {
            pBar.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

            Toast.makeText(activity.getApplicationContext(), "A enviar dados...", Toast.LENGTH_SHORT).show();

            // SEND Experiment
            sendExperiment();

        }else {
            pBar.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
            Toast.makeText(activity.getApplicationContext(), "A experiência tem de ser concluida primeiro!", Toast.LENGTH_SHORT).show();
        }
    }


    // SEND EXPERIMENT
    public void sendExperiment() {

        // POST DATA
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.EXPERIMENT_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.contains("Falha no envio dos dados...")){
                    experiment.setId(Integer.parseInt(response));
                    System.out.println("INFO:");
                    System.out.println("EXPERIMENT: " + experiment.toString());
                    // SEND Samples
                    sample_N = samples.size() - 1;
                    sample = samples.get(index);
                    sample.setExperimentID(experiment.getId());
                    System.out.println("SAMPLE: "+sample.toString());
                    sendSample();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error instanceof TimeoutError){
                    Toast.makeText(activity, "Timeout Error", Toast.LENGTH_SHORT).show();
                }else if(error instanceof NoConnectionError){
                    Toast.makeText(activity, "No Connection Error", Toast.LENGTH_SHORT).show();
                }else if(error instanceof AuthFailureError){
                    Toast.makeText(activity, "Authentication Failure Error", Toast.LENGTH_SHORT).show();
                }else if(error instanceof NetworkError){
                    Toast.makeText(activity, "Network Error", Toast.LENGTH_SHORT).show();
                }else if(error instanceof ServerError){
                    Toast.makeText(activity, "Server Error", Toast.LENGTH_SHORT).show();
                }else if(error instanceof ParseError){
                    Toast.makeText(activity, "JSON Parse Error", Toast.LENGTH_SHORT).show();
                }
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String,String>();
                params.put(Constants.KEY_ANDROID,experiment.getAndroidVersion());
                params.put(Constants.KEY_BRAND,experiment.getBrand());
                params.put(Constants.KEY_MODEL,experiment.getModel());
                params.put(Constants.KEY_PROTOCOL_ID,String.valueOf(experiment.getProtocolId()));
                params.put(Constants.KEY_USER_ID,String.valueOf(experiment.getUserId()));

                return params;
            }

            @Override
            public Map<String,String> getHeaders() throws AuthFailureError{
                Map<String,String> headers = new HashMap<String,String>();
                headers.put("User-Agent","LuxApp");
                return  headers;
            }
        };

        MySingleton.getInstance(activity).addToRequestQueue(stringRequest);

    }

    // SEND SAMPLE
    public void sendSample() {

        // POST DATA
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.SAMPLE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.contains("Dados enviados com sucesso!")) {
                    // Last sample
                    if (index == sample_N) {
                        pBar.setProgress(100);
                        Toast.makeText(activity, response, Toast.LENGTH_SHORT).show();
                        showMapRoute(); // SHOW MAP
                    }else{
                        index++;
                        sample = samples.get(index);
                        sample.setExperimentID(experiment.getId());
                        System.out.println("SAMPLE: "+sample.toString());
                        sendSample();
                    }
                }else{
                    pBar.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
                    Toast.makeText(activity, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error instanceof TimeoutError){
                    Toast.makeText(activity, "Timeout Error", Toast.LENGTH_SHORT).show();
                }else if(error instanceof NoConnectionError){
                    Toast.makeText(activity, "No Connection Error", Toast.LENGTH_SHORT).show();
                }else if(error instanceof AuthFailureError){
                    Toast.makeText(activity, "Authentication Failure Error", Toast.LENGTH_SHORT).show();
                }else if(error instanceof NetworkError){
                    Toast.makeText(activity, "Network Error", Toast.LENGTH_SHORT).show();
                }else if(error instanceof ServerError){
                    Toast.makeText(activity, "Server Error", Toast.LENGTH_SHORT).show();
                }else if(error instanceof ParseError){
                    Toast.makeText(activity, "JSON Parse Error", Toast.LENGTH_SHORT).show();
                }
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String,String>();
                params.put(Constants.KEY_LAT,String.valueOf(sample.getLatitude()));
                params.put(Constants.KEY_LONG,String.valueOf(sample.getLongitude()));
                params.put(Constants.KEY_LUM,String.valueOf(sample.getLuminosity()));
                params.put(Constants.KEY_TIME,sample.getTimestamp().toString());
                params.put(Constants.KEY_EXP_ID,String.valueOf(sample.getExperimentID()));

                return params;
            }

            @Override
            public Map<String,String> getHeaders() throws AuthFailureError{
                Map<String,String> headers = new HashMap<String,String>();
                headers.put("User-Agent","LuxApp");
                return  headers;
            }
        };

        MySingleton.getInstance(activity).addToRequestQueue(stringRequest);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            // Check for the integer request code originally supplied to startResolutionForResult().
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Log.e(TAG, "User agreed to make required location settings changes.");
                        // Nothing to do. startLocationupdates() gets called in onResume again.
                        break;
                    case Activity.RESULT_CANCELED:
                        Log.e(TAG, "User chose not to make required location settings changes.");
                        mRequestingLocationUpdates = false;
                        break;
                }
                break;
        }
    }

    private void openSettings() {
        Intent intent = new Intent();
        intent.setAction(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package",
                BuildConfig.APPLICATION_ID, null);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();

        // LIGHT SENSOR
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        // Resuming location updates depending on button state and
        // allowed permissions
        if (mRequestingLocationUpdates && checkPermissions()) {
            startLocationUpdates();
        }

        updateLocationUI();
    }

    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }


    @Override
    public void onPause() {
        super.onPause();

        // LIGHT SENSOR
        sensorManager.unregisterListener(this);

        if (mRequestingLocationUpdates) {
            // pausing location updates
            stopLocationUpdates();
        }

    }

    // LIGHT SENSOR
    @Override
    public void onSensorChanged(SensorEvent event1) {
        event = event1;
    }
    // LIGHT SENSOR
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

package com.example.luxapp.Classes;

public class Constants {

    public static final String DB_URL = "http://luxandroidapp.000webhostapp.com/connect/";

    public static final String REGISTER_URL = DB_URL + "register.php";
    public static final String LOGIN_URL = DB_URL + "login.php";
    public static final String EXPERIMENT_URL = DB_URL + "experiment.php";
    public static final String SAMPLE_URL = DB_URL + "sample.php";
    public static final String RANK_URL = DB_URL + "rank.php";
    public static final String LUX_URL = DB_URL + "lux.php";

    // USER
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    // EXPERIMENT
    public static final String KEY_ANDROID = "android_version";
    public static final String KEY_MODEL = "model";
    public static final String KEY_BRAND = "brand";
    public static final String KEY_LUX = "lux";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_PROTOCOL_ID = "protocol_id";

    // SAMPLE

    public static final String KEY_LAT = "latitude";
    public static final String KEY_LONG = "longitude";
    public static final String KEY_LUM = "luminosity";
    public static final String KEY_TIME= "timestamp";
    public static final String KEY_EXP_ID = "experimentID";

}

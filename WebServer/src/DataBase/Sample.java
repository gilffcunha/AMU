package DataBase;

import java.io.Serializable;
import java.sql.Timestamp;

public class Sample implements Serializable
{
    private int id;
    private double latitude;
    private double longitude;
    private double luminosity;
    private Timestamp timestamp;
    private int experimentID;

    public Sample(int id, double latitude, double longitude, double luminosity,int experimentID) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.luminosity = luminosity;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.experimentID = experimentID;
    }

    public Sample(Sample a)
    {
        this.id = a.getId();
        this.latitude = a.getLatitude();
        this.longitude = a.getLongitude();
        this.luminosity = a.getLuminosity();
        this.experimentID = a.getExperimentID();
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public Sample()
    {
        this.id = 0;
        this.latitude = 0;
        this.longitude = 0;
        this.luminosity = 0;
        this.experimentID = 0;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id) {this.id = id;}

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(double luminosity) {
        this.luminosity = luminosity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getExperimentID() {
        return experimentID;
    }

    public void setExperimentID(int experimentID) {
        this.experimentID = experimentID;
    }

    public Sample clone()
    {
        return new Sample(this);
    }

    public  String toString(){return "Id: "+id+"| Latitude: "+latitude+" | Longitude: "+longitude+"| Luminosity: "+luminosity+" | Timestamp: "+timestamp+ " | Experiment ID: " + experimentID;}

    public void insertIntoDB(InsertionDB insert){
        insert.insertSample(this);
    }
}

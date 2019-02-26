package com.example.lightmonitor;

import java.time.LocalDateTime;

public class Sample
{
    private int id;
    private double latitude;
    private double longitude;
    private double luminosity;
    private LocalDateTime timestamp;

    public Sample(int id, double latitude, double longitude, double luminusidade, LocalDateTime timestamp) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.luminosity = luminusidade;
        this.timestamp = timestamp;
    }

    public Sample(Sample a)
    {
        this.id = a.getId();
        this.latitude = a.getLatitude();
        this.longitude = a.getLongitude();
        this.luminosity = a.getLuminusity();
        this.timestamp = a.getTimestamp();
    }

    public Sample()
    {
        this.id = 0;
        this.latitude = 0;
        this.longitude = 0;
        this.luminosity = 0;
        this.timestamp = LocalDateTime.now();
    }

    public int getId()
    {
        return id;
    }

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

    public double getLuminusity() {
        return luminosity;
    }

    public void setLuminusity(double luminusidade) {
        this.luminosity = luminusidade;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Sample clone()
    {
        return new Sample(this);
    }

    public  String toString(){return "Id: "+id+"| Latitude: "+latitude+" | Longitude: "+longitude+"| Luminosidade: "+luminosity+" ! Timestamp: "+timestamp;}
}

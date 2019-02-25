package com.example.lightmonitor;

import java.time.LocalDateTime;

public class Amostra
{
    private int id;
    private double latitude;
    private double longitude;
    private double luminusidade;
    private LocalDateTime timestamp;

    public Amostra(int id, double latitude, double longitude, double luminusidade, LocalDateTime timestamp) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.luminusidade = luminusidade;
        this.timestamp = timestamp;
    }

    public Amostra(Amostra a)
    {
        this.id = a.getId();
        this.latitude = a.getLatitude();
        this.longitude = a.getLongitude();
        this.luminusidade = a.getLuminusidade();
        this.timestamp = a.getTimestamp();
    }

    public Amostra()
    {
        this.id = 0;
        this.latitude = 0;
        this.longitude = 0;
        this.luminusidade = 0;
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

    public double getLuminusidade() {
        return luminusidade;
    }

    public void setLuminusidade(double luminusidade) {
        this.luminusidade = luminusidade;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Amostra clone()
    {
        return new Amostra(this);
    }

    public  String toString(){return "Id: "+id+"| Latitude: "+latitude+" | Longitude: "+longitude+"| Luminosidade: "+luminusidade+" ! Timestamp: "+timestamp;}
}

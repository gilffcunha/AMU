package com.example.lightmonitor;

import java.time.LocalDateTime;

public class Amostra
{
    private int id;
    private String latitude;
    private String longitude;
    private double luminusidade;
    private LocalDateTime timestamp;

    public Amostra(int id, String latitude, String longitude, double luminusidade, LocalDateTime timestamp) {
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

    public int getId()
    {
        return id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
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
}

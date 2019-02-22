package Server;

import java.time.LocalDateTime;

public class Amostra
{
    private String latitude;
    private String longitude;
    private double luminusidade;
    private LocalDateTime timestamp;

    public Amostra(String latitude, String longitude, double luminusidade, LocalDateTime timestamp) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.luminusidade = luminusidade;
        this.timestamp = timestamp;
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
}

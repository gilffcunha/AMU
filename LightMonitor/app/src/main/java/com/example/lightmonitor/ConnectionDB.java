package com.example.lightmonitor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB
{
    private String url;
    private String username;
    private String password;


    public ConnectionDB(String url, String username, String password)
    {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public ConnectionDB()
    {
        this.url = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        this.username = "root";
        this.password = "26121996"; // metam a vossa password
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

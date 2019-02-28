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
        this.password = "pass"; // metam a vossa password
    }

    public boolean isConnected()
    {
        boolean ret = false;
        Connection con = null;

        con = establishConnection();

        if(con != null)
        {
            ret = true;
        }

        return ret;
    }

    public Connection establishConnection()
    {
        System.out.println("Connecting database...");

        try (
                Connection connection = DriverManager.getConnection(url, username, password)) {
                System.out.println("Database connected!");
                return connection;
        } catch (
                SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
}

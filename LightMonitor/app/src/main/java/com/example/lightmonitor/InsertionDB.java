package com.example.lightmonitor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class InsertionDB
{
    private ConnectionDB connection;

    public InsertionDB()
    {
        this.connection = new ConnectionDB();
    }

    /*
    public boolean login(String username, String password)
    {
        boolean ret = false;
        String query = "Select C.Name, C.password from User as C" +
                    " where C.Name = '" + username + "';";

        try {

            Connection connection = DriverManager.getConnection(this.connection.getUrl(), this.connection.getUsername(), this.connection.getPassword());
            Statement stmt = connection.prepareStatement(query);
            ResultSet res = stmt.executeQuery(query);

            if(res != null)
            {
                String pass = (String) res.getObject("Password");
                if(pass.equals(password))
                {
                    ret = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return ret;
        }
    }
    */

    public void insertUser(String username, String email, String password)
    {

        boolean ret = false;
        String query =  "INSERT INTO User (Name, Email, Password) " +
                "VALUES ('" + username + "' ,'" +  email + "' ,'" + password + "');";

        try {
            Connection connection = DriverManager.getConnection(this.connection.getUrl(), this.connection.getUsername(), this.connection.getPassword());

            Statement stmt = connection.prepareStatement(query);

            ((PreparedStatement) stmt).execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertProtocol(String type, String text)
    {
        String query = "INSERT INTO Protocol (Type, Description)\n" +
                    "VALUES ('" + type + "' ,'" +  text  + "');";

        try {
             Connection connection = DriverManager.getConnection(this.connection.getUrl(), this.connection.getUsername(), this.connection.getPassword());

            Statement stmt = connection.prepareStatement(query);

            ((PreparedStatement) stmt).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
    public void insertSample(int experimentID, double latitude, double longitude, double luminusity, LocalDateTime date)
    {
        boolean ret = false;
        Timestamp timestamp = (Timestamp) Timestamp.valueOf(String.valueOf(date));

        LocalDateTime ldt = LocalDateTime.now();
        Timestamp t = (Timestamp) Timestamp.from(Instant.from(date.atZone(ZoneId.from(date))));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss");

        String formatDateTime = date.format(formatter);


        String query = "INSERT INTO Sample (Latitude, Longitude, Luminusity, TimeStamp, Experiment_ID) " +
                    "VALUES (" + latitude + ", " +  longitude  + ", " + luminusity + " ," + timestamp + ", " + experimentID + ");";

        try {
            Connection connection = DriverManager.getConnection(this.connection.getUrl(), this.connection.getUsername(), this.connection.getPassword());

            Statement stmt = connection.prepareStatement(query);

            ((PreparedStatement) stmt).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/
    public void insertExperiment(String androidVersion, String brand, String model, int userId, int protocol) {
        boolean ret = false;
        String query = "INSERT INTO Experiment (AndroidVersion, Brand, Model, User_ID, Protocol_ID)\n" +
                "VALUES ('" + androidVersion + "' ,'" + brand + "' ,'" + model + "' ,'" + userId + "' ,'" + protocol + "');";

        try {
            Connection connection = DriverManager.getConnection(this.connection.getUrl(), this.connection.getUsername(), this.connection.getPassword());

            Statement stmt = connection.prepareStatement(query);

            ((PreparedStatement) stmt).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

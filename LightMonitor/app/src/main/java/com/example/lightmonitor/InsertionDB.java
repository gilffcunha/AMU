package com.example.lightmonitor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class InsertionDB
{
    private ConnectionDB connDB;
    private Connection connection;
    private String encrypt_key;

    public InsertionDB(ConnectionDB connDB)
    {
        this.connDB = connDB;
        this.encrypt_key = "AmU1819";
        this.connection = null;
    }


    public boolean validateLogin(String email, String password)  {
        boolean ret = false;
        String query = "Select Email, CAST(aes_decrypt(password,'AmU1819') as char(45)) as Password from User" +
                    " where Email = '" + email + "';";

        try {
            connection = DriverManager.getConnection(connDB.getUrl(), connDB.getUsername(), connDB.getPassword());
            Statement stmt = connection.prepareStatement(query);
            ResultSet res = stmt.executeQuery(query);
            res.next();

            if(res != null)
            {
                String pass = res.getString("Password");
                if(pass.equals(password))
                {
                    ret = true;
                }
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return ret;
        }
    }


    public void insertUser(User user)
    {   // INSERT USER
        String query =  "INSERT INTO User (Name, Email, Password) " +
                "VALUES ('" + user.getName() + "' ,'" +  user.getEmail() + "' , AES_ENCRYPT('" + user.getPassword() + "','"+encrypt_key+"'));";

        // SET USER ID
        String query2 =  "SELECT MAX(id) as ID FROM User; ";


        try {
            connection = DriverManager.getConnection(connDB.getUrl(), connDB.getUsername(), connDB.getPassword());

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.execute();


            Statement stmt2 = connection.createStatement();
            ResultSet res = stmt2.executeQuery(query2);
            res.next();

            if(res != null) {
                int id = res.getInt("ID");
                user.setId(id);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertProtocol(Protocol protocol)
    {   // INSERT PROTOCOL
        String query = "INSERT INTO Protocol (Type, Description)\n" +
                    "VALUES ('" + protocol.getType() + "' ,'" +  protocol.getDescription()  + "');";

        // SET PROTOCOL ID
        String query2 =  "SELECT MAX(id) as ID FROM Protocol; ";

        try {
            connection = DriverManager.getConnection(connDB.getUrl(), connDB.getUsername(), connDB.getPassword());

            Statement stmt = connection.prepareStatement(query);
            ((PreparedStatement) stmt).execute();

            Statement stmt2 = connection.createStatement();
            ResultSet res = stmt2.executeQuery(query2);
            res.next();

            if(res != null) {
                int id = res.getInt("ID");
                protocol.setId(id);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertExperiment(Experiment experiment) {

        // INSERT EXPERIMENT
        String query = "INSERT INTO Experiment (AndroidVersion, Brand, Model, User_ID, Protocol_ID)\n" +
                "VALUES ('" + experiment.getAndroidVersion() + "' ,'" + experiment.getBrand() + "' ,'" + experiment.getModel() + "' ,'" + experiment.getUserId() + "' ,'" + experiment.getProtocolId() + "');";

        // SET EXPERIMENT ID
        String query2 =  "SELECT MAX(id) as ID FROM Experiment; ";

        try {
            connection = DriverManager.getConnection(connDB.getUrl(), connDB.getUsername(), connDB.getPassword());

            Statement stmt = connection.prepareStatement(query);
            ((PreparedStatement) stmt).execute();

            Statement stmt2 = connection.createStatement();
            ResultSet res = stmt2.executeQuery(query2);
            res.next();

            if(res != null) {
                int id = res.getInt("ID");
                experiment.setId(id);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertSample(Sample sample)
    {
        // INSERT SAMPLE
        String query = "INSERT INTO Sample (Latitude, Longitude, Luminosity, TimeStamp, Experiment_ID) " +
                    "VALUES (" + sample.getLatitude() + ", " +  sample.getLongitude()  + ", " + sample.getLuminosity() + " ,'" + sample.getTimestamp() + "', " + sample.getExperimentID() + ");";

        // SET SAMPLE ID
        String query2 =  "SELECT MAX(id) as ID FROM Sample; ";

        try {
            connection = DriverManager.getConnection(connDB.getUrl(), connDB.getUsername(), connDB.getPassword());

            Statement stmt = connection.prepareStatement(query);
            ((PreparedStatement) stmt).execute();

            Statement stmt2 = connection.createStatement();
            ResultSet res = stmt2.executeQuery(query2);
            res.next();

            if(res != null) {
                int id = res.getInt("ID");
                sample.setId(id);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

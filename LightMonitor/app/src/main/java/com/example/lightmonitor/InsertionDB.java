package com.example.lightmonitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class InsertionDB
{
    private ConnectionDB connection;

    public InsertionDB()
    {
        this.connection = new ConnectionDB();
    }

    public boolean login(String username, String password)
    {
        boolean ret = false;

        try {
            Statement stmt = this.connection.establishConnection().createStatement();
            ResultSet res = stmt.executeQuery("Select C.username, C.password from User as C" +
                    " where C.username = " + username );

            if(res != null)
            {
                String pass = (String) res.getObject("password");
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

    public boolean insertUser(String username, String email, String password)
    {
        boolean ret = false;

        try {
            Statement stmt = this.connection.establishConnection().createStatement();
            ResultSet res = stmt.executeQuery("INSERT INTO User (Name, Email, Password)\n" +
                    "VALUES (" + username + " ," +  email + " ," + password + ");");

            if(res != null)
            {
                ret = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return ret;
        }
    }

    public boolean insertProtocol(String type, String text)
    {
        boolean ret = false;

        try {
            Statement stmt = this.connection.establishConnection().createStatement();
            ResultSet res = stmt.executeQuery("INSERT INTO Protocol (Type, text)\n" +
                    "VALUES (" + type + " ," +  text  + ");");

            if(res != null)
            {
                ret = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return ret;
        }
    }

    public boolean insertSample(int experimentID, double latitude, double longitude, double luminusity, LocalDateTime date)
    {
        boolean ret = false;
        Timestamp timestamp = Timestamp.valueOf(String.valueOf(date));

        try {
            Statement stmt = this.connection.establishConnection().createStatement();
            ResultSet res = stmt.executeQuery("INSERT INTO Sample (Latitude, Longitude, Luminusity, TimeStamp, Experiment_ID)\n" +
                    "VALUES (" + latitude + " ," +  longitude  + " ," + luminusity + " ," + timestamp + " ," + experimentID + ");");

            if(res != null)
            {
                ret = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return ret;
        }
    }

    public boolean insertExperiment(String androidVersion, String brand, String model, int userId, int protocol)
    {
        boolean ret = false;

        try {
            Statement stmt = this.connection.establishConnection().createStatement();
            ResultSet res = stmt.executeQuery("INSERT INTO Experiment (AndroidVersion, Brand, Model, User_ID, Protocol_ID)\n" +
                    "VALUES (" + androidVersion + " ," +  brand  + " ," + model + " ," + userId + " ," + protocol + ");");

            if(res != null)
            {
                ret = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return ret;
        }
    }
}

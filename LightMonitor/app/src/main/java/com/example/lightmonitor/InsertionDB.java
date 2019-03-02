package com.example.lightmonitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}

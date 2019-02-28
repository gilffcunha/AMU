package com.example.lightmonitor;

import java.sql.Connection;

public class TestConnection
{
    public static void main(String[] args)
    {
        ConnectionDB con = new ConnectionDB();
        boolean ret;
        ret = con.isConnected();
        System.out.println("Resultado " + ret);
    }
}

package com.example.lightmonitor;

import java.sql.Connection;
import java.time.LocalDateTime;

public class TestConnection
{
    public static void main(String[] args)
    {
        //ConnectionDB con = new ConnectionDB();
       // boolean ret;
        //ret = con.isConnected();
        InsertionDB i = new InsertionDB();
       // i.insertUser("Goncalo","gcamaz@sapo.pt","123");
       // i.insertProtocol("teste", "hehehehehe");
       // i.insertExperiment("android","samsungas","modelo",1,1);
        i.insertSample(1,0.1,0.2,0.3, LocalDateTime.now());
         //System.out.println("Resultado " + ret);
    }
}

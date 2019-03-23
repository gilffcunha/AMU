package com.example.luxapp.Classes;

import java.io.Serializable;
import java.util.HashMap;

public class Experiment implements Serializable
{
    private int id;
    private int protocolID;
    private String androidVersion;
    private String brand;
    private String model;
    private int userId;
    private HashMap<Integer, Sample> samples;

    private int index;

    public Experiment(int id, int protocolID, String androidVersion, String brand, String model, int userId, HashMap<Integer, Sample> samples) {

        this.id = id;
        this.protocolID = protocolID;
        this.androidVersion = androidVersion;
        this.brand = brand;
        this.model = model;
        this.userId = userId;
        this.samples = this.validateSamples(samples);

        this.index = 0;
    }

    public Experiment(Experiment e)
    {
        this.id = e.getId();
        this.protocolID = e.getProtocolId();
        this.samples = e.getSamples();
        this.brand = e.getBrand();
        this.model = e.getModel();
        this.userId = e.getUserId();
        this.androidVersion = e.getAndroidVersion();
    }

    public Experiment()
    {
        this.id = 0;
        this.protocolID = 0;
        this.androidVersion = "";
        this.brand = "";
        this.model = "";
        this.userId = 0;
        this.samples = new HashMap<>();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id) {this.id = id;}

    public int getProtocolId() {
        return protocolID;
    }

    public void setProtocolId(int protocolID) {
        this.protocolID = protocolID;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId) {this.userId = userId;}

    public HashMap<Integer, Sample> getSamples() {
        return samples;
    }

    public void setSamples(HashMap<Integer, Sample> samples) {
        this.samples = samples;
    }

    public HashMap<Integer, Sample> validateSamples(HashMap<Integer, Sample> samples)
    {
        HashMap<Integer, Sample> validSamples = new HashMap<Integer, Sample>();

        for(Sample a : samples.values())
            validSamples.put(a.getId(), a.clone());

        return validSamples;
    }

    public Experiment clone()
    {
        return new Experiment(this);
    }

    public String toString() {return "Experiment - User: " + userId + "Protocol: " + protocolID + "Android version: "+ androidVersion +" "+"| Brand: "+ brand +" "+"| Model: "+ model +"\n";}


    public void addSample(Sample a){
        samples.put(index,a);
        a.setId(index);
        index++;
    }

}


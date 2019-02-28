package com.example.lightmonitor;

import java.util.HashMap;

public class Experiment
{
    private Protocol protocol;
    private String androidVersion;
    private String brand;
    private String model;
    private HashMap<Integer, Sample> samples;

    public Experiment(Protocol protocol, String androidVersion, String brand, String model, HashMap<Integer, Sample> samples) {
        this.protocol = protocol;
        this.androidVersion = androidVersion;
        this.brand = brand;
        this.model = model;
        this.samples = this.validateSamples(samples);
    }

    public Experiment(Experiment e)
    {
        this.protocol = e.getProtocol();
        this.samples = e.getSamples();
        this.brand = e.getBrand();
        this.model = e.getModel();
        this.androidVersion = e.getAndroidVersion();
    }

    public Experiment()
    {
        this.protocol = new Protocol();
        this.androidVersion = "";
        this.brand = "";
        this.model = "";
        this.samples = new HashMap<>();
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
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
        {
            if(DefaultSettings.validate(a))
            {
                validSamples.put(a.getId(), a.clone());
            }
        }

        return validSamples;
    }

    public Experiment clone()
    {
        return new Experiment(this);
    }

    public String toString() {return "Protocol: "+ protocol +" "+"| Android version: "+ androidVersion +" "+"| Brand: "+ brand +" "+"| Model: "+ model +"\n";}


    public void addSample(Sample a){
        samples.put(a.getId(),a);
    }
}

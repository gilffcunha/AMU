package com.example.lightmonitor;

import java.util.HashMap;
import java.util.Objects;

public class Protocol
{
    private int id;
    private String type;
    private String description;
    private HashMap<Integer, Experiment> experiments;

    public Protocol(int id, String type, String description,HashMap<Integer, Experiment> experiments) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.experiments = new HashMap<Integer, Experiment>();

        for(int i : experiments.keySet())
            this.experiments.put(i,experiments.get(i).clone());
    }

    public Protocol() {
        this.id = 0;
        this.type = "";
        this.description = "";
        this.experiments = new HashMap<Integer, Experiment>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<Integer, Experiment> getExperiment() {
        return this.experiments;
    }

    public void setExperiment(HashMap<Integer, Experiment> experiments) {
        for(int i : experiments.keySet())
            this.experiments.put(i,experiments.get(i).clone());
    }

    public void addExperiment(Experiment exp)
    {
        this.experiments.put(exp.getId(), exp);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Protocol)) return false;
        Protocol protocol = (Protocol) o;
        return getId() == protocol.getId() &&
                Objects.equals(getType(), protocol.getType()) &&
                Objects.equals(getDescription(), protocol.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getDescription());
    }

    @Override
    public String toString() {
        return "Protocol{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public void insertIntoDB(InsertionDB insert){
        insert.insertProtocol(this);
    }
}
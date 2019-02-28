package com.example.lightmonitor;

import java.util.HashMap;

public class User
{
     private int id;
     private String name;
     private String email;
     private String password;
     private HashMap<Integer, Experiment> experiments;

     public User(int id, String name, String email, String password, Double height, HashMap<Integer, Experiment> experiments) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.password = password;
            this.experiments = new HashMap<Integer, Experiment>();

            for(int i : experiments.keySet())
                this.experiments.put(i,experiments.get(i).clone());

     }

     public User()
     {
            this.id = 0;
            this.name = "";
            this.email = "";
            this.password = "";
            this.experiments = new HashMap<Integer, Experiment>();
     }

     public int getId()
    {
        return id;
    }

    public void setId(int id) {this.id = id;}

     public String getName() {
            return name;
        }

     public void setName(String name) {
            this.name = name;
        }

     public String getEmail() {
            return email;
        }

     public void setEmail(String email) {
            this.email = email;
        }

     public String getPassword() {
            return password;
        }

     public void setPassword(String password) {
            this.password = password;
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
}

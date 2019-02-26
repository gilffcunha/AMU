package com.example.lightmonitor;

import java.util.HashMap;

public class User
{
     private String name;
     private String email;
     private String password;
     private Double height;
     private Experiment experiment;

     public User(String name, String email, String password, Double height, Experiment experiment) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.height = height;
            this.experiment = experiment;
     }

     public User()
     {
            this.name = "";
            this.email = "";
            this.password = "";
            this.height = 0.0;
            this.experiment = new Experiment();
     }

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

     public Double getHeight() {
            return height;
        }

     public void setHeight(Double height) {
            this.height = height;
        }

     public Experiment getExperiment() {
            return experiment;
        }

     public void setExperiment(Experiment experiment) {
            this.experiment = experiment;
        }

     public void addExperiment(String protocolo, String versao_Android, String marca, String modelo, HashMap<Integer, Sample> amostras)
     {
         Experiment e = new Experiment(protocolo, versao_Android, marca, modelo, amostras);

         this.setExperiment(e.clone());
     }
}

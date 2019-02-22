package com.example.lightmonitor;

import java.util.HashMap;

public class Utilizador
{
     private String nome;
     private String email;
     private String password;
     private Double altura;
     private Experiencia experiencia;

     public Utilizador(String nome, String email, String password, Double altura, Experiencia experiencia) {
            this.nome = nome;
            this.email = email;
            this.password = password;
            this.altura = altura;
            this.experiencia = experiencia;
     }

     public Utilizador()
     {
            this.nome = "";
            this.email = "";
            this.password = "";
            this.altura = 0.0;
            this.experiencia = new Experiencia();
     }

     public String getNome() {
            return nome;
        }

     public void setNome(String nome) {
            this.nome = nome;
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

     public Double getAltura() {
            return altura;
        }

     public void setAltura(Double altura) {
            this.altura = altura;
        }

     public Experiencia getExperiencia() {
            return experiencia;
        }

     public void setExperiencia(Experiencia experiencia) {
            this.experiencia = experiencia;
        }

     public void addExperiencia(String protocolo, String versao_Android, String marca, String modelo, HashMap<Integer, Amostra> amostras)
     {
         Experiencia e = new Experiencia(protocolo, versao_Android, marca, modelo, amostras);

         this.setExperiencia(e.clone());
     }
}

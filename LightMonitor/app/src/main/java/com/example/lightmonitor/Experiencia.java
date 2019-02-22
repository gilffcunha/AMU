package com.example.lightmonitor;

import java.util.HashMap;

public class Experiencia
{
    private String protocolo;
    private String versao_Android;
    private String marca;
    private String modelo;
    private HashMap<Integer, Amostra> amostras;

    public Experiencia(String protocolo, String versao_Android, String marca, String modelo, HashMap<Integer, Amostra> amostras) {
        this.protocolo = protocolo;
        this.versao_Android = versao_Android;
        this.marca = marca;
        this.modelo = modelo;
        this.amostras = this.validaAmostras(amostras);
    }

    public Experiencia(Experiencia e)
    {
        this.protocolo = e.getProtocolo();
        this.amostras = e.getAmostras();
        this.marca = e.getMarca();
        this.modelo = e.getModelo();
        this.versao_Android = e.getVersao_Android();
    }

    public Experiencia()
    {
        this.protocolo = "";
        this.versao_Android = "";
        this.marca = "";
        this.modelo = "";
        this.amostras = new HashMap<>();
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getVersao_Android() {
        return versao_Android;
    }

    public void setVersao_Android(String versao_Android) {
        this.versao_Android = versao_Android;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public HashMap<Integer, Amostra> getAmostras() {
        return amostras;
    }

    public void setAmostras(HashMap<Integer, Amostra> amostras) {
        this.amostras = amostras;
    }

    public HashMap<Integer, Amostra> validaAmostras(HashMap<Integer,Amostra> amostras)
    {
        HashMap<Integer,Amostra> amostrasValidas = new HashMap<Integer,Amostra>();

        for(Amostra a : amostras.values())
        {
            if(DefaultSettings.valida(a))
            {
                amostrasValidas.put(a.getId(), a.clone());
            }
        }

        return amostrasValidas;
    }

    public Experiencia clone()
    {
        return new Experiencia(this);
    }
}

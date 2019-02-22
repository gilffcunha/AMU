package Server;

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
        this.amostras = amostras;
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


}

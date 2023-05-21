package com.example.riegoback.dto;


public class DatosAmbiente {
    private int tempAmbiente;
    private int humTerreno;
    private int humAmbiente;
    private String uuid;

    public DatosAmbiente() {
    }

    public DatosAmbiente(int tempAmbiente, int humTerreno, int humAmbiente, String uuid) {
        this.tempAmbiente = tempAmbiente;
        this.humTerreno = humTerreno;
        this.humAmbiente = humAmbiente;
        this.uuid = uuid;
    }

    public int getTempAmbiente() {
        return tempAmbiente;
    }

    public void setTempAmbiente(int tempAmbiente) {
        this.tempAmbiente = tempAmbiente;
    }

    public int getHumTerreno() {
        return humTerreno;
    }

    public void setHumTerreno(int humTerreno) {
        this.humTerreno = humTerreno;
    }

    public int getHumAmbiente() {
        return humAmbiente;
    }

    public void setHumAmbiente(int humAmbiente) {
        this.humAmbiente = humAmbiente;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}

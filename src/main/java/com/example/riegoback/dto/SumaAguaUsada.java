package com.example.riegoback.dto;

import java.sql.Timestamp;

public class SumaAguaUsada {
    private Float sumaAgua;
    private Integer fecha;
    private Integer numRiego;

    public Integer getNumRiego() {
        return numRiego;
    }

    public void setNumRiego(Integer numRiego) {
        this.numRiego = numRiego;
    }

    public Float getSumaAgua() {
        return sumaAgua;
    }

    public void setSumaAgua(Float sumaAgua) {
        this.sumaAgua = sumaAgua;
    }

    public Integer getFecha() {
        return fecha;
    }

    public void setFecha(Integer fecha) {
        this.fecha = fecha;
    }
}

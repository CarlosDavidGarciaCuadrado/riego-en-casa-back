package com.example.riegoback.dto;

import java.util.Date;

public class DatosAhorro {
    private Date fechaInicio;
    private Date fechaFinal;
    private Long tiempoRiego;
    private Float aguaUsada;
    private String uuid;
    private int tempAmbienteInicial;
    private int tempAmbienteFinal;
    private int humTerrenoInicial;
    private int humTerrenoFinal;

    public DatosAhorro(Date fecha, Long tiempoRiego, Float aguaUsada, String uuid, int tempAmbiente, int humTerreno, int humAmbiente) {
        this.fechaInicio = fecha;
        this.tiempoRiego = tiempoRiego;
        this.aguaUsada = aguaUsada;
        this.uuid = uuid;
        this.tempAmbienteInicial = tempAmbiente;
        this.humTerrenoInicial = humTerreno;
        this.humTerrenoFinal = humAmbiente;
    }

    public DatosAhorro() {
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public int getTempAmbienteFinal() {
        return tempAmbienteFinal;
    }

    public void setTempAmbienteFinal(int tempAmbienteFinal) {
        this.tempAmbienteFinal = tempAmbienteFinal;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Long getTiempoRiego() {
        return tiempoRiego;
    }

    public void setTiempoRiego(Long tiempoRiego) {
        this.tiempoRiego = tiempoRiego;
    }

    public Float getAguaUsada() {
        return aguaUsada;
    }

    public void setAguaUsada(Float aguaUsada) {
        this.aguaUsada = aguaUsada;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getTempAmbienteInicial() {
        return tempAmbienteInicial;
    }

    public void setTempAmbienteInicial(int tempAmbienteInicial) {
        this.tempAmbienteInicial = tempAmbienteInicial;
    }

    public int getHumTerrenoInicial() {
        return humTerrenoInicial;
    }

    public void setHumTerrenoInicial(int humTerrenoInicial) {
        this.humTerrenoInicial = humTerrenoInicial;
    }

    public int getHumTerrenoFinal() {
        return humTerrenoFinal;
    }

    public void setHumTerrenoFinal(int humTerrenoFinal) {
        this.humTerrenoFinal = humTerrenoFinal;
    }
}

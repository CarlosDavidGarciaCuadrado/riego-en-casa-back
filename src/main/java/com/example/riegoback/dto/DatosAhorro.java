package com.example.riegoback.dto;

import java.util.Date;

public class DatosAhorro {
    private Date fecha;
    private Long tiempoRiego;
    private Long aguaUsada;
    private String estadoRiego;
    private String uuid;
    private int tempAmbiente;
    private int humTerreno;
    private int humAmbiente;

    public DatosAhorro(Date fecha, Long tiempoRiego, Long aguaUsada, String estadoRiego, String uuid, int tempAmbiente, int humTerreno, int humAmbiente) {
        this.fecha = fecha;
        this.tiempoRiego = tiempoRiego;
        this.aguaUsada = aguaUsada;
        this.estadoRiego = estadoRiego;
        this.uuid = uuid;
        this.tempAmbiente = tempAmbiente;
        this.humTerreno = humTerreno;
        this.humAmbiente = humAmbiente;
    }

    public DatosAhorro() {
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getTiempoRiego() {
        return tiempoRiego;
    }

    public void setTiempoRiego(Long tiempoRiego) {
        this.tiempoRiego = tiempoRiego;
    }

    public Long getAguaUsada() {
        return aguaUsada;
    }

    public void setAguaUsada(Long aguaUsada) {
        this.aguaUsada = aguaUsada;
    }

    public String getEstadoRiego() {
        return estadoRiego;
    }

    public void setEstadoRiego(String estadoRiego) {
        this.estadoRiego = estadoRiego;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
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
}

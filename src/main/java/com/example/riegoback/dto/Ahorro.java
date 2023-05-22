package com.example.riegoback.dto;

import java.util.Date;

public class Ahorro {
    private Long id;
    private Date fecha;
    private Long tiempoRiego;
    private Float aguaUsada;
    private String estadoRiego;
    private String uuid;

    public Ahorro(Long id, Date fecha, Long tiempoRiego, Float aguaUsada,String estadoRiego, String uuid) {
        this.id = id;
        this.fecha = fecha;
        this.tiempoRiego = tiempoRiego;
        this.aguaUsada = aguaUsada;
        this.estadoRiego = estadoRiego;
        this.uuid = uuid;
    }

    public Ahorro() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEstadoRiego() {
        return estadoRiego;
    }

    public void setEstadoRiego(String estadoRiego) {
        this.estadoRiego = estadoRiego;
    }
}

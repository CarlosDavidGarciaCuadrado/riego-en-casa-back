package com.example.riegoback.dto;


import java.sql.Date;

public class DatosAmbiente {
    private int codigo;
    private int tempAmbiente;
    private int humTerreno;
    private int humAmbiente;
    private int phTerreno;
    private Date fecha;
    private String estadoRiego;
    private Long tiempoRiego;

    public DatosAmbiente() {
    }

    public DatosAmbiente(int codigo, int tempAmbiente, int humTerreno, int humAmbiente, int phTerreno, Date fecha, String estadoRiego, Long tiempoRiego) {
        this.codigo = codigo;
        this.tempAmbiente = tempAmbiente;
        this.humTerreno = humTerreno;
        this.humAmbiente = humAmbiente;
        this.phTerreno = phTerreno;
        this.fecha = fecha;
        this.estadoRiego = estadoRiego;
        this.tiempoRiego = tiempoRiego;
    }

    public String getEstadoRiego() {
        return estadoRiego;
    }

    public void setEstadoRiego(String estadoRiego) {
        this.estadoRiego = estadoRiego;
    }

    public Long getTiempoRiego() {
        return tiempoRiego;
    }

    public void setTiempoRiego(Long tiempoRiego) {
        this.tiempoRiego = tiempoRiego;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public int getPhTerreno() {
        return phTerreno;
    }

    public void setPhTerreno(int phTerreno) {
        this.phTerreno = phTerreno;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}

package com.example.riegoback.dao;

import com.example.riegoback.Exceptions.ExceptionConexion;
import com.example.riegoback.Exceptions.ExceptionDao;
import com.example.riegoback.dto.Ahorro;
import com.example.riegoback.dto.DatosAhorro;
import com.example.riegoback.dto.DatosAmbiente;
import com.example.riegoback.dto.SumaAguaUsada;

import java.sql.Date;
import java.util.List;

public interface DatosAmbienteDao {
    void saveDatos(DatosAmbiente datosAmbiente) throws ExceptionDao, ExceptionConexion;
    List<DatosAmbiente> getAllDatos() throws ExceptionDao, ExceptionConexion;
    DatosAmbiente getByIdDatos(String id) throws ExceptionDao, ExceptionConexion;
    void delete(String id) throws ExceptionDao, ExceptionConexion;
    void deleteAll() throws ExceptionDao, ExceptionConexion;
    List<DatosAhorro> getAll() throws ExceptionDao, ExceptionConexion;
    List<DatosAhorro> getByDates(String fechaInicio, String fechaFin) throws ExceptionDao;
    List<SumaAguaUsada> getBySumaConsume(String fechaInicio, String fechaFin) throws ExceptionDao;
    Integer getIdDatos(String uuid) throws ExceptionDao;
}

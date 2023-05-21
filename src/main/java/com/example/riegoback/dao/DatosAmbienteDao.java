package com.example.riegoback.dao;

import com.example.riegoback.Exceptions.ExceptionConexion;
import com.example.riegoback.Exceptions.ExceptionDao;
import com.example.riegoback.dto.Ahorro;
import com.example.riegoback.dto.DatosAhorro;
import com.example.riegoback.dto.DatosAmbiente;
import java.util.List;

public interface DatosAmbienteDao {
    void saveDatos(DatosAmbiente datosAmbiente) throws ExceptionDao, ExceptionConexion;
    List<DatosAmbiente> getAllDatos() throws ExceptionDao, ExceptionConexion;
    DatosAmbiente getByIdDatos(String id) throws ExceptionDao, ExceptionConexion;
    void delete(String id) throws ExceptionDao, ExceptionConexion;
    void deleteAll() throws ExceptionDao, ExceptionConexion;
    List<DatosAhorro> getAll() throws ExceptionDao, ExceptionConexion;
}

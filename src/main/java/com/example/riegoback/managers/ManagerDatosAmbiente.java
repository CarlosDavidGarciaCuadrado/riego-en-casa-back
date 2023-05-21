package com.example.riegoback.managers;

import com.example.riegoback.Exceptions.ExceptionConexion;
import com.example.riegoback.Exceptions.ExceptionDao;
import com.example.riegoback.Exceptions.ExceptionManager;
import com.example.riegoback.dto.Ahorro;
import com.example.riegoback.dto.DatosAhorro;
import com.example.riegoback.dto.DatosAmbiente;
import java.util.List;

public interface ManagerDatosAmbiente {
    void saveDatos(DatosAmbiente datosAmbiente)throws ExceptionManager;
    List<DatosAmbiente> listAllDatos()throws ExceptionManager;
    DatosAmbiente getByIdDatos(String id)throws ExceptionManager;
    void delete(String id)throws ExceptionManager;
    void deleteAll()throws ExceptionManager;
    List<DatosAhorro> getAll() throws ExceptionDao, ExceptionConexion, ExceptionManager;
}

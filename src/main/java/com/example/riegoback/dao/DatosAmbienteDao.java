package com.example.riegoback.dao;

import com.example.riegoback.Exceptions.ExceptionConexion;
import com.example.riegoback.Exceptions.ExceptionDao;
import com.example.riegoback.dto.DatosAmbiente;
import java.util.List;

public interface DatosAmbienteDao {
    public void save(DatosAmbiente datosAmbiente) throws ExceptionDao, ExceptionConexion;
    public List<DatosAmbiente> getAll() throws ExceptionDao, ExceptionConexion;
    public DatosAmbiente getById(int id) throws ExceptionDao, ExceptionConexion;
    public void delete(int id) throws ExceptionDao, ExceptionConexion;
    public void deleteAll() throws ExceptionDao, ExceptionConexion;
}

package com.example.riegoback.dao;

import com.example.riegoback.Exceptions.ExceptionConexion;
import com.example.riegoback.Exceptions.ExceptionDao;
import com.example.riegoback.dto.Ahorro;
import com.example.riegoback.dto.DatosAmbiente;

import java.util.List;

public interface AhorroDao {
    void saveAhorro(Ahorro ahorro) throws ExceptionDao, ExceptionConexion;
    List<Ahorro> getAllAhorro() throws ExceptionDao, ExceptionConexion;
    Ahorro getByIdAhorro(Long id) throws ExceptionDao, ExceptionConexion;
    List<Ahorro> getByUiidAhorro(String uuid) throws ExceptionDao, ExceptionConexion;
    void deleteAllAhorro() throws ExceptionDao, ExceptionConexion;
    void deleteAhorro(Long id) throws ExceptionDao, ExceptionConexion;
}

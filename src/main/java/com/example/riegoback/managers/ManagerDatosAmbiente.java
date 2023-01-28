package com.example.riegoback.managers;

import com.example.riegoback.Exceptions.ExceptionManager;
import com.example.riegoback.dto.DatosAmbiente;
import java.util.List;

public interface ManagerDatosAmbiente {
    public void save(DatosAmbiente datosAmbiente)throws ExceptionManager;
    public List<DatosAmbiente> listAll()throws ExceptionManager;
    public DatosAmbiente getById(int id)throws ExceptionManager;
    public void delete(int id)throws ExceptionManager;
    public void deleteAll()throws ExceptionManager;
}

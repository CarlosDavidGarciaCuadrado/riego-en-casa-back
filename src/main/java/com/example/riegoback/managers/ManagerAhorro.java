package com.example.riegoback.managers;

import com.example.riegoback.Exceptions.ExceptionManager;
import com.example.riegoback.dto.Ahorro;

import java.util.List;

public interface ManagerAhorro {
    void saveAhorro(Ahorro ahorro)throws ExceptionManager;
    List<Ahorro> listAllAhorro()throws ExceptionManager;
    Ahorro getByIdAhorro(Long id)throws ExceptionManager;
    List<Ahorro> getByUuidAhorro(String uuid)throws ExceptionManager;
    void delete(Long id)throws ExceptionManager;
    void deleteAllAhorro()throws ExceptionManager;
}

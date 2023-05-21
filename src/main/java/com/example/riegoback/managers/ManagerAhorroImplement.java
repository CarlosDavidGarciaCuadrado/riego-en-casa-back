package com.example.riegoback.managers;

import com.example.riegoback.Exceptions.ExceptionConexion;
import com.example.riegoback.Exceptions.ExceptionDao;
import com.example.riegoback.Exceptions.ExceptionManager;
import com.example.riegoback.config.Constants;
import com.example.riegoback.dao.AhorroDao;
import com.example.riegoback.db.MngrConexion;
import com.example.riegoback.dto.Ahorro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerAhorroImplement implements ManagerAhorro{

    MngrConexion mngrConexion = MngrConexion.getInstance();

    @Autowired
    AhorroDao ahorroDao;

    public ManagerAhorroImplement() throws ExceptionConexion {
    }

    @Override
    public void saveAhorro(Ahorro ahorro) throws ExceptionManager {
        try {
            mngrConexion.openConexion();
            if(ahorro.getEstadoRiego().equals(Constants.ESTADO_FINAL)){
                ahorro.setAguaUsada(calcularAguaUsada(ahorro.getTiempoRiego()));
            }
            ahorroDao.saveAhorro(ahorro);
            mngrConexion.conexion.getConexion().commit();
        } catch (ExceptionDao e) {
            try {
                mngrConexion.getConexion().rollback();
            } catch (SQLException ex) {
                throw new ExceptionManager(ex);
            } catch (ExceptionConexion ex) {
                ex.printStackTrace();
            }
            throw new ExceptionManager(e);
        } catch (ExceptionConexion e) {
            throw new ExceptionManager(e);
        } catch (SQLException e) {
            throw new ExceptionManager(e);
        }finally {
            try {
                mngrConexion.closeConexion();
            } catch (ExceptionConexion e) {
                throw new ExceptionManager(e);
            }
        }
    }

    @Override
    public List<Ahorro> listAllAhorro() throws ExceptionManager {
        List<Ahorro> lista = new ArrayList<>();
        try {
            mngrConexion.openConexion();
            lista = ahorroDao.getAllAhorro();
            mngrConexion.conexion.getConexion().commit();
        } catch (ExceptionConexion e) {
            try {
                mngrConexion.conexion.getConexion().rollback();
            } catch (SQLException ex) {
                throw new ExceptionManager(ex);
            }
            throw new ExceptionManager(e);
        }catch (Exception e){
            throw new ExceptionManager(e);
        }finally {
            try {
                mngrConexion.closeConexion();
            } catch (ExceptionConexion e) {
                throw new ExceptionManager(e);
            }
        }
        return lista;
    }

    @Override
    public Ahorro getByIdAhorro(Long id) throws ExceptionManager {
        Ahorro ahorro = null;
        try {
            mngrConexion.openConexion();
            ahorro = ahorroDao.getByIdAhorro(id);
            mngrConexion.conexion.getConexion().commit();
        } catch (ExceptionConexion e) {
            try {
                mngrConexion.conexion.getConexion().rollback();
            } catch (SQLException ex) {
                throw new ExceptionManager(e);
            }
            throw new ExceptionManager(e);
        }catch (Exception e){
            throw new ExceptionManager(e);
        }finally {
            try {
                mngrConexion.closeConexion();
            } catch (ExceptionConexion e) {
                throw new ExceptionManager(e);
            }
        }
        return ahorro;
    }

    @Override
    public List<Ahorro> getByUuidAhorro(String uuid) throws ExceptionManager {
        List<Ahorro> lista = new ArrayList<>();
        try {
            mngrConexion.openConexion();
            lista = ahorroDao.getByUiidAhorro(uuid);
            mngrConexion.conexion.getConexion().commit();
        } catch (ExceptionConexion e) {
            try {
                mngrConexion.conexion.getConexion().rollback();
            } catch (SQLException ex) {
                throw new ExceptionManager(ex);
            }
            throw new ExceptionManager(e);
        }catch (Exception e){
            throw new ExceptionManager(e);
        }finally {
            try {
                mngrConexion.closeConexion();
            } catch (ExceptionConexion e) {
                throw new ExceptionManager(e);
            }
        }
        return lista;
    }


    @Override
    public void delete(Long id) throws ExceptionManager {

    }

    @Override
    public void deleteAllAhorro() throws ExceptionManager {
        try {
            mngrConexion.openConexion();
            ahorroDao.deleteAllAhorro();
            mngrConexion.conexion.getConexion().commit();
        } catch (ExceptionConexion e) {
            try {
                mngrConexion.conexion.getConexion().rollback();
            } catch (SQLException ex) {
                throw new ExceptionManager(ex);
            }
            throw new ExceptionManager(e);
        }catch (Exception e){
            throw new ExceptionManager(e);
        }finally {
            try {
                mngrConexion.closeConexion();
            } catch (ExceptionConexion e) {
                throw new ExceptionManager(e);
            }
        }
    }


    private Long calcularAguaUsada(Long timeMili){
        return Constants.FLUJO_DE_AGUA * getHoras(timeMili);
    }

    private Long getHoras(Long timeMili) {
        var residuo = timeMili % Constants.HORA_DE_MILI;
        var horas = (timeMili - residuo) / Constants.HORA_DE_MILI;
        var mr = residuo % Constants.MINUTO_DE_MILI;
        var minutos = (residuo - mr) / Constants.MINUTO_DE_MILI;
        minutos = minutos/Constants.MINUTOS_EN_HORA;
        var sr = mr % Constants.SEGUNDO_DE_MILI;
        var segundos = (mr - sr) / Constants.SEGUNDO_DE_MILI;
        segundos = segundos/Constants.SEGUNDOS_EN_HORA;
        return horas +  minutos +  segundos;
    }


}

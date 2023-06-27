package com.example.riegoback.managers;

import com.example.riegoback.Exceptions.ExceptionConexion;
import com.example.riegoback.Exceptions.ExceptionDao;
import com.example.riegoback.Exceptions.ExceptionManager;
import com.example.riegoback.dao.DatosAmbienteDao;
import com.example.riegoback.db.MngrConexion;
import com.example.riegoback.dto.DatosAhorro;
import com.example.riegoback.dto.DatosAmbiente;
import com.example.riegoback.dto.SumaAguaUsada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerDatosAmbienteImplement implements ManagerDatosAmbiente{

    @Autowired
    DatosAmbienteDao datosAmbienteDao;
    MngrConexion mngrConexion = MngrConexion.getInstance();

    public ManagerDatosAmbienteImplement() throws ExceptionConexion {
    }

    @Override
    public void saveDatos(DatosAmbiente datosAmbiente) throws ExceptionManager {
        try {
            mngrConexion.openConexion();
                datosAmbienteDao.saveDatos(datosAmbiente);
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
    public List<DatosAmbiente> listAllDatos() throws ExceptionManager {
        List<DatosAmbiente> lista = new ArrayList<>();
        try {
            mngrConexion.openConexion();
            lista = datosAmbienteDao.getAllDatos();
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
    public DatosAmbiente getByIdDatos(String id) throws ExceptionManager {
        DatosAmbiente datosAmbiente = null;
        try {
            mngrConexion.openConexion();
            datosAmbiente = datosAmbienteDao.getByIdDatos(id);
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
        return datosAmbiente;
    }

    @Override
    public void delete(String id) throws ExceptionManager {
        try {
            mngrConexion.openConexion();
            datosAmbienteDao.delete(id);
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

    @Override
    public void deleteAll() throws ExceptionManager {
        try {
            mngrConexion.openConexion();
            datosAmbienteDao.deleteAll();
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

    @Override
    public List<DatosAhorro> getAll() throws ExceptionManager {
        List<DatosAhorro> lista;
        try {
            mngrConexion.openConexion();
            lista = datosAmbienteDao.getAll();
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
    public List<DatosAhorro> getByDates(String fechaInicio, String fechaFin) throws ExceptionManager {
        List<DatosAhorro> lista;
        try {
            mngrConexion.openConexion();
            lista = datosAmbienteDao.getByDates(fechaInicio, fechaFin);
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
    public List<SumaAguaUsada> getBySumaConsume(String fechaInicio, String fechaFin) throws ExceptionManager {
        List<SumaAguaUsada> lista;
        try {
            mngrConexion.openConexion();
            lista = datosAmbienteDao.getBySumaConsume(fechaInicio, fechaFin);
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
    public Integer getIdDatos(String uuid) throws ExceptionManager {
        Integer id;
        try {
            mngrConexion.openConexion();
            id = datosAmbienteDao.getIdDatos(uuid);
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
        return id;
    }

}

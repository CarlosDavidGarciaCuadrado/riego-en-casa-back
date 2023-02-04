package com.example.riegoback.managers;

import com.example.riegoback.Exceptions.ExceptionConexion;
import com.example.riegoback.Exceptions.ExceptionDao;
import com.example.riegoback.Exceptions.ExceptionManager;
import com.example.riegoback.dao.DatosAmbienteDao;
import com.example.riegoback.db.MngrConexion;
import com.example.riegoback.dto.DatosAmbiente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public void save(DatosAmbiente datosAmbiente) throws ExceptionManager {
        try {
            mngrConexion.openConexion();
            if(datosAmbienteDao.getById(datosAmbiente.getCodigo()) == null){
                datosAmbienteDao.save(datosAmbiente);
            }
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
    public List<DatosAmbiente> listAll() throws ExceptionManager {
        List<DatosAmbiente> lista = new ArrayList<>();
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
    public DatosAmbiente getById(int id) throws ExceptionManager {
        DatosAmbiente datosAmbiente = null;
        try {
            mngrConexion.openConexion();
            datosAmbiente = datosAmbienteDao.getById(id);
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
    public void delete(int id) throws ExceptionManager {
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
}

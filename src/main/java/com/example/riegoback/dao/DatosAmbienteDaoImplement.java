package com.example.riegoback.dao;

import com.example.riegoback.Exceptions.ExceptionConexion;
import com.example.riegoback.Exceptions.ExceptionDao;
import com.example.riegoback.db.MngrConexion;
import com.example.riegoback.dto.DatosAhorro;
import com.example.riegoback.dto.DatosAmbiente;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DatosAmbienteDaoImplement implements DatosAmbienteDao{

    MngrConexion mngrConexion = MngrConexion.getInstance();

    public DatosAmbienteDaoImplement() throws ExceptionConexion {
    }

    @Override
    public void saveDatos(DatosAmbiente datosAmbiente) throws ExceptionDao{
        String INSERT = "INSERT INTO datos(uuid, tempAmbiente, humTerreno, humAmbiente)values(?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = mngrConexion.getConexion().prepareStatement(INSERT);
            preparedStatement.setString(1, datosAmbiente.getUuid());
            preparedStatement.setInt(2,datosAmbiente.getTempAmbiente());
            preparedStatement.setInt(3, datosAmbiente.getHumTerreno());
            preparedStatement.setInt(4, datosAmbiente.getHumAmbiente());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionDao(e);
        } catch (ExceptionConexion e) {
            throw new ExceptionDao(e);
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new ExceptionDao(e);
            }
        }
    }

    @Override
    public List<DatosAmbiente> getAllDatos() throws ExceptionDao{
        String SELECT = "SELECT uuid, tempAmbiente, humTerreno, humAmbiente FROM datos ";
        DatosAmbiente datosAmbiente = null;
        List<DatosAmbiente> lista = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = mngrConexion.getConexion().prepareStatement(SELECT);
            resultSet = preparedStatement.executeQuery();
            if(resultSet != null){
                while (resultSet.next()) {
                    datosAmbiente = new DatosAmbiente();
                    datosAmbiente.setUuid(resultSet.getString("uuid"));
                    datosAmbiente.setTempAmbiente(resultSet.getInt("tempAmbiente"));
                    datosAmbiente.setHumTerreno(resultSet.getInt("humTerreno"));
                    datosAmbiente.setHumAmbiente(resultSet.getInt("humAmbiente"));
                    lista.add(datosAmbiente);
                }
            }
        } catch (Exception e){
            throw new ExceptionDao(e);
        }finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new ExceptionDao(e);
            }
        }
        return lista;
    }

    @Override
    public DatosAmbiente getByIdDatos(String id) throws ExceptionDao {
        String SELECT = "SELECT * FROM datos WHERE uuid = ?";
        DatosAmbiente datosAmbiente = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = mngrConexion.getConexion().prepareStatement(SELECT);
            preparedStatement.setString(1,id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet != null){
                while (resultSet.next()) {
                    datosAmbiente = new DatosAmbiente();
                    datosAmbiente.setUuid(resultSet.getString("uuid"));
                    datosAmbiente.setTempAmbiente(resultSet.getInt("tempAmbiente"));
                    datosAmbiente.setHumTerreno(resultSet.getInt("humTerreno"));
                    datosAmbiente.setHumAmbiente(resultSet.getInt("humAmbiente"));
                }
            }
        } catch (ExceptionConexion e) {
            throw new ExceptionDao(e);
        }catch (Exception e){
            throw new ExceptionDao(e);
        }finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new ExceptionDao(e);
            }
        }
        return datosAmbiente;
    }


    @Override
    public void delete(String id) throws ExceptionDao{
        String DELETE = "DELETE FROM datos WHERE uuid = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = mngrConexion.getConexion().prepareStatement(DELETE);
            preparedStatement.setString(1,id);
            preparedStatement.executeUpdate();
        } catch (ExceptionConexion e) {
            throw new ExceptionDao(e);
        }catch (Exception e){
            throw new ExceptionDao(e);
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new ExceptionDao(e);
            }
        }
    }

    @Override
    public void deleteAll() throws ExceptionDao{

        String DELETE = "DELETE FROM datos";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = mngrConexion.getConexion().prepareStatement(DELETE);
            preparedStatement.executeUpdate();
        } catch (ExceptionConexion e) {
            throw new ExceptionDao(e);
        }catch (Exception e){
            throw new ExceptionDao(e);
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new ExceptionDao(e);
            }
        }
    }

    @Override
    public List<DatosAhorro> getAll() throws ExceptionDao, ExceptionConexion {
        String SELECT = "SELECT d.uuid, d.tempAmbiente, d.humTerreno, d.humAmbiente, a.estadoRiego, a.fecha, a.aguaUsada, a.tiempoRiego " +
                "FROM datos d INNER JOIN ahorro a ON d.uuid = a.uuid where a.estadoRiego = 'Final'";
        DatosAhorro datosAhorro = null;
        List<DatosAhorro> lista = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = mngrConexion.getConexion().prepareStatement(SELECT);
            resultSet = preparedStatement.executeQuery();
            if(resultSet != null){
                while (resultSet.next()) {
                    datosAhorro = new DatosAhorro();
                    datosAhorro.setUuid(resultSet.getString("uuid"));
                    datosAhorro.setTempAmbiente(resultSet.getInt("tempAmbiente"));
                    datosAhorro.setHumTerreno(resultSet.getInt("humTerreno"));
                    datosAhorro.setHumAmbiente(resultSet.getInt("humAmbiente"));
                    datosAhorro.setEstadoRiego(resultSet.getString("estadoRiego"));
                    datosAhorro.setFecha(resultSet.getTimestamp("fecha"));
                    datosAhorro.setAguaUsada(resultSet.getLong("aguaUsada"));
                    datosAhorro.setTiempoRiego(resultSet.getLong("tiempoRiego"));
                    lista.add(datosAhorro);
                }
            }
        } catch (Exception e){
            throw new ExceptionDao(e);
        }finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new ExceptionDao(e);
            }
        }
        return lista;
    }


}

package com.example.riegoback.dao;

import com.example.riegoback.Exceptions.ExceptionConexion;
import com.example.riegoback.Exceptions.ExceptionDao;
import com.example.riegoback.db.MngrConexion;
import com.example.riegoback.dto.Ahorro;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AhorroDaoImplement implements AhorroDao{
    MngrConexion mngrConexion = MngrConexion.getInstance();

    public AhorroDaoImplement() throws ExceptionConexion {
    }

    @Override
    public void saveAhorro(Ahorro ahorro) throws ExceptionDao{
        String INSERT = "INSERT INTO ahorro(fecha, tiempoRiego, aguaUsada, estadoRiego, uuid)values(?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = mngrConexion.getConexion().prepareStatement(INSERT);
            preparedStatement.setDate(1, (Date) ahorro.getFecha());
            preparedStatement.setLong(2,ahorro.getTiempoRiego());
            preparedStatement.setLong(3, ahorro.getAguaUsada());
            preparedStatement.setString(4, ahorro.getEstadoRiego());
            preparedStatement.setString(5, ahorro.getUuid());

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
    public List<Ahorro> getAllAhorro() throws ExceptionDao{
        String SELECT = "SELECT id, fecha, tiempoRiego, aguaUsada, estadoRiego, uuid FROM ahorro ";
        Ahorro ahorro = null;
        List<Ahorro> lista = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = mngrConexion.getConexion().prepareStatement(SELECT);
            resultSet = preparedStatement.executeQuery();
            if(resultSet != null){
                while (resultSet.next()) {
                    ahorro = new Ahorro();
                    ahorro.setId(resultSet.getLong("id"));
                    ahorro.setFecha(resultSet.getTimestamp("fecha"));
                    ahorro.setTiempoRiego(resultSet.getLong("tiempoRiego"));
                    ahorro.setAguaUsada(resultSet.getLong("aguaUsada"));
                    ahorro.setEstadoRiego(resultSet.getString("estadoRiego"));
                    ahorro.setUuid(resultSet.getString("uuid"));
                    lista.add(ahorro);
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
    public Ahorro getByIdAhorro(Long id) throws ExceptionDao {
        String SELECT = "SELECT * FROM ahorro WHERE id = ?";
        Ahorro ahorro = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = mngrConexion.getConexion().prepareStatement(SELECT);
            preparedStatement.setLong(1,id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet != null){
                while (resultSet.next()) {
                    ahorro = new Ahorro();
                    ahorro.setFecha(resultSet.getDate("fecha"));
                    ahorro.setTiempoRiego(resultSet.getLong("tiempoRiego"));
                    ahorro.setAguaUsada(resultSet.getLong("aguaUsada"));
                    ahorro.setEstadoRiego(resultSet.getString("estadoRiego"));
                    ahorro.setUuid(resultSet.getString("uuid"));
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
        return ahorro;
    }

    @Override
    public List<Ahorro> getByUiidAhorro(String uuid) throws ExceptionDao{
        String SELECT = "SELECT * FROM ahorro WHERE uuid = ?";
        Ahorro ahorro = null;
        List<Ahorro> lista = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = mngrConexion.getConexion().prepareStatement(SELECT);
            resultSet = preparedStatement.executeQuery();
            if(resultSet != null){
                while (resultSet.next()) {
                    ahorro = new Ahorro();
                    ahorro.setFecha(resultSet.getDate("fecha"));
                    ahorro.setTiempoRiego(resultSet.getLong("tiempoRiego"));
                    ahorro.setAguaUsada(resultSet.getLong("aguaUsada"));
                    ahorro.setEstadoRiego(resultSet.getString("estadoRiego"));
                    ahorro.setUuid(resultSet.getString("uuid"));
                    lista.add(ahorro);
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
    public void deleteAllAhorro() throws ExceptionDao, ExceptionConexion {
        String DELETE = "DELETE FROM ahorro";
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
    public void deleteAhorro(Long id) throws ExceptionDao, ExceptionConexion {
        String DELETE = "DELETE FROM ahorro WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = mngrConexion.getConexion().prepareStatement(DELETE);
            preparedStatement.setLong(1,id);
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
}

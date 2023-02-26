package com.example.riegoback.dao;

import com.example.riegoback.Exceptions.ExceptionConexion;
import com.example.riegoback.Exceptions.ExceptionDao;
import com.example.riegoback.db.MngrConexion;
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
    public void save(DatosAmbiente datosAmbiente) throws ExceptionDao{
        String INSERT = "INSERT INTO datos(tempAmbiente, humTerreno, humAmbiente, phTerreno, fecha, estadoRiego, tiempoRiego)values(?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = mngrConexion.getConexion().prepareStatement(INSERT);
            preparedStatement.setInt(1,datosAmbiente.getTempAmbiente());
            preparedStatement.setInt(2, datosAmbiente.getHumTerreno());
            preparedStatement.setInt(3, datosAmbiente.getHumAmbiente());
            preparedStatement.setInt(4, datosAmbiente.getPhTerreno());
            preparedStatement.setDate(5, datosAmbiente.getFecha());
            preparedStatement.setString(6, datosAmbiente.getEstadoRiego());
            preparedStatement.setLong(7, datosAmbiente.getTiempoRiego());
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
    public List<DatosAmbiente> getAll() throws ExceptionDao{
        String SELECT = "SELECT codigo, tempAmbiente, humTerreno, humAmbiente, phTerreno, fecha, estadoRiego, tiempoRiego FROM datos";
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
                    datosAmbiente.setCodigo(resultSet.getInt("codigo"));
                    datosAmbiente.setTempAmbiente(resultSet.getInt("tempAmbiente"));
                    datosAmbiente.setHumTerreno(resultSet.getInt("humTerreno"));
                    datosAmbiente.setHumAmbiente(resultSet.getInt("humAmbiente"));
                    datosAmbiente.setPhTerreno(resultSet.getInt("phTerreno"));
                    datosAmbiente.setFecha(resultSet.getDate("fecha"));
                    datosAmbiente.setEstadoRiego(resultSet.getString("estadoRiego"));
                    datosAmbiente.setTiempoRiego(resultSet.getLong("tiempoRiego"));
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
    public DatosAmbiente getById(int id) throws ExceptionDao {
        String SELECT = "SELECT * FROM datos WHERE codigo = ?";
        DatosAmbiente datosAmbiente = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = mngrConexion.getConexion().prepareStatement(SELECT);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet != null){
                while (resultSet.next()) {
                    datosAmbiente = new DatosAmbiente();
                    datosAmbiente.setCodigo(resultSet.getInt("codigo"));
                    datosAmbiente.setTempAmbiente(resultSet.getInt("tempAmbiente"));
                    datosAmbiente.setHumTerreno(resultSet.getInt("humTerreno"));
                    datosAmbiente.setHumAmbiente(resultSet.getInt("humAmbiente"));
                    datosAmbiente.setPhTerreno(resultSet.getInt("phTerreno"));
                    datosAmbiente.setFecha(resultSet.getDate("fecha"));
                    datosAmbiente.setEstadoRiego(resultSet.getString("estadoRiego"));
                    datosAmbiente.setTiempoRiego(resultSet.getLong("tiempoRiego"));
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
    public void delete(int id) throws ExceptionDao{
        String DELETE = "DELETE FROM datos WHERE codigo = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = mngrConexion.getConexion().prepareStatement(DELETE);
            preparedStatement.setInt(1,id);
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
}

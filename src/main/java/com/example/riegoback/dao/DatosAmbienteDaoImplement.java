package com.example.riegoback.dao;

import com.example.riegoback.Exceptions.ExceptionConexion;
import com.example.riegoback.Exceptions.ExceptionDao;
import com.example.riegoback.db.MngrConexion;
import com.example.riegoback.dto.DatosAhorro;
import com.example.riegoback.dto.DatosAmbiente;
import com.example.riegoback.dto.SumaAguaUsada;
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
    public List<DatosAhorro>
    getAll() throws ExceptionDao{
        String SELECT = "Select \n" +
                "inicioRiego.uuid, inicioRiego.tempAmbiente as tempAmbienteInicial, finalRiego.tempAmbiente as tempAmbienteFinal, \n" +
                "inicioRiego.humTerreno as humTerrenoInicial, finalRiego.humTerreno as humTerrenoFinal, \n" +
                "inicioRiego.fecha as fechaInicio, finalRiego.fecha as fechaFinal,\n" +
                "finalRiego.aguaUsada, finalRiego.tiempoRiego \n" +
                "FROM (SELECT d.uuid, d.tempAmbiente, d.humTerreno, d.humAmbiente, a.estadoRiego, a.fecha, a.aguaUsada, a.tiempoRiego\n" +
                "   FROM ahorro a\n" +
                "   INNER JOIN datos d\n" +
                "   ON d.idDatos = a.iddatos\n" +
                "   WHERE estadoRiego = 'Inicio'\n" +
                "   AND EXISTS (\n" +
                "       SELECT estadoRiego\n" +
                "       FROM ahorro r2\n" +
                "       WHERE r2.estadoRiego = 'Final'\n" +
                "       AND r2.uuid = a.uuid\n" +
                ")) as inicioRiego \n" +
                "inner join (SELECT d.uuid, d.tempAmbiente, d.humTerreno, d.humAmbiente, a.estadoRiego, a.fecha, a.aguaUsada, a.tiempoRiego\n" +
                "   FROM ahorro a\n" +
                "   INNER JOIN datos d\n" +
                "   ON d.idDatos = a.iddatos\n" +
                "   WHERE estadoRiego = 'Final'\n" +
                "   AND EXISTS (SELECT estadoRiego\n" +
                "       FROM ahorro r2\n" +
                "       WHERE r2.estadoRiego = 'Inicio'\n" +
                "       AND r2.uuid = a.uuid\n" +
                ")) as finalRiego \n" +
                "ON inicioRiego.uuid = finalRiego.uuid\n" +
                "WHERE YEAR(finalRiego.fecha)=YEAR(CURDATE()) order by finalRiego.fecha asc";
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
                    datosAhorro.setTempAmbienteInicial(resultSet.getInt("tempAmbienteInicial"));
                    datosAhorro.setTempAmbienteFinal(resultSet.getInt("tempAmbienteFinal"));
                    datosAhorro.setHumTerrenoInicial(resultSet.getInt("humTerrenoInicial"));
                    datosAhorro.setHumTerrenoFinal(resultSet.getInt("humTerrenoFinal"));
                    datosAhorro.setFechaInicio(resultSet.getTimestamp("fechaInicio"));
                    datosAhorro.setFechaFinal(resultSet.getTimestamp("fechaFinal"));
                    datosAhorro.setAguaUsada(resultSet.getFloat("aguaUsada"));
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
    @Override
    public List<DatosAhorro> getByDates(String fechaInicio, String fechaFin) throws ExceptionDao {
        String SELECT = "Select \n" +
                "inicioRiego.uuid, inicioRiego.tempAmbiente as tempAmbienteInicial, finalRiego.tempAmbiente as tempAmbienteFinal, \n" +
                "inicioRiego.humTerreno as humTerrenoInicial, finalRiego.humTerreno as humTerrenoFinal, \n" +
                "inicioRiego.fecha as fechaInicio, finalRiego.fecha as fechaFinal,\n" +
                "finalRiego.aguaUsada, finalRiego.tiempoRiego \n" +
                "FROM (SELECT d.uuid, d.tempAmbiente, d.humTerreno, d.humAmbiente, a.estadoRiego, a.fecha, a.aguaUsada, a.tiempoRiego\n" +
                "   FROM ahorro a\n" +
                "   INNER JOIN datos d\n" +
                "   ON d.idDatos = a.iddatos\n" +
                "   WHERE estadoRiego = 'Inicio'\n" +
                "   AND EXISTS (\n" +
                "       SELECT estadoRiego\n" +
                "       FROM ahorro r2\n" +
                "       WHERE r2.estadoRiego = 'Final'\n" +
                "       AND r2.uuid = a.uuid\n" +
                ")) as inicioRiego \n" +
                "inner join (SELECT d.uuid, d.tempAmbiente, d.humTerreno, d.humAmbiente, a.estadoRiego, a.fecha, a.aguaUsada, a.tiempoRiego\n" +
                "   FROM ahorro a\n" +
                "   INNER JOIN datos d\n" +
                "   ON d.idDatos = a.iddatos\n" +
                "   WHERE estadoRiego = 'Final'\n" +
                "   AND EXISTS (SELECT estadoRiego\n" +
                "       FROM ahorro r2\n" +
                "       WHERE r2.estadoRiego = 'Inicio'\n" +
                "       AND r2.uuid = a.uuid\n" +
                ")) as finalRiego \n" +
                "ON inicioRiego.uuid = finalRiego.uuid\n" +
                "   where finalRiego.fecha >= ? AND finalRiego.fecha <= ? order by finalRiego.fecha asc";
        DatosAhorro datosAhorro = null;
        List<DatosAhorro> lista = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = mngrConexion.getConexion().prepareStatement(SELECT);
            preparedStatement.setString(1, fechaInicio);
            preparedStatement.setString(2, fechaFin);
            resultSet = preparedStatement.executeQuery();
            if(resultSet != null){
                while (resultSet.next()) {
                    datosAhorro = new DatosAhorro();
                    datosAhorro.setUuid(resultSet.getString("uuid"));
                    datosAhorro.setTempAmbienteInicial(resultSet.getInt("tempAmbienteInicial"));
                    datosAhorro.setTempAmbienteFinal(resultSet.getInt("tempAmbienteFinal"));
                    datosAhorro.setHumTerrenoInicial(resultSet.getInt("humTerrenoInicial"));
                    datosAhorro.setHumTerrenoFinal(resultSet.getInt("humTerrenoFinal"));
                    datosAhorro.setFechaInicio(resultSet.getTimestamp("fechaInicio"));
                    datosAhorro.setFechaFinal(resultSet.getTimestamp("fechaFinal"));
                    datosAhorro.setAguaUsada(resultSet.getFloat("aguaUsada"));
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

    @Override
    public List<SumaAguaUsada> getBySumaConsume(String fechaInicio, String fechaFin) throws ExceptionDao {
        String SELECT = "SELECT MONTH(a.fecha) AS fecha, SUM(a.aguaUsada) AS aguaUsada, COUNT(1) AS numRiego " +
                "FROM ahorro a where a.estadoRiego = 'Final' AND a.fecha >= ? AND a.fecha <= ? GROUP by MONTH(a.fecha) asc";
        SumaAguaUsada datosAhorro = null;
        List<SumaAguaUsada> lista = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = mngrConexion.getConexion().prepareStatement(SELECT);
            preparedStatement.setString(1, fechaInicio);
            preparedStatement.setString(2, fechaFin);
            resultSet = preparedStatement.executeQuery();
            if(resultSet != null){
                while (resultSet.next()) {
                    datosAhorro = new SumaAguaUsada();
                    datosAhorro.setFecha(resultSet.getInt("fecha"));
                    datosAhorro.setSumaAgua(resultSet.getFloat("aguaUsada"));
                    datosAhorro.setNumRiego(resultSet.getInt("numRiego"));
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

    @Override
    public Integer getIdDatos(String uuid) throws ExceptionDao {
        String SELECT = "SELECT idDatos " +
                "FROM datos where uuid = ? ";
        Integer id = 0;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = mngrConexion.getConexion().prepareStatement(SELECT);
            preparedStatement.setString(1, uuid);
            resultSet = preparedStatement.executeQuery();
            if(resultSet != null){
                while (resultSet.next()) {
                    id = resultSet.getInt("idDatos");
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
        return id;
    }

}

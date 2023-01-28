package com.example.riegoback.db;

import com.example.riegoback.Exceptions.ExceptionConexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private Connection conexion = null;

    public void open()throws ExceptionConexion {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mariadb://localhost:3306/siembraencasa", "root", "");
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/siembraencasa", "root", "toor");
            conexion.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            throw new ExceptionConexion("ERROR AL CONECTAR. "+e);
        } catch (Exception e){
            throw new ExceptionConexion(e);
        }

    }

    public Connection getConexion(){
         return this.conexion;
    }

    public void close()throws ExceptionConexion{
        try {
            this.conexion.close();
        } catch (SQLException e) {
            throw new ExceptionConexion(e);
        }
    }
}

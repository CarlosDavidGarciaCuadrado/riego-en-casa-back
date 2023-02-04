package com.example.riegoback.db;

import com.example.riegoback.Exceptions.ExceptionConexion;
import java.sql.Connection;

public class MngrConexion {

        public Conexion conexion;

   private MngrConexion() throws ExceptionConexion {
       this.conexion = new Conexion();
       openConexion();
   }

    private static MngrConexion instance;

   public static MngrConexion getInstance()throws ExceptionConexion{
       if(instance == null){
           instance = new MngrConexion();
           return instance;
       }
       return instance;
   }

    public void openConexion()throws ExceptionConexion{
       conexion.open();
    }

    public void closeConexion()throws ExceptionConexion{
       conexion.close();
    }

    public Connection getConexion()throws ExceptionConexion{
       return conexion.getConexion();
    }
}

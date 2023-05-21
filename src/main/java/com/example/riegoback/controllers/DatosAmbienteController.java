package com.example.riegoback.controllers;

import com.example.riegoback.Exceptions.ExceptionConexion;
import com.example.riegoback.Exceptions.ExceptionController;
import com.example.riegoback.Exceptions.ExceptionDao;
import com.example.riegoback.Exceptions.ExceptionManager;
import com.example.riegoback.config.Constants;
import com.example.riegoback.dto.Ahorro;
import com.example.riegoback.dto.DatosAmbiente;
import com.example.riegoback.dto.Respuesta;
import com.example.riegoback.managers.ManagerAhorro;
import com.example.riegoback.managers.ManagerDatosAmbiente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping(path = "/IrrigationSystem")
@CrossOrigin(origins = "*")
public class DatosAmbienteController {

    @Autowired
    ManagerDatosAmbiente managerDatosAmbiente;
    @Autowired
    ManagerAhorro managerAhorro;

    @PostMapping("/Datos/save")
    public void save(@RequestParam String tempAmbiente, @RequestParam String humTerreno,
                     @RequestParam String humAmbiente, @RequestParam String estadoRiego,
                     @RequestParam String tiempoRiego, @RequestParam String uuid) throws ExceptionController {
        SimpleDateFormat formatt= new SimpleDateFormat(Constants.FORMAT_DATE);
        Date date = new Date(System.currentTimeMillis());
        Date fecha = Date.valueOf(formatt.format(date));
        DatosAmbiente datosAmbiente =
                new DatosAmbiente(Integer.parseInt(tempAmbiente),Integer.parseInt(humTerreno),Integer.parseInt(humAmbiente),uuid);
        Ahorro ahorro = new Ahorro(0L, fecha, Long.parseLong(tiempoRiego), 0L, estadoRiego, uuid);
        try {
            managerDatosAmbiente.saveDatos(datosAmbiente);
            managerAhorro.saveAhorro(ahorro);
        } catch (ExceptionManager e) {
            throw new ExceptionController(e);
        }
    }

    @GetMapping(path = "/Datos/listAll")
    public @ResponseBody ResponseEntity<Respuesta> listAll(){
        Respuesta respuesta = new Respuesta();
        try {
            respuesta.setCodigo(200);
            respuesta.setData(managerDatosAmbiente.getAll());
        } catch (ExceptionManager e) {
            respuesta.setCodigo(500);
            respuesta.setMensaje(e.getMessage());
        } catch (ExceptionConexion | ExceptionDao e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping(path = "/Datos/delete/{id}")
    public void delete(@PathVariable String id) throws ExceptionController {
        try {
            managerDatosAmbiente.delete(id);
        } catch (ExceptionManager e) {
            throw new ExceptionController(e);
        }
    }

    @GetMapping(path = "/Datos/deleteAll")
    public void deleteAll() throws ExceptionController {
        try {
            managerDatosAmbiente.deleteAll();
        } catch (ExceptionManager e) {
            throw new ExceptionController(e);
        }
    }

    @GetMapping(path = "/Datos/getByid/{id}")
    public @ResponseBody ResponseEntity<Respuesta> getById(@PathVariable String id) throws ExceptionController {
        Respuesta respuesta = new Respuesta();
        try {
            respuesta.setCodigo(200);
            respuesta.setData(managerDatosAmbiente.getByIdDatos(id));
        } catch (ExceptionManager e) {
            respuesta.setCodigo(500);
            respuesta.setMensaje(e.getMessage());
            throw new ExceptionController(e);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping(path = "/Ahorro/getByUiid/{uuid}")
    public @ResponseBody ResponseEntity<Respuesta> getByUuid(@PathVariable String uuid)throws ExceptionController {
        Respuesta respuesta = new Respuesta();
        try {
            respuesta.setCodigo(200);
            respuesta.setData(managerAhorro.getByUuidAhorro(uuid));
        } catch (ExceptionManager e) {
            respuesta.setCodigo(500);
            respuesta.setMensaje(e.getMessage());
            throw new ExceptionController(e);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

}

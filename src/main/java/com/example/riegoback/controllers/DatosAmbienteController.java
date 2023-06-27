package com.example.riegoback.controllers;

import com.example.riegoback.Exceptions.ExceptionConexion;
import com.example.riegoback.Exceptions.ExceptionController;
import com.example.riegoback.Exceptions.ExceptionDao;
import com.example.riegoback.Exceptions.ExceptionManager;
import com.example.riegoback.config.Constants;
import com.example.riegoback.dto.Ahorro;
import com.example.riegoback.dto.DatosAmbiente;
import com.example.riegoback.dto.Respuesta;
import com.example.riegoback.dto.dataFechas;
import com.example.riegoback.managers.ManagerAhorro;
import com.example.riegoback.managers.ManagerDatosAmbiente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.Socket;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
        DateTimeFormatter formatt = DateTimeFormatter.ofPattern(Constants.FORMAT_DATE);
        String fecha = formatt.format(LocalDateTime.now());
        System.out.println(fecha);
        try {
            DatosAmbiente datosAmbiente =
                    new DatosAmbiente(Integer.parseInt(tempAmbiente),Integer.parseInt(humTerreno),Integer.parseInt(humAmbiente),uuid);
            managerDatosAmbiente.saveDatos(datosAmbiente);
            Integer idDatos = managerDatosAmbiente.getIdDatos(uuid);
            Ahorro ahorro =
                    new Ahorro(0L, fecha, Long.parseLong(tiempoRiego), 0F, estadoRiego, uuid, idDatos);
            managerAhorro.saveAhorro(ahorro);
        } catch (ExceptionManager e) {
            throw new ExceptionController(e);
        }
    }

    @GetMapping(path = "/Datos/listAll")
    public @ResponseBody ResponseEntity<Respuesta> listAll(){
        Respuesta respuesta = new Respuesta();
        try {
            LocalDate date = LocalDate.now();
            String year = String.valueOf(date.getYear());
            respuesta.setCodigo(200);
            ArrayList<Object> data = new ArrayList<>();
            data.add(managerDatosAmbiente.getAll());
            data.add(managerDatosAmbiente.getBySumaConsume(year+"-01-01", year+"-12-31"));
            respuesta.setData(data);
        } catch (ExceptionManager e) {
            respuesta.setCodigo(500);
            respuesta.setMensaje(e.getMessage());
        } catch (ExceptionConexion | ExceptionDao e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PostMapping(path = "/Datos/getByDates")
    public @ResponseBody ResponseEntity<Respuesta> getByDates(@RequestBody dataFechas dates) throws ExceptionManager {
        Respuesta respuesta = new Respuesta();
        respuesta.setCodigo(200);
        ArrayList<Object> data = new ArrayList<>();
        data.add(managerDatosAmbiente.getByDates(dates.getFechaInicio(), dates.getFechaFin()));
        data.add(managerDatosAmbiente.getBySumaConsume(dates.getFechaInicio(), dates.getFechaFin()));
        respuesta.setData(data);
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

    @PostMapping(path = "/Ahorro/getPin")
    public @ResponseBody ResponseEntity<Respuesta> getPin(@RequestParam String pin){
        Respuesta respuesta = new Respuesta();
        try {
            respuesta.setCodigo(200);
            getPinHttp(pin);
            respuesta.setData("se ha enviado correctamente");
        } catch (IOException e) {
            respuesta.setCodigo(400);
            respuesta.setData("no se ha completado la acción");
            System.out.println("error al enviar data a nodemcu: "+ e);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    public void getPinHttp(String mensaje) throws IOException {
        String host = "192.168.1.9";
        int port = 9090;
        try{
            Socket socket = new Socket(host, port);
            OutputStream outputStream = socket.getOutputStream();
            String data = mensaje;
            outputStream.write(data.getBytes());
            outputStream.close();
            socket.close();
            } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping(path = "/Ahorro/getHola")
    public @ResponseBody ResponseEntity<Respuesta> getHola(@RequestParam String hola) {
        Respuesta respuesta = new Respuesta();
            respuesta.setCodigo(200);
            respuesta.setData(hola);
            System.out.println(hola);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

}

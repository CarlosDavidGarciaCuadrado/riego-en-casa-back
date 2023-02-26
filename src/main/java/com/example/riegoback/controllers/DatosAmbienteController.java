package com.example.riegoback.controllers;

import com.example.riegoback.Exceptions.ExceptionController;
import com.example.riegoback.Exceptions.ExceptionManager;
import com.example.riegoback.dto.DatosAmbiente;
import com.example.riegoback.dto.Respuesta;
import com.example.riegoback.managers.ManagerDatosAmbiente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/IrrigationSystem")
@CrossOrigin(origins = "*")
public class DatosAmbienteController {

    @Autowired
    ManagerDatosAmbiente managerDatosAmbiente;

    @PostMapping("/Datos/save")
    public void save(@RequestParam String tempAmbiente, @RequestParam String humTerreno,
                     @RequestParam String humAmbiente, @RequestParam String phTerreno,
                     @RequestParam String estadoRiego, @RequestParam String tiempoRiego) throws ExceptionController {
        SimpleDateFormat formatt= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        Date fecha = Date.valueOf(formatt.format(date));
        DatosAmbiente datosAmbiente = new DatosAmbiente();
        datosAmbiente.setTempAmbiente(Integer.parseInt(tempAmbiente));
        datosAmbiente.setHumTerreno(Integer.parseInt(humTerreno));
        datosAmbiente.setHumAmbiente(Integer.parseInt(humAmbiente));
        datosAmbiente.setPhTerreno(Integer.parseInt(phTerreno));
        datosAmbiente.setFecha(fecha);
        datosAmbiente.setEstadoRiego(estadoRiego);
        datosAmbiente.setTiempoRiego(Long.parseLong(tiempoRiego));
        try {
            managerDatosAmbiente.save(datosAmbiente);
        } catch (ExceptionManager e) {
            throw new ExceptionController(e);
        }
    }

    @GetMapping(path = "/Datos/listAll")
    public @ResponseBody List<DatosAmbiente> listAll(){
        List<DatosAmbiente> datosAmbiente = new ArrayList<>();
        Respuesta respuesta = new Respuesta();
        try {
            datosAmbiente = managerDatosAmbiente.listAll();
            respuesta.setCodigo(0);
            respuesta.setData(datosAmbiente);
        } catch (ExceptionManager e) {
            respuesta.setCodigo(1);
            respuesta.setMensaje(e.getMessage());
        }
        return datosAmbiente;//return respuesta;
    }

    @GetMapping(path = "/Datos/delete/{id}")
    public void delete(@RequestParam int id) throws ExceptionController {
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
    public @ResponseBody DatosAmbiente getById(@RequestParam int id) throws ExceptionController {
        DatosAmbiente datosAmbiente = new DatosAmbiente();
        try {
            datosAmbiente = managerDatosAmbiente.getById(id);
        } catch (ExceptionManager e) {
            throw new ExceptionController(e);
        }
        return datosAmbiente;
    }

    @GetMapping("/hola")
    public String hola() {
        return "hola";
    }

}

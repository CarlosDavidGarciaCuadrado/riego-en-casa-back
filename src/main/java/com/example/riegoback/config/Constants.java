package com.example.riegoback.config;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Constants {
    public static final String ESTADO_FINAL = "Final";
    public static final String FORMAT_DATE = "yyyy-MM-dd HH:mm:ss";
    public static final Float FLUJO_DE_AGUA = 6.250F;
    public static final Integer HORA_DE_MILI = 3600000;
    public static final Integer MINUTO_DE_MILI = 60000;
    public static final Float MINUTOS_EN_HORA = 60F;

    public static Date getDateFormat(String formatPattern, String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatPattern);
        return (Date) formatter.parse(date);
    }
}

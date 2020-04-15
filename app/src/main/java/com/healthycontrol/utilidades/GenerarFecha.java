package com.healthycontrol.utilidades;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerarFecha {

    public static String ejecutar(){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy h:mma");
        return format.format(new Date());
    }
}

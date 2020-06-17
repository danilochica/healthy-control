package com.healtycontrol.entidades;

import java.util.Date;

public class PresionArterial {

    private String valorPresion;
    private String resultado;
    private Date fechaRegistro;

    public PresionArterial(){}

    public PresionArterial(String valorPresion, String resultado, Date fechaRegistro) {
        this.valorPresion = valorPresion;
        this.resultado = resultado;
        this.fechaRegistro = fechaRegistro;
    }

    public String getValorPresion() {
        return valorPresion;
    }

    public void setValorPresion(String valorPresion) {
        this.valorPresion = valorPresion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}

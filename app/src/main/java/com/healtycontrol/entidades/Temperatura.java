package com.healtycontrol.entidades;

import java.util.Date;

public class Temperatura {

    private Date fechaRegistro;
    private String resultado;
    private Double valorTemperatura;

    public Temperatura(){}

    public Temperatura(Date fechaRegistro, String resultado, Double valorTemperatura) {
        this.fechaRegistro = fechaRegistro;
        this.resultado = resultado;
        this.valorTemperatura = valorTemperatura;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Double getValorTemperatura() {
        return valorTemperatura;
    }

    public void setValorTemperatura(Double valorTemperatura) {
        this.valorTemperatura = valorTemperatura;
    }
}

package com.healtycontrol.entidades;

import com.google.firebase.Timestamp;

import java.util.Date;


public class Glucometria {

    private Date fechaRegistro;
    private String horario;
    private String resultado;
    private Double valorGlucometria;

    public Glucometria(){}

    public Glucometria(Date fechaRegistro, String horario, String resultado, Double valorGlucometria) {
        this.fechaRegistro = fechaRegistro;
        this.horario = horario;
        this.resultado = resultado;
        this.valorGlucometria = valorGlucometria;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Double getValorGlucometria() {
        return valorGlucometria;
    }

    public void setValorGlucometria(Double valorGlucometria) {
        this.valorGlucometria = valorGlucometria;
    }
}



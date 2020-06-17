package com.healtycontrol.entidades;

import java.util.Date;

public class Peso {

    private Double peso;
    private Date fechaRegistro;
    private String usuario;

    public Peso(){}

    public Peso(Double peso, Date fechaRegistro, String usuario) {
        this.peso = peso;
        this.fechaRegistro = fechaRegistro;
        this.usuario = usuario;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}

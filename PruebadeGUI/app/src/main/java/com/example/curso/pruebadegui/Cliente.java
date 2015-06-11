package com.example.curso.pruebadegui;

import java.util.ArrayList;

/**
 * Created by curso on 09/06/2015.
 */
public class Cliente {

    public int horaInicio;
    public int horaFinal;
    public String nombre;

    public Cliente(int horaInicio, int horaFinal, String nombre) {
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.nombre = nombre;


    }


    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(int horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}


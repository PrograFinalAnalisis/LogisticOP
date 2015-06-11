package com.example.curso.pruebadegui;

/**
 * Created by curso on 09/06/2015.
 */
public class Paquete {
    public int id;
    public int largo;
    public int ancho;
    public int alto;
    public int volumen; //volumen del camion
    public Cliente cliente;

    public Paquete(int id, int largo, int ancho, int alto) {
        this.id = id;
        this.largo = largo;
        this.ancho = ancho;
        this.alto = alto;
        this.volumen = largo*ancho*alto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}


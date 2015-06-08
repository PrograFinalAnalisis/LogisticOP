import java.util.ArrayList;

/**
 * Created by nelson on 26/05/15.
 */
public class Paquete {
    public int volumen; //volumen del camion

    public Paquete(int largo, int ancho, int alto) {
        this.largo = largo;
        this.ancho = ancho;
        this.alto = alto;
        this.volumen = largo*ancho*alto;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int largo;
    public int ancho;
    public int alto;
    public Cliente cliente;



}


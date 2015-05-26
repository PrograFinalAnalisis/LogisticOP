import java.util.ArrayList;

/**
 * Created by nelson on 26/05/15.
 */
public class Paquete {
    public int volumen; //volumen del camion
    public int largo;
    public int ancho;
    public ArrayList<Articulo> paquete;
    public String nombreCliente;


    public Paquete(int largo, int ancho, ArrayList<Articulo> paquete, String nombreCliente) {
        this.largo = largo;
        this.ancho = ancho;
        this.paquete = paquete;
        this.nombreCliente = nombreCliente;
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

    public ArrayList<Articulo> getPaquete() {
        return paquete;
    }

    public void setPaquete(ArrayList<Articulo> paquete) {
        this.paquete = paquete;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }
}

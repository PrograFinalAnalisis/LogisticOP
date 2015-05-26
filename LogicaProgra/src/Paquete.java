import java.util.ArrayList;

/**
 * Created by nelson on 26/05/15.
 */
public class Paquete {
    public int volumen; //volumen del camion
    public int largo;
    public int ancho;
    public ArrayList<Articulo> paquete;
    public String nombreClinete;


    public Paquete(int largo, int ancho, ArrayList<Articulo> paquete, String nombreClinete) {
        this.largo = largo;
        this.ancho = ancho;
        this.paquete = paquete;
        this.nombreClinete = nombreClinete;
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

    public String getNombreClinete() {
        return nombreClinete;
    }

    public void setNombreClinete(String nombreClinete) {
        this.nombreClinete = nombreClinete;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }



}

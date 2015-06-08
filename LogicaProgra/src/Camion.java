import java.util.ArrayList;

/**
 * Created by nelson on 26/05/15.
 */
public class Camion {
    public int tamaño; //volumen del camion
    public int alto;
    public int largo;
    public int ancho;
    public ArrayList<Paquete> envio;

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
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

    public ArrayList<Paquete> getEnvio() {
        return envio;
    }

    public void setEnvio(ArrayList<Paquete> envio) {
        this.envio = envio;
    }
}

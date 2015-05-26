/**
 * Created by nelson on 26/05/15.
 */
public class Camion {
    public int tamaño; //volumen del camion
    public int largo;
    public int ancho;

    public Camion(int tamaño, int largo, int ancho) {
        this.tamaño = tamaño;
        this.largo = largo;
        this.ancho = ancho;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
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


}

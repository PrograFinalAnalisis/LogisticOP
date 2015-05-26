/**
 * Created by nelson on 26/05/15.
 */
public class Articulo {
    public int volumen;
    public int largo;
    public int ancho;
    public int codigo;
    public int nombre;

    public Articulo(int volumen, int largo, int ancho, int codigo, int nombre) {
        this.volumen = volumen;
        this.largo = largo;
        this.ancho = ancho;
        this.codigo = codigo;
        this.nombre = nombre;
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

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }
}

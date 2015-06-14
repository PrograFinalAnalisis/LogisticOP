package clases;

import java.io.Serializable;

/**
 * Created by Programador on 06/04/2015.
 */
public class ListEmpleados implements Serializable {


    private String Nombre;
    private String id;

    public ListEmpleados(String nombre, String id) {
        Nombre = nombre;
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  Nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}

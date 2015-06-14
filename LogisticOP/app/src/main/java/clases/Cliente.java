package clases;

import java.io.Serializable;

/**
 * Created by Programador on 05/03/2015.
 */
public class Cliente  implements Serializable {

    private int id_clientes;
    private String name;
    private String NombreQB;

    public Cliente(int id_clientes, String name, String nombreQB) {
        super();
        this.id_clientes = id_clientes;
        this.name = name;
        NombreQB = nombreQB;
    }

    public String getNombreQB() {
        return NombreQB;
    }

    public void setNombreQB(String nombreQB) {
        NombreQB = nombreQB;
    }

    @Override
    public String toString() {
        return name;
    }
    public int getId_clientes() {
        return id_clientes;
    }
    public void setId_clientes(int id_clientes) {
        this.id_clientes = id_clientes;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}

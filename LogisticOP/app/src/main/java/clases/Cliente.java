package clases;

import java.io.Serializable;

/**
 * Created by Programador on 05/03/2015.
 */
public class Cliente  implements Serializable
{

    public int horaInicioEntrega;
    public int horaFinEntrega;
    public int idCliente;
    public String nombreCliente;

    public Cliente(String pNombre,int pHoraInicioEntrega, int pHoraFinEntrega, int pIdCliente)
    {
        this.nombreCliente = pNombre;
        this.horaInicioEntrega = pHoraInicioEntrega;
        this.horaFinEntrega = pHoraFinEntrega;
        this.idCliente = pIdCliente;
    }

    @Override
    public String toString() {
        return nombreCliente;
    }


}

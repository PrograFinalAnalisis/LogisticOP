/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalanalisis;

/**
 *
 * @author Soto
 */
public class cliente 
{
    public int horaInicioEntrega;
    public int horaFinEntrega;
    public int idCliente;
    
    public cliente(int pHoraInicioEntrega, int pHoraFinEntrega, int pIdCliente)
    {
        this.horaInicioEntrega = pHoraInicioEntrega;
        this.horaFinEntrega = pHoraFinEntrega;
        this.idCliente = pIdCliente;
    }
    
}

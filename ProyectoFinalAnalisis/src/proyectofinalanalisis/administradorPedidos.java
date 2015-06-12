/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalanalisis;

import java.util.ArrayList;

/**
 *
 * @author Soto
 */
public class administradorPedidos 
{
    
    public administradorPedidos()
    {
        
    }
    
    
    
    
    //////Este es el metodo greedy que va a repartir las cajas en los camiones
    /////pListaCajas es el conjunto de Seleccion
    public ArrayList<camion> crearPedidos(ArrayList<caja> pListaCajas, int[] pListaIdClientes)
    {
//        System.out.println(pListaCajas.get(399).volumen);
        ArrayList<camion> miListaCamiones = new ArrayList();/// Este sera nuestro conjunto solucion
        miListaCamiones.add(new camion(400,500,500));
        int indiceCamion = 0;
        
        
        ///Funcion de seleccion, ira metiendo cajas al camion , por cliente
        for(int i = 0 ;i < pListaIdClientes.length;i++)
        {
            for(int x = 0;x<pListaCajas.size();x++)
            {
                ///Verifica que sea de un cliente en especifico
                if(pListaCajas.get(x).idCliente == pListaIdClientes[i])
                {
                    if(miListaCamiones.get(indiceCamion).getVolumenRestante() >= pListaCajas.get(x).volumen)
                    {
                        miListaCamiones.get(indiceCamion).meterCaja(pListaCajas.get(x));
                        pListaCajas.get(x).seleccionada = true; // nos indica que la caja se selecciono
                    }
                    else
                    {
                        miListaCamiones.add(new camion(400,500,500));
                        indiceCamion++;
                        miListaCamiones.get(indiceCamion).meterCaja(pListaCajas.get(x));
                        pListaCajas.get(x).seleccionada = true; // nos indica que la caja se selecciono
                    }    
                }
            }
        }
        
        if(verificarCajasEnCamiones(pListaCajas))
        {
            ///Esta iteracion es para reviar los datos luego se quita 
            for(int i = 0;i<miListaCamiones.size();i++)
            {
                System.out.println("Camion numero " + i);
                miListaCamiones.get(i).imprimirClientesAVisitar();
                System.out.println("//////////////////////////////////////////////////////////////////////////");
            }
            return miListaCamiones;
        }
        
        else
        {
            System.out.println("No se llego a la solucion");
            return crearPedidos(pListaCajas, pListaIdClientes);
        }
        
        
        
        
    }
    
    
    
    //// Funcion objetivo, define si se llego a una solucion
    
    public boolean verificarCajasEnCamiones(ArrayList<caja> pListaCajas)
    {
        boolean esSolucion = true;
        for(int i = 0;i < pListaCajas.size();i++)
        {
            if(!pListaCajas.get(i).seleccionada)
            {
                esSolucion = false;
            }
            if(!pListaCajas.get(i).guardada)
            {
                System.out.println("Caja no entro en camion por sus dimensiones");
            }
        }
        return esSolucion;
    }
    
    
}

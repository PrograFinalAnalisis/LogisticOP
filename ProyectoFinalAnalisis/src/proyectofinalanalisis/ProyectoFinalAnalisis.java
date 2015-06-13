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
public class ProyectoFinalAnalisis 
{

    /**
     * @param args the command line arguments
     */
    
//    Nota: las medidas de camion y cajas estan dadas en cm
    public static void main(String[] args) 
    {
        administradorPedidos miAdministradorPedidos = new administradorPedidos();
        
        cliente [] listaClientes = {new cliente(8,11,1),
                                    new cliente(5,15,2),
                                    new cliente(15,18,3),
                                    new cliente(12,15,4)};///Esto se obtiene de la base de datos, 
                                                          ///Es una lista con  los id de los clientes.
        
        
        
        cliente [] listaClientesEnOrdenVisita = miAdministradorPedidos.rutaASeguir(listaClientes);
        
        ArrayList<caja> listaCajas = new ArrayList(); // esto es una lista de cajas X solo para pruebas
        
        
        // Van a ser 100 cajas por cliente lo que implica, 100 000 000 cm cubicos por cliente
        for(int i = 0;i<400; i++)
        {
            if(i >= 0 && i < 100)
            {
            listaCajas.add(new caja(100, 100, 100,listaClientes[0]));// puse todas las cajas iguales para poder hacer 
                                                    // calculos en papel y ver si se aproxima, cliente 1
            }
            if(i >= 100 && i < 200)
            {
            listaCajas.add(new caja(100, 100, 100,listaClientes[1]));// puse todas las cajas iguales para poder hacer 
                                                    // calculos en papel y ver si se aproxima, cliente 2
            }
            if(i >= 200 && i < 300)
            {
            listaCajas.add(new caja(100, 100, 100,listaClientes[2]));// puse todas las cajas iguales para poder hacer 
                                                    // calculos en papel y ver si se aproxima, cliente 3
            }
            if(i >= 300 && i < 400)
            {
            listaCajas.add(new caja(100, 100, 100,listaClientes[3]));// puse todas las cajas iguales para poder hacer 
                                                    // calculos en papel y ver si se aproxima, cliente 4
            }
            //400 * 500 * 500
        }
        
//        for(int j = 0;j<listaIdClientesOrdenVisita.length;j++)
//        {
//            System.out.println(listaIdClientesOrdenVisita[j].horaInicioEntrega);
//        }
        
        miAdministradorPedidos.crearPedidos(listaCajas, listaClientesEnOrdenVisita);/// eviamos al algoritmo geedy los datos
    }
    
}

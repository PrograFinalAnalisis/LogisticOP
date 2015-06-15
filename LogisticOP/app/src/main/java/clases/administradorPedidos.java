package clases;


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
    public ArrayList<camion> crearPedidos(ArrayList<caja> pListaCajas, Cliente [] pListaIdClientes)
    {
//        System.out.println(pListaCajas.get(399).volumen);
        ArrayList<camion> miListaCamiones = new ArrayList();/// Este sera nuestro conjunto solucion

        int indiceCamion = 0;

        miListaCamiones.add(new camion(400,500,500,indiceCamion + 1));
        ///Funcion de seleccion, ira metiendo cajas al camion , por Cliente
        for(int i = 0 ;i < pListaIdClientes.length;i++)
        {
            for(int x = 0;x<pListaCajas.size();x++)
            {
                ///Verifica que sea de un Cliente en especifico
                if(pListaCajas.get(x).cliente.idCliente == pListaIdClientes[i].idCliente)
                {
                    if(miListaCamiones.get(indiceCamion).getVolumenRestante() >= pListaCajas.get(x).volumen)
                    {
                        miListaCamiones.get(indiceCamion).meterCaja(pListaCajas.get(x));
                        pListaCajas.get(x).seleccionada = true; // nos indica que la caja se selecciono
                    }
                    else
                    {
                        indiceCamion++;
                        miListaCamiones.add(new camion(400,500,500,indiceCamion + 1));
                        miListaCamiones.get(indiceCamion).meterCaja(pListaCajas.get(x));
                        pListaCajas.get(x).seleccionada = true; // nos indica que la caja se selecciono
                    }
                }
            }
        }

        if(verificarCajasEnCamiones(pListaCajas))
        {
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



    /////////////Algoritmo divide y conquista que me ayuda a obtener el orden de visitas/////////

    public Cliente[] rutaASeguir(Cliente[] pListaClientes)
    {
        Cliente[] ordenVisita = new Cliente[pListaClientes.length];
        if(pListaClientes.length <= 1)
        {
            ordenVisita[0]= pListaClientes[0];
            return ordenVisita;
        }
        else
        {
            int mitad = pListaClientes.length / 2;
            Cliente[] pListaClientes1 = new Cliente[mitad];
            Cliente[] pListaClientes2 = new Cliente[pListaClientes.length-mitad];
            for (int i = 0;i< pListaClientes.length;i++)
            {
                if(i < mitad)
                {
                    pListaClientes1[i] = pListaClientes[i];
                }
                else
                {
                    pListaClientes2[i-mitad] = pListaClientes[i];
                }
            }
            return rutaASeguirMezcla(rutaASeguir(pListaClientes1), rutaASeguir(pListaClientes2));
        }
    }


    public Cliente[] rutaASeguirMezcla(Cliente[] pListaClientes1,Cliente[] pListaClientes2)
    {
        Cliente[] respuesta = new Cliente[pListaClientes1.length+pListaClientes2.length];
        int i = 0;
        int j = 0;
//        System.out.println(pListaClientes1[1].horaInicioEntrega);


        for(int k = 0;k<respuesta.length;k++)
        {

            if(verificarIndices(pListaClientes1,pListaClientes2,i,j))
            {
                respuesta[k] = pListaClientes1[i];
//                System.out.println(respuesta[k].Cliente);
//                if(i < pListaClientes1.length -1)
//                {
                i++;
//                }
            }
            else
            {
                respuesta[k] = pListaClientes2[j];
//                System.out.println(respuesta[k]);
//                if(j < pListaClientes2.length -1)
//                {
                j++;
//                }
            }

        }
        return respuesta;
    }

    private boolean verificarIndices(Cliente[] pClientes1, Cliente[] pClientes2,int indice1, int indice2)
    {
        if(indice1 >= pClientes1.length)
        {
            return false;
        }
        else if(indice2 >= pClientes2.length)
        {
            return true;
        }
        else if(pClientes1[indice1].horaInicioEntrega < pClientes2[indice2].horaInicioEntrega)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


}

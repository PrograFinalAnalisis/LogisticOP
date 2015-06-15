Proyecto Final de Análisis de Algoritmos
=================================

##Manual de Usuario
----------
Al inicio el usuario Entrara con su Nombre de perfil y contraseña como se muestra en la imagen adjunta.

![enter image description here](https://lh3.googleusercontent.com/-42GhkbsdP4k/VX4TL_afngI/AAAAAAAAATU/RIpcIYNd-vM/s0/20150614164336.jpg "Inicio.jpg")

![enter image description here](https://lh3.googleusercontent.com/-5cRy0szZ32A/VX4U--LzN-I/AAAAAAAAATw/zbcjM0lUcEc/s0/20150614164336%25283%2529.jpg "conNombre.jpg")

----------
Luego accederemos al menú principal 
![enter image description here](https://lh3.googleusercontent.com/-V2vxUKVt5QM/VX4UyZsAWGI/AAAAAAAAATk/0kZUr5cD2tI/s0/20150614164337.jpg "menu.jpg")


----------
Tocando donde dice **Ingresar Cliente** podremos registrar un nuevo cliente
![enter image description here](https://lh3.googleusercontent.com/-8i0vMxv66eU/VX4fHfNChQI/AAAAAAAAAUc/2Z7bNmXssiU/s0/20150614164336%25285%2529.jpg "IngresarCliente.jpg")

----------
Tocando donde dice **Ingresar Paquete** podremos registrar un nuevo Paquete
![enter image description here](https://lh3.googleusercontent.com/-mo-HRp6gUyM/VX4ferwxqmI/AAAAAAAAAUo/3VnkYS_DnLE/s0/20150614164336%25282%2529.jpg "IngresarProducto.jpg")

----------
Tocando donde dice **Crear Pedido** podremos crear los pedidos para nuestros clientes, escogiendo el nombre del cliente y escaneando el código de barras del paquete
![enter image description here](https://lh3.googleusercontent.com/--q4Yy92cuPw/VX4ehaa0_8I/AAAAAAAAAUQ/EvKmGEOWSgE/s0/20150614164336%25284%2529.jpg "CrearPedido.jpg")

----------
y finalmente en **Calcular logística** es donde podremos ver hacia donde se fueron los paquetes y a que hora


----------

##Lógica del Programa
----------
Para este proyecto se uso **Android 4.0 "Ice Cream Sandwich"**
y se decidió usar algoritmos **Greedy** y **Divide y Conquista**

###Algoritmo Divide y Conquista para la selección de rutas
Se utilizó **Divide y conquista** para asignar las rutas a los camiones

    public cliente[] rutaASeguir(cliente[] pListaClientes)
    {
        cliente[] ordenVisita = new cliente[pListaClientes.length];
        if(pListaClientes.length <= 1)
        {
            ordenVisita[0]= pListaClientes[0];
            return ordenVisita;
        }
        else
        {
            int mitad = pListaClientes.length / 2;
            cliente[] pListaClientes1 = new cliente[mitad];
            cliente[] pListaClientes2 = new cliente[pListaClientes.length-mitad];
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
    
    
    public cliente[] rutaASeguirMezcla(cliente[] pListaClientes1,cliente[] pListaClientes2)
    {
        cliente[] respuesta = new cliente[pListaClientes1.length+pListaClientes2.length];
        int i = 0;
        int j = 0;


        
        for(int k = 0;k<respuesta.length;k++)
        {
            
            if(verificarIndices(pListaClientes1,pListaClientes2,i,j))
            {
                respuesta[k] = pListaClientes1[i];

            }
            else
            {
                respuesta[k] = pListaClientes2[j];

            }
            
        }
        return respuesta;
    }
    
    private boolean verificarIndices(cliente[] pClientes1, cliente[] pClientes2,int indice1, int indice2)
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

----------
###Algoritmo Greedy para guardar los paquetes
Se utilizó **Greedy** para acomodar las cajas como lo veremos a continuación:

        public ArrayList<camion> crearPedidos(ArrayList<caja> pListaCajas, cliente [] pListaIdClientes)
    {
        ArrayList<camion> miListaCamiones = new ArrayList();/// Este sera nuestro conjunto solucion
        
        int indiceCamion = 0;
        
        miListaCamiones.add(new camion(400,500,500,indiceCamion + 1));
        ///Funcion de seleccion, ira metiendo cajas al camion , por cliente
        for(int i = 0 ;i < pListaIdClientes.length;i++)
        {
            for(int x = 0;x<pListaCajas.size();x++)
            {
                ///Verifica que sea de un cliente en especifico
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
            ///Esta iteracion es para reviar los datos luego se quita 
            for(int i = 0;i<miListaCamiones.size();i++)
            {
                System.out.println("Camion numero " + miListaCamiones.get(i).idCamion);
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


 


package com.logisticop.logisticop.logisticop;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

import clases.*;

import static android.database.sqlite.SQLiteDatabase.openDatabase;


public class Logistica extends ActionBarActivity
{
    private static String DB_NAME = "DB_LOGISTICOP.sqlite"; /// nombre de la base de datos
    private static String DB_PATH = "/data/data/com.logisticop.logisticop.logisticop/databases/";// ubicación en la raiz de android de donde se encuentra la base de datos
    int cant;
    SQLiteDatabase db;//objeto de base sqlite


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_datos);

        String cabeceras[] = { "Camion #", "Nombre Cliente", "Hora Salida" };



        // Cabecera de la tabla
        TableRow cabecera = new TableRow(this);
        cabecera.setLayoutParams(new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ((TableLayout) findViewById(R.id.TablaResultado)).addView(cabecera);

        // Textos de la cabecera
        for (int i = 0; i < cabeceras.length; i++)
        {
            TextView columna = new TextView(this);
            columna.setLayoutParams(new TableRow.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            columna.setText(cabeceras[i]);
            columna.setTextColor(Color.parseColor("#005500"));
            columna.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            columna.setGravity(Gravity.CENTER_HORIZONTAL);
            columna.setPadding(5, 5, 5, 5);
            cabecera.addView(columna);
        }

        // Línea que separa la cabecera de los datos
        TableRow separador_cabecera = new TableRow(this);
        separador_cabecera.setLayoutParams(new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        FrameLayout linea_cabecera = new FrameLayout(this);
        TableRow.LayoutParams linea_cabecera_params =
                new TableRow.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 2);
        linea_cabecera_params.span = 6;
        linea_cabecera.setBackgroundColor(Color.parseColor("#FFFFFF"));
        separador_cabecera.addView(linea_cabecera, linea_cabecera_params);
        ((TableLayout) findViewById(R.id.TablaResultado)).addView(separador_cabecera);



        cargarLogistica();

        eliminarPedidos();


    }



    public void cargarLogistica()
    {
        administradorPedidos miAdministradorPedidos = new administradorPedidos();
        Cliente [] listaClientes = cargarClientes();

        Cliente [] listaClientesEnOrdenVisita = miAdministradorPedidos.rutaASeguir(listaClientes);

        ArrayList<caja> listaCajas = cargarCajas(listaClientes);

        ArrayList<camion> miListaCamiones = miAdministradorPedidos.crearPedidos(listaCajas, listaClientesEnOrdenVisita);/// eviamos al algoritmo geedy los datos


        ///Esta iteracion es para reviar los datos luego se quita
        for(int i = 0;i<miListaCamiones.size();i++)
        {
        //    System.out.println("Camion numero " + miListaCamiones.get(i).idCamion);
            for(int j = 0;j < miListaCamiones.get(i).listaClientesAVisitar.size();j++)
            {
                insertarFila(((TableLayout) findViewById(R.id.TablaResultado)), "Camion "+(i+1),
                             miListaCamiones.get(i).listaClientesAVisitar.get(j).nombreCliente,
                            Integer.toString(miListaCamiones.get(i).listaClientesAVisitar.get(j).horaInicioEntrega));
            }

        }

    }

    public void insertarFila(TableLayout miTabla,String pCamion, String pCliente, String pHora)
    {

        TextView Camion;
        TextView Cliente;
        TextView Hora;

        TableRow fila = new TableRow(this);

        Camion = new TextView(this);

        Camion.setText(pCamion);
        Camion.setGravity(Gravity.CENTER_HORIZONTAL);
        fila.addView(Camion);


        Cliente = new TextView(this);

        Cliente.setText(pCliente);
        Cliente.setGravity(Gravity.CENTER_HORIZONTAL);
        fila.addView(Cliente);

        Hora = new TextView(this);
        Hora.setText(pHora);
        Hora.setGravity(Gravity.CENTER_HORIZONTAL);
        fila.addView(Hora);

        miTabla.addView(fila);


    }

    public Cliente[] cargarClientes()
    {

        Cliente[] misClientes = null;
        try {

            db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            Cursor filas = db.rawQuery("select * from  clientes", null);
            //habro la conexion la base
            //ejecuto un select
            LinkedList<Cliente> spcliente = new LinkedList<Cliente>();
            //creo un objecto de la clase de Cliente
            while (filas.moveToNext())
            {
                spcliente.add(new Cliente(filas.getString(1),filas.getInt(2), filas.getInt(3), filas.getInt(0)));
            }

            misClientes = new Cliente[spcliente.size()];
            for(int i =0;i<misClientes.length;i++)
            {
                misClientes[i] = spcliente.get(i);
            }


            db.close();//cierro conexion

        } catch (Exception e)
        {

            Toast.makeText(this, "Error de conexión, revise la configuración o verifique que el servidor esté encendido", Toast.LENGTH_LONG).show();
            //  Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


        return misClientes;
    }//FIN cargarClientes


    public ArrayList<caja> cargarCajas(Cliente[] pListaClientes)
    {

        ArrayList<caja>  misCajas = null;
        try {

            db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            Cursor filas = db.rawQuery("select Base,Altura,Profundidad,id_Cliente,cantidad from  caja,pedidos where caja._id = pedidos.id_Caja", null);
            //habro la conexion la base
            //ejecuto un select
            misCajas = new ArrayList<caja>();
            //creo un objecto de la clase de Cliente
            while (filas.moveToNext())
            {


                for(int i= 0;i<pListaClientes.length;i++)
                {
                    if(filas.getInt(3) == pListaClientes[i].idCliente)
                    {
                        for(int j = 0;j<filas.getInt(4);j++)
                        {
                            misCajas.add(new caja(filas.getInt(2),filas.getInt(1),filas.getInt(0),pListaClientes[i]));
                        }
                    }
                }
            }
            db.close();//cierro conexion

        } catch (Exception e)
        {

            Toast.makeText(this, "Error de conexión, revise la configuración o verifique que el servidor esté encendido", Toast.LENGTH_LONG).show();
            //  Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


        return misCajas;
    }//FIN cargarClientes


    public void eliminarPedidos()
    {
        try {

            db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            db.execSQL("Delete from pedidos");
            db.close();//cierro conexion

        } catch (Exception e)
        {
            Toast.makeText(this, "Error de conexión, revise la configuración o verifique que el servidor esté encendido", Toast.LENGTH_LONG).show();
            //  Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }//FIN











}

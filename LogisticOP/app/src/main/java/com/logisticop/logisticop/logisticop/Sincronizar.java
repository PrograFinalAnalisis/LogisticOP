package com.logisticop.logisticop.logisticop;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.view.ViewGroup.*;
import android.graphics.*;
import android.util.*;
import android.view.Gravity;
import android.widget.FrameLayout;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.sql.Connection;
import java.util.LinkedList;


import clases.Cliente;

import static android.database.sqlite.SQLiteDatabase.openDatabase;

/**
 * Created by Programador on 17/04/2015.
 */
public class Sincronizar extends ActionBarActivity {
    private static String DB_PATH = "/data/data/com.logisticop.logisticop.logisticop/databases/";
    static Connection conexionMySQL;



    public Spinner Sp_clientes;


    boolean Insertado;

    private static String DB_NAME = "DB_LOGISTICOP.sqlite";

    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sincronizar);

        Sp_clientes = (Spinner) findViewById(R.id.Sp_clientes);


        cargarClientes();

        // Datos para la tabla
        String cabeceras[] = { "Codigo", "Producto", "Cantidad" };



        // Cabecera de la tabla
        TableRow cabecera = new TableRow(this);
        cabecera.setLayoutParams(new TableLayout.LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        ((TableLayout) findViewById(R.id.Tabla)).addView(cabecera);

        // Textos de la cabecera
        for (int i = 0; i < cabeceras.length; i++)
        {
            TextView columna = new TextView(this);
            columna.setLayoutParams(new TableRow.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            columna.setText(cabeceras[i]);
            columna.setTextColor(Color.parseColor("#005500"));
            columna.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
            columna.setGravity(Gravity.CENTER_HORIZONTAL);
            columna.setPadding(5, 5, 5, 5);
            cabecera.addView(columna);
        }

        // Línea que separa la cabecera de los datos
        TableRow separador_cabecera = new TableRow(this);
        separador_cabecera.setLayoutParams(new TableLayout.LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        FrameLayout linea_cabecera = new FrameLayout(this);
        TableRow.LayoutParams linea_cabecera_params =
                new TableRow.LayoutParams(LayoutParams.FILL_PARENT, 2);
        linea_cabecera_params.span = 6;
        linea_cabecera.setBackgroundColor(Color.parseColor("#FFFFFF"));
        separador_cabecera.addView(linea_cabecera, linea_cabecera_params);
        ((TableLayout) findViewById(R.id.Tabla)).addView(separador_cabecera);


    }



    public void insertarFila(TableLayout miTabla,String pIdProducto, String pNombreProducto)
    {

        TextView Codigo;
        TextView Nombre;
        EditText cantidad;

        TableRow fila = new TableRow(this);

        Codigo = new TextView(this);

        Codigo.setText(pIdProducto);
        Codigo.setGravity(Gravity.CENTER_HORIZONTAL);
        fila.addView(Codigo);


        Nombre = new TextView(this);

        Nombre.setText(pNombreProducto);
        Nombre.setGravity(Gravity.CENTER_HORIZONTAL);
        fila.addView(Nombre);

        cantidad = new EditText(this);
        cantidad.setText("1");
        cantidad.setGravity(Gravity.CENTER_HORIZONTAL);
        fila.addView(cantidad);

        miTabla.addView(fila);


        }



    public void cargarClientes()
    {

        try {

            db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            Cursor filas = db.rawQuery("select * from  clientes", null);
            //habro la conexion la base
            //ejecuto un select
            Spinner Sp_clientes3 = (Spinner) findViewById(R.id.Sp_clientes);
            LinkedList<Cliente> spcliente = new LinkedList<Cliente>();
            //creo un objecto de la clase de Cliente
            while (filas.moveToNext())
            {



                spcliente.add(new Cliente(filas.getString(1),filas.getInt(2), filas.getInt(3), filas.getInt(0)));
                //agrego los valores obtenidos del cursor a la clase departamento
                //Creamos el adaptador
            }
            ArrayAdapter<Cliente> spinner_adapter = new ArrayAdapter<Cliente>(this, android.R.layout.simple_spinner_item, spcliente);
            //Añadimos el layout para el menú y se lo damos al spinner
            spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Sp_clientes3.setAdapter(spinner_adapter);
            db.close();//cierro conexion

        } catch (Exception e) {

            Toast.makeText(this, "Error de conexión, revise la configuración o verifique que el servidor esté encendido", Toast.LENGTH_LONG).show();
            //  Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }//FIN cargarClientes





    public void BtnTodasActividades(View v)
    {
        //Se instancia un objeto de la clase IntentIntegrator
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        //Se procede con el proceso de scaneo
        scanIntegrator.initiateScan();

    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        //Se obtiene el resultado del proceso de scaneo y se parsea
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            //Quiere decir que se obtuvo resultado pro lo tanto:
            //Desplegamos en pantalla el contenido del c�digo de barra scaneado


            String scanContent = scanningResult.getContents();

            //Desplegamos en pantalla el nombre del formato del c�digo de barra scaneado
            String scanFormat = scanningResult.getFormatName();


            Mostrar(Integer.parseInt(scanContent));
        }
        else
        {
            //Quiere decir que NO se obtuvo resultado
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No se ha recibido datos del scaneo!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    public void Mostrar(int pCodigo)
    {
        try {

            db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            Cursor filas = db.rawQuery("select _id,Nombre_Producto from  caja where _id = "+pCodigo, null);
            //habro la conexion la base
            //ejecuto un select
            //creo un objecto de la clase de Cliente
            while (filas.moveToNext())
            {
                //Insertamos En el grid
                insertarFila((TableLayout) findViewById(R.id.Tabla), filas.getString(0), filas.getString(1));
            }
            db.close();//cierro conexion

        } catch (Exception e)
        {

            Toast.makeText(this, "Error de conexión, revise la configuración o verifique que el servidor esté encendido", Toast.LENGTH_LONG).show();
            //  Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }





    boolean Conexion(TableLayout miTabla,Spinner idCliente)
    {
        for(int i=2;i<miTabla.getChildCount();i++)
        {
            TableRow row = (TableRow) miTabla.getChildAt(i);
            Insertar_Pedido_Sqlite(((Cliente) idCliente.getSelectedItem()).idCliente,
                                    Integer.parseInt(((TextView) row.getChildAt(0)).getText().toString()),
                                    Integer.parseInt(((EditText) row.getChildAt(2)).getText().toString()));
        }

        return Insertado;
    }

    public void BtnEnviar(View v)
    {
        if(Conexion((TableLayout) findViewById(R.id.Tabla), (Spinner) findViewById(R.id.Sp_clientes)))
        {
            Toast.makeText(this, "Pedido insertado con exito.", Toast.LENGTH_LONG).show();
        }

    }


    //metodo de insertar
    public void Insertar_Pedido_Sqlite(int idCliente, int idCaja, int cantidad)
    {
        try {
            db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            //habro conexion y ejecuto el INSERT
            db.execSQL("INSERT INTO pedidos(id_Cliente,id_Caja,cantidad,fecha)"
                    + " VALUES("+idCliente+"," + idCaja + "," + cantidad + ",date('now'))");
            Insertado = true;

            db.close();//cierro conexion
            db.close();
        } catch (Exception e)
        {
            Insertado = false;
            Toast.makeText(getApplicationContext(),
                    "Error ", Toast.LENGTH_LONG);
        }
    }





}



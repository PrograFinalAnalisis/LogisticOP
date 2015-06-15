package com.logisticop.logisticop.logisticop;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;


import static android.database.sqlite.SQLiteDatabase.openDatabase;

/**
 * Created by Programador on 05/03/2015.
 */
public class registro_de_productos extends ActionBarActivity {
    private static String DB_PATH = "/data/data/com.logisticop.logisticop.logisticop/databases/";//ubicacion de la base de datos en la carpeta raiz de android
    private static String DB_NAME = "DB_LOGISTICOP.sqlite";//nombre de la base de datos
    SQLiteDatabase db;


    String ClNombre = "";
    String ClCodigo = "";
    String ClBase = "";
    String ClAltura = "";
    String ClProfundidad = "";


    private EditText nombreProducto;
    private EditText codigoProducto;
    private EditText baseDeCaja;
    private EditText alturaDeCaja;
    private EditText profundidadDeCaja;


    Boolean Insertado;








    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_de_actividades_por_clases);

        nombreProducto = (EditText) findViewById(R.id.editText);
        codigoProducto = (EditText) findViewById(R.id.editText6);
        baseDeCaja = (EditText) findViewById(R.id.editText3);
        alturaDeCaja = (EditText) findViewById(R.id.editText4);
        profundidadDeCaja = (EditText) findViewById(R.id.editText5);


    }








    public void BtnGrupo(View v) throws ParseException
    {


        ClNombre = nombreProducto.getText().toString();
        ClCodigo = codigoProducto.getText().toString();
        ClBase = baseDeCaja.getText().toString();
        ClAltura = alturaDeCaja.getText().toString();
        ClProfundidad = profundidadDeCaja.getText().toString();
        //ejecuto el metodo de insertar
        Insertar_Producto_Sqlite(ClNombre, Integer.parseInt(ClCodigo), Integer.parseInt(ClBase),
                                Integer.parseInt(ClAltura),Integer.parseInt(ClProfundidad));
        //valido si se inserto
        if (Insertado == true)
        {
            Toast.makeText(this, "Insertado con exito", Toast.LENGTH_LONG).show();
        }


    }

    //metodo de insercción



    public void Insertar_Producto_Sqlite(String pNombreProducto, int pCodigoProducto, int pBase, int pAltura, int pProfundidad)
    {


        try {
            db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);

            db.execSQL("INSERT INTO caja(_id,Nombre_Producto,Base,Altura,Profundidad)"
                    + " VALUES("+pCodigoProducto+",'" + pNombreProducto + "'," + pBase + "," + pAltura + "," + pProfundidad + ")");
            Insertado = true;
            db.close();
        } catch (Exception e) {

            Toast.makeText(getApplicationContext(),
                    "Error ", Toast.LENGTH_LONG);
        }
    }




}
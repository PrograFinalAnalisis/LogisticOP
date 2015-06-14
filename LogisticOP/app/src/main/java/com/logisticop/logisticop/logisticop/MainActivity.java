package com.logisticop.logisticop.logisticop;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.logisticop.logisticop.logisticop.BaseDatos.DBAdapter;
import com.logisticop.logisticop.logisticop.BaseDatos.Tablas.TablaEmpleados;

import java.util.ArrayList;

import static android.database.sqlite.SQLiteDatabase.openDatabase;

//import android.support.v7.appcompat.R.string;

public class MainActivity extends ActionBarActivity {

    SQLiteDatabase db;

    private  static String TABLE = "horas";
    private static String TABLE2 = "minutos";
    private TextView txt1,txt2,resultado;
    private EditText txtclave;
    private Button btnIngresar;
    public Spinner Sp_Usuarios;
    public ArrayList Mi_arreglo;
    public String NombreUsuario,id_em;
    public String nombreOrigen;
    public String claveOrigen;
    public  boolean verdadero;
    private DBAdapter myAdapBD;
    ImageView iv;
    private static String DB_PATH = "/data/data/com.logisticop.logisticop.logisticop/databases/";

    private static String DB_NAME = "DB_LOGISTICOP.sqlite";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.imageView2);
        txt1 = (TextView) findViewById(R.id.lbversion);
        txt2 = (TextView) findViewById(R.id.textView2);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        txtclave = (EditText) findViewById(R.id.txtpass);
        Sp_Usuarios = (Spinner) findViewById(R.id.SP_usuarios);
        resultado = (TextView) findViewById (R.id.lbversion);
        Mi_arreglo = new ArrayList();
        Mi_arreglo = ObtenerDatos();
        ArrayAdapter arrayAdapter; //= new ArrayAdapter(this, R.layout.spinner_row,Mi_arreglo);
        arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Mi_arreglo);
        ArrayAdapter my_Adapter = arrayAdapter;
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Sp_Usuarios.setAdapter(arrayAdapter);
        Sp_Usuarios.setAdapter(my_Adapter);
        Sp_Usuarios.setOnItemSelectedListener(new SpinnerListener());
        myAdapBD = new DBAdapter(this);
        myAdapBD.abrir();

        insertIntoTable();
        verdadero = false;
        btnIngresar.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                // try		{
                String password = txtclave.getText().toString();
               if(Login(NombreUsuario,password))
                {
                    if(verdadero == true){//este booleano se convierte a true cuando el login sea correcto
                        //bundle nos permite almacenar valores de la siguiente forma
                    /*  // bundle.putString( clave, valor );
                        // pudiendo BUNDLE alamcenar valores de todo tipo*/
                        Bundle bundle = new Bundle();
                        bundle.putString("id" , id_em );
                        bundle.putString("NombreUsuario" , NombreUsuario );
                        //envio los valores a la clase munuprincipal estos valores los uso en otros lado tambien tener cuidado cuando lo elimineis
                        Intent intent = null;
                        intent = new Intent(MainActivity.this, menuprincipal.class);
                        intent.putExtras( bundle );
                        startActivity(intent);
                       }
              }
                else
                {
                    Intent intent = null;
                    Toast.makeText(getApplicationContext(), "Los Datos son los incorrectos.",
                            Toast.LENGTH_LONG);
                }


            }
        });
    }


    public class SpinnerListener implements OnItemSelectedListener {

        // Metodo onItemSelected en el que indicamos lo que queremos hacer
        // cuando sea seleccionado un elemento del Spinner
        public void onItemSelected(AdapterView<?> parent,
                                   View view, int pos, long id) {

            NombreUsuario =  parent.getItemAtPosition(pos).toString();

        }
        public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(getApplicationContext(),
                    "No ah Selecionado un usuario: ",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void createBaseDatos() {
        try {
            db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);



            db.execSQL("create table IF NOT EXISTS empleados(_id integer primary key, " +
                    " Nombre text, Apellido1 text," +
                    " Apellido2 text , NombreCompleto text, Clave text);");

            db.execSQL("create table IF NOT EXISTS clientes(_id integer primary key, Nombre text,horaInicio integer, horaFin integer);");

            db.execSQL("create table IF NOT EXISTS caja(_id integer primary key, Nombre_Producto text,Base integer, Altura integer, Profundidad integer);");


            db.execSQL("create table IF NOT EXISTS pedidos(id_Cliente integer , id_Caja integer, cantidad integer, fecha Date);");


            db.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Se ha producido un error creando la base de datos",
                    Toast.LENGTH_LONG);
        }
    }//metodo que no se sa pero sirve


    public void insertIntoTable() {// inserto las horas a la tabla
        try {
            db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            Cursor c = db.query(TablaEmpleados.DATABASE_TABLE_EMP, null, null, null, null, null, null);


            if (c == null || c.getCount() == 0) {


                db.execSQL("INSERT INTO " + TablaEmpleados.DATABASE_TABLE_EMP + "(Nombre,Apellido1,Apellido2,NombreCompleto,Clave) VALUES('Adrian','Soto','Loria','Adrian Soto Loria','1234')");

                db.close();
            }

            c.close();



        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "Error in inserting into table", Toast.LENGTH_LONG);
        }
    }

    public ArrayList<String> ObtenerDatos() {//traigo los nombres de cada persona o empleado en la base de datos y lo muestro
        //en el spinner

        ArrayList<String> my_array = new ArrayList<String>();
        try {

            db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            Cursor allrows = db.rawQuery("SELECT _id,NombreCompleto FROM empleados", null);
            //System.out.println("COUNT : " + allrows.getCount());
            my_array.add("Seleccione su usuario");
            if (allrows.moveToFirst()) {
                do {

                    String ID = allrows.getString(0);
                    String NAME = allrows.getString(1);
                    //String PLACE = allrows.getString(2);

                    my_array.add(NAME);

                } while (allrows.moveToNext());
            }

            allrows.close();
            db.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error.",
                    Toast.LENGTH_LONG);
        }
        return my_array;
    }


    public boolean Login(String nombreUsuario2,String clave) throws SQLException {
    //metodo del login valido que el usuario exita y su clave sea corrects
        verdadero = false;
        //try	{
        ArrayList<String> mi_array = new ArrayList<String>();
        db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);


        Cursor celdas = db.rawQuery("SELECT _id,NombreCompleto,Clave FROM empleados where NombreCompleto=? and Clave=?;", new String[]{nombreUsuario2, clave});

        if(celdas.getCount() > 0 & celdas.getCount() < 2)
        // if(NombreUsuario == nombreUsuario2 & txtclave.getText().toString() == clave)
        {

            verdadero = true;

            if (celdas.moveToFirst()) {
                do {

                    String ID = celdas.getString(0);
                    this.id_em = ID;
                    mi_array.add(ID);

                } while (celdas.moveToNext());
            }

            celdas.close();
            return verdadero;

        }
        else
        {
            Toast.makeText(getApplicationContext(), "Los Datos proporcionados son incorrectos", Toast.LENGTH_LONG).show();
            verdadero = false;
            return verdadero;

        }





    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_crear) {
            Intent intent = null;
            intent = new Intent(MainActivity.this, Settings.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

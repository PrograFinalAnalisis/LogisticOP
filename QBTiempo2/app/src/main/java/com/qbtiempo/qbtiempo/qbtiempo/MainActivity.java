package com.qbtiempo.qbtiempo.qbtiempo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades.DBAdapter;
import com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades.Tablas.TablaHoras;
import com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades.Tablas.TablaMinutos;

import java.util.ArrayList;

import static android.database.sqlite.SQLiteDatabase.openDatabase;

//import android.support.v7.appcompat.R.string;

public class MainActivity extends ActionBarActivity {

    SQLiteDatabase db;
   // private  static String DBNAME = "QBTiempos.db";
    private  static String TABLE = "horas";
    private static String TABLE2 = "minutos";
    private TextView txt1,txt2,resultado;
    private EditText txtclave;
    private Button btnIngresar;
    public Spinner Sp_Usuarios;
    public ArrayList Mi_arreglo;
    public  String NombreUsuario,id_em;
    public String nombreOrigen;
    public String claveOrigen;
    public  boolean verdadero;
    private DBAdapter myAdapBD;

    private static String DB_PATH = "/data/data/com.qbtiempo.qbtiempo.qbtiempo/databases/";

    private static String DB_NAME = "DB_QBTIEMPOS.sqlite";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        //createBaseDatos();
        myAdapBD = new DBAdapter(this);
        myAdapBD.abrir();

      //  Toast.makeText(getBaseContext(), "Base de datos preparada", Toast.LENGTH_LONG).show();
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
                    if(verdadero == true){
                        //bundle nos permite almacenar valores de la siguiente forma
                    /*  // bundle.putString( clave, valor );
                        // pudiendo BUNDLE alamcenar valores de todo tipo*/
                        Bundle bundle = new Bundle();
                        bundle.putString("id" , id_em );
                        bundle.putString("NombreUsuario" , NombreUsuario );

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
                    " Apellido2 text , NombreCompleto text, Clave text," +
                    " NombreQB text," +
                    " ListID text, EditSequence text, ParentId text,ClassId text,Tipo integer, " +
                    " CodigoEstado integer,Email text, CodigoSupervisor interger," +
                    " FechaAlta date,Cedula text,DiasVacacionesRest float, HorasTrabajadas float," +
                    " Consecutivo integer,Visible integer, CodigoRol integer,CodigoClase integer," +
                    " cbClase integer, cbActividad integer, cbCliente integer,cbNomina integer," +
                    " Activo integer);");

            db.execSQL("create table IF NOT EXISTS clases(_id integer primary key, Nombre text, " +
                    "FullName text,ClassId text,ParentId text,EditSecuence text, " +
                    "ListId text, SubLevel integer,LastLevel integer,Activo integer );");

            db.execSQL("create table IF NOT EXISTS clientes(_id integer primary key, Nombre text,Apellido1 text, Apellido2 text, NombreCompleto text," +
                    "Clave text,NombreQB text,ListID text,EditSequence text,ParentId text,ClassId text, Tipo integer,CodigoEstado integer, " +
                    "Email text, FechaAlta date, Cedula text,Consecutivo integer,CodigoClase integer,Empresa text, Contacto text,Moneda text," +
                    "Sublevel integer,LastLevel integer,Activo integer);");

            db.execSQL("create table IF NOT EXISTS nomina(_id integer primary key, Nombre text, FullName text,ParentId text,EditSequence text,ListId text, Activo integer);");


            db.execSQL("create table IF NOT EXISTS servicios(_id integer primary key, Nombre text, FullName text,ParentId text,EditSecuence text,ListId text,SubLevel integer," +
                    "LastLevel integer, Activo integer);");

            db.execSQL("create table IF NOT EXISTS proveedores(_id integer primary key, Nombre text, Apellido1 text,Apellido2 text,NombreCompleto text, Clave text,NombreQB text," +
                    "ListID text,EditSequence text,ParentId text,ClassId text, Tipo integer,CodigoEstado integer,Email text,CodigoSupervisor integer,FechaAlta date," +
                    "Cedula text,DiasVacacionesRest float,HorasTrabajadas float,Consecutivo integer,Visible integer,CodigoRol integer,CodigoClase integer," +
                    "cbClase integer,cbActividad integer,cbCliente integer,cbNomina integer,Activo integer);");

            db.execSQL("create table IF NOT EXISTS roles(_id integer primary key, Nombre text);");

            db.execSQL("create table IF NOT EXISTS roles_empleados(_id integer primary key, ListID text, Tipo integer, Rol1 integer, Rol2 integer, Rol3 integer, Rol4 integer, Rol5 integer);");


            db.execSQL("CREATE TABLE IF NOT EXISTS actividades (_id integer  primary key,Linea integer DEFAULT NULL,"
                    + "CodigoEmpleado integer DEFAULT NULL,CodigoCliente integer DEFAULT NULL,"
                    + "CodigoServicio integer DEFAULT NULL,CodigoNomina integer DEFAULT NULL,"
                    + "CodigoClase integer DEFAULT NULL,Duracion float DEFAULT NULL,"
                    + "Fecha date DEFAULT NULL,CodigoEstado integer DEFAULT NULL,"
                    + "CodigoDia integer DEFAULT NULL,Descripcion text,EditSequence text DEFAULT NULL,"
                    + "ListId text DEFAULT NULL,CodigoAprovador integer DEFAULT NULL,TxnID text DEFAULT NULL,"
                    + "Customer_FullName text DEFAULT NULL,ItemService_FullName text DEFAULT NULL,"
                    + "Class_FullName text DEFAULT NULL,PayrollItemWage_FullName text DEFAULT NULL,"
                    + "Entity_FullName text DEFAULT NULL,BillableStatus integer DEFAULT NULL,"
                    + "Paquete integer DEFAULT NULL,Grupo integer DEFAULT NULL,CodigoCierre integer DEFAULT NULL,"
                    + "CodigoRevision integer DEFAULT NULL,CodigoRegistrador integer DEFAULT NULL,"
                    + "FechaCreacion datetime DEFAULT NULL,FechaAprobacion datetime DEFAULT NULL,"
                    + "Horas float DEFAULT NULL,Completa integer DEFAULT NULL,CodigoEstadoRevision integer DEFAULT NULL);");
            db.execSQL("CREATE TABLE IF NOT EXISTS horas (_id integer PRIMARY KEY AUTOINCREMENT,hhoras integer DEFAULT NULL);");
            db.execSQL("CREATE TABLE IF NOT EXISTS minutos (_id integer PRIMARY KEY AUTOINCREMENT,Mminutos integer DEFAULT NULL);");

            db.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Se ha producido un error creando la base de datos",
                    Toast.LENGTH_LONG);
        }
    }


    public void insertIntoTable() {
        try {
            db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            Cursor c = db.query(TablaHoras.DATABASE_TABLE_Horas, null, null, null, null, null, null);
            Cursor c2 = db.query(TablaMinutos.DATABASE_TABLE_minutos, null, null, null, null, null, null);
            if (c == null || c.getCount() == 0) {
                db.execSQL("INSERT INTO " + TablaHoras.DATABASE_TABLE_Horas + "(hhoras) VALUES('00')");
                db.execSQL("INSERT INTO " + TablaHoras.DATABASE_TABLE_Horas + "(hhoras) VALUES('01')");
                db.execSQL("INSERT INTO " + TablaHoras.DATABASE_TABLE_Horas + "(hhoras) VALUES('02')");
                db.execSQL("INSERT INTO " + TablaHoras.DATABASE_TABLE_Horas + "(hhoras) VALUES('03')");
                db.execSQL("INSERT INTO " + TablaHoras.DATABASE_TABLE_Horas + "(hhoras) VALUES('04')");
                db.execSQL("INSERT INTO " + TablaHoras.DATABASE_TABLE_Horas + "(hhoras) VALUES('05')");
                db.execSQL("INSERT INTO " + TablaHoras.DATABASE_TABLE_Horas + "(hhoras) VALUES('06')");
                db.execSQL("INSERT INTO " + TablaHoras.DATABASE_TABLE_Horas + "(hhoras) VALUES('07')");
                db.execSQL("INSERT INTO " + TablaHoras.DATABASE_TABLE_Horas + "(hhoras) VALUES('08')");
                db.execSQL("INSERT INTO " + TablaHoras.DATABASE_TABLE_Horas + "(hhoras) VALUES('09')");
                db.execSQL("INSERT INTO " + TablaHoras.DATABASE_TABLE_Horas + "(hhoras) VALUES('10')");
                db.execSQL("INSERT INTO " + TablaHoras.DATABASE_TABLE_Horas + "(hhoras) VALUES('11')");
                db.execSQL("INSERT INTO " + TablaHoras.DATABASE_TABLE_Horas + "(hhoras) VALUES('12')");


                db.close();
            }

            c.close();


            if (c2 == null || c2.getCount() == 0) {

                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos + "(mminutos) VALUES('00')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos + "(mminutos) VALUES('01')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos + "(mminutos) VALUES('02')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos + "(mminutos) VALUES('03')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos + "(mminutos) VALUES('04')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos + "(mminutos) VALUES('05')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos + "(mminutos) VALUES('06')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos + "(mminutos) VALUES('08')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos + "(mminutos) VALUES('09')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos + "(mminutos) VALUES('10')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos + "(mminutos) VALUES('11')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos + "(mminutos) VALUES('12')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos + "(mminutos) VALUES('13')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos + "(mminutos) VALUES('14')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos + "(mminutos) VALUES('15')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos + "(mminutos) VALUES('16')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos + "(mminutos) VALUES('17')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos + "(mminutos) VALUES('18')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('20')");

                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('21')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('22')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('23')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('24')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('25')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('26')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('27')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('28')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('29')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('30')");

                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('31')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('32')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('33')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('34')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('35')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('36')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('37')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('38')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('39')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('40')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('41')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('42')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('43')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('44')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('45')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('46')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('47')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('48')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('49')");

                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('50')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('51')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('52')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('53')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('54')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('55')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('56')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('57')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('58')");
                db.execSQL("INSERT INTO " + TablaMinutos.DATABASE_TABLE_minutos  + "(mminutos) VALUES('59')");


                db.close();
            }

            c2.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "Error in inserting into table", Toast.LENGTH_LONG);
        }
    }

    public ArrayList<String> ObtenerDatos() {

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


    public boolean Login(String nombreUsuario2,String clave) throws SQLException{

        verdadero = false;
        //try	{
        ArrayList<String> mi_array = new ArrayList<String>();
        db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
      //  db = openDatabase  ("DB_QBTIEMPOS.sqlite", null,Context.MODE_PRIVATE);

        Cursor celdas = db.rawQuery("SELECT _id,NombreCompleto,Clave FROM empleados where NombreCompleto=? and Clave=?;", new String[]{nombreUsuario2,clave});

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
            Toast.makeText(getApplicationContext()," La contraseña es incorrecta", Toast.LENGTH_LONG).show();
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

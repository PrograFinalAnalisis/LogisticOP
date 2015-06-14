package com.logisticop.logisticop.logisticop;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.logisticop.logisticop.logisticop.BaseDatos.DBAdapter;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static android.database.sqlite.SQLiteDatabase.openDatabase;

/**
 * Created by Programador on 05/03/2015.
 */
public class Settings extends ActionBarActivity {
    static Connection conexionMySQL;
    TextView textIP, textPuerto, textContrasena, textUsuario, textCatalogo, txterror;
    private Button buttonProbarConexion, btnsincronizar, btnguardar_configuracion, btnCargar_Preferencias;

    ProgressBar progressBar;
    ProgressDialog barProgressDialog;
    Handler updateBarHandler;
    private ProgressDialog prgDialog;
    // Progress Dialog type (0 - for Horizontal progress bar)
    public static final int progress_bar_type = 0;
    private static String DB_PATH = "/data/data/com.logisticop.logisticop.logisticop/databases/";
    //unicacion de la base de datos
    private static String DB_NAME = "DB_LOGISTICOP.sqlite";
    //nombre de la base de datos
    private ProgressDialog pDialog;
    private ProgressDialog dialog;
    private DBAdapter myAdapBD;
    SQLiteDatabase db;
    Handler j = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuracion);
        updateBarHandler = new Handler();

        textIP = (TextView) findViewById(R.id.txthost);
        textPuerto = (TextView) findViewById(R.id.txtpuerto);
        textContrasena = (TextView) findViewById(R.id.txtpass);
        textUsuario = (TextView) findViewById(R.id.txtusuario);
        textCatalogo = (TextView) findViewById(R.id.txtbase);
        buttonProbarConexion = (Button) findViewById(R.id.btntestconec);
        btnsincronizar = (Button) findViewById(R.id.btnsinc);
        btnguardar_configuracion = (Button) findViewById(R.id.btnsavepref);
        btnCargar_Preferencias = (Button) findViewById(R.id.btncargar_prefe);
        txterror = (TextView) findViewById(R.id.txterror);
        // progressBar = (ProgressBar) findViewById(R.id.progressBar);

        dialog = new ProgressDialog(this);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter
                .addAction("com.logisticop.logisticop.logisticop.ACTION_LOGOUT");
        registerReceiver(new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                context.unregisterReceiver(this);
                Log.d("onReceive", "Cerrando Sesión");
				/*
				 * En este punto es que se destruye este Activity para que no
				 * salga al presionar la tecla para volver atras, también
				 * debería iniciarse la MainActivity
				 */
                finish();
                Intent loginIntent = new Intent(context, MainActivity.class);
                startActivity(loginIntent);
            }
        }, intentFilter);


        buttonProbarConexion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)

            {

                try {
                    //probar la conexion a la base de datos
                    conectarBDMySQL(textUsuario.getText().toString(), textContrasena.getText().toString(), textIP.getText().toString(), textPuerto.getText().toString(), textCatalogo.getText().toString());
                } catch (java.sql.SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        btnsincronizar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)

            {

                // SincronizarThread();
                launchBarDialog();
            }
        });

        btnguardar_configuracion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)

            {
                guardarConfiguracion();

            }
        });
        btnCargar_Preferencias.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)

            {
                cargarConfiguracion();

            }
        });

    }


    public void conectar2() {


        try {

            java.sql.Connection connMySQL = null;

            StrictMode.ThreadPolicy tp = StrictMode.ThreadPolicy.LAX;
            //StrictMode.setThreadPolicy(tp);
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());


            Toast.makeText(getApplicationContext(),
                    "conecta: ",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

            Toast.makeText(getApplicationContext(),
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void terminar(View v){
        Intent broadcastIntent = new Intent();
        broadcastIntent
                .setAction("com.logisticop.logisticop.logisticop.ACTION_LOGOUT");
        sendBroadcast(broadcastIntent);
    }

    public void conectarBDMySQL(String usuario, String contrasena,
                                String ip, String puerto, String catalogo) throws java.sql.SQLException {
        if (usuario == "" || puerto == "" || ip == "") {

        } else {
            java.sql.Connection connMySQL1 = null;
            if (Build.VERSION.SDK_INT >= 10) {

                StrictMode.ThreadPolicy tp1 = StrictMode.ThreadPolicy.LAX;
                // StrictMode.setThreadPolicy(tp1);
                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
                String urlConexionMySQL = "";
                if (catalogo != "")
                    urlConexionMySQL = "jdbc:mysql://" + ip + ":" + puerto + "/" + catalogo;
                else
                    urlConexionMySQL = "jdbc:mysql://" + ip + ":" + puerto;
                if (usuario != "" & contrasena != "" & ip != "" & puerto != "") {
                    try {
                        java.sql.Connection connMySQL = null;
                        // if (Build.VERSION.SDK_INT>= 10) {
                        StrictMode.ThreadPolicy tp = StrictMode.ThreadPolicy.LAX;
                        StrictMode.setThreadPolicy(tp);
                        Class.forName("com.mysql.jdbc.Driver");
                        conexionMySQL = DriverManager.getConnection(urlConexionMySQL, usuario, contrasena);
                        if (conexionMySQL != null) {
                            Toast.makeText(getApplicationContext(), "Se Establecio la Conexión a la Base de Datos Satisfactoriamente  ", Toast.LENGTH_SHORT).show();
                        }
                    } catch (ClassNotFoundException e) {
                        Toast.makeText(getApplicationContext(),
                                "Error: " + e.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    } catch (SQLException e) {
                        Toast.makeText(getApplicationContext(),
                                "Error: " + e.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void guardarConfiguracion() {
        try {
            SharedPreferences prefs =
                    getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("Conexion", textIP.getText().toString());
            editor.putString("Contrasena", textContrasena.getText().toString());
            int puerto = 3306;
            puerto = Integer.valueOf(textPuerto.getText().toString());
            editor.putInt("Puerto", puerto);
            editor.putString("Usuario", textUsuario.getText().toString());
            editor.putString("Catalogo", textCatalogo.getText().toString());
            editor.commit();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    public void cargarConfiguracion() {
        try {
            //utilizo el SharedPreferences para guardar la informacion de la conexion
            //asi no la alamceno en una base de datos
            SharedPreferences prefs =
                    getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
            textIP.setText(prefs.getString("Conexion", ""));
            textContrasena.setText(prefs.getString("Contrasena", ""));
            int puerto = 3306;
            puerto = prefs.getInt("Puerto", 3306);
            textPuerto.setText(Integer.toString(puerto));
            textUsuario.setText(prefs.getString("Usuario", ""));
            textCatalogo.setText(prefs.getString("Catalogo", ""));

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.menu_crear) {
            Intent intent = null;
            intent = new Intent(Settings.this, Settings.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

// este metodo no lo uso debido aque solo se puede usar solo una vez
    public class MiTareaAsincrona extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPostExecute(Void result) {
            SystemClock.sleep(2000);
        }

        @Override
        protected void onPreExecute() {
            SystemClock.sleep(2100);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            SystemClock.sleep(100);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            //INICI CODi
            //Conectamos con el servidor de MySQL directamente
            try {
                conectarBDMySQL(textUsuario.getText().toString(), textContrasena.getText().toString(), textIP.getText().toString(), textPuerto.getText().toString(), textCatalogo.getText().toString());
            } catch (java.sql.SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return null;
            //FINAL CODI
        }

    }

    Message msg = new Message();
  // este hilo funciona a medias .. es decir en el hilo empieza bien pero despues deja de funcionar por eso no lo uso
    public void SincronizarThread() {

        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(), "Entro", Toast.LENGTH_SHORT).show();
                guardarConfiguracion();
                try {
                    db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);

                    db.execSQL("Delete from empleados");

                    db.execSQL("Delete from clases");

                    db.execSQL("Delete from clientes");

                    db.execSQL("Delete from nomina");

                    db.execSQL("Delete from servicios");

                    db.execSQL("Delete from proveedores");

                    db.execSQL("Delete from roles");

                    db.execSQL("Delete from roles_empleados");
                    conectarBDMySQL(textUsuario.getText().toString(), textContrasena.getText().toString(), textIP.getText().toString(), textPuerto.getText().toString(), textCatalogo.getText().toString());

                    Statement st = (Statement) conexionMySQL.createStatement();
                    ResultSet rs = st.executeQuery("select Id,Nombre,Apellido1,Apellido2,NombreCompleto,Clave,NombreQB,ListID,EditSequence,ParentId,ClassId,Tipo," +
                            "CodigoEstado,Email,CodigoSupervisor,FechaAlta,Cedula,DiasVacacionesRest,HorasTrabajadas,Consecutivo,Visible," +
                            "CodigoRol,CodigoClase,cbClase,cbActividad,cbCliente,cbNomina,Activo from tb_empleados");

                    while (rs.next()) {
                        int id = rs.getInt("Id");
                        String Nombre = rs.getString("Nombre");
                        String Apellido1 = rs.getString("Apellido1");
                        String Apellido2 = rs.getString("Apellido2");
                        String NombreCompleto = rs.getString("NombreCompleto");
                        String Clave = rs.getString("Clave");
                        String NombreQB = rs.getString("NombreQB");
                        String ListID = rs.getString("ListID");
                        String EditSequence = rs.getString("EditSequence");
                        String ParentId = rs.getString("ParentId");
                        String ClassId = rs.getString("ClassId");
                        int Tipo = rs.getInt("Tipo");
                        int CodigoEstado = rs.getInt("CodigoEstado");
                        String Email = rs.getString("Email");
                        int CodigoSupervisor = rs.getInt("CodigoSupervisor");
                        Date FechaAlta = rs.getDate("FechaAlta");
                        String Cedula = rs.getString("Cedula");
                        float DiasVacacionesRest = rs.getFloat("DiasVacacionesRest");
                        float HorasTrabajadas = rs.getFloat("HorasTrabajadas");
                        int Consecutivo = rs.getInt("Consecutivo");
                        int Visible = rs.getInt("Visible");
                        int CodigoRol = rs.getInt("CodigoRol");
                        int CodigoClase = rs.getInt("CodigoClase");
                        int cbClase = rs.getInt("cbClase");
                        int cbActividad = rs.getInt("cbActividad");
                        int cbCliente = rs.getInt("cbCliente");
                        int cbNomina = rs.getInt("cbNomina");
                        int Activo = rs.getInt("Activo");


                        db.execSQL("INSERT INTO empleados(_id  ," +
                                " Nombre, Apellido1 ," +
                                " Apellido2 , NombreCompleto, Clave," +
                                " NombreQB , ListID , EditSequence , ParentId ,ClassId ," +
                                " Tipo , CodigoEstado ,Email , CodigoSupervisor ," +
                                " FechaAlta ,Cedula ,DiasVacacionesRest , HorasTrabajadas ," +
                                " Consecutivo ,Visible , CodigoRol ,CodigoClase ," +
                                " cbClase , cbActividad , cbCliente ,cbNomina ," +
                                " Activo) VALUES(" + id + ",'" + Nombre + "','" + Apellido1 + "','" + Apellido2 + "','" + NombreCompleto + "','" + Clave + "','" + NombreQB + "','" + ListID + "','" + EditSequence + "','" + ParentId + "','" + ClassId + "'" +
                                "," + Tipo + "," + CodigoEstado + ",'" + Email + "'," + CodigoSupervisor + "," + FechaAlta + ",'" + Cedula + "'," + DiasVacacionesRest + "," + HorasTrabajadas + "," + Consecutivo + "," + Visible + "," + CodigoRol + "," +
                                "" + CodigoClase + "," + cbClase + "," + cbActividad + "," + cbCliente + "," + cbNomina + "," + Activo + ")");


                    }

                    Statement st2 = (Statement) conexionMySQL.createStatement();
                    ResultSet rs2 = st2.executeQuery("select Id,Nombre,FullName,ClassId,ParentId,EditSecuence,ListId,SubLevel,LastLevel,Activo from tb_clases");

                    while (rs2.next()) {
                        int id = rs2.getInt("Id");
                        String Nombre = rs2.getString("Nombre");
                        String FullName = rs2.getString("FullName");

                        String ClassId = rs2.getString("ClassId");
                        String ParentId = rs2.getString("ParentId");
                        String EditSecuence = rs2.getString("EditSecuence");
                        String ListId = rs2.getString("ListId");
                        int SubLevel = rs2.getInt("SubLevel");
                        int LastLevel = rs2.getInt("LastLevel");
                        int Activo = rs2.getInt("Activo");


                        //       db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
                        db.execSQL("INSERT INTO clases(_id  ," +
                                " Nombre, FullName ," +
                                " ClassId , ParentId, EditSecuence," +
                                " ListId , SubLevel , LastLevel , Activo)" +
                                " VALUES(" + id + ",'" + Nombre + "','" + FullName + "','" + ClassId + "','" + ParentId +
                                "','" + EditSecuence + "','" + ListId + "'," + SubLevel + "," + LastLevel + "," + Activo + ")");

                    }
                    ///////empieza clientes
                    Statement st3 = (Statement) conexionMySQL.createStatement();
                    ResultSet rs3 = st3.executeQuery("SELECT Id,Nombre,Apellido1,Apellido2,NombreCompleto ,Clave ,NombreQB,ListID ,EditSequence " +
                            ",ParentId ,ClassId,Tipo,CodigoEstado,Email,FechaAlta,Cedula,Consecutivo,CodigoClase,Empresa," +
                            "Contacto,Moneda,Sublevel,LastLevel,Activo FROM tb_clientes");

                    while (rs3.next()) {
                        int id = rs3.getInt("Id");
                        String Nombre = rs3.getString("Nombre");
                        String Apellido1 = rs3.getString("Apellido1");

                        String Apellido2 = rs3.getString("Apellido2");
                        String NombreCompleto = rs3.getString("NombreCompleto");
                        String Clave = rs3.getString("Clave");
                        String NombreQB = rs3.getString("NombreQB");
                        String ListID = rs3.getString("ListID");
                        String EditSequence = rs3.getString("EditSequence");
                        String ParentId = rs3.getString("ParentId");
                        String ClassId = rs3.getString("ClassId");
                        int Tipo = rs3.getInt("Tipo");
                        int CodigoEstado = rs3.getInt("CodigoEstado");
                        String Email = rs3.getString("Email");
                        Date FechaAlta = rs3.getDate("FechaAlta");
                        String Cedula = rs3.getString("Cedula");
                        int Consecutivo = rs3.getInt("Consecutivo");
                        int CodigoClase = rs3.getInt("CodigoClase");
                        String Empresa = rs3.getString("Empresa");
                        String Contacto = rs3.getString("Contacto");
                        String Moneda = rs3.getString("Moneda");
                        int Sublevel = rs3.getInt("Sublevel");
                        int LastLevel = rs3.getInt("LastLevel");
                        int Activo = rs3.getInt("Activo");


                        //      db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
                        db.execSQL("INSERT INTO clientes(_id  ," +
                                " Nombre, Apellido1 ," +
                                " Apellido2 , NombreCompleto, Clave," +
                                " NombreQB , ListID , EditSequence , ParentId ,ClassId ," +
                                " Tipo , CodigoEstado ,Email , FechaAlta ,Cedula ," +
                                " Consecutivo ,CodigoClase , Empresa ,Contacto ," +
                                " Moneda , Sublevel , LastLevel ," +
                                " Activo) " +
                                "VALUES(" + id + ",'" + Nombre + "','" + Apellido1 + "','" + Apellido2 + "','" + NombreCompleto + "','" + Clave + "','" + NombreQB + "'," +
                                "'" + ListID + "','" + EditSequence + "','" + ParentId + "','" + ClassId + "'" +
                                "," + Tipo + "," + CodigoEstado + ",'" + Email + "'," + FechaAlta + ",'" + Cedula + "'," + Consecutivo + "," +
                                "" + CodigoClase + ",'" + Empresa + "','" + Contacto + "','" + Moneda + "'," + Sublevel + "," + LastLevel + "," + Activo + ")");


                    }
                    ///// fin nomina

                    Statement st4 = (Statement) conexionMySQL.createStatement();
                    ResultSet rs4 = st4.executeQuery("SELECT Id,Nombre,FullName,ParentId,EditSequence,ListId,Activo FROM tb_nomina");

                    while (rs4.next()) {
                        int id = rs4.getInt("Id");
                        String Nombre = rs4.getString("Nombre");
                        String FullName = rs4.getString("FullName");

                        String ParentId = rs4.getString("ParentId");
                        String EditSequence = rs4.getString("EditSequence");
                        String ListId = rs4.getString("ListId");
                        int Activo = rs4.getInt("Activo");


                        db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
                        db.execSQL("INSERT INTO nomina(_id  ," +
                                " Nombre, FullName ," +
                                " ParentId , EditSecuence , ListId," +
                                " Activo) VALUES(" + id + ",'" + Nombre + "','" + FullName + "','" + ParentId + "','" + EditSequence + "','" + ListId + "'," + Activo + ")");


                    }
                    // fin nomina
                    Statement st5 = (Statement) conexionMySQL.createStatement();
                    ResultSet rs5 = st5.executeQuery("SELECT Id,Nombre,FullName,ParentId,EditSecuence,ListId,SubLevel,LastLevel,Activo FROM tb_servicios");

                    while (rs5.next()) {
                        int id = rs5.getInt("Id");
                        String Nombre = rs5.getString("Nombre");
                        String FullName = rs5.getString("FullName");

                        String ParentId = rs5.getString("ParentId");
                        String EditSequence = rs5.getString("EditSecuence");
                        String ListId = rs5.getString("ListId");
                        int Sublevel = rs5.getInt("Sublevel");
                        int LastLevel = rs5.getInt("LastLevel");
                        int Activo = rs5.getInt("Activo");


                        //   db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
                        db.execSQL("INSERT INTO servicios(_id  ," +
                                " Nombre, FullName ," +
                                " ParentId , EditSecuence, ListId, SubLevel, LastLevel," +
                                " Activo) VALUES(" + id + ",'" + Nombre + "','" + FullName + "','" + ParentId + "','" + EditSequence + "','" + ListId + "'," + Sublevel + "," + LastLevel + "," + Activo + ")");


                    } //---------------------------- fin servicios
                    Statement st6 = (Statement) conexionMySQL.createStatement();
                    ResultSet rs6 = st6.executeQuery("SELECT Id,Nombre,Apellido1,Apellido2,NombreCompleto,Clave,NombreQB,ListID,EditSequence," +
                            "ParentId,ClassId,Tipo,CodigoEstado,Email,CodigoSupervisor,FechaAlta,Cedula,DiasVacacionesRest,HorasTrabajadas," +
                            "Consecutivo,Visible,CodigoRol,CodigoClase,cbClase,cbActividad,cbCliente,cbNomina,Activo FROM tb_proveedores;");

                    while (rs6.next()) {
                        int id = rs6.getInt("Id");
                        String Nombre = rs6.getString("Nombre");
                        String Apellido1 = rs6.getString("Apellido1");
                        String Apellido2 = rs6.getString("Apellido2");
                        String NombreCompleto = rs6.getString("NombreCompleto");
                        String Clave = rs6.getString("Clave");
                        String NombreQB = rs6.getString("NombreQB");
                        String ListID = rs6.getString("ListID");
                        String EditSequence = rs6.getString("EditSequence");
                        String ParentId = rs6.getString("ParentId");
                        String ClassId = rs6.getString("ClassId");
                        int Tipo = rs6.getInt("Tipo");
                        int CodigoEstado = rs6.getInt("CodigoEstado");
                        String Email = rs6.getString("Email");
                        int CodigoSupervisor = rs6.getInt("CodigoSupervisor");
                        Date FechaAlta = rs6.getDate("FechaAlta");
                        String Cedula = rs6.getString("Cedula");
                        float DiasVacacionesRest = rs6.getFloat("DiasVacacionesRest");
                        float HorasTrabajadas = rs6.getFloat("HorasTrabajadas");
                        int Consecutivo = rs6.getInt("Consecutivo");
                        int Visible = rs6.getInt("Visible");
                        int CodigoRol = rs6.getInt("CodigoRol");
                        int CodigoClase = rs6.getInt("CodigoClase");
                        int cbClase = rs6.getInt("cbClase");
                        int cbActividad = rs6.getInt("cbActividad");
                        int cbCliente = rs6.getInt("cbCliente");
                        int cbNomina = rs6.getInt("cbNomina");
                        int Activo = rs6.getInt("Activo");

                        if (Nombre.contains("'")) {
                            Nombre = Nombre.replaceAll("'", "''");
                        }
                        if (NombreCompleto.contains("'")) {
                            NombreCompleto = NombreCompleto.replaceAll("'", "''");
                        }
                        if (NombreQB.contains("'")) {
                            NombreQB = NombreQB.replaceAll("'", "''");
                        }
                        //     db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
                        db.execSQL("INSERT INTO proveedores(_id  ," +
                                " Nombre, Apellido1 ," +
                                " Apellido2 , NombreCompleto, Clave," +
                                " NombreQB , ListID , EditSequence , ParentId ,ClassId ," +
                                " Tipo , CodigoEstado ,Email , CodigoSupervisor ," +
                                " FechaAlta ,Cedula ,DiasVacacionesRest , HorasTrabajadas ," +
                                " Consecutivo ,Visible , CodigoRol ,CodigoClase ," +
                                " cbClase , cbActividad , cbCliente ,cbNomina ," +
                                " Activo) VALUES(" + id + ",'" + Nombre + "','" + Apellido1 + "','" + Apellido2 + "','" + NombreCompleto + "','" + Clave + "','" + NombreQB + "','" + ListID + "','" + EditSequence + "','" + ParentId + "','" + ClassId + "'" +
                                "," + Tipo + "," + CodigoEstado + ",'" + Email + "'," + CodigoSupervisor + "," + FechaAlta + ",'" + Cedula + "'," + DiasVacacionesRest + "," + HorasTrabajadas + "," + Consecutivo + "," + Visible + "," + CodigoRol + "," +
                                "" + CodigoClase + "," + cbClase + "," + cbActividad + "," + cbCliente + "," + cbNomina + "," + Activo + ")");

                    }

                    //////// empieza la tabla roles

                    Statement st7 = (Statement) conexionMySQL.createStatement();
                    ResultSet rs7 = st7.executeQuery("SELECT Codigo,Nombre FROM tb_roles");

                    while (rs7.next()) {
                        int id = rs7.getInt("Codigo");
                        String Nombre = rs7.getString("Nombre");


                        //  db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
                        db.execSQL("INSERT INTO roles(_id, Nombre) VALUES(" + id + ",'" + Nombre + "')");

                    }

                    //// termina la tabloa roles

                    //////empieza tabla roles empleados

                    Statement st8 = (Statement) conexionMySQL.createStatement();
                    ResultSet rs8 = st8.executeQuery(" SELECT Id,ListID,Tipo,Rol1,Rol2,Rol3,Rol4,Rol5 FROM tb_rolesempleados");

                    while (rs8.next()) {
                        int id = rs8.getInt("Id");
                        String ListID = rs8.getString("ListID");
                        int Tipo = rs8.getInt("Tipo");
                        int Rol1 = rs8.getInt("Rol1");
                        int Rol2 = rs8.getInt("Rol2");
                        int Rol3 = rs8.getInt("Rol3");
                        int Rol4 = rs8.getInt("Rol4");
                        int Rol5 = rs8.getInt("Rol5");
                        //    db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
                        db.execSQL("INSERT INTO roles_empleados(_id, ListID,Tipo,Rol1,Rol2,Rol3,Rol4,Rol5) " +
                                "VALUES(" + id + ",'" + ListID + "'," + Tipo + "," + Rol1 + "," + Rol2 + "," + Rol3 + "," + Rol4 + "," + Rol5 + ")");

                    }


                    // termina roles empleados

                    conexionMySQL.close();

                    Toast.makeText(getApplicationContext(), "Termino ", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    txterror.setText(e.getMessage());
                }
            }
        });

    }

    public void launchBarDialog() {

       /*    barProgressDialog = new ProgressDialog(Settings.this);
        barProgressDialog.setTitle("Espere  ...");
        barProgressDialog.setMessage("Conectando con el servidor ...");
        barProgressDialog.setProgressStyle(barProgressDialog.STYLE_SPINNER);
        barProgressDialog.setProgress(0);
        barProgressDialog.setMax(10);
        barProgressDialog.show();

     new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    while (barProgressDialog.getProgress() <= barProgressDialog.getMax()) {

                        Thread.sleep(2000);

                        updateBarHandler.post(new Runnable() {

                            public void run() {

                                barProgressDialog.incrementProgressBy(2);*/

        guardarConfiguracion();
        try {
            //habro conexion
            db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            //borro los datos de las tablas
            db.execSQL("Delete from empleados");

            db.execSQL("Delete from clases");

            db.execSQL("Delete from clientes");

            db.execSQL("Delete from nomina");

            db.execSQL("Delete from servicios");

            db.execSQL("Delete from proveedores");

            db.execSQL("Delete from roles");

            db.execSQL("Delete from roles_empleados");
            //consulto  la base de datos
            conectarBDMySQL(textUsuario.getText().toString(), textContrasena.getText().toString(), textIP.getText().toString(), textPuerto.getText().toString(), textCatalogo.getText().toString());

            Statement st = (Statement) conexionMySQL.createStatement();
            //ejecuto el query
            ResultSet rs = st.executeQuery("select Id,Nombre,Apellido1,Apellido2,NombreCompleto,Clave,NombreQB,ListID,EditSequence,ParentId,ClassId,Tipo," +
                    "CodigoEstado,Email,CodigoSupervisor,FechaAlta,Cedula,DiasVacacionesRest,HorasTrabajadas,Consecutivo,Visible," +
                    "CodigoRol,CodigoClase,cbClase,cbActividad,cbCliente,cbNomina,Activo from tb_empleados");

            while (rs.next()) {
                //asigno todos los valores del query a variables
                int id = rs.getInt("Id");
                String Nombre = rs.getString("Nombre");
                String Apellido1 = rs.getString("Apellido1");
                String Apellido2 = rs.getString("Apellido2");
                String NombreCompleto = rs.getString("NombreCompleto");
                String Clave = rs.getString("Clave");
                String NombreQB = rs.getString("NombreQB");
                String ListID = rs.getString("ListID");
                String EditSequence = rs.getString("EditSequence");
                String ParentId = rs.getString("ParentId");
                String ClassId = rs.getString("ClassId");
                int Tipo = rs.getInt("Tipo");
                int CodigoEstado = rs.getInt("CodigoEstado");
                String Email = rs.getString("Email");
                int CodigoSupervisor = rs.getInt("CodigoSupervisor");
                Date FechaAlta = rs.getDate("FechaAlta");
                String Cedula = rs.getString("Cedula");
                float DiasVacacionesRest = rs.getFloat("DiasVacacionesRest");
                float HorasTrabajadas = rs.getFloat("HorasTrabajadas");
                int Consecutivo = rs.getInt("Consecutivo");
                int Visible = rs.getInt("Visible");
                int CodigoRol = rs.getInt("CodigoRol");
                int CodigoClase = rs.getInt("CodigoClase");
                int cbClase = rs.getInt("cbClase");
                int cbActividad = rs.getInt("cbActividad");
                int cbCliente = rs.getInt("cbCliente");
                int cbNomina = rs.getInt("cbNomina");
                int Activo = rs.getInt("Activo");


                //inserto los empleados
                db.execSQL("INSERT INTO empleados(_id  ," +
                        " Nombre, Apellido1 ," +
                        " Apellido2 , NombreCompleto, Clave," +
                        " NombreQB , ListID , EditSequence , ParentId ,ClassId ," +
                        " Tipo , CodigoEstado ,Email , CodigoSupervisor ," +
                        " FechaAlta ,Cedula ,DiasVacacionesRest , HorasTrabajadas ," +
                        " Consecutivo ,Visible , CodigoRol ,CodigoClase ," +
                        " cbClase , cbActividad , cbCliente ,cbNomina ," +
                        " Activo) VALUES(" + id + ",'" + Nombre + "','" + Apellido1 + "','" + Apellido2 + "','" + NombreCompleto + "','" + Clave + "','" + NombreQB + "','" + ListID + "','" + EditSequence + "','" + ParentId + "','" + ClassId + "'" +
                        "," + Tipo + "," + CodigoEstado + ",'" + Email + "'," + CodigoSupervisor + "," + FechaAlta + ",'" + Cedula + "'," + DiasVacacionesRest + "," + HorasTrabajadas + "," + Consecutivo + "," + Visible + "," + CodigoRol + "," +
                        "" + CodigoClase + "," + cbClase + "," + cbActividad + "," + cbCliente + "," + cbNomina + "," + Activo + ")");


            }

            Statement st2 = (Statement) conexionMySQL.createStatement();
            ResultSet rs2 = st2.executeQuery("select Id,Nombre,FullName,ClassId,ParentId,EditSecuence,ListId,SubLevel,LastLevel,Activo from tb_clases");

            while (rs2.next()) {
                int id = rs2.getInt("Id");
                String Nombre = rs2.getString("Nombre");
                String FullName = rs2.getString("FullName");

                String ClassId = rs2.getString("ClassId");
                String ParentId = rs2.getString("ParentId");
                String EditSecuence = rs2.getString("EditSecuence");
                String ListId = rs2.getString("ListId");
                int SubLevel = rs2.getInt("SubLevel");
                int LastLevel = rs2.getInt("LastLevel");
                int Activo = rs2.getInt("Activo");



                db.execSQL("INSERT INTO clases(_id  ," +
                        " Nombre, FullName ," +
                        " ClassId , ParentId, EditSecuence," +
                        " ListId , SubLevel , LastLevel , Activo)" +
                        " VALUES(" + id + ",'" + Nombre + "','" + FullName + "','" + ClassId + "','" + ParentId +
                        "','" + EditSecuence + "','" + ListId + "'," + SubLevel + "," + LastLevel + "," + Activo + ")");

            }

            ///////empieza clientes
            Statement st3 = (Statement) conexionMySQL.createStatement();
            ResultSet rs3 = st3.executeQuery("SELECT Id,Nombre,Apellido1,Apellido2,NombreCompleto ,Clave ,NombreQB,ListID ,EditSequence " +
                    ",ParentId ,ClassId,Tipo,CodigoEstado,Email,FechaAlta,Cedula,Consecutivo,CodigoClase,Empresa," +
                    "Contacto,Moneda,Sublevel,LastLevel,Activo FROM tb_clientes");

            while (rs3.next()) {
                int id = rs3.getInt("Id");
                String Nombre = rs3.getString("Nombre");
                String Apellido1 = rs3.getString("Apellido1");

                String Apellido2 = rs3.getString("Apellido2");
                String NombreCompleto = rs3.getString("NombreCompleto");
                String Clave = rs3.getString("Clave");
                String NombreQB = rs3.getString("NombreQB");
                String ListID = rs3.getString("ListID");
                String EditSequence = rs3.getString("EditSequence");
                String ParentId = rs3.getString("ParentId");
                String ClassId = rs3.getString("ClassId");
                int Tipo = rs3.getInt("Tipo");
                int CodigoEstado = rs3.getInt("CodigoEstado");
                String Email = rs3.getString("Email");
                Date FechaAlta = rs3.getDate("FechaAlta");
                String Cedula = rs3.getString("Cedula");
                int Consecutivo = rs3.getInt("Consecutivo");
                int CodigoClase = rs3.getInt("CodigoClase");
                String Empresa = rs3.getString("Empresa");
                String Contacto = rs3.getString("Contacto");
                String Moneda = rs3.getString("Moneda");
                int Sublevel = rs3.getInt("Sublevel");
                int LastLevel = rs3.getInt("LastLevel");
                int Activo = rs3.getInt("Activo");


                //    db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
                db.execSQL("INSERT INTO clientes(_id  ," +
                        " Nombre, Apellido1 ," +
                        " Apellido2 , NombreCompleto, Clave," +
                        " NombreQB , ListID , EditSequence , ParentId ,ClassId ," +
                        " Tipo , CodigoEstado ,Email , FechaAlta ,Cedula ," +
                        " Consecutivo ,CodigoClase , Empresa ,Contacto ," +
                        " Moneda , Sublevel , LastLevel ," +
                        " Activo) " +
                        "VALUES(" + id + ",'" + Nombre + "','" + Apellido1 + "','" + Apellido2 + "','" + NombreCompleto + "','" + Clave + "','" + NombreQB + "'," +
                        "'" + ListID + "','" + EditSequence + "','" + ParentId + "','" + ClassId + "'" +
                        "," + Tipo + "," + CodigoEstado + ",'" + Email + "'," + FechaAlta + ",'" + Cedula + "'," + Consecutivo + "," +
                        "" + CodigoClase + ",'" + Empresa + "','" + Contacto + "','" + Moneda + "'," + Sublevel + "," + LastLevel + "," + Activo + ")");


            }
            ///// fin nomina

            Statement st4 = (Statement) conexionMySQL.createStatement();
            ResultSet rs4 = st4.executeQuery("SELECT Id,Nombre,FullName,ParentId,EditSequence,ListId,Activo FROM tb_nomina");

            while (rs4.next()) {
                int id = rs4.getInt("Id");
                String Nombre = rs4.getString("Nombre");
                String FullName = rs4.getString("FullName");

                String ParentId = rs4.getString("ParentId");
                String EditSequence = rs4.getString("EditSequence");
                String ListId = rs4.getString("ListId");
                int Activo = rs4.getInt("Activo");


                //      db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
                db.execSQL("INSERT INTO nomina(_id  ," +
                        " Nombre, FullName ," +
                        " ParentId , EditSecuence , ListId," +
                        " Activo) VALUES(" + id + ",'" + Nombre + "','" + FullName + "','" + ParentId + "','" + EditSequence + "','" + ListId + "'," + Activo + ")");


            }

            // fin nomina
            Statement st5 = (Statement) conexionMySQL.createStatement();
            ResultSet rs5 = st5.executeQuery("SELECT Id,Nombre,FullName,ParentId,EditSecuence,ListId,SubLevel,LastLevel,Activo FROM tb_servicios");

            while (rs5.next()) {
                int id = rs5.getInt("Id");
                String Nombre = rs5.getString("Nombre");
                String FullName = rs5.getString("FullName");

                String ParentId = rs5.getString("ParentId");
                String EditSequence = rs5.getString("EditSecuence");
                String ListId = rs5.getString("ListId");
                int Sublevel = rs5.getInt("Sublevel");
                int LastLevel = rs5.getInt("LastLevel");
                int Activo = rs5.getInt("Activo");


                //      db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
                db.execSQL("INSERT INTO servicios(_id  ," +
                        " Nombre, FullName ," +
                        " ParentId , EditSecuence, ListId, SubLevel, LastLevel," +
                        " Activo) VALUES(" + id + ",'" + Nombre + "','" + FullName + "','" + ParentId + "','" + EditSequence + "','" + ListId + "'," + Sublevel + "," + LastLevel + "," + Activo + ")");


            } //---------------------------- fin servicios


            Statement st6 = (Statement) conexionMySQL.createStatement();
            ResultSet rs6 = st6.executeQuery("SELECT Id,Nombre,Apellido1,Apellido2,NombreCompleto,Clave,NombreQB,ListID,EditSequence," +
                    "ParentId,ClassId,Tipo,CodigoEstado,Email,CodigoSupervisor,FechaAlta,Cedula,DiasVacacionesRest,HorasTrabajadas," +
                    "Consecutivo,Visible,CodigoRol,CodigoClase,cbClase,cbActividad,cbCliente,cbNomina,Activo FROM tb_proveedores;");

            while (rs6.next()) {
                int id = rs6.getInt("Id");
                String Nombre = rs6.getString("Nombre");
                String Apellido1 = rs6.getString("Apellido1");
                String Apellido2 = rs6.getString("Apellido2");
                String NombreCompleto = rs6.getString("NombreCompleto");
                String Clave = rs6.getString("Clave");
                String NombreQB = rs6.getString("NombreQB");
                String ListID = rs6.getString("ListID");
                String EditSequence = rs6.getString("EditSequence");
                String ParentId = rs6.getString("ParentId");
                String ClassId = rs6.getString("ClassId");
                int Tipo = rs6.getInt("Tipo");
                int CodigoEstado = rs6.getInt("CodigoEstado");
                String Email = rs6.getString("Email");
                int CodigoSupervisor = rs6.getInt("CodigoSupervisor");
                Date FechaAlta = rs6.getDate("FechaAlta");
                String Cedula = rs6.getString("Cedula");
                float DiasVacacionesRest = rs6.getFloat("DiasVacacionesRest");
                float HorasTrabajadas = rs6.getFloat("HorasTrabajadas");
                int Consecutivo = rs6.getInt("Consecutivo");
                int Visible = rs6.getInt("Visible");
                int CodigoRol = rs6.getInt("CodigoRol");
                int CodigoClase = rs6.getInt("CodigoClase");
                int cbClase = rs6.getInt("cbClase");
                int cbActividad = rs6.getInt("cbActividad");
                int cbCliente = rs6.getInt("cbCliente");
                int cbNomina = rs6.getInt("cbNomina");
                int Activo = rs6.getInt("Activo");

                if (Nombre.contains("'")) {
                    Nombre = Nombre.replaceAll("'", "''");
                }
                if (NombreCompleto.contains("'")) {
                    NombreCompleto = NombreCompleto.replaceAll("'", "''");
                }
                if (NombreQB.contains("'")) {
                    NombreQB = NombreQB.replaceAll("'", "''");
                }
                //      db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
                db.execSQL("INSERT INTO proveedores(_id  ," +
                        " Nombre, Apellido1 ," +
                        " Apellido2 , NombreCompleto, Clave," +
                        " NombreQB , ListID , EditSequence , ParentId ,ClassId ," +
                        " Tipo , CodigoEstado ,Email , CodigoSupervisor ," +
                        " FechaAlta ,Cedula ,DiasVacacionesRest , HorasTrabajadas ," +
                        " Consecutivo ,Visible , CodigoRol ,CodigoClase ," +
                        " cbClase , cbActividad , cbCliente ,cbNomina ," +
                        " Activo) VALUES(" + id + ",'" + Nombre + "','" + Apellido1 + "','" + Apellido2 + "','" + NombreCompleto + "','" + Clave + "','" + NombreQB + "','" + ListID + "','" + EditSequence + "','" + ParentId + "','" + ClassId + "'" +
                        "," + Tipo + "," + CodigoEstado + ",'" + Email + "'," + CodigoSupervisor + "," + FechaAlta + ",'" + Cedula + "'," + DiasVacacionesRest + "," + HorasTrabajadas + "," + Consecutivo + "," + Visible + "," + CodigoRol + "," +
                        "" + CodigoClase + "," + cbClase + "," + cbActividad + "," + cbCliente + "," + cbNomina + "," + Activo + ")");

            }

            //////// empieza la tabla roles

            Statement st7 = (Statement) conexionMySQL.createStatement();
            ResultSet rs7 = st7.executeQuery("SELECT Codigo,Nombre FROM tb_roles");

            while (rs7.next()) {
                int id = rs7.getInt("Codigo");
                String Nombre = rs7.getString("Nombre");


                //      db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
                db.execSQL("INSERT INTO roles(_id, Nombre) VALUES(" + id + ",'" + Nombre + "')");

            }

            //// termina la tabloa roles

            //////empieza tabla roles empleados

            Statement st8 = (Statement) conexionMySQL.createStatement();
            ResultSet rs8 = st8.executeQuery(" SELECT Id,ListID,Tipo,Rol1,Rol2,Rol3,Rol4,Rol5 FROM tb_rolesempleados");

            while (rs8.next()) {
                int id = rs8.getInt("Id");
                String ListID = rs8.getString("ListID");
                int Tipo = rs8.getInt("Tipo");
                int Rol1 = rs8.getInt("Rol1");
                int Rol2 = rs8.getInt("Rol2");
                int Rol3 = rs8.getInt("Rol3");
                int Rol4 = rs8.getInt("Rol4");
                int Rol5 = rs8.getInt("Rol5");
                //    db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
                db.execSQL("INSERT INTO roles_empleados(_id, ListID,Tipo,Rol1,Rol2,Rol3,Rol4,Rol5) " +
                        "VALUES(" + id + ",'" + ListID + "'," + Tipo + "," + Rol1 + "," + Rol2 + "," + Rol3 + "," + Rol4 + "," + Rol5 + ")");

            }


            // termina roles empleados

            conexionMySQL.close();
            db.close();
            Toast.makeText(getApplicationContext(), "Termino ", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    txterror.setText(e.getMessage());
        }


    }

}
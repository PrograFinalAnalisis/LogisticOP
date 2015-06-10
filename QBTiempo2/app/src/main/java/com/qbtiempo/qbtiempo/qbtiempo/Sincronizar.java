package com.qbtiempo.qbtiempo.qbtiempo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;

import java.util.*;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades.DBAdapter;
import com.qbtiempo.qbtiempo.qbtiempo.MultiColumnasListview.CargarAct_Adapter;


import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import clases.ActividadesSeleccionadas;
import clases.CargarActividadesRegistradas;


import static android.database.sqlite.SQLiteDatabase.openDatabase;

/**
 * Created by Programador on 17/04/2015.
 */
public class Sincronizar extends ActionBarActivity {
    private static String DB_PATH = "/data/data/com.qbtiempo.qbtiempo.qbtiempo/databases/";
    static Connection conexionMySQL;
    private DBAdapter myAdapBD;
    //    String NombreEmp = "", NombreAct = "", Horas = "", Fecha = "", id = "";
    static java.sql.Connection connMySQL;
    final ArrayList<Map<String, CargarActividadesRegistradas>> mylist = new ArrayList<Map<String, CargarActividadesRegistradas>>();
    final Map<String, CargarActividadesRegistradas> map = new TreeMap<String, CargarActividadesRegistradas>();
    //HashMap<String, String> map = new HashMap<String, String>();
    final LinkedList<CargarActividadesRegistradas> list2 = new LinkedList<CargarActividadesRegistradas>();
    CargarActividadesRegistradas ActRegis2;
    //String host, usuario, clave, base, puerto;
    //  ArrayList<String> valor;
    //  ListView listV;
    //  List<String> ListNombres_Seleccionados;
    //   private TextView textView9;
    //  private StringBuilder seleccionados = new StringBuilder();
    private static String DB_NAME = "DB_QBTIEMPOS.sqlite";
    private ArrayList<HashMap<String, String>> list22;
    SQLiteDatabase db;

    GridView CargarActividades;
    int Consecutivo;
    String Fecha = "";
    String Horas = "";
    List<String> ListNombres_Seleccionados;
    String NombreAct = "";
    String NombreEmp = "";
    int b;
    String base;
    String clave;
    // SQLiteDatabase db;
    String host;
    String id = "";
    List<CargarActividadesRegistradas> items = new ArrayList<CargarActividadesRegistradas>();
    int j;
    List<ActividadesSeleccionadas> lisAct = new ArrayList<ActividadesSeleccionadas>();;
    ActividadesSeleccionadas ActSel = new ActividadesSeleccionadas();
    // private ArrayList<HashMap<String, String>> list22;
    ListView listV;

    int pos;
    String puerto;
    private StringBuilder seleccionados;
    private TextView textView10;
    private TextView textView9;
    String usuario;
    ArrayList<String> valor;
    int w;

    private StringBuilder CodigoID = new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sincronizar);


        /*
		 * Codigo de para validacion de logout y registro del broadcastreceiver
		 */
        IntentFilter intentFilter = new IntentFilter();
        intentFilter
                .addAction("com.qbtiempo.qbtiempo.qbtiempo.ACTION_LOGOUT");
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

        cargarConfiguracion();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            CodigoID.append(bundle.getString("id2"));
        }
        // CargarActividades = (GridView) findViewById(R.id.SCHEDULE);
        listV = (ListView) findViewById(R.id.SCHEDULE);
        textView9 = (TextView) findViewById(R.id.textView9);
        listV.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                //  AdapterView.OnItemClickListener local1 = new AdapterView.OnItemClickListener() {
                //   public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int position, long paramAnonymousLong) {
                CargarActividadesRegistradas item = (CargarActividadesRegistradas) listV.getAdapter().getItem(position);
                pos = lisAct.indexOf(item.getId());

                System.out.println("entro " + pos);
                if (pos >= 0) {
                    // Iterator localIterator = Sincronizar.this.lisAct.iterator();
                    //   while (localIterator.hasNext()) {
                    //    ActividadesSeleccionadas ActSel = (ActividadesSeleccionadas) localIterator.next();
                    //         if (ActSel.equals(ActSel.getId()) == true)
                    //  Sincronizar.this.lisAct.remove(ActSel);
                    //  PrintStream localPrintStream2 = System.out;
                    //  StringBuilder localStringBuilder2 = new StringBuilder();
                    // localPrintStream2.println("entro " + ActSel.getId());
                    //  textView9.setText(ActSel.getId());
                } else {


                    lisAct.add(new ActividadesSeleccionadas(item.getNombreEmpleado(), item.getNombreActividad(), item.getHoras(),
                            item.getFecha(), item.getId(), item.getDescripcion(), item.getEditSequence(), item.getListId(), item.getCodigoAprovador(),
                            item.getTxnID(), item.getCustomer_FullName(), item.getClass_FullName(), item.getPayrollItemWage_FullName(), item.getLinea(),
                            item.getCodigoEmpleado(), item.getCodigoCliente(), item.getCodigoServicio(), item.getCodigoNomina(), item.getCodigoClase(),
                            item.getCodigoEstado(), item.getCodigoDia(), item.getBillableStatus(), item.getPaquete(), item.getGrupo(), item.getCodigoCierre(),
                            item.getCodigoEstadoRevision(), item.getCompleta(), item.getCodigoRevision(), item.getCodigoRegistrador(), item.getDuracion(),
                            item.getFechaCreacion(), item.getFechaAprobacion()));
                }

            }


            //   listV.setOnItemClickListener(local1);
            // this.textView9.setText(" ");


        });
    }



    public void BtnSincronizar(View v) {
        try {
            listV.setAdapter(null);
            items.clear();
            //        StringBuilder localStringBuilder = new StringBuilder();
            db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);

            Cursor c = db.rawQuery("select Entity_FullName , ItemService_FullName ,Horas , Fecha , _id,Descripcion ,EditSequence ,  ListId ," +
                    "CodigoAprovador , TxnID , Customer_FullName ,Class_FullName ,PayrollItemWage_FullName ,  Linea, CodigoEmpleado , " +
                    "CodigoCliente , CodigoServicio , CodigoNomina , CodigoClase , CodigoEstado ,CodigoDia ,BillableStatus , Paquete , " +
                    "Grupo , CodigoCierre  ,CodigoEstadoRevision, Completa ,CodigoRevision,CodigoRegistrador  ," +
                    "Duracion,FechaCreacion,FechaAprobacion   FROM actividades where CodigoEmpleado ="+ CodigoID.toString()+" order by fecha desc", null);
            while (c.moveToNext()) {

                this.NombreEmp = c.getString(0);
                this.NombreAct = c.getString(1);
                this.Horas = c.getString(2);
                this.Fecha = c.getString(3);
                this.id = c.getString(4);

                items.add(new CargarActividadesRegistradas(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4),
                        c.getString(5), c.getString(6), c.getString(7), c.getString(8), c.getString(9), c.getString(10), c.getString(11), c.getString(12),
                        c.getString(13), c.getInt(14), c.getInt(15), c.getInt(16), c.getInt(17), c.getInt(18), c.getInt(19), c.getInt(20), c.getInt(21),
                        c.getInt(22), c.getInt(23), c.getInt(24), c.getInt(25), c.getInt(26), c.getInt(27), c.getInt(28), c.getFloat(29), c.getString(30),
                        c.getString(31)));


                CargarAct_Adapter adapter = new CargarAct_Adapter(this, items);
                listV.setAdapter(adapter);

            }
            db.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void BtnSeleccionarTodos(View v) {
        for (Map.Entry<String, CargarActividadesRegistradas> maps : map.entrySet()) {
            String clave = maps.getKey();
            CargarActividadesRegistradas valor = maps.getValue();
            System.out.println(clave + "  ->  " + valor.getNombreEmpleado() + valor.getNombreActividad());
        }
    }

    //---------------------------------------

    public void cargarConfiguracion() {
        try {


            SharedPreferences prefs =
                    getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
            host = prefs.getString("Conexion", "");
            clave = prefs.getString("Contrasena", "");
            int puerto1 = 3306;
            puerto1 = prefs.getInt("Puerto", 3306);
            puerto = Integer.toString(puerto1);
            usuario = prefs.getString("Usuario", "");
            base = prefs.getString("Catalogo", "");
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    public void BtnEnviar(View v) {
        try {
        //    ArrayList localArrayList = new ArrayList();
         //   this.ListNombres_Seleccionados = localArrayList;
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
            ActividadesSeleccionadas act = new ActividadesSeleccionadas();
            String str;
            ResultSet rs;
            Statement st = null;

            if (this.base != "") {
                StringBuilder localStringBuilder2 = new StringBuilder();
                str = "jdbc:mysql://" + this.host + ":" + this.puerto + "/" + this.base;
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                st = DriverManager.getConnection(str, this.usuario, this.clave).createStatement();

            }

 for (ActividadesSeleccionadas p : lisAct) {
     // ActSel = new ActividadesSeleccionadas();
     //    localIterator.next();
     rs = st.executeQuery("select max(Actividades) as Consecutivo from tb_consecutivos");
     //   while (true)
     if (rs.next()) {
         Consecutivo = rs.getInt("Consecutivo");

     }

     int il = Consecutivo + 1;

     st.execute("Insert into tb_actividades(Id,CodigoEmpleado,CodigoCliente,CodigoServicio,CodigoNomina,CodigoClase,Duracion," +
             "Fecha,CodigoEstado,Descripcion,ListId,CodigoAprovador,Customer_FullName,ItemService_FullName" +
             ",Class_FullName,PayrollItemWage_FullName,Entity_FullName,BillableStatus,CodigoCierre," +
             "CodigoEstadoRevision,Completa,CodigoRevision,CodigoRegistrador,Horas)" +
             " values(" + il + ",'" + p.getCodigoEmpleado() + "','" + p.getCodigoCliente() + "'," + "'"
             + p.getCodigoServicio() + "','" + p.getCodigoNomina() + "','" + p.getCodigoClase()
             + "','" + p.getDuracion() + "','" + p.getFecha() + "','" + p.getCodigoEstado() + "','"
             + p.getDescripcion() + "','" + p.getListId() + "','" + p.getCodigoAprovador() + "'," + "'"
             + p.getCustomer_FullName() + "','" + p.getNombreActividad() + "','" + p.getClass_FullName()
             + "','" + p.getPayrollItemWage_FullName() + "'," + "'" + p.getNombreEmpleado() + "','"
             + p.getBillableStatus() + "','" + p.getCodigoCierre() + "'," + "'" + p.getCodigoEstadoRevision()
             + "','" + p.getCompleta() + "','" + p.getCodigoRevision() + "','" + p.getCodigoRegistrador() + "'," + "'" + p.getHoras() + "')");

     st.execute("update tb_consecutivos set Actividades=" + il + " where idConse=" + 1 + "");
     //}
 }
        } catch (Exception e) {

            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    // }//"Error de conexion, revise la configuracion o verifique que el servidor esta encendido"


    public void BtnDeseleccionar(View v) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cargar_registro_actividades, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_logout) {
            Intent broadcastIntent = new Intent();
            broadcastIntent
                    .setAction("com.qbtiempo.qbtiempo.qbtiempo.ACTION_LOGOUT");
            sendBroadcast(broadcastIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}



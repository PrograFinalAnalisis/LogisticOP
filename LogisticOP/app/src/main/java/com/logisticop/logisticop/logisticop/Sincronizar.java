package com.logisticop.logisticop.logisticop;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.logisticop.logisticop.logisticop.MultiColumnasListview.CargarAct_Adapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import clases.ActividadesSeleccionadas;
import clases.CargarActividadesRegistradas;
import clases.Cliente;

import static android.database.sqlite.SQLiteDatabase.openDatabase;

/**
 * Created by Programador on 17/04/2015.
 */
public class Sincronizar extends ActionBarActivity {
    private static String DB_PATH = "/data/data/com.logisticop.logisticop.logisticop/databases/";
    static Connection conexionMySQL;



    public Spinner Sp_clientes;

    static java.sql.Connection connMySQL;
    final ArrayList<Map<String, CargarActividadesRegistradas>> mylist = new ArrayList<Map<String, CargarActividadesRegistradas>>();
    final Map<String, CargarActividadesRegistradas> map = new TreeMap<String, CargarActividadesRegistradas>();
    final LinkedList<CargarActividadesRegistradas> list2 = new LinkedList<CargarActividadesRegistradas>();
    boolean Estado = false, Datosmoviles = false, wifi = false;
    private static String DB_NAME = "DB_LOGISTICOP.sqlite";
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
    String host;
    String id = "";
    List<CargarActividadesRegistradas> items = new ArrayList<CargarActividadesRegistradas>();
    int j;
    List<ActividadesSeleccionadas> lisAct = new ArrayList<ActividadesSeleccionadas>();
    List<CargarActividadesRegistradas> lisActReg = new ArrayList<CargarActividadesRegistradas>();
    ActividadesSeleccionadas ActSel = new ActividadesSeleccionadas();
    // private ArrayList<HashMap<String, String>> list22;
    ListView listV;
    boolean Actualizado = false, Eliminado = false;
    int pos;
    String puerto;
    private StringBuilder seleccionados;
    private TextView textView10;
    private TextView textView9;
    String usuario;
    ArrayList<String> valor;
    int w;
    ActividadesSeleccionadas ids;
    private StringBuilder CodigoID = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sincronizar);
        try {

        /*
         * Codigo de para validacion de logout y registro del broadcastreceiver
		 */
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

                    Intent loginIntent = new Intent(context, MainActivity.class);
                    startActivity(loginIntent);
                    //unregisterReceiver(this);
                    finish();
                }
            }, intentFilter);
        } catch (OutOfMemoryError e) {
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Sp_clientes = (Spinner) findViewById(R.id.Sp_clientes);


        cargarClientes();


        //cargo la configuración del metodo SharedPreferences de que se guarda en un xml
        cargarConfiguracion();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            CodigoID.append(bundle.getString("id2"));
        }

        listV = (ListView) findViewById(R.id.SCHEDULE);
        textView9 = (TextView) findViewById(R.id.textView9);
        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {

                CargarActividadesRegistradas item = (CargarActividadesRegistradas) listV.getAdapter().getItem(position);
                //obtengo la posicion selccionada
                pos = lisAct.indexOf(item.getId());

                if (pos >= 0) {
                    //limpiao la lista
                    lisAct.clear();

                } else
                {
                    lisAct.clear();
                    //agrego el dato seleccionado a la lista
                    lisAct.add(new ActividadesSeleccionadas(item.getNombreEmpleado(), item.getNombreActividad(), item.getHoras(),
                            item.getFecha(), item.getId(), item.getDescripcion(), item.getEditSequence(), item.getListId(), item.getCodigoAprovador(),
                            item.getTxnID(), item.getCustomer_FullName(), item.getClass_FullName(), item.getPayrollItemWage_FullName(), item.getLinea(),
                            item.getCodigoEmpleado(), item.getCodigoCliente(), item.getCodigoServicio(), item.getCodigoNomina(), item.getCodigoClase(),
                            item.getCodigoEstado(), item.getCodigoDia(), item.getBillableStatus(), item.getPaquete(), item.getGrupo(), item.getCodigoCierre(),
                            item.getCodigoEstadoRevision(), item.getCompleta(), item.getCodigoRevision(), item.getCodigoRegistrador(), item.getDuracion(),
                            item.getFechaCreacion(), item.getFechaAprobacion()));
                }

            }
        });
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
                spcliente.add(new Cliente(filas.getString(1),filas.getInt(0), filas.getInt(2), filas.getInt(3)));
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




    public void BtnMisActividades(View v) {
        try {
            listV.setAdapter(null);
            items.clear();
            //limpio el adapter
            db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            //habro una conexion a la base de datos
            //creo y ejecuto un donde carga las actividades por el coidgo de la persona logueada y por orden de fecha en forma desendente

            Cursor c = db.rawQuery("select Entity_FullName , ItemService_FullName ,Horas , Fecha , _id,Descripcion ,EditSequence ,  ListId ," +
                    "CodigoAprovador , TxnID , Customer_FullName ,Class_FullName ,PayrollItemWage_FullName ,  Linea, CodigoEmpleado , " +
                    "CodigoCliente , CodigoServicio , CodigoNomina , CodigoClase , CodigoEstado ,CodigoDia ,BillableStatus , Paquete , " +
                    "Grupo , CodigoCierre  ,CodigoEstadoRevision, Completa ,CodigoRevision,CodigoRegistrador  ," +
                    "Duracion,FechaCreacion,FechaAprobacion   FROM actividades where CodigoEmpleado =" + CodigoID.toString() + " order by fecha desc", null);
            while (c.moveToNext()) {
                //recoorro el cursor para ver si trae algo
                this.NombreEmp = c.getString(0);
                this.NombreAct = c.getString(1);
                this.Horas = c.getString(2);
                this.Fecha = c.getString(3);
                this.id = c.getString(4);
                //agrego los valores que trago a una clase
                items.add(new CargarActividadesRegistradas(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4),
                        c.getString(5), c.getString(6), c.getString(7), c.getString(8), c.getString(9), c.getString(10), c.getString(11), c.getString(12),
                        c.getString(13), c.getInt(14), c.getInt(15), c.getInt(16), c.getInt(17), c.getInt(18), c.getInt(19), c.getInt(20), c.getInt(21),
                        c.getInt(22), c.getInt(23), c.getInt(24), c.getInt(25), c.getInt(26), c.getInt(27), c.getInt(28), c.getFloat(29), c.getString(30),
                        c.getString(31)));

                //creo el adaptador con los valores agregados
                CargarAct_Adapter adapter = new CargarAct_Adapter(this, items);
                listV.setAdapter(adapter);
                //y lo seteo

            }
            db.close();//cierro la conexion
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void BtnTodasActividades(View v)
    {
        //Se instancia un objeto de la clase IntentIntegrator
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        //Se procede con el proceso de scaneo
        scanIntegrator.initiateScan();
        //Mostrar();
    }

    public void Mostrar()
    {
 /**
        try {
            listV.setAdapter(null);
            items.clear();
            //  limpio los valores que esten en la lista
            db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            //habro conexion y ejecuto un cursor el cual trae las actividades que esten en la tabla de actvidades en orden desc
            Cursor c = db.rawQuery("select Entity_FullName , ItemService_FullName ,Horas , Fecha , _id,Descripcion ,EditSequence ,  ListId ," +
                    "CodigoAprovador , TxnID , Customer_FullName ,Class_FullName ,PayrollItemWage_FullName ,  Linea, CodigoEmpleado , " +
                    "CodigoCliente , CodigoServicio , CodigoNomina , CodigoClase , CodigoEstado ,CodigoDia ,BillableStatus , Paquete , " +
                    "Grupo , CodigoCierre  ,CodigoEstadoRevision, Completa ,CodigoRevision,CodigoRegistrador  ," +
                    "Duracion,FechaCreacion,FechaAprobacion   FROM actividades order by fecha desc", null);
            while (c.moveToNext()) {
                //recoorro el cursor para ver si trae algo
                this.NombreEmp = c.getString(0);
                this.NombreAct = c.getString(1);
                this.Horas = c.getString(2);
                this.Fecha = c.getString(3);
                this.id = c.getString(4);
                //agrego los valores que trago a una clase
                items.add(new CargarActividadesRegistradas(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4),
                        c.getString(5), c.getString(6), c.getString(7), c.getString(8), c.getString(9), c.getString(10), c.getString(11), c.getString(12),
                        c.getString(13), c.getInt(14), c.getInt(15), c.getInt(16), c.getInt(17), c.getInt(18), c.getInt(19), c.getInt(20), c.getInt(21),
                        c.getInt(22), c.getInt(23), c.getInt(24), c.getInt(25), c.getInt(26), c.getInt(27), c.getInt(28), c.getFloat(29), c.getString(30),
                        c.getString(31)));

                //creo el adaptador con los valores agregados
                CargarAct_Adapter adapter = new CargarAct_Adapter(this, items);
                listV.setAdapter(adapter);

                //lo seteo
            }
            db.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
     **/
    }


    public void cargarConfiguracion() {
        try {

            // creo un SharedPreferences para obtener el que ya esta almacendao en labase de datos
            //almaceno los valores para la conexion
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


    boolean Conexion() {
        //En este metodo verifico que si esta conectado por medio
        //de alguna red ya sea una red wifi o mivl
        ConnectivityManager manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo EstadoConexion = manager.getActiveNetworkInfo();
        if (EstadoConexion != null) {
            Datosmoviles = EstadoConexion.getType() == ConnectivityManager.TYPE_MOBILE;//obtine la información de la redes moviles. devuelve true o false
            wifi = EstadoConexion.getType() == ConnectivityManager.TYPE_WIFI;//obtine la informacion de la red wifi. devuelve true o false
            //si el estado de conexion esta conectado o esta conectandoce
            if (EstadoConexion.isConnected() || EstadoConexion.isConnectedOrConnecting()) {
                //si la inf}ormacion obtenidad de los datos wifi es true
                //significa que esta conectado y sino verifica la conexion movil
                //haber si esta conectado
                if (wifi == true) {
                    Estado = true;
                    Toast.makeText(
                            getApplicationContext(),
                            "Conectado por WiFi",
                            Toast.LENGTH_LONG).show();
                    enviar();//ejecuta el metodo de enviar los datos a la base de mysql
                }
                if (Datosmoviles == true) {
                    Estado = true;
                    Toast.makeText(
                            getApplicationContext(),
                            "Estas Conectado Datos Moviles  este proceso puede tardar mas",
                            Toast.LENGTH_LONG).show();
                    enviar();
                }

            }
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Estas Desconectado del WiFi  por favor conectece a una red. ",
                    Toast.LENGTH_LONG).show();
            Estado = false;
        }
        return Estado;
    }

    public void BtnEnviar(View v)
    {
        Conexion();
    }

    // }//"Error de conexion, revise la configuracion o verifique que el servidor esta encendido"
    public void enviar()
    {

        /**
        try {


            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
            ActividadesSeleccionadas act = new ActividadesSeleccionadas();
            String str;
            ResultSet rs;
            Statement st = null;

            if (this.base != "") {
                //usdo el drivers para la conexion con mysql con los datos obtenidos de SharedPreferences

                str = "jdbc:mysql://" + this.host + ":" + this.puerto + "/" + this.base;
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                st = DriverManager.getConnection(str, this.usuario, this.clave).createStatement();

            }
            //recorro la clase con un for each
            for (ActividadesSeleccionadas p : lisAct) {


                rs = st.executeQuery("select max(Actividades) as Consecutivo from tb_consecutivos");
                //asigno el valor obtenido a una variable
                if (rs.next()) {
                    Consecutivo = rs.getInt("Consecutivo");

                }
                //le sumo uno al consecutivo
                int IncrementalParaMysql = Consecutivo + 1;
                //inseto los valores en la base de datos ,utilizando el consecutivo
                //en la base de datos de mysql
                st.execute("Insert into tb_actividades(Id,CodigoEmpleado,CodigoCliente,CodigoServicio,CodigoNomina,CodigoClase,Duracion," +
                        "Fecha,CodigoEstado,Descripcion,ListId,CodigoAprovador,Customer_FullName,ItemService_FullName" +
                        ",Class_FullName,PayrollItemWage_FullName,Entity_FullName,BillableStatus,CodigoCierre," +
                        "CodigoEstadoRevision,Completa,CodigoRevision,CodigoRegistrador,Horas)" +
                        " values(" + IncrementalParaMysql + ",'" + p.getCodigoEmpleado() + "','" + p.getCodigoCliente() + "'," + "'"
                        + p.getCodigoServicio() + "','" + p.getCodigoNomina() + "','" + p.getCodigoClase()
                        + "','" + p.getDuracion() + "','" + p.getFecha() + "','" + p.getCodigoEstado() + "','"
                        + p.getDescripcion() + "','" + p.getListId() + "','" + p.getCodigoAprovador() + "'," + "'"
                        + p.getCustomer_FullName() + "','" + p.getNombreActividad() + "','" + p.getClass_FullName()
                        + "','" + p.getPayrollItemWage_FullName() + "'," + "'" + p.getNombreEmpleado() + "','"
                        + p.getBillableStatus() + "','" + p.getCodigoCierre() + "'," + "'" + p.getCodigoEstadoRevision()
                        + "','" + p.getCompleta() + "','" + p.getCodigoRevision() + "','" + p.getCodigoRegistrador() + "'," + "'" + p.getHoras() + "')");
                //actualizo el consecutivo en la tabla consecutivos
                st.execute("update tb_consecutivos set Actividades=" + IncrementalParaMysql + " where idConse=" + 1 + "");


                //habro conexion
                db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
                String text = Integer.toString(pos);

                String[] args = new String[]{text};
                //actualizo el campo EstadoInsercion  en la base de sqlite para saber que se inserto en mysql
                //este estado de insercion se usa para cuando el campo sea insertado  se podra eliminar esta campo
                ActualizarEstadoInsercion(Integer.parseInt(p.getId()));

                EliminarPorId(Integer.parseInt(p.getId()));
                //elimino las actividades donde el campo EstadoInsercion sea igual Insertado


                db.close();//cierro la conexion
                //actualizo el listview con los nuevos datos es decir refresco los datos del listview
                //usando el metodo de mostrar datos


                Mostrar();
                //}
            }
        } catch (Exception e) {
            //   Toast.makeText(getApplicationContext(), "Error de conexion, revise la configuracion o verifique que el servidor esta encendid ", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

   **/
    }
    //////////////////---------metodo para enviar todas las actividades
/**
    public void enviarTodasAct(View v)
    {
        try {


            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
            CargarActividadesRegistradas act = new CargarActividadesRegistradas();
            String str;
            ResultSet rs;
            Statement st = null;

            if (this.base != "") {
                //uso el driver para la conexion con mysql con los datos obtenidos de SharedPreferences
                str = "jdbc:mysql://" + this.host + ":" + this.puerto + "/" + this.base;
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                st = DriverManager.getConnection(str, this.usuario, this.clave).createStatement();

            }

            int contador = 0;

            while (items.size() > contador) {
                for (CargarActividadesRegistradas p : items) {


                    rs = st.executeQuery("select max(Actividades) as Consecutivo from tb_consecutivos");
                    //asigno el valor obtenido a una variable
                    if (rs.next()) {
                        Consecutivo = rs.getInt("Consecutivo");

                    }
                    //le sumo uno al consecutivo
                    int il = Consecutivo + 1;
                    //inseto todos los valores en la base de datos ,utilizando el consecutivo
                    //en la base de datos de mysql
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
                    //actualizo el consecutivo en la tabla consecutivos
                    st.execute("update tb_consecutivos set Actividades=" + il + " where idConse=" + 1 + "");

                    //actualizo el campo EstadoInsercion  en la base de sqlite para saber que se inserto en mysql
                    //habro conexion
                    db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
                    String text = Integer.toString(pos);

                    String[] args = new String[]{text};
                    //actualizo el campo EstadoInsercion  en la base de sqlite para saber que se inserto en mysql
                    //este estado de insercion se usa para cuando el campo sea insertado  se podra eliminar esta campo


                    ActualizarEstadoInsercion(Integer.parseInt(p.getId()));

                    EliminarPorId(Integer.parseInt(p.getId()));
                    //elimino las actividades donde el campo EstadoInsercion sea igual Insertado

                    db.close();
                    //cierro la conexion

                    contador = contador + 1;
                }
                items.clear();//limpio la lista una vez enviado los datos
                //actualizo el listview con los nuevos datos es decir refresco los datos del listview
                //usando el metodo de mostrar datos

                Mostrar();
                //}
            }
        } catch (Exception e) {
            //   Toast.makeText(getApplicationContext(), "Error de conexion, revise la configuracion o verifique que el servidor esta encendid ", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    **/
    ///-------------------------------fin del metodo de enviar todas las act


    public void EliminarPorId(int ids) {
        try {
            db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            //habro la conexion a sqlite
            //ejecuto un delete de las activades donde el valor de comlumna sea Insertqado
            db.execSQL("delete from actividades where EstadoInsercion='Insertado' and _id=" + ids);
            Eliminado = true;
            db.close();//cierro conexion
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            //   textView10.setText(e.getMessage());
        }

    }

    public void ActualizarEstadoInsercion(int pk) {


        try {
            db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            //habro la conexion a sqlite
            //ejecuto un update a la tabla actividades actualizando el valor a de la columna para
            //que sea Insertado .. estoo con el fin de una vez actualizado cuando se ejecute el metodo
            //se puedan borrar
            db.execSQL("UPDATE actividades " +
                    "SET EstadoInsercion='Insertado'" +
                    " WHERE _id=" + pk + "");
            Actualizado = true;
            db.close();//cierro conexion
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            //   textView10.setText(e.getMessage());
        }
    }



}



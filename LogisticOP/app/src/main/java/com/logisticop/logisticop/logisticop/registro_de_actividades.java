package com.logisticop.logisticop.logisticop;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import static android.database.sqlite.SQLiteDatabase.openDatabase;

/**
 * Created by Programador on 05/03/2015.
 */
public class registro_de_actividades extends ActionBarActivity {

    final int DATE_DIALOG_ID = 0;
    private static String DB_NAME = "DB_LOGISTICOP.sqlite";
    private static String DB_PATH = "/data/data/com.logisticop.logisticop.logisticop/databases/";

   // String Class_FullName;
   // String Customer_FullName;
   // String Entity_FullName;
   // int primary;
   // int ID_actividad;
   // int ID_clientes;
    //String ItemService_FullName;
   // public String NombreUsuario;
   // public ArrayList Nomina_arreglo;
   // String PayrollItemWage_FullName;
   // public Spinner Sp_actividad;
   // public Spinner Sp_clientes;
    //public Spinner Sp_departamento;
   // public Spinner Sp_horas;
   // public Spinner Sp_minutos;
   // public Spinner Sp_nomina;
    SQLiteDatabase db;
   // int id_departamento;
   // String id_horas;
    //String id_minutos;
   // int id_nomina = 0;
    //private StringBuilder mensaje = new StringBuilder();
   // private StringBuilder mensaje2 = new StringBuilder();
    //private StringBuilder MensajeRecivido = new StringBuilder();
   // private StringBuilder MensajeRecividoRegistro = new StringBuilder();
    //variables para el metodo actualizar
   // private StringBuilder Nombre = new StringBuilder();
   // private StringBuilder idEmpl = new StringBuilder();
   // private StringBuilder CodigoCliente = new StringBuilder();
   // private StringBuilder CodigoServicio = new StringBuilder();
   // private StringBuilder CodigoNomina = new StringBuilder();
   // private StringBuilder CodigoClase = new StringBuilder();
   // private StringBuilder Duracion = new StringBuilder();
   // private StringBuilder Fecha = new StringBuilder();
   // private StringBuilder descripcion = new StringBuilder();
   // private StringBuilder listID = new StringBuilder();
   // private StringBuilder customer_FullName2 = new StringBuilder();
   // private StringBuilder itemService_FullName2 = new StringBuilder();
  //  private StringBuilder class_FullName2 = new StringBuilder();
   // private StringBuilder payrollItemWage_FullName2 = new StringBuilder();
   // private StringBuilder CodigoRegistrador = new StringBuilder();
  //  private StringBuilder Horas = new StringBuilder();
  //  private StringBuilder idPrim = new StringBuilder();
  //  String ActlistID = "";
  //  String ActCodigoRegistrador = "";
    String ActCliente = "";
    String ActHoraInicio = "";
    String ActHoraFin = "";
    boolean Insertado;
 //   String ActFechaSinEspacioEnBlanco = "";
 //   private int pDay;
  //  private TextView pDisplayDate;
 //   private int pMonth;
     TextView textView10;
  //  private ImageButton pPickDate;
  //  private int pYear;
    private EditText nombreCliente;
    private EditText horaInicioEntrega;
    private EditText horaFinEntrega;








    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_de_actividades);
      
        /*
         * Codigo de para validacion de logout y registro del broadcastreceiver

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


                    Intent loginIntent = new Intent(//context, MainActivity.class);
                    startActivity(loginIntent);

                   // unregisterReceiver(this);
                    finish();
                }
            }, intentFilter);

**/

        nombreCliente = (EditText) findViewById(R.id.txtdescripcion);
        horaInicioEntrega = (EditText) findViewById(R.id.editText2);
        horaFinEntrega = (EditText) findViewById(R.id.editText);
        textView10 = (TextView) findViewById(R.id.textView10);
        //Button btnguardarDatos = (Button) findViewById(R.id.btnguardarDatos);
        //Button btnActualizar = (Button) findViewById(R.id.btnActualizar);


        /**
        txtdescripcion = (EditText) findViewById(R.id.txtdescripcion);
        textView10 = (TextView) findViewById(R.id.textView10);
        Button btnguardarDatos = (Button) findViewById(R.id.btnguardarDatos);
        Button btnActualizar = (Button) findViewById(R.id.btnActualizar);
        //Recupera parametros y los muestra en el TextView
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Sp_clientes = (Spinner) findViewById(R.id.Sp_clientes);
        Sp_departamento = (Spinner) findViewById(R.id.Sp_departamento);
        Sp_actividad = (Spinner) findViewById(R.id.Sp_actividad);
        Sp_nomina = (Spinner) findViewById(R.id.Sp_nomina);
        Sp_horas = (Spinner) findViewById(R.id.Sp_horas);
        Sp_minutos = (Spinner) findViewById(R.id.Sp_minutos);
        //  spinner1= (Spinner) findViewById(R.id.spinner1);

        Sp_clientes.setOnItemSelectedListener(new MyOnItemSelectedListener());
        Sp_departamento.setOnItemSelectedListener(new MyOnItemSelectedListener());
        Sp_actividad.setOnItemSelectedListener(new MyOnItemSelectedListener());
        Sp_nomina.setOnItemSelectedListener(new MyOnItemSelectedListener());
        Sp_horas.setOnItemSelectedListener(new MyOnItemSelectedListener());
        Sp_minutos.setOnItemSelectedListener(new MyOnItemSelectedListener());
        // spinner1.setOnItemSelectedListener(new MyOnItemSelectedListener());
        //Sp_clientes = (Spinner)findViewById(R.id.Sp_clientes);


        /// estos metodos cargan todos los spinners del formulario dependiendo de su nombre
        cargarClientes();
        cargarDepartamento();
        cargarActividad();
        cargarNomina();
        cargarHoraS();
        cargarMinutos();
        /// cargo toda los dato para la fecha
        pDisplayDate = (TextView) findViewById(R.id.lbseleccionarFecha);
        pPickDate = (ImageButton) findViewById(R.id.btnfecha);
        pPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);// muestra el calendar en pantalla
            }
        });
        final Calendar cal = Calendar.getInstance();
        pYear = cal.get(Calendar.YEAR);
        pMonth = cal.get(Calendar.MONTH);
        pDay = cal.get(Calendar.DAY_OF_MONTH);


        updateDisplay(); //actualizo la fecha seleccionada

        if (bundle != null) {// si no se recive datos de algun formulario nno hara nada
            MensajeRecivido.append(bundle.getString("Activo"));// recive la variable bundle Activo
            MensajeRecividoRegistro.append(bundle.getString("Mensaje"));// recive la variable bundlee Mensaje
            if (MensajeRecivido.toString().equals("ActivoNormal")) {// si el mensaje recibido es ActivoNormal
                //entrar en el metodo de insertar actiividades normales
                mensaje.append(bundle.getString("id"));
                // mensaje2.append("Número entero: \r\n");
                mensaje2.append(bundle.getString("NombreUsuario"));

                txtid_em.setTextSize(33);
                txtid_em.setText(mensaje);
                txtnombre_empl.setText(mensaje2);
                txtid_em.setVisibility(View.INVISIBLE); //lo hago invisible
                btnActualizar.setVisibility(View.INVISIBLE);//el boton de actualizar es invisible por que eta en modo insertar

            } else {  //Convierto el formulario en actualizar
                if (MensajeRecividoRegistro.toString().equals("Actualizar")) {// si recibe el mensaje  Actualizar
                    //entrar en modo actualizar
                    //y los valores obtenidos seran los de la actividad seleccionada
                    ///actualizar
                    idEmpl.append(bundle.getString("CodigoEmpleado"));
                    Nombre.append(bundle.getString("entity_FullName2"));
                    CodigoCliente.append(bundle.getInt("CodigoCliente"));
                    CodigoServicio.append(bundle.getInt("CodigoServicio"));
                    CodigoNomina.append(bundle.getInt("CodigoNomina"));
                    CodigoClase.append(bundle.getInt("CodigoClase"));
                    Duracion.append(bundle.getFloat("Duracion"));
                    Fecha.append(bundle.getString("Fecha"));
                    descripcion.append(bundle.getString("descripcion"));
                    listID.append(bundle.getString("listID"));
                    customer_FullName2.append(bundle.getString("customer_FullName2"));
                    itemService_FullName2.append(bundle.getString("itemService_FullName2"));
                    class_FullName2.append(bundle.getString("class_FullName2"));
                    payrollItemWage_FullName2.append(bundle.getString("payrollItemWage_FullName2"));
                    CodigoRegistrador.append(bundle.getInt("CodigoRegistrador"));
                    Horas.append(bundle.getString("Horas"));
                    idPrim.append(bundle.getInt("idPrim"));

                    txtid_em.setText(idEmpl);
                    txtnombre_empl.setText(Nombre);
                    txtid_em.setVisibility(View.INVISIBLE); //lo hago invisible
                    btnguardarDatos.setVisibility(View.INVISIBLE);//hago invisible el boton insertar
                    //divido la horas para pasarlo a los spinners
                    String dataHoras = Horas.toString();
                    //como la hora ya estaba concatenada se tiene que dividir en 2 por eso la spliteo
                    String s[] = dataHoras.split(":");
                    //asigno los valores guardado en a los spinners
                    PosicionDatoSpinner(Sp_horas, s[0]);
                    PosicionDatoSpinner(Sp_minutos, s[1]);
                    //termina de splitear
                    //------------------------asigno las actividades que fueron selecciondas y almacenadas en la base de datos
                    PosicionDatoSpinner(Sp_departamento, class_FullName2.toString());
                    PosicionDatoSpinner(Sp_actividad, itemService_FullName2.toString());
                    PosicionDatoSpinner(Sp_clientes, customer_FullName2.toString());
                    PosicionDatoSpinner(Sp_nomina, payrollItemWage_FullName2.toString());

                    String dataFecha = Fecha.toString();
                    String f[] = dataFecha.split("-");
                    //paso la fechha guarda de la actividad en el label
                    pYear = Integer.parseInt(f[0]);
                    pMonth = Integer.parseInt(f[1]);
                    pDay = Integer.parseInt(f[2]);

                    //sino tiene descripcion le seteo una
                    if (descripcion.toString() == "") {
                        txtdescripcion.setText("Sin Descripción");
                    } else {
                        txtdescripcion.setText(descripcion.toString());
                    }
                    //  textView10.setText(f[0] + f[1] + f[2]);

                    //le paso los valores a las variables globales
                    ID_clientes = Integer.parseInt(CodigoCliente.toString());
                    ID_actividad = Integer.parseInt(CodigoServicio.toString());
                    id_nomina = Integer.parseInt(CodigoNomina.toString());
                    id_departamento = Integer.parseInt(CodigoClase.toString());
                    Customer_FullName = customer_FullName2.toString();
                    ItemService_FullName = itemService_FullName2.toString();
                    Class_FullName = class_FullName2.toString();
                    PayrollItemWage_FullName = payrollItemWage_FullName2.toString();
                    Entity_FullName = Nombre.toString();
                    primary = Integer.parseInt(idPrim.toString());
                    ActCodigoRegistrador = CodigoRegistrador.toString();
                    ActHoraFin = Horas.toString();
                    ActHoraInicio = Duracion.toString();
                    if (Fecha != null) {
                        pDisplayDate.setText("");
                        pDisplayDate.setText(Fecha.toString());
                    }

                    ActCliente = txtdescripcion.getText().toString();
                    ActlistID = listID.toString();

                    //asigne todoas las variables para poder usarlas en el formulario
                }
            }

        }
 */

    }



   public void btnguardar_eventoOnclick(View v)      throws ParseException
    {

        ActCliente = nombreCliente.getText().toString();
        ActHoraInicio = horaInicioEntrega.getText().toString();
        ActHoraFin = horaFinEntrega.getText().toString();
        //ejecuto el metodo de insertar
        Insertar_Cliente_Sqlite(ActCliente,Integer.parseInt(ActHoraInicio), Integer.parseInt(ActHoraFin));
        //valido si se inserto
        if (Insertado == true)
        {
            Toast.makeText(this, "Insertado con exito", Toast.LENGTH_LONG).show();
            textView10.setText("Insertado con exito");
        }
    }



    //metodo de insertar este metodo ejecuta un insert a la tabla de actividades
    public void Insertar_Cliente_Sqlite(String pNombreCliente, int pHoraInicio, int pHoraSalida)
    {
        try {
            db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            //habro conexion y ejecuto el INSERT
            db.execSQL("INSERT INTO clientes(Nombre,horaInicio,horaFin)"
                    + " VALUES('"+pNombreCliente+"'," + pHoraInicio + "," + pHoraSalida + ")");
            Insertado = true;

            db.close();//cierro conexion
            db.close();
        } catch (Exception e)
        {

            Toast.makeText(getApplicationContext(),
                    "Error ", Toast.LENGTH_LONG);
        }
    }



}

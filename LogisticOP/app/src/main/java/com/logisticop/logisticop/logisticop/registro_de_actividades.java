package com.logisticop.logisticop.logisticop;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TimeZone;

import clases.Cliente;
import clases.Horas;
import clases.Minutos;
import clases.actividad;
import clases.departamento;
import clases.nomina;

import static android.database.sqlite.SQLiteDatabase.openDatabase;

/**
 * Created by Programador on 05/03/2015.
 */
public class registro_de_actividades extends ActionBarActivity {

    final int DATE_DIALOG_ID = 0;
    private static String DB_NAME = "DB_LOGISTICOP.sqlite";
    private static String DB_PATH = "/data/data/com.logisticop.logisticop.logisticop/databases/";
    String Class_FullName;
    String Customer_FullName;
    String Entity_FullName;
    int primary;
    int ID_actividad;
    int ID_clientes;
    String ItemService_FullName;
    public String NombreUsuario;
    public ArrayList Nomina_arreglo;
    String PayrollItemWage_FullName;
    public Spinner Sp_actividad;
    public Spinner Sp_clientes;
    public Spinner Sp_departamento;
    public Spinner Sp_horas;
    public Spinner Sp_minutos;
    public Spinner Sp_nomina;
    SQLiteDatabase db;
    int id_departamento;
    String id_horas;
    String id_minutos;
    int id_nomina = 0;
    private StringBuilder mensaje = new StringBuilder();
    private StringBuilder mensaje2 = new StringBuilder();
    private StringBuilder MensajeRecivido = new StringBuilder();
    private StringBuilder MensajeRecividoRegistro = new StringBuilder();
    //variables para el metodo actualizar
    private StringBuilder Nombre = new StringBuilder();
    private StringBuilder idEmpl = new StringBuilder();
    private StringBuilder CodigoCliente = new StringBuilder();
    private StringBuilder CodigoServicio = new StringBuilder();
    private StringBuilder CodigoNomina = new StringBuilder();
    private StringBuilder CodigoClase = new StringBuilder();
    private StringBuilder Duracion = new StringBuilder();
    private StringBuilder Fecha = new StringBuilder();
    private StringBuilder descripcion = new StringBuilder();
    private StringBuilder listID = new StringBuilder();
    private StringBuilder customer_FullName2 = new StringBuilder();
    private StringBuilder itemService_FullName2 = new StringBuilder();
    private StringBuilder class_FullName2 = new StringBuilder();
    private StringBuilder payrollItemWage_FullName2 = new StringBuilder();
    private StringBuilder CodigoRegistrador = new StringBuilder();
    private StringBuilder Horas = new StringBuilder();
    private StringBuilder idPrim = new StringBuilder();
    String ActlistID = "";
    String ActCodigoRegistrador = "";
    String ActDescripcion = "";
    String ActDuracion = "";
    String ActHoras = "";
    boolean Insertado;
    String ActFechaSinEspacioEnBlanco = "";
    private int pDay;
    private TextView pDisplayDate;
    private int pMonth;
    TextView textView10;
    private ImageButton pPickDate;
    private int pYear;
    private EditText txtdescripcion;
    private TextView txtid_em, txtid_cliente;
    private EditText txtnombre_empl;

    /// este metodo muetra el calendar
    private DatePickerDialog.OnDateSetListener pDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    pYear = year;
                    pMonth = monthOfYear;
                    pDay = dayOfMonth;
                    updateDisplay();
                    displayToast();
                }
            };


    private void updateDisplay() {
        pDisplayDate.setText(
                new StringBuilder()
                        // Month is 0 based so add 1

                        .append(pMonth + 1).append("-")
                        .append(pDay).append("-")
                        .append(pYear).append(" "));
    }


    private void displayToast() {

        //Toast.makeText(this, new StringBuilder().append("Fecha elegida es ").append(pDisplayDate.getText()), Toast.LENGTH_SHORT).show();

    }
///------termina el metodo del calendar

    public String ConvertirFecha(String paramString1, String paramString2, String paramString3) {
        try {
            SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat(paramString2);
            localSimpleDateFormat1.setTimeZone(TimeZone.getTimeZone("UTC"));
            SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat(paramString3.trim());
            String str = localSimpleDateFormat2.format(localSimpleDateFormat1.parse(paramString1));
            return str;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return "";
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_de_actividades);
      
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

                   // unregisterReceiver(this);
                    finish();
                }
            }, intentFilter);



        txtid_em = (TextView) findViewById(R.id.txtid_em);
        txtnombre_empl = (EditText) findViewById(R.id.txtnombre_empl);
        txtid_cliente = (TextView) findViewById(R.id.TXTID_CLIENT);
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
                    ActHoras = Horas.toString();
                    ActDuracion = Duracion.toString();
                    if (Fecha != null) {
                        pDisplayDate.setText("");
                        pDisplayDate.setText(Fecha.toString());
                    }

                    ActDescripcion = txtdescripcion.getText().toString();
                    ActlistID = listID.toString();

                    //asigne todoas las variables para poder usarlas en el formulario
                }
            }

        }


    }

    public void PosicionDatoSpinner(Spinner spin, String text) {//con este metodo asigno los valores de la base de datos y los muestro en los spinner
        for (int i = 0; i < spin.getAdapter().getCount(); i++) {
            if (spin.getAdapter().getItem(i).toString().equalsIgnoreCase(text)) {
                spin.setSelection(i);
            }
        }

    }

    public class MyOnItemSelectedListener implements OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            //si se selecciono algun spinner segun sea el spinner sera los valores obtenidos
            if (parent.getId() == R.id.Sp_clientes) {
                ID_clientes = ((Cliente) parent.getItemAtPosition(pos)).getId_clientes();
                Customer_FullName = ((Cliente) parent.getItemAtPosition(pos)).getNombreQB();

            }
            if (parent.getId() == R.id.Sp_departamento) {
                id_departamento = ((departamento) parent.getItemAtPosition(pos)).getId_departamento();
                Class_FullName = ((departamento) parent.getItemAtPosition(pos)).getClass_FullName();
            }
            if (parent.getId() == R.id.Sp_actividad) {
                ID_actividad = ((actividad) parent.getItemAtPosition(pos)).getId_actividad();
                //	Customer_FullName = ((actividad) parent.getItemAtPosition(pos)).getCustomer_FullName();
                ItemService_FullName = ((actividad) parent.getItemAtPosition(pos)).getItemService_FullName();

                //	Entity_FullName = ((actividad) parent.getItemAtPosition(pos)).getEntity_FullName();
            }
            if (parent.getId() == R.id.Sp_nomina) {
                id_nomina = ((nomina) parent.getItemAtPosition(pos)).getId_nomina();
                PayrollItemWage_FullName = ((nomina) parent.getItemAtPosition(pos)).getPayrollItemWage_FullName();
            }
            if (parent.getId() == R.id.Sp_horas) {
                id_horas = ((clases.Horas) parent.getItemAtPosition(pos)).getHoras();

            }
            if (parent.getId() == R.id.Sp_minutos) {
                id_minutos = ((Minutos) parent.getItemAtPosition(pos)).getMinutos();

            }

            //Podemos hacer varios ifs o un switchs por si tenemos varios spinners.
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Do nothing.
        }
    }

    public void cargarDepartamento() {// tabla clases
        try {

            db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            //habro la conexion la base
            //ejecuto un select
            Cursor filasDep = db.rawQuery("select _id, FullName from  clases", null);

            LinkedList<departamento> Dep = new LinkedList<departamento>();
            //creo un objecto de la clase de departamento

            while (filasDep.moveToNext()) {
                Dep.add(new departamento(filasDep.getInt(0), filasDep.getString(1)));
                //agrego los valores obtenidos del cursor a la clase departamento
                //Creamos el adaptador
            }
            ArrayAdapter<departamento> spinner_adapter = new ArrayAdapter<departamento>(this, android.R.layout.simple_spinner_item, Dep);
            //muestro los valores en el spinners
            //Añadimos el layout para el menú y se lo damos al spinner
            spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Sp_departamento.setAdapter(spinner_adapter);
            db.close();//cierro conexion

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    public void cargarActividad() {
        try {

            db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            //habro la conexion la base
            //ejecuto un select
            Cursor filasActividad = db.rawQuery("select _id, FullName from  servicios", null);

            LinkedList<actividad> Act = new LinkedList<actividad>();
            //creo un objecto de la clase de actividad
            while (filasActividad.moveToNext()) {
                Act.add(new actividad(filasActividad.getInt(0),
                        filasActividad.getString(1)));
                //agrego los valores obtenidos del cursor a la clase departamento
                //Creamos el adaptador
            }

            ArrayAdapter<actividad> spinner_adapter = new ArrayAdapter<actividad>(this, android.R.layout.simple_spinner_item, Act);
            //Añadimos el layout para el menú y se lo damos al spinner
            spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Sp_actividad.setAdapter(spinner_adapter);
            db.close();//cierro conexion

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void cargarNomina() {
        try {

            db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            //habro la conexion la base
            //ejecuto un select
            Cursor filasnom = db.rawQuery("select _id, FullName from  nomina", null);

            Spinner Sp_nomina = (Spinner) findViewById(R.id.Sp_nomina);
            LinkedList<nomina> nom = new LinkedList<nomina>();
            //creo un objecto de la clase de nomina
            while (filasnom.moveToNext()) {
                nom.add(new nomina(filasnom.getInt(0), filasnom.getString(1)));
                //agrego los valores obtenidos del cursor a la clase departamento
                //Creamos el adaptador

            }
            ArrayAdapter<nomina> spinner_adapter = new ArrayAdapter<nomina>(this, android.R.layout.simple_spinner_item, nom);
            //Añadimos el layout para el menú y se lo damos al spinner
            spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Sp_nomina.setAdapter(spinner_adapter);
            db.close();//cierro conexion

        } catch (Exception e) {
        }
    }

    public void cargarClientes() {

        try {

            db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            Cursor filas = db.rawQuery("select _id, NombreCompleto,NombreQB from  clientes", null);
            //habro la conexion la base
            //ejecuto un select
            Spinner Sp_clientes3 = (Spinner) findViewById(R.id.Sp_clientes);
            LinkedList<Cliente> spcliente = new LinkedList<Cliente>();
            //creo un objecto de la clase de Cliente
            while (filas.moveToNext()) {
                spcliente.add(new Cliente(filas.getInt(0), filas.getString(1), filas.getString(2)));
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

    public void cargarHoraS() {

        try {

            db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            Cursor filas = db.rawQuery("select  hhoras from horas", null);
            //habro la conexion la base
            //ejecuto un select
            LinkedList<clases.Horas> arrayhoras = new LinkedList<clases.Horas>();
            //creo un objecto de la clase de Horas
            while (filas.moveToNext()) {
                arrayhoras.add(new Horas(filas.getString(0)));
                //agrego los valores obtenidos del cursor a la clase departamento
                //Creamos el adaptador
            }
            ArrayAdapter<clases.Horas> spinner_adapter = new ArrayAdapter<clases.Horas>(this, android.R.layout.simple_spinner_item, arrayhoras);
            //Añadimos el layout para el menú y se lo damos al spinner
            spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Sp_horas.setAdapter(spinner_adapter);
            db.close();//cierro conexion

        } catch (Exception e) {

            // 	Toast.makeText(this, "Error de conexión, revise la configuración o verifique que el servidor esté encendido", Toast.LENGTH_LONG).show();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

    public void cargarMinutos() {

        try {

            db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            Cursor filas = db.rawQuery("select  mminutos from minutos", null);
            //habro la conexion la base
            //ejecuto un select
            LinkedList<Minutos> arrayminutos = new LinkedList<Minutos>();
            //creo un objecto de la clase de Minutos
            while (filas.moveToNext()) {
                arrayminutos.add(new Minutos(filas.getString(0)));
                //agrego los valores obtenidos del cursor a la clase departamento
                //Creamos el adaptador
            }
            ArrayAdapter<Minutos> spinner_adapter = new ArrayAdapter<Minutos>(this, android.R.layout.simple_spinner_item, arrayminutos);
            //Añadimos el layout para el menú y se lo damos al spinner
            spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Sp_minutos.setAdapter(spinner_adapter);
            db.close();//cierro conexion
            //	AdapterHoras=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Arreglo_horas);
        } catch (Exception e) {
            // 	Toast.makeText(this, "Error de conexión, revise la configuración o verifique que el servidor esté encendido", Toast.LENGTH_LONG).show();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }


    public void BtnActualizar(View v) throws ParseException {

        ActDescripcion = txtdescripcion.getText().toString();
        ActDuracion = id_horas + '.' + this.id_minutos;//vuelvo a concatenar la duracion
        ActHoras = this.id_horas + ':' + this.id_minutos;
        new SimpleDateFormat("yyyy-MM-dd");
        db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        //obtnego el _id,listID,NombreQB de la tabla empleados
        String[] DatosObt = new String[3];
        DatosObt[0] = "_id";
        DatosObt[1] = "ListID";
        DatosObt[2] = "NombreQB";
        String[] ObtIDEmpl = new String[1];
        ObtIDEmpl[0] = this.txtid_em.getText().toString();
        Cursor c = this.db.query("empleados", DatosObt, "_id=?", ObtIDEmpl, null, null, null);
        while (c.moveToNext()) {
            //estos datos no traian nada al recibir los valores de el formulario actualizar
            //usara los del query sino usa los mismos que trae
            if (ActCodigoRegistrador == "") {
                ActCodigoRegistrador = c.getString(0);
            } else {

            }

            if (ActlistID == "") {
                ActlistID = c.getString(1);
            } else {

            }

            if (Entity_FullName == "") {
                Entity_FullName = c.getString(2);
            } else {

            }


        }
        //inicio a formatear la fecha
        String fechaInicio = pYear + "-" + (pMonth + 1) + "-" + pDay;

        String dateInString = fechaInicio;//"2015-04-11";  // Start date

        ConvertirFecha(fechaInicio, "MM/dd/yyyy", "yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cl = Calendar.getInstance();
        cl.setTime(sdf.parse(dateInString));
        cl.add(Calendar.DATE, 0);
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date resultdate = new Date(cl.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        char palabraBuscar = '0';
        for (int j = 0; j < dateInString.length(); j++) {
            if (dateInString.charAt(0) == palabraBuscar) {
            /* convertimos el String a un array de caracteres */
                char[] tmp = dateInString.toCharArray();
                /* cambiamos el carácter en la posición deseada */
                tmp[0] = ' ';
                /* reconvertimos a String */
                dateInString = new String(tmp);
                //textView8.setText("La palabra que esta buscando es "+"dateInString  "+dateInString+" "+dateInString.charAt(0)+"---" + palabraBuscar);
                break;
            } else {
                dateInString = sdf.format(resultdate);
            }
        }
        StringTokenizer stTexto = new StringTokenizer(dateInString);

        while (stTexto.hasMoreElements()) {
            ActFechaSinEspacioEnBlanco += stTexto.nextElement();
        }
        //termino de formatear la fecha
        //ejecuto el metodo de actualizar con los valores obtenidos o actualizados(Es decir si selecciona otra clase o departamento o cambia la descripcion)
        ActualizarDatos(Integer.parseInt(ActCodigoRegistrador), ID_clientes, ID_actividad, id_nomina,
                id_departamento, ActDuracion, ActFechaSinEspacioEnBlanco, ActDescripcion, ActlistID, Customer_FullName,
                ItemService_FullName, Class_FullName, PayrollItemWage_FullName, Entity_FullName,
                Integer.parseInt(ActCodigoRegistrador), ActHoras, primary);
        //valido que alla insertado
        if (Insertado == true) {
            Toast.makeText(this, "Actualizado con exito", Toast.LENGTH_LONG).show();
            textView10.setText("Actualizado con exito");
        }
    }

    public void btnguardar_eventoOnclick(View v)
            throws ParseException {
        String listID = "";
        String CodigoRegistrador = "";
        String Descripcion = txtdescripcion.getText().toString();
        String Duracion = id_horas + '.' + this.id_minutos;
        String Horas = this.id_horas + ':' + this.id_minutos;
        new SimpleDateFormat("yyyy-MM-dd");
        //habro conexion
        db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        //datos a buscar
        String[] ArrayDatosEmpleados = new String[3];
        ArrayDatosEmpleados[0] = "_id";
        ArrayDatosEmpleados[1] = "ListID";
        ArrayDatosEmpleados[2] = "NombreQB";
        //el valor que se usa para buscar la informacion del empleado
        String[] ArrayID = new String[1];
        ArrayID[0] = this.txtid_em.getText().toString();
        Cursor c = this.db.query("empleados", ArrayDatosEmpleados, "_id=?", ArrayID, null, null, null);
        while (c.moveToNext()) {
            CodigoRegistrador = c.getString(0);
            listID = c.getString(1);
            this.Entity_FullName = c.getString(2);
        }
        //inicio a formatear la fecha
        String fechaInicio = pYear + "-" + (pMonth + 1) + "-" + pDay;
        String dateInString = fechaInicio;//"2015-04-11";  // Start date
        ConvertirFecha(fechaInicio, "MM/dd/yyyy", "yyyy-MM-dd");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cl = Calendar.getInstance();
        cl.setTime(sdf.parse(dateInString));
        cl.add(Calendar.DATE, 0);

        sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date resultdate = new Date(cl.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        char palabraBuscar = '0';

        for (int j = 0; j < dateInString.length(); j++) {

            if (dateInString.charAt(0) == palabraBuscar) {
            /* convertimos el String a un array de caracteres */
                char[] tmp = dateInString.toCharArray();

                /* cambiamos el carácter en la posición deseada */
                tmp[0] = ' ';

                /* reconvertimos a String */
                dateInString = new String(tmp);
                //textView8.setText("La palabra que esta buscando es "+"dateInString  "+dateInString+" "+dateInString.charAt(0)+"---" + palabraBuscar);
                break;
            } else {

                dateInString = sdf.format(resultdate);
            }
        }
        StringTokenizer stTexto = new StringTokenizer(dateInString);
        String sCadenaSinBlancos = "";
        while (stTexto.hasMoreElements()) {
            sCadenaSinBlancos += stTexto.nextElement();
        }
        //termino de formatear la fecha
        //ejecuto el metodo de insertar
        Insertar_Actividad_Sqlite(Integer.parseInt(this.txtid_em.getText().toString()), ID_clientes, ID_actividad, id_nomina,
                id_departamento, Duracion, sCadenaSinBlancos, Descripcion, listID, Customer_FullName, ItemService_FullName, Class_FullName,
                PayrollItemWage_FullName, Entity_FullName, Integer.parseInt(CodigoRegistrador), Horas);
        //valido si se inserto
        if (Insertado == true) {
            Toast.makeText(this, "Insertado con exito", Toast.LENGTH_LONG).show();
            textView10.setText("Insertado con exito");
        }
    }

    //metodo de actualizar este metodo ejecuta un update a la tabla de actividades
    //segun sea la actividad seleccionada
    // se actualiza con la llave primaria que obtenego del formulario que se selcciona la actividad a actualizar
    public void ActualizarDatos(int CodigoEmpleado, int CodigoCliente, int CodigoServicio, int CodigoNomina, int CodigoClase,
                                String Duracion, String Fecha, String descripcion, String listID, String customer_FullName2,
                                String itemService_FullName2, String class_FullName2, String payrollItemWage_FullName2, String entity_FullName2
            , int CodigoRegistrador, String Horas, int idPrim) {


        try {
            db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            //habro conexion y ejecuto el update
            db.execSQL("UPDATE actividades " +
                    "SET CodigoEmpleado =" + CodigoEmpleado + ",CodigoCliente =" + CodigoCliente + ",CodigoServicio=" + CodigoServicio +
                    ",CodigoNomina =" + CodigoNomina + ",CodigoClase =" + CodigoClase + ",Duracion ='" + Duracion + "'," +
                    "Fecha ='" + Fecha + "',Descripcion ='" + descripcion + "',ListId ='" + listID + "'" +
                    ",Customer_FullName ='" + customer_FullName2 + "',ItemService_FullName='" + itemService_FullName2 + "'," +
                    "Class_FullName='" + class_FullName2 + "',PayrollItemWage_FullName='" + payrollItemWage_FullName2 + "'," +
                    " Entity_FullName ='" + entity_FullName2 + "',CodigoRegistrador =" + CodigoRegistrador + ",Horas ='" + Horas + "'" +
                    " WHERE _id=" + idPrim + "");
            Insertado = true;// este no es el mejor metodo para validacion
            //lo mejor seria que haga un select a la base
            //este funciona por el momento
            db.close();//cierro conexion
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            //   textView10.setText(e.getMessage());
        }
    }

    //metodo de insertar este metodo ejecuta un insert a la tabla de actividades
    public void Insertar_Actividad_Sqlite(int CodigoEmpleado, int CodigoCliente, int CodigoServicio, int CodigoNomina, int CodigoClase,
                                          String Duracion, String Fecha, String descripcion, String listID, String customer_FullName2,
                                          String itemService_FullName2, String class_FullName2, String payrollItemWage_FullName2, String entity_FullName2
            , int CodigoRegistrador, String Horas) {


        try {
            db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            //habro conexion y ejecuto el INSERT
            db.execSQL("INSERT INTO actividades(Linea,CodigoEmpleado,CodigoCliente,"
                    + "CodigoServicio,CodigoNomina ,CodigoClase,Duracion,"
                    + "Fecha ,CodigoEstado,CodigoDia,Descripcion,EditSequence,"
                    + "ListId,CodigoAprovador,TxnID,Customer_FullName,ItemService_FullName ,"
                    + "Class_FullName,PayrollItemWage_FullName ,Entity_FullName,BillableStatus,"
                    + "Paquete,Grupo,CodigoCierre,CodigoRevision,CodigoRegistrador,"
                    + "FechaCreacion,FechaAprobacion ,Horas,Completa ,CodigoEstadoRevision)"
                    + " VALUES(NULL," + CodigoEmpleado + "," + CodigoCliente + "," + CodigoServicio + "," + CodigoNomina + "," + CodigoClase + ",'" + Duracion + "','" + Fecha + "',"
                    + "2,NULL,'" + descripcion + "',NULL,'" + listID + "',0,NULL,'" + customer_FullName2 + "','" + itemService_FullName2 + "','" + class_FullName2 + "'"
                    + ",'" + payrollItemWage_FullName2 + "','" + entity_FullName2 + "',1,Null,NULL,0,0," + CodigoRegistrador + ",null,NULL,'" + Horas + "',1,0)");
            Insertado = true;
            // este no es el mejor metodo para validacion
            //lo mejor seria que haga un select a la base
            //este funciona por el momento
            db.close();//cierro conexion
            db.close();
        } catch (Exception e) {

            Toast.makeText(getApplicationContext(),
                    "Error ", Toast.LENGTH_LONG);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cargar_registro_actividades, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.menu_crear) {
            Intent intent = null;
            intent = new Intent(registro_de_actividades.this, Settings.class);
            startActivity(intent);

            return true;
        }
        //ejecuto el metodo de cerrar session
        if (id == R.id.action_logout) {
            Intent broadcastIntent = new Intent();
            broadcastIntent
                    .setAction("com.logisticop.logisticop.logisticop.ACTION_LOGOUT");
            sendBroadcast(broadcastIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        pDateSetListener,
                        pYear, pMonth, pDay);
        }
        return null;
    }


}

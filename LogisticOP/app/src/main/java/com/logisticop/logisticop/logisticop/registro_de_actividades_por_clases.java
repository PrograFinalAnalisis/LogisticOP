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
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TimeZone;

import clases.Cliente;
import clases.Horas;
import clases.ListEmpleados;
import clases.Minutos;
import clases.actividad;
import clases.departamento;
import clases.nomina;

import static android.database.sqlite.SQLiteDatabase.openDatabase;

/**
 * Created by Programador on 05/03/2015.
 */
public class registro_de_actividades_por_clases extends ActionBarActivity {
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





        /*
        try {

          Codigo de para validacion de logout y registro del broadcastreceiver

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

                    finish();
                }
            }, intentFilter);
        }catch ( OutOfMemoryError e){
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        txtdescripcion = (EditText) findViewById(R.id.txtdescripcion);
        //Recupera parametros y los muestra en el TextView
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {

            CodigoEmpleado.append(bundle.getString("id"));
        }

        textView8 = (TextView) findViewById(R.id.textView8);
        //Lv_Empleados = (ListView) findViewById(R.id.Lv_Empleados);
        Lv_Empleados.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        Sp_clientes = (Spinner) findViewById(R.id.Sp_clientes);
        Sp_departamento = (Spinner) findViewById(R.id.Sp_departamento);
        Sp_actividad = (Spinner) findViewById(R.id.Sp_actividad);
        Sp_nomina = (Spinner) findViewById(R.id.Sp_nomina);
        Sp_horas = (Spinner) findViewById(R.id.Sp_horas);
        Sp_minutos = (Spinner) findViewById(R.id.Sp_minutos);
        //Sp_Grupo = (Spinner) findViewById(R.id.Sp_Grupo);

        //les defino el emtodo listener
        Sp_clientes.setOnItemSelectedListener(new MyOnItemSelectedListener());
        Sp_departamento.setOnItemSelectedListener(new MyOnItemSelectedListener());
        Sp_actividad.setOnItemSelectedListener(new MyOnItemSelectedListener());
        Sp_nomina.setOnItemSelectedListener(new MyOnItemSelectedListener());
        Sp_horas.setOnItemSelectedListener(new MyOnItemSelectedListener());
        Sp_minutos.setOnItemSelectedListener(new MyOnItemSelectedListener());
        Sp_Grupo.setOnItemSelectedListener(new MyOnItemSelectedListener());

        //cargo los spinners
        cargarClientes();
        cargarDepartamento();
        cargarActividad();
        cargarNomina();
        cargarHoraS();
        cargarMinutos();
        cargarGrupo();

        /// cargo toda los dato para la fecha
        pDisplayDate = (TextView) findViewById(R.id.lbseleccionarFecha);
        pPickDate = (ImageButton) findViewById(R.id.btnfecha);

        //pDisplayDate2 = (TextView) findViewById(R.id.lbelegir);
       // pPickDate2 = (ImageButton) findViewById(R.id.btnfecha2);

        //muestra los dos calendar

        pPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        pPickDate2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID2);
            }
        });

        final Calendar cal = Calendar.getInstance();
        pYear = cal.get(Calendar.YEAR);
        pMonth = cal.get(Calendar.MONTH);
        pDay = cal.get(Calendar.DAY_OF_MONTH);

        final Calendar cal2 = Calendar.getInstance();
        pYear2 = cal2.get(Calendar.YEAR);
        pMonth2 = cal2.get(Calendar.MONTH);
        pDay2 = cal2.get(Calendar.DAY_OF_MONTH);

        updateDisplay();
        updateDisplay2();
        */
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
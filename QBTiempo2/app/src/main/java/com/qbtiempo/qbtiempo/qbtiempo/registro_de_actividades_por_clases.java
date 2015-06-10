package com.qbtiempo.qbtiempo.qbtiempo;

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
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades.DBAdapter;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
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
    private static String DB_PATH = "/data/data/com.qbtiempo.qbtiempo.qbtiempo/databases/";
    private DBAdapter myAdapBD;
    private static String DB_NAME = "DB_QBTIEMPOS.sqlite";
    SQLiteDatabase db;

    public String[] Arreglo_horas,Arreglo_minutos,Arreglo_nombres;
    List<String> ListNombres_Seleccionados;
    public Spinner Sp_horas,Sp_minutos,Sp_departamento,Sp_clientes,Sp_actividad,Sp_nomina,Sp_Grupo;
    public ArrayList deparamento_aareglo,clientes_arreglo,actividades_arreglo,Nomina_arreglo;
    public  String NombreUsuario,id_cliente2;
    private static final String LOGTAG = "LogsAndroid";
    public int id_cli,id_dep ;
    private TextView pDisplayDate,pDisplayDate2;
    private ImageButton pPickDate,pPickDate2;
    private int pYear,pYear2;
    private int pMonth,pMonth2;
    private int pDay,pDay2;
    float totalDuracion;
    private String selectedCountry = null;
    private String selectedAnimal = null;
    int ID_clientes,id_departamento,ID_actividad,id_nomina =0;
    String id_horas;
    String grupo,id_minutos,Customer_FullName,ItemService_FullName,Class_FullName,PayrollItemWage_FullName,Entity_FullName;
    ArrayList<Cliente> listMesa = new ArrayList<Cliente>();
    private StringBuilder mensaje = new StringBuilder();
    private StringBuilder CodigoEmpleado = new StringBuilder();
    static final int DATE_DIALOG_ID = 0;
    static final int DATE_DIALOG_ID2 = 1;
    private TextView txtid_em,txtid_cliente,textView8;
    public Date fechaSelec;
    private EditText txtnombre_empl,txtdescripcion;
    private ListView Lv_Empleados;
    TextView seleccionado;
    private StringBuilder seleccionados = new StringBuilder();
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
                        // Mes está  basado en 0 por lo que añadi 1

                        .append(pMonth + 1).append("-")
                        .append(pDay).append("-")
                        .append(pYear).append(" "));
    }


    private void displayToast() {

        Toast.makeText(this, new StringBuilder().append("Fecha elegida es ").append(pDisplayDate.getText()), Toast.LENGTH_SHORT).show();

    }
 //-------------------------

    private DatePickerDialog.OnDateSetListener pDateSetListener2 =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {pYear2 = year; pMonth2 = monthOfYear; pDay2 = dayOfMonth; updateDisplay2(); displayToast2();
                }
            };


    private void updateDisplay2() {
        pDisplayDate2.setText(
                new StringBuilder()
                        // Month is 0 based so add 1

                        .append(pMonth2 + 1).append("-")
                        .append(pDay2).append("-")
                        .append(pYear2).append(" "));
    }


    private void displayToast2() {

        Toast.makeText(this, new StringBuilder().append("Fecha elegida es ").append(pDisplayDate2.getText()), Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_de_actividades_por_clases);

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

        txtdescripcion =  (EditText) findViewById( R.id.txtdescripcion );
        //Recupera parametros y los muestra en el TextView
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {

            CodigoEmpleado.append(bundle.getString("id"));
        }

        textView8 = (TextView) findViewById(R.id.textView8) ;
        Lv_Empleados = (ListView)findViewById(R.id.Lv_Empleados);
        Lv_Empleados.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        Sp_clientes = (Spinner) findViewById(R.id.Sp_clientes);
        Sp_departamento = (Spinner) findViewById(R.id.Sp_departamento);
        Sp_actividad = (Spinner) findViewById(R.id.Sp_actividad);
        Sp_nomina = (Spinner) findViewById(R.id.Sp_nomina);
        Sp_horas= (Spinner) findViewById(R.id.Sp_horas);
        Sp_minutos= (Spinner) findViewById(R.id.Sp_minutos);
        Sp_Grupo =(Spinner) findViewById(R.id.Sp_Grupo);
        //  spinner1= (Spinner) findViewById(R.id.spinner1);

        Sp_clientes.setOnItemSelectedListener(new MyOnItemSelectedListener());
        Sp_departamento.setOnItemSelectedListener(new MyOnItemSelectedListener());
        Sp_actividad.setOnItemSelectedListener(new MyOnItemSelectedListener());
        Sp_nomina.setOnItemSelectedListener(new MyOnItemSelectedListener());
        Sp_horas.setOnItemSelectedListener(new MyOnItemSelectedListener());
        Sp_minutos.setOnItemSelectedListener(new MyOnItemSelectedListener());
        Sp_Grupo.setOnItemSelectedListener(new MyOnItemSelectedListener());
        // spinner1.setOnItemSelectedListener(new MyOnItemSelectedListener());
        //Sp_clientes = (Spinner)findViewById(R.id.Sp_clientes);

        cargarClientes();
        cargarDepartamento();
        cargarActividad();
        cargarNomina();
        cargarHoraS();
        cargarMinutos();
        cargarGrupo();


       // lv = (ListView)findViewById(R.id.list);


        /// referencio a la clase metodos

    /*    Metodos met = new Metodos();
        met.cargarNomina(Sp_nomina);*/


        /// cargo toda los dato para la fecha
        pDisplayDate = (TextView) findViewById(R.id.lbseleccionarFecha);
        pPickDate = (ImageButton) findViewById(R.id.btnfecha);

        pDisplayDate2 = (TextView) findViewById(R.id.lbelegir);
        pPickDate2 = (ImageButton) findViewById(R.id.btnfecha2);


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
    }

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {

            if (parent.getId() == R.id.Sp_clientes) {
                ID_clientes = ((Cliente) parent.getItemAtPosition(pos)).getId_clientes();
                Customer_FullName = ((Cliente) parent.getItemAtPosition(pos)).getNombreQB();

            }
            if (parent.getId() == R.id.Sp_departamento) {
                id_departamento = ((departamento) parent.getItemAtPosition(pos)).getId_departamento();
                Class_FullName= ((departamento) parent.getItemAtPosition(pos)).getClass_FullName();
            }
            if (parent.getId() == R.id.Sp_actividad) {
                ID_actividad = ((actividad) parent.getItemAtPosition(pos)).getId_actividad();
                //	Customer_FullName = ((actividad) parent.getItemAtPosition(pos)).getCustomer_FullName();
                ItemService_FullName  = ((actividad) parent.getItemAtPosition(pos)).getItemService_FullName();

                //	Entity_FullName = ((actividad) parent.getItemAtPosition(pos)).getEntity_FullName();
            }
            if (parent.getId() == R.id.Sp_nomina)
            {
                id_nomina = ((nomina) parent.getItemAtPosition(pos)).getId_nomina();
                PayrollItemWage_FullName = ((nomina) parent.getItemAtPosition(pos)).getPayrollItemWage_FullName();
            }
            if (parent.getId() == R.id.Sp_horas)
            {
                id_horas = ((Horas) parent.getItemAtPosition(pos)).getHoras();

            }
            if (parent.getId() == R.id.Sp_minutos)
            {
                id_minutos = ((Minutos) parent.getItemAtPosition(pos)).getMinutos();

            }
            if (parent.getId() == R.id.Sp_Grupo)
            {
                Grupo();
            }
            //Podemos hacer varios ifs o un switchs por si tenemos varios spinners.
        }
        public void onNothingSelected(AdapterView<?> parent) {
            // Do nothing.
        }
    }
    public void Grupo(){
        try {

            //-------- se cargan los clientes asinagdos a la clases
            db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            String text = Sp_Grupo.getSelectedItem().toString();

            String[] args = new String[] {text};

            Cursor c = db.rawQuery("SELECT NombreCompleto,e._id  as idempleado FROM empleados e" +
                    "                INNER JOIN clases c on e.CodigoClase = c._Id" +
                    "                where c.FullName =? ", args);
            final LinkedList<ListEmpleados> list = new LinkedList<ListEmpleados>();
            while(c.moveToNext()){
                list.add(new ListEmpleados(c.getString(0),c.getString(1)));
           //     String id = c.getString(1);
            }
            ArrayAdapter<ListEmpleados> adaptador = new ArrayAdapter<ListEmpleados>(this, android.R.layout.simple_list_item_multiple_choice, list);
            Lv_Empleados.setAdapter(adaptador);

            seleccionado = (TextView)findViewById(R.id.seleccionado);

            ListNombres_Seleccionados = new ArrayList<String>();
            Lv_Empleados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    String tmpseleccionar = "";
                    ListEmpleados ids;
                    int pos =-1,j=0;
                   // String ids;
                    tmpseleccionar = (String) Lv_Empleados.getItemAtPosition(position).toString();
                   ids = (ListEmpleados) Lv_Empleados.getItemAtPosition(position);
                  //  ids.getId();
                            pos = seleccionados.indexOf(ids.getId());
                    if (pos >= 0){

                        seleccionados.delete(pos, pos + ids.getId().length());
                        for (j= 0 ; j < ListNombres_Seleccionados.size(); j++){
                        if (ListNombres_Seleccionados.get(j).equalsIgnoreCase(ids.getId()) == true){
                            ListNombres_Seleccionados.remove(j);
                         }
                        }

                    }
                    else {
                        seleccionados.append(ids.getId());
                        ListNombres_Seleccionados.add(ids.getId().toString());
                    }

                 /*   seleccionado.setText("Cantidad: "+ seleccionados);
                    for(int i=0 ; i < ListNombres_Seleccionados.size(); i++) {
                        textView8.setText("Nombres{"+i+"}" + ListNombres_Seleccionados.get(i));
                    }
*/

                    /*
                    for(int i=0 ; i < Lv_Empleados.getAdapter().getCount(); i++)
                    {
                        // Lv_Empleados.setItemChecked(i, true);
                        seleccionado.setText("Has seleccionado: " + list);
                    }*/
                }
            });
            seleccionado.setText(" ");
            textView8.setText("");
       }catch(Exception e){
          Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

       }
    }
    public void  cargarDepartamento(){
        try
        {
            db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            Cursor filasDep = db.rawQuery("select _id, FullName from  clases", null);
            LinkedList<departamento> Dep = new LinkedList<departamento>();
            //La poblamos con los ejemplos
            while(filasDep.moveToNext()){
                Dep.add(new departamento(filasDep.getInt(0),filasDep.getString(1)));
            }
            ArrayAdapter<departamento> spinner_adapter = new ArrayAdapter<departamento>(this, android.R.layout.simple_spinner_item, Dep);
            spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Sp_departamento.setAdapter(spinner_adapter);
        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }
    public void  cargarActividad(){
        try
        {
            db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            Cursor filasActividad = db.rawQuery("select _id, FullName from  servicios", null);
            LinkedList<actividad> Act = new LinkedList<actividad>();
            while(filasActividad.moveToNext()){
                Act.add(new actividad(filasActividad.getInt(0),
                        filasActividad.getString(1)));
            }
            ArrayAdapter<actividad> spinner_adapter = new ArrayAdapter<actividad>(this, android.R.layout.simple_spinner_item, Act);
            spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Sp_actividad.setAdapter(spinner_adapter);
        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void  cargarNomina(){
        try
        {
            db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            Cursor filasnom = db.rawQuery("select _id, FullName from  nomina", null);
            Spinner Sp_nomina = (Spinner)findViewById(R.id.Sp_nomina);
            LinkedList<nomina> nom = new LinkedList<nomina>();
            while(filasnom.moveToNext()){
                nom.add(new nomina(filasnom.getInt(0),filasnom.getString(1)));
            }
            ArrayAdapter<nomina> spinner_adapter = new ArrayAdapter<nomina>(this, android.R.layout.simple_spinner_item, nom);
            //A�adimos el layout para el men� y se lo damos al spinner
            spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Sp_nomina.setAdapter(spinner_adapter);

        }catch(Exception e){}
    }
    public void cargarClientes(){

        try{
            db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            Cursor filas = db.rawQuery("select _id, NombreCompleto,NombreQB from  clientes", null);
            Spinner Sp_clientes3 = (Spinner)findViewById(R.id.Sp_clientes);
            LinkedList<Cliente> comidas = new LinkedList<Cliente>();
            while(filas.moveToNext()){
                comidas.add(new Cliente(filas.getInt(0),filas.getString(1),filas.getString(2)));
            }
            ArrayAdapter<Cliente> spinner_adapter = new ArrayAdapter<Cliente>(this, android.R.layout.simple_spinner_item, comidas);
            //A�adimos el layout para el men� y se lo damos al spinner
            spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Sp_clientes3.setAdapter(spinner_adapter);
            //	AdapterHoras=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Arreglo_horas);
        }catch(Exception e) {
            // TODO: handle exception
            // 	Toast.makeText(this, "Error de conexi�n, revise la configuraci�n o verifique que el servidor est� encendido", Toast.LENGTH_LONG).show();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }//FIN cargarClientes
    public void cargarHoraS() {

        try{

            db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            Cursor filas = db.rawQuery("select  hhoras from horas", null);

            LinkedList<Horas> arrayhoras = new LinkedList<Horas>();
            //La poblamos con los ejemplos
            while(filas.moveToNext()){
                arrayhoras.add(new Horas(filas.getString(0)));

                //Creamos el adaptador
            }
            ArrayAdapter<Horas> spinner_adapter = new ArrayAdapter<Horas>(this, android.R.layout.simple_spinner_item, arrayhoras);
            //A�adimos el layout para el men� y se lo damos al spinner
            spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Sp_horas.setAdapter(spinner_adapter);

            //	AdapterHoras=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Arreglo_horas);
        }catch(Exception e) {
            // TODO: handle exception
            // 	Toast.makeText(this, "Error de conexi�n, revise la configuraci�n o verifique que el servidor est� encendido", Toast.LENGTH_LONG).show();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }
    public void cargarMinutos() {

        try{

            db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            Cursor filas = db.rawQuery("select  mminutos from minutos", null);

            LinkedList<Minutos> arrayminutos = new LinkedList<Minutos>();
            //La poblamos con los ejemplos
            while(filas.moveToNext()){
                arrayminutos.add(new Minutos(filas.getString(0)));

                //Creamos el adaptador
            }
            ArrayAdapter<Minutos> spinner_adapter = new ArrayAdapter<Minutos>(this, android.R.layout.simple_spinner_item, arrayminutos);
            //A�adimos el layout para el men� y se lo damos al spinner
            spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Sp_minutos.setAdapter(spinner_adapter);

            //	AdapterHoras=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Arreglo_horas);
        }catch(Exception e) {
            // 	Toast.makeText(this, "Error de conexi�n, revise la configuraci�n o verifique que el servidor est� encendido", Toast.LENGTH_LONG).show();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    //////////---------------------
////Se carga ek spinner el cual al seleccionar carga los empleados por clase
    public void  cargarGrupo(){
        try
        {
            db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            Cursor filasDep = db.rawQuery("select _id, FullName from  clases", null);
            LinkedList<departamento> Dep = new LinkedList<departamento>();
            //La poblamos con los ejemplos
            while(filasDep.moveToNext()){
                Dep.add(new departamento(filasDep.getInt(0),filasDep.getString(1)));
            }
            ArrayAdapter<departamento> spinner_adapter = new ArrayAdapter<departamento>(this, android.R.layout.simple_spinner_item, Dep);
            //A�adimos el layout para el men� y se lo damos al spinner
            spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item
            );
            Sp_Grupo.setAdapter(spinner_adapter);


        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    public String ConvertirFecha(String Fecha, String DesdeFormato, String alFormato){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(DesdeFormato);
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            SimpleDateFormat SDF_Formato = new SimpleDateFormat(alFormato.trim());
            return SDF_Formato.format(sdf.parse(Fecha));
        }catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    int cont = 0,j=0;      boolean vdd = false;

    public void BtnGrupo(View v)  throws ParseException {
        //Boton  Guardar
        String duracion = "",duracionHoras = "";
        String ListID = "",_id = "" ;
        String descripcion = "";
        descripcion   = txtdescripcion.getText().toString();
        duracion = id_horas + '.' +id_minutos;
        duracionHoras = id_horas + ':' +id_minutos;

        int i =0, CantidadDias =0;
        db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);


        /* Obtenemos la cantidad de dias
        *
        * */

        Calendar c1 = Calendar.getInstance();
        //fecha inicio
        Calendar NuevafechaInicio = new GregorianCalendar();
        NuevafechaInicio.set(pYear,(pMonth + 1),pDay);
        //fecha fin
        Calendar NuevafechaFin = new GregorianCalendar();

        NuevafechaFin.set(pYear2,(pMonth2 + 1),pDay2);
        //restamos las fechas como se puede ver son de tipo Calendar,
        //debemos obtener el valor long con getTime.getTime.

        c1.setTimeInMillis(NuevafechaFin.getTime().getTime() - NuevafechaInicio.getTime().getTime());

        //la resta provoca que guardamos este valor en c,
        //los milisegundos corresponde al tiempo en dias
        //asi sabemos cuantos dias

      //

        CantidadDias =c1.get(Calendar.DAY_OF_YEAR);
        for (int m= 0; m < CantidadDias;m++){
        ///-----------------------------------------

        String fechaInicio = pYear+"-" + (pMonth +1) +"-"+ pDay;
        String fechaFinal = (pMonth2+1)+"/"+ (pDay2+1)+"/"+pYear2 ;//al mes se le suma uno por que la variable esta en 0 y al dia sele suma uno para cuando se hace el if  salga

        String dateInString = fechaInicio;//"2015-04-11";  // Start date
        String fechaf = fechaFinal;///"4/15/2015";  // Start date

        ConvertirFecha(fechaf, "MM/dd/yyyy", "yyyy-MM-dd");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cl = Calendar.getInstance();
        cl.setTime(sdf.parse(dateInString));
        cl.add(Calendar.DATE,cont);

        sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date resultdate = new Date(cl.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        char palabraBuscar = '0';

        for(int j=0; j < dateInString.length(); j++ ){

            if( dateInString.charAt(0)== palabraBuscar){
            /* convertimos el String a un array de caracteres */
                char[] tmp = dateInString.toCharArray();

                /* cambiamos el carácter en la posición deseada */
                tmp[0] = ' ';

                /* reconvertimos a String */
                dateInString = new String(tmp);
                //textView8.setText("La palabra que esta buscando es "+"dateInString  "+dateInString+" "+dateInString.charAt(0)+"---" + palabraBuscar);
                break;
            }
            else{

                dateInString = sdf.format(resultdate);
            }
        }
        StringTokenizer stTexto = new StringTokenizer(dateInString);
        String sCadenaSinBlancos="";
        while (stTexto.hasMoreElements())
            sCadenaSinBlancos += stTexto.nextElement();

        if (sCadenaSinBlancos.equals(fechaf)){
            textView8.setText("insertado");
            Toast.makeText(getApplicationContext(),
                    "Inserto ", Toast.LENGTH_LONG);

        }
        else {
            // textView8.setText("Fecha mas un día:" + dateInString);
            for( i=0 ; i < ListNombres_Seleccionados.size(); i++) {
                String[] campos = new String[] {"_id", "ListID", "NombreQB"};
                String[] args = new String[] {ListNombres_Seleccionados.get(i).toString()};

                Cursor c = db.query("empleados", campos, "_id=?", args, null, null, null);

                //Nos aseguramos de que existe al menos un registro
                while(c.moveToNext()){
                    //Recorremos el cursor hasta que no haya más registros
                    do {
                        _id= c.getString(0);
                        ListID = c.getString(1);
                        Entity_FullName = c.getString(2);
                    } while(c.moveToNext());
                }

                //----------------------
                Insertar_Actividad_Sqlite(Integer.parseInt(ListNombres_Seleccionados.get(i)), ID_clientes, ID_actividad,
                        id_nomina, id_departamento, duracion, sCadenaSinBlancos, descripcion, ListID, Customer_FullName,
                        ItemService_FullName, Class_FullName, PayrollItemWage_FullName, Entity_FullName,
                        Integer.parseInt(CodigoEmpleado.toString()), duracionHoras);

            }
            cont = cont + 1;

        }

         if(cont == CantidadDias){
             textView8.setText("Insertado con exito");
            }
            else
         {
             textView8.setText("No se pudo Insertar");
         }

        ///----------------------------

        Toast.makeText(getApplicationContext(),
                "Inserto ", Toast.LENGTH_LONG);
        //  myAdapBD.cerrar();
        //	} catch (Exception e) {
       }
        ////	Toast.makeText(getApplicationContext(),
        //		"Error ", Toast.LENGTH_LONG);
        //}
    }



    public void Insertar_Actividad_Sqlite(int CodigoEmpleado,int CodigoCliente,int CodigoServicio,int CodigoNomina ,int CodigoClase,
                                          String Duracion,String Fecha ,String descripcion,String listID,String customer_FullName2,
                                          String itemService_FullName2 ,String class_FullName2,String payrollItemWage_FullName2 ,String entity_FullName2
            ,int CodigoRegistrador,String Horas) {


        try   {
            db =  openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            db.execSQL("INSERT INTO actividades(Linea,CodigoEmpleado,CodigoCliente,"
                    + "CodigoServicio,CodigoNomina ,CodigoClase,Duracion,"
                    + "Fecha ,CodigoEstado,CodigoDia,Descripcion,EditSequence,"
                    + "ListId,CodigoAprovador,TxnID,Customer_FullName,ItemService_FullName ,"
                    + "Class_FullName,PayrollItemWage_FullName ,Entity_FullName,BillableStatus,"
                    + "Paquete,Grupo,CodigoCierre,CodigoRevision,CodigoRegistrador,"
                    + "FechaCreacion,FechaAprobacion ,Horas,Completa ,CodigoEstadoRevision)"
                    + " VALUES(NULL,"+ CodigoEmpleado +","+ CodigoCliente +","+ CodigoServicio +","+CodigoNomina+","+CodigoClase+",'"+Duracion+"','"+Fecha+"',"
                    + "2,NULL,'"+descripcion+"',NULL,'"+listID+"',0,NULL,'"+ customer_FullName2 +"','"+itemService_FullName2+"','"+class_FullName2+"'"
                    + ",'"+payrollItemWage_FullName2+"','"+entity_FullName2+"',1,Null,NULL,0,0,"+CodigoRegistrador+",null,NULL,'"+Horas+"',1,0)");

        } catch (Exception e) {

            Toast.makeText(getApplicationContext(),
                    "Error ", Toast.LENGTH_LONG);
        }
    }
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        pDateSetListener,
                        pYear, pMonth, pDay);
            case DATE_DIALOG_ID2:
                return new DatePickerDialog(this,
                        pDateSetListener2,
                        pYear2, pMonth2, pDay2);
        }
        return null;
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
            intent = new Intent(registro_de_actividades_por_clases.this, Settings.class);
            startActivity(intent);

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
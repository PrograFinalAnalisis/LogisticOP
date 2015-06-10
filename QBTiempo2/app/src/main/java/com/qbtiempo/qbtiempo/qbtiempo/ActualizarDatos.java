package com.qbtiempo.qbtiempo.qbtiempo;


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
import android.widget.ListView;
import android.widget.Toast;

import com.qbtiempo.qbtiempo.qbtiempo.MultiColumnasListview.CargarAct_Adapter;

import java.util.ArrayList;
import java.util.List;

import clases.ActividadesSeleccionadas;
import clases.CargarActividadesRegistradas;

import static android.database.sqlite.SQLiteDatabase.openDatabase;


public class ActualizarDatos extends ActionBarActivity {
    private static String DB_NAME = "DB_QBTIEMPOS.sqlite";
    private static String DB_PATH = "/data/data/com.qbtiempo.qbtiempo.qbtiempo/databases/";
    List<CargarActividadesRegistradas> items = new ArrayList<CargarActividadesRegistradas>();
    List<ActividadesSeleccionadas> lisAct = new ArrayList<ActividadesSeleccionadas>();
    int cant;
    String codigoCliente;
    private static final String LOGTAG = "LogsAndroid";
    SQLiteDatabase db;
    ListView listV;
    String Mensaje = "Actualizar";

    private StringBuilder CodigoCliente = new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_datos);
        listV = (ListView) findViewById(R.id.listV);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            CodigoCliente.append(bundle.getString("id3"));
        }
        //evento click de listview

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

        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                //envio las variables al activity

                if (cant == 0) {
                    CargarActividadesRegistradas item = (CargarActividadesRegistradas) listV.getAdapter().getItem(position);
                    lisAct.add(new ActividadesSeleccionadas(item.getIdprim(),item.getNombreEmpleado(), item.getNombreActividad(), item.getHoras(),
                            item.getFecha(), item.getId(), item.getDescripcion(), item.getEditSequence(), item.getListId(), item.getCodigoAprovador(),
                            item.getTxnID(), item.getCustomer_FullName(), item.getClass_FullName(), item.getPayrollItemWage_FullName(), item.getLinea(),
                            item.getCodigoEmpleado(), item.getCodigoCliente(), item.getCodigoServicio(), item.getCodigoNomina(), item.getCodigoClase(),
                            item.getCodigoEstado(), item.getCodigoDia(), item.getBillableStatus(), item.getPaquete(), item.getGrupo(), item.getCodigoCierre(),
                            item.getCodigoEstadoRevision(), item.getCompleta(), item.getCodigoRevision(), item.getCodigoRegistrador(), item.getDuracion(),
                            item.getFechaCreacion(), item.getFechaAprobacion()));
                    cant = 1;
                } else {

                   // Toast.makeText(getApplicationContext(), "Deseleccionado", Toast.LENGTH_SHORT).show();
                    listV.clearChoices();
                    listV.requestLayout();
                    //lisAct.clear();
                    CargarActividadesRegistradas item = (CargarActividadesRegistradas) listV.getAdapter().getItem(position);
                    lisAct.add(new ActividadesSeleccionadas(item.getIdprim(), item.getNombreEmpleado(), item.getNombreActividad(), item.getHoras(),
                            item.getFecha(), item.getId(), item.getDescripcion(), item.getEditSequence(), item.getListId(), item.getCodigoAprovador(),
                            item.getTxnID(), item.getCustomer_FullName(), item.getClass_FullName(), item.getPayrollItemWage_FullName(), item.getLinea(),
                            item.getCodigoEmpleado(), item.getCodigoCliente(), item.getCodigoServicio(), item.getCodigoNomina(), item.getCodigoClase(),
                            item.getCodigoEstado(), item.getCodigoDia(), item.getBillableStatus(), item.getPaquete(), item.getGrupo(), item.getCodigoCierre(),
                            item.getCodigoEstadoRevision(), item.getCompleta(), item.getCodigoRevision(), item.getCodigoRegistrador(), item.getDuracion(),
                            item.getFechaCreacion(), item.getFechaAprobacion()));
                    cant = 1;
                 /*   for (int i = 0; i < listV.getAdapter().getCount(); i++) {
                        if (listV.getAdapter().getItem(i).toString().equalsIgnoreCase(text)) {
                            spin.setSelection(i);
                        }
                    }*/
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cargar_registro_actividades, menu);
        return true;
    }

    public void btnEditar(View v) {
        if (cant == 1) {
            for (ActividadesSeleccionadas p : lisAct) {

                Bundle bundle = new Bundle();
                bundle.putString("Mensaje", Mensaje);
                //   bundle.putString("NombreUsuario" , NombreUsuario );
                bundle.putString("CodigoEmpleado", p.getId());
                bundle.putInt("CodigoCliente", p.getCodigoCliente());
                bundle.putInt("CodigoServicio", p.getCodigoServicio());
                bundle.putInt("CodigoNomina", p.getCodigoNomina());
                bundle.putInt("CodigoClase", p.getCodigoClase());
                bundle.putFloat("Duracion", p.getDuracion());
                bundle.putString("Fecha", p.getFecha());
                bundle.putString("descripcion", p.getDescripcion());
                bundle.putString("listID", p.getListId());
                bundle.putString("customer_FullName2", p.getCustomer_FullName());
                bundle.putString("itemService_FullName2", p.getNombreActividad());
                bundle.putString("class_FullName2", p.getClass_FullName());
                bundle.putString("payrollItemWage_FullName2", p.getPayrollItemWage_FullName());
                bundle.putString("entity_FullName2", p.getNombreEmpleado());
                bundle.putInt("CodigoRegistrador", p.getCodigoRegistrador());
                bundle.putString("Horas", p.getHoras());
                bundle.putInt("idPrim", p.getIdprim());


                Intent intent = null;
                intent = new Intent(ActualizarDatos.this, registro_de_actividades.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        }
    }

    public void VerRegistro(View v) {
        try {
            listV.setAdapter(null);
            items.clear();
            //        StringBuilder localStringBuilder = new StringBuilder();
            db = openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);

            Cursor c = db.rawQuery("select _id,Entity_FullName , ItemService_FullName ,Horas , Fecha , _id,Descripcion ,EditSequence ,  ListId ," +
                    "CodigoAprovador , TxnID , Customer_FullName ,Class_FullName ,PayrollItemWage_FullName ,  Linea, CodigoEmpleado , " +
                    "CodigoCliente , CodigoServicio , CodigoNomina , CodigoClase , CodigoEstado ,CodigoDia ,BillableStatus , Paquete , " +
                    "Grupo , CodigoCierre  ,CodigoEstadoRevision, Completa ,CodigoRevision,CodigoRegistrador  ," +
                    "Duracion,FechaCreacion,FechaAprobacion FROM actividades where CodigoEmpleado ="+ CodigoCliente.toString()+" order by fecha desc", null);
            while (c.moveToNext()) {


                items.add(new CargarActividadesRegistradas(c.getInt(0),c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5),
                        c.getString(6), c.getString(7), c.getString(8), c.getString(9), c.getString(10), c.getString(11), c.getString(12), c.getString(13),
                        c.getString(14), c.getInt(15), c.getInt(16), c.getInt(17), c.getInt(18), c.getInt(19), c.getInt(20), c.getInt(21), c.getInt(22),
                        c.getInt(23), c.getInt(24), c.getInt(25), c.getInt(26), c.getInt(27), c.getInt(28), c.getInt(29), c.getFloat(30), c.getString(31),
                        c.getString(32)));


                CargarAct_Adapter adapter = new CargarAct_Adapter(this, items);
                listV.setAdapter(adapter);
            }
           db.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }


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

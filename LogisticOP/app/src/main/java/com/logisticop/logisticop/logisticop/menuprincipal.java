package com.logisticop.logisticop.logisticop;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Programador on 05/03/2015.
 */
public class menuprincipal extends ActionBarActivity {

    SQLiteDatabase db;
    private StringBuilder mensaje = new StringBuilder();
    private StringBuilder mensaje2 = new StringBuilder();
    private TextView txtid,txtnombre;
    String ActivoNormal = "ActivoNormal";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                finish();
                Intent loginIntent = new Intent(context, MainActivity.class);
                startActivity(loginIntent);
            }
        }, intentFilter);


        setContentView(R.layout.menuprincipal);
        Button btn_registro_de_actividades = (Button) findViewById(R.id.btnregistro);
        Button btn_registro_de_actividades_por_clases = (Button) findViewById(R.id.btnregistro_clases);

        Button btnSincr= (Button) findViewById(R.id.btnSincr);
        Button btnActualizar= (Button) findViewById(R.id.btnActualizar);
        //Referencia a los objetos del layout
        txtid = (TextView) findViewById( R.id.txtid );
        txtnombre = (TextView) findViewById(R.id.txtnombre);
        //Recupera parametros y los muestra en el TextView
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if ( bundle != null ) {
        //recibo los datos que viene del XML del login(activity_main.xml)
            mensaje.append( bundle.getString("id"));
            mensaje2.append( bundle.getString("NombreUsuario") );
        }
         //  txtid.setTextSize(12);
        txtid.setText( mensaje );
          //   txtnombre.setTextSize(12);
        txtnombre.setText( mensaje2 );
        //++++++++
        btn_registro_de_actividades.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                try		{

                    //bundle nos permite almacenar valores de la siguiente forma
                    // bundle.putString( clave, valor );
                    // pudiendo BUNDLE alamcenar valores de todo tipo
                    Bundle bundle = new Bundle();
                    bundle.putString("Activo" , ActivoNormal);
                    bundle.putString("id" , txtid.getText().toString() );
                    bundle.putString("NombreUsuario" , txtnombre.getText().toString() );
                    //cuando el mensaje enviado a la clase registro_de_clientes sea ACTIVONORMAL
                    //el formulario entrara en el metodo de insertar a la base de datos

                    Intent intent = null;
                    intent = new Intent(menuprincipal.this, registro_de_clientes.class);
                    intent.putExtras( bundle );
                    startActivity(intent);

                }catch(Exception e){

                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }

        });
        btn_registro_de_actividades_por_clases.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                try		{

                    Bundle bundle = new Bundle();
                    bundle.putString("id" , txtid.getText().toString() );


                    Intent intent = null;
                    intent = new Intent(menuprincipal.this, registro_de_productos.class);
                    intent.putExtras(bundle);
                    startActivity(intent);


                }catch(Exception e){

                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }

        });


        btnSincr.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                try		{

                    Bundle bundle = new Bundle();
                    bundle.putString("id2", txtid.getText().toString());
                    Intent intent = null;
                    intent = new Intent(menuprincipal.this, Sincronizar.class);
                    intent.putExtras( bundle );
                    startActivity(intent);

                }catch(Exception e){

                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }

        });


        btnActualizar.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                try		{



                    Bundle bundle = new Bundle();
                    bundle.putString("id3" , txtid.getText().toString() );
                    //envio el id del empleado al formulario de actualizar
                    // con este ID se obtiene las actividades por usuario logueado
                    Intent intent = null;
                    intent = new Intent(menuprincipal.this, Logistica.class);
                    intent.putExtras( bundle );
                    startActivity(intent);

                }catch(Exception e){

                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }

        });
    }





}

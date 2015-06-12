package com.example.curso.pruebadegui;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Objects;


public class Ventana_Usuario extends ActionBarActivity {

        private EditText campoNombre;
        private EditText campoHoraInicio;
        private EditText campoHoraFinal;
        private Button button;
        //public ArrayList<Cliente> listaUsuarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana__agregar_usuario);
        button= (Button) findViewById(R.id.buttonAgergarCliente);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                campoNombre = (EditText) findViewById(R.id.editNombre);
                campoHoraInicio= (EditText) findViewById(R.id.editHinicio);
                campoHoraFinal= (EditText) findViewById(R.id.editHfinal);

                String nombre = campoNombre.getText().toString();
                String Hinico = campoHoraInicio.getText().toString();
                String Hfinal = campoHoraFinal.getText().toString();
                Integer hi = Integer.parseInt(Hinico);
                Integer hf = Integer.parseInt(Hfinal);
                Cliente cliente = new Cliente(hi,hf,nombre);
                Bundle contenedor = getIntent().getExtras();
                ListaClientes listaClientes = contenedor.getParcelable("array");
                Intent in = new Intent(Ventana_Usuario.this,Ventana_Principal.class);
                startActivity(in);
                //listaClientes.add(cliente);

            //Log.e("MENSAJE: ", "---------------------->se agrego el usuario");
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ventana__usuario, menu);
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

        return super.onOptionsItemSelected(item);
    }




}

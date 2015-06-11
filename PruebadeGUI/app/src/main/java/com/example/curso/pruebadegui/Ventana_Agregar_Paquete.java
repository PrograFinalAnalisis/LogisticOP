package com.example.curso.pruebadegui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


public class Ventana_Agregar_Paquete extends ActionBarActivity {
    private EditText idPaquete;
    private EditText anchoScreen,largoScreen,altoScreen;
    private Button button;
    public ArrayList<Paquete> listaPaquetes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana__agregar__paquete);

        idPaquete = (EditText) findViewById(R.id.editID);
        anchoScreen = (EditText) findViewById(R.id.editAncho);
        largoScreen = (EditText) findViewById(R.id.editLargo);
        altoScreen = (EditText) findViewById(R.id.editAlto);
        button = (Button) findViewById(R.id.buttonAgregarPaquete);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer id = Integer.parseInt(idPaquete.getText().toString());
                Integer ancho = Integer.parseInt(anchoScreen.getText().toString());
                Integer largo = Integer.parseInt(largoScreen.getText().toString());
                Integer alto = Integer.parseInt(altoScreen.getText().toString());
                Paquete paquete = new Paquete(id,largo,ancho,alto);
                listaPaquetes.add(paquete);


            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ventana__agregar__paquete, menu);
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

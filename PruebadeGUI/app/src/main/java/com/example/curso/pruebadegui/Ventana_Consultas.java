package com.example.curso.pruebadegui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;


public class Ventana_Consultas extends ActionBarActivity {

    private String[] colores;
    Cliente cliente1 = new Cliente(8,13,"MeliLeDaIgual");
    Cliente cliente2 = new Cliente(7,14,"NelsonPapiRin");
    Cliente cliente3 = new Cliente(8,12,"Asoto");
    Cliente cliente4 = new Cliente(9,15,"Fuffy :3");
    ListaClientes listaClientes = new ListaClientes();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana__consultas);
        //Mostrar Datos(Establecer cantidad de columnas al GridView en la ventana activity)
        //Arreglo de prueba
        //colores = new String []{"Rojo","Verde","Azul","Amarillo","Naranja"};
        listaClientes.add(cliente1);
        listaClientes.add(cliente2);
        listaClientes.add(cliente3);
        listaClientes.add(cliente4);
        ArrayAdapter<Cliente> adapter;

        adapter = new ArrayAdapter<Cliente>(this,android.R.layout.simple_list_item_1,listaClientes);
        GridView lv = (GridView) findViewById(R.id.gridView);
        lv.setAdapter(adapter);

        //gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //@Override
            /*public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        ((TextView)view).getText(),
                        Toast.LENGTH_SHORT).show();
            }});*/

            }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ventana__consultas, menu);
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

package com.example.curso.pruebadegui;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Objects;


public class Ventana_Principal extends ActionBarActivity {


    Cliente cliente1 = new Cliente(8,13,"MeliLeDaIgual");
    Cliente cliente2 = new Cliente(7,14,"NelsonPapiRin");
    Cliente cliente3 = new Cliente(8,12,"Asoto");
    Cliente cliente4 = new Cliente(9,15,"Fuffy :3");
    ListaClientes listaClientes = new ListaClientes();


    Paquete paquete1 = new Paquete(1,20,30,60);
    Paquete paquete2 = new Paquete(2,60,30,10);
    Paquete paquete3 = new Paquete(3,20,45,60);
    Paquete paquete4 = new Paquete(4,10,100,10);
    Paquete paquete5 = new Paquete(5,20,30,60);





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana__principal);

        listaClientes.add(cliente1);
        listaClientes.add(cliente2);
        listaClientes.add(cliente3);
        listaClientes.add(cliente4);
       /*
        listaPaquetes.add(paquete1);
        listaPaquetes.add(paquete2);
        listaPaquetes.add(paquete3);
        listaPaquetes.add(paquete4);
        listaPaquetes.add(paquete5);
        */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ventana__principal, menu);
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

// Boton agregar usuario
    public void onClickUsuario(View view){
        Intent intent = new Intent(Ventana_Principal.this,Ventana_Usuario.class);
        Bundle contenedor = new Bundle();
        contenedor.putParcelable("array",listaClientes);

        intent.putExtras(contenedor);

        startActivity(intent);

    }
//Boton enviar
    public void onClickEnviar(View view){
        Intent intent = new Intent(Ventana_Principal.this,Ventana_Informacion_Envio.class);
        startActivity(intent);

    }

// Boton preparar pedido
    public void onClickPedido(View view){
        Intent intent = new Intent(Ventana_Principal.this,Ventana_Escoger_Cliente.class);
        startActivity(intent);

    }
// Boton agregar paquete
    public void onClickPaquete(View view){
        Intent intent = new Intent(Ventana_Principal.this,Ventana_Agregar_Paquete.class);
        //intent.putExtra("listaP",listaPaquetes);
        startActivity(intent);

    }

 // Boton ver paquetes y usuarios
    public void onClickConsulta(View view){
        Intent intent = new Intent(Ventana_Principal.this,Ventana_Consultas.class);
        startActivity(intent);


    }



}

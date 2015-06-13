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

    //Este es el objeto de la clase principal





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana__principal);


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
        //Este es el codigo para que al momento de tocar un boton cambien de Actividad (Pantalla) en este caso la pantalla
        // de agregar nuevo Usuario
        Intent intent = new Intent(Ventana_Principal.this,Ventana_Usuario.class);
        startActivity(intent);

    }
//Boton enviar, nos manda a la pantalla donde se ejecutara el codigo de Adrian y se desplegara la información
    public void onClickEnviar(View view){
        Intent intent = new Intent(Ventana_Principal.this,Ventana_Informacion_Envio.class);
        startActivity(intent);

    }

// Boton preparar pedido,Nos lleva a la ventana para escoger el cliente
    public void onClickPedido(View view){
        Intent intent = new Intent(Ventana_Principal.this,Ventana_Escoger_Cliente.class);
        startActivity(intent);

    }
// Boton agregar paquete, Ventana para agregar paquetes nuevos
    public void onClickPaquete(View view){
        Intent intent = new Intent(Ventana_Principal.this,Ventana_Agregar_Paquete.class);
        //intent.putExtra("listaP",listaPaquetes);
        startActivity(intent);

    }

 // Boton ver paquetes y usuarios, ventana para ver los resultados de cliente x paquete
    public void onClickConsulta(View view){
        Intent intent = new Intent(Ventana_Principal.this,Ventana_Consultas.class);
        startActivity(intent);


    }



}

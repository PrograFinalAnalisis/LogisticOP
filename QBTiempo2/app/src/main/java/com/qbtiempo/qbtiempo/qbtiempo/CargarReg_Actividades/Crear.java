package com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades;

//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.OutputStreamWriter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qbtiempo.qbtiempo.qbtiempo.R;

public class Crear extends Activity implements OnClickListener {
	private Button guardar, cancelar;
	private EditText titulo, nota;
	private String titu;
	//private String estado;
	
	
	private DBAdapter myAdapBD;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.crear);
		inicializar();
	}
	private void inicializar()
	{
		myAdapBD = new DBAdapter(this);
		//estado = Environment.getExternalStorageState();
		guardar = (Button) findViewById(R.id.bGuardar);
		cancelar = (Button) findViewById(R.id.bCancelar);
		
		titulo = (EditText)findViewById(R.id.titulo);
		nota = (EditText)findViewById(R.id.nota);
		
		guardar.setOnClickListener(this);
		cancelar.setOnClickListener(this);
		
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.bGuardar:
			titu = titulo.getText().toString();
			if(! titu.equals(""))
			{
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("Estas seguro de guardar la nota o deseas seguir editandola")
				       .setCancelable(false)
				       .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int id) {
				        	   guardarArchivo();
				               Crear.this.finish();
				           }
				       })
				       .setNegativeButton("Editar", new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int id) {
				                dialog.cancel();
				           }
				       });
				AlertDialog alert = builder.create();
				alert.show();
			}
			else
			{
				Toast.makeText(getApplication(), "No has agregado un titulo a la nota \nLa nota no se puede guardar aun", Toast.LENGTH_LONG).show();
			}
			
			break;
		case R.id.bCancelar:
			finish();
			break;
		}
	}
	private void guardarArchivo() {
		// TODO Auto-generated method stub
		try {
			myAdapBD.abrir();
			myAdapBD.insertarNotas(titu, nota.getText().toString());
			myAdapBD.cerrar();
		} catch (SQLiteException se ) {
        	Log.e(getClass().getSimpleName(), "No se ha podido guardar la nota");
        }
	}
	
	
	
}

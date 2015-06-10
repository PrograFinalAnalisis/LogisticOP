package com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.qbtiempo.qbtiempo.qbtiempo.R;

public class Editar extends Activity implements OnClickListener {
	private Button guardar, cancelar;
	private EditText titulo, nota;
	private String titu;
	private DBAdapter myAdapBD;
	
	
	int row = 0;
	
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
		guardar = (Button) findViewById(R.id.bGuardar);
		cancelar = (Button) findViewById(R.id.bCancelar);
		
		titulo = (EditText)findViewById(R.id.titulo);
		nota = (EditText)findViewById(R.id.nota);
		
		
		Bundle bl = getIntent().getExtras();
		row = bl.getInt("ID");
		myAdapBD.abrir();
		Cursor c = myAdapBD.getNota(row);
		titulo.setText(c.getString(1));
		nota.setText(c.getString(2));
		myAdapBD.cerrar();
		
		
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
				builder.setMessage("Estas seguro de actualizar la nota")
				       .setCancelable(false)
				       .setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int id) {
				        	   guardarArchivo();
				               Editar.this.finish();
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
			myAdapBD.updateNota(row, titu, nota.getText().toString());
			//myAdapBD.insertarNotas(titu, nota.getText().toString());
			myAdapBD.cerrar();
		} catch (SQLiteException se ) {
        	Log.e(getClass().getSimpleName(), "No se ha podido actualizar la nota");
        }

	}

}

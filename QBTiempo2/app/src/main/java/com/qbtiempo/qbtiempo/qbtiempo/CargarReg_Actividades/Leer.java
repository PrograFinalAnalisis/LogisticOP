package com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.qbtiempo.qbtiempo.qbtiempo.R;

public class Leer extends Activity implements OnClickListener {
	private TextView mostrar, titu;
	String titulo ="", nota = "";
	DBAdapter db ;
	Button editar;
	int id = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.leer);
		inicializar();
	}
	private void inicializar()
	{
		db = new DBAdapter(this);
		mostrar = (TextView) findViewById(R.id.mostrar);
		titu = (TextView) findViewById(R.id.titulo);
		
		
		
		editar = (Button) findViewById(R.id.beditarNota);
		
		db.abrir();
		Cursor c;
		Bundle bl = getIntent().getExtras();
		
		
		
		id = bl.getInt("ID");
		c = db.getNota(id);
		
		
		
		titu.setText(c.getString(1));
		mostrar.setText(c.getString(2));
		db.cerrar();
		
		
		editar.setOnClickListener(this);
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i = new Intent(Leer.this, Editar.class);
		i.putExtra("ID", id);
		startActivityForResult(i,0);
		//startActivity(i);
	}
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==0)
		{
			inicializar();
		}
	}
	
}

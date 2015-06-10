package com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NotasList extends ListActivity implements OnItemClickListener {
	private ArrayList<String> resultados = new ArrayList<String>();
	private DBAdapter myAdapBD;
	ListView lista;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lista = getListView();
        abrirDBAdapter();
        
        mostrarResultados();
        lista.setOnItemClickListener(this);
    }
	
	
	private void mostrarResultados() {
		// TODO Auto-generated method stub
		setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, resultados));
        getListView().setTextFilterEnabled(true);
	}
	
	
	
	private void abrirDBAdapter() {
		// TODO Auto-generated method stub
		try {
			myAdapBD = new DBAdapter(this);
			myAdapBD.abrir();
			Cursor c = myAdapBD.getAllNotas();
			if (c.moveToFirst())
			{
			do {
				addToLista(c);
			} while (c.moveToNext());
			}
			myAdapBD.cerrar();
		} catch (SQLiteException se ) {
        	Log.e(getClass().getSimpleName(), "NO se ha podido acceder a la base de datos");
        }
	}


	private void addToLista(Cursor c) {
		// TODO Auto-generated method stub
		resultados.add(c.getString(1));
	}


	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
		//borramos el contenido de resultados
		resultados.clear();
		
		Intent i = new Intent(NotasList.this, Leer.class);
		i.putExtra("ID", arg2+1);
		//startActivity(i);
		
		startActivityForResult(i, 0);
	}


	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==0)
		{
			abrirDBAdapter();
	        
	        mostrarResultados();
		}
	}
	
	
	
}

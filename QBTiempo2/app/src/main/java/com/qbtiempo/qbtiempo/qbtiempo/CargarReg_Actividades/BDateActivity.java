package com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.qbtiempo.qbtiempo.qbtiempo.R;

public class BDateActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	private Button bCrear, bLeer;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba);
        inicializar();
    }
    private void inicializar()
    {
    	bCrear = (Button) findViewById(R.id.bCrearA);
    	bLeer = (Button) findViewById(R.id.bLeerA);
    	
    	bCrear.setOnClickListener(this);
    	bLeer.setOnClickListener(this);
    }
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent i;
		switch(arg0.getId())
		{
		case R.id.bCrearA:
			i = new Intent(BDateActivity.this, Crear.class);
			startActivity(i);
			break;
		case R.id.bLeerA:
			i = new Intent(BDateActivity.this, NotasList.class);
			startActivity(i);
			break;
        case R.id.btninsertar:
            i = new Intent(BDateActivity.this, NotasList.class);
            startActivity(i);
            break;
		}
	}
}
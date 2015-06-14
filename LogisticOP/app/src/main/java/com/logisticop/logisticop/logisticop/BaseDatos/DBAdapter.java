package com.logisticop.logisticop.logisticop.BaseDatos;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.logisticop.logisticop.logisticop.BaseDatos.Tablas.TablaCaja;
import com.logisticop.logisticop.logisticop.BaseDatos.Tablas.TablaClientes;
import com.logisticop.logisticop.logisticop.BaseDatos.Tablas.TablaEmpleados;
import com.logisticop.logisticop.logisticop.BaseDatos.Tablas.TablaPedidos;

public class DBAdapter {

    public static final String DATABASE_NAME = "DB_LOGISTICOP.sqlite";
 //   public static final String DATABASE_TABLE = "BDate";
    public static final int DATABASE_VERSION = 1;//version de la base de datos
    public static final String TAG = "DBAdapter";



	private final Context context;
	private DatabaseHelper DBHelper;
	
	private SQLiteDatabase db;
	
	public DBAdapter(Context ctx)
	{
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}
	
	
	public static class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);

		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			try{
                // se crean las tablas
                db.execSQL(TablaEmpleados.DATABASE_CREATE_Emp);
                db.execSQL(TablaPedidos.DATABASE_CREATE_PEDIDO);
                db.execSQL(TablaClientes.DATABASE_CREATE_Client);
                db.execSQL(TablaCaja.DATABASE_CREATE_CAJA);

            }
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

			Log.w(DBAdapter.TAG, "se ha actualizado la base de datos");
            db.execSQL("DROP TABLE IF EXIST"+ TablaEmpleados.DATABASE_TABLE_EMP);
            db.execSQL("DROP TABLE IF EXIST"+ TablaClientes.DATABASE_CREATE_Client);
            db.execSQL("DROP TABLE IF EXIST"+ TablaCaja.DATABASE_CREATE_CAJA);
			onCreate(db);
		}

	}
	
	
	public DBAdapter abrir() throws SQLException
	{
		db = DBHelper.getWritableDatabase(); 
		return this;
		
	}



}

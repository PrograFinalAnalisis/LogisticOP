package com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades.Tablas.TablaActividades;
import com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades.Tablas.TablaClases;
import com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades.Tablas.TablaClientes;
import com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades.Tablas.TablaEmpleados;
import com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades.Tablas.TablaHoras;
import com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades.Tablas.TablaMinutos;
import com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades.Tablas.TablaNomina;
import com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades.Tablas.TablaProveedores;
import com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades.Tablas.TablaRoles;
import com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades.Tablas.TablaRolesEmpleados;
import com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades.Tablas.TablaServicios;

public class DBAdapter {

    public static final String DATABASE_NAME = "DB_QBTIEMPOS.sqlite";
    public static final String DATABASE_TABLE = "BDate";
    public static final int DATABASE_VERSION = 1;
    public static final String TAG = "DBAdapter";



    //***************************************/


	public static final String KEY_ROWID = "_id";
	public static final String KEY_TITULO = "titulo";
	public static final String KEY_NOTA = "nota";
	public static final String DATABASE_CREATE =
            "create table "+DATABASE_TABLE+"("+KEY_ROWID+" integer primary key autoincrement not null, "+
												 KEY_TITULO+" text not null, "+KEY_NOTA+" text not null);"; 



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
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			try{
				db.execSQL(DATABASE_CREATE);
                db.execSQL(TablaEmpleados.DATABASE_CREATE_Emp);
                db.execSQL(TablaClases.DATABASE_CREATE_Clas);
                db.execSQL(TablaClientes.DATABASE_CREATE_Client);
                db.execSQL(TablaNomina.DATABASE_CREATE_Nomina);
                db.execSQL(TablaServicios.DATABASE_CREATE_servicios);
                db.execSQL(TablaProveedores.DATABASE_CREATE_Prov);
                db.execSQL(TablaHoras.DATABASE_CREATE_Horas);
                db.execSQL(TablaMinutos.DATABASE_CREATE_minutos);
                db.execSQL(TablaRoles.DATABASE_CREATE_roles);
                db.execSQL(TablaRolesEmpleados.DATABASE_CREATE_RolesEmpleados);
                db.execSQL(TablaActividades.DATABASE_CREATE_actividades);

            }
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			Log.w(DBAdapter.TAG, "se ha actualizado la base de datos");
			db.execSQL("DROP TABLE IF EXIST"+DBAdapter.DATABASE_TABLE);
            db.execSQL("DROP TABLE IF EXIST"+ TablaEmpleados.DATABASE_TABLE_EMP);
            db.execSQL("DROP TABLE IF EXIST"+ TablaClientes.DATABASE_CREATE_Client);
            db.execSQL("DROP TABLE IF EXIST"+ TablaNomina.DATABASE_CREATE_Nomina);
            db.execSQL("DROP TABLE IF EXIST"+ TablaServicios.DATABASE_CREATE_servicios);
            db.execSQL("DROP TABLE IF EXIST"+ TablaProveedores.DATABASE_CREATE_Prov);
            db.execSQL("DROP TABLE IF EXIST"+ TablaHoras.DATABASE_CREATE_Horas);
            db.execSQL("DROP TABLE IF EXIST"+ TablaMinutos.DATABASE_CREATE_minutos);
            db.execSQL("DROP TABLE IF EXIST"+ TablaRoles.DATABASE_CREATE_roles);
            db.execSQL("DROP TABLE IF EXIST"+ TablaRolesEmpleados.DATABASE_CREATE_RolesEmpleados);
            db.execSQL("DROP TABLE IF EXIST"+ TablaActividades.DATABASE_CREATE_actividades);
			onCreate(db);
		}

	}
	
	
	public DBAdapter abrir() throws SQLException
	{
		db = DBHelper.getWritableDatabase(); 
		return this;
		
	}
	public void cerrar()
	{
		DBHelper.close();
	}

    ////revisar los valores enteros
   public long insertar_mysql(int id,String nombre,
                              String apellido1,String apellido2,
                              String nombrecompleto,String clave,
                              String nombreqb,
                              String listid,String editsequense,
                              String parentid,String classid,
                              int tipo,int codigoestado,
                              String email,int codigosupervisor,
                              String fechaalta,String cedula,
                              float diasvaciones,float horastrabajadas,
                              int consecutivo,int visible,int codigorol,
                              int codigoclase,int cbclase,
                              int cbactividad,int cbclientes,
                              int cbnomina,int activo){


       ContentValues insertarEmpleados = new ContentValues();
       insertarEmpleados.put(TablaEmpleados.KEY_ROWID_EMP,id);
       insertarEmpleados.put(TablaEmpleados.KEY_Nombre,nombre);
       insertarEmpleados.put(TablaEmpleados.KEY_Apellido1,apellido1);
       insertarEmpleados.put(TablaEmpleados.KEY_Apellido2,apellido2);
       insertarEmpleados.put(TablaEmpleados.KEY_NombreCompleto,nombrecompleto);
       insertarEmpleados.put(TablaEmpleados.KEY_Clave,clave);
       insertarEmpleados.put(TablaEmpleados.KEY_NombreQB,nombreqb);
       insertarEmpleados.put(TablaEmpleados.KEY_ListID,listid);
       insertarEmpleados.put(TablaEmpleados.KEY_EditSequence,editsequense);
       insertarEmpleados.put(TablaEmpleados.KEY_ParentId,parentid);
       insertarEmpleados.put(TablaEmpleados.KEY_ClassId,classid);
       insertarEmpleados.put(TablaEmpleados.KEY_Tipo,tipo);
       insertarEmpleados.put(TablaEmpleados.KEY_CodigoEstado,codigoestado);
       insertarEmpleados.put(TablaEmpleados.KEY_Email,email);
       insertarEmpleados.put(TablaEmpleados.KEY_CodigoSupervisor,codigosupervisor);
       insertarEmpleados.put(TablaEmpleados.KEY_FechaAlta, fechaalta);
       insertarEmpleados.put(TablaEmpleados.KEY_Cedula, cedula);
       insertarEmpleados.put(TablaEmpleados.KEY_DiasVacacionesRest, diasvaciones);
       insertarEmpleados.put(TablaEmpleados.KEY_HorasTrabajadas,horastrabajadas);
       insertarEmpleados.put(TablaEmpleados.KEY_Consecutivo,consecutivo);
       insertarEmpleados.put(TablaEmpleados.KEY_Visible,visible);
       insertarEmpleados.put(TablaEmpleados.KEY_CodigoRol,codigorol);

       insertarEmpleados.put(TablaEmpleados.KEY_CodigoClase,codigoclase);
       insertarEmpleados.put(TablaEmpleados.KEY_cbClase,cbclase);
       insertarEmpleados.put(TablaEmpleados.KEY_cbActividad,cbactividad);
       insertarEmpleados.put(TablaEmpleados.KEY_cbCliente,cbclientes);
       insertarEmpleados.put(TablaEmpleados.KEY_cbNomina,cbnomina);
       insertarEmpleados.put(TablaEmpleados.KEY_Activo,activo);

       return db.insert(TablaEmpleados.DATABASE_TABLE_EMP, null, insertarEmpleados);

   }


	public long insertarNotas(String titu, String nota)
	{
		ContentValues initVal = new ContentValues();
		initVal.put(KEY_TITULO, titu);
		initVal.put(KEY_NOTA, nota);
		return db.insert(DATABASE_TABLE, null, initVal);
	}

     public long insertarAct(int codigoemple,int codclient,
                            int CodigoServicio,int CodigoNomina,
                            int CodigoClase,float duracion,String Fecha
                             ,String descripcion,String ListId,
                            String Customer_FullName,String ItemService_FullName,String Class_FullName,
                            String PayrollItemWage_FullName,String Entity_FullName,int CodigoRegistrador,float Horas){
        ContentValues act = new ContentValues();
        act.put(TablaActividades.KEY_Linea," ");
        act.put(TablaActividades.KEY_CodigoEmpleado,codigoemple);
        act.put(TablaActividades.KEY_CodigoCliente,codclient);
        act.put(TablaActividades.KEY_CodigoServicio,CodigoServicio);
        act.put(TablaActividades.KEY_CodigoNomina,CodigoNomina);
        act.put(TablaActividades.KEY_CodigoClase,CodigoClase);
        act.put(TablaActividades.KEY_Duracion,duracion);
        act.put(TablaActividades.KEY_Fecha,Fecha);
        act.put(TablaActividades.KEY_CodigoEstado,2);
        act.put(TablaActividades.KEY_CodigoDia," ");
        act.put(TablaActividades.KEY_CodigoEmpleado,descripcion);
        act.put(TablaActividades.KEY_EditSequence," ");
        act.put(TablaActividades.KEY_ListId,ListId);
        act.put(TablaActividades.KEY_CodigoAprovador,0);
        act.put(TablaActividades.KEY_TxnID," ");
        act.put(TablaActividades.KEY_Customer_FullName,Customer_FullName);
        act.put(TablaActividades.KEY_ItemService_FullName,ItemService_FullName);
        act.put(TablaActividades.KEY_Class_FullName,Class_FullName);
        act.put(TablaActividades.KEY_PayrollItemWage_FullName,PayrollItemWage_FullName);
        act.put(TablaActividades.KEY_Entity_FullName,Entity_FullName);
        act.put(TablaActividades.KEY_BillableStatus,1);
        act.put(TablaActividades.KEY_Paquete," ");
        act.put(TablaActividades.KEY_Grupo," ");
        act.put(TablaActividades.KEY_CodigoCierre,0);
        act.put(TablaActividades.KEY_CodigoRevision,0);
        act.put(TablaActividades.KEY_CodigoRegistrador,CodigoRegistrador);
        act.put(TablaActividades.KEY_FechaCreacion," ");
        act.put(TablaActividades.KEY_FechaAprobacion," ");
        act.put(TablaActividades.KEY_Horas,Horas);
        act.put(TablaActividades.KEY_Completa,1);
        act.put(TablaActividades.KEY_CodigoEstado,0);
        return db.insert(TablaActividades.DATABASE_TABLE_actividades, null, act);


    }
	public Cursor getAllNotas()
	{
		String[] getMain = {KEY_ROWID,
							KEY_TITULO,
							KEY_NOTA
							};
		return db.query(DATABASE_TABLE, getMain, null, null, null, null, null);
	}
	
	public Cursor getNota(long row)
	{
		String[] getOneMain = {KEY_ROWID,
				 KEY_TITULO,
				 KEY_NOTA };
		Cursor miCursor = db.query(DATABASE_TABLE, getOneMain, KEY_ROWID+"="+row, null, null, null, null);
		if(miCursor != null)
		{
			miCursor.moveToFirst();
		}
		return miCursor;
	}
	
	public boolean updateNota(long row, String tit, String nota)
	{
		ContentValues arg = new ContentValues();
		arg.put(KEY_TITULO, tit);
		arg.put(KEY_NOTA, nota);
		return db.update(DATABASE_TABLE, arg, KEY_ROWID+"="+row, null) > 0;
	}
}

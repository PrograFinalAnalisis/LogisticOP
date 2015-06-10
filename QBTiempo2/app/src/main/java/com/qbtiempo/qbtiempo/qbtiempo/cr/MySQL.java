package com.qbtiempo.qbtiempo.qbtiempo.cr;

public class MySQL  {
   /* extends SQLiteOpenHelper private static int version = 1;
    private static String DBNAME = "QBTiempos" ;
    private static CursorFactory factory = null;*/
	 
   /* SQLiteDatabase  db;
	public MySQL(Context context) {
	      super(context, DBNAME, factory, version);
	}*/
	/*
	@Override
	public void onCreate(SQLiteDatabase db) {
		 
		//db= openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);

		db.execSQL("create table IF NOT EXISTS empleados(_id integer primary key, " +
					" Nombre text, Apellido1 text," +
					" Apellido2 text , NombreCompleto text, Clave text," +
					" NombreQB text," +
					" ListID text, EditSequence text, ParentId text,ClassId text,Tipo integer, " +
					" CodigoEstado integer,Email text, CodigoSupervisor interger," +
					" FechaAlta date,Cedula text,DiasVacacionesRest float, HorasTrabajadas float," +
					" Consecutivo integer,Visible integer, CodigoRol integer,CodigoClase integer," +
					" cbClase integer, cbActividad integer, cbCliente integer,cbNomina integer," +
					" Activo integer);");
		
		db.execSQL("create table IF NOT EXISTS clases(_id integer primary key, Nombre text, " +
					"FullName text,ClassId text,ParentId text,EditSecuence text, " +
					"ListId text, SubLevel integer,LastLevel integer,Activo integer );");
		
		db.execSQL("create table IF NOT EXISTS clientes(_id integer primary key, Nombre text,Apellido1 text, Apellido2 text, NombreCompleto text," +
					"Clave text,NombreQB text,ListID text,EditSequence text,ParentId text,ClassId text, Tipo integer,CodigoEstado integer, " +
					"Email text, FechaAlta date, Cedula text,Consecutivo integer,CodigoClase integer,Empresa text, Contacto text,Moneda text," +
					"Sublevel integer,LastLevel integer,Activo integer);");
		
		db.execSQL("create table IF NOT EXISTS nomina(_id integer primary key, Nombre text, FullName text,ParentId text,EditSequence text,ListId text, Activo integer);");
		
		
		db.execSQL("create table IF NOT EXISTS servicios(_id integer primary key, Nombre text, FullName text,ParentId text,EditSecuence text,ListId text,SubLevel integer," +
					"LastLevel integer, Activo integer);");
		
		db.execSQL("create table IF NOT EXISTS proveedores(_id integer primary key, Nombre text, Apellido1 text,Apellido2 text,NombreCompleto text, Clave text,NombreQB text," +
					"ListID text,EditSequence text,ParentId text,ClassId text, Tipo integer,CodigoEstado integer,Email text,CodigoSupervisor integer,FechaAlta date," +
					"Cedula text,DiasVacacionesRest float,HorasTrabajadas float,Consecutivo integer,Visible integer,CodigoRol integer,CodigoClase integer," +
					"cbClase integer,cbActividad integer,cbCliente integer,cbNomina integer,Activo integer);");
		
		db.execSQL("create table IF NOT EXISTS roles(_id integer primary key, Nombre text);");
		 
		db.execSQL("create table IF NOT EXISTS roles_empleados(_id integer primary key, ListID text, Tipo integer, Rol1 integer, Rol2 integer, Rol3 integer, Rol4 integer, Rol5 integer);");
		   
		db.execSQL("CREATE TABLE IF NOT EXISTS actividades (_id integer  primary key,Linea integer DEFAULT NULL,"
				+ "CodigoEmpleado integer DEFAULT NULL,CodigoCliente integer DEFAULT NULL,"
				+ "CodigoServicio integer DEFAULT NULL,CodigoNomina integer DEFAULT NULL,"
				+ "CodigoClase integer DEFAULT NULL,Duracion float DEFAULT NULL,"
				+ "Fecha date DEFAULT NULL,CodigoEstado integer DEFAULT NULL,"
				+ "CodigoDia integer DEFAULT NULL,Descripcion text,EditSequence text DEFAULT NULL,"
				+ "ListId text DEFAULT NULL,CodigoAprovador integer DEFAULT NULL,TxnID text DEFAULT NULL,"
				+ "Customer_FullName text DEFAULT NULL,ItemService_FullName text DEFAULT NULL,"
				+ "Class_FullName text DEFAULT NULL,PayrollItemWage_FullName text DEFAULT NULL,"
				+ "Entity_FullName text DEFAULT NULL,BillableStatus integer DEFAULT NULL,"
				+ "Paquete integer DEFAULT NULL,Grupo integer DEFAULT NULL,CodigoCierre integer DEFAULT NULL,"
				+ "CodigoRevision integer DEFAULT NULL,CodigoRegistrador integer DEFAULT NULL,"
				+ "FechaCreacion datetime DEFAULT NULL,FechaAprobacion datetime DEFAULT NULL,"
				+ "Horas float DEFAULT NULL,Completa integer DEFAULT NULL,CodigoEstadoRevision integer DEFAULT NULL);");
		db.execSQL("CREATE TABLE IF NOT EXISTS horas (_id integer PRIMARY KEY AUTOINCREMENT,hhoras integer DEFAULT NULL);");
		db.execSQL("CREATE TABLE IF NOT EXISTS minutos (_id integer PRIMARY KEY AUTOINCREMENT,Mminutos integer DEFAULT NULL);");
		
	//------------------------------------------------------	
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
		//db.execSQL("drop table if exists pruebas");
		 
		
		db.execSQL("create table IF NOT EXISTS empleados(_id integer primary key, " +
					" Nombre text, Apellido1 text," +
					" Apellido2 text , NombreCompleto text, Clave text," +
					" NombreQB text," +
					" ListID text, EditSequence text, ParentId text,ClassId text,Tipo integer, " +
					" CodigoEstado integer,Email text, CodigoSupervisor interger," +
					" FechaAlta date,Cedula text,DiasVacacionesRest float, HorasTrabajadas float," +
					" Consecutivo integer,Visible integer, CodigoRol integer,CodigoClase integer," +
					" cbClase integer, cbActividad integer, cbCliente integer,cbNomina integer," +
					" Activo integer);");
		
		db.execSQL("create table IF NOT EXISTS clases(_id integer primary key, Nombre text, " +
					"FullName text,ClassId text,ParentId text,EditSecuence text, " +
					"ListId text, SubLevel integer,LastLevel integer,Activo integer );");
		
		db.execSQL("create table IF NOT EXISTS clientes(_id integer primary key, Nombre text,Apellido1 text, Apellido2 text, NombreCompleto text," +
					"Clave text,NombreQB text,ListID text,EditSequence text,ParentId text,ClassId text, Tipo integer,CodigoEstado integer, " +
					"Email text, FechaAlta date, Cedula text,Consecutivo integer,CodigoClase integer,Empresa text, Contacto text,Moneda text," +
					"Sublevel integer,LastLevel integer,Activo integer);");
		
		db.execSQL("create table IF NOT EXISTS nomina(_id integer primary key, Nombre text, FullName text,ParentId text,EditSequence text,ListId text, Activo integer);");
		
		
		db.execSQL("create table IF NOT EXISTS servicios(_id integer primary key, Nombre text, FullName text,ParentId text,EditSecuence text,ListId text,SubLevel integer," +
					"LastLevel integer, Activo integer);");
		
		db.execSQL("create table IF NOT EXISTS proveedores(_id integer primary key, Nombre text, Apellido1 text,Apellido2 text,NombreCompleto text, Clave text,NombreQB text," +
					"ListID text,EditSequence text,ParentId text,ClassId text, Tipo integer,CodigoEstado integer,Email text,CodigoSupervisor integer,FechaAlta date," +
					"Cedula text,DiasVacacionesRest float,HorasTrabajadas float,Consecutivo integer,Visible integer,CodigoRol integer,CodigoClase integer," +
					"cbClase integer,cbActividad integer,cbCliente integer,cbNomina integer,Activo integer);");
		
		db.execSQL("create table IF NOT EXISTS roles(_id integer primary key, Nombre text);");
		 
		db.execSQL("create table IF NOT EXISTS roles_empleados(_id integer primary key, ListID text, Tipo integer, Rol1 integer, Rol2 integer, Rol3 integer, Rol4 integer, Rol5 integer);");
		   

		db.execSQL("CREATE TABLE IF NOT EXISTS actividades (_id integer  primary key,Linea integer DEFAULT NULL,"
				+ "CodigoEmpleado integer DEFAULT NULL,CodigoCliente integer DEFAULT NULL,"
				+ "CodigoServicio integer DEFAULT NULL,CodigoNomina integer DEFAULT NULL,"
				+ "CodigoClase integer DEFAULT NULL,Duracion float DEFAULT NULL,"
				+ "Fecha date DEFAULT NULL,CodigoEstado integer DEFAULT NULL,"
				+ "CodigoDia integer DEFAULT NULL,Descripcion text,EditSequence text DEFAULT NULL,"
				+ "ListId text DEFAULT NULL,CodigoAprovador integer DEFAULT NULL,TxnID text DEFAULT NULL,"
				+ "Customer_FullName text DEFAULT NULL,ItemService_FullName text DEFAULT NULL,"
				+ "Class_FullName text DEFAULT NULL,PayrollItemWage_FullName text DEFAULT NULL,"
				+ "Entity_FullName text DEFAULT NULL,BillableStatus integer DEFAULT NULL,"
				+ "Paquete integer DEFAULT NULL,Grupo integer DEFAULT NULL,CodigoCierre integer DEFAULT NULL,"
				+ "CodigoRevision integer DEFAULT NULL,CodigoRegistrador integer DEFAULT NULL,"
				+ "FechaCreacion datetime DEFAULT NULL,FechaAprobacion datetime DEFAULT NULL,"
				+ "Horas float DEFAULT NULL,Completa integer DEFAULT NULL,CodigoEstadoRevision integer DEFAULT NULL);");
		db.execSQL("CREATE TABLE IF NOT EXISTS horas (_id integer PRIMARY KEY AUTOINCREMENT,hhoras integer DEFAULT NULL);");
		db.execSQL("CREATE TABLE IF NOT EXISTS minutos (_id integer PRIMARY KEY AUTOINCREMENT,Mminutos integer DEFAULT NULL);");
		
	}	*/
}

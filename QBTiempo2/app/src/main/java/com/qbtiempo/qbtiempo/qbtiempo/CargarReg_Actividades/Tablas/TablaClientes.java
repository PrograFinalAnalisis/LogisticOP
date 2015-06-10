package com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades.Tablas;

/**
 * Created by Programador on 10/03/2015.
 */
public class TablaClientes {


    public static final String DATABASE_TABLE_Client = "clientes";
    // Creacion de las tabla empleados

    public static final String KEY_ROWID_client = "_id";
    public static final String KEY_Nombre = "Nombre";
    public static final String KEY_Apellido1 = "Apellido1";
    public static final String KEY_Apellido2 = "Apellido2";
    public static final String KEY_NombreCompleto = "NombreCompleto";
    public static final String KEY_Clave = "Clave";
    public static final String KEY_NombreQB = "NombreQB";
    public static final String KEY_ListID = "ListID";
    public static final String KEY_EditSequence = "EditSequence";
    public static final String KEY_ParentId = "ParentId";
    public static final String KEY_ClassId = "ClassId";
    public static final String KEY_Tipo = "Tipo";
    public static final String KEY_CodigoEstado = "CodigoEstado";
    public static final String KEY_Email = "Email";
    public static final String KEY_FechaAlta = "FechaAlta";
    public static final String KEY_Cedula = "Cedula";
    public static final String KEY_Consecutivo = "Consecutivo";
    public static final String KEY_CodigoClase = "CodigoClase";
    public static final String KEY_Empresa = "Empresa";
    public static final String KEY_Contacto = "Contacto";
    public static final String KEY_Moneda = "Moneda";
    public static final String KEY_Sublevel = "Sublevel";
    public static final String KEY_LastLevel = "LastLevel";
    public static final String KEY_Activo = "Activo";

    public static final String DATABASE_CREATE_Client= "create table IF NOT EXISTS "+DATABASE_TABLE_Client+"" +
            "("+KEY_ROWID_client+" integer primary key, " +
            "  "+KEY_Nombre+ " text, "+KEY_Apellido1+ " text," +
            " "+KEY_Apellido2+" text , "+KEY_NombreCompleto+ " text, "+KEY_Clave+" text," +
            " "+KEY_NombreQB+ " text," +
            " "+KEY_ListID+  " text,  "+KEY_EditSequence+  " text, "+KEY_ParentId+ " text,"+KEY_ClassId+ " text,"+KEY_Tipo+ " integer, " +
            " "+KEY_CodigoEstado+ " integer,"+KEY_Email+ " text," +
            " "+KEY_FechaAlta+ " date,"+KEY_Cedula+ " text,"+
            " "+KEY_Consecutivo+ " integer, "+KEY_CodigoClase+ " integer,"+KEY_Empresa+ " text," +
            " "+KEY_Contacto+ " text, "+KEY_Moneda+ " text, "+KEY_Sublevel+ " integer,"+KEY_LastLevel+ " integer," +
            " "+KEY_Activo+ " integer);";




}

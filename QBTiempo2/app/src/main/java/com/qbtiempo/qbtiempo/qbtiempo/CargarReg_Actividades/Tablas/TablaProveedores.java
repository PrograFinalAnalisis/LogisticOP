package com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades.Tablas;

/**
 * Created by Programador on 10/03/2015.
 */
public class TablaProveedores {

    public static final String DATABASE_TABLE_Prov = "proveedores";

    public static final String KEY_ROWID_EMP = "_id";
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
    public static final String KEY_CodigoSupervisor = "CodigoSupervisor";
    public static final String KEY_FechaAlta = "FechaAlta";
    public static final String KEY_Cedula = "Cedula";
    public static final String KEY_DiasVacacionesRest = "DiasVacacionesRest";
    public static final String KEY_HorasTrabajadas = "HorasTrabajadas";
    public static final String KEY_Consecutivo = "Consecutivo";
    public static final String KEY_Visible = "Visible";
    public static final String KEY_CodigoRol = "CodigoRol";
    public static final String KEY_CodigoClase = "CodigoClase";
    public static final String KEY_cbClase = "cbClase";
    public static final String KEY_cbActividad = "cbActividad";
    public static final String KEY_cbCliente = "cbCliente";
    public static final String KEY_cbNomina = "cbNomina";
    public static final String KEY_Activo = "Activo";



    public static final String DATABASE_CREATE_Prov= "create table IF NOT EXISTS "+DATABASE_TABLE_Prov+"" +
            "("+KEY_ROWID_EMP+" integer primary key, " +
            "  "+KEY_Nombre+ " text, "+KEY_Apellido1+ " text," +
            " "+KEY_Apellido2+" text , "+KEY_NombreCompleto+ " text, "+KEY_Clave+" text," +
            " "+KEY_NombreQB+ " text," +
            " "+KEY_ListID+  " text,  "+KEY_EditSequence+  " text, "+KEY_ParentId+ " text,"+KEY_ClassId+ " text,"+KEY_Tipo+ " integer, " +
            " "+KEY_CodigoEstado+ " integer,"+KEY_Email+ " text, "+KEY_CodigoSupervisor+ " interger," +
            " "+KEY_FechaAlta+ " date,"+KEY_Cedula+ " text,"+KEY_DiasVacacionesRest+ " float, "+KEY_HorasTrabajadas+ " float," +
            " "+KEY_Consecutivo+ " integer,"+KEY_Visible+ " integer, "+KEY_CodigoRol+ " integer,"+KEY_CodigoClase+ " integer," +
            " "+KEY_cbClase+ " integer, "+KEY_cbActividad+ " integer, "+KEY_cbCliente+ " integer,"+KEY_cbNomina+ " integer," +
            " "+KEY_Activo+ " integer);";
}

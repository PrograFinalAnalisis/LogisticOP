package com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades.Tablas;

/**
 * Created by Programador on 10/03/2015.
 */
public class TablaServicios {


    public static final String DATABASE_TABLE_servicios = "servicios";
    // Creacion de las tabla empleados

    public static final String KEY_ROWID = "_id";
    public static final String KEY_Nombre = "Nombre";
    public static final String KEY_FullName = "FullName";
    public static final String KEY_ParentId = "ParentId";
    public static final String KEY_EditSecuence = "EditSecuence";
    public static final String KEY_ListId = "ListId";
    public static final String KEY_SubLevel = "SubLevel";
    public static final String KEY_LastLevel = "LastLevel";
    public static final String KEY_Activo = "Activo";

    public static final String DATABASE_CREATE_servicios= "create table IF NOT EXISTS "+ DATABASE_TABLE_servicios +"" +
            "("+ KEY_ROWID +" integer primary key, " +
            "  "+ KEY_Nombre + " text, "+ KEY_FullName + " text, "+ KEY_ParentId + " text, "+ KEY_EditSecuence +" text," +
            " "+ KEY_ListId + " text," +
            " "+ KEY_SubLevel +  " text,  "+ KEY_LastLevel +  " text, "+ KEY_Activo + " integer);";


}

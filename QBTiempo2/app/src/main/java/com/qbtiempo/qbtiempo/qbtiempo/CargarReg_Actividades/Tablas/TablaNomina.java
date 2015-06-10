package com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades.Tablas;

/**
 * Created by Programador on 10/03/2015.
 */
public class TablaNomina {

    public static final String DATABASE_TABLE_Nomina = "nomina";
    // Creacion de las tabla empleados

    public static final String KEY_ROWID = "_id";
    public static final String KEY_Nombre = "Nombre";
    public static final String KEY_FullName = "FullName";
    public static final String KEY_ParentId = "ParentId";
    public static final String KEY_EditSecuence = "EditSecuence";
    public static final String KEY_ListId = "ListId";
    public static final String KEY_Activo = "Activo";

    public static final String DATABASE_CREATE_Nomina= "create table IF NOT EXISTS "+ DATABASE_TABLE_Nomina +"" +
            "("+ KEY_ROWID +" integer primary key, " +
            "  "+ KEY_Nombre + " text, "+ KEY_FullName + " text, "+ KEY_ParentId + " text, "+ KEY_EditSecuence +" text," +
            " "+ KEY_ListId + " text," + KEY_Activo + " integer);";


}

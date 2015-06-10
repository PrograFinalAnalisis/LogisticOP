package com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades.Tablas;

/**
 * Created by Programador on 10/03/2015.
 */
public class TablaMinutos {

    public static final String DATABASE_TABLE_minutos = "minutos";

    public static final String KEY_ROWID = "_id";
    public static final String KEY_Mminutos = "mminutos";

    public static final String DATABASE_CREATE_minutos= "create table IF NOT EXISTS "+ DATABASE_TABLE_minutos +"" +
            "("+ KEY_ROWID +" integer primary key, " +
            "  "+ KEY_Mminutos + " text);";

}

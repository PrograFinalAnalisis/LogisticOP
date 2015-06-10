package com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades.Tablas;

/**
 * Created by Programador on 10/03/2015.
 */
public class TablaHoras {

    public static final String DATABASE_TABLE_Horas = "horas";

    public static final String KEY_ROWID_Clas = "_id";
    public static final String KEY_hhoras = "hhoras";



    public static final String DATABASE_CREATE_Horas= "create table IF NOT EXISTS "+ DATABASE_TABLE_Horas +"" +
            "("+ KEY_ROWID_Clas +" integer primary key, " +
            "  "+ KEY_hhoras + " text);";

}

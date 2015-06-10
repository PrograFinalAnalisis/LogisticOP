package com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades.Tablas;

/**
 * Created by Programador on 10/03/2015.
 */
public class TablaRoles {
    public static final String DATABASE_TABLE_roles = "roles";

    public static final String KEY_ROWID = "_id";
    public static final String KEY_Nombre = "Nombre";

    public static final String DATABASE_CREATE_roles= "create table IF NOT EXISTS "+ DATABASE_TABLE_roles +"" +
            "("+ KEY_ROWID +" integer primary key, " +
            "  "+ KEY_Nombre + " text);";

}

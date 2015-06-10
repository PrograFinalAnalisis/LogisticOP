package com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades.Tablas;

/**
 * Created by Programador on 10/03/2015.
 */
public class TablaRolesEmpleados {

    public static final String DATABASE_TABLE_RolesEmpleados = "roles_empleados";
    // Creacion de las tabla empleados

    public static final String KEY_ROWID = "_id";
    public static final String KEY_ListId = "ListID";
    public static final String KEY_Tipo = "Tipo";
    public static final String KEY_Rol1 = "Rol1";
    public static final String KEY_Rol2 = "Rol2";
    public static final String KEY_Rol3 = "Rol3";
    public static final String KEY_Rol4 = "Rol4";
    public static final String KEY_Rol5 = "Rol5";
    public static final String DATABASE_CREATE_RolesEmpleados= "create table IF NOT EXISTS "+ DATABASE_TABLE_RolesEmpleados +"" +
            "("+ KEY_ROWID +" integer primary key, " +
            "  "+ KEY_ListId + " text, "+ KEY_Tipo + " integer, "+ KEY_Rol1+ " integer, "+ KEY_Rol2 + " integer, "+ KEY_Rol3 +" integer," +
            " "+ KEY_Rol4 + " integer," + KEY_Rol5 + " integer);";

}

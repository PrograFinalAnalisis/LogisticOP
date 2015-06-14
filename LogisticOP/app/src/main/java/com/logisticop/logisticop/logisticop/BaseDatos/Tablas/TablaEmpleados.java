package com.logisticop.logisticop.logisticop.BaseDatos.Tablas;

/**
 * Se crea la tabla empleados con sus respectivos campos
 */
public class TablaEmpleados {
    public static final String DATABASE_TABLE_EMP = "empleados";
    // Creacion de las tabla empleados

    public static final String KEY_ROWID_EMP = "_id";
    public static final String KEY_Nombre = "Nombre";
    public static final String KEY_Apellido1 = "Apellido1";
    public static final String KEY_Apellido2 = "Apellido2";
    public static final String KEY_NombreCompleto = "NombreCompleto";
    public static final String KEY_Clave = "Clave";


    public static final String DATABASE_CREATE_Emp= "create table IF NOT EXISTS "+DATABASE_TABLE_EMP+"" +
            "("+KEY_ROWID_EMP+" integer primary key, " +
            "  "+KEY_Nombre+ " text, "+KEY_Apellido1+ " text," +
            " "+KEY_Apellido2+" text , "+KEY_NombreCompleto+ " text, "+KEY_Clave+" text);";
}

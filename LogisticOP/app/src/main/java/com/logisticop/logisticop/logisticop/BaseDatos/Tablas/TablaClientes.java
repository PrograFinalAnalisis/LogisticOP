package com.logisticop.logisticop.logisticop.BaseDatos.Tablas;

/**
 * Se crea la tabla clientes con sus respectivos campos
 */
public class TablaClientes {


    public static final String DATABASE_TABLE_Client = "clientes";
    // Creacion de las tabla empleados

    public static final String KEY_ROWID_client = "_id";
    public static final String KEY_Nombre = "Nombre";
    public static final String KEY_HORA_INICIO = "horaInicio";
    public static final String KEY_HORA_FIN = "horaFin";

    public static final String DATABASE_CREATE_Client= "create table IF NOT EXISTS "+DATABASE_TABLE_Client+"" +
            "("+KEY_ROWID_client+" integer primary key AUTOINCREMENT, " +
            "  "+KEY_Nombre+ " text, "+ KEY_HORA_INICIO + " integer," +
            " "+ KEY_HORA_FIN +" integer);";





}

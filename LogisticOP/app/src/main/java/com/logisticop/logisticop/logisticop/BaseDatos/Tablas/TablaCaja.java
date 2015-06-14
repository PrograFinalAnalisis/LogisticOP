package com.logisticop.logisticop.logisticop.BaseDatos.Tablas;

/**
 * Se crea la tabla actividades con sus respectivos campos
 */
public class TablaCaja
{


    public static final String DATABASE_TABLE_caja = "caja";
    // Creacion de las tabla empleados

    public static final String KEY_ROWID = "_id";
    public static final String KEY_Nombre_Producto = "Nombre_Producto";
    public static final String KEY_Base = "Base";
    public static final String KEY_Altura = "Altura";
    public static final String KEY_Profundidad = "Profundidad";

    public static final String DATABASE_CREATE_CAJA = "create table IF NOT EXISTS "+ DATABASE_TABLE_caja +"" +
            "("+ KEY_ROWID +" integer primary key AUTOINCREMENT, " +
            "  "+ KEY_Nombre_Producto + " text, "+ KEY_Base + " integer," +
            " "+ KEY_Altura +" integer , "+ KEY_Profundidad + " integer);";



}

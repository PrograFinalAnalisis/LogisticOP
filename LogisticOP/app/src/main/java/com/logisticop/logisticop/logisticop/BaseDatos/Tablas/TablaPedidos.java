package com.logisticop.logisticop.logisticop.BaseDatos.Tablas;

/**
 * Se crea la tabla clases con sus respectivos campos
 */
public class TablaPedidos {


    public static final String DATABASE_TABLE_Pedido = "pedidos";

    public static final String KEY_ID_CLIENTE = "id_Cliente";
    public static final String KEY_ID_CAJA = "id_Caja";
    public static final String KEY_CANTIDAD = "cantidad";
    public static final String KEY_FECHA = "fecha";

    public static final String DATABASE_CREATE_PEDIDO = "create table IF NOT EXISTS "+ DATABASE_TABLE_Pedido +"" +
            "("+ KEY_ID_CLIENTE +" integer, " +
            "  "+ KEY_ID_CAJA + " text, "+ KEY_CANTIDAD + " text," +
            " "+ KEY_FECHA +" text);";

}

package com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades.Tablas;

/**
 * Created by Programador on 10/03/2015.
 */
public class TablaActividades {

    public static final String DATABASE_TABLE_actividades = "actividades";
    // Creacion de las tabla empleados

    public static final String KEY_ROWID = "_id";
    public static final String KEY_Linea = "Linea";
    public static final String KEY_CodigoEmpleado = "CodigoEmpleado";
    public static final String KEY_CodigoCliente = "CodigoCliente";
    public static final String KEY_CodigoServicio = "CodigoServicio";
    public static final String KEY_CodigoNomina = "CodigoNomina";

    public static final String KEY_CodigoClase = "CodigoClase";
    public static final String KEY_Duracion = "Duracion";
    public static final String KEY_Fecha = "Fecha";
    public static final String KEY_CodigoEstado = "CodigoEstado";
    public static final String KEY_CodigoDia = "CodigoDia";
    public static final String KEY_Descripcion = "Descripcion";
    public static final String KEY_EditSequence = "EditSequence";
    public static final String KEY_ListId = "ListId";
    public static final String KEY_CodigoAprovador = "CodigoAprovador";
    public static final String KEY_TxnID = "TxnID";
    public static final String KEY_Customer_FullName = "Customer_FullName";
    public static final String KEY_ItemService_FullName = "ItemService_FullName";
    public static final String KEY_Class_FullName = "Class_FullName";
    public static final String KEY_PayrollItemWage_FullName = "PayrollItemWage_FullName";
    public static final String KEY_Entity_FullName = "Entity_FullName";
    public static final String KEY_BillableStatus = "BillableStatus";
    public static final String KEY_Paquete = "Paquete";
    public static final String KEY_Grupo = "Grupo";
    public static final String KEY_CodigoCierre = "CodigoCierre";

    public static final String KEY_CodigoRevision = "CodigoRevision";
    public static final String KEY_CodigoRegistrador = "CodigoRegistrador";
    public static final String KEY_FechaCreacion = "FechaCreacion";
    public static final String KEY_FechaAprobacion = "FechaAprobacion";
    public static final String KEY_Horas = "Horas";
    public static final String KEY_Completa = "Completa";
    public static final String KEY_CodigoEstadoRevision = "CodigoEstadoRevision";

    public static final String DATABASE_CREATE_actividades= "create table IF NOT EXISTS "+ DATABASE_TABLE_actividades +"" +
            "("+ KEY_ROWID +" integer primary key AUTOINCREMENT, " +
            "  "+ KEY_Linea + " text, "+ KEY_CodigoEmpleado + " integer," +
            " "+ KEY_CodigoCliente +" integer , "+KEY_CodigoServicio+ " integer, "+KEY_CodigoNomina+" integer," +
            " "+ KEY_CodigoClase + " integer," +
            " "+KEY_Duracion+  " text,  "+ KEY_Fecha +  " text, "+ KEY_CodigoEstado + " integer,"+ KEY_CodigoDia + " integer,"+ KEY_Descripcion + " text, " +
            " "+ KEY_EditSequence + " text, "+ KEY_ListId + " text," +
            " "+ KEY_CodigoAprovador + " integer,"+ KEY_TxnID + " text,"+ KEY_Customer_FullName + " text, "+ KEY_ItemService_FullName + " text," +
            " "+ KEY_Class_FullName + " text,"+ KEY_PayrollItemWage_FullName + " text, "+ KEY_Entity_FullName + " text,"+ KEY_BillableStatus + " integer," +
            " "+ KEY_Paquete + " integer, "+ KEY_Grupo + " integer, "+ KEY_CodigoCierre + " integer,"+ KEY_CodigoRevision + " integer," +
            " "+ KEY_CodigoRegistrador + " integer,"+ KEY_FechaCreacion + " text,"+ KEY_FechaAprobacion + " text,"+ KEY_Horas + " text,"+ KEY_Completa + " integer," +
            ""+ KEY_CodigoEstadoRevision + " integer);";




}

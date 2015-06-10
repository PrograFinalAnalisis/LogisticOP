package com.qbtiempo.qbtiempo.qbtiempo.CargarReg_Actividades.Tablas;

/**
 * Created by Programador on 10/03/2015.
 */
public class TablaClases {


    public static final String DATABASE_TABLE_Clas = "clases";

    public static final String KEY_ROWID_Clas = "_id";
    public static final String KEY_Nombre_Clas = "Nombre";
    public static final String KEY_FullName_clas = "FullName";
    public static final String KEY_ClassId_clas = "ClassId";
    public static final String KEY_ParentId_clas = "ParentId";
    public static final String KEY_EditSecuence_clas = "EditSecuence";
    public static final String KEY_ListId_clas = "ListId";
    public static final String KEY_SubLevel_clas = "SubLevel";
    public static final String KEY_LastLevel_class = "LastLevel";
    public static final String KEY_Activo_clas = "Activo";

    public static final String DATABASE_CREATE_Clas= "create table IF NOT EXISTS "+ DATABASE_TABLE_Clas +"" +
            "("+ KEY_ROWID_Clas +" integer primary key, " +
            "  "+ KEY_Nombre_Clas + " text, "+ KEY_FullName_clas + " text," +
            " "+ KEY_ClassId_clas +" text , "+ KEY_ParentId_clas + " text, "+ KEY_EditSecuence_clas +" text," +
            " "+ KEY_ListId_clas + " text," +
            " "+ KEY_SubLevel_clas +  " text,  "+ KEY_LastLevel_class +  " text, "+ KEY_Activo_clas + " integer);";

}

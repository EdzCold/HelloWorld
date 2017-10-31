package com.volley.profuturo.en501863.learningproyectv07.db;

import android.provider.BaseColumns;

/**
 * Created by edrag on 15/10/2017.
 */

public abstract class TableData {

    public TableData(){}

    public static abstract class TablaUsuario implements BaseColumns
    {
        public static final String TABLE_NAME = "USUARIO";
        public static final String COLUMNS_USUARIO = "username";
        public static final String COLUMNS_NUMERO_EMPLEADO = "empleado";
        public static final String COLUMNS_FECHA_SESION = "fecha_inicio";
        public static final String COLUMNS_CONTRASENIA = "contrasenia";
        public static final String COLUMNS_IMAGE = "imagebase64";
        //public static final String COLUMNS_ISCHECKED = "isChecked";
    }

    public static abstract class TablaSession implements BaseColumns
    {
        public static final String TABLE_NAME = "SESION";
        public static final String COLUMNS_ID = "user_id";
        public static final String COLUMNS_NAME = "nombre";
        public static final String COLUMNS_FECHA_SESION = "fecha_inicio";
    }
}

package com.volley.profuturo.en501863.learningproyectv07.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import com.volley.profuturo.en501863.learningproyectv07.db.TableData.TablaUsuario;
import com.volley.profuturo.en501863.learningproyectv07.db.TableData.TablaSession;
import com.volley.profuturo.en501863.learningproyectv07.model.EmpleadoInformacion;
//import com.volley.profuturo.en501863.learningproyectv07.model.EmpleadoInformacion;

/**
 * Created by edrag on 15/10/2017.
 */

public class DataBaseSqlHelper extends SQLiteOpenHelper {

    private static final int VERSION_DATABASE = 1;
    private static final String NAME_DATABASE = "learning_db";

    //Create table AppInformation
    private static final String SQL_TABLE_CREATE_INFORMATION =
            "CREATE TABLE " + TablaUsuario.TABLE_NAME + " (" +
                    TablaUsuario.COLUMNS_USUARIO + " TEXT, " +
                    TablaUsuario.COLUMNS_NUMERO_EMPLEADO + " TEXT, " +
                    TablaUsuario.COLUMNS_FECHA_SESION + " TEXT, " +
                    //TablaUsuario.COLUMNS_ISCHECKED + " BOOLEAN, " +
                    TablaUsuario.COLUMNS_IMAGE + " TEXT, " +
                    TablaUsuario.COLUMNS_CONTRASENIA + " TEXT);";


    //Create table UserSession

    private static final String SQL_TABLE_CREATE_SESSION =
            "CREATE TABLE " + TablaSession.TABLE_NAME + " (" +
                    TablaSession.COLUMNS_ID + " TEXT, " +
                    TablaSession.COLUMNS_NAME + " TEXT, " +
                    TablaSession.COLUMNS_FECHA_SESION + " TEXT);";


    public DataBaseSqlHelper(Context context) {
        super(context, NAME_DATABASE, null, VERSION_DATABASE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_TABLE_CREATE_INFORMATION);
        db.execSQL(SQL_TABLE_CREATE_SESSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long registrarUsuario(String usuario, String contrasenia, String numEmpleado, String date) { //NOTE utilizar y comprobar parametros
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TablaUsuario.COLUMNS_USUARIO, "GNP" + usuario);
        cv.put(TablaUsuario.COLUMNS_NUMERO_EMPLEADO, numEmpleado);
        cv.put(TablaUsuario.COLUMNS_CONTRASENIA, contrasenia);
        cv.put(TablaUsuario.COLUMNS_FECHA_SESION, date);

//      cv.put(TablaUsuario.COLUMNS_IMAGE, path);
//      Log.d("COLUMNS_IMAGE_PATH", path);
//      cv.put(TablaUsuario.COLUMNS_ISCHECKED, false);
//      Log.d("COLUMNS_ISCHECKED", false + "");

        return db.insert(TablaUsuario.TABLE_NAME, null, cv);

    }

    public ArrayList<EmpleadoInformacion> recuperarDatos(Context context) {  //NOTE utilizar y comprobar parametros
        ArrayList<EmpleadoInformacion> empleadoItem = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {TablaUsuario.COLUMNS_USUARIO,
                TablaUsuario.COLUMNS_NUMERO_EMPLEADO,
                TablaUsuario.COLUMNS_CONTRASENIA,
                TablaUsuario.COLUMNS_IMAGE,
                TablaUsuario.COLUMNS_FECHA_SESION};

        //String selection = TablaUsuario.COLUMNS_USUARIO + " = ?";
        //String[] selectionArgs = {usuario};

        Cursor cursor = db.query(TablaUsuario.TABLE_NAME,
                projection,
                null,
                null, null, null, null);

        if (cursor.getCount() == 0) {
            Toast.makeText(context, "No existe ningun registro", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                EmpleadoInformacion empleado = new EmpleadoInformacion();

                empleado.setUsuario(cursor.getString(cursor.getColumnIndex(TablaUsuario.COLUMNS_USUARIO)));
                empleado.setNumEmpleado(cursor.getString(cursor.getColumnIndex(TablaUsuario.COLUMNS_NUMERO_EMPLEADO)));
                empleado.setFecha_inicio(cursor.getString(cursor.getColumnIndex(TablaUsuario.COLUMNS_FECHA_SESION)));
                empleado.setImagePath(cursor.getString(cursor.getColumnIndex(TablaUsuario.COLUMNS_IMAGE)));
//              empleado.setChecked(!"false".equals(cursor.getColumnIndex(TablaUsuario.COLUMNS_ISCHECKED)));
                empleadoItem.add(empleado);
                Log.d("cursorInformation: ", empleado.toString());
            }
        }
        return empleadoItem;
    }

    public void eliminarTablaRegistros() {
        SQLiteDatabase db = this.getWritableDatabase();
        //ContentValues cv;
        db.delete(TablaUsuario.TABLE_NAME, null, null);
    }

    public Cursor consultarUsuarios(DataBaseSqlHelper dop) {
        SQLiteDatabase sdb = dop.getReadableDatabase();

        String[] colums = {
                TablaUsuario.COLUMNS_USUARIO,
                TablaUsuario.COLUMNS_CONTRASENIA,
                TablaUsuario.COLUMNS_FECHA_SESION
        };

        Cursor cursor = sdb.query(TablaUsuario.TABLE_NAME, colums, null, null, null, null, null);
        return cursor;
    }

    public int guardatImagen(String base64) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TablaUsuario.COLUMNS_USUARIO, base64);

        return db.update(TablaUsuario.TABLE_NAME, cv, "where ", null);
    }

    public String getFechaSession(Context context) {
        ArrayList<EmpleadoInformacion> empleadoItem = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String fechaSession = "";
        String[] projection = {TablaUsuario.COLUMNS_FECHA_SESION};

        Cursor cursor = db.query(TablaUsuario.TABLE_NAME,
                projection,
                null,
                null, null, null, null);

        if (cursor.getCount() == 0) {
            Toast.makeText(context, "No existe ningun registro", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                EmpleadoInformacion empleado = new EmpleadoInformacion();

                empleado.setFecha_inicio(cursor.getString(cursor.getColumnIndex(TablaUsuario.COLUMNS_FECHA_SESION)));
                fechaSession = empleado.getFecha_inicio();
                Log.d("cursorInformation: ", empleado.toString());
            }
        }
        return fechaSession;
    }

//
//    public static final String TABLE_NAME = "SESION";
//    public static final String COLUMNS_ID = "user_id";
//    public static final String COLUMNS_NAME = "nombre";
//    public static final String COLUMNS_IMAGE_BASE64 = "imagebase64";
//    public static final String COLUMNS_FECHA_SESION = "fecha_inicio";

    public long registrarSession(String idSession, String nombre, String dateSession) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TablaSession.COLUMNS_ID, idSession);
        cv.put(TablaSession.COLUMNS_NAME, nombre);
        cv.put(TablaSession.COLUMNS_FECHA_SESION, dateSession);

        return db.insert(TablaSession.TABLE_NAME, null, cv);
    }
}

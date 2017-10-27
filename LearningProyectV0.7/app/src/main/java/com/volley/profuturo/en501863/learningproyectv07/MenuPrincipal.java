package com.volley.profuturo.en501863.learningproyectv07;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.volley.profuturo.en501863.learningproyectv07.db.DataBaseSqlHelper;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        DataBaseSqlHelper dop = new DataBaseSqlHelper(getApplication());
        Cursor registros = dop.consultarUsuarios(dop);

        if(registros.getCount()>0){
            Toast.makeText(getApplication(),"Hay Registro Nuevo", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplication(),"No Hay Registro Nuevo", Toast.LENGTH_LONG).show();
        }

    }
}

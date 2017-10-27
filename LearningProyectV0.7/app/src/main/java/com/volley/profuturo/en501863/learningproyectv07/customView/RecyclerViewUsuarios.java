package com.volley.profuturo.en501863.learningproyectv07.customView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.volley.profuturo.en501863.learningproyectv07.MainActivity;
import com.volley.profuturo.en501863.learningproyectv07.R;
import com.volley.profuturo.en501863.learningproyectv07.adapter.AdapterUsuario;
import com.volley.profuturo.en501863.learningproyectv07.db.DataBaseSqlHelper;
import com.volley.profuturo.en501863.learningproyectv07.model.EmpleadoInformacion;

import java.util.ArrayList;

//import com.example.edrag.learningproyectv03.R;
//import com.example.edrag.learningproyectv03.adapter.AdapterUsuario;
//import com.example.edrag.learningproyectv03.db.DataBaseSqlHelper;
//import com.example.edrag.learningproyectv03.model.EmpleadoInformacion;

/**
 * Created by edrag on 16/10/2017.
 */

public class RecyclerViewUsuarios extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerViewUsuarios;
    private RecyclerView.Adapter mAdapter;
    private Button salir;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_usuarios);

        context = this;
        salir = (Button) findViewById(R.id.logout_btn);

        salir.setOnClickListener(this);

        Context context = this;
        recyclerViewUsuarios = (RecyclerView) findViewById(R.id.recyclerEmpleado_rv);

        LinearLayoutManager linearManager = new LinearLayoutManager(RecyclerViewUsuarios.this);
        recyclerViewUsuarios.setLayoutManager(linearManager);

        ArrayList<EmpleadoInformacion> empleado = new DataBaseSqlHelper(RecyclerViewUsuarios.this).recuperarDatos(context);


        AdapterUsuario adapter = new AdapterUsuario(RecyclerViewUsuarios.this, empleado);
        recyclerViewUsuarios.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logout_btn:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}

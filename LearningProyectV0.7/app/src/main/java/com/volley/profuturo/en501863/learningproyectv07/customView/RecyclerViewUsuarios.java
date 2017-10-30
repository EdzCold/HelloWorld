package com.volley.profuturo.en501863.learningproyectv07.customView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    //variables para ContentAccessor
    static final String TABLE_VICS = "vics";
    static final String COLUMN_ID = "_ID";
    static final String COLUMN_NAME = "name";
    static final String COLUMN_IMAGE_BASE64 = "imagebase64";
    static final Uri CONTENT_URI = Uri.parse("content://com.en501863.contentproviders.provider/" + TABLE_VICS);
    LinearLayout linearLayout;

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
        linearLayout = (LinearLayout) findViewById(R.id.informacionContentProvider);
        updateList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout_btn:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    public void updateList() {
        linearLayout.removeAllViews();

        Cursor cursor = getContentResolver().query(CONTENT_URI, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                //String base64 = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_BASE64));

                TextView textView = getNewTextView(id, name);
                linearLayout.addView(textView);
            } while (cursor.moveToNext());
            cursor.close();
        }

    }

    private TextView getNewTextView(String id, String name) {
        TextView textView = new TextView(this);
        textView.setText(id + "  " + name);
        textView.setTextSize(24f);
        return textView;
    }

}

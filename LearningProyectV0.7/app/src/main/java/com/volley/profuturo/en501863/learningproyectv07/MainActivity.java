package com.volley.profuturo.en501863.learningproyectv07;

import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.volley.profuturo.en501863.learningproyectv07.volley.RequestLogin;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //variables para requestLogin
    private EditText usuario;
    private EditText contrasenia;
    private Button enviar;
    private RequestLogin requesPrepared;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = (EditText) findViewById(R.id.usuario_edt);
        contrasenia = (EditText) findViewById(R.id.contrasenia_edt);
        enviar = (Button) findViewById(R.id.enviar_btn);


        usuario.setText("004173");
        contrasenia.setText("Profutur0");

        requesPrepared = new RequestLogin();

        enviar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.enviar_btn:
                Log.d("ClickListenerLogin", "");
                requesPrepared.consumirServicioLogin(getApplicationContext(), usuario.getText().toString(), contrasenia.getText().toString());
                break;
        }
    }

    public void mostrarAlerta(String titulo, String mensaje) {
        DialogFragment dialogFragment = new MensajeDialogFragment(titulo, mensaje);
        dialogFragment.show(getSupportFragmentManager(), "MensajeDialogFragment");
    }
}

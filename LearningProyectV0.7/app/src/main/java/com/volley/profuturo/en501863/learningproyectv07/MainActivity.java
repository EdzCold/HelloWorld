package com.volley.profuturo.en501863.learningproyectv07;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.volley.profuturo.en501863.learningproyectv07.customView.RecyclerViewUsuarios;
import com.volley.profuturo.en501863.learningproyectv07.db.DataBaseSqlHelper;
import com.volley.profuturo.en501863.learningproyectv07.volley.Constant;
import com.volley.profuturo.en501863.learningproyectv07.volley.RequestLogin;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //variables para requestLogin
    private EditText usuario;
    private EditText contrasenia;
    private Button enviar;
    private RequestLogin requesPrepared;
    private DataBaseSqlHelper bddh;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        bddh = new DataBaseSqlHelper(context);

        usuario = (EditText) findViewById(R.id.usuario_edt);
        contrasenia = (EditText) findViewById(R.id.contrasenia_edt);
        enviar = (Button) findViewById(R.id.enviar_btn);

        enviar.setOnClickListener(this);
        usuario.setText("004173");
        contrasenia.setText("Profutur0");

        String fechaInicio = Util.getDate();

        requesPrepared = new RequestLogin();


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

    // Sesion iniciada
//
//    Toast.makeText(context, Constant.REGISTRO_OK, Toast.LENGTH_LONG).show();
//    Intent intent = new Intent(context, RecyclerViewUsuarios.class);
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                    context.startActivity(intent);

//
//    /**
//     * type of format that return
//     * dd/mm/yyyy - hh:mm:ss
//     *
//     * @param dateLastLoging
//     * @return true if is valid
//     */
//    public boolean limiteSession(String dateLastLoging) {
//
//        Log.d("customDate: ", dateFirstLogin);
//
////        dateFirstLogin  dateLastLoging;
////        boolean correcto = false;
////        long diferencia = (Math.abs(date1.getTime() - date2.getTime())) / 1000;
////        long limit = (60 * 1000) / 1000L;//limite de tiempo
////
////
////        if (diferencia <= limit) {
////            correcto = true;
////        }
//        return correcto;
//    }


}

package com.volley.profuturo.en501863.learningproyectv07.volley;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.volley.profuturo.en501863.learningproyectv07.MainActivity;
import com.volley.profuturo.en501863.learningproyectv07.Util;
import com.volley.profuturo.en501863.learningproyectv07.customView.RecyclerViewUsuarios;
import com.volley.profuturo.en501863.learningproyectv07.db.DataBaseSqlHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

//import com.example.edrag.learningproyectv03.customView.RecyclerViewUsuarios;
//import com.example.edrag.learningproyectv03.db.DataBaseSqlHelper;

/**
 * Created by edrag on 15/10/2017.
 */

public class RequestLogin {

    private DataBaseSqlHelper bddh;
    private Context context;
    private String usuario;
    private String contrasenia;

    public void consumirServicioLogin(final Context context, final String usuario, final String contrasenia) {
//      String URL_RELATIVE = "mb/cusp/rest/autenticacionUsuario";
//      Log.d("consumirServicioLogin", " status: true ");

        this.context = context;
        this.usuario = usuario;
        this.contrasenia = contrasenia;

        HashMap mapJson = generateFormatCode();
        JSONObject jsonRequest = new JSONObject(mapJson);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                Constant.URL_BASE, //+ URL_RELATIVE,
                jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("inOnResponse: ", response.toString());

                        if (response.has("confirmacion")) {  //Validar json if(response is OK)

                            String fechaInicio = Util.getDate();

//                            StoreFile storage = new StoreFile();
//                            ArrayList<Bitmap> map = new ArrayList<>();
//                            Bitmap icon1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.icon_profuturo);
//                            map.add(icon1);

                            long okRegistro = registrarElementosTest(response, fechaInicio);

                            if (okRegistro > 0) {
                                Toast.makeText(context, Constant.REGISTRO_OK, Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(context, RecyclerViewUsuarios.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                context.startActivity(intent);
                            }


                        } else if (response.has("Exception")) {
                            try {
                                response.getString("Exception");
                                Toast.makeText(context, response.getString("Exception"), Toast.LENGTH_SHORT).show();
//                                String titulo = "Exception";
//                                mostrarAlerta(titulo, response.getString("Exception"));

                            } catch (JSONException ex) {
                                Log.d("exceptionUsuario", "---------------------------------");

                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("inOnErrorResponse: ", volleyError.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Basic cndzcHJheGlzcDpQcjR4MXMjdTVS");  //NOTE add HEADER
                return params;
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                Map<String, String> responseHeaders = response.headers;
                return super.parseNetworkResponse(response);
            }
        };

        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public long registrarElementosTest(JSONObject response, String fechaInicio) {
        //registro return - 0 false && 1 true
        long okRegistro = 0;

        bddh = new DataBaseSqlHelper(context);
        bddh.eliminarTablaRegistros();
        long okRegistroSession = 0;

        okRegistro = bddh.registrarSession("1", "EN501863", fechaInicio);

        try {
            okRegistro = bddh.registrarUsuario(
                    usuario,
                    contrasenia,
                    response.getString("numeroEmpleado"),
                    fechaInicio);

            ////////////////////// Segundo registro 2

            bddh.registrarUsuario(
                    usuario,
                    contrasenia,
                    response.getString("numeroEmpleado"),
                    fechaInicio);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("okRegistro: ", okRegistro + "");

        return okRegistro;
    }

    /**
     * Formato Json of service
     * {"rqt": {"aplicacion": "avanza",
     * "usuario": "004173",
     * "contrasena": "Profutur0"}
     * }
     *
     * @return HashMap for transform to JsonObject
     */
    public HashMap generateFormatCode() {
        //
            /*
        */
        HashMap map = new HashMap();
        HashMap mapJson = new HashMap();

        map.put("aplicacion", "avanza");
        map.put("usuario", usuario);
        map.put("contrasena", contrasenia);
        mapJson.put("rqt", map);

        return mapJson;
    }

}

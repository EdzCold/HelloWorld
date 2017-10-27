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

    public void consumirServicioLogin(final Context context, final String usuario, final String contrasenia) {
        //String URL_RELATIVE = "mb/cusp/rest/autenticacionUsuario";

        Log.d("consumirServicioLogin", " status: true ");

        // Formato Json
            /*{"rqt": {"aplicacion": "avanza",
                       "usuario": "004173",
                       "contrasena": "Profutur0"
                       }
               }
        */

        HashMap map = new HashMap();
        HashMap mapJson = new HashMap();

        map.put("aplicacion", "avanza");
        map.put("usuario", usuario);
        map.put("contrasena", contrasenia);
        mapJson.put("rqt", map);

        final JSONObject jsonRequest = new JSONObject(mapJson);

        //delete
        Log.d("JsonGenerated: ", jsonRequest.toString());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                Constant.URL_BASE, //+ URL_RELATIVE,
                jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("inOnResponse: ", response.toString());

                        if (response.has("confirmacion")) {  //Validar json if(response is OK)
//                            Date d = new Date();
//                            CharSequence fechaInicio = DateFormat.format("MMMM d, yyyy ", d.getTime());
//                            CharSequence fechaInicio = "";

                            Calendar c = new GregorianCalendar();
                            Date date = new Date();
                            c.setTime(date);

                            String fechaInicio = c.get(Calendar.DATE) +" "+ c.get(Calendar.HOUR) +" "+ c.get(Calendar.MINUTE) +
                                    " "+ c.get(Calendar.SECOND) +" "+ c.get(Calendar.MILLISECOND); //+  df.MILLISECOND_FIELD

//                            Log.d("Fecha1", fechaInicio );
//                            Log.d("Fecha2", c.get(Calendar.LONG) + "");

                            bddh = new DataBaseSqlHelper(context);
                            bddh.eliminarTablaRegistros();

//                            StoreFile storage = new StoreFile();
//                            ArrayList<Bitmap> map = new ArrayList<>();
//                            Bitmap icon1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.icon_profuturo);


                            //                          map.add(icon1);


                            try {

                                //                            for (Bitmap m : map) {
                                //                            Log.d("path_img_1: ", m.getByteCount() + "");

//                                    String path_img = storage.saveToInternalStorage(context, m);
                                //                          Log.d("imagen", path_img);
                                //                        Log.d("path_img_2: ", response.getString("numeroEmpleado"));

                                long okRegistro = 0;
                                okRegistro = bddh.registrarUsuario(
                                        usuario,
                                        contrasenia,
                                        response.getString("numeroEmpleado"),
                                        fechaInicio.toString());

////////////////////// Segundo registro 2
                                bddh.registrarUsuario(
                                        usuario,
                                        contrasenia,
                                        response.getString("numeroEmpleado"),
                                        fechaInicio);

                                Log.d("okRegistro: ", okRegistro + "");

                                if (okRegistro > 0) {
                                    Toast.makeText(context, Constant.REGISTRO_OK, Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(context, RecyclerViewUsuarios.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    context.startActivity(intent);
                                }

                            } catch (JSONException ex) {
                                Log.d("JSON_Eror: ", ex.toString());
                                ex.printStackTrace();
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
}

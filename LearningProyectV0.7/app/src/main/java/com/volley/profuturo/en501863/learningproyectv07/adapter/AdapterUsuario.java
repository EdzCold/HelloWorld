package com.volley.profuturo.en501863.learningproyectv07.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.volley.profuturo.en501863.learningproyectv07.R;
import com.volley.profuturo.en501863.learningproyectv07.StoreFile;
import com.volley.profuturo.en501863.learningproyectv07.model.EmpleadoInformacion;

import java.util.ArrayList;

//import android.support.v7.widget

/**
 * Created by edrag on 15/10/2017.
 */

public class AdapterUsuario extends RecyclerView.Adapter<AdapterUsuario.ViewHolder> implements View.OnClickListener {

    private Context context;
    private ArrayList<EmpleadoInformacion> empleadoItem;
    private String path;
    public ImageView image;
    private EmpleadoInformacion empleado;

    public AdapterUsuario(Context context, ArrayList<EmpleadoInformacion> empleadoItem) {
        this.context = context;
        this.empleadoItem = empleadoItem;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.empleado_item, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        empleado = empleadoItem.get(position);

        holder.usuario.setText(empleado.getUsuario().toString());
        holder.numEmpeado.setText(empleado.getNumEmpleado());
        holder.fechaSesion.setText(empleado.getFecha_inicio());
//        holder.checkBoxUsuario.setActivated(empleado.isChecked());
        path = empleado.getImagePath();

        holder.accion.setOnClickListener(this);
        holder.foto.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.actionFoto_btn:
                Toast.makeText(context,"Funcion de tomar foto ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ActionEmpleado_btn:
                StoreFile storage = new StoreFile();
                Toast.makeText(context,"Funcion de mostrar foto ", Toast.LENGTH_SHORT).show();
                break;
        }

    }


    @Override
    public int getItemCount() {
        return empleadoItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView usuario, numEmpeado, fechaSesion;
        public Button accion, foto;
        public CheckBox checkBoxUsuario;

        public ViewHolder(View itemView) {
            super(itemView);

            usuario = (TextView) itemView.findViewById(R.id.usuarioEmpleado_txt);
            numEmpeado = (TextView) itemView.findViewById(R.id.numEmpleado_txt);
            fechaSesion = (TextView) itemView.findViewById(R.id.fechaInicio_txt);
            accion = (Button) itemView.findViewById(R.id.ActionEmpleado_btn);
            foto = (Button) itemView.findViewById(R.id.actionFoto_btn);
            checkBoxUsuario = (CheckBox) itemView.findViewById(R.id.check_cb);
            image = (ImageView) itemView.findViewById(R.id.image_view);

        }


    }
//
//    public interface butonElementEmpleado {
//
//    }


}

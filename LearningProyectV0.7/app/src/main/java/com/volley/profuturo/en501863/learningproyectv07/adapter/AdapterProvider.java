package com.volley.profuturo.en501863.learningproyectv07.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.volley.profuturo.en501863.learningproyectv07.R;
import com.volley.profuturo.en501863.learningproyectv07.model.ContentInformation;

import java.util.ArrayList;

/**
 * Created by EN501863 on 31/10/2017.
 */

public class AdapterProvider extends RecyclerView.Adapter<AdapterProvider.ViewHolder> {

    private Context context;
    private ArrayList<ContentInformation> contentInformation;
    private setOnProviderListener content;

    static final String TABLE_VICS = "vics";
    static final String COLUMN_ID = "_ID";
    static final String COLUMN_NAME = "name";
    static final String COLUMN_IMAGE_BASE64 = "imagebase64";

    static final String COLUMN_PHONE_NUMBER = "phonenumber";
    static final String COLUMN_LASTNAME = "lastname";
    static final String COLUMN_CODIGO_POSTAL = "codigopostal";
    static final String COLUMN_ADDRESS = "address";

    static final Uri CONTENT_URI = Uri.parse("content://com.en501863.contentproviders.provider/" + TABLE_VICS);

    public AdapterProvider(Context context, setOnProviderListener listener) {
        this.context = context;
        contentInformation = new ArrayList<>();
        this.content = listener;
        ContentInformation aux;

        Cursor cursor = context.getContentResolver().query(CONTENT_URI, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                aux = new ContentInformation();
                aux.setNombre(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                aux.setId(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
                aux.setBase64(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_BASE64)));

                aux.setTelefono(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER)));
                aux.setLastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)));
                aux.setCodigPostal(cursor.getString(cursor.getColumnIndex(COLUMN_CODIGO_POSTAL)));
                aux.setDireccion(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));

                contentInformation.add(aux);
            } while (cursor.moveToNext());
            cursor.close();
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.content_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final ContentInformation item = contentInformation.get(i);

        viewHolder.texto.setText(item.getNombre());

//        viewHolder.texto.setText(item.getLastName());
//        viewHolder.texto.setText(item.getTelefono());
//        viewHolder.texto.setText(item.getCodigPostal());
//        viewHolder.texto.setText(item.getDireccion());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content.cargarInfo(item);
            }
        });

    }

    @Override
    public int getItemCount() {
        return contentInformation.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView texto;

        public ViewHolder(View itemView) {
            super(itemView);
            texto = (TextView) itemView.findViewById(R.id.texto);
        }
    }

    public interface setOnProviderListener {
        void cargarInfo(ContentInformation v);
    }


}

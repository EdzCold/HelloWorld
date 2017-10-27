package com.volley.profuturo.en501863.myapplication;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

public class MainActivity extends AppCompatActivity {

    private TextView ruta;
    private Button guardar;
    private Button mostrar;
    private ImageView contenido;
    private int cambianum = 0;
    private Context context;
    private String path_image;
    private Bitmap icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ruta = (TextView) findViewById(R.id.mostrar_ruta);
        guardar = (Button) findViewById(R.id.guardar_btn);
        mostrar = (Button) findViewById(R.id.mostrar_btn);
        contenido = (ImageView) findViewById(R.id.imagen);


        icon = BitmapFactory.decodeResource(getResources(),R.drawable.icon_profuturo);

//        map = ((BitmapDrawable) getDrawable(R.drawable.icon_profuturo).getCurrent()).getBitmap();

        context = this;

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path_image = saveToInternalStorage(icon);
                ruta.setText(path_image);
                Log.d("pathImage: ", path_image);
            }
        });

        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImageFromStorage(path_image);
                Log.d("pathLoadImage: ", "---------------------------------");
            }
        });

    }

    public String saveToInternalStorage(Bitmap bitmapImage) {
        ContextWrapper cw = new ContextWrapper(context);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_APPEND);
        // Create imageDir

        File mypath = new File(directory, "profile.jpg");
        Log.d("profile: ", mypath.getAbsolutePath());

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    private void loadImageFromStorage(String path)
    {

        try {
            File f=new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            ImageView img=(ImageView)findViewById(R.id.load_image);
            img.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}
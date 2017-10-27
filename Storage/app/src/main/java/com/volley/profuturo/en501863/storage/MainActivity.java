package com.volley.profuturo.en501863.storage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView pathText;
    private ImageView contenido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = this;

        // contenido = (ImageView) findViewById(R.id.image_Iv);
        pathText = (TextView) findViewById(R.id.path_txt);


        Drawable drawable = getDrawable(R.drawable.icon_profuturo);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();

        String text = guardarImagen(bitmap);

        Log.d("pathImage", text);

        pathText.setText(text);

    }



    public String guardarImagen(Bitmap image)
    {

        // File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File directory = new File("image");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(directory);
            // Use the compress method on the BitMap object to write image to the OutputStream
            image.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return directory.getAbsolutePath();
    }
}

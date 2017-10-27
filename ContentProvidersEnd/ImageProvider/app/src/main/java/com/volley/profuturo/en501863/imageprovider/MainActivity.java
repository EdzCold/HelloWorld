package com.volley.profuturo.en501863.imageprovider;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private Button addButton;
    private ImageView imageView;
    private static final int TAKE_PICTURE = 1;
    private Uri imageUri;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = (Button) findViewById(R.id.addButton);
        imageView = (ImageView) findViewById(R.id.image_view);

        final EditText editText = (EditText) findViewById(R.id.nameEditText);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = editText.getText().toString();
                if (name.length() > 0) {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 1);
                    updateList();
                    editText.setText("");
                    name = "";
                } else {
                    Toast.makeText(getBaseContext(), "debes introducir un nombre", Toast.LENGTH_SHORT).show();
                }
            }
        });


        updateList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
            byte[] b = baos.toByteArray();

            addContact(name, encodeImage(b));
        }
    }

// comvertir una imagen a base 64

    public String encodeImage(byte[] byteArrayImage) {
        return Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
    }

    private void addContact(String name, String imageBase64) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name);
        values.put(DatabaseHelper.COLUMN_Image_BASE64, imageBase64);

        Uri uri = getContentResolver().insert(MyContentProvider.CONTENT_URI, values);
        if (uri != null) {
            Toast.makeText(this, uri.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void updateList() {
        linearLayout.removeAllViews();

        Cursor cursor = getContentResolver().query(MyContentProvider.CONTENT_URI, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));

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

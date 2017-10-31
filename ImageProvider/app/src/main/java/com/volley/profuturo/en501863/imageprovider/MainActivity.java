package com.volley.profuturo.en501863.imageprovider;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.volley.profuturo.en501863.imageprovider.model.Datos;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    static final int REQUEST_IMAGE_CAPTURE = 1;

    ArrayList<EditText> edidText;
    private LinearLayout linearLayout;
    private EditText nameEditText, lastNameEditText, phoneNumberEditText, addressEditText, codigoPostalEditText;
    private Button addButton;
    private ImageView imageView;

    private Datos elemento;
    private byte[] b;
    private String imageEncode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edidText = new ArrayList<>();

        addButton = (Button) findViewById(R.id.addButton);
        imageView = (ImageView) findViewById(R.id.image_view);

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        phoneNumberEditText = (EditText) findViewById(R.id.TelEditText);
        addressEditText = (EditText) findViewById(R.id.DireccionEditText);
        codigoPostalEditText = (EditText) findViewById(R.id.CodigoPostalEditText);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        linearLayout.setOnClickListener(this);
        addButton.setOnClickListener(this);
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
            b = baos.toByteArray();
            encodeImage(b);
            addContact(elemento);
            updateList();
        }
    }

// comvertir una imagen a base 64

    public Datos checkData(){
        elemento = new Datos();

        elemento.setName(nameEditText.getText().toString());
        //nameEditText.getText().toString().isEmpty();
        elemento.setLastName(lastNameEditText.getText().toString());
        elemento.setPhoneNumber(phoneNumberEditText.getText().toString());
        elemento.setAddress(addressEditText.getText().toString());
        elemento.setCodigoPostal(codigoPostalEditText.getText().toString());

        return elemento;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addButton:
                checkData();

                if (elemento.getName().length() > 0) {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 1);

                    nameEditText.setText("");
                    lastNameEditText.setText("");
                    phoneNumberEditText.setText("");
                    addressEditText.setText("");
                    codigoPostalEditText.setText("");

                } else {
                    Toast.makeText(getBaseContext(), "debes introducir un nombre", Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.linearLayout:

                break;
        }

        Toast.makeText(this, "id: " + v.getId() , Toast.LENGTH_SHORT).show();
    }

    public void encodeImage(byte[] byteArrayImage) {
        imageEncode = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
    }

    private void addContact(Datos data) {
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.COLUMN_NAME, data.getName());
        values.put(DatabaseHelper.COLUMN_IMAGE_BASE64, imageEncode);

        values.put(DatabaseHelper.COLUMN_LASTNAME, data.getLastName());
        Log.d("AAAAAAA", data.getLastName());
        Log.d("AAAAAAA", values.get(DatabaseHelper.COLUMN_LASTNAME).toString());

        values.put(DatabaseHelper.COLUMN_ADDRESS, data.getAddress());
        Log.d("BBBBB", data.getAddress());
        Log.d("BBBBB", values.get(DatabaseHelper.COLUMN_LASTNAME).toString());

        values.put(DatabaseHelper.COLUMN_CODIGO_POSTAL, data.getCodigoPostal());
        Log.d("CCCCCC", data.getCodigoPostal());
        Log.d("CCCCCC", values.get(DatabaseHelper.COLUMN_LASTNAME).toString());

        values.put(DatabaseHelper.COLUMN_PHONE_NUMBER, data.getPhoneNumber());
        Log.d("DDDDD", data.getPhoneNumber() );
        Log.d("DDDDD", values.get(DatabaseHelper.COLUMN_LASTNAME).toString());

        Uri uri = getContentResolver().insert(MyContentProvider.CONTENT_URI, values);

        Log.d("uriConterProvider", "-----" + uri.toString());
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
                String lastName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LASTNAME));
                String address = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ADDRESS));
                String codigoPostal = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CODIGO_POSTAL));
                String phoneNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE_NUMBER));

                // String base64 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_IMAGE_BASE64));

                TextView textView = getNewTextView(id, name, lastName, address, codigoPostal, phoneNumber);
                linearLayout.addView(textView);
            } while (cursor.moveToNext());
            cursor.close();
        }
    }

    private TextView getNewTextView(String id, String name, String lastName, String address, String codigoPostal, String phoneNumber) {
        TextView textView = new TextView(this);
        Log.d("nombreTest", "---- " + name);
        textView.setText(id + "  " + name + "  " + lastName + "  " + address + "  " + codigoPostal + "  " + phoneNumber + "  ");
        textView.setTextSize(24f);
        return textView;
    }

}

package com.example.android.assignment2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadActivity extends AppCompatActivity {
    EditText imageURL;
    EditText titleText;
    String title;
    public static byte [] ourByte;
    String urlText;
    MyDB db;
    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new MyDB(this);
        setContentView(R.layout.download);
        imageURL = findViewById(R.id.URL);
        titleText = findViewById(R.id.title);
        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        //networkInfo = connectivityManager.getActiveNetworkInfo();
    }

    public void downloadImage(View view) {
        networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            urlText = imageURL.getText().toString();
            title = titleText.getText().toString();
            if(urlText.isEmpty() || title.isEmpty()){
                Toast.makeText(this,"Please make sure both fields are initialized",Toast.LENGTH_LONG).show();
                return;
            }
            try{
                //Log.d("in try1", "we got to try");
                int SDK_INT = Build.VERSION.SDK_INT;
                if(SDK_INT > 8){
                    StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }
                URL url = new URL(urlText);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                //con.setConnectTimeout(500);
                //con.setReadTimeout(500);
                //con.setRequestMethod("GET");
                con.connect();

                //Log.d("in try2", "What");
                InputStream is = con.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 0, stream);
                ourByte = stream.toByteArray();
                //Log.d("in try3", "set Byte");

            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.d("DownloadActivity", "URL connection not good");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
            //if(imageURL != null && titleText != null ) {
                //Log.d("wtf", title);
                if(ourByte == null){
                    Log.d("DownloadActivity", "Image byte is null");
                }
                else {
                    db.insert(title, ourByte);
                    Toast.makeText(this,"Successful Download", Toast.LENGTH_LONG).show();
                }
                db.close();

           // }
            //else{
            //    Toast.makeText(DownloadActivity.this, "Invalid URL", Toast.LENGTH_LONG).show();
            //}
        }
        else{
            Toast.makeText(DownloadActivity.this, "NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(DownloadActivity.this, MainActivity.class);
        startActivity(intent);
    }
}

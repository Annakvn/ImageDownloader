package com.example.android.assignment2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class ImageDownloader extends AsyncTask<String, Void, byte []> {

    public byte [] ourByte;
    @Override
    protected byte  [] doInBackground(String... strings) {
        try{
            URL url = new URL(strings[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(500);
            con.setReadTimeout(500);
            con.setRequestMethod("GET");
            con.connect();

            InputStream is = con.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 0, stream);
            ourByte = stream.toByteArray();
            Log.d("in try", "set Byte");

            return ourByte;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(byte[] ourByte)
    {
        super.onPostExecute(ourByte);
        Log.d("here", "we get here");
        //DownloadActivity.imageByte = ourByte;
        //if (ourByte == null){
          //  Log.d("onpost", "it is null");
        //}

    }
}

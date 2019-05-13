package com.example.android.assignment2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Blob;
import java.util.ArrayList;

public class PictureListAdapter extends ArrayAdapter<PicData>{
    private Context mContext;
    int mResource;
    public PictureListAdapter(Context context, int resource, ArrayList<PicData> objects){
        super(context, resource,objects);
        mContext = context;
        mResource = resource;
    }
    @SuppressLint("ViewHolder")
    public View getView(int position, View convertView, ViewGroup parent){
        String title = getItem(position).getTitle();
        Bitmap img = getItem(position).getImg();


        //PicData info = new PicData();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView txt = convertView.findViewById(R.id.textTitle);
        ImageView pic = convertView.findViewById(R.id.img);
        String log =  " Name: " + title;

        Log.d("String", log);

        txt.setText(title);
        pic.setImageBitmap(img);
        return convertView;

    }
}

package com.example.android.assignment2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class viewC extends AppCompatActivity {
    MyDB db;
    //ArrayList imageArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_c);
        db = new MyDB(this);
        ArrayList imageArr = new ArrayList();

        //ListView myListView = findViewById(R.id.List);
        List<PicData> pics = db.getEverything();
        for(PicData p : pics){
        String log =  " Name: " + p.getTitle();
        Log.d("Result: ",log);
        imageArr.add(p);}
        //ArrayList<PicData> picInfoList = new ArrayList<>();
        PictureListAdapter adapter = new PictureListAdapter(this, R.layout.screen_list, imageArr);
        ListView myListView = findViewById(R.id.List);
        myListView.setAdapter(adapter);
    }


    }

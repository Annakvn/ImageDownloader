package com.example.android.assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class range extends AppCompatActivity {

    MyDB db;
    int itemAmount;
    EditText start;
    EditText end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_range);
        db = new MyDB(this);
        start = findViewById(R.id.startingTxt);
        end = findViewById(R.id.endingTxt);
    }

    public void DisplayMethod(View view) {
        if(start.getText().toString().isEmpty() || end.getText().toString().isEmpty()){
            Toast.makeText(this,"Please enter both start and end of range",Toast.LENGTH_LONG).show();
            return;
        }
        else{
        String SR = start.getText().toString();
        String ER = end.getText().toString();
        int SRI = Integer.parseInt(SR);
        int ERI = Integer.parseInt(ER);
        //Log.d("SRI",String.valueOf(SRI));
        //Log.d("ERI",String.valueOf(ERI));
        //Log.d("itemAmount", String.valueOf(itemAmount));
        if ((SRI > 0 ) && (ERI >= SRI)){
            //Log.d("In if", "we got into if");
            ArrayList imageArr = new ArrayList();
            //Log.d("Before", "we got into if");
            List<PicData>pics = db.getRange(SRI,ERI);
            //Log.d("After", "we got into if");
            for(PicData p : pics){
                imageArr.add(p);
                //Log.d("infor","we add");
            }
            PictureListAdapter adapter = new PictureListAdapter(this, R.layout.screen_list, imageArr);
            ListView myListView = findViewById(R.id.ListRange);
            myListView.setAdapter(adapter);
            //Toast.makeText(this, "we got the range to workk ok", Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(this,"Improper Range", Toast.LENGTH_LONG).show();
        }
    }
}
}
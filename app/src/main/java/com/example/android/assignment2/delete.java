package com.example.android.assignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class delete extends AppCompatActivity {
    EditText id;
    EditText title;
    MyDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        db = new MyDB(this);
        id = findViewById(R.id.IdText);
        title = findViewById(R.id.titleText);
        //db.delete(0,null, null);
        //Toast.makeText(this,"deleted database", Toast.LENGTH_LONG).show();

    }


    public void deleting(View view) {
        if (id.getText().toString().length()>0 && title.getText().toString().isEmpty()){
            String idStr = id.getText().toString();
            int idINT = Integer.parseInt(idStr);
            if(db.checkID(idINT)) {
                db.delete(idINT, null);
                db.close();
                Intent intent = new Intent(delete.this, MainActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this,"ID does not exist in database", Toast.LENGTH_LONG).show();
            }
        }
        else if(id.getText().toString().isEmpty() && title.getText().toString().length()>0){
            String titleS = title.getText().toString();
            if (db.checkTitle(titleS)) {
                db.delete(0, titleS);
                db.close();
                Intent intent = new Intent(delete.this, MainActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(this,"Title does not exist in database", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(this,"Please enter id OR title", Toast.LENGTH_LONG).show();
        }

    }
}

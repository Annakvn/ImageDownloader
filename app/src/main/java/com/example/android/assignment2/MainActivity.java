package com.example.android.assignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void download(View view) {
        Intent intent = new Intent(MainActivity.this, DownloadActivity.class);
        startActivity(intent);
    }

    public void delete(View view) {
        Intent intent = new Intent(MainActivity.this, delete.class);
        startActivity(intent);
    }

    public void view(View view) {
        Intent intent = new Intent(MainActivity.this, viewC.class);
        startActivity(intent);
    }

    public void range(View view) {
        Intent intent = new Intent(MainActivity.this, range.class);
        startActivity(intent);
    }
}

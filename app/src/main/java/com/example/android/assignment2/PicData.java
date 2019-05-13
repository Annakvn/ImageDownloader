package com.example.android.assignment2;

import android.graphics.Bitmap;

import java.sql.Blob;

class PicData {
    private String title;
    private Bitmap img;
    private int id;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

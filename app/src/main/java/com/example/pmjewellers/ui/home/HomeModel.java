package com.example.pmjewellers.ui.home;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeModel {
    String image;
    String text;
    public HomeModel(String image , String text){
        this.image = image;
        this.text = text;
    }
    public HomeModel(){

    }
    public String getImage() {
        return image;

    }

    public void setImage(String image) {

        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

package com.example.pmjewellers.ui.home;



public class HomeModel {
    String image;
    String text;
    public HomeModel(String image , String text){
        this.image = image;
        this.text = text;
    }
    public HomeModel(String image){
        this.image = image;
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



package com.sgu.myapplication.models;

import android.widget.ImageView;

public class DataModel {
    private String firstName;
    private String lastName;
    private ImageView img;

    public DataModel(String firstName, String description, ImageView img) {
        this.firstName = firstName;
        this.lastName = description;
        this.img = img;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ImageView getImg(){ return img;}

    public void setImg(ImageView img){this.img = img;}

    public void updateVolley(){

    }
}

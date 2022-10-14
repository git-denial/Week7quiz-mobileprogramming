package com.sgu.myapplication.models;

import android.widget.ImageView;

import java.io.Serializable;

public class DataModel implements Serializable {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private ImageView img;

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    private String imgurl;

    public DataModel(int id, String email,String firstName, String description, String imgurl, ImageView img) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = description;
        this.img = img;
        this.imgurl = imgurl;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

package com.example.cc.retrofitpractice;

import com.google.gson.annotations.SerializedName;

//Model class contains java objects
public class Modelclass {

    //json key and variable name is same but differ it is @serialized

    private int userId;
    private Integer Id;
    private String title;
   @SerializedName("body")
    private String text;

    public Modelclass(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;

    }

//In Post request we create constructor instead of userId because it is generated by RestApi

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return Id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}

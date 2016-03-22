package com.example.bbg;


import java.io.Serializable;

/**
 * Created by Богдан on 16.11.2015.
 */
public class Product implements Serializable{

    private String title;
    private String describe_title;
    private String price;
    private byte[] Image;

    public String getDescribe_title() {
        return describe_title;
    }

    public void setDescribe_title(String describe_title) {
        this.describe_title = describe_title;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] image) {
        Image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

package com.example.bbg;


import java.util.Random;
import java.util.UUID;

/**
 * Created by Богдан on 12.11.2015.
 */
public class CategoryItem{

    private String textView;
    private byte[] imageView;
    private int mId;


    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }


    public byte[] getImageView() {
        return imageView;
    }

    public void setImageView(byte[] imageView) {
        this.imageView = imageView;
    }

    public String getTextView() {
        return textView;
    }

    public void setTextView(String textView) {
        this.textView = textView;
    }
}

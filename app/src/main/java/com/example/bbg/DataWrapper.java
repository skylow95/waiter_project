package com.example.bbg;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Богдан on 16.12.2015.
 */
public class DataWrapper implements Serializable {

    private ArrayList<Product> parliaments;

    public DataWrapper(ArrayList<Product> data) {
        this.parliaments = data;
    }

    public ArrayList<Product> getParliaments() {
        return this.parliaments;
    }

}
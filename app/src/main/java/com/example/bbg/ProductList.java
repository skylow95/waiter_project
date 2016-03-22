package com.example.bbg;

import android.content.Context;
import android.database.Cursor;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Богдан on 15.12.2015.
 */
public class ProductList {
    private ArrayList<ProductLab> mProductList;


    private static ProductList sProductList;
    private Context mAppContext;

    private ProductList(Context AppContext){

        mProductList = new ArrayList<ProductLab>();

        for(int i = 1; i <= 4;i++){
            ProductLab item = new ProductLab(AppContext,i);
            mProductList.add(item);
        }
    }

    public static ProductList get(Context AppContext){
        if(sProductList == null){
            sProductList = new ProductList(AppContext.getApplicationContext());
        }
        return sProductList;
    }

    public ArrayList<ProductLab> getProductLists(){
        return mProductList;
    }

    public ProductLab getProductList(int id){
        for(ProductLab c : mProductList){
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }
}

package com.example.bbg;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Богдан on 16.11.2015.
 */
public class ProductLab {
    protected DataBaseHelplerProduct sqlHelper;
    protected Cursor userCursor;
    private int id;
    private ArrayList<Product> mProducts;
    private Context mAppContext;


    public ProductLab (Context AppContext,int id){
        sqlHelper = new DataBaseHelplerProduct(AppContext);
        sqlHelper.create_db();

        try {
            sqlHelper.open();
            userCursor = sqlHelper.database.rawQuery("SELECT * FROM " + DataBaseHelplerProduct.TABLE + " WHERE " + DataBaseHelplerProduct.COLUMN_CATEGORY_ID + " =  " + id,null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mProducts = new ArrayList<Product>();
      // int s[] = {R.drawable.header,R.drawable.shushi3,R.drawable.shushi1};
        if(userCursor.moveToFirst()){
            while(!userCursor.isAfterLast()){
                Product item = new Product();
                item.setImage(userCursor.getBlob(4));
                item.setDescribe_title(userCursor.getString(2));
                item.setTitle(userCursor.getString(1));
                item.setPrice(userCursor.getString(3) + " грн.");
                mProducts.add(item);
                userCursor.moveToNext();
            }
        }
        for(int i = 0;i < 10; i++){

        }
        this.id = id;

    }

    public int getId()
    {
        return id;
    }

    public ArrayList<Product> getProducts()
    {
        return mProducts;
    }

}

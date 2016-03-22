package com.example.bbg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;

/**
 * Created by Богдан on 16.12.2015.
 */
public class BoxShopActivity extends ActionBarActivity {

    public Fragment createFragment(){
        DataWrapper dw = (DataWrapper) getIntent().getSerializableExtra(ProductListFragment.EXTRA_BOX_SHOP);
        int product_list_id = (int) getIntent().getSerializableExtra(ProductListFragment.EXTRA_PRODUCT_LIST_ID);
        String title =  getIntent().getStringExtra(ProductListFragment.EXTRA_CATEGORY_ITEM_TITLE);
        ArrayList<Product> mBoxList = dw.getParliaments();
        getSupportActionBar().setTitle("Корзина товарів");
        return new BoxShopFragment(mBoxList,product_list_id,title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boxshop_list);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        // Enabling Up / Back navigation
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.Fragment fragment = fm.findFragmentById(R.id.box_shop_container);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.box_shop_container, fragment).commit();
        }
    }
}

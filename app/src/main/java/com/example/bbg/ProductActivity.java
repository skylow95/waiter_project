package com.example.bbg;

import android.support.v4.app.Fragment;

import java.sql.SQLException;

/**
 * Created by Богдан on 19.11.2015.
 */
public class ProductActivity extends SingleFragmentActivity {


    @Override
    public Fragment createFragment() {
        int product_list_id = (int) getIntent().getSerializableExtra(ProductListFragment.EXTRA_PRODUCT_LIST_ID);
        String title = (String)getIntent().getSerializableExtra(ProductListFragment.EXTRA_CATEGORY_ITEM_TITLE);
        getSupportActionBar().setTitle(title);
        return ProductListFragment.newInstance(product_list_id,title);
    }

    @Override
    protected void onResume() {


        super.onResume();
    }


}

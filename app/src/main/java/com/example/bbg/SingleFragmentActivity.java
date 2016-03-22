package com.example.bbg;

import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Богдан on 19.11.2015.
 */
public abstract class SingleFragmentActivity extends ActionBarActivity {

    public static final String TAG = "ERROR! ";
    public abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_activity_fragment);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        // Enabling Up / Back navigation
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }

        FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.Fragment fragment = fm.findFragmentById(R.id.product_container);



        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.product_container, fragment).commit();
        }


    }


}

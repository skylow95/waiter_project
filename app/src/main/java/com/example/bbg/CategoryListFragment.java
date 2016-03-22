package com.example.bbg;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Богдан on 12.11.2015.
 */
public class CategoryListFragment extends ListFragment {
    protected DataBaseHelper sqlHelper;
    protected Cursor userCursor;
    private ImageView titleImage;

    private ArrayList<CategoryItem> mCategoryList;
    private static final int REQUEST_CRIME = 1;


    private int mPage;

    public static final String ARG_PAGE = "ARG_PAGE";
    private static final String TAG = "CategoryListFragment";

   /* static  ArrayList<CategoryItem> testList = new ArrayList<CategoryItem>();*/
  //  static ArrayList<CategoryItem> testList1 = new ArrayList<CategoryItem>();
    static ArrayList<CategoryItem> testList2 = new ArrayList<CategoryItem>();
    //Створює
    public static CategoryListFragment newInstance(int page){
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE,page);
        CategoryListFragment fragment  = new CategoryListFragment();
        fragment.setArguments(args);
        return fragment;
    }



    private class CategoryAdapter extends ArrayAdapter<CategoryItem> {

        public CategoryAdapter(ArrayList<CategoryItem> products) {
            super(getActivity(),0, products);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.category_item_list,null);
            }

            CategoryItem c = getItem(position);

            TextView titleTextView = (TextView) convertView.findViewById(R.id.title_item_fastfood);
            titleTextView.setText(c.getTextView());
            ImageView imageTextView = (ImageView) convertView.findViewById(R.id.image_item_fastfood);
            imageTextView.setImageBitmap(BitmapFactory.decodeByteArray(c.getImageView(), 0, c.getImageView().length));

            return convertView;
        }

    }

   // public CategoryListFragment(ArrayList<CategoryItem> list){
   ///     mCategoryList = list;
   // }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // View current = getActivity().getLayoutInflater().inflate(R.layout.activity_main, null);
        //titleImage = (ImageView) current.findViewById(R.id.Maintitle_image);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.category_list,container,false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);

        if(mPage == 1) {

          //  titleImage.setImageResource(R.drawable.food_background);

            sqlHelper = new DataBaseHelper(getActivity(),"category_product");

            sqlHelper.create_db();

            try {
                sqlHelper.open();

                userCursor = sqlHelper.database.rawQuery("select * from " + sqlHelper.TABLE,null);
                if(userCursor.moveToFirst()){
                    mCategoryList = new ArrayList<>();
                    while(!userCursor.isAfterLast()){
                        CategoryItem item = new CategoryItem();
                        item.setImageView(userCursor.getBlob(2));
                        item.setTextView(userCursor.getString(1));
                        item.setId(userCursor.getInt(0));
                        mCategoryList.add(item);
                        userCursor.moveToNext();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            if(mPage == 2){

             //   titleImage.setImageResource(R.drawable.news_background);
                sqlHelper = new DataBaseHelper(getActivity(),"news");

                sqlHelper.create_db();

                try {
                    sqlHelper.open();

                    userCursor = sqlHelper.database.rawQuery("select * from " + sqlHelper.TABLE,null);
                    if(userCursor.moveToFirst()){
                        mCategoryList = new ArrayList<>();
                        while(!userCursor.isAfterLast()){
                            CategoryItem item = new CategoryItem();
                            item.setImageView(userCursor.getBlob(2));
                            item.setTextView(userCursor.getString(1));
                            item.setId(userCursor.getInt(0));
                            mCategoryList.add(item);
                            userCursor.moveToNext();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else
            {
               // titleImage.setImageResource(R.drawable.other_background);
                mCategoryList = testList2;
            }
        }
        CategoryAdapter adapter = new CategoryAdapter(mCategoryList);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        CategoryItem c = ((CategoryAdapter) getListAdapter()).getItem(position);
        Intent i = new Intent(getActivity(),ProductActivity.class);
        i.putExtra(ProductListFragment.EXTRA_PRODUCT_LIST_ID,c.getId());
        i.putExtra(ProductListFragment.EXTRA_CATEGORY_ITEM_TITLE,c.getTextView());
        startActivityForResult(i, REQUEST_CRIME);
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

}

package com.example.bbg;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.bbg.R.id.box_shop;

/**
 * Created by Богдан on 16.11.2015.
 */

public class ProductListFragment extends ListFragment {

    public static final String EXTRA_PRODUCT_LIST_ID = "this";
    public static final String EXTRA_CATEGORY_ITEM_TITLE = "title";
    public static final String EXTRA_BOX_SHOP = "box_shop";
    private static final int REQUEST_CRIME = 1;



    private ArrayList<Product> mProducts;
    private static ArrayList<Product> sBoxProducts = new ArrayList<>();

    public static ProductListFragment newInstance(int product_list_id,String title_page){
        Bundle args = new Bundle();
        args.putInt(EXTRA_PRODUCT_LIST_ID, product_list_id);
        args.putString(EXTRA_CATEGORY_ITEM_TITLE, title_page);
        ProductListFragment fragment = new ProductListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private class ProductAdapter extends ArrayAdapter<Product> {

        public ProductAdapter(ArrayList<Product> products) {
            super(getActivity(),0, products);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.product_list_item,null);
            }

            final Product c = getItem(position);

            ImageView imageView = (ImageView) convertView.findViewById(R.id.image_product);
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(c.getImage(), 0, c.getImage().length));
            TextView title = (TextView) convertView.findViewById(R.id.title_product);
            title.setText(c.getTitle());
            TextView describe = (TextView) convertView.findViewById(R.id.describe_title_product);
            describe.setText(c.getDescribe_title());
            TextView price = (TextView) convertView.findViewById(R.id.price_product);
            price.setText(c.getPrice());
            final Button orderButton = (Button) convertView.findViewById(R.id.button_product);
            orderButton.setText(R.string.button_order);
            orderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sBoxProducts.add(c);
                }
            });

            return convertView;
        }


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int productListID = getArguments().getInt(EXTRA_PRODUCT_LIST_ID);
        mProducts = ProductList.get(getActivity()).getProductList(productListID).getProducts();
        ArrayAdapter<Product> adapter = new ProductAdapter(mProducts);
        setListAdapter(adapter);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.product_activity_fragment,container,false);
        setHasOptionsMenu(true);
      /*  if(sBoxProducts.size() == 0) {
            getActivity().getActionBar().setIcon(R.mipmap.boxshopfull1);
        }
        else
        {
            getActivity().getActionBar().setIcon(R.mipmap.boxshop);
        }*/
        return rootView;
    }

     @Override
     public void onActivityCreated(Bundle savedInstanceState) {
         super.onActivityCreated(savedInstanceState);
         setHasOptionsMenu(true);
     }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.product_menu, menu);
    }

    private void startBoxShop(){
        Intent i = new Intent(getActivity(),BoxShopActivity.class);
        i.putExtra(EXTRA_BOX_SHOP,new DataWrapper(sBoxProducts));
        i.putExtra(EXTRA_PRODUCT_LIST_ID,getArguments().getInt(EXTRA_PRODUCT_LIST_ID));
        i.putExtra(EXTRA_CATEGORY_ITEM_TITLE,getArguments().getString(EXTRA_CATEGORY_ITEM_TITLE));
        startActivityForResult(i, REQUEST_CRIME);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (NavUtils.getParentActivityName(getActivity()) != null) {
                    NavUtils.navigateUpFromSameTask(getActivity());
                }
                return true;
            case R.id.box_shop:
                startBoxShop();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}

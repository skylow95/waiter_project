package com.example.bbg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.bbg.R.id.box_shop;

/**
 * Created by Богдан on 16.12.2015.
 */

public class BoxShopFragment extends ListFragment {
    private ArrayList<Product> mBoxList;
    private int product_list_id;
    private String title_page;


    public BoxShopFragment(ArrayList<Product> list,int id,String page){
        this.mBoxList = list;
        this.product_list_id = id;
        this.title_page = page;
    }

    private class ProductAdapter extends ArrayAdapter<Product> {

        public ProductAdapter(ArrayList<Product> products) {
            super(getActivity(),0, products);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.boxshop_item_list,null);
            }

            final Product c = getItem(position);

            ImageView imageView = (ImageView) convertView.findViewById(R.id.image_boxshop);
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(c.getImage(), 0, c.getImage().length));
            TextView title = (TextView) convertView.findViewById(R.id.title_boxshop);
            title.setText(c.getTitle());
            TextView describe = (TextView) convertView.findViewById(R.id.describe_title_boxshop);
            describe.setText(c.getDescribe_title());
            TextView price = (TextView) convertView.findViewById(R.id.price_boxshop);
            price.setText(c.getPrice());
            final Button orderButton = (Button) convertView.findViewById(R.id.button_boxshop);
            orderButton.setText("Видалити");
            orderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBoxList.remove(c);
                }
            });

            return convertView;
        }


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<Product> adapter = new ProductAdapter(mBoxList);
        setListAdapter(adapter);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.boxshop_list,container,false);
        Button takeOrder = (Button) rootView.findViewById(R.id.doshoping);
        setHasOptionsMenu(true);
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (NavUtils.getParentActivityName(getActivity()) != null) {
                   // NavUtils.navigateUpFromSameTask(getActivity());
                    Intent intent = NavUtils.getParentActivityIntent(getActivity());
                    intent.putExtra(ProductListFragment.EXTRA_PRODUCT_LIST_ID, product_list_id);
                    intent.putExtra(ProductListFragment.EXTRA_CATEGORY_ITEM_TITLE,title_page);// CustInfo is serializable
                    NavUtils.navigateUpTo(getActivity(), intent);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}

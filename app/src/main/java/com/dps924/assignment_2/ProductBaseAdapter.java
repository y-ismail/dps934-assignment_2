package com.dps924.assignment_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductBaseAdapter extends BaseAdapter {

    ArrayList<Product> productList;
    Context context;

    public ProductBaseAdapter(ArrayList<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.productList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.productList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(this.context).inflate(R.layout.sample_list_view, null);

        TextView productNameText = view.findViewById(R.id.productName);
        TextView productQuantityText = view.findViewById(R.id.productQuantity);
        TextView productPriceText = view.findViewById(R.id.productPrice);

        productNameText.setText(this.productList.get(i).getProductName());
        productQuantityText.setText(String.valueOf(this.productList.get(i).getInventoryQuantity()));
        productPriceText.setText(String.format(context.getResources().getString(R.string.dollar_amount), String.valueOf(this.productList.get(i).getProductPrice())));

        return view;

    }
}

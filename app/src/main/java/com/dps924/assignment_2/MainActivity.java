package com.dps924.assignment_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final DecimalFormat df = new DecimalFormat("0.00");
    private int quantity;
    private double total;
    private NumberPicker productNumberPicker;
    private TextView totalAmount;
    private TextView quantityTextView;
    private ProductManager productManager;
    private PurchaseHistoryManager purchaseHistoryManager;
    private Product selectedProduct;
    private ProductBaseAdapter adapter;
    TextView productDisplayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.productNumberPicker = findViewById(R.id.product_numberPicker);
        productNumberPicker.setMaxValue(100);
        productNumberPicker.setMinValue(0);
        this.quantityTextView = findViewById(R.id.quantityAmount);
        this.totalAmount = findViewById(R.id.totalAmount);
        this.productDisplayText = findViewById(R.id.product_text);

        this.setDisplayDefaults();

        this.productManager = ((MyApp)getApplication()).productManager;
        this.purchaseHistoryManager = ((MyApp)getApplication()).purchaseHistoryManager;

        ListView productBaseAdapterList = findViewById(R.id.product_listView);

        productBaseAdapterList.setOnItemClickListener((adapterView, view, i, l) -> {
            this.selectedProduct = this.productManager.productList.get(i);
            this.productDisplayText.setText(this.selectedProduct.getProductName());
            this.productDisplayText.setTypeface(Typeface.DEFAULT_BOLD);

            // update the value in quantity and total if product types change
            this.quantity = this.productNumberPicker.getValue();
            this.total = this.selectedProduct.getProductPrice() * this.quantity;
            this.totalAmount.setText(String.format(getResources().getString(R.string.dollar_amount), df.format(total)));
        });

        this.adapter = new ProductBaseAdapter(this.productManager.productList, this);
        productBaseAdapterList.setAdapter(adapter);

        productNumberPicker.setOnValueChangedListener((numberPicker, i, i1) -> {
            this.quantity = this.productNumberPicker.getValue();
            if (this.selectedProduct != null) {
                this.total = this.selectedProduct.getProductPrice() * this.quantity;
            }
            this.quantityTextView.setText(String.valueOf(this.quantity));
            this.totalAmount.setText(String.format(getResources().getString(R.string.dollar_amount), df.format(this.total)));
        });


        Button buyBtn = findViewById(R.id.buyBtn);
        Button managerBtn = findViewById(R.id.managerBtn);

        buyBtn.setOnClickListener(this);
        managerBtn.setOnClickListener(this);


    }

    private void setDisplayDefaults() {
        this.quantityTextView.setText("0");
        this.totalAmount.setText(String.format(getResources().getString(R.string.dollar_amount), "0.00"));
        this.quantity = 0;
        this.total = 0.0f;
        this.productDisplayText.setText("");
        this.productDisplayText.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Toast toast;

        if (id == R.id.buyBtn) {
            if (quantity == 0 || this.selectedProduct == null) {
                toast = Toast.makeText(this, "All fields are required.", Toast.LENGTH_LONG);
                toast.show();
            }
            else if (quantity > this.selectedProduct.getInventoryQuantity()) {
                toast = Toast.makeText(this, "Not enough inventory.", Toast.LENGTH_LONG);
                toast.show();
            }
            else {
                String message = String.format(getResources().getString(R.string.toast_message), this.quantity, this.selectedProduct.getProductName(), df.format(this.total));
                new AlertDialog.Builder(this)
                        .setTitle("Thank you for your purchase.")
                        .setMessage(message)
                        .setNegativeButton("Close", null)
                        .show();
                this.productManager.productList.get(this.productManager.productList.indexOf(this.selectedProduct)).updateInventoryQuantity(quantity, false);
                this.purchaseHistoryManager.purchaseHistories.add(new PurchaseHistory(this.selectedProduct.getProductName(), this.quantity, df.format(total), new Date()));
                this.adapter.notifyDataSetChanged();
                this.selectedProduct = null;
                this.productNumberPicker.setValue(this.productNumberPicker.getMinValue());
                this.setDisplayDefaults();
            }
        }
        else {
            Intent managerIntent = new Intent(this, ManagerActivity.class);
            startActivity(managerIntent);
        }



    }
}
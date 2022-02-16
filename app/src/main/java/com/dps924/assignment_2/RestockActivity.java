package com.dps924.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RestockActivity extends AppCompatActivity {

    private Product selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);

        ProductManager productManager = ((MyApp)getApplication()).productManager;
        ListView productListView = findViewById(R.id.restockListView);

        ProductBaseAdapter adapter = new ProductBaseAdapter(productManager.productList, this);
        productListView.setAdapter(adapter);

        TextView selectedProductText = findViewById(R.id.selectedProduct);

        productListView.setOnItemClickListener((adapterView, view, i, l) -> {
            selectedItem = productManager.productList.get(i);
            selectedProductText.setText(selectedItem.getProductName());
            selectedProductText.setTypeface(Typeface.DEFAULT_BOLD);
        });

        Button updateQuantityButton = findViewById(R.id.updateQuantityBtn);
        EditText quantityEntered = findViewById(R.id.quantityEditText);
        updateQuantityButton.setOnClickListener(view -> {
            if (selectedItem != null && quantityEntered.getText().toString().length() > 0) {
                try {
                    productManager.productList.get(productManager.productList.indexOf(this.selectedItem)).updateInventoryQuantity(Integer.parseInt(quantityEntered.getText().toString()), true);
                    adapter.notifyDataSetChanged();
                    quantityEntered.setText("");
                    selectedProductText.setText("");
                    selectedProductText.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                    this.selectedItem = null;

                } catch(Exception e) {
                    Toast toast = Toast.makeText(this, "An error occurred.", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
            else {
                Toast toast = Toast.makeText(this, "All fields are required.", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        Button cancelButton = findViewById(R.id.cancelBtn);
        cancelButton.setOnClickListener(view -> super.onBackPressed());


    }
}
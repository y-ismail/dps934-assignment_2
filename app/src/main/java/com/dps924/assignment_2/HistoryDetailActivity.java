package com.dps924.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HistoryDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);

        TextView productNameText = findViewById(R.id.detail_productName);
        TextView totalPriceText = findViewById(R.id.detail_purchaseTotal);
        TextView dateText = findViewById(R.id.detail_purchaseDate);

        productNameText.setText(getIntent().getExtras().getString("productName"));
        totalPriceText.setText(getIntent().getExtras().getString("total"));
        dateText.setText(getIntent().getExtras().getString("date"));

    }
}
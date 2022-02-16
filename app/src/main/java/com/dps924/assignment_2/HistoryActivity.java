package com.dps924.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class HistoryActivity extends AppCompatActivity {
    private double total = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        PurchaseHistoryManager purchaseHistoryManager = ((MyApp) getApplication()).purchaseHistoryManager;

        ListView historyListView = findViewById(R.id.history_listView);
        PurchaseHistoryAdapter adapter = new PurchaseHistoryAdapter(purchaseHistoryManager.purchaseHistories, this);

        historyListView.setAdapter(adapter);

        historyListView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent historyDetail = new Intent(this, HistoryDetailActivity.class);
            historyDetail.putExtra("total", purchaseHistoryManager.purchaseHistories.get(i).getTotalPrice());
            historyDetail.putExtra("productName", purchaseHistoryManager.purchaseHistories.get(i).getProductName());
            historyDetail.putExtra("date", purchaseHistoryManager.purchaseHistories.get(i).getPurchasedDate().toString());
            startActivity(historyDetail);
        });

        TextView totalSpentText = findViewById(R.id.totalSpent);

        purchaseHistoryManager.purchaseHistories.forEach(item -> total += Double.parseDouble(item.getTotalPrice()));

        totalSpentText.setText(String.format(getResources().getString(R.string.totalSpent), String.format("%.2f", this.total)));

    }
}
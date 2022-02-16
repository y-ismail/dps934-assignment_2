package com.dps924.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        Button historyButton = findViewById(R.id.historyBtn);
        historyButton.setOnClickListener(view -> {
            Intent historyIntent = new Intent(this, HistoryActivity.class);
            startActivity(historyIntent);
        });

        Button restockButton = findViewById(R.id.restockBtn);
        restockButton.setOnClickListener(view -> {
            Intent restockIntent = new Intent(this, RestockActivity.class);
            startActivity(restockIntent);
        });

    }

}
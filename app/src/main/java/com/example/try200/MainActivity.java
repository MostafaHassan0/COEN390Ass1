package com.example.try200;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SharedPreferenceHelper sharedPreferenceHelper;
    private TextView totalCountTextView;
    private Button eventButton1, eventButton2, eventButton3, showCountsButton, settingsButton;
    private int eventCount1 = 0, eventCount2 = 0, eventCount3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferenceHelper = new SharedPreferenceHelper(this);
        totalCountTextView = findViewById(R.id.totalCountTextView);
        eventButton1 = findViewById(R.id.eventButton1);
        eventButton2 = findViewById(R.id.eventButton2);
        eventButton3 = findViewById(R.id.eventButton3);
        showCountsButton = findViewById(R.id.showCountsButton);
        settingsButton = findViewById(R.id.settingsButton);

        // Load button names from SharedPreferences
        updateButtonNames();

        eventButton1.setOnClickListener(view -> incrementEventCount(1));
        eventButton2.setOnClickListener(view -> incrementEventCount(2));
        eventButton3.setOnClickListener(view -> incrementEventCount(3));

        showCountsButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, DataActivity.class)));
        settingsButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, SettingsActivity.class)));
    }

    private void incrementEventCount(int event) {
        switch (event) {
            case 1: eventCount1++; break;
            case 2: eventCount2++; break;
            case 3: eventCount3++; break;
        }
        updateTotalCount();
        sharedPreferenceHelper.saveEventCounts(eventCount1, eventCount2, eventCount3);
    }

    private void updateTotalCount() {
        int totalCount = eventCount1 + eventCount2 + eventCount3;
        totalCountTextView.setText("Total Count: " + totalCount);
    }

    private void updateButtonNames() {
        Settings settings = sharedPreferenceHelper.getSettings();
        if (settings != null) {
            eventButton1.setText(settings.getButton1Name());
            eventButton2.setText(settings.getButton2Name());
            eventButton3.setText(settings.getButton3Name());
        }
    }
}

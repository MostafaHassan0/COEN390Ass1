package com.example.try200;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SharedPreferenceHelper sharedPreferenceHelper;
    private TextView totalCountTextView;
    private Button eventA, eventB, eventC, showCountsButton, settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hide the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        sharedPreferenceHelper = new SharedPreferenceHelper(this);
        totalCountTextView = findViewById(R.id.Counter);
        eventA = findViewById(R.id.EventA);
        eventB = findViewById(R.id.EventB);
        eventC = findViewById(R.id.EventC);
        showCountsButton = findViewById(R.id.Databt);
        settingsButton = findViewById(R.id.SettingsBT);

        // Load button names from SharedPreferences
        updateButtonNames();



        eventA.setOnClickListener(view -> incrementEventCount(1));
        eventB.setOnClickListener(view -> incrementEventCount(2));
        eventC.setOnClickListener(view -> incrementEventCount(3));

        showCountsButton.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, DataActivity.class)));
        settingsButton.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, SettingsActivity.class)));
    }

    private void incrementEventCount(int event) {
        if ( updateTotalCount() < sharedPreferenceHelper.getSettings().getMaxEventCount()){
        sharedPreferenceHelper.IncrementEventCount(event);
            updateTotalCount();
        } else {
            Toast.makeText(this,"Maximum Count Reached",Toast.LENGTH_SHORT).show();
        }
    }

    private int updateTotalCount() {
        int count1 = sharedPreferenceHelper.getEventCount(1);
        int count2 = sharedPreferenceHelper.getEventCount(2);
        int count3 = sharedPreferenceHelper.getEventCount(3);
        int totalCount = count1 + count2 + count3;
        totalCountTextView.setText("Total Count: " + totalCount);
        return totalCount;
    }

    private void updateButtonNames() {
        Settings settings = sharedPreferenceHelper.getSettings();
        if (settings != null) {
            eventA.setText(settings.getButton1Name());
            eventB.setText(settings.getButton2Name());
            eventC.setText(settings.getButton3Name());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateButtonNames();
        updateTotalCount();
    }
}

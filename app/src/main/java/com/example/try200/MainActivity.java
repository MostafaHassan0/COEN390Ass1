package com.example.try200;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declaring Variables for the UI elements
    private TextView totalCount;
    private Button eventA, eventB, eventC, showCountsBt, settingsBt;

    private SharedPreferenceHelper sharedPreferenceHelper; // Declaring a SharedPreferenceHelper object to access our shared preferences

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hide the action bar
        if (getSupportActionBar() != null) { // Check if the action bar is there in the first place to prevent crashing
            getSupportActionBar().hide();
        }

        // Linking each Variable to the XML
        totalCount = findViewById(R.id.Counter);
        eventA = findViewById(R.id.EventA);
        eventB = findViewById(R.id.EventB);
        eventC = findViewById(R.id.EventC);
        showCountsBt = findViewById(R.id.Databt);
        settingsBt = findViewById(R.id.SettingsBT);

        sharedPreferenceHelper = new SharedPreferenceHelper(this); // Initialize SharedPreferenceHelper to the one already created

        updateButtonNames(); // Load button names from SharedPreferences and update the UI

        // Set click listeners for the buttons
        eventA.setOnClickListener(view -> incrementEventCount(1));
        eventB.setOnClickListener(view -> incrementEventCount(2));
        eventC.setOnClickListener(view -> incrementEventCount(3));

        showCountsBt.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, DataActivity.class))); // Start DataActivity when the Show Counts button is clicked

        settingsBt.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, SettingsActivity.class))); // Start SettingsActivity when the Settings button is clicked
    }

    // Increment the event count and update the total count
    private void incrementEventCount(int event) {
        Settings settings = sharedPreferenceHelper.getSettings(); // Get the settings object from SharedPreferences
        if (settings != null) { // Check if settings exist in SharedPreferences

            if ( updateTotalCount() < sharedPreferenceHelper.getSettings().getMaxEventCount()){ // Check if the maximum count has not been reached
            sharedPreferenceHelper.IncrementEventCount(event); // Increment the event count saved in SharedPreferences
                updateTotalCount(); // Update the total count displayed in the UI

            } else { // If the maximum count has been reached, display a toast message
                Toast.makeText(this,"Maximum Count Reached",Toast.LENGTH_SHORT).show();
            }

        } else { // If settings do not exist in SharedPreferences
            startActivity(new Intent(MainActivity.this, SettingsActivity.class)); // Redirect to SettingsActivity
            Toast.makeText(this,"Please configure your settings first",Toast.LENGTH_SHORT).show(); // Display a toast message
        }
    }

    // Update the total count displayed in the UI
    private int updateTotalCount() {

        // Hold the event counts from SharedPreferences
        int count1 = sharedPreferenceHelper.getEventCount(1);
        int count2 = sharedPreferenceHelper.getEventCount(2);
        int count3 = sharedPreferenceHelper.getEventCount(3);

        int total = count1 + count2 + count3; // Calculate the total count
        totalCount.setText("Total Count: " + total); // Display the total count in the UI
        return total;
    }

    // Update the counter button names displayed in the UI
    private void updateButtonNames() {
        Settings settings = sharedPreferenceHelper.getSettings();// Get the settings object from SharedPreferences
        if (settings != null) { // Check if settings exist in SharedPreferences

            // Set the names of the counter buttons to the corresponding values from SharedPreferences
            eventA.setText(settings.getButton1Name());
            eventB.setText(settings.getButton2Name());
            eventC.setText(settings.getButton3Name());
        }
    }

    // Update the button names and total count when the activity every time the Activity is resumed
    @Override
    protected void onResume() {
        super.onResume();
        updateButtonNames();
        updateTotalCount();
    }
}

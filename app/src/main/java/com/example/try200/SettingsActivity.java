package com.example.try200;

import static android.text.TextUtils.isEmpty;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    // Declaring Variables for the UI elements
    private EditText counter1, counter2, counter3, maxCount;
    private Button Save;

    // Declaring a SharedPreferenceHelper object to access our shared preferences
    private SharedPreferenceHelper sharedPreferenceHelper;

//    private boolean FirstRun = true; // Was trying to make Save button invisible after first run
                                        // I even tried to hold a FirstRun in SharedPreferenceHelper but it didn't work
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setTitle("Settings Activity"); // Set the title of the action bar to Settings Activity

        // Linking each Variable to the XML
        counter1 = findViewById(R.id.Counter1);
        counter2 = findViewById(R.id.Counter2);
        counter3 = findViewById(R.id.Counter3);
        maxCount = findViewById(R.id.MaxCount);
        Save = findViewById(R.id.Save);

        sharedPreferenceHelper = new SharedPreferenceHelper(this); // Initialize SharedPreferenceHelper to the one in MainActivity
        loadSettings(); // Load the saved settings from SharedPreferences

        Save.setVisibility(View.VISIBLE); // Make Save button visible. Removing that line made the Save button invisible and you need to click Edit first.
        Save.setOnClickListener(view -> saveSettings()); // Save button click
    }


    // Load settings from SharedPreferences
    private void loadSettings() {

        Settings settings = sharedPreferenceHelper.getSettings(); // Get the settings object from SharedPreferences
        if (settings != null) { // Check if settings exist in SharedPreferences

            // Set the values of the EditText fields to the corresponding values from SharedPreferences
            counter1.setText(settings.getButton1Name());
            counter2.setText(settings.getButton2Name());
            counter3.setText(settings.getButton3Name());
            maxCount.setText(String.valueOf(settings.getMaxEventCount()));
        }
    }

    // Save settings to SharedPreferences
    private void saveSettings() {

        // Create holders to hold the values of the EditText fields as strings
        String counter1Name = counter1.getText().toString();
        String counter2Name = counter2.getText().toString();
        String counter3Name = counter3.getText().toString();
        String maxCountStr = maxCount.getText().toString();


        if (!isEmpty(counter1Name) && !isEmpty(counter2Name) && !isEmpty(counter3Name) && !isEmpty(maxCountStr)){ // Check if all fields are filled

            int maxCount = Integer.parseInt(maxCountStr);// Convert maxCountStr to an integer to compare with the range
            if (maxCount < 5 || maxCount > 200) { // Check if maxCount is within the range
                Toast.makeText(this, "Maximum count must be between 5 and 200", Toast.LENGTH_SHORT).show(); // Display toast message
                return;
            }

            // Create a new Settings object with the values of the EditText fields
            Settings settings = new Settings(counter1Name, counter2Name, counter3Name, maxCount);
            sharedPreferenceHelper.saveSettings(settings); // Save the settings object to SharedPreferences
            Toast.makeText(this, "Settings Saved!", Toast.LENGTH_SHORT).show(); // Indicate that settings have been saved
            EditMode(false); // Disable the EditText fields
            Save.setVisibility(View.GONE); // Hide the Save button.


        } else { // If any field is empty, display a toast message
            Toast.makeText(this, "One or more fields are empty", Toast.LENGTH_SHORT).show();
        }
    }

    // Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu); // Inflate the menu to appear in the action bar
        return true;
    }

    // Menu Item Click Handler
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.Edit) { // Target the Edit button
            EditMode(true); // Enable the EditText fields
            Save.setVisibility(View.VISIBLE); // Make Save button visible
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Edit Mode, enable/disable EditText fields
    private void EditMode(boolean editable) {
        counter1.setEnabled(editable);
        counter2.setEnabled(editable);
        counter3.setEnabled(editable);
        maxCount.setEnabled(editable);

    }
}

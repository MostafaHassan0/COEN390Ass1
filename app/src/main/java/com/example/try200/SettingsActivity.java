package com.example.try200;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private EditText counter1EditText, counter2EditText, counter3EditText, maxCountEditText;
    private Button saveButton;
    private SharedPreferenceHelper sharedPreferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        counter1EditText = findViewById(R.id.Counter1);
        counter2EditText = findViewById(R.id.Counter2);
        counter3EditText = findViewById(R.id.Counter3);
        maxCountEditText = findViewById(R.id.MaxCount);
        saveButton = findViewById(R.id.Save);
        sharedPreferenceHelper = new SharedPreferenceHelper(this);

        loadSettings();

        saveButton.setOnClickListener(view -> saveSettings());
    }

    private void loadSettings() {
        Settings settings = sharedPreferenceHelper.getSettings();
        if (settings != null) {
            counter1EditText.setText(settings.getButton1Name());
            counter2EditText.setText(settings.getButton2Name());
            counter3EditText.setText(settings.getButton3Name());
            maxCountEditText.setText(String.valueOf(settings.getMaxEventCount()));
        }
    }

    private void saveSettings() {
        String counter1Name = counter1EditText.getText().toString().trim();
        String counter2Name = counter2EditText.getText().toString().trim();
        String counter3Name = counter3EditText.getText().toString().trim();
        String maxCountStr = maxCountEditText.getText().toString().trim();

        if (isValidInput(counter1Name, counter2Name, counter3Name, maxCountStr)) {
            int maxCount = Integer.parseInt(maxCountStr);
            Settings settings = new Settings(counter1Name, counter2Name, counter3Name, maxCount);
            sharedPreferenceHelper.saveSettings(settings);
            Toast.makeText(this, "Settings Saved!", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity after saving
        } else {
            Toast.makeText(this, "Invalid input!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidInput(String counter1Name, String counter2Name, String counter3Name, String maxCountStr) {
        if (maxCountStr.isEmpty()) return false;
        int maxCount;
        try {
            maxCount = Integer.parseInt(maxCountStr);
        } catch (NumberFormatException e) {
            return false;
        }
        return counter1Name.matches("[a-zA-Z ]{1,10}") &&
                counter2Name.matches("[a-zA-Z ]{1,10}") &&
                counter3Name.matches("[a-zA-Z ]{1,10}") &&
                (maxCount >= 5 && maxCount <= 200);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.Edit) {
            // Add your logic for edit action if needed
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

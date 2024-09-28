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

    private EditText button1Name, button2Name, button3Name, maxEventCount;
    private Button saveButton;
    private SharedPreferenceHelper sharedPreferenceHelper;
    private boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        button1Name = findViewById(R.id.button1Name);
        button2Name = findViewById(R.id.button2Name);
        button3Name = findViewById(R.id.button3Name);
        maxEventCount = findViewById(R.id.maxEventCount);
        saveButton = findViewById(R.id.saveButton);
        sharedPreferenceHelper = new SharedPreferenceHelper(this);

        loadSettings();

        saveButton.setOnClickListener(view -> saveSettings());
    }

    private void loadSettings() {
        Settings settings = sharedPreferenceHelper.getSettings();
        if (settings != null) {
            button1Name.setText(settings.getButton1Name());
            button2Name.setText(settings.getButton2Name());
            button3Name.setText(settings.getButton3Name());
            maxEventCount.setText(String.valueOf(settings.getMaxEventCount()));
        }
    }

    private void saveSettings() {
        String btn1Name = button1Name.getText().toString();
        String btn2Name = button2Name.getText().toString();
        String btn3Name = button3Name.getText().toString();
        int maxCount = Integer.parseInt(maxEventCount.getText().toString());

        if (isValidInput(btn1Name, btn2Name, btn3Name, maxCount)) {
            Settings settings = new Settings(btn1Name, btn2Name, btn3Name, maxCount);
            sharedPreferenceHelper.saveSettings(settings);
            Toast.makeText(this, "Settings Saved!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Invalid input!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidInput(String btn1Name, String btn2Name, String btn3Name, int maxCount) {
        return btn1Name.matches("[a-zA-Z ]{1,20}") && btn2Name.matches("[a-zA-Z ]{1,20}") &&
                btn3Name.matches("[a-zA-Z ]{1,20}") && (maxCount >= 5 && maxCount <= 200);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
            toggleEditMode();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggleEditMode() {
        isEditMode = !isEditMode;
        button1Name.setEnabled(isEditMode);
        button2Name.setEnabled(isEditMode);
        button3Name.setEnabled(isEditMode);
        maxEventCount.setEnabled(isEditMode);
        saveButton.setVisibility(isEditMode ? View.VISIBLE : View.GONE);
    }
}

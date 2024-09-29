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

    private EditText counter1, counter2, counter3, maxCount;
    private Button Save;
    private SharedPreferenceHelper sharedPreferenceHelper;
    private boolean FirstRun = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        counter1 = findViewById(R.id.Counter1);
        counter2 = findViewById(R.id.Counter2);
        counter3 = findViewById(R.id.Counter3);
        maxCount = findViewById(R.id.MaxCount);
        Save = findViewById(R.id.Save);
        sharedPreferenceHelper = new SharedPreferenceHelper(this);

        loadSettings();


        Save.setOnClickListener(view -> saveSettings());

    }






    private void loadSettings() {
        Settings settings = sharedPreferenceHelper.getSettings();
        if (settings != null) {
            counter1.setText(settings.getButton1Name());
            counter2.setText(settings.getButton2Name());
            counter3.setText(settings.getButton3Name());
            maxCount.setText(String.valueOf(settings.getMaxEventCount()));
        }
    }

    private void saveSettings() {
        String counter1Name = counter1.getText().toString();
        String counter2Name = counter2.getText().toString();
        String counter3Name = counter3.getText().toString();
        String maxCountStr = maxCount.getText().toString();


        if (!isEmpty(counter1Name) && !isEmpty(counter2Name) && !isEmpty(counter3Name) && !isEmpty(maxCountStr)){
            int maxCount = Integer.parseInt(maxCountStr);
            if (maxCount < 5 || maxCount > 200) {
                Toast.makeText(this, "Maximum count must be between 5 and 200", Toast.LENGTH_SHORT).show();
                return;
            }
            Settings settings = new Settings(counter1Name, counter2Name, counter3Name, maxCount);
            sharedPreferenceHelper.saveSettings(settings);
            Toast.makeText(this, "Settings Saved!", Toast.LENGTH_SHORT).show();
            EditMode(false);
            Save.setVisibility(View.GONE);
        } else {
            Toast.makeText(this, "One or more fields are empty", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.Edit) {
            EditMode(true);
            Save.setVisibility(View.VISIBLE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void EditMode(boolean editable) {
        counter1.setEnabled(editable);
        counter2.setEnabled(editable);
        counter3.setEnabled(editable);
        maxCount.setEnabled(editable);

    }
}

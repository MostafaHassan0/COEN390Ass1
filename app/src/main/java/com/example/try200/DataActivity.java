package com.example.try200;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity {

    private TextView totalCountTextView;
    private ListView eventListView;
    private SharedPreferenceHelper sharedPreferenceHelper;
    private boolean isButtonNameMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        totalCountTextView = findViewById(R.id.totalCountTextView);
        eventListView = findViewById(R.id.eventListView);
        sharedPreferenceHelper = new SharedPreferenceHelper(this);

        displayEventData();
    }

    private void displayEventData() {
        int totalCount = sharedPreferenceHelper.getTotalEventCount();
        totalCountTextView.setText("Total Count: " + totalCount);

        List<String> eventHistory = sharedPreferenceHelper.getEventHistory();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eventHistory);
        eventListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.data_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_toggle_name) {
            toggleEventNameDisplay();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggleEventNameDisplay() {
        isButtonNameMode = !isButtonNameMode;

        // Update the displayed event history accordingly
        List<String> eventHistory = sharedPreferenceHelper.getEventHistory();
        List<String> updatedHistory = new ArrayList<>();

        if (isButtonNameMode) {
            updatedHistory = eventHistory; // Use event names
        } else {
            for (int i = 0; i < eventHistory.size(); i++) {
                updatedHistory.add("Event " + (i + 1)); // Use button numbers
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, updatedHistory);
        eventListView.setAdapter(adapter);
    }
}

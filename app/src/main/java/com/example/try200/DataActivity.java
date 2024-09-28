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

    private TextView counter1TextView, counter2TextView, counter3TextView, totalCountTextView;
    private ListView listView;
    private SharedPreferenceHelper sharedPreferenceHelper;
    private boolean showEventNames = true;  // Flag to toggle between event names and generic names

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        totalCountTextView = findViewById(R.id.TotalLB);
        counter1TextView = findViewById(R.id.Counter1LB);
        counter2TextView = findViewById(R.id.Counter2LB);
        counter3TextView = findViewById(R.id.Counter3LB);
        listView = findViewById(R.id.ListView);
        sharedPreferenceHelper = new SharedPreferenceHelper(this);

        displayEventData();
    }

    private void displayEventData() {
        int count1 = sharedPreferenceHelper.getEventCount(1);
        int count2 = sharedPreferenceHelper.getEventCount(2);
        int count3 = sharedPreferenceHelper.getEventCount(3);
        int totalCount = count1 + count2 + count3;

        // Update the text views with the counts
        counter1TextView.setText("Counter 1: " + count1 + " events");
        counter2TextView.setText("Counter 2: " + count2 + " events");
        counter3TextView.setText("Counter 3: " + count3 + " events");
        totalCountTextView.setText("Total: " + totalCount + " events");

        // Load the event history to the list view
        updateEventHistory();
    }

    private void updateEventHistory() {
        List<String> eventHistory = sharedPreferenceHelper.getEventHistory();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eventHistory);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.data_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.Toggle_Name) {
            toggleEventNameDisplay();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggleEventNameDisplay() {
        showEventNames = !showEventNames;  // Toggle the flag

        // Prepare the event history list based on the current toggle state
        List<String> updatedHistory = new ArrayList<>();
        if (showEventNames) {
            updatedHistory = sharedPreferenceHelper.getEventHistory();  // Use actual event names
        } else {
            // Use generic names
            int count1 = sharedPreferenceHelper.getEventCount(1);
            int count2 = sharedPreferenceHelper.getEventCount(2);
            int count3 = sharedPreferenceHelper.getEventCount(3);
            updatedHistory.add("Event 1: " + count1 + " events");
            updatedHistory.add("Event 2: " + count2 + " events");
            updatedHistory.add("Event 3: " + count3 + " events");
        }

        // Update the ListView with the new data
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, updatedHistory);
        listView.setAdapter(adapter);
    }
}

package com.example.try200;

import android.os.Bundle;
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
        // Get the total count from SharedPreferences
        int totalCount = sharedPreferenceHelper.getTotalEventCount();
        totalCountTextView.setText("Total Count: " + totalCount);

        // Get the event history from SharedPreferences
        List<String> eventHistory = sharedPreferenceHelper.getEventHistory();

        // Set up an adapter for the ListView to display event history
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eventHistory);
        eventListView.setAdapter(adapter);
    }
}

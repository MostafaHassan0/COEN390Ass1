package com.example.try200;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataActivity extends AppCompatActivity {

    private TextView counter1TextView, counter2TextView, counter3TextView, totalCountTextView;
    private ListView listView;
    private SharedPreferenceHelper sharedPreferenceHelper;

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
        int totalCount = count1 + count2 + count3;// Toggle the flag

        // Prepare the event history list based on the current toggle state
        List<String> updatedlist = new ArrayList<>();
        if (sharedPreferenceHelper.getToggleStat()) {


            updatedlist = sharedPreferenceHelper.getEventList();
            counter1TextView.setText("Counter 1: " + count1 + " events");
            counter2TextView.setText("Counter 2: " + count2 + " events");
            counter3TextView.setText("Counter 3: " + count3 + " events");
            // Use actual event names
        } else {
            // Use generic names
            counter1TextView.setText(sharedPreferenceHelper.getSettings().getButton1Name() + ": " + count1 + " events");
            counter2TextView.setText(sharedPreferenceHelper.getSettings().getButton2Name() + ": " + count2 + " events");
            counter3TextView.setText(sharedPreferenceHelper.getSettings().getButton3Name() + ": " + count3 + " events");
            List<String> temp = sharedPreferenceHelper.getEventList();
            for (String i : temp) {
                if (i.equals("1")) {
                    updatedlist.add(sharedPreferenceHelper.getSettings().getButton1Name());
                }
                else if (i.equals("2")) {
                    updatedlist.add(sharedPreferenceHelper.getSettings().getButton2Name());
                }
                else if (i.equals("3")) {
                    updatedlist.add(sharedPreferenceHelper.getSettings().getButton3Name());
                }
            }
        }

        totalCountTextView.setText("Total: " + totalCount + " events");

        // Update the ListView with the new data
        Collections.reverse(updatedlist);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, updatedlist);
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
            boolean Toggle_Status = sharedPreferenceHelper.getToggleStat();
            sharedPreferenceHelper.setToggleStat(!Toggle_Status);
            displayEventData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

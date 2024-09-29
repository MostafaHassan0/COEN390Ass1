package com.example.try200;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity {

    // Declaring Variables for the UI elements
    private TextView counter1, counter2, counter3, totalCount;
    private ListView listView;
    private Button reset;

    // Declaring a SharedPreferenceHelper object to access our shared preferences
    private SharedPreferenceHelper sharedPreferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        // Linking each Variable to the XML
        totalCount = findViewById(R.id.TotalLB);
        counter1 = findViewById(R.id.Counter1LB);
        counter2 = findViewById(R.id.Counter2LB);
        counter3 = findViewById(R.id.Counter3LB);
        listView = findViewById(R.id.ListView);
        reset = findViewById(R.id.Reset);

        sharedPreferenceHelper = new SharedPreferenceHelper(this); // Initialize SharedPreferenceHelper to the one in MainActivity

        getSupportActionBar().setTitle("Data Activity"); // Set the title of the action bar

        DisplayEventData(); // Display events data in listview

        reset.setOnClickListener(view -> Reset(view)); // Set click listener for the reset button
    }

    private void DisplayEventData() {
        // Holders to hold the event counts
        int count1 = sharedPreferenceHelper.getEventCount(1);
        int count2 = sharedPreferenceHelper.getEventCount(2);
        int count3 = sharedPreferenceHelper.getEventCount(3);

        int total = count1 + count2 + count3;  // Total count of all events
        totalCount.setText("Total events: " + total); // Display total count

        // Prepare the event list based on the current toggle state
        List<String> Eventlist = new ArrayList<>();
        if (sharedPreferenceHelper.getToggleStat()) { // If toggle is on, meaning we want to show default names

            Eventlist = sharedPreferenceHelper.getEventList(); // Use default list from shared preferences
            counter1.setText("Event A: " + count1 + " events");
            counter2.setText("Event B: " + count2 + " events");
            counter3.setText("Event C: " + count3 + " events");

        } else { // If toggle is off, meaning we want to show Event settings names

            counter1.setText(sharedPreferenceHelper.getSettings().getButton1Name() + ": " + count1 + " events");
            counter2.setText(sharedPreferenceHelper.getSettings().getButton2Name() + ": " + count2 + " events");
            counter3.setText(sharedPreferenceHelper.getSettings().getButton3Name() + ": " + count3 + " events");

            List<String> temp = sharedPreferenceHelper.getEventList(); // Get the list of events from shared preferences and store it in a temporary list
            for (String i : temp) {  // Iterate through the temporary list and add the corresponding event name to the Eventlist
                if (i.equals("1")) {
                    Eventlist.add(sharedPreferenceHelper.getSettings().getButton1Name());
                }
                else if (i.equals("2")) {
                    Eventlist.add(sharedPreferenceHelper.getSettings().getButton2Name());
                }
                else if (i.equals("3")) {
                    Eventlist.add(sharedPreferenceHelper.getSettings().getButton3Name());
                }
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Eventlist); // Create an adapter to display EventList in the listview
        listView.setAdapter(adapter); // Set the adapter to the listview

    }

    // Reset button click handler
    public void Reset(android.view.View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this); // Create a dialog to confirm reset
        builder.setTitle("Reset counters"); // Set the title of the dialog
        builder.setMessage("Are you sure you want to reset all counter?"); // Set the message of the dialog
        builder.setPositiveButton("Yes", (dialog, which) -> { // Set the positive button of the dialog
            sharedPreferenceHelper.ResetEventCount(); // Reset all event counts to 0 and clear the event list
            DisplayEventData(); // Update the display
        });
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss()); // Set the negative button of the dialog
        builder.show(); // Show the dialog
    }

    // Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.data_menu, menu); // Inflate the menu to appear in the action bar
        return true;
    }

    // Menu Item Click Handler
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.Toggle_Name) { // Target the toggle button
            boolean temp = sharedPreferenceHelper.getToggleStat(); // Temp variable to hold the current toggle status
            sharedPreferenceHelper.setToggleStat(!temp);  // Change the toggle status to the opposite of current
            DisplayEventData(); // Update the display
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

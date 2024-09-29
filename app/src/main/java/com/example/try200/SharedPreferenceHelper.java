package com.example.try200;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Class SharedPreferenceHelper to access and save our shared preferences
public class SharedPreferenceHelper {

    // Declaring a SharedPreferences object
    private SharedPreferences sharedPreferences;

    // Constructor to initialize the SharedPreferencesHelper object
    public SharedPreferenceHelper(Context context) {
        sharedPreferences = context.getSharedPreferences("EventCounterPrefs", Context.MODE_PRIVATE); // Initialize SharedPreferences
    }

    // Methods to save settings into SharedPreferences
    public void saveSettings(Settings settings) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("button1Name", settings.getButton1Name());
        editor.putString("button2Name", settings.getButton2Name());
        editor.putString("button3Name", settings.getButton3Name());
        editor.putInt("maxEventCount", settings.getMaxEventCount());
        editor.apply();
    }

    // Methods to get the saved settings from SharedPreferences
    public Settings getSettings() {

        //Variables to hold the saved settings from SharedPreferences
        String Name1 = sharedPreferences.getString("button1Name", null);
        String Name2 = sharedPreferences.getString("button2Name", null);
        String Name3 = sharedPreferences.getString("button3Name", null);
        int maxCount = sharedPreferences.getInt("maxEventCount", 0);

        // Check if no settings is saved
        if (Name1 == null || Name2 == null || Name3 == null || maxCount == 0) {
            return null;
        }

        return new Settings(Name1, Name2, Name3, maxCount); // Return the settings object
    }

    // Methods to save event counts to SharedPreferences
    public void saveEventCounts(int count1, int count2, int count3) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("eventCount1", count1);
        editor.putInt("eventCount2", count2);
        editor.putInt("eventCount3", count3);
        editor.apply();
    }

    // Methods to get event counts from SharedPreferences
    public int getEventCount(int event) {

        switch (event) { // Specifying which count we need
            case 1: return sharedPreferences.getInt("eventCount1", 0);
            case 2: return sharedPreferences.getInt("eventCount2", 0);
            case 3: return sharedPreferences.getInt("eventCount3", 0);
            default: return 0;
        }
    }

    // Methods to set the toggle status of event names in DataActivity to SharedPreferences
    // This is to keep which naming option the user prefer in DataActivity and loading it directly
    public void setToggleStat(boolean status) {
        sharedPreferences.edit().putBoolean("toggleStatus", status).apply();
    }

    // Methods to get the saved toggle status of event names in DataActivity from SharedPreferences
    public boolean getToggleStat() {
        return sharedPreferences.getBoolean("toggleStatus", true);
    }

    // Methods to save the event list to SharedPreferences
    public void saveEventList(int x) { // x is the event number
        String eventList = sharedPreferences.getString("eventList", ""); // Variable to hold current even list for checks

        if (eventList.isEmpty()) { // Check if the list is empty
            sharedPreferences.edit().putString("eventList", String.valueOf(x)).apply(); // Add the first event to the list in SharedPreferences as a string

        } else { // If the list is not empty
            String[] stringArray = eventList.split(","); // Split the list into an array of strings
            List<String> temp = new ArrayList<>(Arrays.asList(stringArray)); // Convert the array to a temporary list
            temp.add(String.valueOf(x)); // Add the new event to the list
            String newEventList = String.join(",", temp); // Convert the list back to a string
            sharedPreferences.edit().putString("eventList", newEventList).apply(); // Save the new list in SharedPreferences as a string
            }
    }

    // Methods to get the saved event list from SharedPreferences
    public List<String> getEventList() {
        String eventList = sharedPreferences.getString("eventList", ""); // Variable to hold current even list as a string for checks
        List<String> temp; // Temporary list to hold the event list string as a List

        if (eventList.isEmpty()) { // Check if the list is empty
            temp = new ArrayList<>(); // If empty, create an empty list

        } else { // If the list is not empty
            String[] stringArray = eventList.split(","); // Split the list into an array of strings
            temp = new ArrayList<>();
            for (String i : stringArray) { // Convert the array to a list
                temp.add(String.valueOf(i.trim())); // Add each string to the list as a trimmed string
            }
        }
        return temp; // Return the list of events as strings
    }

    // Methods to increment the event count for the desired event
    public void IncrementEventCount(int event) {

        switch (event) { // Specifying which count we want
            case 1:
                int count1 = sharedPreferences.getInt("eventCount1", 0);            // Get the current count for event
                count1++;                                                               // Increment the count
                sharedPreferences.edit().putInt("eventCount1", count1).apply();     // Save the new count in SharedPreferences
                break;
            case 2:
                int count2 = sharedPreferences.getInt("eventCount2", 0);
                count2++;
                sharedPreferences.edit().putInt("eventCount2", count2).apply();
                break;
            case 3:
                int count3 = sharedPreferences.getInt("eventCount3", 0);
                count3++;
                sharedPreferences.edit().putInt("eventCount3", count3).apply();
                break;
        }

        saveEventList(event); // Add that event to event List
    }

}

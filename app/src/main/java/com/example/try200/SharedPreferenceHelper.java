package com.example.try200;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SharedPreferenceHelper {

    private SharedPreferences sharedPreferences;

    public SharedPreferenceHelper(Context context) {
        sharedPreferences = context.getSharedPreferences("EventCounterPrefs", Context.MODE_PRIVATE);
    }

    public void saveSettings(Settings settings) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("button1Name", settings.getButton1Name());
        editor.putString("button2Name", settings.getButton2Name());
        editor.putString("button3Name", settings.getButton3Name());
        editor.putInt("maxEventCount", settings.getMaxEventCount());
        editor.apply();
    }

    public Settings getSettings() {
        String btn1Name = sharedPreferences.getString("button1Name", null);
        String btn2Name = sharedPreferences.getString("button2Name", null);
        String btn3Name = sharedPreferences.getString("button3Name", null);
        int maxCount = sharedPreferences.getInt("maxEventCount", 0);

        if (btn1Name == null || btn2Name == null || btn3Name == null || maxCount == 0) {
            return null;
        }

        return new Settings(btn1Name, btn2Name, btn3Name, maxCount);
    }

    public void saveEventCounts(int count1, int count2, int count3) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("eventCount1", count1);
        editor.putInt("eventCount2", count2);
        editor.putInt("eventCount3", count3);
        editor.apply();
    }

    public List<String> getEventHistory() {
        String eventHistory = sharedPreferences.getString("eventHistory", "");
        if (eventHistory.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.asList(eventHistory.split(","));
    }

    public int getTotalEventCount() {
        return sharedPreferences.getInt("eventCount1", 0) +
                sharedPreferences.getInt("eventCount2", 0) +
                sharedPreferences.getInt("eventCount3", 0);
    }
}
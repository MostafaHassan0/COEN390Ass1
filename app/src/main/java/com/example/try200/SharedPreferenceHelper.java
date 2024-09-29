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

    public int getEventCount(int event) {
        switch (event) {
            case 1: return sharedPreferences.getInt("eventCount1", 0);
            case 2: return sharedPreferences.getInt("eventCount2", 0);
            case 3: return sharedPreferences.getInt("eventCount3", 0);
            default: return 0;
        }
    }

    public void IncrementEventCount(int event) {
        switch (event) {
            case 1:
                int count1 = sharedPreferences.getInt("eventCount1", 0);
                count1++;
                sharedPreferences.edit().putInt("eventCount1", count1).apply();
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
                break; }
    }

    public boolean isAppRunFistTime(){
        return sharedPreferences.getBoolean("firstrun",true);
    }

    public void setAppRunFirstTime(boolean firstRun){
        sharedPreferences.edit().putBoolean("firstrun",firstRun).apply();
    }

    public List<String> getEventHistory() {
        String eventHistory = sharedPreferences.getString("eventHistory", "");
        if (eventHistory.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.asList(eventHistory.split(","));
    }
}

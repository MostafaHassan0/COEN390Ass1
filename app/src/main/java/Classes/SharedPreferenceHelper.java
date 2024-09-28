package Classes;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;

public class SharedPreferenceHelper {
    private SharedPreferences sharedPreferences;


    public SharedPreferenceHelper(Context context) {
        sharedPreferences = context.getSharedPreferences("ProfilePreference", Context.MODE_PRIVATE);
    }


    public void saveSettings(Setting s) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Event Names", Arrays.toString(s.Get_Names()));
        editor.putInt("Max Count", s.Get_Max_Count());
        editor.putString("Events", Arrays.toString(s.Get_Events_List_Names()));
        editor.putString("Counters", Arrays.toString(s.Get_Events_List_Counter()));
        editor.putString("Counters Count", Arrays.toString(s.Get_Events_Count()));
        editor.putInt("Count", s.Get_Counter());
        editor.commit();

    }

    public Setting GetSetting() {
        Setting s = new Setting();
        String[] str = sharedPreferences.getString("Events", null).split(", ");
        s.Set_Counter_Names(str);
        s.Set_Max_Count(sharedPreferences.getInt("Max Count", 0));
        str = sharedPreferences.getString("Counters", null).split(", ");
        s.events_list_counter = str;
        s.Update_list();


        String st = sharedPreferences.getString("Counters Count", null);
        String[] stringArray = st.replaceAll("\\\\[|\\\\]", "").split(", ");

        int[] intArray = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }
        s.events_count = intArray;
        s.counter = sharedPreferences.getInt("Count", 0);


        return s;
    }
}

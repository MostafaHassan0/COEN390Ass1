package com.example.try200;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import Classes.Setting;
import Classes.SharedPreferenceHelper;

public class MainActivity extends AppCompatActivity {

    protected SharedPreferenceHelper sharedPreferenceHelper;
    @Override
    protected void onStart(){
        super.onStart();
        setting = sharedPreferenceHelper.GetSetting();
        Counter.setText("Total Count: "+ setting.Get_Counter());
    }


    TextView Counter;
    Button EventA;
    Button EventB;
    Button EventC;
    Button SettingsBt;
    Button Databt;
    Setting setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getSupportActionBar().hide();

        Counter = findViewById(R.id.Counter);
        EventA = findViewById(R.id.EventA);
        EventB = findViewById(R.id.EventB);
        EventC = findViewById(R.id.EventC);
        SettingsBt = findViewById(R.id.SettingsBT);
        Databt = findViewById(R.id.Databt);

        EventA.setText(setting.Get_Names()[0]);
        EventB.setText(setting.Get_Names()[1]);
        EventC.setText(setting.Get_Names()[2]);

        if (setting.Get_Counter() >= setting.Get_Max_Count()){
        EventA.setOnClickListener(view -> {
            setting.Inc_Counter(0);
            Counter.setText("Total Count: "+ setting.Get_Counter());
            ;
        });

        EventB.setOnClickListener(view -> {
            setting.Inc_Counter(1);
            Counter.setText("Total Count: "+ setting.Get_Counter());;
        });

        EventC.setOnClickListener(view -> {
            setting.Inc_Counter(2);
            Counter.setText("Total Count: "+ setting.Get_Counter());;
        });
        }
        else {
            Toast.makeText(this, "Maximum Count Reached", Toast.LENGTH_SHORT).show();
        }

        SettingsBt.setOnClickListener(view -> {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        });

        Databt.setOnClickListener(view ->{
            Intent intent = new Intent(this, Data.class);
            startActivity(intent);
        });



    }


}
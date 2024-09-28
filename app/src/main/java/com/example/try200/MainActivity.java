package com.example.try200;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.try200.Settings;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView Counter;
    Button EventA;
    Button EventB;
    Button EventC;
    Button SettingsBt;
    Button Databt;
    int count = 0;



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

        EventA.setOnClickListener(view -> {
            count =count+5;
            Counter.setText("Total Count: "+ count);
        });

        EventB.setOnClickListener(view -> {
            count =count+10;
            Counter.setText("Total Count: "+ count);
        });

        EventC.setOnClickListener(view -> {
            count =count+2;
            Counter.setText("Total Count: "+ count);
        });

        SettingsBt.setOnClickListener(view -> {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        });

        Databt.setOnClickListener(view ->{
            Intent intent = new Intent(this, Data.class);
            startActivity(intent);
        });

//        Intent intent1 = getIntent();
//        String str = intent1.getStringExtra(":message_key");
//        EventB.setText(str);



    }

    public void Countincre(){
        if (count < 200){

        }
    }

}
package com.example.try200;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Settings extends AppCompatActivity {

    Button Save;
    EditText Counter1;
    EditText Counter2;
    EditText Counter3;
    EditText MaxCount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        getSupportActionBar().setTitle("Settings");
        Save = findViewById(R.id.Save);
        Counter1 = findViewById(R.id.Counter1);
        Counter2 = findViewById(R.id.Counter2);
        Counter3 = findViewById(R.id.Counter3);
        MaxCount = findViewById(R.id.MaxCount);



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        MenuItem Edit = menu.findItem(R.id.Edit);
        Edit.setOnMenuItemClickListener(new OnMenuItemClickListener(){
            public boolean onMenuItemClick(MenuItem item) {
                Save.setVisibility(View.VISIBLE);
                Counter1.setEnabled(true);
                Counter1.setClickable(true);
                Counter2.setEnabled(true);
                Counter2.setClickable(true);
                Counter3.setEnabled(true);
                Counter3.setClickable(true);
                MaxCount.setEnabled(true);
                MaxCount.setClickable(true);
                Edit.setVisible(false);

                return false;

        }
        });
        Save.setOnClickListener(view -> {
            try {
                int num = Integer.valueOf(String.valueOf(MaxCount.getText()));
                if ( num < 5 || num > 200) {
                    Toast.makeText(this, "Count must be between 5 and 200", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Maximum Count is empty", Toast.LENGTH_SHORT).show();
                return;
            }

            if (Counter1.getText().toString().isEmpty() || Counter2.getText().toString().isEmpty() || Counter3.getText().toString().isEmpty()) {
                Toast.makeText(this, "One or more name fileds are empty", Toast.LENGTH_SHORT).show();
                return;
            }

            // it doesn't work for now
//            if (Counter1.getText() == Counter2.getText() || Counter1.getText() == Counter3.getText() || Counter2.getText() == Counter3.getText()) {
//                Toast.makeText(this, "Counter names must be unique", Toast.LENGTH_SHORT).show();
//                return;
//            }



            Edit.setVisible(true);
            Save.setVisibility(View.GONE);
            Counter1.setEnabled(false);
            Counter1.setClickable(false);
            Counter2.setEnabled(false);
            Counter2.setClickable(false);
            Counter3.setEnabled(false);
            Counter3.setClickable(false);
            MaxCount.setEnabled(false);
            MaxCount.setClickable(false);
        });

        return true;
    }











}
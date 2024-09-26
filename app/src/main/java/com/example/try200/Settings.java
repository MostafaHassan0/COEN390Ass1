package com.example.try200;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Settings extends AppCompatActivity {

    Button Edit;
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

        Edit = findViewById(R.id.Edit);
        Save = findViewById(R.id.Save);
        Counter1 = findViewById(R.id.Counter1);
        Counter2 = findViewById(R.id.Couner2);
        Counter3 = findViewById(R.id.Counter3);
        MaxCount = findViewById(R.id.MaxCount);

        Save.setOnClickListener(view -> {
            View b = findViewById(R.id.Save);
            b.setVisibility(View.GONE);
            Counter1.setEnabled(false);
            Counter1.setClickable(false);
            Counter2.setEnabled(false);
            Counter2.setClickable(false);
            Counter3.setEnabled(false);
            Counter3.setClickable(false);
            MaxCount.setEnabled(false);
            MaxCount.setClickable(false);


        });

        Edit.setOnClickListener(view -> {
            View b = findViewById(R.id.Save);
            b.setVisibility(View.VISIBLE);
            Counter1.setEnabled(true);
            Counter1.setClickable(true);
            Counter2.setEnabled(true);
            Counter2.setClickable(true);
            Counter3.setEnabled(true);
            Counter3.setClickable(true);
            MaxCount.setEnabled(true);
            MaxCount.setClickable(true);

        });




//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayOptions(true);

//        NavController navController = Navigation.findNavController(this, R.id.);
//
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavigationUI.setupWithNavController(binding.myToolbar, navController, appBarConfiguration);

    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId()==android.R.id.home){
//            finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
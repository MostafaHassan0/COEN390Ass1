package com.example.try200;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Settings extends AppCompatActivity {

    //Button Edit;
    //MenuItem Edit;
    Button Save;
    EditText Counter1;
    EditText Counter2;
    EditText Counter3;
    EditText MaxCount;


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater(); //this adds items to the action bar if it is present.
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }


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
        //Edit = findViewById(R.id.Edit);
        Save = findViewById(R.id.Save);
        Counter1 = findViewById(R.id.Counter1);
        Counter2 = findViewById(R.id.Counter2);
        Counter3 = findViewById(R.id.Counter3);
        MaxCount = findViewById(R.id.MaxCount);
        // Edit = findViewById(R.id.Edit);



        Save.setOnClickListener(view -> {
            int num =  Integer.valueOf(String.valueOf(MaxCount.getText()));
            while (num > 200 ){
                Toast.makeText(this,"Maximum Count is 200",Toast.LENGTH_SHORT).show();
                return;
            }



//            String eventname = Counter2.getText().toString();
//            Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
//            intent1.putExtra("message_key",eventname);




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


//        Edit.setOnActionExpandListener(View ) {
//            View b = findViewById(R.id.Save);
//            b.setVisibility(View.VISIBLE);
//            Counter1.setEnabled(true);
//            Counter1.setClickable(true);
//            Counter2.setEnabled(true);
//            Counter2.setClickable(true);
//            Counter3.setEnabled(true);
//            Counter3.setClickable(true);
//            MaxCount.setEnabled(true);
//            MaxCount.setClickable(true);
//
//        });


    }





//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle item selection.
//        switch (item.getItemId()) {
//            case R.id.Edit_item:
////                View b = findViewById(R.id.Save);
//                Save.setVisibility(View.VISIBLE);
//                Counter1.setEnabled(true);
//                Counter1.setClickable(true);
//                Counter2.setEnabled(true);
//                Counter2.setClickable(true);
//                Counter3.setEnabled(true);
//                Counter3.setClickable(true);
//                MaxCount.setEnabled(true);
//                MaxCount.setClickable(true);
//                return true;
//
//            default:
//                return true;
//        }
//    }










}
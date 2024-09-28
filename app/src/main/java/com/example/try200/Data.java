package com.example.try200;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.ArrayAdapter;


public class Data extends AppCompatActivity {
    TextView Counter1LB;
    TextView Counter2LB;
    TextView Counter3LB;
    TextView TotalLB;
    ListView ListView;
    boolean name_state = false;
    int[] events_count = {5,4,3,22};
    String[] names = {"Event A", "Event B", "Event C"};
    String[] events_list_names = {"Event A", "Event B", "Event C","Event A", "Event B", "Event C","Event A", "Event B", "Event C","Event A", "Event B", "Event C","Event A", "Event B", "Event C"};
    String[] events_list_counter = {"1","2","3","1","2","3","1","2","3","1","2","3","1","2","3","1","2","3"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_data);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getSupportActionBar().setTitle("Data");

        TotalLB = findViewById(R.id.TotalLB);
        Counter1LB = findViewById(R.id.Counter1LB);
        Counter2LB = findViewById(R.id.Counter2LB);
        Counter3LB = findViewById(R.id.Counter3LB);
        ListView = findViewById(R.id.ListView);

        toggle_name_switch();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater(); //this adds items to the action bar if it is present.
        inflater.inflate(R.menu.data_menu, menu);
        MenuItem Toggle_Name = menu.findItem(R.id.Toggle_Name);
        Toggle_Name.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            public boolean onMenuItemClick(MenuItem item) {
                toggle_name_switch();
            return false;
            }
        });
        return true;
    }


    public void toggle_name_switch(){
        ArrayAdapter<String> names_adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, events_list_names);
        ArrayAdapter<String> counter_adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, events_list_counter);;
        if (name_state == false){
            Counter1LB.setText(names[0]+": "+ events_count[0] + " Events");
            Counter2LB.setText(names[1]+": "+ events_count[1] + " Events");
            Counter3LB.setText(names[2]+": "+ events_count[2] + " Events");
            ListView.setAdapter(names_adapter);
            name_state = true;




        }
        else if(name_state == true){
            Counter1LB.setText("Counter 1: " + events_count[0] + " Events");
            Counter2LB.setText("Counter 2: " + events_count[1] + " Events");
            Counter3LB.setText("Counter 3: " + events_count[2] + " Events");
            ListView.setAdapter(counter_adapter);
            name_state = false;
        }
    }
}
package com.example.findmyslot.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.findmyslot.R;
import com.example.findmyslot.adapters.SlotsAdapter;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private RecyclerView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Initialize();

        myView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ArrayList<String> items = fetch_data();
        SlotsAdapter adapter = new SlotsAdapter(items);
        myView.setAdapter(adapter);
    }

    private ArrayList<String> fetch_data(){

        ArrayList<String> items = new ArrayList<>();

        for(int i=0;i<10;i++)
            items.add(String.valueOf(i));

        return items;
    }

    private void Initialize(){

        myView = findViewById(R.id.recyclerView);
    }
}
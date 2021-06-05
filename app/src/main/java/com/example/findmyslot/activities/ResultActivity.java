package com.example.findmyslot.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.findmyslot.R;
import com.example.findmyslot.adapters.SlotsAdapter;
import com.example.findmyslot.dataClass.MySingleton;
import com.example.findmyslot.dataClass.Slots;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ResultActivity extends AppCompatActivity {

    private RecyclerView myView;
    private SlotsAdapter adapter;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Initialize();
        myView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        ArrayList<Slots> slot = fetch_data();
        adapter = new SlotsAdapter(getApplicationContext(), slot);
        myView.setAdapter(adapter);
    }


    ArrayList<Slots> fetch_data(){

        String date;
        @SuppressLint("SimpleDateFormat") DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        date = format.format(new Date());

        String type = getIntent().getStringExtra("type");

        if(type.equals("pincode"))
            url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode="+
                    getIntent().getStringExtra("input")+"&date="+date;
        if(type.equals("district"))
            url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByDistrict?district_id="+
                    getIntent().getStringExtra("input")+"&date="+date;


        ArrayList<Slots> slotItems = new ArrayList<>();

        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            
            try {

                JSONObject object = new JSONObject(response);
                JSONArray array = object.getJSONArray("sessions");

                for (int i = 0; i < array.length(); i++) {

                    JSONObject slot = array.getJSONObject(i);
                    slotItems.add(new Slots(slot.getString("name"),
                                                   slot.getString("date"),
                                                   slot.getString("vaccine"),
                                                   slot.getString("address"),
                                                   slot.getString("available_capacity")));
                    adapter.notifyDataSetChanged();
                }
            }
            catch (JSONException e) {

                Toast.makeText(getApplicationContext(),"Sorry",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }, error -> Toast.makeText(getApplicationContext(),"Sorry no response",Toast.LENGTH_SHORT).show());

        MySingleton.getInstance(this).addToRequestQueue(request);
        
        return slotItems;
    }

    private void Initialize(){

        myView = findViewById(R.id.recyclerView);
    }
}
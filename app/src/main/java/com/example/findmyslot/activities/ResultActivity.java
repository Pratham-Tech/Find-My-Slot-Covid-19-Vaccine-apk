package com.example.findmyslot.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private RecyclerView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Initialize();
        myView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        ArrayList<Slots> slot = fetch_data();
        SlotsAdapter adapter = new SlotsAdapter(getApplicationContext(), slot);
        myView.setAdapter(adapter);
    }


    ArrayList<Slots> fetch_data(){

        String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode="+
                getIntent().getStringExtra("input") +"&date=4-06-2021";

        ArrayList<Slots> slotItems = new ArrayList<>();

        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            
            try {

                JSONObject object = new JSONObject(response);
                JSONArray array = object.getJSONArray("sessions");

                for (int i = 0; i < array.length(); i++) {

                    JSONObject slot = array.getJSONObject(i);

                    String center,date,address,vaccine;
                    String slots;

                    center = slot.getString("name");
                    date = slot.getString("date");
                    address = slot.getString("address");
                    vaccine = slot.getString("vaccine");
                    slots = slot.getString("block_name");

                    //Toast.makeText(getApplicationContext(),slots,Toast.LENGTH_SHORT).show();

                    Slots s = new Slots();
                    s.setAddress(address);
                    s.setCenterName(center);
                    s.setDate(date);
                    s.setBlock_name(slots);
                    s.setVaccineName(vaccine);
                    slotItems.add(s);
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
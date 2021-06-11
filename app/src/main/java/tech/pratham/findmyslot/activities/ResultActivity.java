package tech.pratham.findmyslot.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import tech.pratham.findmyslot.R;
import tech.pratham.findmyslot.adapters.SlotsAdapter;
import tech.pratham.findmyslot.dataClass.MySingleton;
import tech.pratham.findmyslot.dataClass.Slots;
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
    private TextView dateView;
    private FloatingActionButton actionButton;
    private FloatingActionButton bookActionBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_result);

        Initialize();
        myView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        ArrayList<Slots> slot = fetch_data();

            adapter = new SlotsAdapter(getApplicationContext(), slot);
            myView.setAdapter(adapter);

            actionButton.setOnClickListener(v->{

                ArrayList<Slots> refreshSlots = fetch_data();
                adapter = new SlotsAdapter(getApplicationContext(),refreshSlots);
                myView.setAdapter(adapter);
            });

            bookActionBT.setOnClickListener(v->{

                String url = "https://selfregistration.cowin.gov.in/";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(this, Uri.parse(url));
            });
    }

    ArrayList<Slots> fetch_data(){

        String date;
        @SuppressLint("SimpleDateFormat") DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        date = format.format(new Date());

        dateView.setText(date);
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


                if(array.length()==0)
                    startActivity(new Intent(ResultActivity.this, NoResultActivity.class));
                else {
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject slot = array.getJSONObject(i);

                        slotItems.add(new Slots(slot.getString("name"),
                                slot.getString("vaccine"),
                                slot.getString("address"),
                                slot.getString("available_capacity_dose1"),
                                slot.getString("available_capacity_dose2")));

                        adapter.notifyDataSetChanged();
                    }
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
        actionButton = findViewById(R.id.actionBT);
        dateView = findViewById(R.id.vaccinationDate);
        bookActionBT = findViewById(R.id.bookActionBT);
    }
}
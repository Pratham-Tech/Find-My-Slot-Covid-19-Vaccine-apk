package app.cowin.findmyslot.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import app.cowin.findmyslot.R;
import app.cowin.findmyslot.adapters.SlotsAdapter;
import app.cowin.findmyslot.adapters.MySingleton;
import app.cowin.findmyslot.dataClass.Slots;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class ResultActivity extends AppCompatActivity {

    private RecyclerView myView;
    private SlotsAdapter adapter;
    private ArrayList<Slots> refreshSlots;
    private TextView dateView;
    private Button bookBt;
    private Button refreshBt;
    private ImageButton nextBt;
    private ImageButton backBt;
    @SuppressLint("SimpleDateFormat")
    private final DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    private String date = format.format(new Date());
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_result);

        Initialize();

        ArrayList<Slots> slotList = fetch_data(date);
        adapter = new SlotsAdapter(getApplicationContext(), slotList);
        myView.setAdapter(adapter);

            refreshBt.setOnClickListener(v->{

                refreshSlots = fetch_data(date);
                adapter = new SlotsAdapter(getApplicationContext(),refreshSlots);
                myView.setAdapter(adapter);
            });

            bookBt.setOnClickListener(v->{

                String url = "https://selfregistration.cowin.gov.in/";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(this, Uri.parse(url));
            });

            nextBt.setOnClickListener(v ->{

                date = IncrementDate();
                refreshSlots = fetch_data(date);
                adapter = new SlotsAdapter(getApplicationContext(),refreshSlots);
                myView.setAdapter(adapter);
            });

            backBt.setOnClickListener(v ->{

                date = DecrementDate();
                refreshSlots = fetch_data(date);
                adapter = new SlotsAdapter(getApplicationContext(),refreshSlots);
                myView.setAdapter(adapter);
            });
        }

    ArrayList<Slots> fetch_data(String date){

        dateView.setText(date);
        String type = getIntent().getStringExtra("type");
        String[] vaccineType = getIntent().getStringArrayExtra("vaccine");
        int age = getIntent().getIntExtra("age",18);

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

                        if (!slot.getString("available_capacity").equals("0")) {

                            if (vaccineType[0].equals("") && vaccineType[1].equals("") && vaccineType[2].equals("")) {

                                if (slot.getInt("min_age_limit") == age) {
                                    slotItems.add(new Slots(slot.getString("name"),
                                            slot.getString("vaccine"),
                                            slot.getString("address"),
                                            slot.getString("available_capacity_dose1"),
                                            slot.getString("available_capacity_dose2"),
                                            slot.getString("fee_type"),
                                            slot.getString("min_age_limit")));

                                    adapter.notifyDataSetChanged();
                                }
                            }
                            if (vaccineType[0].equals("Covaxin") && vaccineType[1].equals("Covishield") && vaccineType[2].equals("Sputnik V")) {

                                if (slot.getInt("min_age_limit") == age) {
                                    slotItems.add(new Slots(slot.getString("name"),
                                            slot.getString("vaccine"),
                                            slot.getString("address"),
                                            slot.getString("available_capacity_dose1"),
                                            slot.getString("available_capacity_dose2"),
                                            slot.getString("fee_type"),
                                            slot.getString("min_age_limit")));

                                    adapter.notifyDataSetChanged();
                                }
                            }
                            if (vaccineType[0].equals("Covaxin") && vaccineType[1].equals("") && vaccineType[2].equals("")) {

                                if (slot.getString("vaccine").equals("COVAXIN") &&
                                        slot.getInt("min_age_limit") == age) {

                                    slotItems.add(new Slots(slot.getString("name"),
                                            slot.getString("vaccine"),
                                            slot.getString("address"),
                                            slot.getString("available_capacity_dose1"),
                                            slot.getString("available_capacity_dose2"),
                                            slot.getString("fee_type"),
                                            slot.getString("min_age_limit")));

                                    adapter.notifyDataSetChanged();
                                }
                            }
                            if (vaccineType[0].equals("") && vaccineType[1].equals("Covishield") && vaccineType[2].equals("")) {

                                if (slot.getString("vaccine").equals("COVISHIELD") &&
                                        slot.getInt("min_age_limit") == age) {

                                    slotItems.add(new Slots(slot.getString("name"),
                                            slot.getString("vaccine"),
                                            slot.getString("address"),
                                            slot.getString("available_capacity_dose1"),
                                            slot.getString("available_capacity_dose2"),
                                            slot.getString("fee_type"),
                                            slot.getString("min_age_limit")));

                                    adapter.notifyDataSetChanged();
                                }
                            }
                            if (vaccineType[0].equals("") && vaccineType[1].equals("") && vaccineType[2].equals("Sputnik V")) {

                                if (slot.getString("vaccine").equals("SPUTNIK V") &&
                                        slot.getInt("min_age_limit") == age) {

                                    slotItems.add(new Slots(slot.getString("name"),
                                            slot.getString("vaccine"),
                                            slot.getString("address"),
                                            slot.getString("available_capacity_dose1"),
                                            slot.getString("available_capacity_dose2"),
                                            slot.getString("fee_type"),
                                            slot.getString("min_age_limit")));

                                    adapter.notifyDataSetChanged();
                                }
                            }
                            if (vaccineType[0].equals("Covaxin") && vaccineType[1].equals("Covishield") && vaccineType[2].equals("")) {

                                if ((slot.getString("vaccine").equals("COVAXIN") ||
                                        slot.getString("vaccine").equals("COVISHIELD")) &&
                                        slot.getInt("min_age_limit") == age) {

                                    slotItems.add(new Slots(slot.getString("name"),
                                            slot.getString("vaccine"),
                                            slot.getString("address"),
                                            slot.getString("available_capacity_dose1"),
                                            slot.getString("available_capacity_dose2"),
                                            slot.getString("fee_type"),
                                            slot.getString("min_age_limit")));

                                    adapter.notifyDataSetChanged();
                                }
                            }
                            if (vaccineType[0].equals("") && vaccineType[1].equals("Covishield") && vaccineType[2].equals("Sputnik V")) {

                                if ((slot.getString("vaccine").equals("COVISHIELD") ||
                                        slot.getString("vaccine").equals("SPUTNIK V")) &&
                                        slot.getInt("min_age_limit") == age) {

                                    slotItems.add(new Slots(slot.getString("name"),
                                            slot.getString("vaccine"),
                                            slot.getString("address"),
                                            slot.getString("available_capacity_dose1"),
                                            slot.getString("available_capacity_dose2"),
                                            slot.getString("fee_type"),
                                            slot.getString("min_age_limit")));

                                    adapter.notifyDataSetChanged();
                                }
                            }
                            if (vaccineType[0].equals("Covaxin") && vaccineType[1].equals("") && vaccineType[2].equals("Sputnik V")) {

                                if ((slot.getString("vaccine").equals("COVAXIN") ||
                                        slot.getString("vaccine").equals("SPUTNIK V")) &&
                                        slot.getInt("min_age_limit") == age) {

                                    slotItems.add(new Slots(slot.getString("name"),
                                            slot.getString("vaccine"),
                                            slot.getString("address"),
                                            slot.getString("available_capacity_dose1"),
                                            slot.getString("available_capacity_dose2"),
                                            slot.getString("fee_type"),
                                            slot.getString("min_age_limit")));

                                    adapter.notifyDataSetChanged();
                                }
                            }
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

    private String IncrementDate(){

        String newDate;

        Calendar c = Calendar.getInstance();
        try {
            c.setTime(Objects.requireNonNull(format.parse(date)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 1);  // number of days to add
        newDate = format.format(c.getTime());

        return newDate;
    }

    private String DecrementDate(){

        String newDate;

        Calendar c = Calendar.getInstance();
        try {
            c.setTime(Objects.requireNonNull(format.parse(date)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, -1);  // number of days to decrement
        newDate = format.format(c.getTime());

        return newDate;
    }

    private void Initialize(){

        myView = findViewById(R.id.recyclerView);
        dateView = findViewById(R.id.vaccinationDate);
        bookBt = findViewById(R.id.bookBT);
        refreshBt = findViewById(R.id.refreshBT);
        nextBt = findViewById(R.id.nextBt);
        backBt = findViewById(R.id.backBt);

        myView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
//              LinearLayout myCons = findViewById(R.id.resLinear);
//
//                    TextView textView = new TextView(getApplicationContext());
//                    textView.setGravity(Gravity.CENTER);
//                    textView.setText(R.string.No_slots);
//                    textView.setTextSize(45,43);
//                    textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                    textView.setVisibility(View.VISIBLE);
//
//                    myCons.addView(textView);
//        }
//            myView.setVisibility(View.VISIBLE);
//            bookBt.setVisibility(View.VISIBLE);
//
}
package app.cowin.findmyslot.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import java.util.Objects;
import app.cowin.findmyslot.R;
import app.cowin.findmyslot.dataClass.Districts;
import app.cowin.findmyslot.dataClass.States;
import static app.cowin.findmyslot.R.id.radioButton1;
import static app.cowin.findmyslot.R.id.radioButton2;
import static app.cowin.findmyslot.R.id.radioButton3;
import static app.cowin.findmyslot.R.id.radioButton4;

public class HomeActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    protected String[] vaccineType = {"","",""};
    private RadioGroup radioGroup;
    private RadioGroup radioGroup2;
    private EditText pincodeInput;
    private CheckBox covaxinCb;
    private CheckBox covishieldCb;
    private CheckBox sputnikCb;
    private Spinner stateSpinner;
    private Spinner districtSpinner;
    private RadioButton age18;
    private RadioButton age45;
    private Button findSlots;
    private String Pincode;
    private String District = "Select District";
    private String State = "Select State";
    private String districtID = "0";
    private int age;
    private int ALL_SET = 0;
    private int radioId;
    private Intent intent;
    private ArrayAdapter<String> districtsAdapter;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.app_icon);

        Initialize();
        intent = new Intent(HomeActivity.this, ResultActivity.class);

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        radioGroup2.setOnCheckedChangeListener(this);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {

            switch (checkedId) {

                case radioButton1:

                    stateSpinner.setVisibility(View.INVISIBLE);
                    districtSpinner.setVisibility(View.INVISIBLE);
                    pincodeInput.setVisibility(View.VISIBLE);

                    pincodeInput.requestFocus();
                    findSlots.setEnabled(true);
                    radioId = checkedId;
                    break;

                case radioButton2:

                    pincodeInput.setVisibility(View.INVISIBLE);
                    stateSpinner.setVisibility(View.VISIBLE);
                    districtSpinner.setVisibility(View.VISIBLE);

                    ArrayAdapter<CharSequence> stateList = ArrayAdapter.createFromResource(this,
                            R.array.states, R.layout.spinner_text);

                    stateList.setDropDownViewResource(R.layout.spinner_text);
                    stateSpinner.setAdapter(stateList);

                    findSlots.setEnabled(true);
                    radioId = checkedId;
                    break;
            }
        });

        findSlots.setOnClickListener(view -> {

            validate(radioId);

            switch (ALL_SET) {

                case 1:

                    intent.putExtra("input", Pincode);
                    intent.putExtra("vaccine",vaccineType);
                    intent.putExtra("age",age);
                    intent.putExtra("type","pincode");
                    startActivity(intent);
                    break;

                case 2:

                    intent.putExtra("input", districtID);
                    intent.putExtra("vaccine",vaccineType);
                    intent.putExtra("age",age);
                    intent.putExtra("type","district");
                    startActivity(intent);
                    break;

                case 0:

                    break;
            }
        });

        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                State = parent.getItemAtPosition(position).toString();
                States s = new States();

                districtsAdapter = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, s.getDistricts(position));
                districtsAdapter.setDropDownViewResource(R.layout.spinner_text);

                districtSpinner.setAdapter(districtsAdapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Toast.makeText(getApplicationContext(), "Please select your state"
                        , Toast.LENGTH_SHORT).show();
            }
        });

        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Districts d = new Districts();

                District = parent.getItemAtPosition(position).toString();
                districtID = d.getDistrictId(District);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        covaxinCb.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (covaxinCb.isChecked())
                vaccineType[0] = String.valueOf(covaxinCb.getText());
            else
                vaccineType[0] = "";

        });

        covishieldCb.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (covishieldCb.isChecked())
                vaccineType[1] = String.valueOf(covishieldCb.getText());
            else
                vaccineType[1] = "";

        });

        sputnikCb.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (sputnikCb.isChecked())
                vaccineType[2] = String.valueOf(sputnikCb.getText());
            else
                vaccineType[2] = "";
        });
    }

    private void Initialize() {

        radioGroup = findViewById(R.id.radioGrp);
        radioGroup2 = findViewById(R.id.radioGrp2);
        pincodeInput = findViewById(R.id.pincodeInput);
        districtSpinner = findViewById(R.id.listDistrict);
        stateSpinner = findViewById(R.id.listState);
        findSlots = findViewById(R.id.findSlots);
        covaxinCb = findViewById(R.id.covaxinCB);
        covishieldCb = findViewById(R.id.covishieldCB);
        sputnikCb = findViewById(R.id.sputnikCB);
        age18 = findViewById(radioButton3);
        age45 = findViewById(radioButton4);

        pincodeInput.setVisibility(View.INVISIBLE);
        stateSpinner.setVisibility(View.INVISIBLE);
        districtSpinner.setVisibility(View.INVISIBLE);
        findSlots.setEnabled(false);
    }

    private void validate(int checkedId) {

        if (checkedId == radioButton1) {

            if (TextUtils.isEmpty(pincodeInput.getText().toString()) || pincodeInput.getText().length() < 6) {

                Toast.makeText(getApplicationContext(), "Please enter any valid pincode", Toast.LENGTH_SHORT).show();
                ALL_SET = 0;
            } else {

                Pincode = pincodeInput.getText().toString();
                findSlots.setEnabled(true);
                ALL_SET = 1;
            }
        }

        if (checkedId == radioButton2) {

            if (!State.equals("Select State") && !District.equals("Select District"))
                ALL_SET = 2;

            else {

                if (State.equals("Select State"))
                    Toast.makeText(getApplicationContext(), "Please select your state"
                            , Toast.LENGTH_SHORT).show();

                ALL_SET = 0;
            }
        }

        if(!age18.isChecked() && !age45.isChecked()){
            ALL_SET =0;
            Toast.makeText(getApplicationContext(), "Please select age group"
                    , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch(checkedId){

            case radioButton3:

                age = 18;
                break;

            case radioButton4:

                age = 45;
                break;

            default:
                Toast.makeText(getApplicationContext(),"Please select age group",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
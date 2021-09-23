package app.cowin.findmyslot.activities;

import static app.cowin.findmyslot.R.id.radioButton1;
import static app.cowin.findmyslot.R.id.radioButton2;
import static app.cowin.findmyslot.R.id.radioButton3;
import static app.cowin.findmyslot.R.id.radioButton4;
import static app.cowin.findmyslot.activities.fragments.PincodeFragment.pincodeInput;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import app.cowin.findmyslot.R;
import app.cowin.findmyslot.activities.fragments.DistrictFragment;
import app.cowin.findmyslot.activities.fragments.PincodeFragment;

public class FindActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    protected String[] vaccineType = {"","",""};
    private RadioGroup radioGroup;
    private RadioGroup radioGroup2;
    private CheckBox covaxinCb;
    private CheckBox covishieldCb;
    private CheckBox sputnikCb;
    private RadioButton age18;
    private RadioButton age45;
    private Button findSlots;
    private String Pincode;
    public static String District = "Select District";
    public static String State = "Select State";
    public static String districtID = "0";
    private int age;
    private int ALL_SET = 0;
    private int radioId;
    private Intent intent;
    private TextView UserName;
    private PincodeFragment pincodeFragment = new PincodeFragment();
    private DistrictFragment districtFragment = new DistrictFragment();


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        Initialize();

        UserName.setText(VerificationActivity.UserName);

        intent = new Intent(FindActivity.this, ResultActivity.class);

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        radioGroup2.setOnCheckedChangeListener(this);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {

            switch (checkedId) {

                case radioButton1:

                    this.getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.inputFrame, new PincodeFragment())
                            .addToBackStack(null).commit();

                    findSlots.setEnabled(true);
                    radioId = checkedId;
                    break;

                case radioButton2:

                    this.getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.inputFrame, new DistrictFragment())
                            .addToBackStack(null)
                            .commit();

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

//
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
        findSlots = findViewById(R.id.findSlots);
        covaxinCb = findViewById(R.id.covaxinCB);
        covishieldCb = findViewById(R.id.covishieldCB);
        sputnikCb = findViewById(R.id.sputnikCB);
        age18 = findViewById(radioButton3);
        age45 = findViewById(radioButton4);
        UserName = findViewById(R.id.userNameView);

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
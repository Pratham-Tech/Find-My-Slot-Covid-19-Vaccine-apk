package com.example.findmyslot.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.findmyslot.R;

public class HomeActivity extends AppCompatActivity{

    private EditText pincodeText;
    private Spinner stateSpinner;
    private Spinner districtSpinner;
    private Button findSlots;
    private Boolean ALL_SET = false;
    private int radioId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Initialize();

        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGrp);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch(checkedId)
                {
                    case R.id.radioButton1:

                        stateSpinner.setVisibility(View.INVISIBLE);
                        districtSpinner.setVisibility(View.INVISIBLE);
                        pincodeText.setVisibility(View.VISIBLE);
                        findSlots.setEnabled(true);
                        radioId = checkedId;
                        break;

                    case R.id.radioButton2:
                        pincodeText.setVisibility(View.INVISIBLE);
                        stateSpinner.setVisibility(View.VISIBLE);
                        districtSpinner.setVisibility(View.VISIBLE);
                        findSlots.setEnabled(true);
                        radioId = checkedId;
                        break;
                }
            }
        });

        findSlots.setOnClickListener(view ->{

            validate(radioId);

            if(ALL_SET)
                startActivity(new Intent(HomeActivity.this,NoResultActivity.class));
        });

    }

    private void Initialize(){

        pincodeText = findViewById(R.id.pincodeInput);
        stateSpinner = findViewById(R.id.listState);
        districtSpinner = findViewById(R.id.listDistrict);
        findSlots = findViewById(R.id.findSlots);

        pincodeText.setVisibility(View.INVISIBLE);
        stateSpinner.setVisibility(View.INVISIBLE);
        districtSpinner.setVisibility(View.INVISIBLE);
        findSlots.setEnabled(false);

        }

    private void validate(int checkedId){

        if(checkedId == R.id.radioButton1){

            if (TextUtils.isEmpty(pincodeText.getText().toString()) || pincodeText.getText().length() < 6) {

                Toast.makeText(getApplicationContext(),"Please enter any valid pincode",Toast.LENGTH_SHORT).show();
                ALL_SET = false;
            }
            else
                ALL_SET = true;
        }
        else{

            if (stateSpinner.getSelectedItem()== null || districtSpinner.getSelectedItem()== null) {

                Toast.makeText(getApplicationContext(),"Please select your district",Toast.LENGTH_SHORT).show();
                ALL_SET = false;
            }
            else if(stateSpinner.getSelectedItem().toString().equals("Select State") || districtSpinner.getSelectedItem().toString().equals("Select District")){

                Toast.makeText(getApplicationContext(),"Please select your district",Toast.LENGTH_SHORT).show();
                ALL_SET = false;
            }
            else
                ALL_SET = true;
        }
    }
}
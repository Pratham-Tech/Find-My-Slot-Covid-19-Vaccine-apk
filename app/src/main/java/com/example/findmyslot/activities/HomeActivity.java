package com.example.findmyslot.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.findmyslot.R;
import static com.example.findmyslot.R.id.radioButton1;
import static com.example.findmyslot.R.id.radioButton2;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private RadioGroup radioGroup;
    private EditText pincodeInput;
    private Spinner stateSpinner;
    private Spinner districtSpinner;
    private Button findSlots;
    private int ALL_SET = 0;
    private int radioId;
    private String Pincode;
    private String State = "Select State";
    private String District = "Select District";
    private Intent intent;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Initialize();
        intent = new Intent(HomeActivity.this, ResultActivity.class);

        stateSpinner.setOnItemSelectedListener(this);
        districtSpinner.setOnItemSelectedListener(this);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {

            switch (checkedId) {

                case radioButton1:

                    stateSpinner.setVisibility(View.INVISIBLE);
                    districtSpinner.setVisibility(View.INVISIBLE);
                    pincodeInput.setVisibility(View.VISIBLE);
                    pincodeInput.setFocusableInTouchMode(true);
                    findSlots.setEnabled(true);
                    radioId = checkedId;
                    break;

                case radioButton2:

                    pincodeInput.setVisibility(View.INVISIBLE);
                    stateSpinner.setVisibility(View.VISIBLE);

                    ArrayAdapter<CharSequence> stateList = ArrayAdapter.createFromResource(this,
                            R.array.states, R.layout.support_simple_spinner_dropdown_item);

                    stateList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
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
                    startActivity(intent);
                    break;

                case 2:

                    intent.putExtra("input", District);
                    startActivity(intent);
                    break;

                case 0:

                    break;
            }
        });

    }

    private void Initialize() {

        radioGroup = findViewById(R.id.radioGrp);
        pincodeInput = findViewById(R.id.pincodeInput);
        districtSpinner = findViewById(R.id.listDistrict);
        stateSpinner = findViewById(R.id.listState);
        findSlots = findViewById(R.id.findSlots);

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
            else
                ALL_SET = 0;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent.getId() == stateSpinner.getId()) {

            State = parent.getSelectedItem().toString();

            if (State.equals("Select State"))
                Toast.makeText(getApplicationContext(), "Please select your state",
                        Toast.LENGTH_SHORT).show();
            else {

                ArrayAdapter<CharSequence> districtList = ArrayAdapter.createFromResource(this,
                        R.array.states, R.layout.support_simple_spinner_dropdown_item);

                districtList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                districtSpinner.setAdapter(districtList);

                districtSpinner.setVisibility(View.VISIBLE);
            }
        } else {

            District = parent.getSelectedItem().toString();

            if (District.equals("Select District"))
                Toast.makeText(getApplicationContext(), "Please select your district",
                        Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
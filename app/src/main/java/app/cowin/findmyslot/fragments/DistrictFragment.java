package app.cowin.findmyslot.activities.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import app.cowin.findmyslot.R;
import app.cowin.findmyslot.activities.FindActivity;
import app.cowin.findmyslot.dataClass.Districts;
import app.cowin.findmyslot.dataClass.States;

public class DistrictFragment extends Fragment {

    private Spinner stateSpinner;
    private Spinner districtSpinner;
    private ArrayAdapter<String> districtsAdapter;

    public DistrictFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        districtSpinner = view.findViewById(R.id.listDistrict);
        stateSpinner = view.findViewById(R.id.listState);

        ArrayAdapter<CharSequence> stateList = ArrayAdapter.createFromResource(getContext(),
                R.array.states, R.layout.spinner_text);

        stateList.setDropDownViewResource(R.layout.spinner_text);
        stateSpinner.setAdapter(stateList);

        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                FindActivity.State = parent.getItemAtPosition(position).toString();
                States s = new States();

                districtsAdapter = new ArrayAdapter<>(getContext(),
                        R.layout.spinner_text, s.getDistricts(position));
                districtsAdapter.setDropDownViewResource(R.layout.spinner_text);

                districtSpinner.setAdapter(districtsAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Toast.makeText(getContext(), "Please select your state"
                        , Toast.LENGTH_SHORT).show();
            }
        });

        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Districts d = new Districts();

                FindActivity.District = parent.getItemAtPosition(position).toString();
                FindActivity.districtID = d.getDistrictId(FindActivity.District);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_district, container, false);
    }
}
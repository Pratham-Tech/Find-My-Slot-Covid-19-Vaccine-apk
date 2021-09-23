package app.cowin.findmyslot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import app.cowin.findmyslot.activities.VerificationActivity;

public class VerifyOtpFragment extends Fragment {

    private Button verifyOtp_bt;
    private EditText userOtp_et;

    public VerifyOtpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_verify_otp, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        verifyOtp_bt = view.findViewById(R.id.verifyOtp_bt);
        userOtp_et = view.findViewById(R.id.otp_et);

        verifyOtp_bt.setOnClickListener(v->{

            VerificationActivity.userOtp = String.valueOf(userOtp_et.getText());
            VerificationActivity.validateOtp(getContext());
        });
    }
}
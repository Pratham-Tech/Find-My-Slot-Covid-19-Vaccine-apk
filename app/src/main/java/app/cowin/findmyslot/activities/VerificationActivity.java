package app.cowin.findmyslot.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONException;
import org.json.JSONObject;

import app.cowin.findmyslot.R;
import app.cowin.findmyslot.VerifyOtpFragment;
import app.cowin.findmyslot.adapters.MySingleton;

public class VerificationActivity extends AppCompatActivity {

    protected static String UserName;
    private EditText userName;
    private EditText userMobileNumber;
    private Button getOTP_bt;
    private String OriginaltxnId;
    public static String userOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        userName = findViewById(R.id.userName);
        userMobileNumber = findViewById(R.id.userMobileNumber);
        getOTP_bt = findViewById(R.id.getOTP_bt);

        getOTP_bt.setOnClickListener(v -> {

            UserName = String.valueOf(userName.getText()).trim();

            if (!UserName.equals("")) {

                if (!(userMobileNumber.length() < 10)) {

                    generateOtp();

                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.otpFrame, new VerifyOtpFragment())
                            .addToBackStack(null)
                            .commit();

                    //callTimer();
                    startActivity(new Intent(this, FindActivity.class));

                } else {

                    Toast.makeText(getApplicationContext(), "enter a valid phone number.", Toast.LENGTH_SHORT).show();
                }
            } else {

                Toast.makeText(getApplicationContext(), "Name can't be empty!", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void generateOtp() {

        String OtpUrl = "https://cdn-api.co-vin.in/api/v2/auth/public/generateOTP";

        StringRequest st = new StringRequest(Request.Method.POST, OtpUrl, response -> {

            try {

                JSONObject object = new JSONObject(response);
                OriginaltxnId = object.getString("txnId");

            } catch (JSONException e) {

                Toast.makeText(getApplicationContext(), "Sorry", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }, error -> Toast.makeText(getApplicationContext(), "Sorry no response", Toast.LENGTH_SHORT).show()) {
            @Override
            public byte[] getBody() {
                String str = "{\"mobile\":\"" + userMobileNumber.getText() + "\"}";
                return str.getBytes();
            }

            public String getBodyContentType() {
                return "application/json";
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(st);
    }

    public static void validateOtp(Context context) {

        String GenrateHash = DigestUtils.sha256Hex(userOtp);

        Toast.makeText(context, GenrateHash, Toast.LENGTH_SHORT).show();

        //startActivity(new Intent(this, FindActivity.class));

    }

    private void callTimer(){

        getOTP_bt.setEnabled(false);
        new CountDownTimer(180000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                getOTP_bt.setEnabled(true);
            }
        }.start();
    }
}
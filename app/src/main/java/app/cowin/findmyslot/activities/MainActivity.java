package app.cowin.findmyslot.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.MobileAds;

import app.cowin.findmyslot.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, initializationStatus -> {
        });

        new Handler().postDelayed(() -> {

            Intent intent = new Intent(MainActivity.this, VerificationActivity.class);

            startActivity(intent);
            finish();
        },300);
    }
}
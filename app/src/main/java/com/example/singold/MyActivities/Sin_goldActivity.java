package com.example.singold.MyActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.singold.R;

public class Sin_goldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sin_gold);
        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(1 * 1000);
                    Intent i = new Intent(getBaseContext(), LogInActivity.class);
                    startActivity(i);
                    finish();
                } catch (Exception e) {
                }
            }
        };

        background.start();
    }


}

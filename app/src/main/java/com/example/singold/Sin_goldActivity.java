package com.example.singold;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Sin_goldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sin_gold);
        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(3 * 1000);
                    Intent i = new Intent(getBaseContext(), WelcomeActivity.class);
                    startActivity(i);
                    finish();
                } catch (Exception e) {
                }
            }
        };

        background.start();
    }


}

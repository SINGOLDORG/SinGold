package com.example.singold;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.singold.data.MatchingSurvey;

public class PatientActivity extends AppCompatActivity
{
    private Button playList;
    private Button newMatching;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        playList = (Button) findViewById(R.id.playList);
        newMatching = (Button) findViewById(R.id.newMatching);
    }
    public void onClick(View v)
    {
        if (v == playList) {
            Intent intent = new Intent(getBaseContext(), PlaylistActivity.class);
            startActivity(intent);

        }
        if (v == newMatching) {
            Intent intent = new Intent(getBaseContext(), MatchingSurvey.class);
            startActivity(intent);

        }
    }
}

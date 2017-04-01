package com.example.singold.MyActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.singold.R;
import com.example.singold.data.MatchingSurvey;
import com.example.singold.data.PatientDetails;

public class PatientActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button playList;
    private Button newMatching;
    private PatientDetails patientDetails;
    private TextView tvname;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        playList = (Button) findViewById(R.id.playList);
        newMatching = (Button) findViewById(R.id.newMatching);
        tvname= (TextView) findViewById(R.id.tvname);
        newMatching.setOnClickListener(this);
        playList.setOnClickListener(this);
        Intent i=getIntent();
        if(i!=null)
        {
            patientDetails = (PatientDetails) i.getExtras().get("patient");
            tvname.setText(patientDetails.getfName());

        }
    }
    public void onClick(View v)
    {
        if (v == playList) {
            Intent intent = new Intent(getBaseContext(), PlaylistActivity.class);
            startActivity(intent);

        }
        if (v == newMatching) {
            Intent intent = new Intent(getBaseContext(), MatchingSurveyActivity.class);
            startActivity(intent);
        }
    }
}

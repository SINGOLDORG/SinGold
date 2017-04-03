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
    private Button Questionaire;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        playList = (Button) findViewById(R.id.playList);
        newMatching = (Button) findViewById(R.id.newMatching);
        tvname= (TextView) findViewById(R.id.tvname);
        Questionaire=(Button)findViewById(R.id.Questionaire);
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
            intent.putExtra("patient", patientDetails);
            startActivity(intent);

        }
        if (v == newMatching) {
          //  Intent intent = new Intent(getBaseContext(), MatchingSurveyActivity.class);
            Intent intent = new Intent(getBaseContext(), MatchingSurveyActivity.class);
            intent.putExtra("patient", patientDetails);
            startActivity(intent);
        }
        if (v == Questionaire) {
            //  Intent intent = new Intent(getBaseContext(), MatchingSurveyActivity.class);
            Intent intent = new Intent(getBaseContext(), QuestionaireActivity.class);
            intent.putExtra("patient", patientDetails);
            startActivity(intent);
        }
    }
}

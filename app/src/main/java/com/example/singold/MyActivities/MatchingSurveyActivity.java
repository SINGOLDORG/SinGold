package com.example.singold.MyActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.singold.R;
import com.example.singold.data.ConnectToServer;
import com.example.singold.data.MatchingSurvey;
import com.example.singold.data.PatientDetails;
import com.example.singold.data.PatientProfile;
import com.example.singold.data.Song;

import java.util.concurrent.ExecutionException;

/**
 * Created by user on 01/04/2017.
 */

public class MatchingSurveyActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText  country, year, religion, language,etCultutre;
    private Button btnSearch;
    private PatientDetails patientDetails;
    private RadioGroup rgQ3language;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_survey);

        etCultutre=(EditText)findViewById(R.id.etCultutre) ;
        rgQ3language=(RadioGroup)findViewById(R.id.rgQ3language);
        country = (EditText) findViewById(R.id.country);
        year = (EditText) findViewById(R.id.year);
        religion = (EditText) findViewById(R.id.religin);
        language = (EditText) findViewById(R.id.language);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);
        Intent i=getIntent();
        if(i!=null)
        {
            patientDetails= (PatientDetails) i.getExtras().get("patient");
        }
    }

    private void dataHandler() {

        String stCountry = country.getText().toString();
        String stCulture = etCultutre.getText().toString();

        String stYear = year.getText().toString();
        String stReligion = religion.getText().toString();
        String stLanguage = language.getText().toString();
        int id=rgQ3language.getCheckedRadioButtonId();
        if(id!=-1)
        {
            stLanguage=((RadioButton)findViewById(id)).getText().toString();
        }
        boolean isok = true;


        if (stCountry.length() == 0) {
            country.setError("Enter your country");
            isok = false;
        }
        if (stCulture.length() == 0) {
            etCultutre.setError("Enter your country");
            isok = false;
        }
        if (stYear.length() == 0) {
            year.setError("Enter your year of birth");
            isok = false;
        }
        if (stReligion.length() == 0) {
            religion.setError("Enter your city");
            isok = false;
        }
        if (stLanguage.length() == 0) {
            language.setError("Enter your language");
            isok = false;
        }
        if (isok == true) {

            PatientProfile patientProfile=new PatientProfile();
            patientProfile.setCulture(stCulture);
            patientProfile.setCountry(stCountry);
            patientProfile.setLanguage(stLanguage);
            patientProfile.setReligion(stReligion);
            patientProfile.setYear(Integer.parseInt(stYear));
            Intent i=new Intent(this,FoundSongActivity.class);
            i.putExtra("patientProfile",patientProfile);
            i.putExtra("patient",patientDetails);
            startActivity(i);


        }
    }

    public void onClick(View v) {
        if (v == btnSearch) {
            dataHandler();
        }

    }
}


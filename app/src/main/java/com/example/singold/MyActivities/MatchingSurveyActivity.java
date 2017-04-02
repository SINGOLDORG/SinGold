package com.example.singold.MyActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.singold.R;

/**
 * Created by user on 01/04/2017.
 */

public class MatchingSurveyActivity extends AppCompatActivity {
    private EditText  country, year, religion, language;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_survey);


        country = (EditText) findViewById(R.id.country);
        year = (EditText) findViewById(R.id.year);
        religion = (EditText) findViewById(R.id.religin);
        language = (EditText) findViewById(R.id.language);
        btnSearch = (Button) findViewById(R.id.btnSearch);
    }

    private void dataHandler() {

        String stCountry = country.getText().toString();
        String stYear = year.getText().toString();
        String stReligion = religion.getText().toString();
        String stLanguage = language.getText().toString();

        boolean isok = true;


        if (stCountry.length() == 0) {
            country.setError("Enter your country");
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

        }
    }

    public void onClick(View v) {
        if (v == btnSearch) {
            dataHandler();
        }

    }
}


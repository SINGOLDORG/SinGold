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
    private EditText id, country, year, city, language;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_survey);

        id = (EditText) findViewById(R.id.ma7azot);
        country = (EditText) findViewById(R.id.country);
        year = (EditText) findViewById(R.id.year);
        city = (EditText) findViewById(R.id.city);
        language = (EditText) findViewById(R.id.language);
        btnSearch = (Button) findViewById(R.id.btnSearch);
    }

    private void dataHandler() {
        String stId = id.getText().toString();
        String stCountry = country.getText().toString();
        String stYear = year.getText().toString();
        String stCity = city.getText().toString();
        String stLanguage = language.getText().toString();

        boolean isok = true;

        if (stId.length() < 9) {
            id.setError("Wrong id");
            isok = false;
        }
        if (stCountry.length() == 0) {
            country.setError("Enter your country");
            isok = false;
        }
        if (stYear.length() == 0) {
            year.setError("Enter your year of birth");
            isok = false;
        }
        if (stCity.length() == 0) {
            city.setError("Enter your city");
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


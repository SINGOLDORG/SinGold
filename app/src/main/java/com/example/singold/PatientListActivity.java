package com.example.singold;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class PatientListActivity extends AppCompatActivity
{
    private EditText search1;
    private Button Search;
    private ListView patient;
    private Button addPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);

        search1 = (EditText) findViewById(R.id.search1);
        Search = (Button) findViewById(R.id.Saerch);
        patient=(ListView)findViewById(R.id.patient);
        addPatient=(Button)findViewById(R.id.addPatient);

    }
    public void onClick (View v)
    {
        if(v==addPatient){
            Intent intent= new Intent(getBaseContext(), AddPatientActivity.class);
            startActivity(intent);
        }

    }
}

package com.example.singold.MyActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.singold.R;
import com.example.singold.data.ConnectToServer;
import com.example.singold.data.PatientDetailsAdapter;

public class PatientListActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText search1;
    private Button Search;
    private ListView patientList;
    private Button addPatient;
    private Button patientX;
    private PatientDetailsAdapter detailsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);

        search1 = (EditText) findViewById(R.id.search1);
        Search = (Button) findViewById(R.id.Saerch);
        addPatient = (Button) findViewById(R.id.addPatient);
        patientX = (Button) findViewById(R.id.patientX);
        patientX.setOnClickListener(this);
        addPatient.setOnClickListener(this);
        patientList = (ListView) findViewById(R.id.patientList);



    }


    @Override
    protected void onStart() {
        super.onStart();
        initListView();
    }

    private void initListView() {
        if(detailsAdapter==null)
            detailsAdapter=new PatientDetailsAdapter(this, R.layout.item_patient_details);
        patientList.setAdapter(detailsAdapter);
        ConnectToServer.connect(this);
        ConnectToServer.refreshItemsFromTable(detailsAdapter);

    }

    public void onClick(View v) {
        if (v == addPatient) {
            Intent intent = new Intent(getBaseContext(), AddPatientActivity.class);
            startActivity(intent);
        }
        if (v == patientX) {
            Intent intent = new Intent(getBaseContext(), PatientActivity.class);
            startActivity(intent);
        }
    }
}



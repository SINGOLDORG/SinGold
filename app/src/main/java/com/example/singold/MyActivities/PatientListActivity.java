package com.example.singold.MyActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.singold.R;
import com.example.singold.data.ConnectToServer;
import com.example.singold.data.PatientDetailsAdapter;
import com.example.singold.data.PrefManager;

public class PatientListActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText search1;
    private Button Search;
    private ListView patientList;
    private ImageButton addPatient;
    private Button patientX;
    private PatientDetailsAdapter detailsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);

        search1 = (EditText) findViewById(R.id.search1);
        Search = (Button) findViewById(R.id.Saerch);
        addPatient = (ImageButton) findViewById(R.id.addPatient);
//        patientX = (Button) findViewById(R.id.patientX);
//        patientX.setOnClickListener(this);
        addPatient.setOnClickListener(this);
        patientList = (ListView) findViewById(R.id.patientList);



    }


    private void initListView() {
        if(detailsAdapter==null)
            detailsAdapter=new PatientDetailsAdapter(this, R.layout.item_patient_details);
        patientList.setAdapter(detailsAdapter);
        ConnectToServer.connect(this);
        ConnectToServer.refreshItemsFromTable(detailsAdapter, PrefManager.getUserId(this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        initListView();
    }


    public void onClick(View v) {
        if (v == addPatient) {
            Intent intent = new Intent(getBaseContext(), AddPatientActivity.class);
            startActivity(intent);
        }

    }
}



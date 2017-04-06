package com.example.singold.MyActivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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


        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPatientDetails(search1.getText().toString());
            }
        });
    }


    private void initListView() {
        if(detailsAdapter==null)
            detailsAdapter=new PatientDetailsAdapter(this, R.layout.item_patient_details);
        patientList.setAdapter(detailsAdapter);
        ConnectToServer.connect(this);
        ConnectToServer.refreshPatientDetailsFromTable(detailsAdapter, PrefManager.getUserId(this), "");

    }
    private void searchPatientDetails(String toSearch) {
        if(detailsAdapter==null)
            detailsAdapter=new PatientDetailsAdapter(this, R.layout.item_patient_details);
        patientList.setAdapter(detailsAdapter);
        ConnectToServer.connect(this);
        ConnectToServer.refreshPatientDetailsFromTable(detailsAdapter, PrefManager.getUserId(this),toSearch);

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.mnItmLogOutOut)
        {
            SharedPreferences preferences=getSharedPreferences("myfile",MODE_PRIVATE);
            SharedPreferences.Editor editor=preferences.edit();
            editor.clear();
            editor.commit();
            Intent intent=new Intent(getBaseContext(),LogInActivity.class);
            startActivity(intent);
            finish();

        }
        return true;
    }



}


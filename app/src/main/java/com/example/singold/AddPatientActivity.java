package com.example.singold;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.singold.data.ConnectToServer;
import com.example.singold.data.PatientDetails;

import java.util.concurrent.ExecutionException;

public class AddPatientActivity extends AppCompatActivity {
    private TextView details;
    private EditText first, last, familyphone, address, year, personalid;
    private ImageButton plus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        details = (TextView) findViewById(R.id.details);
        first = (EditText) findViewById(R.id.first);
        last = (EditText) findViewById(R.id.last);
        familyphone = (EditText) findViewById(R.id.familyphone);
        address = (EditText) findViewById(R.id.address);
        year = (EditText) findViewById(R.id.year);
        personalid = (EditText) findViewById(R.id.personalid);
        plus = (ImageButton) findViewById(R.id.plus);
    }

    private void dataHandler() {
        String stFirst = first.getText().toString();
        String stLast = last.getText().toString();
        String stFamilyphone = familyphone.getText().toString();
        String stAddress = address.getText().toString();
        String stYear = year.getText().toString();
        String stPersonalid = personalid.getText().toString();

        boolean isok = true;
        if (stFirst.length() < 3) {
            first.setError(getResources().getString(R.string.atLeast3));
            isok = false;
        }
        if (stLast.length() < 3) {
            last.setError(getResources().getString(R.string.atLeast3));
            isok = false;
        }
        if (stFamilyphone.length() != 10) {
            familyphone.setError(getResources().getString(R.string.AtLeast10Numbers));
            isok = false;
        }
        if (stAddress.length() < 3) {
            address.setError(getResources().getString(R.string.enterYourAdress));
            isok = false;
        }
        if (stYear.length() < 1880) {
            year.setError(getResources().getString(R.string.enterYourYearOfBirth));
            isok = false;
        }
        if (stPersonalid.length() != 9) {
            personalid.setError(getResources().getString(R.string.AtLeast10Number));
            isok = false;
        }
        if (isok==true)
        {
            PatientDetails patientDetails=new PatientDetails();
            patientDetails.setId(stPersonalid);
            patientDetails.setfName(stFirst);
            patientDetails.setlName(stLast);
            patientDetails.setFamilyPhone(stFamilyphone);
            patientDetails.setAddress(stAddress);
            patientDetails.setYear(stYear);
            try {
                ConnectToServer.connet(this);
                ConnectToServer.addInTable(patientDetails);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
    public void OnClick(View v){
        if (v==plus)
            dataHandler();
    }

}

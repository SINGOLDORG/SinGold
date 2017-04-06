package com.example.singold.MyActivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.singold.R;
import com.example.singold.data.ConnectToServer;
import com.example.singold.data.PatientDetails;
import com.example.singold.data.PrefManager;

import java.util.concurrent.ExecutionException;

public class AddPatientActivity extends AppCompatActivity {
    private TextView details;
    private EditText first, last, familyphone, address, year, personalid;
    private Button plus;

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
        plus = (Button) findViewById(R.id.plus);
        plus.setOnClickListener(clickListener);
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
        if (stYear.length() !=4) {
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
            patientDetails.setIdUser(PrefManager.getUserId(this));
            patientDetails.setpId(stPersonalid);
            patientDetails.setfName(stFirst);
            patientDetails.setlName(stLast);
            patientDetails.setFamilyPhone(stFamilyphone);
            patientDetails.setAddress(stAddress);
            patientDetails.setYear(stYear);
            try {
                ConnectToServer.connect(this);
                ConnectToServer.addInTable(patientDetails,(ProgressBar)findViewById(R.id.progressBar));
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    View.OnClickListener clickListener =new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view==plus)
                dataHandler();
        }
    };
//    public void onClick(View v){
//        if (v==plus)
//            dataHandler();
//    }
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

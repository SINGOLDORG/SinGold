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
import android.widget.TextView;

import com.example.singold.R;
import com.example.singold.data.ConnectToServer;
import com.example.singold.data.MatchingSurvey;
import com.example.singold.data.PatientDetails;
import com.example.singold.data.PatientDetailsAdapter;
import com.example.singold.data.PatientProfile;
import com.example.singold.data.PrefManager;

import java.util.concurrent.ExecutionException;

public class PatientActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button playList;
    private Button newMatching,save;

    private PatientDetails patientDetails;
    private TextView tvname;
//    private TextView tvLName;
//    private TextView tvFamilyPhone;
//    private TextView tvAddress;
//    private TextView tvPId;
//    private TextView tvYear;
    private EditText firstName;
    private EditText lastName;
    private EditText address;
    private EditText year;
    private EditText familyPhone;
    private EditText pId;
    private EditText country;
    private EditText culture;
    private EditText homemusic;
    private EditText favoritesinger;
    private EditText youngmusic;
    private EditText favoritesong;
    private TextView Q1;
    private TextView Q2;
    private TextView Q3;
    private TextView Q4;
    private TextView Q5;
    private TextView Q6;
    private RadioGroup rgQ1;
    private RadioGroup rgQ2;
    private RadioGroup rgQ3;
    private RadioGroup rgQ4;
    private RadioGroup rgQ5;
    private RadioGroup rgQ6;
    private RadioButton opera;
    private RadioButton classical;
    private RadioButton armyBands;
    private RadioButton songs;
    private RadioButton folkDances;
    private RadioButton yiddish;
    private RadioButton hebrew;
    private RadioButton english;
    private RadioButton russian;
    private RadioButton arabic;
    private RadioButton waltz;
    private RadioButton tango;
    private RadioButton samba;
    private RadioButton runningwater;
    private RadioButton insteumental;
    private RadioButton jaz;
    private RadioButton rock;
    private RadioButton pollack;
    private RadioButton chazanout;
    private RadioButton fromPray;
    private RadioButton propnents;


    private PatientDetailsAdapter detailsAdapter;

    private Button Questionaire;
    private ImageButton btnMatch;
    private ImageButton btnMusic;
    private ImageButton btnQuest;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
//        tvname= (TextView) findViewById(R.id.tvname);
        firstName=(EditText) findViewById(R.id.firstName);
        lastName=(EditText)findViewById(R.id.lastName);
        address=(EditText)findViewById((R.id.address));
        year=(EditText)findViewById(R.id.year);
        familyPhone=(EditText)findViewById(R.id.familyPhone);
        pId=(EditText)findViewById(R.id.pId);
        save=(Button)findViewById(R.id.save);


         btnMusic = (ImageButton) findViewById(R.id.btnMusic);
         btnMatch = (ImageButton) findViewById(R.id.newMatching);
         btnQuest = (ImageButton) findViewById(R.id.Questionaire);
        Intent i=getIntent();
        if(i!=null)
        {
            patientDetails = (PatientDetails) i.getExtras().get("patient");
//           tvname.setText(patientDetails.getfName());
            firstName.setText(patientDetails.getfName());
            lastName.setText(patientDetails.getlName());
            pId.setText(patientDetails.getpId());
            familyPhone.setText(patientDetails.getFamilyPhone());
            address.setText(patientDetails.getAddress());
            year.setText(patientDetails.getYear());

        }

        btnMusic.setOnClickListener(this);
        btnMatch.setOnClickListener(this);
        btnQuest.setOnClickListener(this);

    }
    public void onClick(View v)
    {
        if(v==btnMusic)
        {
            Intent intent = new Intent(getBaseContext(), PlaylistActivity.class);
            intent.putExtra("patient", patientDetails);
            startActivity(intent);
        }
        if(v==btnMatch)
        {
            Intent intent = new Intent(getBaseContext(), MatchingSurveyActivity.class);
            intent.putExtra("patient", patientDetails);
            startActivity(intent);
        }
        if(v==btnQuest)
        {
            Intent intent = new Intent(getBaseContext(), QuestionaireActivity.class);
            intent.putExtra("patient", patientDetails);
            startActivity(intent);

        }
        if(v==save)
        {

//            String stFName=firstName.getText().toString();
//            String stlName=lastName.getText().toString();
//            String stAddress=address.getText().toString();
//            String stFamilyPhone=familyPhone.getText().toString();
//            String stYear=year.getText().toString();
//            String stPId=pId.getText().toString();

//            patientDetails.setfName(stFName);
//            patientDetails.setlName(stlName);
//            patientDetails.setFamilyPhone(stFamilyPhone);
//            patientDetails.setAddress(stAddress);
//            patientDetails.setpId(stPId);
//            patientDetails.setYear(stYear);

            Intent intent=new Intent(getBaseContext(),PatientListActivity.class);
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

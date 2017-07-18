package com.example.singold.MyActivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.singold.R;
import com.example.singold.data.ConnectToServer;
import com.example.singold.data.PatientDetails;
import com.example.singold.data.PatientDetailsAdapter;
import com.example.singold.data.PatientProfile;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.concurrent.ExecutionException;

public class PatientActivity extends AppCompatActivity implements View.OnClickListener {
    private Button playList;
    private Button newMatching, save;

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
    private PatientProfile patientProfile;


    private Button Questionaire;
    private ImageButton btnMatch;
    private ImageButton btnMusic;
    private ImageButton btnQuest;
    private EditText textClassic;
    private EditText textIsrael;
    private EditText language;
    private EditText textRelaxing;
    private EditText textReligion;
    private EditText textLoazi;
    private EditText textDance;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
//        tvname= (TextView) findViewById(R.id.tvname);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        address = (EditText) findViewById((R.id.address));
        year = (EditText) findViewById(R.id.year);
        familyPhone = (EditText) findViewById(R.id.familyPhone);
        pId = (EditText) findViewById(R.id.pId);
        homemusic = (EditText) findViewById(R.id.homemusic);
        youngmusic = (EditText) findViewById(R.id.youngmusic);
        favoritesinger = (EditText) findViewById(R.id.favoritesinger);
        favoritesong = (EditText) findViewById(R.id.favoritesong);
        textClassic = (EditText) findViewById(R.id.textClassic);
        textIsrael = (EditText) findViewById(R.id.textIsrael);
        language = (EditText) findViewById(R.id.language);
        textLoazi = (EditText) findViewById(R.id.textLoazi);
        textRelaxing = (EditText) findViewById(R.id.textRelaxing);
        textReligion = (EditText) findViewById(R.id.textReligion);
        textDance = (EditText) findViewById(R.id.textDance);
        country = (EditText) findViewById(R.id.country);
        culture = (EditText) findViewById(R.id.etCultutre);


        save = (Button) findViewById(R.id.save);


        btnMusic = (ImageButton) findViewById(R.id.btnMusic);
        btnMatch = (ImageButton) findViewById(R.id.newMatching);
        btnQuest = (ImageButton) findViewById(R.id.Questionaire);
        Intent i = getIntent();
        if (i != null) {
            patientDetails = (PatientDetails) i.getExtras().get("patient");
            ConnectToServer.connect(this);
            ConnectToServer.refreshPatienProfiletFromTable(patientDetails.getId(), this, (ProgressBar) findViewById(R.id.progressBar));
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

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void onClick(View v) {
        if (v == btnMusic) {
            Intent intent = new Intent(getBaseContext(), PlaylistActivity.class);
            intent.putExtra("patient", patientDetails);
            startActivity(intent);
        }
        if (v == btnMatch) {
            Intent intent = new Intent(getBaseContext(), MatchingSurveyActivity.class);
            intent.putExtra("patient", patientDetails);
            startActivity(intent);
        }
        if (v == btnQuest) {
            Intent intent = new Intent(getBaseContext(), QuestionaireActivity.class);
            intent.putExtra("patient", patientDetails);
            startActivity(intent);

        }
        if (v == save) {

            String stFName = firstName.getText().toString();
            String stlName = lastName.getText().toString();
            String stAddress = address.getText().toString();
            String stFamilyPhone = familyPhone.getText().toString();
            String stYear = year.getText().toString();
            String stPId = pId.getText().toString();

            patientDetails.setfName(stFName);
            patientDetails.setlName(stlName);
            patientDetails.setFamilyPhone(stFamilyPhone);
            patientDetails.setAddress(stAddress);
            patientDetails.setpId(stPId);
            patientDetails.setYear(stYear);

            String stHomemusic = homemusic.getText().toString();
            String stYoungmusic = youngmusic.getText().toString();
            String stfavotitesinger = favoritesinger.getText().toString();
            String stfavoritesong = favoritesong.getText().toString();
            String stclassic = textClassic.getText().toString();
            String stisrael = textIsrael.getText().toString();
            String stlanguage = language.getText().toString();
            String stDance = textDance.getText().toString();
            String stloazi = textLoazi.getText().toString();
            String stRelaxing = textRelaxing.getText().toString();
            String stReligion = textReligion.getText().toString();
            patientProfile.setHomeMusic(stHomemusic);
            patientProfile.setYoungMusic(stYoungmusic);
            patientProfile.setfSinger(stfavotitesinger);
            patientProfile.setfSongs(stfavoritesong);
            patientProfile.setClassic(stclassic);
            patientProfile.setDance(stDance);
            patientProfile.setIsrael(stisrael);
            patientProfile.setLanguage(stlanguage);
            patientProfile.setLoazi(stloazi);
            patientProfile.setRelaxing(stRelaxing);
            patientProfile.setReligion(stReligion);
            patientProfile.setCountry(country.getText().toString());
            patientProfile.setCulture(culture.getText().toString());
            ConnectToServer.connect(this);
            try {
                ConnectToServer.updatePatientDetails(patientDetails, patientProfile, (ProgressBar) findViewById(R.id.progressBar));
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            ConnectToServer.connect(this);
//            try {
//                ConnectToServer.updatePatientProfile(patientProfile,(ProgressBar)findViewById(R.id.progressBar));
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Intent intent=new Intent(getBaseContext(),PatientListActivity.class);
//            startActivity(intent);
            //finish();
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
        if (item.getItemId() == R.id.mnItmLogOutOut) {
            SharedPreferences preferences = getSharedPreferences("myfile", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(getBaseContext(), LogInActivity.class);
            startActivity(intent);
            finish();


        }
        if (item.getItemId() == R.id.mnItmIcons) {
            Intent intent = new Intent(getBaseContext(), icons_explainActivity.class);
            getBaseContext().startActivity(intent);
        }
        return true;
    }

    public void updatePatientProfile(PatientProfile patientProfile) {
        if (patientProfile == null) {
            this.patientProfile = new PatientProfile();
            this.patientProfile.setIdPatient(patientDetails.getId());
            Toast.makeText(this, "Empty Patient Profile", Toast.LENGTH_LONG).show();
        } else {
            this.patientProfile = patientProfile;
            homemusic.setText(patientProfile.getHomeMusic());
            youngmusic.setText(patientProfile.getYoungMusic());
            favoritesinger.setText(patientProfile.getfSinger());
            favoritesong.setText(patientProfile.getfSongs());
            textClassic.setText(patientProfile.getClassic());
            textIsrael.setText(patientProfile.getIsrael());
            language.setText(patientProfile.getLanguage());
            textLoazi.setText(patientProfile.getLoazi());
            textRelaxing.setText(patientProfile.getRelaxing());
            textReligion.setText(patientProfile.getReligion());
            textDance.setText(patientProfile.getDance());
            culture.setText(patientProfile.getCulture());
            country.setText(patientProfile.getCountry());
        }
    }

//    /**
//     * ATTENTION: This was auto-generated to implement the App Indexing API.
//     * See https://g.co/AppIndexing/AndroidStudio for more information.
//     */
//    public Action getIndexApiAction() {
//        Thing object = new Thing.Builder()
//                .setName("Patient Page") // TODO: Define a title for the content shown.
//                // TODO: Make sure this auto-generated URL is correct.
//                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
//                .build();
//        return new Action.Builder(Action.TYPE_VIEW)
//                .setObject(object)
//                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
//                .build();
//    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        AppIndex.AppIndexApi.end(client, getIndexApiAction());
//        client.disconnect();
    }
}

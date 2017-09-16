package com.example.singold.MyActivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.singold.R;
import com.example.singold.data.ConnectToServer;
import com.example.singold.data.PatientDetails;
import com.example.singold.data.PatientProfile;
import com.example.singold.data.SongAdapter;

public class FoundSongActivity extends AppCompatActivity implements View.OnClickListener {

    private Button save;
    private ListView foundList;

    private SongAdapter FoundSongAdapter;
    private PatientDetails patientDetails;
    private PatientProfile patientProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_song_list);
        Intent i=getIntent();
        if(i!=null)
        {
            patientDetails= (PatientDetails) i.getExtras().get("patient");
            patientProfile=(PatientProfile)i.getExtras().get("patientProfile");
        }
        foundList = (ListView) findViewById(R.id.foundList);
        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);
    }

    private void initListView() {
        if (FoundSongAdapter == null)
            FoundSongAdapter = new SongAdapter(this, R.layout.item_song,true,false);
        foundList.setAdapter(FoundSongAdapter);
       ConnectToServer.connect(this);
        ConnectToServer.matchingFromTable(FoundSongAdapter,patientProfile,(ProgressBar)findViewById(R.id.progressBar));

    }

    @Override
    protected void onStart() {
        super.onStart();
        initListView();
    }


    @Override
    public void onClick(View view) {
        if(view==save)
        {

            ConnectToServer.connect(this);
            ConnectToServer.addListOfSongsToPatient(FoundSongAdapter.getSelectedSongs(),patientDetails,(ProgressBar)findViewById(R.id.progressBar));
//

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
        if (item.getItemId()==R.id.mnItmIcons){
            Intent intent = new Intent(getBaseContext(), icons_explainActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }
}
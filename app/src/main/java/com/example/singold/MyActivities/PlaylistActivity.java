package com.example.singold.MyActivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.singold.R;
import com.example.singold.data.ConnectToServer;
import com.example.singold.data.PatientDetails;
import com.example.singold.data.SongAdapter;

public class PlaylistActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView list;
    private ImageButton add,BTNmatching;
    private TextView textView;

    private SongAdapter songAdapter;
    private PatientDetails patientDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        textView=(TextView) findViewById(R.id.textView);
        list = (ListView) findViewById(R.id.list);
        add = (ImageButton) findViewById(R.id.add);
        add.setOnClickListener(this);
        BTNmatching=(ImageButton) findViewById(R.id.BTNmatching);
        Intent i=getIntent();
        if(i!=null)
        {
            patientDetails= (PatientDetails) i.getExtras().get("patient");
            textView.setText(patientDetails.getfName()+" "+patientDetails.getlName());
        }
    }
    private void initListView() {
        if(songAdapter==null)
            songAdapter=new SongAdapter(this, R.layout.item_song,false);
        list.setAdapter(songAdapter);
        ConnectToServer.connect(this);
        ConnectToServer.refreshSongsByPatient(songAdapter,patientDetails.getId(),(ProgressBar)findViewById(R.id.progressBar));

    }

    @Override
    protected void onStart() {
        super.onStart();
        initListView();
    }


    public void onClick(View v) {
        if (v == add)
        {
            Intent intent = new Intent(getBaseContext(), AddSongActivity.class);
            intent.putExtra("patientID",patientDetails.getId());
            startActivity(intent);

        }
        if(v==BTNmatching){
                Intent intent = new Intent(getBaseContext(), MatchingSurveyActivity.class);
                intent.putExtra("patient", patientDetails);
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
        if (item.getItemId()==R.id.mnItmIcons){
            Intent intent = new Intent(getBaseContext(), icons_explainActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }
}

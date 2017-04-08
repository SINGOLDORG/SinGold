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
import android.widget.ProgressBar;

import com.example.singold.R;
import com.example.singold.data.ConnectToServer;
import com.example.singold.data.Song;


import java.util.concurrent.ExecutionException;

public class AddSongActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText theSongName, singer, link;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        theSongName = (EditText) findViewById(R.id.theSongName);
        singer = (EditText) findViewById(R.id.singer);
        link = (EditText) findViewById(R.id.link);
       // idPatient =(EditText) findViewById(R.id.idPatient);

        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);
    }

    private void dataHandler() {
       // String stIdPatient=idPatient.getText().toString();
        String stTheSongName = theSongName.getText().toString();
        String stSinger = singer.getText().toString();
        String stLink = link.getText().toString();
        String patientID="";
        Intent i=getIntent();
        if(i!=null)
        {
            patientID=i.getExtras().getString("patientID");
        }


        boolean isok = true;
//        if (stIdPatient.length() == 0) {
//            idPatient.setError("Enter the patient id");
//            isok = false;
//        }
        if (stTheSongName.length() < 3) {
            theSongName.setError("Enter the song name");
            isok = false;
        }
        if (stSinger.length() < 3) {
            singer.setError("Enter the singer name");
            isok = false;
        }
        if (stLink.length() == 0) {
            link.setError("Enter the link");
            isok = false;
        }


        if (isok == true) {
            Song song = new Song();
         //   song.setIdPatient(stIdPatient);
            song.setIdPatient(patientID);
            song.setName(stTheSongName);
            song.setSinger(stSinger);
            song.setLink(stLink);
            try {
                ConnectToServer.connect(this);
                ConnectToServer.addSongForPatient(song,(ProgressBar)findViewById(R.id.progressBar));
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        }
        }
    public void onClick(View v){
        if (v==save)
            dataHandler();
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






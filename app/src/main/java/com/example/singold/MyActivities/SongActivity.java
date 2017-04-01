package com.example.singold.MyActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.singold.R;
import com.example.singold.data.ConnectToServer;
import com.example.singold.data.Song;


import java.util.concurrent.ExecutionException;

public class SongActivity extends AppCompatActivity {
    private EditText theSongName, singer, link, idPatient;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        theSongName = (EditText) findViewById(R.id.theSongName);
        singer = (EditText) findViewById(R.id.singer);
        link = (EditText) findViewById(R.id.link);
       idPatient =(EditText) findViewById(R.id.idPatient);

        save = (Button) findViewById(R.id.save);
    }

    private void dataHandler() {
        String stIdPatient = idPatient.getText().toString();
        String stTheSongName = theSongName.getText().toString();
        String stSinger = singer.getText().toString();
        String stLink = link.getText().toString();


        boolean isok = true;
        if (stIdPatient.length() == 0) {
            idPatient.setError("Enter the patient id");
            isok = false;
        }
        if (stTheSongName.length() == 0) {
            theSongName.setError("Enter the song name");
            isok = false;
        }
        if (stSinger.length() == 0) {
            singer.setError("Enter the singer name");
            isok = false;
        }
        if (stLink.length() == 0) {
            link.setError("Enter the link");
            isok = false;
        }


        if (isok == true) {
            Song s = new Song();
            s.setIdPatient(stIdPatient);
            s.setName(stTheSongName);
            s.setSinger(stSinger);
            s.setLink(stLink);
            try {
                ConnectToServer.connet(this);
                ConnectToServer.addInTable(s);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void OnClick(View v){
        if (v==save)
            dataHandler();
    }
    }






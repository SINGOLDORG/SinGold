package com.example.singold.MyActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.singold.R;
import com.example.singold.data.ConnectToServer;
import com.example.singold.data.PatientDetailsAdapter;
import com.example.singold.data.SongAdapter;

public class PlaylistActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView list;
    private Button add,BTNmatching;

    private SongAdapter songAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        list = (ListView) findViewById(R.id.list);
        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(this);
        BTNmatching=(Button)findViewById(R.id.BTNmatching);
    }
    private void initListView() {
        if(songAdapter==null)
            songAdapter=new SongAdapter(this, R.layout.item_song);
        list.setAdapter(songAdapter);
        ConnectToServer.connect(this);
        ConnectToServer.refreshItemsFromTable(songAdapter);

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
            startActivity(intent);

        }
        if(v==BTNmatching){
            Intent intent=new Intent(getBaseContext(),MatchingSurveyActivity.class);
            startActivity(intent);
        }
    }
}

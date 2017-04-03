package com.example.singold.MyActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.singold.R;
import com.example.singold.data.ConnectToServer;
import com.example.singold.data.FoundSongAdapter;
import com.example.singold.data.SongAdapter;

public class FoundSongActivity extends AppCompatActivity implements View.OnClickListener {

    private Button save;
    private ListView foundList;

    private SongAdapter FoundSongAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_song_list);

        foundList = (ListView) findViewById(R.id.foundList);
        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);
    }

    private void initListView() {
        if (FoundSongAdapter == null)
            FoundSongAdapter = new SongAdapter(this, R.layout.item_found_song);
        foundList.setAdapter(FoundSongAdapter);
//        ConnectToServer.connect(this);
//        ConnectToServer.refreshItemsFromTable(FoundSongAdapter);

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
            Intent intent = new Intent(getBaseContext(), PlaylistActivity.class);
            startActivity(intent);

        }
    }
}
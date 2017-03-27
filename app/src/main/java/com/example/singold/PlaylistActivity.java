package com.example.singold;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class PlaylistActivity extends AppCompatActivity {
    private ListView ListView;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        ListView = (ListView) findViewById(R.id.ListView);
        add = (Button) findViewById(R.id.add);
    }

    public void onClick(View v) {
        if (v == add) {
            Intent intent = new Intent(getBaseContext(), SongActivity.class);
            startActivity(intent);

        }
    }
}

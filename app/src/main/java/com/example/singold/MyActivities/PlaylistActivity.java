package com.example.singold.MyActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.singold.R;

public class PlaylistActivity extends AppCompatActivity {
    private ListView list;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        list = (ListView) findViewById(R.id.list);
        add = (Button) findViewById(R.id.add);
    }

    public void onClick(View v) {
        if (v == add)
        {
            Intent intent = new Intent(getBaseContext(), PatientDetailsActivity.class);
            startActivity(intent);

        }
    }
}

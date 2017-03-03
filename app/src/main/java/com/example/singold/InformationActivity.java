package com.example.singold;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InformationActivity extends AppCompatActivity {
    private EditText first, last, username, id, confirmid;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        first = (EditText) findViewById(R.id.first);
        last = (EditText) findViewById(R.id.last);
        username = (EditText) findViewById(R.id.username);
        id = (EditText) findViewById(R.id.id);
        confirmid = (EditText) findViewById(R.id.confirmid);
        save = (Button) findViewById(R.id.save);
    }


    private void dataHandler() {
        String stFirst = first.getText().toString();
        String stLast = last.getText().toString();
        String stUsername = username.getText().toString();
        String stId = id.getText().toString();
        String stConfirmid = confirmid.getText().toString();

        boolean isok = true;
        if (stFirst.length() == 0) {
            username.setError("Wrong first name");
            isok = false;
        }
        if (stLast.length() == 0) {
            username.setError("Wrong last name");
            isok = false;
        }
        if (stUsername.length() == 0) {
            username.setError("Wrong username");
            isok = false;
        }
        if (stId.length() == 0) {
            id.setError("Wrong id");
            isok = false;
        }
        if (stConfirmid.length() == 0) {
            id.setError("Wrong confirmate");
            isok = false;
        }

    }

    public void onClick(View v) {
        if (v == save) {
            Intent intent = new Intent(getBaseContext(), ListActivity.class);
            startActivity(intent);
        }

    }
}

package com.example.singold;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InformationActivity extends AppCompatActivity
{
    private EditText first,last,username,id,confirmid;
    private Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        first=(EditText)findViewById(R.id.first);
        last=(EditText)findViewById(R.id.last);
        username=(EditText)findViewById(R.id.username);
        save=(Button)findViewById(R.id.save);
        id=(EditText)findViewById(R.id.id);
        confirmid=(EditText)findViewById(R.id.confirmid);

    }
    public void onClick(View v)
    {
        if(v==save)
        {
            Intent intent=new Intent(getBaseContext(),ListActivity.class);
            startActivity(intent);
        }
    }
}

package com.example.singold;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class InformationActivity extends AppCompatActivity
{
    private EditText first,last,username;
    private Button next,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        first=(EditText)findViewById(R.id.first);
        last=(EditText)findViewById(R.id.last);
        username=(EditText)findViewById(R.id.username);
        next=(Button)findViewById(R.id.next);
        back=(Button)findViewById(R.id.back);
    }
}

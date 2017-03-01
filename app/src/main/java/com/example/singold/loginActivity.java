package com.example.singold;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class loginActivity extends AppCompatActivity
{
    private EditText username,id;
    private Button login,back4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=(EditText) findViewById(R.id.username);
        id=(EditText) findViewById(R.id.id);
        login=(Button) findViewById(R.id.login);
        back4=(Button) findViewById(R.id.back4);
    }
}

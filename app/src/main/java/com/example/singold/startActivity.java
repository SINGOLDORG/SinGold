package com.example.singold;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class startActivity extends AppCompatActivity
{
    private Button back3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        back3=(Button)findViewById(R.id.back3);
    }
}

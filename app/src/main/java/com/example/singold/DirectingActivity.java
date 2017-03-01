package com.example.singold;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DirectingActivity extends AppCompatActivity
{
    private EditText text;
    private TextView directing;
    private Button button7;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directing);
        text=(EditText)findViewById(R.id.text);
        directing=(TextView)findViewById(R.id.directing);
        button7=(Button)findViewById(R.id.button7);
    }
}

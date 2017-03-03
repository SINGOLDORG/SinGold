package com.example.singold;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DirectingActivity extends AppCompatActivity
{
    private TextView text,directing;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directing);
        text = (TextView) findViewById(R.id.text);
        directing = (TextView) findViewById(R.id.directing);
        next=(Button)findViewById(R.id.Next);
    }
    public void onClick(View v)
    {
        if(v==next)
        {
            Intent intent=new Intent(getBaseContext(),EnterActivity.class);
            startActivity(intent);
        }

    }


}

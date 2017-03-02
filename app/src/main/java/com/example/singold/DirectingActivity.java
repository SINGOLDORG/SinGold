package com.example.singold;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DirectingActivity extends AppCompatActivity
{
    private TextView text;
    private TextView directing;
    private Button back2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directing);
        text=(TextView)findViewById(R.id.text);
        directing=(TextView)findViewById(R.id.directing);
        back2=(Button)findViewById(R.id.back2);
    }
    public void onClick (View v)
    {
      if (v==back2)
      {
          Intent intent=new Intent(getBaseContext(),EnterActivity.class);
          startActivity(intent);

      }
    }
}

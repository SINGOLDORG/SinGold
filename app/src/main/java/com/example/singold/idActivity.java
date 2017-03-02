package com.example.singold;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.singold.MyTabs.ListActivity;

public class idActivity extends AppCompatActivity
{
    private EditText enterid,confirmate;
    private Button back2,finish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id);
        enterid=(EditText) findViewById(R.id.enterid);
        confirmate=(EditText) findViewById(R.id.confirmate);
        back2= (Button)findViewById(R.id.back2);
        finish=(Button) findViewById(R.id.finish);

    }
    public void onClick(View v)
    {
        if (v==finish)
        {
            Intent intent=new Intent(getBaseContext(),ListActivity.class);
            startActivity(intent);
        }
        if (v==back2)
        {
            Intent intent=new Intent(getBaseContext(),EnterActivity.class);
            startActivity(intent);
        }

    }
}

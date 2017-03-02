package com.example.singold;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EnterActivity extends AppCompatActivity
{
private Button help,login,sign;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        help=(Button)findViewById(R.id.text);
        login=(Button)findViewById(R.id.login);
        sign=(Button)findViewById(R.id.sign);
    }
    public void onClick(View v)
    {
        if(v==login)
        {
            Intent intent=new Intent(getBaseContext(),loginActivity.class);
            startActivity(intent);
        }
        if(v==help)
        {
            Intent intent=new Intent(getBaseContext(),DirectingActivity.class);
            startActivity(intent);
        }
        if (v==sign)
        {
            Intent intent=new Intent(getBaseContext(),InformationActivity.class);
            startActivity(intent);
        }
    }


}

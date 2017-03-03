package com.example.singold;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterActivity extends AppCompatActivity
{
    private Button login,sign;
    private EditText username,id;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        login=(Button)findViewById(R.id.login);
        sign=(Button)findViewById(R.id.sign);
        username=(EditText)findViewById(R.id.username);
        id=(EditText)findViewById(R.id.id1);
    }
    private void dataHandler()
    {
        String stUsername = username.getText().toString();
        String stId = id.getText().toString();

        boolean isok=true;
        if(stUsername.length()==0)
        {
            username.setError("Wrong username");
            isok=false;
        }
        if(stId.length()==0)
        {
            id.setError("Wrong id");
            isok=false;
        }
    }
    public void onClick(View v)
    {
        if(v==login)
        {
            Intent intent=new Intent(getBaseContext(),ListActivity.class);
            startActivity(intent);
        }

        if (v==sign)
        {
            Intent intent=new Intent(getBaseContext(),InformationActivity.class);
            startActivity(intent);
        }
    }



}

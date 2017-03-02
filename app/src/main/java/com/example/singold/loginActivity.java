package com.example.singold;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.singold.MyTabs.ListActivity;

public class loginActivity extends AppCompatActivity
{
    private EditText Username,password;
    private Button Login,back4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        Login=(Button)findViewById(R.id.login);
        back4=(Button)findViewById(R.id.back4);
    }
    public void onClick(View v)
    {
        if (v==Login)
        {
            Intent intent=new Intent(getBaseContext(),ListActivity.class);
            startActivity(intent);
        }
        if (v==back4)
        {
            Intent intent=new Intent(getBaseContext(),EnterActivity.class);
            startActivity(intent);

        }

    }
}

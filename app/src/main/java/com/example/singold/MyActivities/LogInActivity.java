package com.example.singold.MyActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.singold.R;

public class LogInActivity extends AppCompatActivity
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
    private void dataHandler() {
        String stUsername = username.getText().toString();
        String stId = id.getText().toString();

        boolean isok = true;

        if (stUsername.length() < 3) {
            username.setError("Wrong username");
            isok = false;

        }
        if (stId.length() <= 3) {
            id.setError("Wrong id");
            isok = false;
        }
        if (isok == true) {

        }
    }
    public void onClick(View v)
    {
        if(v==login)
        {
            Intent intent=new Intent(getBaseContext(),PatientListActivity.class);
            startActivity(intent);

        }

        if (v==sign)
        {
            Intent intent=new Intent(getBaseContext(),SignUpActivity.class);
            startActivity(intent);
        }

    }



}

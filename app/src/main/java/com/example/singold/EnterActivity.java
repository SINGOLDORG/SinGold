package com.example.singold;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EnterActivity extends AppCompatActivity
{
private Button help,login,sign;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        help=(Button)findViewById(R.id.help);
        login=(Button)findViewById(R.id.login);
        sign=(Button)findViewById(R.id.sign);
    }
    public void onClick(View v)
    {
        String stH=help.getText().toString();

    }
}

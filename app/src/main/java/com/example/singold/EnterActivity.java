package com.example.singold;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.singold.data.ConnectToServer;
import com.example.singold.data.Login;
import com.example.singold.data.User;

import java.util.concurrent.ExecutionException;

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
        if (isok==true)
        {
            Login login=new Login();
            login.setUsername(stUsername);
            login.setId(stId);
            try {
                ConnectToServer.addInTable(login);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
}
    public void onClick(View v)
    {
        if(v==login)
        {
            Intent intent=new Intent(getBaseContext(),ListMainActivity.class);
            startActivity(intent);
        }

        if (v==sign)
        {
            Intent intent=new Intent(getBaseContext(),InformationActivity.class);
            startActivity(intent);
        }

    }



}

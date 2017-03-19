package com.example.singold;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.singold.data.ConnectToServer;
import com.example.singold.data.MyUser;
import com.example.singold.data.ToDoItem;

import java.util.concurrent.ExecutionException;

public class SignUpActivity extends AppCompatActivity {
    private EditText first, last, username, id, confirmid;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        first = (EditText) findViewById(R.id.first);
        last = (EditText) findViewById(R.id.last);
        username = (EditText) findViewById(R.id.username);
        id = (EditText) findViewById(R.id.id);
        confirmid = (EditText) findViewById(R.id.confirmid);
        save = (Button) findViewById(R.id.save);
    }


    private void dataHandler() {
        String stFirst = first.getText().toString();
        String stLast = last.getText().toString();
        String stUsername = username.getText().toString();
        String stId = id.getText().toString();
        String stConfirmid = confirmid.getText().toString();

        boolean isok = true;
        if (stFirst.length() < 3)
        {
            first.setError("At Least 3 chars");
            isok = false;
        }
        if (stLast.length() == 0)
        {
            last.setError("Wrong last name");
            isok = false;
        }
        if (stUsername.length() == 0)
        {
            username.setError("Wrong username");
            isok = false;
        }
        if (stId.length() == 0)
        {
            id.setError("Wrong id");
            isok = false;
        }
        if (stConfirmid.length() == 0 )
        {
            confirmid.setError("Wrong confirmate");
            isok = false;
        }
        if (!stConfirmid.equals(stId) )
        {
            confirmid.setError("Must Be the Same id");
            isok = false;
        }

        if(isok==true)
        {
//            ToDoItem toDoItem=new ToDoItem();
//            toDoItem.setText(stFirst);
//            ConnectToServer.connet(this);
//            try {
//                ConnectToServer.addInTable(toDoItem);
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            MyUser u=new MyUser();
            u.setfName(stFirst);
            u.setlName(stLast);
            u.setUsername(stUsername);
            u.setEnterId(stId);
            try {
                ConnectToServer.connet(this);
                ConnectToServer.addInTable(u);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public void onClick(View v) {
        if (v == save) {
            dataHandler();
//            Intent intent = new Intent(getBaseContext(), ListMainActivity.class);
//            startActivity(intent);
        }

    }
}

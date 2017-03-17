package com.example.singold.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.singold.R;

/**
 * Created by user on 16/03/2017.
 */

public class LoginAdapter extends ArrayAdapter<Login>
{
    public LoginAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final Login Login = getItem(position);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_login, parent, false);
        }

        TextView textId = (TextView) convertView.findViewById(R.id.textId);
        TextView textUserName = (TextView) convertView.findViewById(R.id.textUserName);

        textId.setText(Login.getId());
        textUserName.setText(Login.getUsername());

        return convertView;
    }
}

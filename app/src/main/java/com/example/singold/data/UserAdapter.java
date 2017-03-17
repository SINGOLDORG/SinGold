package com.example.singold.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.singold.R;
import com.google.gson.internal.bind.ArrayTypeAdapter;

import java.util.ArrayList;

/**
 * Created by user on 14/03/2017.
 */

public class UserAdapter extends ArrayAdapter<User> {

    public UserAdapter(Context context, int resource) {
        super(context, resource);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final User User = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }

        TextView textId = (TextView) convertView.findViewById(R.id.textId);
        TextView textIdInstitute = (TextView) convertView.findViewById(R.id.textidInstitute);
        TextView textFN = (TextView) convertView.findViewById(R.id.textFN);
        TextView textLN = (TextView) convertView.findViewById(R.id.textLN);
        TextView textUsername = (TextView) convertView.findViewById(R.id.textUsername);
        TextView textEnterId = (TextView) convertView.findViewById(R.id.textEnterId);
        TextView textConfirmId = (TextView) convertView.findViewById(R.id.textConfirmId);

        textId.setText(User.getId());
        textIdInstitute.setText(User.getIdInstitute());
        textFN.setText(User.getfName());
        textLN.setText(User.getlName());
        textUsername.setText(User.getUsername());
        textEnterId.setText(User.getEnterId());
        textConfirmId.setText(User.getConfId());

        return convertView;
    }
}


















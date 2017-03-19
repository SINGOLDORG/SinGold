package com.example.singold.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.singold.R;

/**
 * Created by user on 14/03/2017.
 */

public class UserAdapter extends ArrayAdapter<MyUser> {

    public UserAdapter(Context context, int resource) {
        super(context, resource);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MyUser user = getItem(position);

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

        textId.setText(user.getId());
        textIdInstitute.setText(user.getIdInstitute());
        textFN.setText(user.getfName());
        textLN.setText(user.getlName());
        textUsername.setText(user.getUsername());
        textEnterId.setText(user.getEnterId());

        return convertView;
    }
}


















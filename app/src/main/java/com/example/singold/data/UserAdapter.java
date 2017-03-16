package com.example.singold.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

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

        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.Useritem,parent,false);
        }







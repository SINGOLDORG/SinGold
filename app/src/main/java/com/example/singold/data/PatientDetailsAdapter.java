package com.example.singold.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.singold.R;

/**
 * Created by user on 16/03/2017.
 */

public class PatientDetailsAdapter extends ArrayAdapter<PatientDetails>
{
    public PatientDetailsAdapter (Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final PatientDetails PatientDetails = getItem(position);

        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.PatientDetailsitem,parent,false);
        }
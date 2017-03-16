package com.example.singold.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by user on 16/03/2017.
 */

public class PatientSurveyAdapter extends ArrayAdapter<PatientSurvey>
{
    public PatientSurveyAdapter (Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final PatientSurvey PatientSurvey = getItem(position);

        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.PatientSurveyitem,parent,false);
        }
    }
}

package com.example.singold.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.singold.R;

/**
 * Created by user on 16/03/2017.
 */

public class PatientDetailsAdapter extends ArrayAdapter<PatientDetails> {
    public PatientDetailsAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final PatientDetails PatientDetails = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_patient_details, parent, false);
        }

        TextView textFamilyPhone = (TextView) convertView.findViewById(R.id.textFamilyPhone);
        TextView textAddress = (TextView) convertView.findViewById(R.id.textAddress);
        TextView textPId = (TextView) convertView.findViewById(R.id.textPid);
        TextView textYear = (TextView) convertView.findViewById(R.id.textYear);
        ImageButton btnInfo=(ImageButton) convertView.findViewById(R.id.btnInfo);
        ImageButton btnMusic=(ImageButton) convertView.findViewById(R.id.btnMusic);

        textFamilyPhone.setText(PatientDetails.getFamilyPhone());
        textAddress.setText(PatientDetails.getAddress());
        textPId.setText(PatientDetails.getpId());
        textYear.setText(PatientDetails.getYear());

        return convertView;
    }

}



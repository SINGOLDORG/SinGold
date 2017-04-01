package com.example.singold.data;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.singold.MyActivities.PatientActivity;
import com.example.singold.MyActivities.PatientDetailsActivity;
import com.example.singold.MyActivities.PlaylistActivity;
import com.example.singold.R;

/**
 * Created by user on 16/03/2017.
 */

public class PatientDetailsAdapter extends ArrayAdapter<PatientDetails> {
    public PatientDetailsAdapter(Context context, int resource) {
        super(context, resource);
    }

    private ImageButton btnMusic, btnInfo;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final PatientDetails patientDetails = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_patient_details, parent, false);
        }

        TextView textFamilyPhone = (TextView) convertView.findViewById(R.id.textFamilyPhone);
        TextView textAddress = (TextView) convertView.findViewById(R.id.textAddress);
        TextView textPId = (TextView) convertView.findViewById(R.id.textPid);
        TextView textYear = (TextView) convertView.findViewById(R.id.textYear);
        ImageButton btnInfo = (ImageButton) convertView.findViewById(R.id.btnInfo);
        ImageButton btnMusic = (ImageButton) convertView.findViewById(R.id.btnMusic);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PatientActivity.class);
               intent.putExtra("patient",patientDetails);
                getContext().startActivity(intent);
            }
        });
        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),PlaylistActivity.class);
                intent.putExtra("patient",patientDetails);
                getContext().startActivity(intent);
            }
        });

        textFamilyPhone.setText(patientDetails.getFamilyPhone());
        textAddress.setText(patientDetails.getAddress());
        textPId.setText(patientDetails.getpId());
        textYear.setText(patientDetails.getYear());

        return convertView;
    }


//        View.OnClickListener clickListener =new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//        if (v == btnMusic) {
//            Intent intent = new Intent(getBaseContext(), PatientActivity.class);
//            startActivity(intent);
//        }
//        if (v == btnInfo) {
//            Intent intent = new Intent(getBaseContext(), PatientActivity.class);
//            startActivity(intent);
//        }
//    }
}


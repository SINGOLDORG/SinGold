package com.example.singold.data;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.singold.MyActivities.MatchingSurveyActivity;
import com.example.singold.MyActivities.PatientActivity;
import com.example.singold.MyActivities.PlaylistActivity;
import com.example.singold.MyActivities.QuestionaireActivity;
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
        TextView textLName = (TextView) convertView.findViewById(R.id.textLName);
        TextView textFName = (TextView) convertView.findViewById(R.id.textFName);
        TextView textFamilyPhone = (TextView) convertView.findViewById(R.id.textFamilyPhone);
//        TextView textAddress = (TextView) convertView.findViewById(R.id.textAddress);
        TextView textPId = (TextView) convertView.findViewById(R.id.textPid);
//        TextView textYear = (TextView) convertView.findViewById(R.id.textYear);

        final ImageButton btnInfo = (ImageButton) convertView.findViewById(R.id.btnInfo);
        final ImageButton btnMusic = (ImageButton) convertView.findViewById(R.id.btnMusic);
        final ImageButton btnMatch = (ImageButton) convertView.findViewById(R.id.newMatching);
        final ImageButton btnQuest = (ImageButton) convertView.findViewById(R.id.Questionaire);
        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==btnInfo)
                {
                    Intent intent = new Intent(getContext(), PatientActivity.class);
                    intent.putExtra("patient", patientDetails);
                    getContext().startActivity(intent);
                }
                if(v==btnMusic)
                {
                    Intent intent = new Intent(getContext(), PlaylistActivity.class);
                    intent.putExtra("patient", patientDetails);
                    getContext().startActivity(intent);
                }
                if(v==btnMatch)
                {
                    Intent intent = new Intent(getContext(), MatchingSurveyActivity.class);
                    intent.putExtra("patient", patientDetails);
                    getContext().startActivity(intent);
                }
                if(v==btnQuest)
                {
                    Intent intent = new Intent(getContext(), QuestionaireActivity.class);
                    intent.putExtra("patient", patientDetails);
                    getContext().startActivity(intent);

                }
            }
        };
        btnInfo.setOnClickListener(onClickListener);
        btnMusic.setOnClickListener(onClickListener);
        btnMatch.setOnClickListener(onClickListener);
        btnQuest.setOnClickListener(onClickListener);


        textFamilyPhone.setText(getContext().getString(R.string.familyphone)+"  "+patientDetails.getFamilyPhone());
//        textAddress.setText("Address:"+patientDetails.getAddress());
        textPId.setText(getContext().getString(R.string.pId)+"  "+patientDetails.getpId());
        textFName.setText(getContext().getString(R.string.firstName)+"  "+patientDetails.getfName());
        textLName.setText(getContext().getString(R.string.lastName)+"  "+patientDetails.getlName());
//        textYear.setText("Year Of Birth:"+patientDetails.getYear());

        return convertView;
    }
}
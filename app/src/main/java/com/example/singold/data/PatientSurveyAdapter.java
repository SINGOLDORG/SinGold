package com.example.singold.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.singold.R;

/**
 * Created by user on 16/03/2017.
 */

public class  PatientSurveyAdapter extends ArrayAdapter<PatientProfile> {
    public PatientSurveyAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final PatientProfile PatientProfile = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_patient_survey, parent, false);
        }

        TextView textId = (TextView) convertView.findViewById(R.id.textId);
        TextView textIdPatient = (TextView) convertView.findViewById(R.id.textIdPatient);
        TextView textCountry = (TextView) convertView.findViewById(R.id.textCountry);
        TextView textYear = (TextView) convertView.findViewById(R.id.textYear);
        TextView textHomeMusic = (TextView) convertView.findViewById(R.id.textHomeMusic);
        TextView textEducation = (TextView) convertView.findViewById(R.id.textEducation);
        TextView textFsinger = (TextView) convertView.findViewById(R.id.textFsinger);
        TextView textFsongs = (TextView) convertView.findViewById(R.id.textFSongs);
        TextView textClassic = (TextView) convertView.findViewById(R.id.textClassic);
        TextView textIsrael = (TextView) convertView.findViewById(R.id.textIsrael);
        TextView textLanguage = (TextView) convertView.findViewById(R.id.textLanguage);
        TextView textDance = (TextView) convertView.findViewById(R.id.textDance);
        TextView textLoazi = (TextView) convertView.findViewById(R.id.textLoazi);
        TextView textReligion = (TextView) convertView.findViewById(R.id.textReligion);
        TextView textRelaxing = (TextView) convertView.findViewById(R.id.textRelaxing);

        textId.setText(PatientProfile.getId());
        textIdPatient.setText(PatientProfile.getIdPatient());
        textCountry.setText(PatientProfile.getCountry());
        textYear.setText(PatientProfile.getYear());
        textHomeMusic.setText(PatientProfile.getHomeMusic());
        textEducation.setText(PatientProfile.getCulture());
        textFsinger.setText(PatientProfile.getfSinger());
        textFsongs.setText(PatientProfile.getfSongs());
        textClassic.setText(PatientProfile.getClassic());
        textIsrael.setText(PatientProfile.getIsrael());
        textLanguage.setText(PatientProfile.getLanguage());
        textDance.setText(PatientProfile.getDance());
        textLoazi.setText(PatientProfile.getLoazi());
        textReligion.setText(PatientProfile.getReligion());
        textRelaxing.setText(PatientProfile.getRelaxing());

        return convertView;
    }
}
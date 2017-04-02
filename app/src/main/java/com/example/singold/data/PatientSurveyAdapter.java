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

public class  PatientSurveyAdapter extends ArrayAdapter<PatientSurvey> {
    public PatientSurveyAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final PatientSurvey PatientSurvey = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_patient_survey, parent, false);
        }

        TextView textId = (TextView) convertView.findViewById(R.id.textId);
        TextView textIdPatient = (TextView) convertView.findViewById(R.id.textIdPatient);
        TextView textCountry = (TextView) convertView.findViewById(R.id.textCountry);
        TextView textCity = (TextView) convertView.findViewById(R.id.textCity);
        TextView textYear = (TextView) convertView.findViewById(R.id.textYear);
        TextView textHomeMusic = (TextView) convertView.findViewById(R.id.textHomeMusic);
        TextView textEducation = (TextView) convertView.findViewById(R.id.textEducation);
        TextView textIsMahazot = (TextView) convertView.findViewById(R.id.textIsMahazot);
        TextView textMahazot = (TextView) convertView.findViewById(R.id.textMahazot);
        TextView textFsinger = (TextView) convertView.findViewById(R.id.textFsinger);
        TextView textFsongs = (TextView) convertView.findViewById(R.id.textFSongs);
        TextView textWedding = (TextView) convertView.findViewById(R.id.textWedding);
        TextView textClassic = (TextView) convertView.findViewById(R.id.textClassic);
        TextView textIsrael = (TextView) convertView.findViewById(R.id.textIsrael);
        TextView textLanguage = (TextView) convertView.findViewById(R.id.textLanguage);
        TextView textDance = (TextView) convertView.findViewById(R.id.textDance);
        TextView textLoazi = (TextView) convertView.findViewById(R.id.textLoazi);
        TextView textReligion = (TextView) convertView.findViewById(R.id.textReligion);
        TextView textRelaxing = (TextView) convertView.findViewById(R.id.textRelaxing);

        textId.setText(PatientSurvey.getId());
        textIdPatient.setText(PatientSurvey.getIdPatient());
        textCountry.setText(PatientSurvey.getCountry());
        textYear.setText(PatientSurvey.getYear());
        textHomeMusic.setText(PatientSurvey.getHomeMusic());
        textEducation.setText(PatientSurvey.getEducation());
        textFsinger.setText(PatientSurvey.getfSinger());
        textFsongs.setText(PatientSurvey.getfSongs());
        textClassic.setText(PatientSurvey.getClassic());
        textIsrael.setText(PatientSurvey.getIsrael());
        textLanguage.setText(PatientSurvey.getLanguage());
        textDance.setText(PatientSurvey.getDance());
        textLoazi.setText(PatientSurvey.getLoazi());
        textReligion.setText(PatientSurvey.getReligion());
        textRelaxing.setText(PatientSurvey.getRelaxing());

        return convertView;
    }
}
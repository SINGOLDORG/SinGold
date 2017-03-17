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

public class MatchingSurveyAdapter extends ArrayAdapter<MatchingSurvey> {
    public MatchingSurveyAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MatchingSurvey MatchingSurvey = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_matching_survey, parent, false);
        }

        TextView textId = (TextView) convertView.findViewById(R.id.textId);
        TextView textCountry = (TextView) convertView.findViewById(R.id.textCountry);
        TextView textYear = (TextView) convertView.findViewById(R.id.textYear);
        TextView textLanguage = (TextView) convertView.findViewById(R.id.textLanguage);
        TextView textCity = (TextView) convertView.findViewById(R.id.textCity);

        textId.setText(MatchingSurvey.getId());
        textCountry.setText(MatchingSurvey.getCountry());
        textYear.setText(MatchingSurvey.getYear());
        textLanguage.setText(MatchingSurvey.getLanguage());
        textCity.setText(MatchingSurvey.getCity());

        return convertView;
    }
}






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

public class SongAdapter extends ArrayAdapter<Song> {
    public SongAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Song Song = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_song, parent, false);
        }

        TextView textId = (TextView) convertView.findViewById(R.id.textId);
        TextView textIdPatient = (TextView) convertView.findViewById(R.id.textIdPatient);
        TextView textName = (TextView) convertView.findViewById(R.id.textName);
        TextView textSinger = (TextView) convertView.findViewById(R.id.textSinger);
        TextView textLink = (TextView) convertView.findViewById(R.id.textLink);

        textId.setText(Song.getId());
        textIdPatient.setText(Song.getIdPatient());
        textName.setText(Song.getName());
        textSinger.setText(Song.getSinger());
        textLink.setText(Song.getLink());

        return convertView;
    }
}




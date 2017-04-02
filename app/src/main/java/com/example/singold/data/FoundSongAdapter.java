package com.example.singold.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.singold.R;

/**
 * Created by user on 16/03/2017.
 */

public class FoundSongAdapter extends ArrayAdapter<Song> {
    public FoundSongAdapter(Context context, int resource) {
        super(context, resource);
    }

    private CheckBox check;
    private TextView textName,textSinger,textLink;


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Song Song = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_found_song, parent, false);
        }

        CheckBox check = (CheckBox) convertView.findViewById(R.id.check);
        TextView textName = (TextView) convertView.findViewById(R.id.textName);
        TextView textSinger = (TextView) convertView.findViewById(R.id.textSinger);
        TextView textLink = (TextView) convertView.findViewById(R.id.textLink);



        textName.setText(Song.getName());
        textSinger.setText(Song.getSinger());
        textLink.setText(Song.getLink());

        return convertView;
    }
}




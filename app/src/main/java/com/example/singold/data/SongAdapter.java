package com.example.singold.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.singold.R;

import java.util.ArrayList;

/**
 * Created by user on 16/03/2017.
 */

public class SongAdapter extends ArrayAdapter<Song> {
    private boolean chbx=false;
    private ArrayList<Song> selectedSongs;
    public SongAdapter(Context context, int resource,boolean chbx) {
        super(context, resource);
        this.chbx=chbx;
        selectedSongs=new ArrayList<Song>();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Song song = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_song, parent, false);
        }

        TextView textName = (TextView) convertView.findViewById(R.id.textName);
        TextView textSinger = (TextView) convertView.findViewById(R.id.textSinger);
        TextView textLink = (TextView) convertView.findViewById(R.id.textLink);
        CheckBox checkBox=(CheckBox)convertView.findViewById(R.id.chbSelect);
        if(chbx)
        {
            checkBox.setVisibility(View.VISIBLE);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                    {

                        selectedSongs.add(song);
                    }
                    else
                    {
                        selectedSongs.remove(song);
                    }
                }
            });
        }
        else
        {
            checkBox.setVisibility(View.GONE);
        }
        textName.setText(song.getName());
        textSinger.setText(song.getSinger());
        textLink.setText(song.getLink());

        return convertView;
    }

    public ArrayList<Song> getSelectedSongs() {
        return selectedSongs;
    }
}




package com.example.singold.data;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.singold.MyActivities.PlayerYouTubeActivity;
import com.example.singold.R;

import java.util.ArrayList;

import static com.example.singold.data.ConnectToServer.context;

/**
 * Created by user on 16/03/2017.
 */

public class SongAdapter extends ArrayAdapter<Song> {
    private boolean chbx=false;
    private ArrayList<Song> selectedSongs;
    private ImageButton btnPlay;
    private boolean toDelete =false;

    public SongAdapter(Context context, int resource,boolean chbx, boolean toDelete) {
        super(context, resource);
        this.chbx=chbx;
        this.toDelete = toDelete;
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
        ImageButton btnPlay=(ImageButton)convertView.findViewById(R.id.btnPlay);
        ImageButton delete=(ImageButton) convertView.findViewById(R.id.delete);




            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        Intent intent = new Intent(getContext(),
                                PlayerYouTubeActivity.class);
                        intent.putExtra("VIDEO_ID",song.getLink());
    //                    intent.putExtra("VIDEO_ID",song.getName());
                    getContext().startActivity(intent);
                }
            });

        if (toDelete) {
            delete.setVisibility(View.VISIBLE);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context,R.style.YourDialogStyle);

                    // set title
                    alertDialogBuilder.setTitle(R.string.deleteSong);

                    // set dialog message
                    alertDialogBuilder
                            .setMessage(R.string.sure)
                            .setCancelable(false)
                            .setPositiveButton(R.string.yes,new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked, close
                                    // current activity
                                    //ConnectToServer.connect();
                                    ConnectToServer.delSong(song);
                                    remove(song);
                                    notifyDataSetChanged();

                                }
                            })
                            .setNegativeButton(R.string.No,new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked, just close
                                    // the dialog box and do nothing
                                    dialog.cancel();
                                }
                            });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();

                }
            });
        }
        else
            delete.setVisibility(View.GONE);


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




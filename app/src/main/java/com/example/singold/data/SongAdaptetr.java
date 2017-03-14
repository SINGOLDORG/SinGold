package com.example.singold.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by samih on 14/03/2017.
 */

public class SongAdaptetr extends ArrayAdapter<Song> {
    public SongAdaptetr(Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);

    }
}

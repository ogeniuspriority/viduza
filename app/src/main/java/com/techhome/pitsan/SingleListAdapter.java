package com.techhome.pitsan;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class SingleListAdapter extends ArrayAdapter<String> {
    private Activity context;

    private String[] singlelocation;
    private String[] singlevolume;
    private String[] singledate;

    public SingleListAdapter(@NonNull Activity context1, String[] singlelocation, String[] singlevolume, String[] singledate) {
        super(context1, R.layout.crowdlist, singlelocation);
        this.context = context1;
        this.singledate = singledate;
        this.singlelocation = singlelocation;
        this.singlevolume = singlevolume;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.crowdlist, null, true);

        TextView txtlocation = rowView.findViewById(R.id.crowdlocation);
        TextView txtdate = rowView.findViewById(R.id.crowddate);
        TextView txtvolume = rowView.findViewById(R.id.crowdvolume);

        txtlocation.setText(singlelocation[position]);
        txtdate.setText(singledate[position]);
        txtvolume.setText(singlevolume[position]);


        return rowView;
    }
}

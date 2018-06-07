package com.techhome.pitsan;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CrowdsourcingListAdapter extends ArrayAdapter<String> {
    private int stepNumber;
    private int count;
    private int startCount;
    private Activity context;

    private String[] crowdlocation;
    private String[] crowdvolume;
    private String[] crowddate;

    public CrowdsourcingListAdapter(Activity context1, String[] crowdlocation, String[] crowdvolume, String[] crowddate, int startCount, int stepNumber) {
        super(context1, R.layout.crowdlist, crowdlocation);
        this.context = context1;

        this.crowdlocation = crowdlocation;
        this.crowdvolume = crowdvolume;
        this.crowddate = crowddate;
        this.startCount = Math.min(startCount, crowddate.length); //don't try to show more views than we have
        this.count = this.startCount;
        this.stepNumber = stepNumber;
        // Using an AsyncTask to load the slow images in a background thread

    }

    @Override
    public int getCount() {
        return count;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater mInflater = context.getLayoutInflater();
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.crowdlist, null);
            holder = new ViewHolder(convertView);
            holder.txtlocation = convertView.findViewById(R.id.crowdlocation);
            holder.txtvolume = convertView.findViewById(R.id.crowdvolume);
            holder.txtdate = convertView.findViewById(R.id.crowddate);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtlocation.setText(crowdlocation[position]);
        holder.txtvolume.setText(crowdvolume[position]);
        holder.txtdate.setText(crowddate[position]);

        return convertView;
    }

    public boolean showMore() {
        if (count == crowddate.length) {
            return true;
        } else {
            count = Math.min(count + stepNumber, crowddate.length); //don't go past the end
            notifyDataSetChanged(); //the count size has changed, so notify the super of the change
            return endReached();
        }
    }

    public boolean endReached() {
        return count == crowddate.length;
    }

    /**
     * Sets the ListView back to its initial count number
     */
    public void reset() {
        count = startCount;
        notifyDataSetChanged();
    }

    private static class ViewHolder {

        private TextView txtlocation;
        private TextView txtvolume;
        private TextView txtdate;

        public ViewHolder(View v) {
            txtlocation = v.findViewById(R.id.crowdlocation);
            txtdate = v.findViewById(R.id.crowddate);
            txtvolume = v.findViewById(R.id.crowdvolume);
        }
    }
}

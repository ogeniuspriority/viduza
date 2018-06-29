package com.techhome.pitsan.back_end;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.techhome.pitsan.R;
import com.techhome.pitsan.TruckDashboard;

import java.util.List;


/**
 * Created by USER on 2/6/2017.
 */
public class custom_adapter_for_pitsan_message_response_threads extends BaseAdapter {
    public List<String> data;
    public List<String> tags;
    public List<String> tags_unlike;
    Context c;
    int layoutResourceId;
    //-------------
    String[] thread_creator;
    String[] thread_content;
    String[] thread_creation_date;


    public custom_adapter_for_pitsan_message_response_threads(Context c, String[] thread_creator,
                                                              String[] thread_content,
                                                              String[] thread_creation_date) {

        this.c = c;
        this.thread_creator = thread_creator;
        this.thread_content = thread_content;
        this.thread_creation_date = thread_creation_date;

        // this.layoutResourceId = resource;
        this.c = c;


    }


    @Override
    public int getCount() {

        return this.thread_creator.length;

    }

    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        // viewHolder = new ViewHolder();
        // viewHolder.position = position;
        //-----For folder id,profile_photos
        if (convertView == null) {
            convertView = LayoutInflater.from(c).inflate(R.layout.pitsan_message_thread_response, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //---------------------------
        //final Boolax_favorite_booers_boos_objects boolax_boos = (Boolax_favorite_booers_boos_objects) getItem(position);
        viewHolder.thread_creator_left = convertView.findViewById(R.id.thread_creator);
        viewHolder.thread_content_left = convertView.findViewById(R.id.thread_content);
        viewHolder.thread_creation_date_left = convertView.findViewById(R.id.thread_creation_date);

        viewHolder.thread_creator_right = convertView.findViewById(R.id.thread_creator_right);
        viewHolder.thread_content_right = convertView.findViewById(R.id.thread_content_right);
        viewHolder.thread_creation_date_right = convertView.findViewById(R.id.thread_creation_date_right);

        viewHolder.left_pattern = convertView.findViewById(R.id.left_pattern);
        viewHolder.right_pattern = convertView.findViewById(R.id.right_pattern);

        viewHolder.left_pattern.setVisibility(View.VISIBLE);
        viewHolder.right_pattern.setVisibility(View.VISIBLE);
        if (thread_creator[position].equalsIgnoreCase(TruckDashboard.username)) {
            viewHolder.left_pattern.setVisibility(View.GONE);
            viewHolder.right_pattern.setVisibility(View.VISIBLE);

            viewHolder.thread_creator_right.setText(thread_creator[position]);
            viewHolder.thread_content_right.setText(thread_content[position]);
            viewHolder.thread_creation_date_right.setText(thread_creation_date[position]);


        } else {
            viewHolder.left_pattern.setVisibility(View.VISIBLE);
            viewHolder.right_pattern.setVisibility(View.GONE);

            viewHolder.thread_creator_left.setText(thread_creator[position]);
            viewHolder.thread_content_left.setText(thread_content[position]);
            viewHolder.thread_creation_date_left.setText(thread_creation_date[position]);

        }

        convertView.setTag(viewHolder);

        // viewHolder.boolax_event_name = (TextView) convertView.findViewById(R.id.boolax_event_name);


        //--------


        return convertView;
    }


    public static class ViewHolder {
        TextView thread_creator_left;
        TextView thread_content_left;
        TextView thread_creation_date_left;

        TextView thread_creator_right;
        TextView thread_content_right;
        TextView thread_creation_date_right;
        LinearLayout left_pattern;
        LinearLayout right_pattern;

        int position;

        public ViewHolder(View view) {

        }
    }


}

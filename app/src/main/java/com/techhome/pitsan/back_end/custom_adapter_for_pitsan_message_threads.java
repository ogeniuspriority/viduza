package com.techhome.pitsan.back_end;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.techhome.pitsan.R;
import com.techhome.pitsan.TruckDashboard;

import java.util.List;


/**
 * Created by USER on 2/6/2017.
 */
public class custom_adapter_for_pitsan_message_threads extends BaseAdapter {
    public List<String> data;
    public List<String> tags;
    public List<String> tags_unlike;
    Context c;
    int layoutResourceId;
    //-------------
    String[] thread_creator;
    String[] thread_content;
    String[] thread_creation_date;
    String[] thread_identifier_username;
    String[] thread_identifier_pit_id;


    public custom_adapter_for_pitsan_message_threads(Context c, String[] thread_creator,
                                                     String[] thread_content,
                                                     String[] thread_creation_date, String[] thread_identifier_username,
                                                     String[] thread_identifier_pit_id) {

        this.c = c;
        this.thread_creator = thread_creator;
        this.thread_content = thread_content;
        this.thread_creation_date = thread_creation_date;
        this.thread_identifier_username = thread_identifier_username;
        this.thread_identifier_pit_id = thread_identifier_pit_id;

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
        //ewHolder.position = position;
        //-----For folder id,profile_photos
        if (convertView == null) {
            convertView = LayoutInflater.from(c).inflate(R.layout.pitsan_message_thread, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.position = position;

        //---------------------------
        viewHolder.thread_creator = convertView.findViewById(R.id.thread_creator);
        viewHolder.thread_content = convertView.findViewById(R.id.thread_content);
        viewHolder.thread_creation_date = convertView.findViewById(R.id.thread_creation_date);
        //viewHolder.thread_identifier_username = convertView.findViewById(R.id.thread_creation_date);
        viewHolder.thread_identifier_pit_it = convertView.findViewById(R.id.thread_identifier_pit_it);
        //final Boolax_favorite_booers_boos_objects boolax_boos = (Boolax_favorite_booers_boos_objects) getItem(position);
        if (TruckDashboard.username.equalsIgnoreCase(thread_creator[viewHolder.position])) {
            viewHolder.thread_creator.setText("You");
        } else {
            viewHolder.thread_creator.setText("PITSAN LTD.");
        }
        viewHolder.thread_content.setText(thread_content[viewHolder.position]);
        viewHolder.thread_creation_date.setText(thread_creation_date[viewHolder.position]);
//        viewHolder.thread_identifier_username.setText(thread_identifier_username[viewHolder.position]);
        viewHolder.thread_identifier_pit_it.setText(thread_identifier_pit_id[viewHolder.position]);
        convertView.setTag(viewHolder);

        // viewHolder.boolax_event_name = (TextView) convertView.findViewById(R.id.boolax_event_name);


        //--------


        return convertView;
    }


    public static class ViewHolder {
        TextView thread_creator;
        TextView thread_content;
        TextView thread_creation_date;
        TextView thread_identifier_username;
        TextView thread_identifier_pit_it;

        int position;

        public ViewHolder(View view) {

        }
    }


}

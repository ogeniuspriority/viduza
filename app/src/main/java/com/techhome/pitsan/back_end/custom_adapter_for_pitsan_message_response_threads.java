package com.techhome.pitsan.back_end;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.techhome.pitsan.R;

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

        int position;

        public ViewHolder(View view) {

        }
    }


}

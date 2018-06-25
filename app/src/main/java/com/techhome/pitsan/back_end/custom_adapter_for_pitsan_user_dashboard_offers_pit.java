package com.techhome.pitsan.back_end;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.techhome.pitsan.R;

import java.util.List;

/**
 * Created by USER on 2/6/2017.
 */
public class custom_adapter_for_pitsan_user_dashboard_offers_pit extends BaseAdapter {
    public List<String> data;
    public List<String> tags;
    public List<String> tags_unlike;
    Context c;
    int layoutResourceId;
    //-------------
    String[] request_no_data;
    String[] company_name_data;
    String[] amount_data;
    String[] date_data;
    String[] comment_data;


    public custom_adapter_for_pitsan_user_dashboard_offers_pit(Context c, String[] request_no_data,
                                                               String[] company_name_data,
                                                               String[] amount_data,
                                                               String[] date_data,
                                                               String[] comment_data) {

        this.c = c;
        this.request_no_data = request_no_data;
        this.company_name_data = company_name_data;
        this.amount_data = amount_data;
        this.date_data = date_data;
        this.comment_data = comment_data;
        // this.layoutResourceId = resource;


    }


    @Override
    public int getCount() {

        return this.request_no_data.length;

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
        //viewHolder = new ViewHolder();
        //viewHolder.position = position;
        //-----For folder id,profile_photos
        if (convertView == null) {
            convertView = LayoutInflater.from(c).inflate(R.layout.pitsan_user_dashboard_offers_data, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
            viewHolder.position = position;
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.position = position;
        }
        convertView.setTag(viewHolder);
        //--------
        viewHolder.request_no_data = convertView.findViewById(R.id.request_no_data);
        viewHolder.company_name_data = convertView.findViewById(R.id.company_name_data);
        viewHolder.amount_data = convertView.findViewById(R.id.amount_data);
        viewHolder.date_data_ = convertView.findViewById(R.id.date_data);
        viewHolder.comment = convertView.findViewById(R.id.comment_data);

        viewHolder.request_no_data.setText(request_no_data[viewHolder.position]);
        viewHolder.company_name_data.setText(company_name_data[viewHolder.position]);
        viewHolder.amount_data.setText(amount_data[viewHolder.position] + " Rwf");
        viewHolder.date_data_.setText(date_data[viewHolder.position]);
        viewHolder.comment.setText(comment_data[viewHolder.position]);


        viewHolder.accept_offer = convertView.findViewById(R.id.accept_offer);


        return convertView;
    }

    public static class ViewHolder {
        TextView request_no_data;
        TextView company_name_data;
        TextView amount_data;
        TextView date_data_;
        TextView comment;

        Button accept_offer;

        int position;

        public ViewHolder(View view) {

        }
    }


}

package com.techhome.pitsan.back_end;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.techhome.pitsan.R;

import java.util.ArrayList;
import java.util.List;

import static com.techhome.pitsan.CrowdsourcingFragment.seltChoices_users_for_sending_top;
import static com.techhome.pitsan.CrowdsourcingFragment.seltChoices_users_for_sending_top_removed;

/**
 * Created by USER on 2/6/2017.
 */
public class custom_adapter_for_pitsan_crowdsourcing_pit extends BaseAdapter {
    public List<String> data;
    public List<String> tags;
    public List<String> tags_unlike;

    public List<String> chosen_rows;
    Context c;
    int layoutResourceId;
    //-------------
    String[] pitsan_single_pit_full_names;
    String[] pitsan_single_pit_district;
    String[] pitsan_single_pit_sector;
    String[] pitsan_single_pit_cell;
    String[] pitsan_single_pit_village;
    String[] pitsan_single_pit_street;
    String[] pitsan_single_pit_house_number;
    String[] pitsan_single_pit_ref_id;
    String[] pitsan_single_pit_volume;
    String[] pitsan_single_pit_email_add;
    String[] pitsan_single_pit_telephone;
    String[] pitsan_single_pit_request_type;

    String[] pitsan_single_pit_start_date;
    String[] pitsan_single_pit_end_dat;


    public custom_adapter_for_pitsan_crowdsourcing_pit(Context c, String[] pitsan_single_pit_full_names,
                                                       String[] pitsan_single_pit_district,
                                                       String[] pitsan_single_pit_sector,
                                                       String[] pitsan_single_pit_cell,
                                                       String[] pitsan_single_pit_village,
                                                       String[] pitsan_single_pit_street,
                                                       String[] pitsan_single_pit_house_number,
                                                       String[] pitsan_single_pit_ref_id,
                                                       String[] pitsan_single_pit_volume,
                                                       String[] pitsan_single_pit_email_add,
                                                       String[] pitsan_single_pit_telephone,
                                                       String[] pitsan_single_pit_request_type,
                                                       String[] pitsan_single_pit_start_date,
                                                       String[] pitsan_single_pit_end_date) {

        this.c = c;
        this.pitsan_single_pit_end_dat = pitsan_single_pit_end_date;
        this.pitsan_single_pit_start_date = pitsan_single_pit_start_date;
        this.pitsan_single_pit_full_names = pitsan_single_pit_full_names;
        this.pitsan_single_pit_district = pitsan_single_pit_district;
        this.pitsan_single_pit_sector = pitsan_single_pit_sector;
        this.pitsan_single_pit_cell = pitsan_single_pit_cell;
        this.pitsan_single_pit_village = pitsan_single_pit_village;
        this.pitsan_single_pit_street = pitsan_single_pit_street;
        this.pitsan_single_pit_house_number = pitsan_single_pit_house_number;
        this.pitsan_single_pit_ref_id = pitsan_single_pit_ref_id;
        this.pitsan_single_pit_volume = pitsan_single_pit_volume;
        this.pitsan_single_pit_email_add = pitsan_single_pit_email_add;
        this.pitsan_single_pit_telephone = pitsan_single_pit_telephone;
        this.pitsan_single_pit_request_type = pitsan_single_pit_request_type;
        // this.layoutResourceId = resource;
        this.c = c;

        tags = new ArrayList<String>();
        tags_unlike = new ArrayList<String>();
        chosen_rows = new ArrayList<String>();
        int size = pitsan_single_pit_request_type.length;
        for (int i = 0; i < size; i++) {
            tags.add("tag");
            tags_unlike.add("tag");
            chosen_rows.add("notchecked");
        }


    }


    @Override
    public int getCount() {

        return this.pitsan_single_pit_full_names.length;

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
            convertView = LayoutInflater.from(c).inflate(R.layout.pitsan_crowdsourcing_pit_data, parent, false);
            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);
            viewHolder.position = position;

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.position = position;
        }
        convertView.setTag(viewHolder);

        //--------
        viewHolder.names = convertView.findViewById(R.id.names);
        viewHolder.names.setText(pitsan_single_pit_full_names[position]);
        viewHolder.location = convertView.findViewById(R.id.location);
        viewHolder.location.setText(pitsan_single_pit_district[position] + " \n " +
                pitsan_single_pit_sector[position] + " \n " +
                pitsan_single_pit_cell[position] + " \n " +
                pitsan_single_pit_village[position] + " \n " +
                pitsan_single_pit_street[position] + " \n " +
                pitsan_single_pit_house_number[position]);
        viewHolder.start_date = convertView.findViewById(R.id.start_date);
        viewHolder.start_date.setText(pitsan_single_pit_start_date[position]);
        viewHolder.end_date = convertView.findViewById(R.id.end_date);
        viewHolder.end_date.setText(pitsan_single_pit_end_dat[position]);
        viewHolder.crowdsourcing_view = convertView.findViewById(R.id.crowdsourcing_view);

        viewHolder.crowdsourcing_view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (chosen_rows.get(position) == "notchecked") {
                    viewHolder.crowdsourcing_view.setBackgroundColor(Color.parseColor("#AED6F1"));
                    seltChoices_users_for_sending_top.add(pitsan_single_pit_ref_id[position]);
                    chosen_rows.set(position, "checked");
                } else {
                    seltChoices_users_for_sending_top_removed.add(pitsan_single_pit_ref_id[position]);
                    //seltChoices_users_for_sending_top.remove(position);
                    viewHolder.crowdsourcing_view.setBackgroundColor(Color.TRANSPARENT);
                    chosen_rows.set(position, "notchecked");
                }
                return false;
            }
        });

        if (chosen_rows.get(position) == "notchecked") {

            viewHolder.crowdsourcing_view.setBackgroundColor(Color.TRANSPARENT);
            //chosen_rows.add(position, "checked");
        } else {
            viewHolder.crowdsourcing_view.setBackgroundColor(Color.parseColor("#AED6F1"));
            // chosen_rows.add(position, "notchecked");
        }
        //----------


        viewHolder.volume = convertView.findViewById(R.id.volume);
        String theVolume = "";
        if (pitsan_single_pit_volume[position].equalsIgnoreCase("0")) {
            theVolume = "0-5 METER CUBES";
        } else if (pitsan_single_pit_volume[position].equalsIgnoreCase("1")) {
            theVolume = "5-10 METER CUBES";
        } else if (pitsan_single_pit_volume[position].equalsIgnoreCase("2")) {
            theVolume = "10-15 METER CUBES";
        } else if (pitsan_single_pit_volume[position].equalsIgnoreCase("3")) {
            theVolume = "15-20 METER CUBES";
        } else if (pitsan_single_pit_volume[position].equalsIgnoreCase("4")) {
            theVolume = "20-25 METER CUBES";
        } else if (pitsan_single_pit_volume[position].equalsIgnoreCase("5")) {
            theVolume = "25-00 METER CUBES";
        } else if (pitsan_single_pit_volume[position].equalsIgnoreCase("6")) {
            theVolume = "30-40 METER CUBES";
        } else if (pitsan_single_pit_volume[position].equalsIgnoreCase("7")) {
            theVolume = "40-50 METER CUBES";
        } else if (pitsan_single_pit_volume[position].equalsIgnoreCase("8")) {
            theVolume = "50<... METER CUBES";
        }
        viewHolder.volume.setText(theVolume);
        viewHolder.email = convertView.findViewById(R.id.email);
        viewHolder.email.setText(pitsan_single_pit_email_add[position]);
        viewHolder.tel = convertView.findViewById(R.id.tel);
        viewHolder.tel.setText("");
        viewHolder.request_type = convertView.findViewById(R.id.request_type);
        viewHolder.request_type.setText("CROWD SOURCING PIT EMPTYING");


        return convertView;
    }

    public static class ViewHolder {
        static ArrayList<Integer> theseChoices = new ArrayList<Integer>();
        TextView names;
        TextView location;
        TextView start_date;
        TextView end_date;

        TextView volume;
        TextView email;
        TextView tel;
        TextView request_type;
        View vrr;
        LinearLayout crowdsourcing_view;

        int position;


    }


}

package com.techhome.pitsan.back_end;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by USER on 2/6/2017.
 */
public class custom_adapter_for_boos extends BaseAdapter {
    public List<String> data;
    public List<String> tags;
    public List<String> tags_unlike;
    Context c;
    int layoutResourceId;
    //-------------
    String[] fname;
    String[] lname;
    String[] gender;
    String[] email;
    String[] username;
    String[] regdate;
    String[] age;
    String[] birthdate;
    String[] country;
    String[] province;
    String[] district;
    String[] how_attractive;
    String[] like_status;
    String[] about_life;
    String[] about_love;
    String[] about_relationships;
    String[] about_lover_criteria;
    String[] about_hobbies;
    String[] about_religious_affiliation;
    String[] about_academics_and_work;
    String[] avatar_image;
    String[] remote_id;
    String[] interested_in;
    String[] your_height;
    String[] your_weight;
    String[] your_skin_color;


    public custom_adapter_for_boos(Context c, String[] fname, String[] lname, String[] gender, String[] email,
                                   String[] username, String[] regdate, String[] age, String[] birthdate,
                                   String[] country, String[] province, String[] district, String[] how_attractive,
                                   String[] like_status, String[] about_life, String[] about_love, String[] about_relationships,
                                   String[] about_lover_criteria, String[] about_hobbies, String[] about_religious_affiliation,
                                   String[] about_academics_and_work, String[] avatar_image, String[] remote_id,
                                   String[] interested_in, String[] your_height, String[] your_weight, String[] your_skin_color) {

        this.c = c;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.email = email;
        this.username = username;
        this.regdate = regdate;
        this.age = age;
        this.birthdate = birthdate;
        this.country = country;
        this.province = province;
        this.district = district;
        this.how_attractive = how_attractive;
        this.like_status = like_status;
        this.about_life = about_life;
        this.about_love = about_love;
        this.about_relationships = about_relationships;
        this.about_lover_criteria = about_lover_criteria;
        this.about_hobbies = about_hobbies;
        this.about_religious_affiliation = about_religious_affiliation;
        this.about_academics_and_work = about_academics_and_work;
        this.avatar_image = avatar_image;
        this.remote_id = remote_id;
        this.interested_in = interested_in;
        this.your_height = your_height;
        this.your_weight = your_weight;
        this.your_skin_color = your_skin_color;
        // this.layoutResourceId = resource;
        this.c = c;


    }


    @Override
    public int getCount() {

        return this.fname.length;

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
            //convertView = LayoutInflater.from(c).inflate(R.layout.boolax_boo_data_snippet, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //---------------------------
        //final Boolax_favorite_booers_boos_objects boolax_boos = (Boolax_favorite_booers_boos_objects) getItem(position);

        convertView.setTag(viewHolder);

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final String theAvatarImage = new Boolax_get_the_string_avatar(c, Config.BOOLAX_FAVORITE_BOOERS_GET_BOOS_AVATARS_DATA_URL.toString(), remote_id[position]).execute().get();
                    Log.w("theAvatarImage",""+Config.BOOLAX_FAVORITE_BOOERS_GET_BOOS_AVATARS + "/" + theAvatarImage);
                    ((Activity) c).runOnUiThread(new Runnable() {
                        public void run() {
                            Glide.with(c)
                                    .load(Config.BOOLAX_FAVORITE_BOOERS_GET_BOOS_AVATARS + "/" + theAvatarImage)
                                    .apply(new RequestOptions().placeholder(R.mipmap.placeholder).error(R.mipmap.placeholder))
                                    .into(viewHolder.boolax_boo_avatar);
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        }).start();*/

        //--------


        return convertView;
    }

    public static class ViewHolder {
        TextView boolax_boo_location_text;
        TextView boolax_boo_name;
        TextView boo_add_stream;
        TextView boolax_boo_age;

        int position;

        public ViewHolder(View view) {

        }
    }


}

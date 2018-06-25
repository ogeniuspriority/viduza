package com.techhome.pitsan.back_end;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by USER on 2/6/2017.
 */
public class pitsan_parse_message_threads extends AsyncTask<Void, Void, Boolean> {
    Context c;
    String jsonData;
    String[] thread_creator;
    String[] thread_content;
    String[] thread_creation_date;

    ListView boolax_boos_list;


    public pitsan_parse_message_threads(Context c, String jsonData, ListView boolax_boos_list) {
        this.c = c;
        this.jsonData = jsonData;
        this.boolax_boos_list = boolax_boos_list;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        return this.parseData();
    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);
        // swipe_view.setRefreshing(false);
        if (isParsed) {
            //span_count.setText(""+Boolax_favorite_booers_boos_remoteIds.length);
            boolax_boos_list.setAdapter(new custom_adapter_for_pitsan_message_threads(c, thread_creator,
                    thread_content,
                    thread_creation_date));

        } else {


        }
    }

    private Boolean parseData() {
        try {
            //-------------------------------------------

            //------------
            JSONObject posts_JSON = new JSONObject(
                    jsonData);


            //------------------
            JSONArray Boollax_favorite_booers_boos_loaded = (JSONArray) posts_JSON
                    .get("My Gorgeous loaded Boos");
            //---All Fresh New Boos Data--To Load
            String[] thread_creator = new String[Boollax_favorite_booers_boos_loaded.length()];
            String[] thread_content = new String[Boollax_favorite_booers_boos_loaded.length()];
            String[] thread_creation_date = new String[Boollax_favorite_booers_boos_loaded.length()];
            //----
            for (int counter = 0; counter < Boollax_favorite_booers_boos_loaded.length(); counter++) {
                thread_creator[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "boo_users_id")
                        .toString();


                thread_content[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "PassedOnBoos")
                        .toString();
                //----
                //--
                thread_creation_date[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "boo_users_avatar_image")
                        .toString();

                //----------


            }

            return true;


        } catch (JSONException e) {
            e.printStackTrace();
            Log.w("josn_", e.toString());
        }
        return false;
    }
}

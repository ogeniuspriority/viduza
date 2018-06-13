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
public class Boolax_favorite_booers_parse_booers extends AsyncTask<Void, Void, Boolean> {
    Context c;
    String jsonData;
    String[] Boolax_favorite_booers_boos_remoteIds;
    String[] Boolax_favorite_booers_boos_PassonStatus;
    String[] Boolax_favorite_booers_boos_Avatar;
    String[] Boolax_favorite_booers_boos_Location_text;
    String[] Boolax_favorite_booers_boos_Username;
    String[] Boolax_favorite_booers_boos_WholeDatastream;
    String[] Boolax_favorite_booers_boos_Age;

    ListView boolax_boos_list;


    public Boolax_favorite_booers_parse_booers(Context c, String jsonData, ListView boolax_boos_list) {
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
            Boolax_favorite_booers_boos_remoteIds = new String[Boollax_favorite_booers_boos_loaded.length()];
            Boolax_favorite_booers_boos_PassonStatus = new String[Boollax_favorite_booers_boos_loaded.length()];
            Boolax_favorite_booers_boos_Avatar = new String[Boollax_favorite_booers_boos_loaded.length()];
            Boolax_favorite_booers_boos_Location_text = new String[Boollax_favorite_booers_boos_loaded.length()];
            Boolax_favorite_booers_boos_Username = new String[Boollax_favorite_booers_boos_loaded.length()];
            Boolax_favorite_booers_boos_WholeDatastream = new String[Boollax_favorite_booers_boos_loaded.length()];
            Boolax_favorite_booers_boos_Age = new String[Boollax_favorite_booers_boos_loaded.length()];
            //----
            for (int counter = 0; counter < Boollax_favorite_booers_boos_loaded.length(); counter++) {
                Boolax_favorite_booers_boos_remoteIds[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "boo_users_id")
                        .toString();


                Boolax_favorite_booers_boos_PassonStatus[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "PassedOnBoos")
                        .toString();
                //----
                //--
                Boolax_favorite_booers_boos_Avatar[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "boo_users_avatar_image")
                        .toString();
                Boolax_favorite_booers_boos_Location_text[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "boo_users_country") + " " + Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "boo_users_province") + " " + Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "boo_users_district")
                        .toString();

                Boolax_favorite_booers_boos_Username[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "boo_users_username")
                        .toString();
                Boolax_favorite_booers_boos_WholeDatastream[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter).toString();
                Boolax_favorite_booers_boos_Age[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "boo_users_birthdate")
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

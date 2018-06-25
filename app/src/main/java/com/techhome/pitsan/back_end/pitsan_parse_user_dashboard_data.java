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
public class pitsan_parse_user_dashboard_data extends AsyncTask<Void, Void, Boolean> {
    Context c;
    String jsonData;
    String[] request_no_data;
    String[] company_name_data;
    String[] amount_data;
    String[] date_data;
    String[] comment_data;

    ListView singleList;


    public pitsan_parse_user_dashboard_data(Context c, String jsonData, ListView singleList) {
        this.c = c;
        this.jsonData = jsonData;
        this.singleList = singleList;

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
            //span_count.setText(""+Boolax_favorite_booers_boos_remoteIds.length);\

            singleList.setAdapter(new custom_adapter_for_pitsan_user_dashboard_offers_pit(c, request_no_data,
                    company_name_data,
                    amount_data,
                    date_data, comment_data
            ));


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
                    .get("user_offers");
            //---All Fresh New Boos Data--To Load
            request_no_data = new String[Boollax_favorite_booers_boos_loaded.length()];
            company_name_data = new String[Boollax_favorite_booers_boos_loaded.length()];
            amount_data = new String[Boollax_favorite_booers_boos_loaded.length()];
            date_data = new String[Boollax_favorite_booers_boos_loaded.length()];
            comment_data = new String[Boollax_favorite_booers_boos_loaded.length()];
            //----
            for (int counter = 0; counter < Boollax_favorite_booers_boos_loaded.length(); counter++) {
                comment_data[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "pitsan_db_offer_replies_comment")
                        .toString();
                request_no_data[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "pitsan_db_offer_replies_offer_or_request_id")
                        .toString();
                company_name_data[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "pitsan_db_pit_emptying_or_truck_request_fullnames")
                        .toString();
                amount_data[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "pitsan_db_offer_replies_price")
                        .toString();
                date_data[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "pitsan_db_offer_replies_date_proposed")
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

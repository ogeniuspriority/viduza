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
public class pitsan_parse_single_feed_pit_data extends AsyncTask<Void, Void, Boolean> {
    Context c;
    String jsonData;
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
    String[] pitsan_single_pit_end_date;

    ListView singleList;


    public pitsan_parse_single_feed_pit_data(Context c, String jsonData, ListView singleList) {
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
            //span_count.setText(""+Boolax_favorite_booers_boos_remoteIds.length);
            singleList.setAdapter(new custom_adapter_for_pitsan_single_pit(c, pitsan_single_pit_full_names,
                    pitsan_single_pit_district,
                    pitsan_single_pit_sector,
                    pitsan_single_pit_cell,
                    pitsan_single_pit_village,
                    pitsan_single_pit_street,
                    pitsan_single_pit_house_number,
                    pitsan_single_pit_ref_id,
                    pitsan_single_pit_volume,
                    pitsan_single_pit_email_add,
                    pitsan_single_pit_telephone,
                    pitsan_single_pit_request_type,
                    pitsan_single_pit_start_date,
                    pitsan_single_pit_end_date));

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
                    .get("pitsan_single_pit_reqs");
            //---All Fresh New Boos Data--To Load
            pitsan_single_pit_full_names = new String[Boollax_favorite_booers_boos_loaded.length()];
            pitsan_single_pit_district = new String[Boollax_favorite_booers_boos_loaded.length()];
            pitsan_single_pit_sector = new String[Boollax_favorite_booers_boos_loaded.length()];
            pitsan_single_pit_cell = new String[Boollax_favorite_booers_boos_loaded.length()];
            pitsan_single_pit_village = new String[Boollax_favorite_booers_boos_loaded.length()];
            pitsan_single_pit_street = new String[Boollax_favorite_booers_boos_loaded.length()];
            pitsan_single_pit_house_number = new String[Boollax_favorite_booers_boos_loaded.length()];
            pitsan_single_pit_ref_id = new String[Boollax_favorite_booers_boos_loaded.length()];
            pitsan_single_pit_volume = new String[Boollax_favorite_booers_boos_loaded.length()];
            pitsan_single_pit_email_add = new String[Boollax_favorite_booers_boos_loaded.length()];
            pitsan_single_pit_telephone = new String[Boollax_favorite_booers_boos_loaded.length()];
            pitsan_single_pit_request_type = new String[Boollax_favorite_booers_boos_loaded.length()];
            pitsan_single_pit_start_date = new String[Boollax_favorite_booers_boos_loaded.length()];
            pitsan_single_pit_end_date = new String[Boollax_favorite_booers_boos_loaded.length()];
            //----
            for (int counter = 0; counter < Boollax_favorite_booers_boos_loaded.length(); counter++) {
                pitsan_single_pit_start_date[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "pitsan_db_pit_emptying_or_truck_request_startdate")
                        .toString();
                pitsan_single_pit_end_date[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "pitsan_db_pit_emptying_or_truck_request_enddate")
                        .toString();
                pitsan_single_pit_full_names[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "pitsan_db_pit_emptying_or_truck_request_fullnames")
                        .toString();
                pitsan_single_pit_district[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "pitsan_db_pit_emptying_or_truck_request_district")
                        .toString();
                pitsan_single_pit_sector[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "pitsan_db_pit_emptying_or_truck_request_sector")
                        .toString();
                pitsan_single_pit_cell[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "pitsan_db_pit_emptying_or_truck_request_cell")
                        .toString();
                pitsan_single_pit_village[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "pitsan_db_pit_emptying_or_truck_request_village")
                        .toString();
                pitsan_single_pit_street[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "pitsan_db_pit_emptying_or_truck_request_street")
                        .toString();
                pitsan_single_pit_house_number[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "pitsan_db_pit_emptying_or_truck_request_house_nber")
                        .toString();
                pitsan_single_pit_ref_id[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "pitsan_db_pit_emptying_or_truck_request_reference_id")
                        .toString();
                pitsan_single_pit_volume[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "pitsan_db_pit_emptying_or_truck_request_volume")
                        .toString();
                pitsan_single_pit_email_add[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "pitsan_db_pit_emptying_or_truck_request_email")
                        .toString();
                pitsan_single_pit_telephone[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "pitsan_db_pit_emptying_or_truck_request_phone_number")
                        .toString();
                pitsan_single_pit_request_type[counter] = Boollax_favorite_booers_boos_loaded
                        .getJSONObject(
                                counter)
                        .getString(
                                "pitsan_db_pit_emptying_or_truck_request_request_type")
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

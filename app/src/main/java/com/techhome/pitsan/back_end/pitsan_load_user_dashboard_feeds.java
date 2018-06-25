package com.techhome.pitsan.back_end;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

/**
 * Created by USER on 2/6/2017.
 */
public class pitsan_load_user_dashboard_feeds extends AsyncTask<Void, Void, String> {
    Context c;
    String magicUrl;
    String ref_id_log;
    //----------
    TextView fullNames;
    TextView location;
    TextView offerType;
    TextView Tel;

    SwipeRefreshLayout swiperefresh;
    ListView list;
    private ProgressDialog dialog;

    public pitsan_load_user_dashboard_feeds(Context c, String magicUrl, String ref_id_log,
                                            TextView fullNames, TextView location, TextView offerType,
                                            TextView Tel, SwipeRefreshLayout swiperefresh, ListView list) {
        this.c = c;
        this.magicUrl = magicUrl;
        this.ref_id_log = ref_id_log;
        //-----------
        this.fullNames = fullNames;
        this.location = location;
        this.offerType = offerType;
        this.Tel = Tel;
        this.swiperefresh = swiperefresh;
        this.list = list;
        dialog = new ProgressDialog(c);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setMessage("Sending..");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    @Override
    protected String doInBackground(Void... voids) {

        try {
            return this.downloadData();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);
        dialog.dismiss();
        swiperefresh.setRefreshing(false);
        if (jsonData == null) {
            Toast.makeText(c, "No Internet!", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(c, "No Internet!"+jsonData, Toast.LENGTH_SHORT).show();
            //parse the json--
            String[] theServed = jsonData.split("~");
            try {
                JSONObject posts_JSON = new JSONObject(
                        theServed[1]);
                JSONArray Boollax_favorite_booers_boos_loaded = (JSONArray) posts_JSON
                        .get("user_req_creds_offers");
                //---All Fresh New Boos Data--To Load
                String[] fullNames__ = new String[Boollax_favorite_booers_boos_loaded.length()];
                String[] offerType__ = new String[Boollax_favorite_booers_boos_loaded.length()];
                String[] Tel__ = new String[Boollax_favorite_booers_boos_loaded.length()];
                String[] district__ = new String[Boollax_favorite_booers_boos_loaded.length()];
                String[] sector__ = new String[Boollax_favorite_booers_boos_loaded.length()];
                String[] cell__ = new String[Boollax_favorite_booers_boos_loaded.length()];
                String[] village__ = new String[Boollax_favorite_booers_boos_loaded.length()];
                String[] street__ = new String[Boollax_favorite_booers_boos_loaded.length()];
                String[] housenumber__ = new String[Boollax_favorite_booers_boos_loaded.length()];
                //----
                for (int counter = 0; counter < Boollax_favorite_booers_boos_loaded.length(); counter++) {
                    fullNames__[counter] = Boollax_favorite_booers_boos_loaded
                            .getJSONObject(
                                    counter)
                            .getString(
                                    "pitsan_db_pit_emptying_or_truck_request_fullnames")
                            .toString();
                    offerType__[counter] = Boollax_favorite_booers_boos_loaded
                            .getJSONObject(
                                    counter)
                            .getString(
                                    "pitsan_db_pit_emptying_or_truck_request_request_type")
                            .toString();
                    Tel__[counter] = Boollax_favorite_booers_boos_loaded
                            .getJSONObject(
                                    counter)
                            .getString(
                                    "pitsan_db_pit_emptying_or_truck_request_phone_number")
                            .toString();
                    district__[counter] = Boollax_favorite_booers_boos_loaded
                            .getJSONObject(
                                    counter)
                            .getString(
                                    "pitsan_db_pit_emptying_or_truck_request_district")
                            .toString();
                    sector__[counter] = Boollax_favorite_booers_boos_loaded
                            .getJSONObject(
                                    counter)
                            .getString(
                                    "pitsan_db_pit_emptying_or_truck_request_sector")
                            .toString();
                    cell__[counter] = Boollax_favorite_booers_boos_loaded
                            .getJSONObject(
                                    counter)
                            .getString(
                                    "pitsan_db_pit_emptying_or_truck_request_cell")
                            .toString();
                    village__[counter] = Boollax_favorite_booers_boos_loaded
                            .getJSONObject(
                                    counter)
                            .getString(
                                    "pitsan_db_pit_emptying_or_truck_request_village")
                            .toString();
                    street__[counter] = Boollax_favorite_booers_boos_loaded
                            .getJSONObject(
                                    counter)
                            .getString(
                                    "pitsan_db_pit_emptying_or_truck_request_street")
                            .toString();
                    housenumber__[counter] = Boollax_favorite_booers_boos_loaded
                            .getJSONObject(
                                    counter)
                            .getString(
                                    "pitsan_db_pit_emptying_or_truck_request_house_nber")
                            .toString();
                }
                //----------
                fullNames.setText(fullNames__[0]);
                location.setText(district__[0] +
                        "\n" + sector__[0] + "\n" +
                        cell__[0] + "\n" +
                        village__[0] + "\n" +
                        street__[0] + "\n" +
                        housenumber__[0]);
                offerType.setText((offerType__[0].equalsIgnoreCase("0")) ? "SINGLE PIT EMPTYING" : "CROWDSOURCING PIT EMPTYING");
                Tel.setText(Tel__[0]);

                new pitsan_parse_user_dashboard_data(c, theServed[0], list).execute();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            //------------------


        }
    }

    private String downloadData() {

        HttpURLConnection con = pitsan_load_user_dashboard_feeds_connector.connect(magicUrl, ref_id_log);
        if (con == null) {
            return null;
        }
        try {
            InputStream is = new BufferedInputStream(con.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            StringBuffer jsonData = new StringBuffer();
            while ((line = br.readLine()) != null) {
                jsonData.append(line + "\n");
            }
            // Send post request

            br.close();
            is.close();


            return jsonData.toString();


        } catch (IOException e) {
            e.printStackTrace();


        }


        return null;
    }
}

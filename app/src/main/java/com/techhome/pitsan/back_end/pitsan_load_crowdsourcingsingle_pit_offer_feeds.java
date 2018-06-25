package com.techhome.pitsan.back_end;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

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
public class pitsan_load_crowdsourcingsingle_pit_offer_feeds extends AsyncTask<Void, Void, String> {
    Context c;
    String magicUrl;
    String ref_id_log;
    //----------
    SwipeRefreshLayout swiperefresh;
    ListView singleList;
    private ProgressDialog dialog;

    public pitsan_load_crowdsourcingsingle_pit_offer_feeds(Context c, String magicUrl, String ref_id, SwipeRefreshLayout swiperefresh, ListView singleList) {
        this.c = c;
        this.magicUrl = magicUrl;
        this.ref_id_log = ref_id;
        this.swiperefresh = swiperefresh;
        this.singleList = singleList;
        //-----------
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
            //parse the json--
            Log.w("jsonData_", jsonData);
            new pitsan_parse_crowdsourcing_feed_pit_data(c, jsonData, singleList).execute();


        }
    }

    private String downloadData() {

        HttpURLConnection con = pitsan_crowdsourcing_pit_feeds_connector.connect(magicUrl, ref_id_log
        );
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

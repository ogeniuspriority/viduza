package com.techhome.pitsan.back_end;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.techhome.pitsan.TruckDashboard;

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
public class pitsan_truck_driver_log_in_request extends AsyncTask<Void, Void, String> {
    Context c;

    String magicUrl;
    //------------------------------------
    EditText username;
    EditText password;
    //----------
    private ProgressDialog dialog;

    public pitsan_truck_driver_log_in_request(Context c, String magicUrl, EditText username,
                                              EditText password) {
        this.c = c;
        this.magicUrl = magicUrl;
        this.username = username;
        this.password = password;
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
        if (jsonData == null) {
            Toast.makeText(c, "No Internet!", Toast.LENGTH_SHORT).show();
        } else {
            //parse the json--
            Log.w("jsonData_", jsonData);
            if (jsonData.contains("truck driver entered")) {
                Intent intent = new Intent(c, TruckDashboard.class);
                intent.putExtra("username", "" + username.getText().toString());
                c.startActivity(intent);

            } else {
                Toast.makeText(c, "Log in failed!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private String downloadData() {

        HttpURLConnection con = pitsab_truck_driver_log_in_connect.connect(magicUrl, username.getText().toString(),
                password.getText().toString());
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

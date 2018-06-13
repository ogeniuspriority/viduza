package com.techhome.pitsan.back_end;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Spinner;

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
public class pitsan_send_the_client_request extends AsyncTask<Void, Void, String> {
    Context c;

    String magicUrl;
    //------------------------------------
    EditText client_phone_numbr;
    EditText client_full_names;
    EditText client_email;
    Spinner client_province;
    Spinner client_district;
    Spinner client_sector;
    EditText client_cell;
    EditText client_village;
    EditText client_street;
    EditText client_house_number;
    Spinner client_volume;
    EditText client_start_date;
    EditText client_end_date;

    public pitsan_send_the_client_request(Context c, String magicUrl, EditText client_phone_numbr,
                                          EditText client_full_names,
                                          EditText client_email,
                                          Spinner client_province,
                                          Spinner client_district,
                                          Spinner client_sector,
                                          EditText client_cell,
                                          EditText client_village,
                                          EditText client_street,
                                          EditText client_house_number,
                                          Spinner client_volume,
                                          EditText client_start_date,
                                          EditText client_end_date) {
        this.c = c;
        this.magicUrl = magicUrl;
        this.client_phone_numbr = client_phone_numbr;
        this.client_full_names = client_full_names;
        this.client_email = client_email;
        this.client_province = client_province;
        this.client_district = client_district;
        this.client_sector = client_sector;
        this.client_cell = client_cell;
        this.client_village = client_village;
        this.client_street = client_street;
        this.client_house_number = client_house_number;
        this.client_volume = client_volume;
        this.client_start_date = client_start_date;
        this.client_end_date = client_end_date;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

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

        if (jsonData == null) {

        } else {
            //parse the json--

        }
    }

    private String downloadData() {

        HttpURLConnection con = pitsab_client_rq_connect.connect(magicUrl, client_phone_numbr.getText().toString(),
                client_full_names.getText().toString(),
                client_email.getText().toString(),
                client_province.getSelectedItem().toString(),
                client_district.getSelectedItem().toString(),
                client_sector.getSelectedItem().toString(),
                client_cell.getText().toString(),
                client_village.getText().toString(),
                client_street.getText().toString(),
                client_house_number.getText().toString(),
                client_volume.getSelectedItem().toString(),
                client_start_date.getText().toString(),
                client_end_date.getText().toString());
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

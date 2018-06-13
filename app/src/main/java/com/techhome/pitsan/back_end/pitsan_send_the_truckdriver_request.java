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
public class pitsan_send_the_truckdriver_request extends AsyncTask<Void, Void, String> {
    Context c;
    String magicUrl;
    EditText truck_driver_phone_numbr;
    EditText truck_driver_full_names;
    EditText truck_driver_email;
    Spinner truck_driver_province;
    Spinner truck_driver_district;
    Spinner truck_driver_sector;
    EditText truck_driver_cell;
    EditText truck_driver_village;
    EditText truck_driver_street;
    EditText truck_driver_house_number;
    Spinner truck_driver_volume;
    EditText truck_driver_license_plate;
    EditText truck_driver_station;

    public pitsan_send_the_truckdriver_request(Context c, String magicUrl, EditText truck_driver_phone_numbr,
                                               EditText truck_driver_full_names,
                                               EditText truck_driver_email,
                                               Spinner truck_driver_province,
                                               Spinner truck_driver_district,
                                               Spinner truck_driver_sector,
                                               EditText truck_driver_cell,
                                               EditText truck_driver_village,
                                               EditText truck_driver_street,
                                               EditText truck_driver_house_number,
                                               Spinner truck_driver_volume,
                                               EditText truck_driver_license_plate,
                                               EditText truck_driver_station) {
        this.c = c;
        this.magicUrl = magicUrl;
        this.truck_driver_phone_numbr = truck_driver_phone_numbr;
        this.truck_driver_full_names = truck_driver_full_names;
        this.truck_driver_email = truck_driver_email;
        this.truck_driver_province = truck_driver_province;
        this.truck_driver_district = truck_driver_district;
        this.truck_driver_sector = truck_driver_sector;
        this.truck_driver_cell = truck_driver_cell;
        this.truck_driver_village = truck_driver_village;
        this.truck_driver_street = truck_driver_street;
        this.truck_driver_house_number = truck_driver_house_number;
        this.truck_driver_volume = truck_driver_volume;
        this.truck_driver_license_plate = truck_driver_license_plate;
        this.truck_driver_station = truck_driver_station;
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

        HttpURLConnection con = pitsan_send_the_truckdriver_request_connector.connect(magicUrl, truck_driver_phone_numbr.getText().toString(),
                truck_driver_full_names.getText().toString(),
                truck_driver_email.getText().toString(),
                truck_driver_province.getSelectedItem().toString(),
                truck_driver_district.getSelectedItem().toString(),
                truck_driver_sector.getSelectedItem().toString(),
                truck_driver_cell.getText().toString(),
                truck_driver_village.getText().toString(),
                truck_driver_license_plate.getText().toString(),
                truck_driver_station.getText().toString());
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

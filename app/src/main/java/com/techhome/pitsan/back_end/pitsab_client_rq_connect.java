package com.techhome.pitsan.back_end;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by USER on 2/6/2017.
 */
public class pitsab_client_rq_connect {
    public static HttpURLConnection connect(String magicUrl, String client_phone_numbr,
                                            String client_full_names,
                                            String client_email,
                                            String client_province,
                                            String client_district,
                                            String client_sector,
                                            String client_cell,
                                            String client_village,
                                            String client_street,
                                            String client_house_number,
                                            String client_volume,
                                            String client_start_date,
                                            String client_end_date,
                                            String singlePitOrcrouwd) {

        try {

            URL url = new URL(magicUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            // Log.w("0000",""+Urubuga_Services.whichTypeOfData_Keys[Urubuga_Services.whichTypeOfData-1]);
            //--con properties-
            String data = "";
            data = URLEncoder.encode("client_phone_numbr", "UTF-8")
                    + "=" + URLEncoder.encode(client_phone_numbr, "UTF-8");
            data = data + "&" + URLEncoder.encode("client_full_names", "UTF-8")
                    + "=" + URLEncoder.encode(client_full_names, "UTF-8");
            data = data + "&" + URLEncoder.encode("client_email", "UTF-8")
                    + "=" + URLEncoder.encode(client_email, "UTF-8");
            data = data + "&" + URLEncoder.encode("client_district", "UTF-8")
                    + "=" + URLEncoder.encode(client_district, "UTF-8");

            data = data + "&" + URLEncoder.encode("client_province", "UTF-8")
                    + "=" + URLEncoder.encode(client_province, "UTF-8");
            data = data + "&" + URLEncoder.encode("client_sector", "UTF-8")
                    + "=" + URLEncoder.encode(client_sector, "UTF-8");
            data = data + "&" + URLEncoder.encode("client_cell", "UTF-8")
                    + "=" + URLEncoder.encode(client_cell, "UTF-8");
            data = data + "&" + URLEncoder.encode("client_village", "UTF-8")
                    + "=" + URLEncoder.encode(client_village, "UTF-8");
            data = data + "&" + URLEncoder.encode("client_street", "UTF-8")
                    + "=" + URLEncoder.encode(client_street, "UTF-8");

            data = data + "&" + URLEncoder.encode("client_house_number", "UTF-8")
                    + "=" + URLEncoder.encode(client_house_number, "UTF-8");
            data = data + "&" + URLEncoder.encode("client_volume", "UTF-8")
                    + "=" + URLEncoder.encode(client_volume, "UTF-8");
            data = data + "&" + URLEncoder.encode("client_start_date", "UTF-8")
                    + "=" + URLEncoder.encode(client_start_date, "UTF-8");
            data = data + "&" + URLEncoder.encode("client_end_date", "UTF-8")
                    + "=" + URLEncoder.encode(client_end_date, "UTF-8");
            data = data + "&" + URLEncoder.encode("singlePitOrcrouwd", "UTF-8")
                    + "=" + URLEncoder.encode(singlePitOrcrouwd, "UTF-8");


            String lastResort = data;
            String urlParameters = lastResort;
            con.setRequestMethod("POST");
            con.setConnectTimeout(15000);
            con.setReadTimeout(15000);
            con.setDoInput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();


            return con;


        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.w("netErroe", "null conn" + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.w("netErroe", "null conn" + e.toString());
        }
        return null;
    }
}

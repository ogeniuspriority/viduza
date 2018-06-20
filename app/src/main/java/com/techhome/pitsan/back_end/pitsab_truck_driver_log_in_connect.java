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
public class pitsab_truck_driver_log_in_connect {
    public static HttpURLConnection connect(String magicUrl, String username,
                                            String pasword) {

        try {

            URL url = new URL(magicUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            // Log.w("0000",""+Urubuga_Services.whichTypeOfData_Keys[Urubuga_Services.whichTypeOfData-1]);
            //--con properties-
            String data = "";
            data = URLEncoder.encode("email_or_username", "UTF-8")
                    + "=" + URLEncoder.encode(username, "UTF-8");
            data = data + "&" + URLEncoder.encode("inputPassword", "UTF-8")
                    + "=" + URLEncoder.encode(pasword, "UTF-8");

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

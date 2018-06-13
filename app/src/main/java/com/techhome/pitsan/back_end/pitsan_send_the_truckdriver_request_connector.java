package com.techhome.pitsan.back_end;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by USER on 2/6/2017.
 */
public class pitsan_send_the_truckdriver_request_connector {
    public static HttpURLConnection connect(String magicUrl, String truck_driver_phone_numbr,
                                            String truck_driver_full_names,
                                            String truck_driver_email,
                                            String truck_driver_province,
                                            String truck_driver_district,
                                            String truck_driver_sector,
                                            String truck_driver_cell,
                                            String truck_driver_village,
                                            String truck_driver_license_plate,
                                            String truck_driver_station) {

        try {

            URL url = new URL(magicUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            // Log.w("0000",""+Urubuga_Services.whichTypeOfData_Keys[Urubuga_Services.whichTypeOfData-1]);
            //--con properties-
            String data = "";
            data = URLEncoder.encode("truck_driver_phone_numbr", "UTF-8")
                    + "=" + URLEncoder.encode(truck_driver_phone_numbr, "UTF-8");
            data = data + "&" + URLEncoder.encode("truck_driver_full_names", "UTF-8")
                    + "=" + URLEncoder.encode(truck_driver_full_names, "UTF-8");
            data = data + "&" + URLEncoder.encode("truck_driver_email", "UTF-8")
                    + "=" + URLEncoder.encode(truck_driver_email, "UTF-8");
            data = data + "&" + URLEncoder.encode("truck_driver_province", "UTF-8")
                    + "=" + URLEncoder.encode(truck_driver_province, "UTF-8");

            data = data + "&" + URLEncoder.encode("truck_driver_district", "UTF-8")
                    + "=" + URLEncoder.encode(truck_driver_district, "UTF-8");
            data = data + "&" + URLEncoder.encode("truck_driver_sector", "UTF-8")
                    + "=" + URLEncoder.encode(truck_driver_sector, "UTF-8");
            data = data + "&" + URLEncoder.encode("truck_driver_cell", "UTF-8")
                    + "=" + URLEncoder.encode(truck_driver_cell, "UTF-8");
            data = data + "&" + URLEncoder.encode("truck_driver_village", "UTF-8")
                    + "=" + URLEncoder.encode(truck_driver_village, "UTF-8");
            data = data + "&" + URLEncoder.encode("truck_driver_license_plate", "UTF-8")
                    + "=" + URLEncoder.encode(truck_driver_license_plate, "UTF-8");
            data = data + "&" + URLEncoder.encode("truck_driver_station", "UTF-8")
                    + "=" + URLEncoder.encode(truck_driver_station, "UTF-8");


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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

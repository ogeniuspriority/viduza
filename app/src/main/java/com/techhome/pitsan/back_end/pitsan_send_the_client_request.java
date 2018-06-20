package com.techhome.pitsan.back_end;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.techhome.pitsan.LoginActivity;

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
    String singlePitOrcrouwd;
    //----------
    private ProgressDialog dialog;

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
                                          EditText client_end_date, String singlePitOrcrouwd) {
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
        this.singlePitOrcrouwd = singlePitOrcrouwd;
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
            if (jsonData.contains("REq Okay")) {
                String string = jsonData;
                final String[] parts = string.split("~");

                //------------
                final TextView message = new TextView(c);
                // i.e.: R.string.dialog_message =>
                // "Test this dialog following the link to dtmilano.blogspot.com"
                message.setTextSize(16.0f);
                String themsg = "Your request has been received, use this code  <" + parts[0] + "> to login on the login page and follow-up on your request.";
                final SpannableString s =
                        new SpannableString("Your request has been received, use this code  <" + parts[0] + "> to login on the login page and follow-up on your request.");
                s.setSpan(new StyleSpan(Typeface.BOLD), 0, 6,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ClickableSpan webClickSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(c, "haha", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(c, LoginActivity.class);
                        intent.putExtra("client_id", parts[0]);
                        c.startActivity(intent);

                    }
                };
                s.setSpan(webClickSpan, themsg.indexOf("<"), themsg.indexOf(">"), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                message.setText(s);
                message.setMovementMethod(LinkMovementMethod.getInstance());
                //------------

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(c);
                alertDialogBuilder.setTitle("From PITSAN LTD.");
                //alertDialogBuilder.setMessage("Your request has been received, use this code  <" + parts[0] + "> to login on the login page and follow-up on your request.").setCancelable(false);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.setCancelable(true);
                alertDialog.setView(message);
                alertDialog.show();
                //------------Clear the fields---------------------
                client_phone_numbr.setText("");
                client_full_names.setText("");
                client_email.setText("");
                client_cell.setText("");
                client_village.setText("");
                client_street.setText("");
                client_house_number.setText("");
                client_start_date.setText("");
                client_end_date.setText("");

            }

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
                client_end_date.getText().toString(), singlePitOrcrouwd);
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

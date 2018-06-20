package com.techhome.pitsan;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.techhome.pitsan.back_end.pitsan_send_the_client_request;
import com.techhome.pitsan.back_end.pitsan_send_the_truckdriver_request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    /**
     * fields
     */
    private static final int NUMBER_OF_PAGES = 2;
    private static String SAVED_BUNDLE_EXTRAS;
    private Spinner spinner;
    private View step1_view;
    private View step2_view;
    private View step3_view;
    private TextView agreement_text;
    private Button agreement_button;
    private ScrollView scrollView;
    private int selected;
    private Button register_truck;
    private Button register_client;
    //-------------------
    static Calendar myCalendar;
    Date date;
    String[] theAgreementsText = new String[]{"Crowdsourcing kdhsidhsdsidsbdishds sdishsdhds disd" +
            " dsudbsidsv dsidbvs dinsbvd smodjsbi dskdjhbs dm dsdsdi  dsudbsd " +
            " dsuydvsbdisgvud sdjhsbdsoodnbs dsojndsodnbisndsb sdisdvs dsihd" +
            "sdjuvs dsidbvs djsihbdsodhbisdsojdhbsdnib" +
            "dsjvd sdisbdv sdinbsvd sodbs djshibdsjdhnisidn sdusvf sjfubvsyfsihfu",

            "Single Pit kdhsidhsdsidsbdishds sdishsdhds disd" +
                    " dsudbsidsv dsidbvs dinsbvd smodjsbi dskdjhbs dm dsdsdi  dsudbsd " +
                    " dsuydvsbdisgvud sdjhsbdsoodnbs dsojndsodnbisndsb sdisdvs dsihd" +
                    "sdjuvs dsidbvs djsihbdsodhbisdsojdhbsdnib" +
                    "dsjvd sdisbdv sdinbsvd sodbs djshibdsjdhnisidn sdusvf sjfubvsyfsihfu",

            "Truck Driver Pit kdhsidhsdsidsbdishds sdishsdhds disd" +
                    " dsudbsidsv dsidbvs dinsbvd smodjsbi dskdjhbs dm dsdsdi  dsudbsd " +
                    " dsuydvsbdisgvud sdjhsbdsoodnbs dsojndsodnbisndsb sdisdvs dsihd" +
                    "sdjuvs dsidbvs djsihbdsodhbisdsojdhbsdnib" +
                    "dsjvd sdisbdv sdinbsvd sodbs djshibdsjdhnisidn sdusvf sjfubvsyfsihfu"};

    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    String thePositionChosen = "0";
    //----------------
    //--------Truck Drivers------------
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
    //-----------------pit emptying client
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
    //---------
    EditText client_start_date;
    EditText client_end_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Spinner element
        step1_view = findViewById(R.id.step1_layout);
        step2_view = findViewById(R.id.step2_layout);
        step3_view = findViewById(R.id.step3_layout);
        register_client = step3_view.findViewById(R.id.register_btn);
        register_truck = step2_view.findViewById(R.id.register_truck_btn);
        scrollView = step1_view.findViewById(R.id.scrollView1);
        spinner = step1_view.findViewById(R.id.spinner);
        agreement_text = step1_view.findViewById(R.id.agreementtext);
        agreement_button = step1_view.findViewById(R.id.agreementbtn);
        //---------
        //-----------------The crowdsourcing and single pit --------------------
        client_phone_numbr = findViewById(R.id.client_phone_numbr);
        client_full_names = findViewById(R.id.client_full_names);
        client_email = findViewById(R.id.client_email);
        client_province = findViewById(R.id.client_province);
        client_district = findViewById(R.id.client_district);
        client_sector = findViewById(R.id.client_sector);

        client_cell = findViewById(R.id.client_cell);
        client_village = findViewById(R.id.client_village);
        client_street = findViewById(R.id.client_street);
        client_house_number = findViewById(R.id.client_house_number);
        client_volume = findViewById(R.id.client_volume);
        //-------------
        //--------Truck Drivers------------
        truck_driver_phone_numbr = findViewById(R.id.truck_driver_phone_numbr);
        truck_driver_full_names = findViewById(R.id.truck_driver_full_names);
        truck_driver_email = findViewById(R.id.truck_driver_email);
        truck_driver_province = findViewById(R.id.truck_driver_province);
        truck_driver_district = findViewById(R.id.truck_driver_district);
        truck_driver_sector = findViewById(R.id.truck_driver_sector);
        truck_driver_cell = findViewById(R.id.truck_driver_cell);
        truck_driver_village = findViewById(R.id.truck_driver_village);
        truck_driver_street = findViewById(R.id.truck_driver_street);
        truck_driver_house_number = findViewById(R.id.truck_driver_house_number);
        truck_driver_volume = findViewById(R.id.truck_driver_volume);
        truck_driver_license_plate = findViewById(R.id.truck_driver_license_plate);
        truck_driver_station = findViewById(R.id.truck_driver_station);
        //---------------------------------


        client_start_date = findViewById(R.id.client_start_date);
        client_end_date = findViewById(R.id.client_end_date);
        client_start_date.setFocusable(false);
        client_start_date.setClickable(true);

        client_end_date.setFocusable(false);
        client_end_date.setClickable(true);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("CROWDSOURCING");
        categories.add("SINGLE PIT EMPTYING");
        categories.add("TRUCK OWNERS");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        //Log.w("currPos_tr", ""+SlideAdapter.currPos_tr);
        spinner.setSelection(SlideAdapter.currPos_tr);
        selected = SlideAdapter.currPos_tr;
        Log.w("currPos_tr", "" + SlideAdapter.currPos_tr);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        agreement_text.setText("" + theAgreementsText[position]);
                        break;
                    case 1:
                        agreement_text.setText("" + theAgreementsText[position]);
                        break;
                    case 2:
                        agreement_text.setText("" + theAgreementsText[position]);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //-----------select programmatically--
//--------East
        final List<String> east_districts = new ArrayList<String>();
        InputStream inputStream_east = getResources().openRawResource(R.raw.eastprovince_districts);
        BufferedReader bufferedReader_east = new BufferedReader(new InputStreamReader(inputStream_east));
        String eachline_east = null;
        try {
            eachline_east = bufferedReader_east.readLine();
            east_districts.add("" + eachline_east);
            do {

                // `the words in the file are separated by space`, so to get each words
                eachline_east = bufferedReader_east.readLine();
                if (eachline_east != null) {
                    east_districts.add("" + eachline_east);
                }

            } while (eachline_east != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//--West---
        final List<String> west_districts = new ArrayList<String>();
        InputStream inputStream_west = getResources().openRawResource(R.raw.westprovince_districts);
        BufferedReader bufferedReader_west = new BufferedReader(new InputStreamReader(inputStream_west));
        String eachline_west = null;
        try {
            eachline_west = bufferedReader_west.readLine();
            west_districts.add("" + eachline_west);
            do {

                // `the words in the file are separated by space`, so to get each words
                eachline_west = bufferedReader_west.readLine();
                if (eachline_west != null) {
                    west_districts.add("" + eachline_west);
                }

            } while (eachline_west != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-south--
        final List<String> south_districts = new ArrayList<String>();
        InputStream inputStream_south = getResources().openRawResource(R.raw.southprovince_districts);
        BufferedReader bufferedReader_south = new BufferedReader(new InputStreamReader(inputStream_south));
        String eachline_south = null;
        try {
            eachline_south = bufferedReader_south.readLine();
            south_districts.add("" + eachline_south);
            do {

                // `the words in the file are separated by space`, so to get each words
                eachline_south = bufferedReader_south.readLine();
                if (eachline_south != null) {
                    south_districts.add("" + eachline_south);
                }

            } while (eachline_south != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//---North---
        final List<String> north_districts = new ArrayList<String>();
        InputStream inputStream_north = getResources().openRawResource(R.raw.northprovinces_districts);
        BufferedReader bufferedReader_north = new BufferedReader(new InputStreamReader(inputStream_north));
        String eachline_north = null;
        try {
            eachline_north = bufferedReader_north.readLine();
            north_districts.add("" + eachline_north);
            do {
                // `the words in the file are separated by space`, so to get each words

                eachline_north = bufferedReader_north.readLine();
                if (eachline_north != null) {
                    north_districts.add("" + eachline_north);
                }

            } while (eachline_north != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//----------kigali---
        final List<String> kigali_districts = new ArrayList<String>();
        InputStream inputStream_kigali = getResources().openRawResource(R.raw.kigali_districts);
        BufferedReader bufferedReader_kigali = new BufferedReader(new InputStreamReader(inputStream_kigali));
        String eachline_kigali = null;
        try {
            eachline_kigali = bufferedReader_kigali.readLine();
            kigali_districts.add("" + eachline_kigali);
            do {

                // `the words in the file are separated by space`, so to get each words
                eachline_kigali = bufferedReader_kigali.readLine();
                if (eachline_kigali != null) {
                    kigali_districts.add("" + eachline_kigali);
                }

            } while (eachline_kigali != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-----All districts-------------
//----------Bugesera---
        final List<String> Bugesera_Sectors = new ArrayList<String>();
        InputStream inputStream_Bugesera_Sectors = getResources().openRawResource(R.raw.bugesera_sectors);
        BufferedReader bufferedReader_Bugesera_Sectors = new BufferedReader(new InputStreamReader(inputStream_Bugesera_Sectors));
        String eachline_Bugesera_Sectors = null;
        try {
            eachline_Bugesera_Sectors = bufferedReader_Bugesera_Sectors.readLine();
            Bugesera_Sectors.add("" + eachline_Bugesera_Sectors);
            do {


                // `the words in the file are separated by space`, so to get each words
                eachline_Bugesera_Sectors = bufferedReader_Bugesera_Sectors.readLine();
                if (eachline_Bugesera_Sectors != null) {
                    Bugesera_Sectors.add("" + eachline_Bugesera_Sectors);
                }

            } while (eachline_Bugesera_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Burera Sectors------------
        final List<String> Burera_Sectors = new ArrayList<String>();
        InputStream inputStream_Burera_Sectors = getResources().openRawResource(R.raw.burera_sectors);
        BufferedReader bufferedReader_Burera_Sectors = new BufferedReader(new InputStreamReader(inputStream_Burera_Sectors));
        String eachline_Burera_Sectors = null;
        try {
            eachline_Burera_Sectors = bufferedReader_Burera_Sectors.readLine();
            Burera_Sectors.add("" + eachline_Burera_Sectors);
            do {

                // `the words in the file are separated by space`, so to get each words
                eachline_Burera_Sectors = bufferedReader_kigali.readLine();
                if (eachline_Burera_Sectors != null) {
                    Burera_Sectors.add("" + eachline_Burera_Sectors);
                }

            } while (eachline_Burera_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Gakenke Sectors------------
        final List<String> Gakenke_Sectors = new ArrayList<String>();
        InputStream inputStream_Gakenke_Sectors = getResources().openRawResource(R.raw.gakenke_sectors);
        BufferedReader bufferedReader_Gakenke_Sectors = new BufferedReader(new InputStreamReader(inputStream_Gakenke_Sectors));
        String eachline_Gakenke_Sectors = null;
        try {
            eachline_Gakenke_Sectors = bufferedReader_Gakenke_Sectors.readLine();
            Gakenke_Sectors.add("" + eachline_Gakenke_Sectors);
            do {

                // `the words in the file are separated by space`, so to get each words
                eachline_Gakenke_Sectors = bufferedReader_Gakenke_Sectors.readLine();
                if (eachline_Gakenke_Sectors != null) {
                    Gakenke_Sectors.add("" + eachline_Gakenke_Sectors);
                }

            } while (eachline_Gakenke_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Gasabo Sectors-----------
        final List<String> Gasabo_Sectors = new ArrayList<String>();
        InputStream inputStream_Gasabo_Sectors = getResources().openRawResource(R.raw.gasabo_sectors);
        BufferedReader bufferedReader_Gasabo_Sectors = new BufferedReader(new InputStreamReader(inputStream_Gasabo_Sectors));
        String eachline_Gasabo_Sectors = null;
        try {
            eachline_Gasabo_Sectors = bufferedReader_Gasabo_Sectors.readLine();
            Gasabo_Sectors.add("" + eachline_Gasabo_Sectors);
            do {

                // `the words in the file are separated by space`, so to get each words
                eachline_Gasabo_Sectors = bufferedReader_Gasabo_Sectors.readLine();
                if (eachline_Gasabo_Sectors != null) {
                    Gasabo_Sectors.add("" + eachline_Gasabo_Sectors);
                }

            } while (eachline_Gasabo_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Gatsibo Sectors-----------
        final List<String> Gatsibo_Sectors = new ArrayList<String>();
        InputStream inputStream_Gatsibo_Sectors = getResources().openRawResource(R.raw.gatsibo_sectors);
        BufferedReader bufferedReader_Gatsibo_Sectors = new BufferedReader(new InputStreamReader(inputStream_Gatsibo_Sectors));
        String eachline_Gatsibo_Sectors = null;
        try {
            eachline_Gatsibo_Sectors = bufferedReader_Gatsibo_Sectors.readLine();
            Gatsibo_Sectors.add("" + eachline_Gatsibo_Sectors);
            do {

                // `the words in the file are separated by space`, so to get each words
                eachline_Gatsibo_Sectors = bufferedReader_Gatsibo_Sectors.readLine();
                if (eachline_Gatsibo_Sectors != null) {
                    Gatsibo_Sectors.add("" + eachline_Gatsibo_Sectors);
                }

            } while (eachline_Gatsibo_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Gicumbi Sectors-----------
        final List<String> Gicumbi_Sectors = new ArrayList<String>();
        InputStream inputStream_Gicumbi_Sectors = getResources().openRawResource(R.raw.gicumbi_sectors);
        BufferedReader bufferedReader_Gicumbi_Sectors = new BufferedReader(new InputStreamReader(inputStream_Gicumbi_Sectors));
        String eachline_Gicumbi_Sectors = null;
        try {
            eachline_Gicumbi_Sectors = bufferedReader_Gicumbi_Sectors.readLine();
            Gicumbi_Sectors.add("" + eachline_Gicumbi_Sectors);
            do {

                // `the words in the file are separated by space`, so to get each words
                eachline_Gicumbi_Sectors = bufferedReader_Gicumbi_Sectors.readLine();
                if (eachline_Gicumbi_Sectors != null) {
                    Gicumbi_Sectors.add("" + eachline_Gicumbi_Sectors);
                }

            } while (eachline_Gicumbi_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Gisagara Sectors-----------
        final List<String> Gisagara_Sectors = new ArrayList<String>();
        InputStream inputStream_Gisagara_Sectors = getResources().openRawResource(R.raw.gisagara_sectors);
        BufferedReader bufferedReader_Gisagara_Sectors = new BufferedReader(new InputStreamReader(inputStream_Gisagara_Sectors));
        String eachline_Gisagara_Sectors = null;
        try {
            eachline_Gisagara_Sectors = bufferedReader_Gisagara_Sectors.readLine();
            Gisagara_Sectors.add("" + eachline_Gisagara_Sectors);
            do {

                // `the words in the file are separated by space`, so to get each words
                eachline_Gisagara_Sectors = bufferedReader_Gisagara_Sectors.readLine();
                if (eachline_Gisagara_Sectors != null) {
                    Gisagara_Sectors.add("" + eachline_Gisagara_Sectors);
                }

            } while (eachline_Gisagara_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Huye Sectors-----------
        final List<String> Huye_Sectors = new ArrayList<String>();
        InputStream inputStream_Huye_Sectors = getResources().openRawResource(R.raw.huye_sectors);
        BufferedReader bufferedReader_Huye_Sectors = new BufferedReader(new InputStreamReader(inputStream_Huye_Sectors));
        String eachline_Huye_Sectors = null;
        try {
            eachline_Huye_Sectors = bufferedReader_Huye_Sectors.readLine();
            Huye_Sectors.add("" + eachline_Huye_Sectors);
            do {

                // `the words in the file are separated by space`, so to get each words
                eachline_Huye_Sectors = bufferedReader_Huye_Sectors.readLine();
                if (eachline_Huye_Sectors != null) {
                    Huye_Sectors.add("" + eachline_Huye_Sectors);
                }

            } while (eachline_Huye_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Kamonyi Sectors-----------
        final List<String> Kamonyi_Sectors = new ArrayList<String>();
        InputStream inputStream_Kamonyi_Sectors = getResources().openRawResource(R.raw.kamonyi_sectors);
        BufferedReader bufferedReader_Kamonyi_Sectors = new BufferedReader(new InputStreamReader(inputStream_Kamonyi_Sectors));
        String eachline_Kamonyi_Sectors = null;
        try {
            eachline_Kamonyi_Sectors = bufferedReader_Kamonyi_Sectors.readLine();
            Kamonyi_Sectors.add("" + eachline_Kamonyi_Sectors);
            do {

                // `the words in the file are separated by space`, so to get each words
                eachline_Kamonyi_Sectors = bufferedReader_Kamonyi_Sectors.readLine();
                if (eachline_Huye_Sectors != null) {
                    Kamonyi_Sectors.add("" + eachline_Kamonyi_Sectors);
                }

            } while (eachline_Kamonyi_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Karongi Sectors-----------
        final List<String> Karongi_Sectors = new ArrayList<String>();
        InputStream inputStream_Karongi_Sectors = getResources().openRawResource(R.raw.karongi_sectors);
        BufferedReader bufferedReader_Karongi_Sectors = new BufferedReader(new InputStreamReader(inputStream_Karongi_Sectors));
        String eachline_Karongi_Sectors = null;
        try {
            eachline_Karongi_Sectors = bufferedReader_Karongi_Sectors.readLine();
            Karongi_Sectors.add("" + eachline_Karongi_Sectors);
            do {

                // `the words in the file are separated by space`, so to get each words
                eachline_Karongi_Sectors = bufferedReader_Karongi_Sectors.readLine();
                if (eachline_Karongi_Sectors != null) {
                    Karongi_Sectors.add("" + eachline_Karongi_Sectors);
                }

            } while (eachline_Karongi_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }

//-------Kayonza Sectors-----------
        final List<String> Kayonza_Sectors = new ArrayList<String>();
        InputStream inputStream_Kayonza_Sectors = getResources().openRawResource(R.raw.kayonza_sectors);
        BufferedReader bufferedReader_Kayonza_Sectors = new BufferedReader(new InputStreamReader(inputStream_Kayonza_Sectors));
        String eachline_Kayonza_Sectors = null;
        try {
            eachline_Kayonza_Sectors = bufferedReader_Kayonza_Sectors.readLine();
            Kayonza_Sectors.add("" + eachline_Kayonza_Sectors);
            do {

                // `the words in the file are separated by space`, so to get each words
                eachline_Kayonza_Sectors = bufferedReader_Kayonza_Sectors.readLine();
                if (eachline_Kayonza_Sectors != null) {
                    Kayonza_Sectors.add("" + eachline_Kayonza_Sectors);
                }

            } while (eachline_Kayonza_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Kicukiro Sectors-----------
        final List<String> Kicukiro_Sectors = new ArrayList<String>();
        InputStream inputStream_Kicukiro_Sectors = getResources().openRawResource(R.raw.kicukiro_sectors);
        BufferedReader bufferedReader_Kicukiro_Sectors = new BufferedReader(new InputStreamReader(inputStream_Kicukiro_Sectors));
        String eachline_Kicukiro_Sectors = null;
        try {
            eachline_Kicukiro_Sectors = bufferedReader_Kicukiro_Sectors.readLine();
            Kicukiro_Sectors.add("" + eachline_Kicukiro_Sectors);
            do {

                // `the words in the file are separated by space`, so to get each words
                eachline_Kicukiro_Sectors = bufferedReader_Kicukiro_Sectors.readLine();
                if (eachline_Kicukiro_Sectors != null) {
                    Kicukiro_Sectors.add("" + eachline_Kicukiro_Sectors);
                }

            } while (eachline_Kicukiro_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Kirehe Sectors-----------
        final List<String> Kirehe_Sectors = new ArrayList<String>();
        InputStream inputStream_Kirehe_Sectors = getResources().openRawResource(R.raw.kirehe_sectors);
        BufferedReader bufferedReader_Kirehe_Sectors = new BufferedReader(new InputStreamReader(inputStream_Kirehe_Sectors));
        String eachline_Kirehe_Sectors = null;
        try {
            eachline_Kirehe_Sectors = bufferedReader_Kirehe_Sectors.readLine();
            Kirehe_Sectors.add("" + eachline_Kirehe_Sectors);
            do {

                // `the words in the file are separated by space`, so to get each words
                eachline_Kirehe_Sectors = bufferedReader_Kirehe_Sectors.readLine();
                if (eachline_Kirehe_Sectors != null) {
                    Kirehe_Sectors.add("" + eachline_Kirehe_Sectors);
                }

            } while (eachline_Kirehe_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Muhanga Sectors-----------
        final List<String> Muhanga_Sectors = new ArrayList<String>();
        InputStream inputStream_Muhanga_Sectors = getResources().openRawResource(R.raw.muhanga_sectors);
        BufferedReader bufferedReader_Muhanga_Sectors = new BufferedReader(new InputStreamReader(inputStream_Muhanga_Sectors));
        String eachline_Muhanga_Sectors = null;
        try {
            eachline_Muhanga_Sectors = bufferedReader_Muhanga_Sectors.readLine();
            Muhanga_Sectors.add("" + eachline_Muhanga_Sectors);
            do {

                // `the words in the file are separated by space`, so to get each words
                eachline_Muhanga_Sectors = bufferedReader_Muhanga_Sectors.readLine();
                if (eachline_Muhanga_Sectors != null) {
                    Muhanga_Sectors.add("" + eachline_Muhanga_Sectors);
                }

            } while (eachline_Muhanga_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }

//-------Musanze Sectors-----------
        final List<String> Musanze_Sectors = new ArrayList<String>();
        InputStream inputStream_Musanze_Sectors = getResources().openRawResource(R.raw.musanze_sectors);
        BufferedReader bufferedReader_Musanze_Sectors = new BufferedReader(new InputStreamReader(inputStream_Musanze_Sectors));
        String eachline_Musanze_Sectors = null;
        try {
            eachline_Musanze_Sectors = bufferedReader_Musanze_Sectors.readLine();
            Musanze_Sectors.add("" + eachline_Musanze_Sectors);
            do {

                // `the words in the file are separated by space`, so to get each words
                eachline_Musanze_Sectors = bufferedReader_Musanze_Sectors.readLine();
                if (eachline_Musanze_Sectors != null) {
                    Musanze_Sectors.add("" + eachline_Musanze_Sectors);
                }

            } while (eachline_Musanze_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Ngoma Sectors-----------
        final List<String> Ngoma_Sectors = new ArrayList<String>();
        InputStream inputStream_Ngoma_Sectors = getResources().openRawResource(R.raw.ngoma_sectors);
        BufferedReader bufferedReader_Ngoma_Sectors = new BufferedReader(new InputStreamReader(inputStream_Ngoma_Sectors));
        String eachline_Ngoma_Sectors = null;
        try {
            eachline_Ngoma_Sectors = bufferedReader_Ngoma_Sectors.readLine();
            Ngoma_Sectors.add("" + eachline_Ngoma_Sectors);
            do {

                // `the words in the file are separated by space`, so to get each words
                eachline_Ngoma_Sectors = bufferedReader_Ngoma_Sectors.readLine();
                if (eachline_Ngoma_Sectors != null) {
                    Ngoma_Sectors.add("" + eachline_Ngoma_Sectors);
                }

            } while (eachline_Ngoma_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Ngororero Sectors-----------
        final List<String> Ngororero_Sectors = new ArrayList<String>();
        InputStream inputStream_Ngororero_Sectors = getResources().openRawResource(R.raw.ngororero_sectors);
        BufferedReader bufferedReader_Ngororero_Sectors = new BufferedReader(new InputStreamReader(inputStream_Ngororero_Sectors));
        String eachline_Ngororero_Sectors = null;
        try {
            eachline_Ngororero_Sectors = bufferedReader_Ngororero_Sectors.readLine();
            Ngororero_Sectors.add("" + eachline_Ngororero_Sectors);
            do {
                // `the words in the file are separated by space`, so to get each words
                eachline_Ngororero_Sectors = bufferedReader_Ngororero_Sectors.readLine();
                if (eachline_Ngororero_Sectors != null) {
                    Ngororero_Sectors.add("" + eachline_Ngororero_Sectors);
                }

            } while (eachline_Ngororero_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Nyabihu Sectors-----------
        final List<String> Nyabihu_Sectors = new ArrayList<String>();
        InputStream inputStream_Nyabihu_Sectors = getResources().openRawResource(R.raw.nyabihu_sectors);
        BufferedReader bufferedReader_Nyabihu_Sectors = new BufferedReader(new InputStreamReader(inputStream_Nyabihu_Sectors));
        String eachline_Nyabihu_Sectors = null;
        try {
            eachline_Nyabihu_Sectors = bufferedReader_Nyabihu_Sectors.readLine();
            Nyabihu_Sectors.add("" + eachline_Nyabihu_Sectors);
            do {
                // `the words in the file are separated by space`, so to get each words
                eachline_Nyabihu_Sectors = bufferedReader_Nyabihu_Sectors.readLine();
                if (eachline_Nyabihu_Sectors != null) {
                    Nyabihu_Sectors.add("" + eachline_Nyabihu_Sectors);
                }
            } while (eachline_Nyabihu_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Nyagatare Sectors-----------
        final List<String> Nyagatare_Sectors = new ArrayList<String>();
        InputStream inputStream_Nyagatare_Sectors = getResources().openRawResource(R.raw.nyagatare_sectors);
        BufferedReader bufferedReader_Nyagatare_Sectors = new BufferedReader(new InputStreamReader(inputStream_Nyagatare_Sectors));
        String eachline_Nyagatare_Sectors = null;
        try {
            eachline_Nyagatare_Sectors = bufferedReader_Nyagatare_Sectors.readLine();
            Nyagatare_Sectors.add("" + eachline_Nyagatare_Sectors);
            do {
                // `the words in the file are separated by space`, so to get each words
                eachline_Nyagatare_Sectors = bufferedReader_Nyagatare_Sectors.readLine();
                if (eachline_Nyagatare_Sectors != null) {
                    Nyagatare_Sectors.add("" + eachline_Nyagatare_Sectors);
                }
            } while (eachline_Nyagatare_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Nyamagabe Sectors-----------
        final List<String> Nyamagabe_Sectors = new ArrayList<String>();
        InputStream inputStream_Nyamagabe_Sectors = getResources().openRawResource(R.raw.nyamagabe_sectors);
        BufferedReader bufferedReader_Nyamagabe_Sectors = new BufferedReader(new InputStreamReader(inputStream_Nyamagabe_Sectors));
        String eachline_Nyamagabe_Sectors = null;
        try {
            eachline_Nyamagabe_Sectors = bufferedReader_Nyamagabe_Sectors.readLine();
            Nyamagabe_Sectors.add("" + eachline_Nyamagabe_Sectors);
            do {
                // `the words in the file are separated by space`, so to get each words
                eachline_Nyamagabe_Sectors = bufferedReader_Nyamagabe_Sectors.readLine();
                if (eachline_Nyamagabe_Sectors != null) {
                    Nyamagabe_Sectors.add("" + eachline_Nyamagabe_Sectors);
                }
            } while (eachline_Nyamagabe_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Nyamasheke Sectors-----------
        final List<String> Nyamasheke_Sectors = new ArrayList<String>();
        InputStream inputStream_Nyamasheke_Sectors = getResources().openRawResource(R.raw.nyamasheke_sectors);
        BufferedReader bufferedReader_Nyamasheke_Sectors = new BufferedReader(new InputStreamReader(inputStream_Nyamasheke_Sectors));
        String eachline_Nyamasheke_Sectors = null;
        try {
            eachline_Nyamasheke_Sectors = bufferedReader_Nyamasheke_Sectors.readLine();
            Nyamasheke_Sectors.add("" + eachline_Nyamasheke_Sectors);
            do {
                // `the words in the file are separated by space`, so to get each words
                eachline_Nyamasheke_Sectors = bufferedReader_Nyamasheke_Sectors.readLine();
                if (eachline_Nyamasheke_Sectors != null) {
                    Nyamasheke_Sectors.add("" + eachline_Nyamasheke_Sectors);
                }
            } while (eachline_Nyamasheke_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Nyanza Sectors-----------
        final List<String> Nyanza_Sectors = new ArrayList<String>();
        InputStream inputStream_Nyanza_Sectors = getResources().openRawResource(R.raw.nyanza_sectors);
        BufferedReader bufferedReader_Nyanza_Sectors = new BufferedReader(new InputStreamReader(inputStream_Nyanza_Sectors));
        String eachline_Nyanza_Sectors = null;
        try {
            eachline_Nyanza_Sectors = bufferedReader_Nyanza_Sectors.readLine();
            Nyanza_Sectors.add("" + eachline_Nyanza_Sectors);
            do {
                // `the words in the file are separated by space`, so to get each words
                eachline_Nyanza_Sectors = bufferedReader_Nyanza_Sectors.readLine();
                if (eachline_Nyanza_Sectors != null) {
                    Nyanza_Sectors.add("" + eachline_Nyanza_Sectors);
                }
            } while (eachline_Nyanza_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Nyarugenge Sectors-----------
        final List<String> Nyarugenge_Sectors = new ArrayList<String>();
        InputStream inputStream_Nyarugenge_Sectors = getResources().openRawResource(R.raw.nyarugenge_sectors);
        BufferedReader bufferedReader_Nyarugenge_Sectors = new BufferedReader(new InputStreamReader(inputStream_Nyarugenge_Sectors));
        String eachline_Nyarugenge_Sectors = null;
        try {
            eachline_Nyarugenge_Sectors = bufferedReader_Nyarugenge_Sectors.readLine();
            Nyarugenge_Sectors.add("" + eachline_Nyarugenge_Sectors);
            do {
                // `the words in the file are separated by space`, so to get each words
                eachline_Nyarugenge_Sectors = bufferedReader_Nyarugenge_Sectors.readLine();
                if (eachline_Nyarugenge_Sectors != null) {
                    Nyarugenge_Sectors.add("" + eachline_Nyarugenge_Sectors);
                }
            } while (eachline_Nyarugenge_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Nyaruguru Sectors-----------
        final List<String> Nyaruguru_Sectors = new ArrayList<String>();
        InputStream inputStream_Nyaruguru_Sectors = getResources().openRawResource(R.raw.nyaruguru_sectors);
        BufferedReader bufferedReader_Nyaruguru_Sectors = new BufferedReader(new InputStreamReader(inputStream_Nyaruguru_Sectors));
        String eachline_Nyaruguru_Sectors = null;
        try {
            eachline_Nyaruguru_Sectors = bufferedReader_Nyaruguru_Sectors.readLine();
            Nyaruguru_Sectors.add("" + eachline_Nyaruguru_Sectors);
            do {
                // `the words in the file are separated by space`, so to get each words
                eachline_Nyaruguru_Sectors = bufferedReader_Nyaruguru_Sectors.readLine();
                if (eachline_Nyaruguru_Sectors != null) {
                    Nyaruguru_Sectors.add("" + eachline_Nyaruguru_Sectors);
                }
            } while (eachline_Nyaruguru_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Rubavu Sectors-----------
        final List<String> Rubavu_Sectors = new ArrayList<String>();
        InputStream inputStream_Rubavu_Sectors = getResources().openRawResource(R.raw.rubavu_sectors);
        BufferedReader bufferedReader_Rubavu_Sectors = new BufferedReader(new InputStreamReader(inputStream_Rubavu_Sectors));
        String eachline_Rubavu_Sectors = null;
        try {
            eachline_Rubavu_Sectors = bufferedReader_Rubavu_Sectors.readLine();
            Rubavu_Sectors.add("" + eachline_Rubavu_Sectors);
            do {
                // `the words in the file are separated by space`, so to get each words
                eachline_Rubavu_Sectors = bufferedReader_Rubavu_Sectors.readLine();
                if (eachline_Rubavu_Sectors != null) {
                    Rubavu_Sectors.add("" + eachline_Rubavu_Sectors);
                }
            } while (eachline_Rubavu_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Ruhango Sectors-----------
        final List<String> Ruhango_Sectors = new ArrayList<String>();
        InputStream inputStream_Ruhango_Sectors = getResources().openRawResource(R.raw.ruhango_sectors);
        BufferedReader bufferedReader_Ruhango_Sectors = new BufferedReader(new InputStreamReader(inputStream_Ruhango_Sectors));
        String eachline_Ruhango_Sectors = null;
        try {
            eachline_Ruhango_Sectors = bufferedReader_Ruhango_Sectors.readLine();
            Ruhango_Sectors.add("" + eachline_Ruhango_Sectors);
            do {
                // `the words in the file are separated by space`, so to get each words
                eachline_Ruhango_Sectors = bufferedReader_Ruhango_Sectors.readLine();
                if (eachline_Ruhango_Sectors != null) {
                    Ruhango_Sectors.add("" + eachline_Ruhango_Sectors);
                }
            } while (eachline_Ruhango_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Rulindo Sectors-----------
        final List<String> Rulindo_Sectors = new ArrayList<String>();
        InputStream inputStream_Rulindo_Sectors = getResources().openRawResource(R.raw.rulindo_sectors);
        BufferedReader bufferedReader_Rulindo_Sectors = new BufferedReader(new InputStreamReader(inputStream_Rulindo_Sectors));
        String eachline_Rulindo_Sectors = null;
        try {
            eachline_Rulindo_Sectors = bufferedReader_Rulindo_Sectors.readLine();
            Rulindo_Sectors.add("" + eachline_Rulindo_Sectors);
            do {
                // `the words in the file are separated by space`, so to get each words
                eachline_Rulindo_Sectors = bufferedReader_Rulindo_Sectors.readLine();
                if (eachline_Rulindo_Sectors != null) {
                    Rulindo_Sectors.add("" + eachline_Rulindo_Sectors);
                }
            } while (eachline_Rulindo_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Rusizi Sectors-----------
        final List<String> Rusizi_Sectors = new ArrayList<String>();
        InputStream inputStream_Rusizi_Sectors = getResources().openRawResource(R.raw.rusizi_sectors);
        BufferedReader bufferedReader_Rusizi_Sectors = new BufferedReader(new InputStreamReader(inputStream_Rusizi_Sectors));
        String eachline_Rusizi_Sectors = null;
        try {
            eachline_Rusizi_Sectors = bufferedReader_Rusizi_Sectors.readLine();
            Rusizi_Sectors.add("" + eachline_Rusizi_Sectors);
            do {
                // `the words in the file are separated by space`, so to get each words
                eachline_Rusizi_Sectors = bufferedReader_Rusizi_Sectors.readLine();
                if (eachline_Rusizi_Sectors != null) {
                    Rusizi_Sectors.add("" + eachline_Rusizi_Sectors);
                }
            } while (eachline_Rusizi_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Rutsiro Sectors-----------
        final List<String> Rutsiro_Sectors = new ArrayList<String>();
        InputStream inputStream_Rutsiro_Sectors = getResources().openRawResource(R.raw.rutsiro_sectors);
        BufferedReader bufferedReader_Rutsiro_Sectors = new BufferedReader(new InputStreamReader(inputStream_Rutsiro_Sectors));
        String eachline_Rutsiro_Sectors = null;
        try {
            eachline_Rutsiro_Sectors = bufferedReader_Rutsiro_Sectors.readLine();
            Rutsiro_Sectors.add("" + eachline_Rutsiro_Sectors);
            do {
                // `the words in the file are separated by space`, so to get each words
                eachline_Rutsiro_Sectors = bufferedReader_Rutsiro_Sectors.readLine();
                if (eachline_Rutsiro_Sectors != null) {
                    Rutsiro_Sectors.add("" + eachline_Rutsiro_Sectors);
                }
            } while (eachline_Rutsiro_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Rwamagana Sectors-----------
        final List<String> Rwamagana_Sectors = new ArrayList<String>();
        InputStream inputStream_Rwamagana_Sectors = getResources().openRawResource(R.raw.rwamagana_sectors);
        BufferedReader bufferedReader_Rwamagana_Sectors = new BufferedReader(new InputStreamReader(inputStream_Rwamagana_Sectors));
        String eachline_Rwamagana_Sectors = null;
        try {
            eachline_Rwamagana_Sectors = bufferedReader_Rwamagana_Sectors.readLine();
            Rwamagana_Sectors.add("" + eachline_Rwamagana_Sectors);
            do {
                // `the words in the file are separated by space`, so to get each words
                eachline_Rwamagana_Sectors = bufferedReader_Rwamagana_Sectors.readLine();
                if (eachline_Rwamagana_Sectors != null) {
                    Rwamagana_Sectors.add("" + eachline_Rwamagana_Sectors);
                }
            } while (eachline_Rwamagana_Sectors != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//----------The Provinces ---
        List<String> The_provinces = new ArrayList<String>();
        The_provinces.add("Kigali");
        The_provinces.add("North Province");
        The_provinces.add("South Province");
        The_provinces.add("West Province");
        The_provinces.add("Eastern Province");
        //----------For step one Client  ------
        //--------populate the provinces--
        String[] The_provinces_array = new String[The_provinces.size()];
        The_provinces_array = The_provinces.toArray(The_provinces_array);
        ArrayAdapter<String> The_provinces_array_Adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, The_provinces_array);
        The_provinces_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        client_province.setAdapter(The_provinces_array_Adapter);
        //------------For the districts---
        String[] kigali_districts_array = new String[kigali_districts.size()];
        kigali_districts_array = kigali_districts.toArray(kigali_districts_array);
        final ArrayAdapter<String> kigali_districts_array_Adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, kigali_districts_array);
        kigali_districts_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //-----------------------------
        String[] Gasabo_Sectors_array = new String[Gasabo_Sectors.size()];
        Gasabo_Sectors_array = Gasabo_Sectors.toArray(Gasabo_Sectors_array);
        final ArrayAdapter<String> Gasabo_Sectors_array_Adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, Gasabo_Sectors_array);
        Gasabo_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        client_province.setAdapter(The_provinces_array_Adapter);
        client_district.setAdapter(kigali_districts_array_Adapter);
        client_sector.setAdapter(Gasabo_Sectors_array_Adapter);

        client_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                String selectedItem = client_province.getSelectedItem().toString();
                if (selectedItem.contains("Kigali")) {
                    String[] kigali_districts_array_ = new String[kigali_districts.size()];
                    kigali_districts_array_ = kigali_districts.toArray(kigali_districts_array_);
                    ArrayAdapter<String> kigali_districts_array_Adapter_ = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, kigali_districts_array_);
                    kigali_districts_array_Adapter_.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_district.setAdapter(kigali_districts_array_Adapter_);

                } else if (selectedItem.contains("North Province")) {
                    String[] north_districts_array_ = new String[north_districts.size()];
                    north_districts_array_ = north_districts.toArray(north_districts_array_);
                    ArrayAdapter<String> north_districts_array_Adapter_ = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, north_districts_array_);
                    north_districts_array_Adapter_.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_district.setAdapter(north_districts_array_Adapter_);
                } else if (selectedItem.contains("South Province")) {
                    String[] south_districts_array_ = new String[south_districts.size()];
                    south_districts_array_ = south_districts.toArray(south_districts_array_);
                    ArrayAdapter<String> south_districts_array_Adapter_ = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, south_districts_array_);
                    south_districts_array_Adapter_.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_district.setAdapter(south_districts_array_Adapter_);
                } else if (selectedItem.contains("West Province")) {
                    String[] west_districts_array_ = new String[west_districts.size()];
                    west_districts_array_ = west_districts.toArray(west_districts_array_);
                    ArrayAdapter<String> west_districts_array_Adapter_ = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, west_districts_array_);
                    west_districts_array_Adapter_.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_district.setAdapter(west_districts_array_Adapter_);
                } else if (selectedItem.contains("Eastern Province")) {
                    String[] east_districts_array_ = new String[east_districts.size()];
                    east_districts_array_ = east_districts.toArray(east_districts_array_);
                    ArrayAdapter<String> east_districts_array_Adapter_ = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, east_districts_array_);
                    east_districts_array_Adapter_.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_district.setAdapter(east_districts_array_Adapter_);
                }
                ///---------------------------
                String selectedItem_district = client_district.getSelectedItem().toString();
                if (selectedItem_district.contains("Karongi")) {
                    String[] Karongi_Sectors_array = new String[Karongi_Sectors.size()];
                    Karongi_Sectors_array = Karongi_Sectors.toArray(Karongi_Sectors_array);
                    ArrayAdapter<String> Karongi_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Karongi_Sectors_array);
                    Karongi_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Karongi_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Ngororero")) {
                    String[] Ngororero_Sectors_array = new String[Ngororero_Sectors.size()];
                    Ngororero_Sectors_array = Ngororero_Sectors.toArray(Ngororero_Sectors_array);
                    ArrayAdapter<String> Ngororero_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Ngororero_Sectors_array);
                    Ngororero_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Ngororero_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Nyabihu")) {
                    String[] Nyabihu_Sectors_array = new String[Nyabihu_Sectors.size()];
                    Nyabihu_Sectors_array = Nyabihu_Sectors.toArray(Nyabihu_Sectors_array);
                    ArrayAdapter<String> Nyabihu_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyabihu_Sectors_array);
                    Nyabihu_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Nyabihu_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Nyamasheke")) {
                    String[] Nyamasheke_Sectors_array = new String[Nyamasheke_Sectors.size()];
                    Nyamasheke_Sectors_array = Nyamasheke_Sectors.toArray(Nyamasheke_Sectors_array);
                    ArrayAdapter<String> Nyamasheke_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyamasheke_Sectors_array);
                    Nyamasheke_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Nyamasheke_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Rubavu")) {
                    String[] Rubavu_Sectors_array = new String[Rubavu_Sectors.size()];
                    Rubavu_Sectors_array = Nyamasheke_Sectors.toArray(Rubavu_Sectors_array);
                    ArrayAdapter<String> Rubavu_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Rubavu_Sectors_array);
                    Rubavu_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Rubavu_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Rutsiro")) {
                    String[] Rutsiro_Sectors_array = new String[Rutsiro_Sectors.size()];
                    Rutsiro_Sectors_array = Nyamasheke_Sectors.toArray(Rutsiro_Sectors_array);
                    ArrayAdapter<String> Rutsiro_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Rutsiro_Sectors_array);
                    Rutsiro_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Rutsiro_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Rusizi")) {
                    String[] Rusizi_array = new String[Rusizi_Sectors.size()];
                    Rusizi_array = Nyamasheke_Sectors.toArray(Rusizi_array);
                    ArrayAdapter<String> Rusizi_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Rusizi_array);
                    Rusizi_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Rusizi_array_Adapter);

                } else if (selectedItem_district.contains("Gisagara")) {
                    String[] Gisagara_Sectors_array = new String[Gisagara_Sectors.size()];
                    Gisagara_Sectors_array = Gisagara_Sectors.toArray(Gisagara_Sectors_array);
                    ArrayAdapter<String> Gisagara_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Gisagara_Sectors_array);
                    Gisagara_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Gisagara_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Huye")) {
                    String[] Huye_Sectors_array = new String[Huye_Sectors.size()];
                    Huye_Sectors_array = Huye_Sectors.toArray(Huye_Sectors_array);
                    ArrayAdapter<String> Huye_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Huye_Sectors_array);
                    Huye_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Huye_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Kamonyi")) {
                    String[] Kamonyi_Sectors_array = new String[Kamonyi_Sectors.size()];
                    Kamonyi_Sectors_array = Kamonyi_Sectors.toArray(Kamonyi_Sectors_array);
                    ArrayAdapter<String> Kamonyi_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Kamonyi_Sectors_array);
                    Kamonyi_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Kamonyi_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Muhanga")) {
                    String[] Muhanga_Sectors_array = new String[Muhanga_Sectors.size()];
                    Muhanga_Sectors_array = Muhanga_Sectors.toArray(Muhanga_Sectors_array);
                    ArrayAdapter<String> Muhanga_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Muhanga_Sectors_array);
                    Muhanga_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Muhanga_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Nyamagabe")) {
                    String[] Nyamagabe_Sectors_array = new String[Nyamagabe_Sectors.size()];
                    Nyamagabe_Sectors_array = Nyamagabe_Sectors.toArray(Nyamagabe_Sectors_array);
                    ArrayAdapter<String> Nyamagabe_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyamagabe_Sectors_array);
                    Nyamagabe_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Nyamagabe_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Nyanza")) {
                    String[] Nyanza_Sectors_array = new String[Nyanza_Sectors.size()];
                    Nyanza_Sectors_array = Nyanza_Sectors.toArray(Nyanza_Sectors_array);
                    ArrayAdapter<String> Nyanza_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyanza_Sectors_array);
                    Nyanza_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Nyanza_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Nyaruguru")) {
                    String[] Nyaruguru_Sectors_array = new String[Nyaruguru_Sectors.size()];
                    Nyaruguru_Sectors_array = Nyaruguru_Sectors.toArray(Nyaruguru_Sectors_array);
                    ArrayAdapter<String> Nyaruguru_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyaruguru_Sectors_array);
                    Nyaruguru_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Nyaruguru_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Ruhango")) {
                    String[] Ruhango_Sectors_array = new String[Ruhango_Sectors.size()];
                    Ruhango_Sectors_array = Ruhango_Sectors.toArray(Ruhango_Sectors_array);
                    ArrayAdapter<String> Ruhango_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Ruhango_Sectors_array);
                    Ruhango_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Ruhango_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Burera")) {
                    String[] Burera_Sectors_array = new String[Burera_Sectors.size()];
                    Burera_Sectors_array = Burera_Sectors.toArray(Burera_Sectors_array);
                    ArrayAdapter<String> Burera_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Burera_Sectors_array);
                    Burera_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Burera_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Gicumbi")) {
                    String[] Gicumbi_Sectors_array = new String[Gicumbi_Sectors.size()];
                    Gicumbi_Sectors_array = Gicumbi_Sectors.toArray(Gicumbi_Sectors_array);
                    ArrayAdapter<String> Gicumbi_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Gicumbi_Sectors_array);
                    Gicumbi_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Gicumbi_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Gakenke")) {
                    String[] Gakenke_Sectors_array = new String[Gakenke_Sectors.size()];
                    Gakenke_Sectors_array = Gakenke_Sectors.toArray(Gakenke_Sectors_array);
                    ArrayAdapter<String> Gakenke_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Gakenke_Sectors_array);
                    Gakenke_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Gakenke_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Musanze")) {
                    String[] Musanze_Sectors_array = new String[Musanze_Sectors.size()];
                    Musanze_Sectors_array = Musanze_Sectors.toArray(Musanze_Sectors_array);
                    ArrayAdapter<String> Musanze_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Musanze_Sectors_array);
                    Musanze_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Musanze_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Rulindo")) {
                    String[] Rulindo_Sectors_array = new String[Rulindo_Sectors.size()];
                    Rulindo_Sectors_array = Rulindo_Sectors.toArray(Rulindo_Sectors_array);
                    ArrayAdapter<String> Rulindo_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Rulindo_Sectors_array);
                    Rulindo_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Rulindo_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Gasabo")) {
                    String[] Gasabo_Sectors_array = new String[Gasabo_Sectors.size()];
                    Gasabo_Sectors_array = Gasabo_Sectors.toArray(Gasabo_Sectors_array);
                    ArrayAdapter<String> Gasabo_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Gasabo_Sectors_array);
                    Gasabo_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Gasabo_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Kicukiro")) {
                    String[] Kicukiro_Sectors_array = new String[Kicukiro_Sectors.size()];
                    Kicukiro_Sectors_array = Kicukiro_Sectors.toArray(Kicukiro_Sectors_array);
                    ArrayAdapter<String> Kicukiro_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Kicukiro_Sectors_array);
                    Kicukiro_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Kicukiro_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Nyarugenge")) {

                    String[] Nyarugenge_Sectors_array = new String[Nyarugenge_Sectors.size()];
                    Nyarugenge_Sectors_array = Nyarugenge_Sectors.toArray(Nyarugenge_Sectors_array);
                    ArrayAdapter<String> Nyarugenge_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyarugenge_Sectors_array);
                    Nyarugenge_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Nyarugenge_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Bugesera")) {
                    String[] Bugesera_Sectors_array = new String[Bugesera_Sectors.size()];
                    Bugesera_Sectors_array = Bugesera_Sectors.toArray(Bugesera_Sectors_array);
                    ArrayAdapter<String> Bugesera_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Bugesera_Sectors_array);
                    Bugesera_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Bugesera_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Gatsibo")) {
                    String[] Gatsibo_Sectors_array = new String[Gatsibo_Sectors.size()];
                    Gatsibo_Sectors_array = Gatsibo_Sectors.toArray(Gatsibo_Sectors_array);
                    ArrayAdapter<String> Gatsibo_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Gatsibo_Sectors_array);
                    Gatsibo_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Gatsibo_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Kayonza")) {
                    String[] Kayonza_Sectors_array = new String[Kayonza_Sectors.size()];
                    Kayonza_Sectors_array = Kayonza_Sectors.toArray(Kayonza_Sectors_array);
                    ArrayAdapter<String> Kayonza_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Kayonza_Sectors_array);
                    Kayonza_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Kayonza_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Kirehe")) {
                    String[] Kirehe_Sectors_array = new String[Kirehe_Sectors.size()];
                    Kirehe_Sectors_array = Kirehe_Sectors.toArray(Kirehe_Sectors_array);
                    ArrayAdapter<String> Kirehe_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Kirehe_Sectors_array);
                    Kirehe_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Kirehe_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Ngoma")) {
                    String[] Ngoma_Sectors_array = new String[Ngoma_Sectors.size()];
                    Ngoma_Sectors_array = Ngoma_Sectors.toArray(Ngoma_Sectors_array);
                    ArrayAdapter<String> Ngoma_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Ngoma_Sectors_array);
                    Ngoma_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Ngoma_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Nyagatare")) {
                    String[] Nyagatare_Sectors_array = new String[Nyagatare_Sectors.size()];
                    Nyagatare_Sectors_array = Nyagatare_Sectors.toArray(Nyagatare_Sectors_array);
                    ArrayAdapter<String> Nyagatare_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyagatare_Sectors_array);
                    Nyagatare_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Nyagatare_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Rwamagana")) {
                    String[] Rwamagana_Sectors_array = new String[Rwamagana_Sectors.size()];
                    Rwamagana_Sectors_array = Nyagatare_Sectors.toArray(Rwamagana_Sectors_array);
                    ArrayAdapter<String> Rwamagana_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Rwamagana_Sectors_array);
                    Rwamagana_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Rwamagana_Sectors_array_Adapter);


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        client_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                String selectedItem_district = client_district.getSelectedItem().toString();
                if (selectedItem_district.contains("Karongi")) {
                    String[] Karongi_Sectors_array = new String[Karongi_Sectors.size()];
                    Karongi_Sectors_array = Karongi_Sectors.toArray(Karongi_Sectors_array);
                    ArrayAdapter<String> Karongi_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Karongi_Sectors_array);
                    Karongi_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Karongi_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Ngororero")) {
                    String[] Ngororero_Sectors_array = new String[Ngororero_Sectors.size()];
                    Ngororero_Sectors_array = Ngororero_Sectors.toArray(Ngororero_Sectors_array);
                    ArrayAdapter<String> Ngororero_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Ngororero_Sectors_array);
                    Ngororero_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Ngororero_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Nyabihu")) {
                    String[] Nyabihu_Sectors_array = new String[Nyabihu_Sectors.size()];
                    Nyabihu_Sectors_array = Nyabihu_Sectors.toArray(Nyabihu_Sectors_array);
                    ArrayAdapter<String> Nyabihu_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyabihu_Sectors_array);
                    Nyabihu_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Nyabihu_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Nyamasheke")) {
                    String[] Nyamasheke_Sectors_array = new String[Nyamasheke_Sectors.size()];
                    Nyamasheke_Sectors_array = Nyamasheke_Sectors.toArray(Nyamasheke_Sectors_array);
                    ArrayAdapter<String> Nyamasheke_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyamasheke_Sectors_array);
                    Nyamasheke_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Nyamasheke_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Rubavu")) {
                    String[] Rubavu_Sectors_array = new String[Rubavu_Sectors.size()];
                    Rubavu_Sectors_array = Nyamasheke_Sectors.toArray(Rubavu_Sectors_array);
                    ArrayAdapter<String> Rubavu_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Rubavu_Sectors_array);
                    Rubavu_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Rubavu_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Rutsiro")) {
                    String[] Rutsiro_Sectors_array = new String[Rutsiro_Sectors.size()];
                    Rutsiro_Sectors_array = Nyamasheke_Sectors.toArray(Rutsiro_Sectors_array);
                    ArrayAdapter<String> Rutsiro_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Rutsiro_Sectors_array);
                    Rutsiro_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Rutsiro_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Rusizi")) {
                    String[] Rusizi_array = new String[Rusizi_Sectors.size()];
                    Rusizi_array = Nyamasheke_Sectors.toArray(Rusizi_array);
                    ArrayAdapter<String> Rusizi_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Rusizi_array);
                    Rusizi_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Rusizi_array_Adapter);

                } else if (selectedItem_district.contains("Gisagara")) {
                    String[] Gisagara_Sectors_array = new String[Gisagara_Sectors.size()];
                    Gisagara_Sectors_array = Gisagara_Sectors.toArray(Gisagara_Sectors_array);
                    ArrayAdapter<String> Gisagara_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Gisagara_Sectors_array);
                    Gisagara_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Gisagara_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Huye")) {
                    String[] Huye_Sectors_array = new String[Huye_Sectors.size()];
                    Huye_Sectors_array = Huye_Sectors.toArray(Huye_Sectors_array);
                    ArrayAdapter<String> Huye_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Huye_Sectors_array);
                    Huye_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Huye_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Kamonyi")) {
                    String[] Kamonyi_Sectors_array = new String[Kamonyi_Sectors.size()];
                    Kamonyi_Sectors_array = Kamonyi_Sectors.toArray(Kamonyi_Sectors_array);
                    ArrayAdapter<String> Kamonyi_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Kamonyi_Sectors_array);
                    Kamonyi_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Kamonyi_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Muhanga")) {
                    String[] Muhanga_Sectors_array = new String[Muhanga_Sectors.size()];
                    Muhanga_Sectors_array = Muhanga_Sectors.toArray(Muhanga_Sectors_array);
                    ArrayAdapter<String> Muhanga_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Muhanga_Sectors_array);
                    Muhanga_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Muhanga_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Nyamagabe")) {
                    String[] Nyamagabe_Sectors_array = new String[Nyamagabe_Sectors.size()];
                    Nyamagabe_Sectors_array = Nyamagabe_Sectors.toArray(Nyamagabe_Sectors_array);
                    ArrayAdapter<String> Nyamagabe_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyamagabe_Sectors_array);
                    Nyamagabe_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Nyamagabe_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Nyanza")) {
                    String[] Nyanza_Sectors_array = new String[Nyanza_Sectors.size()];
                    Nyanza_Sectors_array = Nyanza_Sectors.toArray(Nyanza_Sectors_array);
                    ArrayAdapter<String> Nyanza_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyanza_Sectors_array);
                    Nyanza_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Nyanza_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Nyaruguru")) {
                    String[] Nyaruguru_Sectors_array = new String[Nyaruguru_Sectors.size()];
                    Nyaruguru_Sectors_array = Nyaruguru_Sectors.toArray(Nyaruguru_Sectors_array);
                    ArrayAdapter<String> Nyaruguru_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyaruguru_Sectors_array);
                    Nyaruguru_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Nyaruguru_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Ruhango")) {
                    String[] Ruhango_Sectors_array = new String[Ruhango_Sectors.size()];
                    Ruhango_Sectors_array = Ruhango_Sectors.toArray(Ruhango_Sectors_array);
                    ArrayAdapter<String> Ruhango_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Ruhango_Sectors_array);
                    Ruhango_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Ruhango_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Burera")) {
                    String[] Burera_Sectors_array = new String[Burera_Sectors.size()];
                    Burera_Sectors_array = Burera_Sectors.toArray(Burera_Sectors_array);
                    ArrayAdapter<String> Burera_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Burera_Sectors_array);
                    Burera_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Burera_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Gicumbi")) {
                    String[] Gicumbi_Sectors_array = new String[Gicumbi_Sectors.size()];
                    Gicumbi_Sectors_array = Gicumbi_Sectors.toArray(Gicumbi_Sectors_array);
                    ArrayAdapter<String> Gicumbi_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Gicumbi_Sectors_array);
                    Gicumbi_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Gicumbi_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Gakenke")) {
                    String[] Gakenke_Sectors_array = new String[Gakenke_Sectors.size()];
                    Gakenke_Sectors_array = Gakenke_Sectors.toArray(Gakenke_Sectors_array);
                    ArrayAdapter<String> Gakenke_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Gakenke_Sectors_array);
                    Gakenke_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Gakenke_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Musanze")) {
                    String[] Musanze_Sectors_array = new String[Musanze_Sectors.size()];
                    Musanze_Sectors_array = Musanze_Sectors.toArray(Musanze_Sectors_array);
                    ArrayAdapter<String> Musanze_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Musanze_Sectors_array);
                    Musanze_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Musanze_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Rulindo")) {
                    String[] Rulindo_Sectors_array = new String[Rulindo_Sectors.size()];
                    Rulindo_Sectors_array = Rulindo_Sectors.toArray(Rulindo_Sectors_array);
                    ArrayAdapter<String> Rulindo_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Rulindo_Sectors_array);
                    Rulindo_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Rulindo_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Gasabo")) {
                    String[] Gasabo_Sectors_array = new String[Gasabo_Sectors.size()];
                    Gasabo_Sectors_array = Gasabo_Sectors.toArray(Gasabo_Sectors_array);
                    ArrayAdapter<String> Gasabo_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Gasabo_Sectors_array);
                    Gasabo_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Gasabo_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Kicukiro")) {
                    String[] Kicukiro_Sectors_array = new String[Kicukiro_Sectors.size()];
                    Kicukiro_Sectors_array = Kicukiro_Sectors.toArray(Kicukiro_Sectors_array);
                    ArrayAdapter<String> Kicukiro_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Kicukiro_Sectors_array);
                    Kicukiro_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Kicukiro_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Nyarugenge")) {

                    String[] Nyarugenge_Sectors_array = new String[Nyarugenge_Sectors.size()];
                    Nyarugenge_Sectors_array = Nyarugenge_Sectors.toArray(Nyarugenge_Sectors_array);
                    ArrayAdapter<String> Nyarugenge_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyarugenge_Sectors_array);
                    Nyarugenge_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Nyarugenge_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Bugesera")) {
                    String[] Bugesera_Sectors_array = new String[Bugesera_Sectors.size()];
                    Bugesera_Sectors_array = Bugesera_Sectors.toArray(Bugesera_Sectors_array);
                    ArrayAdapter<String> Bugesera_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Bugesera_Sectors_array);
                    Bugesera_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Bugesera_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Gatsibo")) {
                    String[] Gatsibo_Sectors_array = new String[Gatsibo_Sectors.size()];
                    Gatsibo_Sectors_array = Gatsibo_Sectors.toArray(Gatsibo_Sectors_array);
                    ArrayAdapter<String> Gatsibo_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Gatsibo_Sectors_array);
                    Gatsibo_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Gatsibo_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Kayonza")) {
                    String[] Kayonza_Sectors_array = new String[Kayonza_Sectors.size()];
                    Kayonza_Sectors_array = Kayonza_Sectors.toArray(Kayonza_Sectors_array);
                    ArrayAdapter<String> Kayonza_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Kayonza_Sectors_array);
                    Kayonza_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Kayonza_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Kirehe")) {
                    String[] Kirehe_Sectors_array = new String[Kirehe_Sectors.size()];
                    Kirehe_Sectors_array = Kirehe_Sectors.toArray(Kirehe_Sectors_array);
                    ArrayAdapter<String> Kirehe_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Kirehe_Sectors_array);
                    Kirehe_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Kirehe_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Ngoma")) {
                    String[] Ngoma_Sectors_array = new String[Ngoma_Sectors.size()];
                    Ngoma_Sectors_array = Ngoma_Sectors.toArray(Ngoma_Sectors_array);
                    ArrayAdapter<String> Ngoma_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Ngoma_Sectors_array);
                    Ngoma_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Ngoma_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Nyagatare")) {
                    String[] Nyagatare_Sectors_array = new String[Nyagatare_Sectors.size()];
                    Nyagatare_Sectors_array = Nyagatare_Sectors.toArray(Nyagatare_Sectors_array);
                    ArrayAdapter<String> Nyagatare_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyagatare_Sectors_array);
                    Nyagatare_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Nyagatare_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Rwamagana")) {
                    String[] Rwamagana_Sectors_array = new String[Rwamagana_Sectors.size()];
                    Rwamagana_Sectors_array = Nyagatare_Sectors.toArray(Rwamagana_Sectors_array);
                    ArrayAdapter<String> Rwamagana_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Rwamagana_Sectors_array);
                    Rwamagana_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Rwamagana_Sectors_array_Adapter);


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        //---------For the truck driver---
        truck_driver_province.setAdapter(The_provinces_array_Adapter);
        truck_driver_district.setAdapter(kigali_districts_array_Adapter);
        truck_driver_sector.setAdapter(Gasabo_Sectors_array_Adapter);
        //---------------
        truck_driver_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                String selectedItem = truck_driver_province.getSelectedItem().toString();
                if (selectedItem.contains("Kigali")) {
                    String[] kigali_districts_array_ = new String[kigali_districts.size()];
                    kigali_districts_array_ = kigali_districts.toArray(kigali_districts_array_);
                    ArrayAdapter<String> kigali_districts_array_Adapter_ = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, kigali_districts_array_);
                    kigali_districts_array_Adapter_.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_district.setAdapter(kigali_districts_array_Adapter_);

                } else if (selectedItem.contains("North Province")) {
                    String[] north_districts_array_ = new String[north_districts.size()];
                    north_districts_array_ = north_districts.toArray(north_districts_array_);
                    ArrayAdapter<String> north_districts_array_Adapter_ = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, north_districts_array_);
                    north_districts_array_Adapter_.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_district.setAdapter(north_districts_array_Adapter_);
                } else if (selectedItem.contains("South Province")) {
                    String[] south_districts_array_ = new String[south_districts.size()];
                    south_districts_array_ = south_districts.toArray(south_districts_array_);
                    ArrayAdapter<String> south_districts_array_Adapter_ = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, south_districts_array_);
                    south_districts_array_Adapter_.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_district.setAdapter(south_districts_array_Adapter_);
                } else if (selectedItem.contains("West Province")) {
                    String[] west_districts_array_ = new String[west_districts.size()];
                    west_districts_array_ = west_districts.toArray(west_districts_array_);
                    ArrayAdapter<String> west_districts_array_Adapter_ = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, west_districts_array_);
                    west_districts_array_Adapter_.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_district.setAdapter(west_districts_array_Adapter_);
                } else if (selectedItem.contains("Eastern Province")) {
                    String[] east_districts_array_ = new String[east_districts.size()];
                    east_districts_array_ = east_districts.toArray(east_districts_array_);
                    ArrayAdapter<String> east_districts_array_Adapter_ = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, east_districts_array_);
                    east_districts_array_Adapter_.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_district.setAdapter(east_districts_array_Adapter_);
                }
                ///---------------------------
                String selectedItem_district = truck_driver_district.getSelectedItem().toString();
                if (selectedItem_district.contains("Karongi")) {
                    String[] Karongi_Sectors_array = new String[Karongi_Sectors.size()];
                    Karongi_Sectors_array = Karongi_Sectors.toArray(Karongi_Sectors_array);
                    ArrayAdapter<String> Karongi_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Karongi_Sectors_array);
                    Karongi_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Karongi_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Ngororero")) {
                    String[] Ngororero_Sectors_array = new String[Ngororero_Sectors.size()];
                    Ngororero_Sectors_array = Ngororero_Sectors.toArray(Ngororero_Sectors_array);
                    ArrayAdapter<String> Ngororero_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Ngororero_Sectors_array);
                    Ngororero_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Ngororero_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Nyabihu")) {
                    String[] Nyabihu_Sectors_array = new String[Nyabihu_Sectors.size()];
                    Nyabihu_Sectors_array = Nyabihu_Sectors.toArray(Nyabihu_Sectors_array);
                    ArrayAdapter<String> Nyabihu_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyabihu_Sectors_array);
                    Nyabihu_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Nyabihu_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Nyamasheke")) {
                    String[] Nyamasheke_Sectors_array = new String[Nyamasheke_Sectors.size()];
                    Nyamasheke_Sectors_array = Nyamasheke_Sectors.toArray(Nyamasheke_Sectors_array);
                    ArrayAdapter<String> Nyamasheke_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyamasheke_Sectors_array);
                    Nyamasheke_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Nyamasheke_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Rubavu")) {
                    String[] Rubavu_Sectors_array = new String[Rubavu_Sectors.size()];
                    Rubavu_Sectors_array = Nyamasheke_Sectors.toArray(Rubavu_Sectors_array);
                    ArrayAdapter<String> Rubavu_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Rubavu_Sectors_array);
                    Rubavu_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Rubavu_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Rutsiro")) {
                    String[] Rutsiro_Sectors_array = new String[Rutsiro_Sectors.size()];
                    Rutsiro_Sectors_array = Nyamasheke_Sectors.toArray(Rutsiro_Sectors_array);
                    ArrayAdapter<String> Rutsiro_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Rutsiro_Sectors_array);
                    Rutsiro_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Rutsiro_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Rusizi")) {
                    String[] Rusizi_array = new String[Rusizi_Sectors.size()];
                    Rusizi_array = Nyamasheke_Sectors.toArray(Rusizi_array);
                    ArrayAdapter<String> Rusizi_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Rusizi_array);
                    Rusizi_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Rusizi_array_Adapter);

                } else if (selectedItem_district.contains("Gisagara")) {
                    String[] Gisagara_Sectors_array = new String[Gisagara_Sectors.size()];
                    Gisagara_Sectors_array = Gisagara_Sectors.toArray(Gisagara_Sectors_array);
                    ArrayAdapter<String> Gisagara_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Gisagara_Sectors_array);
                    Gisagara_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Gisagara_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Huye")) {
                    String[] Huye_Sectors_array = new String[Huye_Sectors.size()];
                    Huye_Sectors_array = Huye_Sectors.toArray(Huye_Sectors_array);
                    ArrayAdapter<String> Huye_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Huye_Sectors_array);
                    Huye_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Huye_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Kamonyi")) {
                    String[] Kamonyi_Sectors_array = new String[Kamonyi_Sectors.size()];
                    Kamonyi_Sectors_array = Kamonyi_Sectors.toArray(Kamonyi_Sectors_array);
                    ArrayAdapter<String> Kamonyi_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Kamonyi_Sectors_array);
                    Kamonyi_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Kamonyi_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Muhanga")) {
                    String[] Muhanga_Sectors_array = new String[Muhanga_Sectors.size()];
                    Muhanga_Sectors_array = Muhanga_Sectors.toArray(Muhanga_Sectors_array);
                    ArrayAdapter<String> Muhanga_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Muhanga_Sectors_array);
                    Muhanga_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Muhanga_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Nyamagabe")) {
                    String[] Nyamagabe_Sectors_array = new String[Nyamagabe_Sectors.size()];
                    Nyamagabe_Sectors_array = Nyamagabe_Sectors.toArray(Nyamagabe_Sectors_array);
                    ArrayAdapter<String> Nyamagabe_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyamagabe_Sectors_array);
                    Nyamagabe_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Nyamagabe_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Nyanza")) {
                    String[] Nyanza_Sectors_array = new String[Nyanza_Sectors.size()];
                    Nyanza_Sectors_array = Nyanza_Sectors.toArray(Nyanza_Sectors_array);
                    ArrayAdapter<String> Nyanza_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyanza_Sectors_array);
                    Nyanza_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Nyanza_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Nyaruguru")) {
                    String[] Nyaruguru_Sectors_array = new String[Nyaruguru_Sectors.size()];
                    Nyaruguru_Sectors_array = Nyaruguru_Sectors.toArray(Nyaruguru_Sectors_array);
                    ArrayAdapter<String> Nyaruguru_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyaruguru_Sectors_array);
                    Nyaruguru_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Nyaruguru_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Ruhango")) {
                    String[] Ruhango_Sectors_array = new String[Ruhango_Sectors.size()];
                    Ruhango_Sectors_array = Ruhango_Sectors.toArray(Ruhango_Sectors_array);
                    ArrayAdapter<String> Ruhango_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Ruhango_Sectors_array);
                    Ruhango_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Ruhango_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Burera")) {
                    String[] Burera_Sectors_array = new String[Burera_Sectors.size()];
                    Burera_Sectors_array = Burera_Sectors.toArray(Burera_Sectors_array);
                    ArrayAdapter<String> Burera_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Burera_Sectors_array);
                    Burera_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Burera_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Gicumbi")) {
                    String[] Gicumbi_Sectors_array = new String[Gicumbi_Sectors.size()];
                    Gicumbi_Sectors_array = Gicumbi_Sectors.toArray(Gicumbi_Sectors_array);
                    ArrayAdapter<String> Gicumbi_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Gicumbi_Sectors_array);
                    Gicumbi_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Gicumbi_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Gakenke")) {
                    String[] Gakenke_Sectors_array = new String[Gakenke_Sectors.size()];
                    Gakenke_Sectors_array = Gakenke_Sectors.toArray(Gakenke_Sectors_array);
                    ArrayAdapter<String> Gakenke_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Gakenke_Sectors_array);
                    Gakenke_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Gakenke_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Musanze")) {
                    String[] Musanze_Sectors_array = new String[Musanze_Sectors.size()];
                    Musanze_Sectors_array = Musanze_Sectors.toArray(Musanze_Sectors_array);
                    ArrayAdapter<String> Musanze_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Musanze_Sectors_array);
                    Musanze_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Musanze_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Rulindo")) {
                    String[] Rulindo_Sectors_array = new String[Rulindo_Sectors.size()];
                    Rulindo_Sectors_array = Rulindo_Sectors.toArray(Rulindo_Sectors_array);
                    ArrayAdapter<String> Rulindo_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Rulindo_Sectors_array);
                    Rulindo_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Rulindo_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Gasabo")) {
                    String[] Gasabo_Sectors_array = new String[Gasabo_Sectors.size()];
                    Gasabo_Sectors_array = Gasabo_Sectors.toArray(Gasabo_Sectors_array);
                    ArrayAdapter<String> Gasabo_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Gasabo_Sectors_array);
                    Gasabo_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Gasabo_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Kicukiro")) {
                    String[] Kicukiro_Sectors_array = new String[Kicukiro_Sectors.size()];
                    Kicukiro_Sectors_array = Kicukiro_Sectors.toArray(Kicukiro_Sectors_array);
                    ArrayAdapter<String> Kicukiro_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Kicukiro_Sectors_array);
                    Kicukiro_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Kicukiro_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Nyarugenge")) {

                    String[] Nyarugenge_Sectors_array = new String[Nyarugenge_Sectors.size()];
                    Nyarugenge_Sectors_array = Nyarugenge_Sectors.toArray(Nyarugenge_Sectors_array);
                    ArrayAdapter<String> Nyarugenge_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyarugenge_Sectors_array);
                    Nyarugenge_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Nyarugenge_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Bugesera")) {
                    String[] Bugesera_Sectors_array = new String[Bugesera_Sectors.size()];
                    Bugesera_Sectors_array = Bugesera_Sectors.toArray(Bugesera_Sectors_array);
                    ArrayAdapter<String> Bugesera_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Bugesera_Sectors_array);
                    Bugesera_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Bugesera_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Gatsibo")) {
                    String[] Gatsibo_Sectors_array = new String[Gatsibo_Sectors.size()];
                    Gatsibo_Sectors_array = Gatsibo_Sectors.toArray(Gatsibo_Sectors_array);
                    ArrayAdapter<String> Gatsibo_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Gatsibo_Sectors_array);
                    Gatsibo_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Gatsibo_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Kayonza")) {
                    String[] Kayonza_Sectors_array = new String[Kayonza_Sectors.size()];
                    Kayonza_Sectors_array = Kayonza_Sectors.toArray(Kayonza_Sectors_array);
                    ArrayAdapter<String> Kayonza_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Kayonza_Sectors_array);
                    Kayonza_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Kayonza_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Kirehe")) {
                    String[] Kirehe_Sectors_array = new String[Kirehe_Sectors.size()];
                    Kirehe_Sectors_array = Kirehe_Sectors.toArray(Kirehe_Sectors_array);
                    ArrayAdapter<String> Kirehe_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Kirehe_Sectors_array);
                    Kirehe_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Kirehe_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Ngoma")) {
                    String[] Ngoma_Sectors_array = new String[Ngoma_Sectors.size()];
                    Ngoma_Sectors_array = Ngoma_Sectors.toArray(Ngoma_Sectors_array);
                    ArrayAdapter<String> Ngoma_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Ngoma_Sectors_array);
                    Ngoma_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Ngoma_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Nyagatare")) {
                    String[] Nyagatare_Sectors_array = new String[Nyagatare_Sectors.size()];
                    Nyagatare_Sectors_array = Nyagatare_Sectors.toArray(Nyagatare_Sectors_array);
                    ArrayAdapter<String> Nyagatare_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyagatare_Sectors_array);
                    Nyagatare_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Nyagatare_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Rwamagana")) {
                    String[] Rwamagana_Sectors_array = new String[Rwamagana_Sectors.size()];
                    Rwamagana_Sectors_array = Nyagatare_Sectors.toArray(Rwamagana_Sectors_array);
                    ArrayAdapter<String> Rwamagana_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Rwamagana_Sectors_array);
                    Rwamagana_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Rwamagana_Sectors_array_Adapter);


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        truck_driver_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                String selectedItem_district = truck_driver_district.getSelectedItem().toString();
                if (selectedItem_district.contains("Karongi")) {
                    String[] Karongi_Sectors_array = new String[Karongi_Sectors.size()];
                    Karongi_Sectors_array = Karongi_Sectors.toArray(Karongi_Sectors_array);
                    ArrayAdapter<String> Karongi_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Karongi_Sectors_array);
                    Karongi_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Karongi_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Ngororero")) {
                    String[] Ngororero_Sectors_array = new String[Ngororero_Sectors.size()];
                    Ngororero_Sectors_array = Ngororero_Sectors.toArray(Ngororero_Sectors_array);
                    ArrayAdapter<String> Ngororero_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Ngororero_Sectors_array);
                    Ngororero_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Ngororero_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Nyabihu")) {
                    String[] Nyabihu_Sectors_array = new String[Nyabihu_Sectors.size()];
                    Nyabihu_Sectors_array = Nyabihu_Sectors.toArray(Nyabihu_Sectors_array);
                    ArrayAdapter<String> Nyabihu_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyabihu_Sectors_array);
                    Nyabihu_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Nyabihu_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Nyamasheke")) {
                    String[] Nyamasheke_Sectors_array = new String[Nyamasheke_Sectors.size()];
                    Nyamasheke_Sectors_array = Nyamasheke_Sectors.toArray(Nyamasheke_Sectors_array);
                    ArrayAdapter<String> Nyamasheke_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyamasheke_Sectors_array);
                    Nyamasheke_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Nyamasheke_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Rubavu")) {
                    String[] Rubavu_Sectors_array = new String[Rubavu_Sectors.size()];
                    Rubavu_Sectors_array = Nyamasheke_Sectors.toArray(Rubavu_Sectors_array);
                    ArrayAdapter<String> Rubavu_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Rubavu_Sectors_array);
                    Rubavu_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Rubavu_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Rutsiro")) {
                    String[] Rutsiro_Sectors_array = new String[Rutsiro_Sectors.size()];
                    Rutsiro_Sectors_array = Nyamasheke_Sectors.toArray(Rutsiro_Sectors_array);
                    ArrayAdapter<String> Rutsiro_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Rutsiro_Sectors_array);
                    Rutsiro_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Rutsiro_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Rusizi")) {
                    String[] Rusizi_array = new String[Rusizi_Sectors.size()];
                    Rusizi_array = Nyamasheke_Sectors.toArray(Rusizi_array);
                    ArrayAdapter<String> Rusizi_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Rusizi_array);
                    Rusizi_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Rusizi_array_Adapter);

                } else if (selectedItem_district.contains("Gisagara")) {
                    String[] Gisagara_Sectors_array = new String[Gisagara_Sectors.size()];
                    Gisagara_Sectors_array = Gisagara_Sectors.toArray(Gisagara_Sectors_array);
                    ArrayAdapter<String> Gisagara_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Gisagara_Sectors_array);
                    Gisagara_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Gisagara_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Huye")) {
                    String[] Huye_Sectors_array = new String[Huye_Sectors.size()];
                    Huye_Sectors_array = Huye_Sectors.toArray(Huye_Sectors_array);
                    ArrayAdapter<String> Huye_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Huye_Sectors_array);
                    Huye_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Huye_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Kamonyi")) {
                    String[] Kamonyi_Sectors_array = new String[Kamonyi_Sectors.size()];
                    Kamonyi_Sectors_array = Kamonyi_Sectors.toArray(Kamonyi_Sectors_array);
                    ArrayAdapter<String> Kamonyi_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Kamonyi_Sectors_array);
                    Kamonyi_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Kamonyi_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Muhanga")) {
                    String[] Muhanga_Sectors_array = new String[Muhanga_Sectors.size()];
                    Muhanga_Sectors_array = Muhanga_Sectors.toArray(Muhanga_Sectors_array);
                    ArrayAdapter<String> Muhanga_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Muhanga_Sectors_array);
                    Muhanga_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Muhanga_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Nyamagabe")) {
                    String[] Nyamagabe_Sectors_array = new String[Nyamagabe_Sectors.size()];
                    Nyamagabe_Sectors_array = Nyamagabe_Sectors.toArray(Nyamagabe_Sectors_array);
                    ArrayAdapter<String> Nyamagabe_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyamagabe_Sectors_array);
                    Nyamagabe_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Nyamagabe_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Nyanza")) {
                    String[] Nyanza_Sectors_array = new String[Nyanza_Sectors.size()];
                    Nyanza_Sectors_array = Nyanza_Sectors.toArray(Nyanza_Sectors_array);
                    ArrayAdapter<String> Nyanza_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyanza_Sectors_array);
                    Nyanza_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Nyanza_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Nyaruguru")) {
                    String[] Nyaruguru_Sectors_array = new String[Nyaruguru_Sectors.size()];
                    Nyaruguru_Sectors_array = Nyaruguru_Sectors.toArray(Nyaruguru_Sectors_array);
                    ArrayAdapter<String> Nyaruguru_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyaruguru_Sectors_array);
                    Nyaruguru_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Nyaruguru_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Ruhango")) {
                    String[] Ruhango_Sectors_array = new String[Ruhango_Sectors.size()];
                    Ruhango_Sectors_array = Ruhango_Sectors.toArray(Ruhango_Sectors_array);
                    ArrayAdapter<String> Ruhango_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Ruhango_Sectors_array);
                    Ruhango_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Ruhango_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Burera")) {
                    String[] Burera_Sectors_array = new String[Burera_Sectors.size()];
                    Burera_Sectors_array = Burera_Sectors.toArray(Burera_Sectors_array);
                    ArrayAdapter<String> Burera_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Burera_Sectors_array);
                    Burera_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Burera_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Gicumbi")) {
                    String[] Gicumbi_Sectors_array = new String[Gicumbi_Sectors.size()];
                    Gicumbi_Sectors_array = Gicumbi_Sectors.toArray(Gicumbi_Sectors_array);
                    ArrayAdapter<String> Gicumbi_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Gicumbi_Sectors_array);
                    Gicumbi_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    client_sector.setAdapter(Gicumbi_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Gakenke")) {
                    String[] Gakenke_Sectors_array = new String[Gakenke_Sectors.size()];
                    Gakenke_Sectors_array = Gakenke_Sectors.toArray(Gakenke_Sectors_array);
                    ArrayAdapter<String> Gakenke_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Gakenke_Sectors_array);
                    Gakenke_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Gakenke_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Musanze")) {
                    String[] Musanze_Sectors_array = new String[Musanze_Sectors.size()];
                    Musanze_Sectors_array = Musanze_Sectors.toArray(Musanze_Sectors_array);
                    ArrayAdapter<String> Musanze_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Musanze_Sectors_array);
                    Musanze_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Musanze_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Rulindo")) {
                    String[] Rulindo_Sectors_array = new String[Rulindo_Sectors.size()];
                    Rulindo_Sectors_array = Rulindo_Sectors.toArray(Rulindo_Sectors_array);
                    ArrayAdapter<String> Rulindo_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Rulindo_Sectors_array);
                    Rulindo_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Rulindo_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Gasabo")) {
                    String[] Gasabo_Sectors_array = new String[Gasabo_Sectors.size()];
                    Gasabo_Sectors_array = Gasabo_Sectors.toArray(Gasabo_Sectors_array);
                    ArrayAdapter<String> Gasabo_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Gasabo_Sectors_array);
                    Gasabo_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Gasabo_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Kicukiro")) {
                    String[] Kicukiro_Sectors_array = new String[Kicukiro_Sectors.size()];
                    Kicukiro_Sectors_array = Kicukiro_Sectors.toArray(Kicukiro_Sectors_array);
                    ArrayAdapter<String> Kicukiro_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Kicukiro_Sectors_array);
                    Kicukiro_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Kicukiro_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Nyarugenge")) {

                    String[] Nyarugenge_Sectors_array = new String[Nyarugenge_Sectors.size()];
                    Nyarugenge_Sectors_array = Nyarugenge_Sectors.toArray(Nyarugenge_Sectors_array);
                    ArrayAdapter<String> Nyarugenge_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyarugenge_Sectors_array);
                    Nyarugenge_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Nyarugenge_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Bugesera")) {
                    String[] Bugesera_Sectors_array = new String[Bugesera_Sectors.size()];
                    Bugesera_Sectors_array = Bugesera_Sectors.toArray(Bugesera_Sectors_array);
                    ArrayAdapter<String> Bugesera_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Bugesera_Sectors_array);
                    Bugesera_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Bugesera_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Gatsibo")) {
                    String[] Gatsibo_Sectors_array = new String[Gatsibo_Sectors.size()];
                    Gatsibo_Sectors_array = Gatsibo_Sectors.toArray(Gatsibo_Sectors_array);
                    ArrayAdapter<String> Gatsibo_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Gatsibo_Sectors_array);
                    Gatsibo_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Gatsibo_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Kayonza")) {
                    String[] Kayonza_Sectors_array = new String[Kayonza_Sectors.size()];
                    Kayonza_Sectors_array = Kayonza_Sectors.toArray(Kayonza_Sectors_array);
                    ArrayAdapter<String> Kayonza_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Kayonza_Sectors_array);
                    Kayonza_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Kayonza_Sectors_array_Adapter);
                } else if (selectedItem_district.contains("Kirehe")) {
                    String[] Kirehe_Sectors_array = new String[Kirehe_Sectors.size()];
                    Kirehe_Sectors_array = Kirehe_Sectors.toArray(Kirehe_Sectors_array);
                    ArrayAdapter<String> Kirehe_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Kirehe_Sectors_array);
                    Kirehe_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Kirehe_Sectors_array_Adapter);

                } else if (selectedItem_district.contains("Ngoma")) {
                    String[] Ngoma_Sectors_array = new String[Ngoma_Sectors.size()];
                    Ngoma_Sectors_array = Ngoma_Sectors.toArray(Ngoma_Sectors_array);
                    ArrayAdapter<String> Ngoma_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Ngoma_Sectors_array);
                    Ngoma_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Ngoma_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Nyagatare")) {
                    String[] Nyagatare_Sectors_array = new String[Nyagatare_Sectors.size()];
                    Nyagatare_Sectors_array = Nyagatare_Sectors.toArray(Nyagatare_Sectors_array);
                    ArrayAdapter<String> Nyagatare_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Nyagatare_Sectors_array);
                    Nyagatare_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Nyagatare_Sectors_array_Adapter);


                } else if (selectedItem_district.contains("Rwamagana")) {
                    String[] Rwamagana_Sectors_array = new String[Rwamagana_Sectors.size()];
                    Rwamagana_Sectors_array = Nyagatare_Sectors.toArray(Rwamagana_Sectors_array);
                    ArrayAdapter<String> Rwamagana_Sectors_array_Adapter = new ArrayAdapter<String>(
                            RegisterActivity.this, android.R.layout.simple_spinner_item, Rwamagana_Sectors_array);
                    Rwamagana_Sectors_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    truck_driver_sector.setAdapter(Rwamagana_Sectors_array_Adapter);


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        String[] Volume_ofPIT_OrTruck = new String[]{"0-5 METER CUBES", "5-10 METER CUBES", "10-15 METER CUBES"
                , "15-20 METER CUBES", "20-25 METER CUBES", "25-30 METER CUBES", "30-40 METER CUBES",
                "40-50 METER CUBES", "50<... METER CUBES"};
        //-------------volume of carriage--
        ArrayAdapter<String> Volume_ofPIT_OrTruck_array_Adapter = new ArrayAdapter<String>(
                RegisterActivity.this, android.R.layout.simple_spinner_item, Volume_ofPIT_OrTruck);
        Volume_ofPIT_OrTruck_array_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        client_volume.setAdapter(Volume_ofPIT_OrTruck_array_Adapter);

        truck_driver_volume.setAdapter(Volume_ofPIT_OrTruck_array_Adapter);

        //------------------
        agreement_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(RegisterActivity.this, "Agreement button clickes!" + selected, Toast.LENGTH_SHORT).show();

                switch (selected) {
                    case 0:
                        step1_view.setVisibility(View.GONE);
                        step3_view.setVisibility(View.VISIBLE);
                        step3_view.startAnimation(AnimationUtils.loadAnimation(
                                getBaseContext(), R.anim.slide_to_right
                        ));

                        register_client.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Intent intent = new Intent(RegisterActivity.this, UserDasboard.class);
                                if (client_phone_numbr.getText().toString().equalsIgnoreCase("") || !isPhoneNumberValid(client_phone_numbr.getText().toString())) {
                                    Toast.makeText(RegisterActivity.this, "Phone number empty or Invalid", Toast.LENGTH_SHORT).show();

                                } else if (client_full_names.getText().toString().equalsIgnoreCase("")) {
                                    Toast.makeText(RegisterActivity.this, "Full Names empty or Invalid", Toast.LENGTH_SHORT).show();

                                } else if (client_email.getText().toString().equalsIgnoreCase("") || !isEmailValid(client_email.getText().toString())) {
                                    Toast.makeText(RegisterActivity.this, "Email empty or Invalid", Toast.LENGTH_SHORT).show();


                                } else if (client_cell.getText().toString().equalsIgnoreCase("")) {
                                    Toast.makeText(RegisterActivity.this, "Cell empty or Invalid", Toast.LENGTH_SHORT).show();
                                } else if (client_village.getText().toString().equalsIgnoreCase("")) {
                                    Toast.makeText(RegisterActivity.this, "Village empty or Invalid", Toast.LENGTH_SHORT).show();

                                } else if (client_street.getText().toString().equalsIgnoreCase("")) {
                                    Toast.makeText(RegisterActivity.this, "Street empty or Invalid", Toast.LENGTH_SHORT).show();

                                } else if (client_house_number.getText().toString().equalsIgnoreCase("")) {
                                    Toast.makeText(RegisterActivity.this, "House number empty or Invalid", Toast.LENGTH_SHORT).show();

                                } else if (client_start_date.getText().toString().equalsIgnoreCase("")) {
                                    Toast.makeText(RegisterActivity.this, "Start date empty or Invalid", Toast.LENGTH_SHORT).show();

                                } else if (client_end_date.getText().toString().equalsIgnoreCase("")) {
                                    Toast.makeText(RegisterActivity.this, "End date empty or Invalid", Toast.LENGTH_SHORT).show();

                                } else {
                                    new pitsan_send_the_client_request(RegisterActivity.this, Config.SEND_S_pit_OR_CROWDSOURCING_REQUEST.toString(), client_phone_numbr,
                                            client_full_names,
                                            client_email,
                                            client_province,
                                            client_district,
                                            client_sector,
                                            client_cell,
                                            client_village,
                                            client_street,
                                            client_house_number,
                                            client_volume,
                                            client_start_date,
                                            client_end_date, "" + selected).execute();
                                }
                            }
                        });
                        break;
                    case 1:
                        step1_view.setVisibility(View.GONE);
                        step3_view.setVisibility(View.VISIBLE);
                        step3_view.startAnimation(AnimationUtils.loadAnimation(
                                getBaseContext(), R.anim.slide_to_right
                        ));

                        register_client.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Intent intent = new Intent(RegisterActivity.this, UserDasboard.class);
                                if (client_phone_numbr.getText().toString().equalsIgnoreCase("") || !isPhoneNumberValid(client_phone_numbr.getText().toString())) {
                                    Toast.makeText(RegisterActivity.this, "Phone number empty or Invalid", Toast.LENGTH_SHORT).show();

                                } else if (client_full_names.getText().toString().equalsIgnoreCase("")) {
                                    Toast.makeText(RegisterActivity.this, "Full Names empty or Invalid", Toast.LENGTH_SHORT).show();

                                } else if (client_email.getText().toString().equalsIgnoreCase("") || !isEmailValid(client_email.getText().toString())) {
                                    Toast.makeText(RegisterActivity.this, "Email empty or Invalid", Toast.LENGTH_SHORT).show();


                                } else if (client_cell.getText().toString().equalsIgnoreCase("")) {
                                    Toast.makeText(RegisterActivity.this, "Cell empty or Invalid", Toast.LENGTH_SHORT).show();
                                } else if (client_village.getText().toString().equalsIgnoreCase("")) {
                                    Toast.makeText(RegisterActivity.this, "Village empty or Invalid", Toast.LENGTH_SHORT).show();

                                } else if (client_street.getText().toString().equalsIgnoreCase("")) {
                                    Toast.makeText(RegisterActivity.this, "Street empty or Invalid", Toast.LENGTH_SHORT).show();

                                } else if (client_house_number.getText().toString().equalsIgnoreCase("")) {
                                    Toast.makeText(RegisterActivity.this, "House number empty or Invalid", Toast.LENGTH_SHORT).show();

                                } else if (client_start_date.getText().toString().equalsIgnoreCase("")) {
                                    Toast.makeText(RegisterActivity.this, "Start date empty or Invalid", Toast.LENGTH_SHORT).show();

                                } else if (client_end_date.getText().toString().equalsIgnoreCase("")) {
                                    Toast.makeText(RegisterActivity.this, "End date empty or Invalid", Toast.LENGTH_SHORT).show();

                                } else {
                                    new pitsan_send_the_client_request(RegisterActivity.this, Config.SEND_S_pit_OR_CROWDSOURCING_REQUEST.toString(), client_phone_numbr,
                                            client_full_names,
                                            client_email,
                                            client_province,
                                            client_district,
                                            client_sector,
                                            client_cell,
                                            client_village,
                                            client_street,
                                            client_house_number,
                                            client_volume,
                                            client_start_date,
                                            client_end_date, "" + selected).execute();
                                }
                            }
                        });
                        break;
                    case 2:
                        step1_view.setVisibility(View.GONE);
                        step2_view.setVisibility(View.VISIBLE);
                        step3_view.setVisibility(View.GONE);
                        step2_view.startAnimation(AnimationUtils.loadAnimation(
                                getBaseContext(), R.anim.slide_to_right
                        ));
                        // register_truck.setOnClickListener(this);
                        register_truck.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Intent intent = new Intent(RegisterActivity.this, TruckDashboard.class);
                                if (truck_driver_phone_numbr.getText().toString().equalsIgnoreCase("") || !isPhoneNumberValid(truck_driver_phone_numbr.getText().toString())) {
                                    Toast.makeText(RegisterActivity.this, "Phone number empty or Invalid", Toast.LENGTH_SHORT).show();

                                } else if (truck_driver_full_names.getText().toString().equalsIgnoreCase("")) {
                                    Toast.makeText(RegisterActivity.this, "Full Names empty or Invalid", Toast.LENGTH_SHORT).show();

                                } else if (truck_driver_email.getText().toString().equalsIgnoreCase("") || !isEmailValid(truck_driver_email.getText().toString())) {
                                    Toast.makeText(RegisterActivity.this, "Email empty or Invalid", Toast.LENGTH_SHORT).show();

                                } else if (truck_driver_cell.getText().toString().equalsIgnoreCase("")) {
                                    Toast.makeText(RegisterActivity.this, "Cell empty or Invalid", Toast.LENGTH_SHORT).show();

                                } else if (truck_driver_village.getText().toString().equalsIgnoreCase("")) {
                                    Toast.makeText(RegisterActivity.this, "Village empty or Invalid", Toast.LENGTH_SHORT).show();

                                } else if (truck_driver_street.getText().toString().equalsIgnoreCase("")) {
                                    Toast.makeText(RegisterActivity.this, "Street  empty or Invalid", Toast.LENGTH_SHORT).show();

                                } else if (truck_driver_house_number.getText().toString().equalsIgnoreCase("")) {
                                    Toast.makeText(RegisterActivity.this, "House number  empty or Invalid", Toast.LENGTH_SHORT).show();

                                } else {
                                    new pitsan_send_the_truckdriver_request(RegisterActivity.this, Config.SEND_TRUCK_DRIVER_REQUEST.toString(), truck_driver_phone_numbr,
                                            truck_driver_full_names,
                                            truck_driver_email,
                                            truck_driver_province,
                                            truck_driver_district,
                                            truck_driver_sector,
                                            truck_driver_cell,
                                            truck_driver_village,
                                            truck_driver_street,
                                            truck_driver_house_number,
                                            truck_driver_volume,
                                            truck_driver_license_plate,
                                            truck_driver_station).execute();
                                }
                            }
                        });
                        break;
                    default:
                        spinner.setFocusable(true);
                        spinner.setFocusableInTouchMode(true);

                        spinner.requestFocus();

                }

            }
        });

       /* register_truck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, TruckDashboard.class);
                startActivity(intent);
            }
        });
        register_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, UserDasboard.class);
                startActivity(intent);
            }
        });*/


        myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                //------
                int weee = monthOfYear + 1;
                client_start_date.setText(year + "-" + weee + "-" + dayOfMonth);
                updateLabel();
            }

        };
        //---------------
        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                //------
                int weee = monthOfYear + 1;
                client_end_date.setText(year + "-" + weee + "-" + dayOfMonth);
                updateLabel();
            }

        };
        //-----------------
        client_start_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(RegisterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        //-----------------------
        client_end_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(RegisterActivity.this, date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        selected = position;
        // Showing selected spinner item
        ///Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        //-------------Change the Agreement Text---
        if (position == 0) {

        } else if (position == 1) {

        } else if (position == 3) {

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if (v == register_client) {
            //Intent intent = new Intent(RegisterActivity.this, UserDasboard.class);
            //Toast.makeText(this, "Selected: " + v, Toast.LENGTH_LONG).show();
            //startActivity(intent);selected
            //------------------------


        } else if (v == register_truck) {
            //Intent intent = new Intent(RegisterActivity.this, TruckDashboard.class);
            //Toast.makeText(this, "Selected: " + v, Toast.LENGTH_LONG).show();
            //startActivity(intent);
            //---------------------

        }
    }

    private void updateLabel() {
        DateFormat df = new SimpleDateFormat("Y-m-d");
        String date = df.format(Calendar.getInstance().getTime());
        //--------------
        String myFormat = "Y-m-d"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.CHINA);

        //Birthday.setText(df.format(myCalendar.getTime()));
    }

    public boolean isEmailValid(String email) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        return matcher.matches();
    }

    public boolean isPhoneNumberValid(String phone) {
        String regExpn = "^([d{12}]$)";

        CharSequence inputStr = phone;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        return phone.length() == 12 || phone.length() == 10;
    }
}

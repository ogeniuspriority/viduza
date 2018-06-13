package com.techhome.pitsan;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
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
        List<String> east_districts = new ArrayList<String>();
        InputStream inputStream_east = getResources().openRawResource(R.raw.eastprovince_districts);
        BufferedReader bufferedReader_east = new BufferedReader(new InputStreamReader(inputStream_east));
        String eachline_east = null;
        try {
            eachline_east = bufferedReader_east.readLine();
            while (eachline_east != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_east = bufferedReader_east.readLine();
                east_districts.add("" + eachline_east);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//--West---
        List<String> west_districts = new ArrayList<String>();
        InputStream inputStream_west = getResources().openRawResource(R.raw.westprovince_districts);
        BufferedReader bufferedReader_west = new BufferedReader(new InputStreamReader(inputStream_west));
        String eachline_west = null;
        try {
            eachline_west = bufferedReader_west.readLine();
            while (eachline_west != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_west = bufferedReader_west.readLine();
                west_districts.add("" + eachline_west);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-south--
        List<String> south_districts = new ArrayList<String>();
        InputStream inputStream_south = getResources().openRawResource(R.raw.southprovince_districts);
        BufferedReader bufferedReader_south = new BufferedReader(new InputStreamReader(inputStream_south));
        String eachline_south = null;
        try {
            eachline_south = bufferedReader_south.readLine();
            while (eachline_south != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_south = bufferedReader_south.readLine();
                south_districts.add("" + eachline_south);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//---North---
        List<String> north_districts = new ArrayList<String>();
        InputStream inputStream_north = getResources().openRawResource(R.raw.northprovinces_districts);
        BufferedReader bufferedReader_north = new BufferedReader(new InputStreamReader(inputStream_north));
        String eachline_north = null;
        try {
            eachline_north = bufferedReader_north.readLine();
            while (eachline_north != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_north = bufferedReader_north.readLine();
                north_districts.add("" + eachline_north);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//----------kigali---
        List<String> kigali_districts = new ArrayList<String>();
        InputStream inputStream_kigali = getResources().openRawResource(R.raw.kigali_districts);
        BufferedReader bufferedReader_kigali = new BufferedReader(new InputStreamReader(inputStream_kigali));
        String eachline_kigali = null;
        try {
            eachline_kigali = bufferedReader_kigali.readLine();
            while (eachline_kigali != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_kigali = bufferedReader_kigali.readLine();
                kigali_districts.add("" + eachline_kigali);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-----All districts-------------
//----------Bugesera---
        List<String> Bugesera_Sectors = new ArrayList<String>();
        InputStream inputStream_Bugesera_Sectors = getResources().openRawResource(R.raw.bugesera_sectors);
        BufferedReader bufferedReader_Bugesera_Sectors = new BufferedReader(new InputStreamReader(inputStream_Bugesera_Sectors));
        String eachline_Bugesera_Sectors = null;
        try {
            eachline_Bugesera_Sectors = bufferedReader_Bugesera_Sectors.readLine();
            while (eachline_Bugesera_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Bugesera_Sectors = bufferedReader_Bugesera_Sectors.readLine();
                Bugesera_Sectors.add("" + eachline_Bugesera_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Burera Sectors------------
        List<String> Burera_Sectors = new ArrayList<String>();
        InputStream inputStream_Burera_Sectors = getResources().openRawResource(R.raw.burera_sectors);
        BufferedReader bufferedReader_Burera_Sectors = new BufferedReader(new InputStreamReader(inputStream_Burera_Sectors));
        String eachline_Burera_Sectors = null;
        try {
            eachline_Burera_Sectors = bufferedReader_Burera_Sectors.readLine();
            while (eachline_Burera_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Burera_Sectors = bufferedReader_kigali.readLine();
                Burera_Sectors.add("" + eachline_Burera_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Gakenke Sectors------------
        List<String> Gakenke_Sectors = new ArrayList<String>();
        InputStream inputStream_Gakenke_Sectors = getResources().openRawResource(R.raw.gakenke_sectors);
        BufferedReader bufferedReader_Gakenke_Sectors = new BufferedReader(new InputStreamReader(inputStream_Gakenke_Sectors));
        String eachline_Gakenke_Sectors = null;
        try {
            eachline_Gakenke_Sectors = bufferedReader_Gakenke_Sectors.readLine();
            while (eachline_Gakenke_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Gakenke_Sectors = bufferedReader_Gakenke_Sectors.readLine();
                Gakenke_Sectors.add("" + eachline_Gakenke_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Gasabo Sectors-----------
        List<String> Gasabo_Sectors = new ArrayList<String>();
        InputStream inputStream_Gasabo_Sectors = getResources().openRawResource(R.raw.gasabo_sectors);
        BufferedReader bufferedReader_Gasabo_Sectors = new BufferedReader(new InputStreamReader(inputStream_Gasabo_Sectors));
        String eachline_Gasabo_Sectors = null;
        try {
            eachline_Gasabo_Sectors = bufferedReader_Gasabo_Sectors.readLine();
            while (eachline_Gasabo_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Gasabo_Sectors = bufferedReader_Gasabo_Sectors.readLine();
                Gasabo_Sectors.add("" + eachline_Gasabo_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Gatsibo Sectors-----------
        List<String> Gatsibo_Sectors = new ArrayList<String>();
        InputStream inputStream_Gatsibo_Sectors = getResources().openRawResource(R.raw.gatsibo_sectors);
        BufferedReader bufferedReader_Gatsibo_Sectors = new BufferedReader(new InputStreamReader(inputStream_Gatsibo_Sectors));
        String eachline_Gatsibo_Sectors = null;
        try {
            eachline_Gatsibo_Sectors = bufferedReader_Gatsibo_Sectors.readLine();
            while (eachline_Gatsibo_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Gatsibo_Sectors = bufferedReader_Gatsibo_Sectors.readLine();
                Gatsibo_Sectors.add("" + eachline_Gatsibo_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Gicumbi Sectors-----------
        List<String> Gicumbi_Sectors = new ArrayList<String>();
        InputStream inputStream_Gicumbi_Sectors = getResources().openRawResource(R.raw.gicumbi_sectors);
        BufferedReader bufferedReader_Gicumbi_Sectors = new BufferedReader(new InputStreamReader(inputStream_Gicumbi_Sectors));
        String eachline_Gicumbi_Sectors = null;
        try {
            eachline_Gicumbi_Sectors = bufferedReader_Gicumbi_Sectors.readLine();
            while (eachline_Gicumbi_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Gicumbi_Sectors = bufferedReader_Gicumbi_Sectors.readLine();
                Gicumbi_Sectors.add("" + eachline_Gicumbi_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Gisagara Sectors-----------
        List<String> Gisagara_Sectors = new ArrayList<String>();
        InputStream inputStream_Gisagara_Sectors = getResources().openRawResource(R.raw.gisagara_sectors);
        BufferedReader bufferedReader_Gisagara_Sectors = new BufferedReader(new InputStreamReader(inputStream_Gisagara_Sectors));
        String eachline_Gisagara_Sectors = null;
        try {
            eachline_Gisagara_Sectors = bufferedReader_Gisagara_Sectors.readLine();
            while (eachline_Gisagara_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Gisagara_Sectors = bufferedReader_Gisagara_Sectors.readLine();
                Gisagara_Sectors.add("" + eachline_Gisagara_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Huye Sectors-----------
        List<String> Huye_Sectors = new ArrayList<String>();
        InputStream inputStream_Huye_Sectors = getResources().openRawResource(R.raw.huye_sectors);
        BufferedReader bufferedReader_Huye_Sectors = new BufferedReader(new InputStreamReader(inputStream_Huye_Sectors));
        String eachline_Huye_Sectors = null;
        try {
            eachline_Huye_Sectors = bufferedReader_Huye_Sectors.readLine();
            while (eachline_Huye_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Huye_Sectors = bufferedReader_Huye_Sectors.readLine();
                Huye_Sectors.add("" + eachline_Huye_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Kamonyi Sectors-----------
        List<String> Kamonyi_Sectors = new ArrayList<String>();
        InputStream inputStream_Kamonyi_Sectors = getResources().openRawResource(R.raw.kamonyi_sectors);
        BufferedReader bufferedReader_Kamonyi_Sectors = new BufferedReader(new InputStreamReader(inputStream_Kamonyi_Sectors));
        String eachline_Kamonyi_Sectors = null;
        try {
            eachline_Kamonyi_Sectors = bufferedReader_Kamonyi_Sectors.readLine();
            while (eachline_Kamonyi_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Kamonyi_Sectors = bufferedReader_Kamonyi_Sectors.readLine();
                Kamonyi_Sectors.add("" + eachline_Kamonyi_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Karongi Sectors-----------
        List<String> Karongi_Sectors = new ArrayList<String>();
        InputStream inputStream_Karongi_Sectors = getResources().openRawResource(R.raw.karongi_sectors);
        BufferedReader bufferedReader_Karongi_Sectors = new BufferedReader(new InputStreamReader(inputStream_Karongi_Sectors));
        String eachline_Karongi_Sectors = null;
        try {
            eachline_Karongi_Sectors = bufferedReader_Karongi_Sectors.readLine();
            while (eachline_Karongi_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Karongi_Sectors = bufferedReader_Karongi_Sectors.readLine();
                Karongi_Sectors.add("" + eachline_Karongi_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//-------Kayonza Sectors-----------
        List<String> Kayonza_Sectors = new ArrayList<String>();
        InputStream inputStream_Kayonza_Sectors = getResources().openRawResource(R.raw.kayonza_sectors);
        BufferedReader bufferedReader_Kayonza_Sectors = new BufferedReader(new InputStreamReader(inputStream_Kayonza_Sectors));
        String eachline_Kayonza_Sectors = null;
        try {
            eachline_Kayonza_Sectors = bufferedReader_Kayonza_Sectors.readLine();
            while (eachline_Kayonza_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Kayonza_Sectors = bufferedReader_Kayonza_Sectors.readLine();
                Kayonza_Sectors.add("" + eachline_Kayonza_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Kicukiro Sectors-----------
        List<String> Kicukiro_Sectors = new ArrayList<String>();
        InputStream inputStream_Kicukiro_Sectors = getResources().openRawResource(R.raw.kicukiro_sectors);
        BufferedReader bufferedReader_Kicukiro_Sectors = new BufferedReader(new InputStreamReader(inputStream_Kicukiro_Sectors));
        String eachline_Kicukiro_Sectors = null;
        try {
            eachline_Kicukiro_Sectors = bufferedReader_Kicukiro_Sectors.readLine();
            while (eachline_Kicukiro_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Kicukiro_Sectors = bufferedReader_Kicukiro_Sectors.readLine();
                Kicukiro_Sectors.add("" + eachline_Kicukiro_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Kirehe Sectors-----------
        List<String> Kirehe_Sectors = new ArrayList<String>();
        InputStream inputStream_Kirehe_Sectors = getResources().openRawResource(R.raw.kirehe_sectors);
        BufferedReader bufferedReader_Kirehe_Sectors = new BufferedReader(new InputStreamReader(inputStream_Kirehe_Sectors));
        String eachline_Kirehe_Sectors = null;
        try {
            eachline_Kirehe_Sectors = bufferedReader_Kirehe_Sectors.readLine();
            while (eachline_Kirehe_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Kirehe_Sectors = bufferedReader_Kirehe_Sectors.readLine();
                Kirehe_Sectors.add("" + eachline_Kirehe_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Muhanga Sectors-----------
        List<String> Muhanga_Sectors = new ArrayList<String>();
        InputStream inputStream_Muhanga_Sectors = getResources().openRawResource(R.raw.muhanga_sectors);
        BufferedReader bufferedReader_Muhanga_Sectors = new BufferedReader(new InputStreamReader(inputStream_Muhanga_Sectors));
        String eachline_Muhanga_Sectors = null;
        try {
            eachline_Muhanga_Sectors = bufferedReader_Muhanga_Sectors.readLine();
            while (eachline_Muhanga_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Muhanga_Sectors = bufferedReader_Muhanga_Sectors.readLine();
                Muhanga_Sectors.add("" + eachline_Muhanga_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//-------Musanze Sectors-----------
        List<String> Musanze_Sectors = new ArrayList<String>();
        InputStream inputStream_Musanze_Sectors = getResources().openRawResource(R.raw.musanze_sectors);
        BufferedReader bufferedReader_Musanze_Sectors = new BufferedReader(new InputStreamReader(inputStream_Musanze_Sectors));
        String eachline_Musanze_Sectors = null;
        try {
            eachline_Musanze_Sectors = bufferedReader_Musanze_Sectors.readLine();
            while (eachline_Musanze_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Musanze_Sectors = bufferedReader_Musanze_Sectors.readLine();
                Musanze_Sectors.add("" + eachline_Musanze_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Ngoma Sectors-----------
        List<String> Ngoma_Sectors = new ArrayList<String>();
        InputStream inputStream_Ngoma_Sectors = getResources().openRawResource(R.raw.ngoma_sectors);
        BufferedReader bufferedReader_Ngoma_Sectors = new BufferedReader(new InputStreamReader(inputStream_Ngoma_Sectors));
        String eachline_Ngoma_Sectors = null;
        try {
            eachline_Ngoma_Sectors = bufferedReader_Ngoma_Sectors.readLine();
            while (eachline_Ngoma_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Ngoma_Sectors = bufferedReader_Ngoma_Sectors.readLine();
                Ngoma_Sectors.add("" + eachline_Ngoma_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Ngororero Sectors-----------
        List<String> Ngororero_Sectors = new ArrayList<String>();
        InputStream inputStream_Ngororero_Sectors = getResources().openRawResource(R.raw.ngororero_sectors);
        BufferedReader bufferedReader_Ngororero_Sectors = new BufferedReader(new InputStreamReader(inputStream_Ngororero_Sectors));
        String eachline_Ngororero_Sectors = null;
        try {
            eachline_Ngororero_Sectors = bufferedReader_Ngororero_Sectors.readLine();
            while (eachline_Ngororero_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Ngororero_Sectors = bufferedReader_Ngororero_Sectors.readLine();
                Ngororero_Sectors.add("" + eachline_Ngororero_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Nyabihu Sectors-----------
        List<String> Nyabihu_Sectors = new ArrayList<String>();
        InputStream inputStream_Nyabihu_Sectors = getResources().openRawResource(R.raw.nyabihu_sectors);
        BufferedReader bufferedReader_Nyabihu_Sectors = new BufferedReader(new InputStreamReader(inputStream_Nyabihu_Sectors));
        String eachline_Nyabihu_Sectors = null;
        try {
            eachline_Nyabihu_Sectors = bufferedReader_Nyabihu_Sectors.readLine();
            while (eachline_Nyabihu_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Nyabihu_Sectors = bufferedReader_Nyabihu_Sectors.readLine();
                Nyabihu_Sectors.add("" + eachline_Nyabihu_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Nyagatare Sectors-----------
        List<String> Nyagatare_Sectors = new ArrayList<String>();
        InputStream inputStream_Nyagatare_Sectors = getResources().openRawResource(R.raw.nyagatare_sectors);
        BufferedReader bufferedReader_Nyagatare_Sectors = new BufferedReader(new InputStreamReader(inputStream_Nyagatare_Sectors));
        String eachline_Nyagatare_Sectors = null;
        try {
            eachline_Nyagatare_Sectors = bufferedReader_Nyagatare_Sectors.readLine();
            while (eachline_Nyagatare_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Nyagatare_Sectors = bufferedReader_Nyagatare_Sectors.readLine();
                Nyagatare_Sectors.add("" + eachline_Nyagatare_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Nyamagabe Sectors-----------
        List<String> Nyamagabe_Sectors = new ArrayList<String>();
        InputStream inputStream_Nyamagabe_Sectors = getResources().openRawResource(R.raw.nyamagabe_sectors);
        BufferedReader bufferedReader_Nyamagabe_Sectors = new BufferedReader(new InputStreamReader(inputStream_Nyamagabe_Sectors));
        String eachline_Nyamagabe_Sectors = null;
        try {
            eachline_Nyamagabe_Sectors = bufferedReader_Nyamagabe_Sectors.readLine();
            while (eachline_Nyamagabe_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Nyamagabe_Sectors = bufferedReader_Nyamagabe_Sectors.readLine();
                Nyamagabe_Sectors.add("" + eachline_Nyamagabe_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Nyamasheke Sectors-----------
        List<String> Nyamasheke_Sectors = new ArrayList<String>();
        InputStream inputStream_Nyamasheke_Sectors = getResources().openRawResource(R.raw.nyamasheke_sectors);
        BufferedReader bufferedReader_Nyamasheke_Sectors = new BufferedReader(new InputStreamReader(inputStream_Nyamasheke_Sectors));
        String eachline_Nyamasheke_Sectors = null;
        try {
            eachline_Nyamasheke_Sectors = bufferedReader_Nyamasheke_Sectors.readLine();
            while (eachline_Nyamasheke_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Nyamasheke_Sectors = bufferedReader_Nyamasheke_Sectors.readLine();
                Nyamasheke_Sectors.add("" + eachline_Nyamasheke_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Nyanza Sectors-----------
        List<String> Nyanza_Sectors = new ArrayList<String>();
        InputStream inputStream_Nyanza_Sectors = getResources().openRawResource(R.raw.nyanza_sectors);
        BufferedReader bufferedReader_Nyanza_Sectors = new BufferedReader(new InputStreamReader(inputStream_Nyanza_Sectors));
        String eachline_Nyanza_Sectors = null;
        try {
            eachline_Nyanza_Sectors = bufferedReader_Nyanza_Sectors.readLine();
            while (eachline_Nyanza_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Nyanza_Sectors = bufferedReader_Nyanza_Sectors.readLine();
                Nyanza_Sectors.add("" + eachline_Nyanza_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Nyarugenge Sectors-----------
        List<String> Nyarugenge_Sectors = new ArrayList<String>();
        InputStream inputStream_Nyarugenge_Sectors = getResources().openRawResource(R.raw.nyarugenge_sectors);
        BufferedReader bufferedReader_Nyarugenge_Sectors = new BufferedReader(new InputStreamReader(inputStream_Nyarugenge_Sectors));
        String eachline_Nyarugenge_Sectors = null;
        try {
            eachline_Nyarugenge_Sectors = bufferedReader_Nyarugenge_Sectors.readLine();
            while (eachline_Nyarugenge_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Nyarugenge_Sectors = bufferedReader_Nyarugenge_Sectors.readLine();
                Nyarugenge_Sectors.add("" + eachline_Nyarugenge_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Nyaruguru Sectors-----------
        List<String> Nyaruguru_Sectors = new ArrayList<String>();
        InputStream inputStream_Nyaruguru_Sectors = getResources().openRawResource(R.raw.nyaruguru_sectors);
        BufferedReader bufferedReader_Nyaruguru_Sectors = new BufferedReader(new InputStreamReader(inputStream_Nyaruguru_Sectors));
        String eachline_Nyaruguru_Sectors = null;
        try {
            eachline_Nyaruguru_Sectors = bufferedReader_Nyaruguru_Sectors.readLine();
            while (eachline_Nyaruguru_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Nyaruguru_Sectors = bufferedReader_Nyaruguru_Sectors.readLine();
                Nyaruguru_Sectors.add("" + eachline_Nyaruguru_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Rubavu Sectors-----------
        List<String> Rubavu_Sectors = new ArrayList<String>();
        InputStream inputStream_Rubavu_Sectors = getResources().openRawResource(R.raw.rubavu_sectors);
        BufferedReader bufferedReader_Rubavu_Sectors = new BufferedReader(new InputStreamReader(inputStream_Rubavu_Sectors));
        String eachline_Rubavu_Sectors = null;
        try {
            eachline_Rubavu_Sectors = bufferedReader_Rubavu_Sectors.readLine();
            while (eachline_Rubavu_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Rubavu_Sectors = bufferedReader_Rubavu_Sectors.readLine();
                Rubavu_Sectors.add("" + eachline_Rubavu_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Ruhango Sectors-----------
        List<String> Ruhango_Sectors = new ArrayList<String>();
        InputStream inputStream_Ruhango_Sectors = getResources().openRawResource(R.raw.ruhango_sectors);
        BufferedReader bufferedReader_Ruhango_Sectors = new BufferedReader(new InputStreamReader(inputStream_Ruhango_Sectors));
        String eachline_Ruhango_Sectors = null;
        try {
            eachline_Ruhango_Sectors = bufferedReader_Ruhango_Sectors.readLine();
            while (eachline_Ruhango_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Ruhango_Sectors = bufferedReader_Ruhango_Sectors.readLine();
                Ruhango_Sectors.add("" + eachline_Ruhango_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Rulindo Sectors-----------
        List<String> Rulindo_Sectors = new ArrayList<String>();
        InputStream inputStream_Rulindo_Sectors = getResources().openRawResource(R.raw.rulindo_sectors);
        BufferedReader bufferedReader_Rulindo_Sectors = new BufferedReader(new InputStreamReader(inputStream_Rulindo_Sectors));
        String eachline_Rulindo_Sectors = null;
        try {
            eachline_Rulindo_Sectors = bufferedReader_Rulindo_Sectors.readLine();
            while (eachline_Rulindo_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Rulindo_Sectors = bufferedReader_Rulindo_Sectors.readLine();
                Rulindo_Sectors.add("" + eachline_Rulindo_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Rusizi Sectors-----------
        List<String> Rusizi_Sectors = new ArrayList<String>();
        InputStream inputStream_Rusizi_Sectors = getResources().openRawResource(R.raw.rusizi_sectors);
        BufferedReader bufferedReader_Rusizi_Sectors = new BufferedReader(new InputStreamReader(inputStream_Rusizi_Sectors));
        String eachline_Rusizi_Sectors = null;
        try {
            eachline_Rusizi_Sectors = bufferedReader_Rusizi_Sectors.readLine();
            while (eachline_Rusizi_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Rusizi_Sectors = bufferedReader_Rusizi_Sectors.readLine();
                Rusizi_Sectors.add("" + eachline_Rusizi_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Rutsiro Sectors-----------
        List<String> Rutsiro_Sectors = new ArrayList<String>();
        InputStream inputStream_Rutsiro_Sectors = getResources().openRawResource(R.raw.rutsiro_sectors);
        BufferedReader bufferedReader_Rutsiro_Sectors = new BufferedReader(new InputStreamReader(inputStream_Rutsiro_Sectors));
        String eachline_Rutsiro_Sectors = null;
        try {
            eachline_Rutsiro_Sectors = bufferedReader_Rutsiro_Sectors.readLine();
            while (eachline_Rutsiro_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Rutsiro_Sectors = bufferedReader_Rutsiro_Sectors.readLine();
                Rutsiro_Sectors.add("" + eachline_Rutsiro_Sectors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//-------Rwamagana Sectors-----------
        List<String> Rwamagana_Sectors = new ArrayList<String>();
        InputStream inputStream_Rwamagana_Sectors = getResources().openRawResource(R.raw.rwamagana_sectors);
        BufferedReader bufferedReader_Rwamagana_Sectors = new BufferedReader(new InputStreamReader(inputStream_Rwamagana_Sectors));
        String eachline_Rwamagana_Sectors = null;
        try {
            eachline_Rwamagana_Sectors = bufferedReader_Rwamagana_Sectors.readLine();
            while (eachline_Rwamagana_Sectors != null) {
                // `the words in the file are separated by space`, so to get each words
                eachline_Rwamagana_Sectors = bufferedReader_Rwamagana_Sectors.readLine();
                Rwamagana_Sectors.add("" + eachline_Rwamagana_Sectors);
            }
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
                                Intent intent = new Intent(RegisterActivity.this, UserDasboard.class);
                                Toast.makeText(RegisterActivity.this, "Selected: " + v, Toast.LENGTH_LONG).show();
                                startActivity(intent);
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
                                Intent intent = new Intent(RegisterActivity.this, UserDasboard.class);
                                Toast.makeText(RegisterActivity.this, "Selected: " + v, Toast.LENGTH_LONG).show();
                                startActivity(intent);
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
                                Intent intent = new Intent(RegisterActivity.this, TruckDashboard.class);
                                Toast.makeText(RegisterActivity.this, "Selected: " + v, Toast.LENGTH_LONG).show();
                                startActivity(intent);
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


        client_start_date = findViewById(R.id.client_start_date);
        client_end_date = findViewById(R.id.client_end_date);
        client_start_date.setFocusable(false);
        client_start_date.setClickable(true);

        client_end_date.setFocusable(false);
        client_end_date.setClickable(true);

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
            //startActivity(intent);
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
}

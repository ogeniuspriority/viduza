package com.techhome.pitsan;

import android.app.Activity;
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
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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

    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

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
        agreement_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Button pressed" + selected, Toast.LENGTH_LONG).show();
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

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        selected = position;

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if (v == register_client) {
            Intent intent = new Intent(RegisterActivity.this, UserDasboard.class);
            Toast.makeText(this, "Selected: " + v, Toast.LENGTH_LONG).show();
            startActivity(intent);
        } else if (v == register_truck) {
            Intent intent = new Intent(RegisterActivity.this, TruckDashboard.class);
            Toast.makeText(this, "Selected: " + v, Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
    }
}

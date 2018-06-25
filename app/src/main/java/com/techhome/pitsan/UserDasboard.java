package com.techhome.pitsan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;
import com.techhome.pitsan.back_end.pitsan_load_user_dashboard_feeds;

public class UserDasboard extends AppCompatActivity {
    ListView list;
    FloatingActionButton requestFab;
    LinearLayout requestPop;


    String[] name = {
            "Safari",
            "Camera",
            "Global",
            "FireFox",
            "UC Browser",
            "Android Folder",
            "VLC Player",
            "Cold War"
    };
    String[] type = {
            "Single",
            "Single",
            "Single",
            "Single",
            "Single",
            "Single",
            "Single",
            "Single"
    };
    String[] comment = {
            "Safari",
            "Camera",
            "Global",
            "FireFox",
            "UC Browser",
            "Android FoldeAndroid Folde Android Folde Android Folde Android Folde Android Folde Android Folder",
            "",
            "Cold War"
    };
    String[] reqnumber = {
            "1",
            "1",
            "1",
            "1",
            "2",
            "2",
            "2",
            "2r"
    };
    String[] amount = {
            "30000",
            "35000",
            "35000",
            "34000",
            "30000",
            "30000",
            "30000",
            "30000"
    };
    String[] date = {
            "12/05/2018",
            "12/05/2018",
            "12/05/2018",
            "12/05/2018",
            "12/05/2018",
            "12/05/2018",
            "12/05/2018",
            "12/05/2018"
    };
    public static volatile String refId = "";
    SwipeRefreshLayout swiperefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dasboard);
        Toolbar userToolbar = findViewById(R.id.user_toolbar);
        setSupportActionBar(userToolbar);
        requestPop = findViewById(R.id.request_pop);
        requestFab = findViewById(R.id.floatingActionButton);
        CustomListAdapter adapter = new CustomListAdapter(this, amount, date, name, type, comment, reqnumber);
        list = findViewById(R.id.list);
        //list.setAdapter(adapter);
        requestFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                requestPop.setVisibility(View.VISIBLE);
                requestPop.startAnimation(AnimationUtils.loadAnimation(
                        getBaseContext(), R.anim.slide_to_right
                ));
            }
        });
        //-------
        Intent intent = getIntent();
        String refid = intent.getStringExtra("refid");
        refId = refid;
        //------------------
        final TextView fullNames = findViewById(R.id.fullNames);
        final TextView location = findViewById(R.id.location);
        final TextView offerType = findViewById(R.id.offerType);
        final TextView Tel = findViewById(R.id.Tel);
        swiperefresh = findViewById(R.id.swiperefresh);
        //-------
        swiperefresh = findViewById(R.id.swiperefresh);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new pitsan_load_user_dashboard_feeds(UserDasboard.this, Config.PITSAN_REF_ID_OFFERS_PIT_DATA_LOAD_FEEDS.toString(), refId,
                        fullNames, location, offerType,
                        Tel, swiperefresh, list).execute();
            }
        });
        //-----------
        new pitsan_load_user_dashboard_feeds(UserDasboard.this, Config.PITSAN_REF_ID_OFFERS_PIT_DATA_LOAD_FEEDS.toString(), refId,
                fullNames, location, offerType,
                Tel, swiperefresh, list).execute();


    }

    public void removerequest(View view) {
        Intent removeIntent = new Intent(this, RemoveRequest.class);
        startActivity(removeIntent);
    }

    public void addnewrequest(View view) {
        Intent requestIntent = new Intent(this, NewRequest.class);
        startActivity(requestIntent);
    }
}

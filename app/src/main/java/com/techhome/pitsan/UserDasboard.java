package com.techhome.pitsan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;

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
        list.setAdapter(adapter);
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

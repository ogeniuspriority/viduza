package com.techhome.pitsan;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

public class RemoveRequest extends AppCompatActivity {
    ListView list;
    String[] location = {
            "Nyarugunga, Kicukiro",
            "Kanombe, Kicukiro"

    };
    String[] type = {
            "Single",
            "Single"

    };

    String[] reqnumber = {
            "Request N1",
            "Request N2"
    };

    String[] date = {
            "12/05/2018",
            "12/05/2018"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_request);
        Toolbar myNewRequest = findViewById(R.id.remove_toolbar);
        setSupportActionBar(myNewRequest);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        RemoveListAdapter adapter = new RemoveListAdapter(this, type, location, reqnumber, date);
        list = findViewById(R.id.removelist);
        list.setAdapter(adapter);

    }
}

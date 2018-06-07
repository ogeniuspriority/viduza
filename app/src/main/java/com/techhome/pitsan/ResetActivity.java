package com.techhome.pitsan;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class ResetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        Toolbar resetToolbar = findViewById(R.id.reset_toolbar);
        setSupportActionBar(resetToolbar);
        ActionBar resetactionBar = getSupportActionBar();
        resetactionBar.setDisplayHomeAsUpEnabled(true);
    }
}

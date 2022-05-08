package com.nedexplorer.myapplication;

import android.os.Bundle;
import android.widget.FrameLayout;

public class Bank extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("BANKS");

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.fragment_container); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_bank, contentFrameLayout);
    }
}

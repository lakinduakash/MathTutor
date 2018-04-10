package com.ultimatex.mathtuter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ChooserActivity extends AppCompatActivity {

    private static final String INTENT_EXTRA_KEY =MainActivity.EXTRA_KEY_OP;
    private String intentExtra;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);

        Intent intent = getIntent();
        intentExtra = intent.getStringExtra(INTENT_EXTRA_KEY);


    }
}

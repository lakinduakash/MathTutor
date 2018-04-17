package com.ultimatex.mathtuter;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;

import com.ultimatex.mathtuter.tinydb.TinyDB;

public class Settings extends AppCompatActivity {

    public static final String PREF_UPDATE_ON_STARTUP = "update_on_startup";

    TinyDB tinyDB;
    Button buttonUpdateDb;
    Switch switchStartUp;
    ProgressBar progressBar;
    View viewFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        tinyDB = new TinyDB(getApplicationContext());
        buttonUpdateDb = findViewById(R.id.button_update_db);
        switchStartUp = findViewById(R.id.switch_update_startup);
        progressBar = findViewById(R.id.prgressBar_update);
        viewFilter = findViewById(R.id.view_filter);

        if (tinyDB.getBoolean(PREF_UPDATE_ON_STARTUP)) {
            switchStartUp.setChecked(true);
        } else {
            switchStartUp.setChecked(false);
        }

        setListeners();

    }

    private void setListeners() {
        switchStartUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchStartUp.isChecked())
                    tinyDB.putBoolean(PREF_UPDATE_ON_STARTUP, true);
                else
                    tinyDB.putBoolean(PREF_UPDATE_ON_STARTUP, false);
            }
        });

        buttonUpdateDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UpdateDb().execute();

            }
        });
    }

    private class UpdateDb extends AsyncTask<Void, Void, Void> {
        boolean updated;

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            viewFilter.setVisibility(View.VISIBLE);
            switchStartUp.setClickable(false);
            buttonUpdateDb.setClickable(false);

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.INVISIBLE);
            viewFilter.setVisibility(View.INVISIBLE);
            switchStartUp.setClickable(true);
            buttonUpdateDb.setClickable(true);
        }
    }
}

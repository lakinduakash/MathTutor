package com.ultimatex.mathtuter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;

import com.ultimatex.mathtuter.tinydb.TinyDB;
import com.ultimatex.mathtuter.util.DataDownloader;
import com.ultimatex.mathtuter.util.QuestionUtil;
import com.ultimatex.mathtuter.util.QuestionUtilDbOperation;
import com.ultimatex.mathtuter.util.QuestionUtilSQLiteHelper;

public class Settings extends AppCompatActivity {

    public static final String PREF_UPDATE_ON_STARTUP = "update_on_startup";

    TinyDB tinyDB;
    Button buttonUpdateDb;
    Button buttonReset;
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
        buttonReset = findViewById(R.id.reset_progress);

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

                AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
                AlertDialog dialog = null;

                builder.setTitle("Update database?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new UpdateDb().execute();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                dialog = builder.create();
                dialog.show();


            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
                AlertDialog dialog = null;

                builder.setTitle("Reset progress?").setMessage("This will reset all solved answer").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new ResetProgressTask().execute();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                dialog = builder.create();
                dialog.show();


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

            SQLiteDatabase db = new QuestionUtilSQLiteHelper(getApplicationContext()).getReadableDatabase();
            DataDownloader downloader = new DataDownloader(getApplicationContext(), db);

            boolean updated = downloader.sqlDownload();
            downloader.imageDownload();

            new QuestionUtilDbOperation(db, null).insertData(updated, getApplicationContext());

            QuestionUtil.init(getApplicationContext(), db, true);
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

    private class ResetProgressTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            viewFilter.setVisibility(View.VISIBLE);
            switchStartUp.setClickable(false);
            buttonUpdateDb.setClickable(false);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            SQLiteDatabase db = new QuestionUtilSQLiteHelper(getApplicationContext()).getReadableDatabase();
            new QuestionUtilDbOperation(db, OperationArgs.EXTRA_ADD).updateSolved(0, "false");
            new QuestionUtilDbOperation(db, OperationArgs.EXTRA_SUB).updateSolved(0, "false");
            new QuestionUtilDbOperation(db, OperationArgs.EXTRA_MUL).updateSolved(0, "false");
            new QuestionUtilDbOperation(db, OperationArgs.EXTRA_DIV).updateSolved(0, "false");

            QuestionUtil.init(getApplicationContext(), db, true);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressBar.setVisibility(View.INVISIBLE);
            viewFilter.setVisibility(View.INVISIBLE);
            switchStartUp.setClickable(true);
            buttonUpdateDb.setClickable(true);
        }
    }
}

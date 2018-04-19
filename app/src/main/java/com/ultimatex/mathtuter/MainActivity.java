package com.ultimatex.mathtuter;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ultimatex.mathtuter.tinydb.TinyDB;
import com.ultimatex.mathtuter.util.AssetManagerHelper;
import com.ultimatex.mathtuter.util.DataDownloader;
import com.ultimatex.mathtuter.util.QuestionUtil;
import com.ultimatex.mathtuter.util.QuestionUtilDbOperation;
import com.ultimatex.mathtuter.util.QuestionUtilSQLiteHelper;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_KEY_OP = OperationArgs.EXTRA_KEY_OP;

    public static final String EXTRA_ADD = OperationArgs.EXTRA_ADD;
    public static final String EXTRA_SUB = OperationArgs.EXTRA_SUB;
    public static final String EXTRA_MUL = OperationArgs.EXTRA_MUL;
    public static final String EXTRA_DIV = OperationArgs.EXTRA_DIV;

    public SQLiteDatabase openedDB;
    TextView textViewHeading;

    Button buttonAdd;
    Button buttonSub;
    Button buttonMul;
    Button buttonDiv;

    TextView textViewLoading;
    ImageView imageViewSplash;
    private QuestionUtilSQLiteHelper questionUtilSQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = findViewById(R.id.add);
        buttonSub = findViewById(R.id.sub);
        buttonMul = findViewById(R.id.mul);
        buttonDiv = findViewById(R.id.div);

        textViewHeading = findViewById(R.id.textView_heading);

        textViewLoading = findViewById(R.id.textView_loading);

        imageViewSplash = findViewById(R.id.imageView_splash);


        DownloadFileFromURL dTak = new DownloadFileFromURL();
        dTak.execute();


        setButtonListeners();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, Settings.class);

        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setButtonListeners() {
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChooserActivity(v);
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChooserActivity(v);
            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChooserActivity(v);
            }
        });

        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChooserActivity(v);
            }
        });
    }

    private void startChooserActivity(View v) {
        String args = null;
        int id;

        id = v.getId();

        switch (id) {
            case R.id.add:
                args = EXTRA_ADD;
                break;
            case R.id.sub:
                args = EXTRA_SUB;
                break;
            case R.id.mul:
                args = EXTRA_MUL;
                break;
            case R.id.div:
                args = EXTRA_DIV;
                break;
        }

        Intent intent = new Intent(this, ChooserActivity.class);
        intent.putExtra(EXTRA_KEY_OP, args);
        startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {

        questionUtilSQLiteHelper.close();
        super.onDestroy();
    }


    private class DownloadFileFromURL extends AsyncTask<Void, String, String> {

        DataDownloader downloader;


        /**
         * Before starting background thread Show Progress Bar Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(Void... voids) {

            questionUtilSQLiteHelper = new QuestionUtilSQLiteHelper(getApplicationContext());
            openedDB = questionUtilSQLiteHelper.getReadableDatabase();

            downloader = new DataDownloader(MainActivity.this, MainActivity.this.openedDB);
            QuestionUtilDbOperation operation = new QuestionUtilDbOperation(openedDB, null);

            operation.insertData(MainActivity.this);
            AssetManagerHelper.copyImageAssets(MainActivity.this);

            TinyDB tinyDB = new TinyDB(getApplicationContext());

            tinyDB.putBoolean(Settings.PREF_UPDATE_ON_STARTUP, tinyDB.getBoolean(Settings.PREF_UPDATE_ON_STARTUP, true));

            if (tinyDB.getBoolean(Settings.PREF_UPDATE_ON_STARTUP)) {
                boolean updated = sqlDownloadTask();
                operation.insertData(updated, MainActivity.this);
                imageDownloadTask();
            }


            QuestionUtil.init(MainActivity.this, openedDB);
            QuestionUtil.randomizeQuestionOrder();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }


            return null;
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            //progressBarStart.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        @Override
        protected void onPostExecute(String file_url) {
            // hide progress bar
            textViewLoading.setVisibility(View.INVISIBLE);
            imageViewSplash.setVisibility(View.GONE);

            textViewHeading.setVisibility(View.VISIBLE);
            buttonAdd.setVisibility(View.VISIBLE);
            buttonSub.setVisibility(View.VISIBLE);
            buttonMul.setVisibility(View.VISIBLE);
            buttonDiv.setVisibility(View.VISIBLE);

        }


        boolean sqlDownloadTask() {
            return downloader.sqlDownload();
        }

        void imageDownloadTask() {
            downloader.imageDownload();
        }


    }
}

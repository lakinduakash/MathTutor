package com.ultimatex.mathtuter;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ultimatex.mathtuter.util.QuestionUtil;
import com.ultimatex.mathtuter.util.QuestionUtilDbOperation;
import com.ultimatex.mathtuter.util.QuestionUtilSQLiteHelper;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_KEY_OP = OperationArgs.EXTRA_KEY_OP;

    public static final String EXTRA_ADD = OperationArgs.EXTRA_ADD;
    public static final String EXTRA_SUB = OperationArgs.EXTRA_SUB;
    public static final String EXTRA_MUL = OperationArgs.EXTRA_MUL;
    public static final String EXTRA_DIV = OperationArgs.EXTRA_DIV;

    public static SQLiteDatabase openedDB;
    TextView textViewHeading;

    Button buttonAdd;
    Button buttonSub;
    Button buttonMul;
    Button buttonDiv;
    ProgressBar progressBarStart;
    TextView textViewLoading;
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
        progressBarStart = findViewById(R.id.progressBar_start);


        DownloadFileFromURL dTak = new DownloadFileFromURL();
        dTak.execute("http://tapidola.com/index.php");


        setButtonListeners();


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


    private class DownloadFileFromURL extends AsyncTask<String, String, String> {

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
        protected String doInBackground(String... f_url) {

            boolean updateDb = downloadTask(f_url[0]);
            dbInit(updateDb);


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
            progressBarStart.setVisibility(View.INVISIBLE);
            textViewLoading.setVisibility(View.INVISIBLE);

            textViewHeading.setVisibility(View.VISIBLE);
            buttonAdd.setVisibility(View.VISIBLE);
            buttonSub.setVisibility(View.VISIBLE);
            buttonMul.setVisibility(View.VISIBLE);
            buttonDiv.setVisibility(View.VISIBLE);

        }


        void dbInit(boolean availNew) {
            questionUtilSQLiteHelper = new QuestionUtilSQLiteHelper(getApplicationContext());
            openedDB = questionUtilSQLiteHelper.getReadableDatabase();

            new QuestionUtilDbOperation(openedDB, null).insertData(availNew, getApplicationContext());
            QuestionUtil.init(getApplicationContext());
        }

        boolean downloadTask(String _url) {
            int count;
            try {
                URL url = new URL(_url);
                URLConnection connection = url.openConnection();
                connection.connect();

                // this will be useful so that you can show a tipical 0-100%
                // progress bar
                int lengthOfFile = connection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream(),
                        8192);

                // Output stream
                OutputStream output = new FileOutputStream(getApplicationContext().getFilesDir().getPath() + "/dfile");

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    //publishProgress("" + (int) ((total * 100) / lengthOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();
                return true;

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
                return false;
            }
        }


    }
}

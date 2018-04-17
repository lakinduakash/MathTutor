package com.ultimatex.mathtuter.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DataDownloader {

    public static final String EXTENSION = ".jpg";
    public static final String SQL_FILE = "questions.sql";
    public static final String BASE_URL = "http://192.168.1.101/";

    private Context context;
    private SQLiteDatabase db;

    public DataDownloader(Context context, SQLiteDatabase db) {
        this.context = context;
        this.db = db;
    }

    public boolean sqlDownload() {
        return download(BASE_URL + SQL_FILE, new File(context.getFilesDir().getPath() + "/" + SQL_FILE));
    }

    public void imageDownload() {
        imageDownloadTask(QuestionsDbContract.AdditionEntry.TABLE_NAME);
        imageDownloadTask(QuestionsDbContract.SubtractionEntry.TABLE_NAME);
        imageDownloadTask(QuestionsDbContract.MultiplicationEntry.TABLE_NAME);
        imageDownloadTask(QuestionsDbContract.DivisionEntry.TABLE_NAME);

    }

    private void imageDownloadTask(String tableName) {
        Cursor cursor = getCursor(tableName);
        String filename = null;
        String url = null;
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndexOrThrow(QuestionsDbContract._ID));
            String type = cursor.getString(cursor.getColumnIndexOrThrow(QuestionsDbContract.COLUMN_NAME_TYPE));

            if (type.equals("i")) {

                filename = tableName + "_" + id + EXTENSION;
                url = BASE_URL + filename;
                File file = new File(context.getFilesDir().getPath() + "/" + filename);

                if (!file.exists()) {
                    if (!download(url, file)) {
                        Log.e("downloadFailed", "from " + url + " downloadFailed");
                    }
                }
            }
        }
    }

    private boolean download(String s_url, File file) {
        int count;
        try {
            URL url = new URL(s_url);
            URLConnection connection = url.openConnection();
            connection.connect();

            // download the file
            InputStream input = new BufferedInputStream(url.openStream(),
                    8192);

            // Output stream
            OutputStream output = new FileOutputStream(file);

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

            Log.d("DownloadSucsess", "from " + s_url);
            return true;

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
            return false;
        }
    }

    private Cursor getCursor(String table) {
        return db.query(table, new String[]{QuestionsDbContract._ID, QuestionsDbContract.COLUMN_NAME_TYPE},
                null, null, null, null, null);
    }


}
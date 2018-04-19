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
    public static final String BASE_URL = "http://192.168.1.101/mathtutor/";

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

        boolean forceDownload = checkUrl(BASE_URL + "force");
        imageDownloadTask(QuestionsDbContract.AdditionEntry.TABLE_NAME, forceDownload);
        imageDownloadTask(QuestionsDbContract.SubtractionEntry.TABLE_NAME, forceDownload);
        imageDownloadTask(QuestionsDbContract.MultiplicationEntry.TABLE_NAME, forceDownload);
        imageDownloadTask(QuestionsDbContract.DivisionEntry.TABLE_NAME, forceDownload);

    }

    private void imageDownloadTask(String tableName, boolean forceDownload) {
        Cursor cursor = getCursor(tableName);
        String filename = null;
        String url = null;
        String extension = null;
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndexOrThrow(QuestionsDbContract._ID));
            String type = cursor.getString(cursor.getColumnIndexOrThrow(QuestionsDbContract.COLUMN_NAME_TYPE));
            extension = cursor.getString(cursor.getColumnIndexOrThrow(QuestionsDbContract.COLUMN_NAME_OP1));

            if (type.equals("i")) {
                if (!extension.equals("0"))
                    filename = tableName + "_" + id + EXTENSION;
                else
                    filename = tableName + "_" + id + "." + extension;
                url = BASE_URL + filename;
                File file = new File(context.getFilesDir().getPath() + "/" + filename);

                if (!file.exists() || forceDownload) {
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

            Log.d("DownloadSuccess", "from " + s_url);
            return true;

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
            return false;
        }
    }

    private boolean checkUrl(String s_url) {
        int count;
        try {
            URL url = new URL(s_url);
            URLConnection connection = url.openConnection();
            connection.connect();
            url.openStream().close();

            return true;
        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
            return false;
        }
    }

    private Cursor getCursor(String table) {
        return db.query(table, new String[]{QuestionsDbContract._ID, QuestionsDbContract.COLUMN_NAME_TYPE, QuestionsDbContract.COLUMN_NAME_OP1},
                null, null, null, null, null);
    }


}

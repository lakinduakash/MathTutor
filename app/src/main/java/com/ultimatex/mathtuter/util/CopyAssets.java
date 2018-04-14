package com.ultimatex.mathtuter.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class CopyAssets {


    public static boolean copySQL(Context context) {

        String assetsPath = "databases/questions.sql";
        String dataPath = context.getFilesDir().getPath();
        String fileName = "/questions.sql";

        File file =new File(dataPath+fileName);

        if(!file.exists()) {

            try {

                OutputStream myOutput = new FileOutputStream(dataPath + fileName);
                byte[] buffer = new byte[1024];
                int length;
                InputStream myInput = context.getAssets().open(assetsPath);
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }
                myInput.close();
                myOutput.flush();
                myOutput.close();

                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    return false;

    }

    public static ArrayList<String> readSQL(Context context) {
        String dataPath = context.getFilesDir().getPath();
        String fileName = "/questions.sql";

        return readFile(dataPath + fileName);


    }

    private static ArrayList<String> readFile(String filePath) {
        ArrayList<String> arrayList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                arrayList.add(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

}

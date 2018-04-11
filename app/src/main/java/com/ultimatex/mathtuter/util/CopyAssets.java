package com.ultimatex.mathtuter.util;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyAssets {


    public static boolean copyDatabase(Context context) {

        String assetsPath = "databases/questions.db";
        String dataPath = context.getFilesDir().getPath();
        String fileName = "/questions.db";

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
}

package com.ultimatex.mathtuter.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class AssetManagerHelper {


    public static boolean copySQL(Context context) {

        String assetsPath = "databases/questions.sql";
        String dataPath = context.getFilesDir().getPath();
        String fileName = "/questions.sql";

        File file = new File(dataPath + fileName);

        if (!file.exists()) {

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

    public static boolean copySQL(Context context, boolean force) {

        String assetsPath = "databases/questions.sql";
        String dataPath = context.getFilesDir().getPath();
        String fileName = "/questions.sql";

        File file = new File(dataPath + fileName);

        if (!file.exists() || force) {

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

    public static void copy(File sourceLocation, File targetLocation) throws IOException {
        if (sourceLocation.isDirectory()) {
            copyDirectory(sourceLocation, targetLocation);
        } else {
            copyFile(sourceLocation, targetLocation);
        }
    }

    private static void copyDirectory(File source, File target) throws IOException {
        if (!target.exists()) {
            boolean i = target.mkdir();
        }

        for (String f : source.list()) {
            copy(new File(source, f), new File(target, f));
        }
    }

    private static void copyFile(File source, File target) throws IOException {
        try (
                InputStream in = new FileInputStream(source);
                OutputStream out = new FileOutputStream(target)
        ) {
            byte[] buf = new byte[1024];
            int length;
            while ((length = in.read(buf)) > 0) {
                out.write(buf, 0, length);
            }
        }
    }


    public static void copyImageAssets(Context context) {
        AssetManager assetManager = context.getAssets();
        String[] files = null;
        try {
            files = assetManager.list("images");
        } catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }
        if (files != null)
            for (String filename : files) {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open("images/" + filename);
                File outFile = new File(context.getFilesDir(), filename);

                if (!outFile.exists()) {
                    out = new FileOutputStream(outFile);
                    copyFile(in, out);
                } else {
                    Log.e("fileExist", "Failed to copy asset file: " + filename + "File exist");
                }
            } catch (IOException e) {
                Log.e("tag", "Failed to copy asset file: " + filename, e);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        // NOOP
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        // NOOP
                    }
                }
            }
        }
    }

    public static void copyImageAssets(Context context, boolean force) {
        AssetManager assetManager = context.getAssets();
        String[] files = null;
        try {
            files = assetManager.list("images");
        } catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }
        if (files != null)
            for (String filename : files) {
                InputStream in = null;
                OutputStream out = null;
                try {
                    in = assetManager.open("images/" + filename);
                    File outFile = new File(context.getFilesDir(), filename);

                    if (!outFile.exists() || force) {
                        out = new FileOutputStream(outFile);
                        copyFile(in, out);
                    } else {
                        Log.e("fileExist", "Override to copy asset file: " + filename);
                    }
                } catch (IOException e) {
                    Log.e("tag", "Failed to copy asset file: " + filename, e);
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            // NOOP
                        }
                    }
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            // NOOP
                        }
                    }
                }
            }
    }

    private static void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    //todo copy images from assets

}

package com.ultimatex.mathtuter.util;

import android.content.Context;

import com.ultimatex.mathtuter.MainActivity;
import com.ultimatex.mathtuter.tinydb.TinyDB;

import java.util.ArrayList;
import java.util.Calendar;

public class QuestionUtil {

    public static final String SP_ADD = "add";
    public static final String SP_SUB = "sub";
    public static final String SP_DIV = "div";
    public static final String SP_MUL = "mul";

    public static final String LAST_DATE = "date";

    public static ArrayList<Integer> ID_ADD;
    public static ArrayList<Integer> ID_SUB;
    public static ArrayList<Integer> ID_MUL;
    public static ArrayList<Integer> ID_DIV;

    private static TinyDB tinyDB;

    public static void init(Context context) {
        tinyDB = new TinyDB(context);

        Calendar calendar = Calendar.getInstance();
        boolean isOutdated = isOutDated();

        tinyDB.putInt(LAST_DATE, calendar.get(Calendar.DAY_OF_YEAR));

        if (tinyDB.getListInt(SP_ADD).isEmpty() || isOutdated) {
            generateNewIds(MainActivity.EXTRA_ADD);
            tinyDB.putListInt(SP_ADD, ID_ADD);
        } else {
            ID_ADD = tinyDB.getListInt(SP_ADD);
        }

        if (tinyDB.getListInt(SP_SUB).isEmpty() || isOutdated) {
            generateNewIds(MainActivity.EXTRA_SUB);
            tinyDB.putListInt(SP_SUB, ID_SUB);
        } else {
            ID_SUB = tinyDB.getListInt(SP_SUB);
        }

        if (tinyDB.getListInt(SP_MUL).isEmpty() || isOutdated) {
            generateNewIds(MainActivity.EXTRA_MUL);
            tinyDB.putListInt(SP_MUL, ID_MUL);
        } else {
            ID_MUL = tinyDB.getListInt(SP_MUL);
        }

        if (tinyDB.getListInt(SP_DIV).isEmpty() || isOutdated) {
            generateNewIds(MainActivity.EXTRA_DIV);
            tinyDB.putListInt(SP_DIV, ID_DIV);
        } else {
            ID_DIV = tinyDB.getListInt(SP_DIV);
        }


    }

    private static void generateNewIds(String op) {
        {//test
            ID_ADD = new ArrayList<Integer>();
            ID_SUB = new ArrayList<Integer>();
            ID_MUL = new ArrayList<Integer>();
            ID_DIV = new ArrayList<Integer>();

            for (int i = 1000; i < 1100; i++) {
                ID_ADD.add(i);
                ID_SUB.add(i);
                ID_MUL.add(i);
                ID_DIV.add(i);
            }

        }
    }

    private static boolean isOutDated() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_YEAR);

        return tinyDB.getInt(LAST_DATE) != day;
    }

    public static ArrayList<Integer> getIds(String op) {
        if (op.equals(MainActivity.EXTRA_ADD))
            return ID_ADD;
        if (op.equals(MainActivity.EXTRA_SUB))
            return ID_SUB;
        if (op.equals(MainActivity.EXTRA_MUL))
            return ID_MUL;
        if (op.equals(MainActivity.EXTRA_DIV))
            return ID_DIV;
        return null;
    }

    public static String getQuestion(int id, String op) {
        return "Hello";
    }

    public static String getAnswer(int id, String op) {
        return "Answer";
    }

    public static String[] getOptions(int id, String op) {
        return new String[]{"a", "b", "c", "d"};
    }

    public static boolean getSolved(int id, String op) {
        return true;
    }

    public static boolean setSolved(int id, String op) {
        return true;
    }

    public static int getType(int id, String op) {
        return 0;
    }

    public static void clearSolved(int id, String op) {

    }


}

package com.ultimatex.mathtuter.util;

import android.content.Context;

import com.ultimatex.mathtuter.OperationArgs;
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
            generateNewIds(OperationArgs.EXTRA_ADD);
            tinyDB.putListInt(SP_ADD, ID_ADD);
        } else {
            ID_ADD = tinyDB.getListInt(SP_ADD);
        }

        if (tinyDB.getListInt(SP_SUB).isEmpty() || isOutdated) {
            generateNewIds(OperationArgs.EXTRA_SUB);
            tinyDB.putListInt(SP_SUB, ID_SUB);
        } else {
            ID_SUB = tinyDB.getListInt(SP_SUB);
        }

        if (tinyDB.getListInt(SP_MUL).isEmpty() || isOutdated) {
            generateNewIds(OperationArgs.EXTRA_MUL);
            tinyDB.putListInt(SP_MUL, ID_MUL);
        } else {
            ID_MUL = tinyDB.getListInt(SP_MUL);
        }

        if (tinyDB.getListInt(SP_DIV).isEmpty() || isOutdated) {
            generateNewIds(OperationArgs.EXTRA_DIV);
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

            for (int i = 0; i < 10; i++) {
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
        if (op.equals(OperationArgs.EXTRA_ADD))
            return ID_ADD;
        if (op.equals(OperationArgs.EXTRA_SUB))
            return ID_SUB;
        if (op.equals(OperationArgs.EXTRA_MUL))
            return ID_MUL;
        if (op.equals(OperationArgs.EXTRA_DIV))
            return ID_DIV;
        return null;
    }


}

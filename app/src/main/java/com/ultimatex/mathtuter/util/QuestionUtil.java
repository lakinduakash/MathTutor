package com.ultimatex.mathtuter.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ultimatex.mathtuter.OperationArgs;
import com.ultimatex.mathtuter.tinydb.TinyDB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class QuestionUtil {

    public static final String SP_ADD = "add";
    public static final String SP_SUB = "sub";
    public static final String SP_DIV = "div";
    public static final String SP_MUL = "mul";

    public static final String LAST_DATE = "date";

    private static final int MAX_Q = 15;
    private static final int MIN_Q = 4;

    private static Context context;

    private static ArrayList<Integer> ID_ADD;
    private static ArrayList<Integer> ID_SUB;
    private static ArrayList<Integer> ID_MUL;
    private static ArrayList<Integer> ID_DIV;

    private static TinyDB tinyDB;


    public static void init(Context context, SQLiteDatabase db) {
        tinyDB = new TinyDB(context);
        QuestionUtil.context = context;


        Calendar calendar = Calendar.getInstance();
        boolean isOutdated = isOutDated(false);

        tinyDB.putInt(LAST_DATE, calendar.get(Calendar.DAY_OF_YEAR));

        if (tinyDB.getListInt(SP_ADD).isEmpty() || isOutdated) {
            generateNewIds(OperationArgs.EXTRA_ADD, db);
            tinyDB.putListInt(SP_ADD, ID_ADD);
        } else {
            ID_ADD = tinyDB.getListInt(SP_ADD);
        }

        if (tinyDB.getListInt(SP_SUB).isEmpty() || isOutdated) {
            generateNewIds(OperationArgs.EXTRA_SUB, db);
            tinyDB.putListInt(SP_SUB, ID_SUB);
        } else {
            ID_SUB = tinyDB.getListInt(SP_SUB);
        }

        if (tinyDB.getListInt(SP_MUL).isEmpty() || isOutdated) {
            generateNewIds(OperationArgs.EXTRA_MUL, db);
            tinyDB.putListInt(SP_MUL, ID_MUL);
        } else {
            ID_MUL = tinyDB.getListInt(SP_MUL);
        }

        if (tinyDB.getListInt(SP_DIV).isEmpty() || isOutdated) {
            generateNewIds(OperationArgs.EXTRA_DIV, db);
            tinyDB.putListInt(SP_DIV, ID_DIV);
        } else {
            ID_DIV = tinyDB.getListInt(SP_DIV);
        }


    }

    public static void init(Context context, SQLiteDatabase db, boolean flagForceGenerate) {
        tinyDB = new TinyDB(context);
        QuestionUtil.context = context;


        Calendar calendar = Calendar.getInstance();
        boolean isOutdated = isOutDated(flagForceGenerate);

        tinyDB.putInt(LAST_DATE, calendar.get(Calendar.DAY_OF_YEAR));

        if (tinyDB.getListInt(SP_ADD).isEmpty() || isOutdated) {
            generateNewIds(OperationArgs.EXTRA_ADD, db);
            tinyDB.putListInt(SP_ADD, ID_ADD);
        } else {
            ID_ADD = tinyDB.getListInt(SP_ADD);
        }

        if (tinyDB.getListInt(SP_SUB).isEmpty() || isOutdated) {
            generateNewIds(OperationArgs.EXTRA_SUB, db);
            tinyDB.putListInt(SP_SUB, ID_SUB);
        } else {
            ID_SUB = tinyDB.getListInt(SP_SUB);
        }

        if (tinyDB.getListInt(SP_MUL).isEmpty() || isOutdated) {
            generateNewIds(OperationArgs.EXTRA_MUL, db);
            tinyDB.putListInt(SP_MUL, ID_MUL);
        } else {
            ID_MUL = tinyDB.getListInt(SP_MUL);
        }

        if (tinyDB.getListInt(SP_DIV).isEmpty() || isOutdated) {
            generateNewIds(OperationArgs.EXTRA_DIV, db);
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

            for (int i = 1; i < 11; i++) {
                ID_ADD.add(i);
                ID_SUB.add(i);
                ID_MUL.add(i);
                ID_DIV.add(i);
            }

        }
    }

    private static void generateNewIds(String op, SQLiteDatabase db) {
        String table = null;
        ArrayList<Integer> arrayList = null;

        switch (op) {
            case OperationArgs.EXTRA_ADD:
                table = QuestionsDbContract.AdditionEntry.TABLE_NAME;
                ID_ADD = new ArrayList<>();
                arrayList = ID_ADD;
                break;
            case OperationArgs.EXTRA_SUB:
                table = QuestionsDbContract.SubtractionEntry.TABLE_NAME;
                ID_SUB = new ArrayList<>();
                arrayList = ID_SUB;
                break;
            case OperationArgs.EXTRA_MUL:
                table = QuestionsDbContract.MultiplicationEntry.TABLE_NAME;
                ID_MUL = new ArrayList<>();
                arrayList = ID_MUL;
                break;
            case OperationArgs.EXTRA_DIV:
                table = QuestionsDbContract.DivisionEntry.TABLE_NAME;
                ID_DIV = new ArrayList<>();
                arrayList = ID_DIV;
        }

        Cursor cursor = db.query(table, new String[]{QuestionsDbContract._ID, QuestionsDbContract.COLUMN_NAME_SOLVED}, QuestionsDbContract.COLUMN_NAME_SOLVED +
                "=?", new String[]{"false"}, null, null, null, Integer.toString(MAX_Q));
        if (cursor.getCount() <= MIN_Q) {
            new QuestionUtilDbOperation(db, op).updateSolved(0, "false");
            cursor = db.query(table, new String[]{QuestionsDbContract._ID, QuestionsDbContract.COLUMN_NAME_SOLVED}, QuestionsDbContract.COLUMN_NAME_SOLVED +
                    "=?", new String[]{"false"}, null, null, null, Integer.toString(MAX_Q));
        }
        while (cursor.moveToNext()) {
            if (arrayList != null)
                arrayList.add(cursor.getInt(cursor.getColumnIndexOrThrow(QuestionsDbContract._ID)));
        }
        cursor.close();

    }

    private static boolean isOutDated(boolean force) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_YEAR);

        return force || tinyDB.getInt(LAST_DATE) != day;
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


    public static void randomizeQuestionOrder() {
        Collections.shuffle(ID_ADD);
        Collections.shuffle(ID_SUB);
        Collections.shuffle(ID_MUL);
        Collections.shuffle(ID_DIV);
    }


}

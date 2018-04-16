package com.ultimatex.mathtuter.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static com.ultimatex.mathtuter.OperationArgs.EXTRA_ADD;
import static com.ultimatex.mathtuter.OperationArgs.EXTRA_DIV;
import static com.ultimatex.mathtuter.OperationArgs.EXTRA_MUL;
import static com.ultimatex.mathtuter.OperationArgs.EXTRA_SUB;
import static com.ultimatex.mathtuter.util.QuestionsDbContract.COLUMN_NAME_ANSWER;
import static com.ultimatex.mathtuter.util.QuestionsDbContract.COLUMN_NAME_OP1;
import static com.ultimatex.mathtuter.util.QuestionsDbContract.COLUMN_NAME_OP2;
import static com.ultimatex.mathtuter.util.QuestionsDbContract.COLUMN_NAME_OP3;
import static com.ultimatex.mathtuter.util.QuestionsDbContract.COLUMN_NAME_OP4;
import static com.ultimatex.mathtuter.util.QuestionsDbContract.COLUMN_NAME_QUESTION;
import static com.ultimatex.mathtuter.util.QuestionsDbContract.COLUMN_NAME_SOLVED;
import static com.ultimatex.mathtuter.util.QuestionsDbContract.COLUMN_NAME_TYPE;

public class QuestionUtilDbOperation {

    private static final String[] COLUMNS = {
            COLUMN_NAME_QUESTION,
            COLUMN_NAME_OP1,
            COLUMN_NAME_OP2,
            COLUMN_NAME_OP3,
            COLUMN_NAME_OP4,
            COLUMN_NAME_TYPE,
            COLUMN_NAME_ANSWER,
            COLUMN_NAME_SOLVED,
            QuestionsDbContract._ID
    };

    private static final String SELECTION = QuestionsDbContract._ID + " = ?";

    private SQLiteDatabase database;
    private String table;

    private SQLExecListener sqlExecListener;

    public QuestionUtilDbOperation(SQLiteDatabase db, String op) {
        database = db;

        if (op != null) {
            switch (op) {
                case EXTRA_ADD:
                    table = QuestionsDbContract.AdditionEntry.TABLE_NAME;
                    break;
                case EXTRA_SUB:
                    table = QuestionsDbContract.SubtractionEntry.TABLE_NAME;
                    break;
                case EXTRA_MUL:
                    table = QuestionsDbContract.MultiplicationEntry.TABLE_NAME;
                    break;
                case EXTRA_DIV:
                    table = QuestionsDbContract.DivisionEntry.TABLE_NAME;
                    break;
            }
        }

    }

    public void insertData(boolean availNew, Context context) {

        boolean c = AssetManagerHelper.copySQL(context);
        if (availNew) {
            ArrayList<String> arrayList = AssetManagerHelper.readSQL(context);

            for (String i : arrayList)
                database.execSQL(i);
        } else {
            if (c) {
                ArrayList<String> arrayList = AssetManagerHelper.readSQL(context);

                for (String i : arrayList)
                    database.execSQL(i);
            }
        }


    }

    public ArrayList<String> getQuestion(int id) {
        ArrayList<String> arrayList = new ArrayList<String>();
        Cursor cursor = database.query(table, COLUMNS, SELECTION, new String[]{Integer.toString(id)}, null, null, null);
        while (cursor.moveToNext()) {
            arrayList.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMNS[0])));
            arrayList.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMNS[1])));
            arrayList.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMNS[2])));
            arrayList.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMNS[3])));
            arrayList.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMNS[4])));
            arrayList.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMNS[5])));
            arrayList.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMNS[6])));
            arrayList.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMNS[7])));
            arrayList.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMNS[8])));
        }
        cursor.close();
        return arrayList;
    }

    public int updateSolved(int id, String solved) {
        ContentValues values = new ContentValues();
        values.put(QuestionsDbContract.COLUMN_NAME_SOLVED, solved);
        return database.update(table, values, SELECTION, new String[]{Integer.toString(id)});
    }

    public void setSqlExecListener(SQLExecListener sqlExecListener) {
        this.sqlExecListener = sqlExecListener;
    }

}

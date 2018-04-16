package com.ultimatex.mathtuter.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuestionUtilSQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Questions.db";
    public static final int VERSION = 1;

    private Context mContext;

    public QuestionUtilSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(QuestionsDbContract.AdditionEntry.CREATE_TABLE);
        db.execSQL(QuestionsDbContract.MultiplicationEntry.CREATE_TABLE);
        db.execSQL(QuestionsDbContract.DivisionEntry.CREATE_TABLE);
        db.execSQL(QuestionsDbContract.SubtractionEntry.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
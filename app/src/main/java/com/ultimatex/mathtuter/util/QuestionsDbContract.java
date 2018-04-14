package com.ultimatex.mathtuter.util;

import android.provider.BaseColumns;

public class QuestionsDbContract implements BaseColumns {

    public static final String COLUMN_NAME_QUESTION = "question";
    public static final String COLUMN_NAME_TYPE = "type";
    public static final String COLUMN_NAME_OP1 = "op1";
    public static final String COLUMN_NAME_OP2 = "op2";
    public static final String COLUMN_NAME_OP3 = "op3";
    public static final String COLUMN_NAME_OP4 = "op4";
    public static final String COLUMN_NAME_ANSWER = "answer";
    public static final String COLUMN_NAME_SOLVED = "solved";

    private QuestionsDbContract() {
    }


    public static class AdditionEntry implements BaseColumns {
        public static final String TABLE_NAME = "Addition";

        public static final String COLUMN_NAME_QUESTION = QuestionsDbContract.COLUMN_NAME_QUESTION;
        public static final String COLUMN_NAME_TYPE = QuestionsDbContract.COLUMN_NAME_TYPE;
        public static final String COLUMN_NAME_OP1 = QuestionsDbContract.COLUMN_NAME_OP1;
        public static final String COLUMN_NAME_OP2 = QuestionsDbContract.COLUMN_NAME_OP2;
        public static final String COLUMN_NAME_OP3 = QuestionsDbContract.COLUMN_NAME_OP3;
        public static final String COLUMN_NAME_OP4 = QuestionsDbContract.COLUMN_NAME_OP4;
        public static final String COLUMN_NAME_ANSWER = QuestionsDbContract.COLUMN_NAME_ANSWER;
        public static final String COLUMN_NAME_SOLVED = QuestionsDbContract.COLUMN_NAME_SOLVED;

        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INT PRIMARY KEY," +
                        COLUMN_NAME_QUESTION + " TEXT," +
                        COLUMN_NAME_TYPE + " CHAR(10) NOT NULL, " +
                        COLUMN_NAME_OP1 + " CHAR(10), " +
                        COLUMN_NAME_OP2 + " CHAR(10), " +
                        COLUMN_NAME_OP3 + " CHAR(10), " +
                        COLUMN_NAME_OP4 + " CHAR(10), " +
                        COLUMN_NAME_ANSWER + " CHAR(10), " +
                        COLUMN_NAME_SOLVED + " CHAR(10)" +
                        ")";


    }

    public static class SubtractionEntry implements BaseColumns {
        public static final String TABLE_NAME = "Subtraction";

        public static final String COLUMN_NAME_QUESTION = QuestionsDbContract.COLUMN_NAME_QUESTION;
        public static final String COLUMN_NAME_TYPE = QuestionsDbContract.COLUMN_NAME_TYPE;
        public static final String COLUMN_NAME_OP1 = QuestionsDbContract.COLUMN_NAME_OP1;
        public static final String COLUMN_NAME_OP2 = QuestionsDbContract.COLUMN_NAME_OP2;
        public static final String COLUMN_NAME_OP3 = QuestionsDbContract.COLUMN_NAME_OP3;
        public static final String COLUMN_NAME_OP4 = QuestionsDbContract.COLUMN_NAME_OP4;
        public static final String COLUMN_NAME_ANSWER = QuestionsDbContract.COLUMN_NAME_ANSWER;
        public static final String COLUMN_NAME_SOLVED = QuestionsDbContract.COLUMN_NAME_SOLVED;

        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INT PRIMARY KEY," +
                        COLUMN_NAME_QUESTION + " TEXT," +
                        COLUMN_NAME_TYPE + " CHAR(10) NOT NULL, " +
                        COLUMN_NAME_OP1 + " CHAR(10), " +
                        COLUMN_NAME_OP2 + " CHAR(10), " +
                        COLUMN_NAME_OP3 + " CHAR(10), " +
                        COLUMN_NAME_OP4 + " CHAR(10), " +
                        COLUMN_NAME_ANSWER + " CHAR(10), " +
                        COLUMN_NAME_SOLVED + " CHAR(10)" +
                        ")";

    }

    public static class DivisionEntry implements BaseColumns {
        public static final String TABLE_NAME = "Division";

        public static final String COLUMN_NAME_QUESTION = QuestionsDbContract.COLUMN_NAME_QUESTION;
        public static final String COLUMN_NAME_TYPE = QuestionsDbContract.COLUMN_NAME_TYPE;
        public static final String COLUMN_NAME_OP1 = QuestionsDbContract.COLUMN_NAME_OP1;
        public static final String COLUMN_NAME_OP2 = QuestionsDbContract.COLUMN_NAME_OP2;
        public static final String COLUMN_NAME_OP3 = QuestionsDbContract.COLUMN_NAME_OP3;
        public static final String COLUMN_NAME_OP4 = QuestionsDbContract.COLUMN_NAME_OP4;
        public static final String COLUMN_NAME_ANSWER = QuestionsDbContract.COLUMN_NAME_ANSWER;
        public static final String COLUMN_NAME_SOLVED = QuestionsDbContract.COLUMN_NAME_SOLVED;

        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INT PRIMARY KEY," +
                        COLUMN_NAME_QUESTION + " TEXT," +
                        COLUMN_NAME_TYPE + " CHAR(10) NOT NULL, " +
                        COLUMN_NAME_OP1 + " CHAR(10), " +
                        COLUMN_NAME_OP2 + " CHAR(10), " +
                        COLUMN_NAME_OP3 + " CHAR(10), " +
                        COLUMN_NAME_OP4 + " CHAR(10), " +
                        COLUMN_NAME_ANSWER + " CHAR(10), " +
                        COLUMN_NAME_SOLVED + " CHAR(10)" +
                        ")";

    }

    public static class MultiplyEntry implements BaseColumns {
        public static final String TABLE_NAME = "Multiplication";

        public static final String COLUMN_NAME_QUESTION = QuestionsDbContract.COLUMN_NAME_QUESTION;
        public static final String COLUMN_NAME_TYPE = QuestionsDbContract.COLUMN_NAME_TYPE;
        public static final String COLUMN_NAME_OP1 = QuestionsDbContract.COLUMN_NAME_OP1;
        public static final String COLUMN_NAME_OP2 = QuestionsDbContract.COLUMN_NAME_OP2;
        public static final String COLUMN_NAME_OP3 = QuestionsDbContract.COLUMN_NAME_OP3;
        public static final String COLUMN_NAME_OP4 = QuestionsDbContract.COLUMN_NAME_OP4;
        public static final String COLUMN_NAME_ANSWER = QuestionsDbContract.COLUMN_NAME_ANSWER;
        public static final String COLUMN_NAME_SOLVED = QuestionsDbContract.COLUMN_NAME_SOLVED;

        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INT PRIMARY KEY," +
                        COLUMN_NAME_QUESTION + " TEXT," +
                        COLUMN_NAME_TYPE + " CHAR(10) NOT NULL, " +
                        COLUMN_NAME_OP1 + " CHAR(10), " +
                        COLUMN_NAME_OP2 + " CHAR(10), " +
                        COLUMN_NAME_OP3 + " CHAR(10), " +
                        COLUMN_NAME_OP4 + " CHAR(10), " +
                        COLUMN_NAME_ANSWER + " CHAR(10), " +
                        COLUMN_NAME_SOLVED + " CHAR(10)" +
                        ")";

    }
}

package com.ultimatex.mathtuter.util;

import android.provider.BaseColumns;

public class QuestionsDbContract {

    private static final String COLUMN_NAME_QUESTION = "question";
    private static final String COLUMN_NAME_TYPE = "type";
    private static final String COLUMN_NAME_OP1 = "op1";
    private static final String COLUMN_NAME_OP2 = "op2";
    private static final String COLUMN_NAME_OP3 = "op3";
    private static final String COLUMN_NAME_OP4 = "op4";
    private static final String COLUMN_NAME_ANSWER = "answer";
    private static final String COLUMN_NAME_SOLVED = "solved";

    private QuestionsDbContract() {
    }

    ;

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

    }

    public static class MultiplyEntry implements BaseColumns {
        public static final String TABLE_NAME = "Multiply";

        public static final String COLUMN_NAME_QUESTION = QuestionsDbContract.COLUMN_NAME_QUESTION;
        public static final String COLUMN_NAME_TYPE = QuestionsDbContract.COLUMN_NAME_TYPE;
        public static final String COLUMN_NAME_OP1 = QuestionsDbContract.COLUMN_NAME_OP1;
        public static final String COLUMN_NAME_OP2 = QuestionsDbContract.COLUMN_NAME_OP2;
        public static final String COLUMN_NAME_OP3 = QuestionsDbContract.COLUMN_NAME_OP3;
        public static final String COLUMN_NAME_OP4 = QuestionsDbContract.COLUMN_NAME_OP4;
        public static final String COLUMN_NAME_ANSWER = QuestionsDbContract.COLUMN_NAME_ANSWER;
        public static final String COLUMN_NAME_SOLVED = QuestionsDbContract.COLUMN_NAME_SOLVED;

    }
}

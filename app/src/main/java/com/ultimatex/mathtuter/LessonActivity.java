package com.ultimatex.mathtuter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class LessonActivity extends AppCompatActivity {

    private static final String INTENT_EXTRA_KEY = OperationArgs.EXTRA_KEY_OP;
    private String intentExtra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        intentExtra = intent.getStringExtra(INTENT_EXTRA_KEY);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (OperationArgs.EXTRA_ADD.equals(intentExtra)) actionBar.setTitle(R.string.add);
            else if (OperationArgs.EXTRA_MUL.equals(intentExtra)) actionBar.setTitle(R.string.mul);
            else if (OperationArgs.EXTRA_SUB.equals(intentExtra)) actionBar.setTitle(R.string.sub);
            else if (OperationArgs.EXTRA_DIV.equals(intentExtra)) actionBar.setTitle(R.string.div);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
    }
}

package com.ultimatex.mathtuter;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity implements QuestionListFragment.OnFragmentInteractionListener, QuestionFragment.DbObjectListener {

    String intentExtra;
    Intent intent;
    volatile SQLiteDatabase db;

    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intent = getIntent();
        intentExtra = intent.getStringExtra(OperationArgs.EXTRA_KEY_OP);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (OperationArgs.EXTRA_ADD.equals(intentExtra)) actionBar.setTitle(R.string.add);
            else if (OperationArgs.EXTRA_MUL.equals(intentExtra)) actionBar.setTitle(R.string.mul);
            else if (OperationArgs.EXTRA_SUB.equals(intentExtra)) actionBar.setTitle(R.string.sub);
            else if (OperationArgs.EXTRA_DIV.equals(intentExtra)) actionBar.setTitle(R.string.div);
        }

        setContentView(R.layout.activity_question);

        new DbOpener().execute();

        FragmentManager fragmentManager;


        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        QuestionListFragment questionListFragment = QuestionListFragment.newInstance(intentExtra);

        fragmentTransaction.add(R.id.fragment_container_question, questionListFragment);


    }

    private void addQuestionFragment(int id) {
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();


        QuestionFragment questionFragment = QuestionFragment.newInstance(intentExtra, id);

        fragmentTransaction.replace(R.id.fragment_container_question, questionFragment).addToBackStack(intentExtra).commit();
    }

    @Override
    public void onFragmentInteraction(int id) {
        addQuestionFragment(id);
    }

    @Override
    public SQLiteDatabase getDatabase() {
        return db;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private class DbOpener extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            db = new com.ultimatex.mathtuter.util.QuestionUtilSQLiteHelper(QuestionActivity.this).getWritableDatabase();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            fragmentTransaction.commit();

        }
    }

}

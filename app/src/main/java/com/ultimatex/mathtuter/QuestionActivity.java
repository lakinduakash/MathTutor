package com.ultimatex.mathtuter;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity implements QuestionListFragment.OnFragmentInteractionListener, QuestionFragment.DbObjectListener {

    String intentExtraOP;
    Intent intent;
    volatile SQLiteDatabase db;

    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        intent = getIntent();
        intentExtraOP = intent.getStringExtra(OperationArgs.EXTRA_KEY_OP);

        new DbOpener().execute();

        FragmentManager fragmentManager;


        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        QuestionListFragment questionListFragment = new QuestionListFragment();

        fragmentTransaction.add(R.id.fragment_container_question, questionListFragment);


    }

    private void addQuestionFragment(int pos) {
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();


        QuestionFragment questionFragment = QuestionFragment.newInstance(intentExtraOP, pos);

        fragmentTransaction.replace(R.id.fragment_container_question, questionFragment).addToBackStack(intentExtraOP).commit();
    }

    @Override
    public void onFragmentInteraction(int position) {
        addQuestionFragment(position);
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

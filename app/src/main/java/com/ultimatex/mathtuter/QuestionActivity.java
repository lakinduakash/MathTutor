package com.ultimatex.mathtuter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity implements QuestionListFragment.OnFragmentInteractionListener {

    String intentExtraOP;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        intent = getIntent();
        intentExtraOP = intent.getStringExtra(MainActivity.EXTRA_KEY_OP);

        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        QuestionListFragment questionListFragment = new QuestionListFragment();

        fragmentTransaction.add(R.id.fragment_container_question, questionListFragment).commit();


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
}

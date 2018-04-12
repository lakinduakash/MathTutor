package com.ultimatex.mathtuter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity implements QuestionListFragment.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        QuestionListFragment questionListFragment = new QuestionListFragment();

        fragmentTransaction.add(R.id.fragment_container_question, questionListFragment).commit();


    }

    private void addFragment() {
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        QuestionFragment questionFragment = new QuestionFragment();

        fragmentTransaction.replace(R.id.fragment_container_question, questionFragment).addToBackStack("b").commit();
    }

    @Override
    public void onFragmentInteraction() {
        addFragment();
    }
}

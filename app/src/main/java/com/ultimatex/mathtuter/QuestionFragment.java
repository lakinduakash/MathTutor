package com.ultimatex.mathtuter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ultimatex.mathtuter.util.QuestionUtilDbOperation;

public class QuestionFragment extends Fragment {

    private static final String ARG_OP = "param1";
    private static final String ARG_ID = "param2";

    private String op;
    private int id;

    SQLiteDatabase db;
    private DbObjectListener mListener;

    public QuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuestionFragment.
     */
    public static QuestionFragment newInstance(String param1, int param2) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_OP, param1);
        args.putInt(ARG_ID, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListener = (DbObjectListener) getActivity();

        if (mListener != null)
            db = mListener.getDatabase();
        if (getArguments() != null) {
            op = getArguments().getString(ARG_OP);
            id = getArguments().getInt(ARG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        QuestionUtilDbOperation dbOperation = new QuestionUtilDbOperation(db, op);
        ((TextView) view.findViewById(R.id.textView_question)).setText(dbOperation.getQuestion(id).get(7) + "whoo");

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface DbObjectListener {
        SQLiteDatabase getDatabase();
    }


}

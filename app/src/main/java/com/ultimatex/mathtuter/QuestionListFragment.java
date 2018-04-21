package com.ultimatex.mathtuter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ultimatex.mathtuter.util.QuestionListAdapter;
import com.ultimatex.mathtuter.util.QuestionUtil;
import com.ultimatex.mathtuter.util.RecyclerItemClickListener;

import java.util.ArrayList;

public class QuestionListFragment extends Fragment {

    private final static String ARG_OP = "com.ultimatex.mathtutor.op785544";
    String op;

    RecyclerView rv;
    RecyclerView.LayoutManager rvl;
    RecyclerView.Adapter rva;

    ArrayList<Integer> idData;

    //TODO get args from activity

    private OnFragmentInteractionListener mListener;


    public static QuestionListFragment newInstance(String param1) {
        QuestionListFragment fragment = new QuestionListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_OP, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public QuestionListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            op = getArguments().getString(ARG_OP);
        }
        idData = QuestionUtil.getIds(op);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_list, container, false);

        rv = (RecyclerView) view.findViewById(R.id.list_questions);
        rv.setHasFixedSize(true);

        rvl = new LinearLayoutManager(view.getContext());
        rv.setLayoutManager(rvl);

        rva = new QuestionListAdapter(idData);
        rv.setAdapter(rva);

        //RecyclerView.ItemDecoration ri = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        //rv.addItemDecoration(ri);

        rv.addOnItemTouchListener(new RecyclerItemClickListener(view.getContext(), new RecyclerItemClickListener.OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                mListener.onFragmentInteraction(idData.get(position));
            }
        }));


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(int id);
    }
}

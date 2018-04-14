package com.ultimatex.mathtuter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
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

    RecyclerView rv;
    RecyclerView.LayoutManager rvl;
    RecyclerView.Adapter rva;

    ArrayList<Integer> idData;


    private OnFragmentInteractionListener mListener;

    public QuestionListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idData = QuestionUtil.getIds(OperationArgs.EXTRA_ADD);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_list, container, false);

        rv = (RecyclerView) view.findViewById(R.id.list_questions);
        rv.setHasFixedSize(true);

        rvl = new LinearLayoutManager(view.getContext());
        rv.setLayoutManager(rvl);

        rva = new QuestionListAdapter(idData);
        rv.setAdapter(rva);

        RecyclerView.ItemDecoration ri = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(ri);

        rv.addOnItemTouchListener(new RecyclerItemClickListener(view.getContext(), new RecyclerItemClickListener.OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                mListener.onFragmentInteraction(position);
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
        void onFragmentInteraction(int position);
    }
}

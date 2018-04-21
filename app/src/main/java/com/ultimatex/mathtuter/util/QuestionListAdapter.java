package com.ultimatex.mathtuter.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ultimatex.mathtuter.R;

import java.util.ArrayList;

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.ViewHolder> {

    private ArrayList<Integer> qId;

    public QuestionListAdapter(ArrayList<Integer> qId) {
        this.qId = qId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.question_list_item_view, parent, false
        );
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = "Question " + (position + 1);
        holder.textView.setText(title);
    }

    @Override
    public int getItemCount() {
        return qId.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView textView;

        public ViewHolder(View view) {
            super(view);
            this.cv = view.findViewById(R.id.card_view);
            this.textView = view.findViewById(R.id.textView_question_list);
        }

    }

}

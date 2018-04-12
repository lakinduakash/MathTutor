package com.ultimatex.mathtuter.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ultimatex.mathtuter.R;

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.ViewHolder> {

    private int qId[];

    public static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout ll;
        public ViewHolder(LinearLayout ll)
        {
            super(ll);
            this.ll =ll;
        }

    }

    public QuestionListAdapter(int qId[])
    {
        this.qId = qId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout ll=(LinearLayout) LayoutInflater.from(parent.getContext()).inflate(
                R.layout.question_list_item_view,parent,false
        );
        return new ViewHolder(ll);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LinearLayout ll=holder.ll;
        TextView tv=ll.findViewById(R.id.textView_question_list);
        tv.setText(""+position);
    }

    @Override
    public int getItemCount() {
        return qId.length;
    }

}
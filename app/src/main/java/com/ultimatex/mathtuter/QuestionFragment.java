package com.ultimatex.mathtuter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ultimatex.mathtuter.util.DataDownloader;
import com.ultimatex.mathtuter.util.QuestionType;
import com.ultimatex.mathtuter.util.QuestionUtilDbOperation;

import java.util.ArrayList;

public class QuestionFragment extends Fragment {

    static int ID = 0;
    static int COLUMN_NAME_QUESTION = 1;
    static int COLUMN_NAME_TYPE = 2;
    static int COLUMN_NAME_OP1 = 3;
    static int COLUMN_NAME_OP2 = 4;
    static int COLUMN_NAME_OP3 = 5;
    static int COLUMN_NAME_OP4 = 6;
    static int COLUMN_NAME_ANSWER = 7;
    static int COLUMN_NAME_SOLVED = 8;


    private static final String ARG_OP = "param1";
    private static final String ARG_ID = "param2";

    private String op;
    private int id;
    ArrayList<String> questionArray;
    ImageView imgView;
    TextView questionText;
    RadioGroup mcqRadio;

    RadioButton op1;
    RadioButton op2;
    RadioButton op3;
    RadioButton op4;

    private DbObjectListener mListener;
    EditText editText;
    private SQLiteDatabase db;
    private QuestionUtilDbOperation dbOperation;

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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dbOperation = new QuestionUtilDbOperation(db, op);
        questionArray = dbOperation.getQuestion(id);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question, container, false);

        createQuestion(view);

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


    private void createQuestion(View view) {
        imgView = view.findViewById(R.id.image_question);
        questionText = view.findViewById(R.id.question_text);
        editText = view.findViewById(R.id.editText_answer);
        mcqRadio = view.findViewById(R.id.mcq);

        op1 = view.findViewById(R.id.radio_op1);
        op2 = view.findViewById(R.id.radio_op2);
        op3 = view.findViewById(R.id.radio_op3);
        op4 = view.findViewById(R.id.radio_op4);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN) {
                    onSubmitAnswer(v.getText().toString());
                }
                onSubmitAnswer(v.getText().toString());
                return true;
            }
        });

        mcqRadio.setOnCheckedChangeListener(new RadioGroupListener());


        String type = questionArray.get(COLUMN_NAME_TYPE);

        if (QuestionType.IMAGE.equals(type)) {

            Activity activity = getActivity();

            String filename = dbOperation.getTable() + "_" + id + DataDownloader.EXTENSION;
            if (activity != null) {
                Bitmap bitmap = BitmapFactory.decodeFile(activity.getApplicationContext().getFilesDir().getPath() + "/" + filename);
                questionText.setVisibility(View.GONE);
                mcqRadio.setVisibility(View.GONE);
                imgView.setVisibility(View.VISIBLE);
                imgView.setImageBitmap(bitmap);
            }
        } else if (QuestionType.TEXT.equals(type)) {
            imgView.setVisibility(View.GONE);
            mcqRadio.setVisibility(View.GONE);
            questionText.setVisibility(View.VISIBLE);
            questionText.setText(questionArray.get(COLUMN_NAME_QUESTION));
        } else if (QuestionType.IMAGE_TEXT.equals(type)) {
            imgView.setVisibility(View.VISIBLE);
            mcqRadio.setVisibility(View.GONE);
            questionText.setVisibility(View.VISIBLE);
            questionText.setText(questionArray.get(COLUMN_NAME_QUESTION));
        } else if (QuestionType.MCQ.equals(type)) {
            mcqRadio.setVisibility(View.VISIBLE);
            imgView.setVisibility(View.GONE);
            editText.setVisibility(View.GONE);
            questionText.setText(questionArray.get(COLUMN_NAME_QUESTION));
            questionText.setVisibility(View.VISIBLE);
            op1.setText(questionArray.get(COLUMN_NAME_OP1));
            op2.setText(questionArray.get(COLUMN_NAME_OP2));
            op3.setText(questionArray.get(COLUMN_NAME_OP3));
            op4.setText(questionArray.get(COLUMN_NAME_OP4));
        }
    }

    private void onSubmitAnswer(String answer) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog dialog = null;

        if (questionArray.get(COLUMN_NAME_ANSWER).equals(answer)) {

            Activity activity = getActivity();
            View view = null;

            if (activity != null) {
                view = activity.getCurrentFocus();
            }
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            dbOperation.updateSolved(id, "true");
            builder.setMessage("Your answer is correct")
                    .setTitle("Congratulation").setPositiveButton("Go to Questions", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FragmentManager fm = getFragmentManager();
                    if (fm != null)
                        fm.popBackStackImmediate();
                }
            });
            dialog = builder.create();
        } else {
            builder.setMessage("Your answer is wrong")
                    .setTitle("Try again!").setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            dialog = builder.create();
        }

        dialog.show();
    }

    public interface DbObjectListener {
        SQLiteDatabase getDatabase();
    }

    private class RadioGroupListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            switch (checkedId) {
                case R.id.radio_op1:
                    QuestionFragment.this.onSubmitAnswer(op1.getText().toString());
                    break;
                case R.id.radio_op2:
                    QuestionFragment.this.onSubmitAnswer(op2.getText().toString());
                    break;
                case R.id.radio_op3:
                    QuestionFragment.this.onSubmitAnswer(op3.getText().toString());
                    break;
                case R.id.radio_op4:
                    QuestionFragment.this.onSubmitAnswer(op4.getText().toString());
            }
        }
    }



}

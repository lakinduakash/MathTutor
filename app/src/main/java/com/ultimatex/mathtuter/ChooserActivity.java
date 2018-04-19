package com.ultimatex.mathtuter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ChooserActivity extends AppCompatActivity {

    private static final String INTENT_EXTRA_KEY = OperationArgs.EXTRA_KEY_OP;
    private String intentExtra;

    Button lessonButon;
    Button quesButon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);

        lessonButon = findViewById(R.id.button_les);
        quesButon = findViewById(R.id.button_ques);

        Intent intent = getIntent();
        intentExtra = intent.getStringExtra(INTENT_EXTRA_KEY);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (OperationArgs.EXTRA_ADD.equals(intentExtra)) actionBar.setTitle(R.string.add);
            else if (OperationArgs.EXTRA_MUL.equals(intentExtra)) actionBar.setTitle(R.string.mul);
            else if (OperationArgs.EXTRA_SUB.equals(intentExtra)) actionBar.setTitle(R.string.sub);
            else if (OperationArgs.EXTRA_DIV.equals(intentExtra)) actionBar.setTitle(R.string.div);
        }

        setButtonListeners();

    }

    private void setButtonListeners(){
        lessonButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(v);
            }
        });

        quesButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(v);
            }
        });
    }

    private void startActivity(View v){

        int id = v.getId();
        Intent intent;

        if(id==R.id.button_les){
            intent =new Intent(this,LessonActivity.class);
            intent.putExtra(INTENT_EXTRA_KEY,intentExtra);
            startActivity(intent);
        }
        else{
            intent = new Intent(this,QuestionActivity.class);
            intent.putExtra(INTENT_EXTRA_KEY,intentExtra);
            startActivity(intent);
        }
    }
}

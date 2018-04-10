package com.ultimatex.mathtuter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_KEY_OP="com.ultimatex.mathtutor.operator";

    public static final String EXTRA_ADD="ADD";
    public static final String EXTRA_SUB="SUB";
    public static final String EXTRA_MUL="MUL";
    public static final String EXTRA_DIV="DIV";

    Button buttonAdd;
    Button buttonSub;
    Button buttonMul;
    Button buttonDiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = findViewById(R.id.add);
        buttonSub = findViewById(R.id.sub);
        buttonMul = findViewById(R.id.mul);
        buttonDiv = findViewById(R.id.div);

        setButtonListeners();


    }

    private void setButtonListeners()
    {
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChooserActivity(v);
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChooserActivity(v);
            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChooserActivity(v);
            }
        });

        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChooserActivity(v);
            }
        });
    }

    private void startChooserActivity(View v)
    {
        String args=null;
        int id;

        id = v.getId();

        switch (id)
        {
            case R.id.add: args=EXTRA_ADD; break;
            case R.id.sub: args=EXTRA_SUB; break;
            case R.id.mul: args=EXTRA_MUL; break;
            case R.id.div: args=EXTRA_DIV; break;
        }

        Intent intent = new Intent(this,ChooserActivity.class);
        intent.putExtra(EXTRA_KEY_OP,args);
        startActivity(intent);

    }





}

package com.sjsu.mingluliu.lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button mAddition;
    private Button mSubtraction;
    private Button mProduction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddition = (Button)findViewById(R.id.addition);
        mAddition.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String operator = "+";
                Intent i = new Intent(MainActivity.this, QuestionActivity.class);
                i.putExtra("operator", operator);
                startActivity(i);
            }
        });
        mSubtraction = (Button)findViewById(R.id.subtraction);
        mSubtraction.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String operator = "-";
                Intent i = new Intent(MainActivity.this, QuestionActivity.class);
                i.putExtra("operator", operator);
                startActivity(i);
            }
        });
        mProduction = (Button)findViewById(R.id.production);
        mProduction.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String operator = "*";
                Intent i = new Intent(MainActivity.this, QuestionActivity.class);
                i.putExtra("operator", operator);
                startActivity(i);
            }
        });
    }

}

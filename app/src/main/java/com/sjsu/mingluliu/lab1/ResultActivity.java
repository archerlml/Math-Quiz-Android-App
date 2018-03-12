package com.sjsu.mingluliu.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView tvResult = (TextView)findViewById(R.id.result);
        Bundle b = getIntent().getExtras();
        int score = b.getInt("score") + 1;
        if(score >= 10) {
            tvResult.setText("Your score is " + score + " !" + " Good job!");
        }else{
            tvResult.setText("Your score is " + score + " ." + " Good luck next time!");
        }
    }
}

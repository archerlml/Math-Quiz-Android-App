package com.sjsu.mingluliu.lab1;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class QuestionActivity extends AppCompatActivity {

    private Button mEnter;
    private EditText mEText;
    public int answerInput = -1;
    public int rightAnswer = -1;
    public String operator = "";
    public String correct = "Correct!";
    public String wrong = "Wrong!";
    public String timeup = "Time is up!";

    public int questionNumber = 1;
    public int score = 0;
    public CountDownTimer CDTObject;
//    public CountDownTimer CDTObject = new CountDownTimer(50000, 1000) {
//        public TextView tvCountDownTimer = (TextView)findViewById(R.id.cdt);
//        public void onTick(long millisUntilFinished) {
//            tvCountDownTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
//        }
//        public void onFinish() {
//            tvCountDownTimer.setText("time's up!");
//            Toast.makeText(QuestionActivity.this,
//                    timeup, Toast.LENGTH_SHORT).show();
//            CDTObject.cancel();
//            setQuestionView();
//            checkProcess();
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_question);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Configuration configInfo = getResources().getConfiguration();
        if(configInfo.orientation == Configuration.ORIENTATION_LANDSCAPE){
            LandscapeFragment landscapeFragment = new LandscapeFragment();
            fragmentTransaction.replace(android.R.id.content, landscapeFragment);
        }else{
            PortraitFragment portraitFragment = new PortraitFragment();
            fragmentTransaction.replace(android.R.id.content, portraitFragment);
        }

        fragmentTransaction.commit();

        Bundle extras = getIntent().getExtras();
        operator = extras.getString("operator");
        TextView tvOperator = (TextView)findViewById(R.id.operator);
        tvOperator.setText(operator);

        mEnter = (Button)findViewById(R.id.buttonNext);
        mEnter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer();
            }
        });
//        CDTObject.start();
        setQuestionView();

    }

    public void displayCDT(){
        if(CDTObject!=null){
            CDTObject.cancel();
        }
        CDTObject = new CountDownTimer(5000, 1000) {

            public TextView tvCountDownTimer = (TextView)findViewById(R.id.cdt);
            public void onTick(long millisUntilFinished) {
                tvCountDownTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
            }
            public void onFinish() {
                tvCountDownTimer.setText("time's up!");
                Toast.makeText(QuestionActivity.this,
                        timeup, Toast.LENGTH_SHORT).show();
                CDTObject.cancel();
                setQuestionView();
                checkProcess();
            }
        }.start();
    }

    public void setQuestionView(){

        mEText = (EditText) findViewById(R.id.editText);
        mEText.setText("");

        generateNumbers();
        setRightAnswer();

        displayProcess();


    }

    private void displayProcess(){
        TextView tvQuestionNumber = (TextView) findViewById(R.id.process);
        StringBuilder sb = new StringBuilder();
        sb.append("Question ").append(questionNumber - 1).append(" of ").append("10");
        String strQuestionNumber = sb.toString();
        tvQuestionNumber.setText(strQuestionNumber);
    }

    public void setRightAnswer(){
        TextView tvFirstNumber = (TextView)findViewById(R.id.firstNumber);
        String str1 = tvFirstNumber.getText().toString();
        int number1 = Integer.valueOf(str1);

        TextView tvSecondNumber = (TextView)findViewById(R.id.secondNumber);
        String str2 = tvSecondNumber.getText().toString();
        int number2 = Integer.valueOf(str2);

        TextView tvOperator = (TextView) findViewById(R.id.operator);
        String str3 = tvOperator.getText().toString();
        switch(str3){
            case "+":
                rightAnswer = number1 + number2;
                break;
            case "-":
                rightAnswer = number1 - number2;
                break;
            case "*":
                rightAnswer = number1 * number2;
                break;
        }
    }

    public void checkAnswer(){

        mEText = (EditText) findViewById(R.id.editText);
        String strET = mEText.getText().toString();
        if(!strET.equals("")){
            answerInput = Integer.valueOf(strET);
        }
//        mEText1 = (EditText) findViewById(R.id.editText1);
//        mEText2 = (EditText) findViewById(R.id.editText2);
//        String str1 = mEText1.getText().toString();
//        String str2 = mEText2.getText().toString();
//        if(!str1.equals("") && str2.equals("")){
//            answerInput = Integer.valueOf(str1);
//        }else if(!str1.equals("") && !str2.equals("")){
//            answerInput = Integer.valueOf(str1) * 10 + Integer.valueOf(str2);
//        }
        if(rightAnswer == answerInput){
            score++;
            Toast.makeText(QuestionActivity.this,
                    correct, Toast.LENGTH_SHORT).show();
            checkProcess();
        }else if(rightAnswer/10 < 1){

            Toast.makeText(QuestionActivity.this,
                    wrong, Toast.LENGTH_SHORT).show();
            checkProcess();
        }else if(answerInput/10 > 0){
            Toast.makeText(QuestionActivity.this,
                    wrong, Toast.LENGTH_SHORT).show();
            checkProcess();
        }

    }

    public void checkProcess(){
        if(questionNumber >= 10){
            Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
            Bundle b = new Bundle();
            b.putInt("score", score);
            intent.putExtras(b);
            startActivity(intent);
            finish();
        }else{
            setQuestionView();
        }

    }
    public void generateNumbers(){
        questionNumber++;
        displayCDT();
        Random rand = new Random();
        int firstNumber = rand.nextInt(8) + 2;
        int secondNumber = rand.nextInt(9) + 1;
        TextView tvOperator = (TextView) findViewById(R.id.operator);
        String str3 = tvOperator.getText().toString();

        if(str3.equals("-")) {
            while (firstNumber < secondNumber + 1) {
                secondNumber = rand.nextInt(9) + 1;
            }
        }

        TextView tvFirstNumber = (TextView)findViewById(R.id.firstNumber);
        String strFirstNumber = String.valueOf(firstNumber);
        tvFirstNumber.setText(strFirstNumber);

        TextView tvSecondNumber = (TextView)findViewById(R.id.secondNumber);
        String strSecondNumber = String.valueOf(secondNumber);
        tvSecondNumber.setText(strSecondNumber);
        //displayCDT();

    }

    public void display(View v) {
//        mEText1 = (EditText) findViewById(R.id.editText1);
//        mEText2 = (EditText) findViewById(R.id.editText2);
//        String str1 = mEText1.getText().toString();
        mEText = (EditText)findViewById(R.id.editText);
        String str1 = mEText.getText().toString();
        if(str1.equals("")){
            switch(v.getId()) {
                case R.id.button1:
                    mEText.setText("1");
                    checkAnswer();
                    break;
                case R.id.button2:
                    mEText.setText("2");
                    checkAnswer();
                    break;
                case R.id.button3:
                    mEText.setText("3");
                    checkAnswer();
                    break;
                case R.id.button4:
                    mEText.setText("4");
                    checkAnswer();
                    break;
                case R.id.button5:
                    mEText.setText("5");
                    checkAnswer();
                    break;
                case R.id.button6:
                    mEText.setText("6");
                    checkAnswer();
                    break;
                case R.id.button7:
                    mEText.setText("7");
                    checkAnswer();
                    break;
                case R.id.button8:
                    mEText.setText("8");
                    checkAnswer();
                    break;
                case R.id.button9:
                    mEText.setText("9");
                    checkAnswer();
                    break;
                case R.id.button0:
                    mEText.setText("0");
                    checkAnswer();
                    break;
            }
        }else{
            int carry = Integer.valueOf(str1);
            String displayS = "";
            switch(v.getId()) {
                case R.id.button1:
                    //mEText.setText("1");
                    displayS = String.valueOf(carry * 10 + 1);
                    mEText.setText(displayS);
                    break;
                case R.id.button2:
                    //mEText.setText("2");
                    displayS = String.valueOf(carry * 10 + 2);
                    mEText.setText(displayS);
                    break;
                case R.id.button3:
                    //mEText.setText("3");
                    displayS = String.valueOf(carry * 10 + 3);
                    mEText.setText(displayS);
                    break;
                case R.id.button4:
                    //mEText.setText("4");
                    displayS = String.valueOf(carry * 10 + 4);
                    mEText.setText(displayS);
                    break;
                case R.id.button5:
                    //mEText.setText("5");
                    displayS = String.valueOf(carry * 10 + 5);
                    mEText.setText(displayS);
                    break;
                case R.id.button6:
                    //mEText.setText("6");
                    displayS = String.valueOf(carry * 10 + 6);
                    mEText.setText(displayS);
                    break;
                case R.id.button7:
                    //mEText.setText("7");
                    displayS = String.valueOf(carry * 10 + 7);
                    mEText.setText(displayS);
                    break;
                case R.id.button8:
                    //mEText.setText("8");
                    displayS = String.valueOf(carry * 10 + 8);
                    mEText.setText(displayS);
                    break;
                case R.id.button9:
                    //mEText.setText("9");
                    displayS = String.valueOf(carry * 10 + 9);
                    mEText.setText(displayS);
                    break;
                case R.id.button0:
                    //mEText.setText("0");
                    displayS = String.valueOf(carry * 10 + 0);
                    mEText.setText(displayS);
                    break;
            }
        }

    }

}

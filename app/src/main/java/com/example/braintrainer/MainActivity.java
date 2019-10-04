package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button b1, b4, b5, b6, b7, b8;
    TextView t1, t2, t3, t4;
    int locCorrectAns, score=0, numberOfQues = 0;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    public void start(View view){
        b1.setVisibility(View.INVISIBLE);
        b4.setVisibility(View.VISIBLE);
        b5.setVisibility(View.VISIBLE);
        b6.setVisibility(View.VISIBLE);
        b7.setVisibility(View.VISIBLE);
        t1.setVisibility(View.VISIBLE);
        t2.setVisibility(View.VISIBLE);
        t3.setVisibility(View.VISIBLE);
        t4.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.button8));
    }
    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locCorrectAns))){
            t4.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            t4.setText("Correct");
            score++;
        }
        else{
            t4.setTextColor(getResources().getColor(R.color.Red));
            t4.setText("Wrong Answer! Try Again");
        }
        numberOfQues++;
        t2.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQues));
        generateQues();
    }
    public void generateQues(){
        b4.setVisibility(View.VISIBLE);
        b5.setVisibility(View.VISIBLE);
        b6.setVisibility(View.VISIBLE);
        b7.setVisibility(View.VISIBLE);
        t3.setVisibility(View.VISIBLE);
        Random rand = new Random();
        int a = rand.nextInt(51);
        int b = rand.nextInt(51);
        answers.clear();
        t3.setText(Integer.toString(a)+" + "+Integer.toString(b));
        locCorrectAns = rand.nextInt(4);
        int incorrectAns;
        for(int i =0; i<4; i++){
            if(i == locCorrectAns){
                answers.add(a + b);
            }
            else{
                incorrectAns = rand.nextInt(101);
                while(incorrectAns == a+b){
                    incorrectAns = rand.nextInt(101);
                }
                answers.add(incorrectAns);
            }
        }
        b4.setText(Integer.toString(answers.get(0)));
        b5.setText(Integer.toString(answers.get(1)));
        b6.setText(Integer.toString(answers.get(2)));
        b7.setText(Integer.toString(answers.get(3)));

    }
    public void playAgain(View view){
        generateQues();
        b8.setVisibility(View.INVISIBLE);
        score =0;
        numberOfQues = 0;
        t1.setText("30s");
        t2.setText("0/0");
        t4.setText("");
        new CountDownTimer(30100, 1000){
            public void onTick(long millisUntilFinished) {
                t1.setText(String.valueOf(millisUntilFinished / 1000)+"s");

            }
            public void onFinish() {
                b8.setVisibility(View.VISIBLE);
                b4.setVisibility(View.INVISIBLE);
                b5.setVisibility(View.INVISIBLE);
                b6.setVisibility(View.INVISIBLE);
                b7.setVisibility(View.INVISIBLE);
                t3.setVisibility(View.INVISIBLE);
                t1.setText("0s");
                t4.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                t4.setText("Your Score: "+Integer.toString(score)+"/"+Integer.toString(numberOfQues));
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.button);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        t1 = findViewById(R.id.textView);
        t2 = findViewById(R.id.textView2);
        t3 = findViewById(R.id.textView3);
        t4 = findViewById(R.id.textView4);
    }
}

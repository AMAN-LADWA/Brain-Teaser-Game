package com.viber.brainteaser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton ;
    int locationOfCorrectAnswer;
    TextView resultTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int score = 0;
    int numberOfQuestions = 0;
    TextView scoreTextView ;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout theGame;

    public void chooseAnswer(View view){
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            Log.i("correct","vorrect");
            resultTextView.setText("CORRECT!! :D");
            score++;
        }else{
            Log.i("wrong","take your shilling fuck off");
            resultTextView.setText("Wrong! :(");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        answers.clear();
        newQuestion();
    }

    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        theGame.setVisibility((View.VISIBLE));
        playAgain(resultTextView);

    }
    public void newQuestion(){
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        sumTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));
        locationOfCorrectAnswer = random.nextInt(4);
        for (int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);
            }else{
                int wrongAns = random.nextInt(41);
                while(wrongAns==a+b){
                    wrongAns = random.nextInt(41);
                }
                answers.add(wrongAns);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }
    public void playAgain(View view){
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
        score=0;
        numberOfQuestions=0;
        scoreTextView.setText("0/0");
        resultTextView.setText("");

        new CountDownTimer(5100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setVisibility(View.INVISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
        newQuestion();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTextView = findViewById(R.id.correctTextView);
        timerTextView = findViewById(R.id.timerTextView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        goButton = (Button) findViewById(R.id.goButton);
        theGame = (ConstraintLayout) findViewById(R.id.theGame);
        playAgainButton = (Button) findViewById(R.id.playAgainButt);
        goButton.setVisibility(View.VISIBLE);
        theGame.setVisibility(View.INVISIBLE);
    }
}
package com.example.mausam.braintrainer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    // own change
    String operatorSign;
    MediaPlayer mediaPlayer1;
    MediaPlayer mediaPlayer2;

    Button button0;

    Button button1;

    Button button2;

    Button button3;

    Button playAgain;

    TextView sumTextView;

    TextView resultTextView;

    TextView pointTextView;

    TextView timerTextView;

    RelativeLayout gameRelativeLayout;

    boolean stopEntireGame = false;

    int score = 0;

    int numberOfQuestions = 0;

    Button startBtn;

    ArrayList<Integer> answers = new ArrayList<>();

    int locationOfCurrectAnswer;

    public void start(View view) {


        startBtn.setVisibility(view.INVISIBLE);

        gameRelativeLayout.setVisibility(View.VISIBLE);

        playAgain1(findViewById(R.id.playAgainButton));

        Log.i("inside", "play game");
    }

    public void playAgain(View view) {

        Intent intent = new Intent(MainActivity.this, OperatorActivity.class);

        startActivity(intent);

    }

    public void playAgain1(View view) {


        stopEntireGame = false;

        score = 0;

        numberOfQuestions = 0;

        timerTextView.setText("30s");

        pointTextView.setText("0/0");

        resultTextView.setText("");

        playAgain.setVisibility(View.INVISIBLE);


        generateQuestion();

        new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long l) {

                timerTextView.setText((Integer.toString((int) l / 1000)) + "s");

            }

            @Override
            public void onFinish() {



                mediaPlayer1.start();

                playAgain.setVisibility(View.VISIBLE);

                timerTextView.setText("0s");

                resultTextView.setText("Your score \n \t\t\t\t\t\t\t\t" + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

                stopEntireGame = true;


            }
        }.start();


    }

    public void generateQuestion() {

        String text = null;

        String op = null;

        int opInt = 0;

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        if (operatorSign.equals("plus")) {

            text = Integer.toString(a) + " + " + Integer.toString(b);

            op = Integer.toString(a + b);

            opInt = a + b;

        } else if (operatorSign.equals("minus")) {

            text = Integer.toString(a) + " - " + Integer.toString(b);

            op = Integer.toString(a - b);

            opInt = a - b;

        } else if (operatorSign.equals("mul")) {

            text = Integer.toString(a) + " * " + Integer.toString(b);

            op = Integer.toString(a * b);

            opInt = a * b;

        } else if (operatorSign.equals("div")) {

            text = Integer.toString(a) + " / " + Integer.toString(b);

            op = Integer.toString(a / b);

            opInt = a / b;
        }
        int incorrectAnswer;

        sumTextView.setText(text);

        locationOfCurrectAnswer = rand.nextInt(4);

        answers.clear();

        for (int i = 0; i < 4; i++) {

            if (i == locationOfCurrectAnswer) {

                answers.add(opInt);


            } else {

                incorrectAnswer = rand.nextInt(60);


                while (incorrectAnswer == opInt) {

                    incorrectAnswer = rand.nextInt(60);

                }
                answers.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }


    public void chooseAnswer(View view) {

        if (stopEntireGame != true) {

            if (view.getTag().toString().equals(Integer.toString(locationOfCurrectAnswer))) {

                score++;

                resultTextView.setText("Correct!");


            } else {


                mediaPlayer2.start();

                resultTextView.setText("Wrong!");


            }
            numberOfQuestions++;

            pointTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

            generateQuestion();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = (Button) findViewById(R.id.startbtn);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        gameRelativeLayout = (RelativeLayout) findViewById(R.id.secondRelativeLayout);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        playAgain = (Button) findViewById(R.id.playAgainButton);


        // String s = getIntent().getStringExtra("operatorSign");
        operatorSign = getIntent().getStringExtra("operatorSign");
         mediaPlayer2 =MediaPlayer.create(MainActivity.this,R.raw.wrong);
         mediaPlayer1 =MediaPlayer.create(MainActivity.this,R.raw.airhorn);


    }
}

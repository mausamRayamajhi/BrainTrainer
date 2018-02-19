package com.example.mausam.braintrainer;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class OperatorActivity extends AppCompatActivity {

    // my modification

    String operator;

    String operatorSign;

    Intent intent;


    public void chooseOperator(View view){

        operator=view.getTag().toString();

        if(operator.equals("4"))
        {
            operatorSign="plus";


            Log.i("inside","plus");

        }
        else if(operator.equals("5")){

            operatorSign="minus";

            Log.i("inside","minus");

        }
        else if(operator.equals("6")){

            operatorSign="mul";

            Log.i("inside","multiplication");

        }
        else{

            operatorSign="div";

            Log.i("inside","dividion");

        }

        intent.putExtra("operatorSign", operatorSign);

        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.operator_activity);

         intent = new Intent(getBaseContext(), MainActivity.class);



    }
}

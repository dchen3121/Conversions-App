package com.example.dchen.conversions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button mass;
    private Button length;
    private Button time;
    private Button temp;
    private Button size;

    private String option;

    public void openCalcActivity() {
        Intent intentCalc = new Intent(this, CalcActivity.class);
        intentCalc.putExtra("option_val", option);
        startActivity(intentCalc);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mass = (Button) findViewById(R.id.buttonMass);
        length = (Button) findViewById(R.id.buttonLength);
        time = (Button) findViewById(R.id.buttonTime);
        temp = (Button) findViewById(R.id.buttonTemp);
        size = (Button) findViewById(R.id.buttonSize);




        mass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option = "mass";
                openCalcActivity();
            }
        });

        length.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option = "length";
                openCalcActivity();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option = "time";
                openCalcActivity();
            }
        });

        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option = "temp";
                openCalcActivity();
            }
        });

        size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option = "size";
                openCalcActivity();
            }
        });
    }


}

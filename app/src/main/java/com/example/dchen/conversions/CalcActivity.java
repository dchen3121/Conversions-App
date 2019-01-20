package com.example.dchen.conversions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;



public class CalcActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText leftEdit;
    private TextView rightText;
    private Spinner leftSpinner, rightSpinner;
    private Button buttonConv;
    private ImageButton buttonBack;


    public void openMainActivity() {
        Intent intentMain = new Intent(this, MainActivity.class);
        startActivity(intentMain);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        Bundle extras = getIntent().getExtras();
        final String option = extras.getString("option_val");

        // leftVal and rightVal are user input and displayed result
        final double[] leftVal = new double[1];
        final double[] rightVal = new double[1];
        // leftScale and rightScale are based on spinner selections
        final int[] leftScale = new int[1];
        final int[] rightScale = new int[1];

        leftSpinner = (Spinner) findViewById(R.id.spinnerLeft);
        rightSpinner = (Spinner) findViewById(R.id.spinnerRight);

        // Setting up the spinners
        switch (option) {
            case "mass":
                ArrayAdapter<CharSequence> adapterMassL =
                        ArrayAdapter.createFromResource
                                (this, R.array.massUnits, android.R.layout.simple_spinner_item);
                adapterMassL.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                leftSpinner.setAdapter(adapterMassL);
                ArrayAdapter<CharSequence> adapterMassR =
                        ArrayAdapter.createFromResource
                                (this, R.array.massUnits, android.R.layout.simple_spinner_item);
                adapterMassR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                rightSpinner.setAdapter(adapterMassR);
                break;
            case "length":
                ArrayAdapter<CharSequence> adapterLengthL =
                        ArrayAdapter.createFromResource
                                (this, R.array.lengthUnits, android.R.layout.simple_spinner_item);
                adapterLengthL.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                leftSpinner.setAdapter(adapterLengthL);
                ArrayAdapter<CharSequence> adapterLengthR =
                        ArrayAdapter.createFromResource
                                (this, R.array.lengthUnits, android.R.layout.simple_spinner_item);
                adapterLengthR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                rightSpinner.setAdapter(adapterLengthR);
                break;
            case "time":
                ArrayAdapter<CharSequence> adapterTimeL =
                        ArrayAdapter.createFromResource
                                (this, R.array.timeUnits, android.R.layout.simple_spinner_item);
                adapterTimeL.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                leftSpinner.setAdapter(adapterTimeL);
                ArrayAdapter<CharSequence> adapterTimeR =
                    ArrayAdapter.createFromResource
                            (this, R.array.timeUnits, android.R.layout.simple_spinner_item);
                adapterTimeR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                rightSpinner.setAdapter(adapterTimeR);
                break;
            case "temp":
                ArrayAdapter<CharSequence> adapterTempL =
                        ArrayAdapter.createFromResource
                                (this, R.array.tempUnits, android.R.layout.simple_spinner_item);
                adapterTempL.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                leftSpinner.setAdapter(adapterTempL);
                ArrayAdapter<CharSequence> adapterTempR =
                        ArrayAdapter.createFromResource
                                (this, R.array.tempUnits, android.R.layout.simple_spinner_item);
                adapterTempR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                rightSpinner.setAdapter(adapterTempR);
                break;
            case "size":
                ArrayAdapter<CharSequence> adapterSizeL =
                        ArrayAdapter.createFromResource
                                (this, R.array.sizeUnits, android.R.layout.simple_spinner_item);
                adapterSizeL.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                leftSpinner.setAdapter(adapterSizeL);
                ArrayAdapter<CharSequence> adapterSizeR =
                        ArrayAdapter.createFromResource
                                (this, R.array.sizeUnits, android.R.layout.simple_spinner_item);
                adapterSizeR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                rightSpinner.setAdapter(adapterSizeR);
                break;
            default: break;
        }
/*
        leftSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                leftScale[0] = textToScale(text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        rightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                String t =
                rightScale[0] = textToScale(text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        leftEdit = (EditText) findViewById(R.id.editTextCurrent);
        rightText = (TextView) findViewById(R.id.textViewDesiredVal);

        buttonConv = (Button) findViewById(R.id.buttonConv);
        buttonConv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (option.equals("mass") || option.equals("length") || option.equals("time")) {
                    leftScale[0] = textToScale(leftSpinner.getSelectedItem().toString());
                    rightScale[0] = textToScale(rightSpinner.getSelectedItem().toString());
                    leftEdit = (EditText) findViewById(R.id.editTextCurrent);
                    leftVal[0] = Double.parseDouble(leftEdit.getText().toString());
                    rightVal[0] = leftVal[0] / rightScale[0] * leftScale[0];
                    String retVal = String.format("%.2f", (rightVal[0]));
                    rightText.setText(retVal);
                } else if (option.equals("temp")) {
                    String left = leftSpinner.getSelectedItem().toString();
                    String right = rightSpinner.getSelectedItem().toString();
                    leftEdit = (EditText) findViewById(R.id.editTextCurrent);
                    leftVal[0] = Double.parseDouble(leftEdit.getText().toString());
                    if (left.equals(right)) {
                        rightVal[0] = leftVal[0];
                    } else {
                        switch (left) {
                            case "°C (degrees Celsius)":
                                if (right.equals("°F (degrees Fahrenheit)")) {
                                    rightVal[0] = leftVal[0] * 9 / 5 + 32;
                                } else if (right.equals("K (Kelvins)")) {
                                    rightVal[0] = leftVal[0] + 273.15;
                                }
                                break;
                            case "°F (degrees Fahrenheit)":
                                if (right.equals("°C (degrees Celsius)")) {
                                    rightVal[0] = (leftVal[0] - 32) * 5 / 9;
                                } else if (right.equals("K (Kelvins)")) {
                                    rightVal[0] = (leftVal[0] - 32) * 5 / 9 + 273.15;
                                }
                                break;
                            case "K (Kelvins)":
                                if (right.equals("°F (degrees Fahrenheit)")) {
                                    rightVal[0] = (leftVal[0] - 273.15) * 9 / 5 + 32;
                                } else if (right.equals("°C (degrees Celsius)")) {
                                    rightVal[0] = leftVal[0] - 273.15;
                                }
                                break;
                        }
                    }
                    String retVal = String.format("%.2f", (rightVal[0]));
                    rightText.setText(retVal);
                } else if (option.equals("size")) {
                    leftScale[0] = textToScale(leftSpinner.getSelectedItem().toString());
                    rightScale[0] = textToScale(rightSpinner.getSelectedItem().toString());
                    leftEdit = (EditText) findViewById(R.id.editTextCurrent);
                    leftVal[0] = Double.parseDouble(leftEdit.getText().toString());
                    rightVal[0] = leftVal[0] - leftScale[0] + rightScale[0];
                    String retVal = String.format("%.0f", (rightVal[0]));
                    rightText.setText(retVal);
                }
            }
        });
        buttonBack = findViewById(R.id.imageButtonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
    }



    public int textToScale(String textVal) {
        int val = 0;
        switch(textVal) {
            case "t (tonnes)":
                val = 1000000;
                break;
            case "kg (kilograms)":
                val = 1000;
                break;
            case "g (grams)":
                val = 1;
                break;
            case "lb (pounds)":
                val = 454;
                break;
            case "oz (ounces)":
                val = 16;
                break;
            case "km (kilometres)":
                val = 1000000;
                break;
            case "m (metres)":
                val = 1000;
                break;
            case "cm (centimetres)":
                val = 10;
                break;
            case "mm (millimetres)":
                val = 1;
                break;
            case "mi (miles)":
                val = 1609340;
                break;
            case "yd (yards)":
                val = 915;
                break;
            case "ft (feet)":
                val = 305;
                break;
            case "seconds":
                val = 1;
                break;
            case "minutes":
                val = 60;
                break;
            case "hours":
                val = 3600;
                break;
            case "days":
                val = 3600 * 24;
                break;
            case "weeks":
                val = 3600 * 24 * 7;
                break;
            case "US & Canada":
                val = 2;
                break;
            case "UK":
                val = 4;
                break;
            case "Europe":
                val = 32;
                break;
            case "Japan":
                val = 5;
                break;
            case "Australia":
                val = 6;
                break;
            default:
                break;
        }
        return val;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

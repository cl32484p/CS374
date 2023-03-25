package com.example.conversioncjlugov2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
EditText userDollars;
Button convertButton;
Boolean check = false;
String input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Convert dollars to euros (1 dollar = .88 euro);


         userDollars = findViewById(R.id.userDollars);
         convertButton = findViewById(R.id.convertBtn);

        //Convert and send to result activity;
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkUserDollars())
                {
                    String text = userDollars.getText().toString();
                    double dollars = Double.parseDouble(text);
                    dollars *= .88; //conversion rate
                    Intent intent = new Intent(MainActivity.this, Result.class);
                    intent.putExtra("euros", dollars);
                    startActivity(intent);
                }


            }
        });


    }

//Check user input
    private boolean checkUserDollars()
    {
        input = userDollars.getText().toString();
        //create pattern for valid input
        Pattern money = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (money.matcher(input).matches()) {
            double inputDouble = Double.parseDouble(input);
           if(inputDouble < 0) //check for negative dollar amount
           {
               Toast.makeText(this, "Please enter a valid dollar amount", Toast.LENGTH_SHORT).show();
           }
           else
            check = true;
        }
        else {
            check = false;
            Toast.makeText(this, "Please enter a valid dollar amount", Toast.LENGTH_SHORT).show();
        }








       /* String text = userDollars.getText().toString();
        double dollars = Double.parseDouble(text);
        if (dollars == 0) {
            Toast.makeText(MainActivity.this, "Please enter valid dollar amount", Toast.LENGTH_SHORT).show();
            check = false;
        }
        else
            check = true;

        return check; */

        return check;
    }



}
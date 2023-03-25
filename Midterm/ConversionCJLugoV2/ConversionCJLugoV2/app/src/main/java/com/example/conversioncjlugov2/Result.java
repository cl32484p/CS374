package com.example.conversioncjlugov2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();

        //Display the calculation as currency
        Double euros = intent.getDoubleExtra("euros", 0.0);
        TextView display = findViewById(R.id.userEuros);
        DecimalFormat currency = new DecimalFormat("#.##");
        String userEuros = currency.format(euros);
        String out = userEuros;
        display.setText(out);

        //Display part 2
        TextView display2 = findViewById(R.id.textView2);
        display2.setText("You have " + euros + " euros");

        //return back to first(main) activity
        Button returnBtn = findViewById(R.id.returnBtn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Result.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
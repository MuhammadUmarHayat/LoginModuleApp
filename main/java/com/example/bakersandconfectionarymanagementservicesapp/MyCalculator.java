package com.example.bakersandconfectionarymanagementservicesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MyCalculator extends AppCompatActivity
{
EditText et11,et21;
Button btn11;
TextView tv1;//object

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);//calling a parent method
        setContentView(R.layout.activity_my_calculator);
    tv1=findViewById(R.id.tvResult) ;
    et11=findViewById(R.id.et1) ;
        et21=findViewById(R.id.et2) ;
        btn11=findViewById(R.id.btn1);
        btn11.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
String s1=et11.getText().toString();
                String s2=et21.getText().toString();
                int v1=Integer.parseInt(s1);
                int v2=Integer.parseInt(s2);
                int result=v1+v2;
                String r=String.valueOf(result);
                tv1.setText(r);


            }
        });



    }
}
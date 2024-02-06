package com.example.bakersandconfectionarymanagementservicesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=findViewById(R.id.btnlogin);
        btn2=findViewById(R.id.btnSignup);
        btn1.setOnClickListener(new View.OnClickListener()
        {
            //naviagete to login activity
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);//go to new activity
            }
        });
        btn2.setOnClickListener(new View.OnClickListener()
        {
            //naviagete to login activity
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(MainActivity.this, Signup.class);
                startActivity(intent);//go to new activity
            }
        });
    }//on create

}
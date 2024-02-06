package com.example.bakersandconfectionarymanagementservicesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Login extends AppCompatActivity
{
private EditText etEmail,etPw;
    private ImageView captchaImageView;
    private EditText captchaInputEditText;
    private Button btnLogin;
private TextView tv1;
    private String generatedCaptcha;

Users user;
User_Table userTable;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin=findViewById(R.id.BtnLogin1);
        etEmail=findViewById(R.id.etEmail);
        etPw=findViewById(R.id.etPw);
        tv1=findViewById(R.id.tv1);
        captchaInputEditText=findViewById(R.id.captchaInputEditText);
        captchaImageView=findViewById(R.id.captchaImageView);
// Generate and display captcha image
        generatedCaptcha = generateCaptcha();
        Bitmap captchaBitmap = generateCaptchaBitmap(generatedCaptcha);
        captchaImageView.setImageBitmap(captchaBitmap);

        user=new Users();
        userTable=new User_Table(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

              //  login();
                String userInput = captchaInputEditText.getText().toString().trim();
                if (userInput.equals(generatedCaptcha))
                {
                    // Captcha validation successful
                   // Toast.makeText(Login.this, "Captcha Validation Successful!", Toast.LENGTH_SHORT).show();
String s="Captcha Validation Successful!";

                   try{
                       tv1.setText(s);
                       login();
                   }
                   catch ( Exception exp)
                   {
                       tv1.setError(exp.getMessage().toString());
                   }
                }
                else
                {
                    // Captcha validation failed
                    Toast.makeText(Login.this, "Captcha Validation Failed! Try Again.", Toast.LENGTH_SHORT).show();
                    // Generate and display new captcha
                    generatedCaptcha = generateCaptcha();
                    Bitmap newCaptchaBitmap = generateCaptchaBitmap(generatedCaptcha);
                    captchaImageView.setImageBitmap(newCaptchaBitmap);
                    String s="Captcha Validation Failed! Try Again.";
                    tv1.setText(s);
                }

            }
        });

    }//end on create
    // Method to generate a random captcha string
    private String generateCaptcha()
    {
        // Generate a random captcha string
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder captcha = new StringBuilder();
        int length = 6; // Captcha length

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allowedChars.length());
            captcha.append(allowedChars.charAt(index));
        }

        return captcha.toString();
    }

    // Method to convert captcha string to bitmap
    private Bitmap generateCaptchaBitmap(String captchaText)
    {
        // Convert captcha string to bitmap
        int width = 300; // Captcha image width
        int height = 100; // Captcha image height
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(40);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.LEFT);

        canvas.drawText(captchaText, 10, 60, paint);

        return bitmap;
    }

    ////////lgoin///////////////////
    private void login()
    {
        // Get the email entered by the user
        String email = etEmail.getText().toString().trim();
        String password=etPw.getText().toString().trim();

        // Check if email is empty
        if (email.isEmpty()||password.isEmpty() )
        {
            tv1.setError("Email/Password cannot be empty");
        }
        else if (isValidEmail(email))
        {
            // Email is valid

if(userTable.checkUserExist(email,password))
{

    tv1.setText("User has been login successfully");
    Intent intent = new Intent(Login.this, MainActivity.class);
    startActivity(intent);//go to new activity
}
        }
        else {
            // Check if email is in valid format using regular expression
            tv1.setError("Invalid email address");
        }
    }

    private boolean isValidEmail(CharSequence target)
    {
        // Use Android's built-in Patterns class to check if email is in valid format
        return Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

}
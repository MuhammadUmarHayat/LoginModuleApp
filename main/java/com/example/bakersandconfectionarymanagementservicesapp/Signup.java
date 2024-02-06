package com.example.bakersandconfectionarymanagementservicesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
private EditText etFname,etLastName,etEmail,etPw,etRPw;
private Button btnSignUp;
TextView tv1;
    User_Table userTable;
    Users user;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btnSignUp=findViewById(R.id.BtnSignUp);
        etFname=findViewById(R.id.etFname);
        etLastName=findViewById(R.id.etLastName);
        etEmail=findViewById(R.id.etEmailSignup);
        etPw=findViewById(R.id.etPwSignup);
        etRPw=findViewById(R.id.etRPw);
        tv1=findViewById(R.id.tv1Signup);
         userTable=new User_Table(this);
        user=new Users();
        btnSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                signup();

            }
        });
    }//on create
    private boolean isValidPassword()
    {
        String pw=etPw.getText().toString();
        String rpw=etRPw.getText().toString();
        if(pw.equals(rpw))
        {
            return true;
        }
return false;
    }
    private boolean isEpmty()
    {
        String fname=etFname.getText().toString();
        String lastName=etLastName.getText().toString();
        String email=etEmail.getText().toString();
        String pw=etPw.getText().toString();
        String rpw=etRPw.getText().toString();
        if(fname.isEmpty()||lastName.isEmpty()||email.isEmpty()||pw.isEmpty())
        {
            return true;
        }
        return false;
    }
    private boolean isValidEmail(CharSequence target)
    {
        // Use Android's built-in Patterns class to check if email is in valid format
        return Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    private void signup()
    {
        String fname=etFname.getText().toString();
        String lastName=etLastName.getText().toString();
        String email=etEmail.getText().toString();
        String pw=etPw.getText().toString();
        String rpw=etRPw.getText().toString();
if(isEpmty())
{
   tv1.setText("Fields should not empty");
}
else {

   if (!isValidEmail(email))
   {
      // Check if email is in valid format using regular expression
      tv1.setError("Invalid email address");
  } else if (isValidEmail(email)&&isValidPassword()){
      // Email is valid
if(userTable.checkEmailExist(email))
{
    tv1.setError("Email is already exist");
}
else
{

    user.email=email;
    user.firstName=fname;
    user.lastName=lastName;
    user.password=pw;
    userTable.addUser(user);
    tv1.setText("User is Registered Successfully");
}

  }
}
    }

}
package com.example.flight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signin extends AppCompatActivity {


    TextView Email_id;
    TextView Password;
    TextView SignUp;
    Button Login_button;
    private FirebaseAuth mAuth;


    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mAuth=FirebaseAuth.getInstance();

        Email_id = (TextView) findViewById(R.id.Signin_Email);
        Password = (TextView) findViewById(R.id.Signin_Password);
        SignUp = (TextView) findViewById(R.id.Signin_Signup);
        Login_button = (Button) findViewById(R.id.Signin_LoginButton);
        underline(SignUp);

        // new user//
        SignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), signup.class);
                startActivity(i);
            }
        });

        Login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginuser();
            }
        });
    }

    private void loginuser() {
        String email_string = Email_id.getText().toString().trim();
        String pass_string = Password.getText().toString().trim();

        if (TextUtils.isEmpty(email_string)) {
            Email_id.setError("email cannot be empty");
            Email_id.requestFocus();
        }
        if (TextUtils.isEmpty(pass_string)) {
            Password.setError("password cannot be empty");
            Password.requestFocus();
        }
        if (!email_string.isEmpty() && !pass_string.isEmpty()) {
            mAuth.signInWithEmailAndPassword(email_string,pass_string).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(signin.this, "sign in", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(signin.this,trial.class));
                    }else{
                        Toast.makeText(signin.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    private void underline(TextView signUp) {
        SpannableString signup_underline = new SpannableString(signUp.getText().toString());
        signup_underline.setSpan(new UnderlineSpan(), 0, signup_underline.length(), 0);
        signUp.setText(signup_underline);
    }

}
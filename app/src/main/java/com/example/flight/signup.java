package com.example.flight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class signup extends AppCompatActivity {

    TextView user_Email;
    TextView user_name;
    TextView user_mobile;
    EditText user_dob;
    Button sign_up_btn;
    TextView user_password;
    TextView sign_in_btn;
    private FirebaseAuth mAuth;
    database_handler database;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        user_Email = (TextView) findViewById(R.id.Signup_Email);
        user_name = (TextView) findViewById(R.id.Signup_Username);
        user_mobile = (TextView) findViewById(R.id.Signup_MobileNo);
        user_dob = (EditText) findViewById(R.id.dob);
        sign_up_btn = (Button) findViewById(R.id.signup);
        user_password = (TextView) findViewById(R.id.Signup_Password);
        sign_in_btn = (TextView) findViewById(R.id.signin);


        underline(sign_in_btn);

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                createuser();
            }
        });

        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), signin.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) reload();
    }

    protected void createuser() {
        String emailString = user_Email.getText().toString();
        String usernameString = user_name.getText().toString();
        String userMobileString = user_mobile.getText().toString();
        String passwordString = user_password.getText().toString();

        if (readyForSignUp(emailString,usernameString,userMobileString,passwordString)) {
            Toast.makeText(this, "working " + " " + userMobileString.length(), Toast.LENGTH_SHORT).show();
            mAuth.createUserWithEmailAndPassword(emailString,passwordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(signup.this, "user created", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(signup.this,signin.class));
                    }else{
                        Toast.makeText(signup.this, "error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    static protected boolean password_security(String pass) {
        boolean upper = false;
        boolean lower = false;
        boolean number = false;
        boolean special = false;
        for (int i = 0; i < pass.length(); i++) {

            if ((int) pass.charAt(i) > 64 && (int) pass.charAt(i) < 91) upper = true;
            if ((int) pass.charAt(i) > 96 && (int) pass.charAt(i) < 123) lower = true;
            if ((int) pass.charAt(i) > 47 && (int) pass.charAt(i) < 58) number = true;
            if ((int) pass.charAt(i) >= 33 && (int) pass.charAt(i) <= 47 || (int) pass.charAt(i) >= 58 && (int) pass.charAt(i) <= 64 || (int) pass.charAt(i) >= 91 && (int) pass.charAt(i) <= 96 || (int) pass.charAt(i) >= 123 && (int) pass.charAt(i) <= 126)
                special = true;
        }
        if (upper && lower && number && special) return true;
        return false;
    }

    protected boolean readyForSignUp(String emailString, String usernameString, String userMobileString, String passwordString)
    {
        if (TextUtils.isEmpty(usernameString)) {
            user_name.setError("user name cannot be empty");
            user_name.requestFocus();
        }
        if (TextUtils.isEmpty(emailString)) {
            user_Email.setError("email cannot be empty");
            user_Email.requestFocus();
        }
        if (TextUtils.isEmpty(userMobileString) || userMobileString.length() != 10) {
            user_mobile.setError("mobile cannot be empty");
            user_mobile.requestFocus();
        }
        if (TextUtils.isEmpty(passwordString)) {
            user_password.setError("password cannot be empty");
            user_password.requestFocus();
        }
        if (!password_security(passwordString)) {
            user_password.setError("password is weak \n Include Upper case, Lower case, number, special character");
            user_password.requestFocus();
        }
        if (!usernameString.isEmpty() && !userMobileString.isEmpty() && !passwordString.isEmpty() && password_security(passwordString) )
            return true;
        return false;
    }

    private void underline(TextView sign_in_btn) {
        SpannableString signup_underline = new SpannableString(sign_in_btn.getText().toString());
        signup_underline.setSpan(new UnderlineSpan(), 0, signup_underline.length(), 0);
        sign_in_btn.setText(signup_underline);
    }
    private void updateUI(FirebaseUser user) {
    }
    private void reload() {
    }
}


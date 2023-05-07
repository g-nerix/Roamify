package com.example.roamify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    EditText email_forgot;
    Button resetPassword;
    ProgressBar progressBarForgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email_forgot= findViewById(R.id.email_forgot);
        resetPassword= findViewById(R.id.reset_password);
        progressBarForgot= findViewById(R.id.progressBar_forgot);

        firebaseAuth= FirebaseAuth.getInstance();

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reset();
            }
        });


    }
    void reset(){

        String inputted_email=email_forgot.getText().toString().trim();

        // if noting is entered just ask to enter again
        if( inputted_email.isEmpty()==true){

            email_forgot.setError(" Please enter an email!!");
            email_forgot.requestFocus();
            return;
        }

        if(Patterns.EMAIL_ADDRESS.matcher(inputted_email).matches()==false){
            email_forgot.setError(" Please enter a valid email!!");
            email_forgot.requestFocus();
            return;
        }

        progressBarForgot.setVisibility(View.VISIBLE);

        firebaseAuth.sendPasswordResetEmail(inputted_email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if( task.isSuccessful()==true){
                    Toast.makeText(ForgotPassword.this, "Please check your email to reset your password.", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(ForgotPassword.this, "Email resetting email couldn't be sent...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
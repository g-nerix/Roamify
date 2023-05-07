package com.example.roamify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private TextView register, forgotPassword;
    private EditText email,pass;
    private Button logIn;
    private ProgressBar pb;
    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            //Toast.makeText(Login.this, "User Already logged in", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), HomeScreen.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email_forgot);
        pass = findViewById(R.id.password);

        logIn = findViewById(R.id.reset_password);
        register  = (TextView) findViewById(R.id.register);
        pb = findViewById(R.id.progressBar);

        forgotPassword = findViewById(R.id.forgotPassword);


        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), ForgotPassword.class));
                //finish();

            }
        });


        register.setOnClickListener(this);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb.setVisibility(View.VISIBLE);
                String mail, ps;
                mail = String.valueOf(email.getText());
                ps = String.valueOf(pass.getText());

                if(TextUtils.isEmpty(mail)){
                    pb.setVisibility(View.GONE);
                    Toast.makeText(Login.this,"Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(ps)){
                    pb.setVisibility(View.GONE);
                    Toast.makeText(Login.this,"Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(mail, ps)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                pb.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), Second_Page.class));
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.register:
                startActivity(new Intent(this,signUp.class));
                finish();
                break;
        }
    }
}
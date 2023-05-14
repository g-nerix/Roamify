package com.example.roamify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
{
    String Location_string;
    EditText Location_edit_text;
    final String API_key = "5ae2e3f221c38a28845f05b6a91332a87694c022a5bfe17fff46a616";
    final String original_API_link = "https://api.opentripmap.com/0.1/en/places/";
    private String country;
    private double latitude, longitude;

    FirebaseAuth auth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth= FirebaseAuth.getInstance();
        user= auth.getCurrentUser();


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
//                startActivity(new Intent(getApplicationContext(), Second_Page.class));
//                finish();
                if( user==null){
                    startActivity(new Intent(getApplicationContext(), Login.class));
                    finish();
                }
                else{
                    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

                    if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
                        startActivity(new Intent(getApplicationContext(), Second_Page.class));
                        finish();
                    } else {
                        startActivity(new Intent(getApplicationContext(), noInternet.class));
                        finish();
                    }
                }
            }
        }, 2000);
    }
}
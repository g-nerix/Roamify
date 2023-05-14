package com.example.roamify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class noInternet extends AppCompatActivity {

    Button noIntbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);
        noIntbtn = findViewById(R.id.try_again_button);

        noIntbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

                if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
                    Toast.makeText(getApplicationContext(),"Connected to Internet", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Second_Page.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),"Internet Connection not found", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
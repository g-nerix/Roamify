package com.example.roamify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{
    String Location_string;
    EditText Location_edit_text;
    final String API_key = "5ae2e3f221c38a28845f05b6a91332a87694c022a5bfe17fff46a616";
    final String original_API_link = "https://api.opentripmap.com/0.1/en/places/";
    Button search_button;
    private String country;
    private double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent i = new Intent(MainActivity.this, HomeScreen.class);
                startActivity(i);
                finish();
            }
        }, 2000);
    }
}
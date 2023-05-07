package com.example.roamify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
{
    String Location_string;
    EditText Location_edit_text;
    final String API_key = "5ae2e3f221c38a28845f05b6a91332a87694c022a5bfe17fff46a616";
    final String original_API_link = "https://api.opentripmap.com/0.1/en/places/";
    private String country;
    private double latitude, longitude;


    /// Sarthak's stupidity starts
    FirebaseAuth auth;
    FirebaseUser user;

    /// Sarthak's stupidity ends

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Sarthak's stupidity
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
//                startActivity(new Intent(getApplicationContext(), Second_Page.class)); mine
//                Intent i = new Intent(MainActivity.this, HomeScreen.class);
//                startActivity(i);
//                finish();

                if( user==null){
//                    profile.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
                            startActivity(new Intent(getApplicationContext(), Login.class));
//                        }
//                    });
                }
                else{
//                    profile.setText(user.getEmail().substring(0,5));
//                    profile.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
                            startActivity(new Intent(getApplicationContext(), HomeScreen.class));
//                        }
//                    });
                }
            }
        }, 2000);
    }
}
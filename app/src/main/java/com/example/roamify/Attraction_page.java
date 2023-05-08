package com.example.roamify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Attraction_page extends AppCompatActivity
{
    private Context context;
    boolean isFav =false;

    TextView nameTxt, pricingTxt, rateTxt, contactTxt, locTxt;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_page);
        context = this;
        nameTxt=findViewById(R.id.nameTxt);
        pricingTxt=findViewById(R.id.pricingTxt);
        rateTxt=findViewById(R.id.ratingTxt);
        contactTxt=findViewById(R.id.phoneTxt);
        locTxt=findViewById(R.id.locTxt);
        image=findViewById(R.id.pic);
        ImageView map = findViewById(R.id.mapButton);

        Intent intent=getIntent();
        String name = intent.getStringExtra("name");
        double lon = intent.getDoubleExtra("longitude",0);
        double lat = intent.getDoubleExtra("latitude",0);
        System.out.println("In particular");
        System.out.println(lon);
        System.out.println(lat);
        String url = intent.getStringExtra("URL");
        int rating = intent.getIntExtra("rating",0);
        String contact = intent.getStringExtra("contact");

        rateTxt.setText(Integer.toString(rating));
        nameTxt.setText(name);
        contactTxt.setText(contact);
        new LoadImage(image).execute(url);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent explicit_intent = new Intent(getApplicationContext(), HomeScreen.class);
                explicit_intent.putExtra("longitude", lon);
                explicit_intent.putExtra("go_to_map", "go_to_map");
                explicit_intent.putExtra("latitude", lat);
                context.startActivity(explicit_intent);
            }
        });
    }
}
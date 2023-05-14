package com.example.roamify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Attraction_page extends AppCompatActivity
{
    private Context context;
    boolean isFav =false;

    TextView nameTxt, pricingTxt, rateTxt, contactTxt, locTxt;
    ImageView image;
    CheckBox box;
    private Button homebtn;


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
        box =findViewById(R.id.checkBox);
        Button map = findViewById(R.id.mapButton);
        homebtn = findViewById(R.id.hbtn);

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Second_Page.class);
                context.startActivity(i);
            }
        });

        Intent intent=getIntent();
        String name = intent.getStringExtra("name");
        double lon = intent.getDoubleExtra("longitude",0);
        double lat = intent.getDoubleExtra("latitude",0);
        System.out.println("In particular");
        System.out.println(lon);
        System.out.println(lat);
        String url = intent.getStringExtra("URL");
        int rating = intent.getIntExtra("rating",0);
        String loc = intent.getStringExtra("location");
        String contact = intent.getStringExtra("contact");
        String price = String.valueOf(intent.getIntExtra("price",0));
        String place = intent.getStringExtra("place");
        rateTxt.setText(String.valueOf(rating));
        nameTxt.setText(name);
        contactTxt.setText(contact);
        locTxt.setText(loc);
        pricingTxt.setText("₹"+price);
        ArrayList<String> checked_name_list = (ArrayList<String>) intent.getSerializableExtra("name_list");
        ArrayList<Integer> checked_price_list = (ArrayList<Integer>) intent.getSerializableExtra("price_list");

        new LoadImage(image).execute(url);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent explicit_intent = new Intent(getApplicationContext(), HomeScreen.class);
                explicit_intent.putExtra("longitude", lon);
                explicit_intent.putExtra("go_to_map", "go_to_map");
                explicit_intent.putExtra("latitude", lat);
                explicit_intent.putExtra("Place", place);
                explicit_intent.putExtra("name_list",checked_name_list);
                explicit_intent.putExtra("price_list",checked_price_list);
                context.startActivity(explicit_intent);
            }
        });

        box.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(box.isChecked())
                {
                    Intent intent2 =new Intent(getApplicationContext(), HomeScreen.class);
                    intent2.putExtra("name",name);
                    intent2.putExtra("price",Integer.valueOf(price));
                    intent2.putExtra("Place", place);
                    intent2.putExtra("name_list",checked_name_list);
                    intent2.putExtra("price_list",checked_price_list);
                    context.startActivity(intent2);
                }
            }
        });
    }
}
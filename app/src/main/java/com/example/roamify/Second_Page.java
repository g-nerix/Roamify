package com.example.roamify;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Second_Page extends AppCompatActivity
{
    String Location_string;
    SearchView Location_edit_text;
    ImageButton search_button;
    private String country;
    private double latitude, longitude;
    private ImageView view1,view2,view3,view4,view5,view6;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        SearchView searchView = findViewById(R.id.location);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                int flag = 0;
                for (int i = 0; i < query.length(); i++) {
                    if (Character.isLetter(query.charAt(i)) == false)
                    {
                        flag = -1;
                        break;
                    }
                }
                if(flag == 0)
                {
                    ConnectivityManager connectivity_manager = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo net_info = connectivity_manager.getActiveNetworkInfo();
                    boolean check_connection = net_info != null && net_info.isConnected();
                    if(check_connection == true)
                    {
                        String API_link = String.format("https://api.foursquare.com/v3/places/search?near=%s",query);
                        Main_search myTask=new Main_search(getApplicationContext(),API_link,query);
                        myTask.execute();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Connection error",Toast.LENGTH_SHORT).show();
                    }
                }
                if(flag == -1)
                {
                    Toast.makeText(getApplicationContext(),"Invalid input",Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        setbuttons();
    }

    private void setbuttons()
    {
        ImageView view1 = findViewById(R.id.img);
        ImageView view2 = findViewById(R.id.img_1);
        ImageView view3 = findViewById(R.id.img_2);
        ImageView view4 = findViewById(R.id.image4);
        ImageView view5 = findViewById(R.id.image5);
        ImageView view6 = findViewById(R.id.image6);
        ConnectivityManager connectivity_manager = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net_info = connectivity_manager.getActiveNetworkInfo();
        boolean check_connection = net_info != null && net_info.isConnected();
        if(check_connection != true)
        {
            Toast.makeText(getApplicationContext(),"Connection error",Toast.LENGTH_SHORT).show();
            return;

        }
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String API_link = String.format("https://api.foursquare.com/v3/places/search?near=%s","Delhi");
                Main_search myTask=new Main_search(getApplicationContext(),API_link,"Delhi");
                myTask.execute();
            }
        });
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String API_link = String.format("https://api.foursquare.com/v3/places/search?near=%s","Bangalore");
                Main_search myTask=new Main_search(getApplicationContext(),API_link,"Bangalore");
                myTask.execute();
            }
        });
        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String API_link = String.format("https://api.foursquare.com/v3/places/search?near=%s","Hyderabad");
                Main_search myTask=new Main_search(getApplicationContext(),API_link,"Hyderabad");
                myTask.execute();
            }
        });
        view4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String API_link = String.format("https://api.foursquare.com/v3/places/search?near=%s","Mumbai");
                Main_search myTask=new Main_search(getApplicationContext(),API_link,"Mumbai");
                myTask.execute();
            }
        });
        view5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String API_link = String.format("https://api.foursquare.com/v3/places/search?near=%s","Paris");
                Main_search myTask=new Main_search(getApplicationContext(),API_link,"Paris");
                myTask.execute();
            }
        });
        view6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String API_link = String.format("https://api.foursquare.com/v3/places/search?near=%s","New York");
                Main_search myTask=new Main_search(getApplicationContext(),API_link,"New York");
                myTask.execute();
            }
        });
    }
}
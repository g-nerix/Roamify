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
    EditText Location_edit_text;
    ImageButton search_button;
    private String country;
    private double latitude, longitude;
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
    }
//    public void set_button_listener()
//    {
//        search_button = (ImageButton)  findViewById(R.id.Search_button);
//        search_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)
//            {
//                Location_edit_text = (EditText) findViewById(R.id.location);
//                Location_string = Location_edit_text.getText().toString();
//                int flag = 0;
//                for (int i = 0; i < Location_string.length(); i++) {
//                    if (Character.isLetter(Location_string.charAt(i)) == false)
//                    {
//                        flag = -1;
//                        break;
//                    }
//                }
//                if(flag == 0)
//                {
//                    ConnectivityManager connectivity_manager = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//                    NetworkInfo net_info = connectivity_manager.getActiveNetworkInfo();
//                    boolean check_connection = net_info != null && net_info.isConnected();
//                    if(check_connection == true)
//                    {
//                        String API_link = String.format("https://api.foursquare.com/v3/places/search?near=%s",Location_string);
//                        Main_search myTask=new Main_search(getApplicationContext(),API_link,Location_string);
//                        myTask.execute();
//                    }
//                    else
//                    {
//                        Toast.makeText(getApplicationContext(),"Connection error",Toast.LENGTH_SHORT).show();
//                    }
//                }
//                if(flag == -1)
//                {
//                    Toast.makeText(getApplicationContext(),"Invalid input",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
}
package com.example.roamify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HomeScreen extends AppCompatActivity {

    BottomNavigationView btm_nav;

    ExploreFragment exploreFragment = new ExploreFragment();
    FavouriteFragment favouriteFragment = new FavouriteFragment();
    MapFragment mapFragment = new MapFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    SearchFragment searchFragment = new SearchFragment();
    private Intent value_received_from_previous_activity;
    private String place;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        value_received_from_previous_activity = getIntent();
        place = value_received_from_previous_activity.getStringExtra("place");
        btm_nav = findViewById(R.id.btm_nav);

        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,exploreFragment).commit();


        btm_nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item)
            {
                switch(item.getItemId()){
                    case R.id.explore:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,exploreFragment).commit();
                        return true;

                    case R.id.search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,searchFragment).commit();
                        return true;

                    case R.id.map:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,mapFragment).commit();
                        return true;

                    case R.id.favourite:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,favouriteFragment).commit();
                        return true;

                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,profileFragment).commit();
                        return true;

                }
                return false;
            }
        });
    }
}
class Search extends AsyncTask<Void, Void, String>
{
    private Context context;
    private String API;
    private String place;

    public Search(Context context,String API,String place)
    {
        this.context = context;
        this.API = API;
        this.place = place;
    }
    @Override
    protected String doInBackground(Void... voids)
    {
        String text_from_API = return_api_response(API);
        if(text_from_API == null)
        {
            return null;
        }
        return "Not none";
    }

    @Override
    protected void onPostExecute (String my_string)
    {
        if(my_string != null)
        {
            Intent explicit_intent = new Intent(context, HomeScreen.class);
            explicit_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            explicit_intent.putExtra("Place",place);
            context.startActivity(explicit_intent);
        }
        else
        {
            Toast.makeText(context,"Place not found",Toast.LENGTH_SHORT).show();
        }
    }
    public String return_api_response(String API_link)
    {
        URL url;
        HttpURLConnection urlConnection;
        String text_from_API;
        try
        {
            url = new URL(API_link);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("accept", "application/json");
            urlConnection.setRequestProperty("Authorization", "fsq3WrI8VuFGnlBez+qYhzN42bUEGOxQ/B1q+o1rt2MGFCU=");
            int response_code = urlConnection.getResponseCode();
            if(response_code != 200)
            {
                return null;
            }
            InputStream in_stream = new BufferedInputStream(urlConnection.getInputStream());
            Reader reader = null;
            reader = new InputStreamReader(in_stream, "UTF-8");
            char[] buffer = new char[1000000];
            reader.read(buffer);
            text_from_API = new String(buffer);
        }
        catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return text_from_API;
    }
}

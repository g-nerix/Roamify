package com.example.roamify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;

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
import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity implements ExploreFragment.Button_click
{

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

    @Override
    public void Button_click(String type)
    {
        String base_query = "https://api.foursquare.com/v3/places/search?fields=name%2Cgeocodes%2Clocation%2Cphotos%2Cprice%2Crating%2Cfeatures%2Ctel&limit=10&accept=application/json&Authorization=fsq3WrI8VuFGnlBez+qYhzN42bUEGOxQ/B1q+o1rt2MGFCU=";
        base_query += String.format("&near=%s",place);
        base_query+=String.format("&query=%s",type);
        System.out.println(base_query);
        Search myTask=new Search(getApplicationContext(),base_query);
        myTask.execute();
//        if(type == "Hotel")
//        {
//            base_query
//        }
//        if(type == "Shop")
//        {
//
//        }
//        if(type == "Restaurant")
//        {
//
//        }
//        if(type == "Attraction")
//        {
//
//        }
    }
}
class Search extends AsyncTask<Void, Void, String>
{

    private Context context;
    private String API;

    public Search(Context context,String API)
    {
        this.context = context;
        this.API = API;
    }
    @Override
    protected String doInBackground(Void... voids)
    {
        String text_from_API = return_api_response(API);
        System.out.println(text_from_API);
        if(text_from_API == null)
        {
            return null;
        }
        return "Not none";
    }

    @Override
    protected void onPostExecute (String my_string)
    {
        ArrayList<Atrraction_description> arr = new ArrayList<>();
        arr.add(new Atrraction_description("name","Location","some url","price range string", (float) 4.3, (float) 5.6, (float) 7.9,"features","telphone"));
        Intent explicit_intent = new Intent(context, Attraction_list.class);
        explicit_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        explicit_intent.putExtra("object_list", arr);

        context.startActivity(explicit_intent);
//        if(my_string != null)
//        {
//            Intent explicit_intent = new Intent(context, HomeScreen.class);
//            explicit_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            explicit_intent.putExtra("Place",place);
//            context.startActivity(explicit_intent);
//        }
//        else
//        {
//            Toast.makeText(context,"Place not found",Toast.LENGTH_SHORT).show();
//        }
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

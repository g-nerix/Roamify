package com.example.roamify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        place = value_received_from_previous_activity.getStringExtra("Place");
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
        String base_query = "https://api.foursquare.com/v3/places/search?fields=name%2Cgeocodes%2Clocation%2Cphotos%2Cprice%2Crating%2Cfeatures%2Ctel&limit=5";
        base_query += String.format("&near=%s",place);
        base_query+=String.format("&query=%s",type);
        System.out.println(base_query);
        Search myTask=new Search(getApplicationContext(),base_query);
        myTask.execute();
    }
}
class Search extends AsyncTask<Void, Void, ArrayList<Attraction_description>>
{

    private Context context;
    private String API;

    public Search(Context context,String API)
    {
        this.context = context;
        this.API = API;
    }
    @Override
    protected ArrayList<Attraction_description> doInBackground(Void... voids)
    {
        String text_from_API = return_api_response(API);
        System.out.println(text_from_API);
        if(text_from_API == null)
        {
            return null;
        }
        JSONArray json_arr_accommodation;
        ArrayList<Attraction_description> tourist_object_list = new ArrayList<>();
        try
        {
            JSONObject json_obj = new JSONObject(text_from_API);
            json_arr_accommodation = json_obj.getJSONArray("results");
            for (int i = 0; i < json_arr_accommodation.length(); i++) {
                JSONObject accommodation_obj = json_arr_accommodation.getJSONObject(i);
                String name = accommodation_obj.getString("name");

                JSONObject location_obj = accommodation_obj.getJSONObject("location");
                String formatted_address = location_obj.getString("formatted_address");

                JSONObject main_geocodes_obj = accommodation_obj.getJSONObject("geocodes").getJSONObject("main");
                float latitude = (float) main_geocodes_obj.getDouble("latitude");
                float longitude = (float) main_geocodes_obj.getDouble("longitude");

                JSONArray photos_arr = accommodation_obj.getJSONArray("photos");
                String photo_url = "";
                if (photos_arr.length() > 0) {
                    JSONObject photo_obj = photos_arr.getJSONObject(0);
                    photo_url = photo_obj.getString("prefix") + photo_obj.getString("suffix");
                }

//                JSONArray features_arr = accommodation_obj.getJSONArray("features");
//                String feature = "";
//                if (features_arr.length() > 0) {
//                    feature = features_arr.getString(0);
//                }
                Attraction_description attraction_description_obj = new Attraction_description(name,formatted_address,photo_url,"abcd",latitude,longitude, (float) 5.6,"","");
                tourist_object_list.add(attraction_description_obj);
            }
        } catch (JSONException e)
        {
            throw new RuntimeException(e);
        }
        return tourist_object_list;
    }

//    private Atrraction_description json_obj_parser(JSONObject json_obj) throws JSONException
//    {
////        String name = json_obj.getString("name");
////        JSONObject point= json_obj.getJSONObject("point");
////        double latitude = point.getDouble("lat");
////        double longitude = point.getDouble("lon");
//        return new Atrraction_description(name,latitude,longitude);
//    }

    @Override
    protected void onPostExecute (ArrayList<Attraction_description> arr)
    {
        ArrayList<Attraction_description> arr_second = new ArrayList<>();
        arr.add(new Attraction_description("name","Location","some url","price range string", (float) 4.3, (float) 5.6, (float) 7.9,"features","telphone"));
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
            BufferedReader reader = new BufferedReader(new InputStreamReader(in_stream, "UTF-8"));
            char[] buffer = new char[4096]; // read input in chunks of 4KB
            StringBuilder stringBuilder = new StringBuilder();
            int bytesRead;
            while ((bytesRead = reader.read(buffer)) != -1) {
                stringBuilder.append(buffer, 0, bytesRead);
                if (stringBuilder.length() >= 20000000) {
                    // terminate reading if the input reaches the maximum length
                    break;
                }
            }
            text_from_API = stringBuilder.toString();
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

package com.example.roamify;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
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
import java.net.URL;
import java.util.ArrayList;
public class Search_particular_tourist_need extends AsyncTask<String, Void, ArrayList<Name_and_coordinates>>
{
    final String API_key = "5ae2e3f221c38a28845f05b6a91332a87694c022a5bfe17fff46a616";
    private Context context;
    private double latitude,longitude;
    private int activity_num;
    public Search_particular_tourist_need(Context context, Double latitude, Double longitude, int activity_num)
    {
        this.context = context;
        this.latitude = latitude;
        this.longitude = longitude;
        this.activity_num = activity_num;
    }
    @Override
    protected ArrayList<Name_and_coordinates> doInBackground(String... strings)
    {
        String query = strings[0];
        String base_query = String.format("https://api.opentripmap.com/0.1/en/places/radius?radius=1000&lon=%f&lat=%f&limit=20&apikey=%s&rate=2&type=Point&format=json",longitude,latitude,API_key);
        String query_for_accommodation = base_query + query;
        String text_from_API = return_api_response(query_for_accommodation);
        Log.e("Return",text_from_API);
        if(text_from_API == null)
        {
            return null;
        }
        JSONArray json_arr_accommodation;
        ArrayList<Name_and_coordinates> tourist_object_list = new ArrayList<>();
        //ArrayList<Name_and_coordinates> tourist_object_cord = new ArrayList<>();
        try
        {
            json_arr_accommodation = new JSONArray(text_from_API);
            for(int i =0;i<json_arr_accommodation.length();i++)
            {
                tourist_object_list.add(json_obj_parser(json_arr_accommodation.getJSONObject(i)));

                //Log.e("Return value",json_obj_parser(json_arr_accommodation.getJSONObject(i)));
            }
        } catch (JSONException e)
        {
            throw new RuntimeException(e);
        }
        return tourist_object_list;
    }

    private Name_and_coordinates json_obj_parser(JSONObject json_obj) throws JSONException
    {
        String name = json_obj.getString("name");
        JSONObject point= json_obj.getJSONObject("point");
        double latitude = point.getDouble("lat");
        double longitude = point.getDouble("lon");
        return new Name_and_coordinates(name,latitude,longitude);
    }

    @Override
    protected void onPostExecute (ArrayList<Name_and_coordinates> arr)
    {
        if(arr != null)
        {
//            Intent explicit_intent = null;
//            if(activity_num == 1)
//            {
//                explicit_intent = new Intent(context, Activities_Options.class);
//            }
//            if(activity_num == 2)
//            {
//                explicit_intent = new Intent(context, Activities_Options.class);
//            }
//            if(activity_num == 3)
//            {
//                explicit_intent = new Intent(context, Activities_Options.class);
//            }
//            if(activity_num == 4)
//            {
//                explicit_intent = new Intent(context, Activities_Options.class);
//            }
//            explicit_intent.putExtra("object_list", arr);
//            explicit_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(explicit_intent);
        }
        else
        {
            Toast.makeText(context,"No suitable results found",Toast.LENGTH_SHORT).show();
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
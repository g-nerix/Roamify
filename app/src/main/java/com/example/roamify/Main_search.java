package com.example.roamify;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
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

public class Main_search extends AsyncTask<Void, Void, String>
{
    private Context context;
    private String API;
    private String place;

    public Main_search(Context context,String API,String place)
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

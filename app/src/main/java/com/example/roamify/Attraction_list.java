package com.example.roamify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class Attraction_list extends AppCompatActivity
{
    private ArrayList<String> types_options = new ArrayList<>();
    private ArrayList<Double> lon_options = new ArrayList<>();
    private ArrayList<Double> lat_options = new ArrayList<>();
    private Intent value_received_from_previous_activity;
    private ArrayList<Attraction_description> arr;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_list);
        value_received_from_previous_activity = getIntent();

        arr = (ArrayList<Attraction_description>) value_received_from_previous_activity.getSerializableExtra("object_list");
        for (int i = 0; i < arr.size();i++)
        {
            types_options.add(arr.get(i).getName());
            lon_options.add((double) arr.get(i).getLongitude());
            lat_options.add((double) arr.get(i).getLatitude());
            System.out.println(arr.get(i).getLatitude());
            System.out.println(arr.get(i).getLongitude());
        }
        System.out.println(types_options);
        //System.out.println("arr->"+arr.get(1).getName());
        //System.out.println(arr.get(1));
        //System.out.println(arr.get(2));
    }
}
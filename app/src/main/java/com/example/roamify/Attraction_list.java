package com.example.roamify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class Attraction_list extends AppCompatActivity
{

    private Intent value_received_from_previous_activity;
    private ArrayList<Atrraction_description> arr;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_list);
        value_received_from_previous_activity = getIntent();

        arr = (ArrayList<Atrraction_description>) value_received_from_previous_activity.getSerializableExtra("object_list");
        for (int i = 0; i < arr.size();i++)
        {
            //process elements
        }
    }
}
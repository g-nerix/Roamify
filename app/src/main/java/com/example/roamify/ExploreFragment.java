package com.example.roamify;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ExploreFragment extends Fragment
{
    private ImageView hotel,shop,restaurant,attraction;
    private TextView head;
    public interface Button_click {
        void Button_click(String type);
    }
    private String myString;
    private Button_click button_listener_interface;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        button_listener_interface = (Button_click) context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        HomeScreen home = (HomeScreen) getActivity();
        myString = home.place;
        return inflater.inflate(R.layout.fragment_explore, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        head=view.findViewById(R.id.heading);
        hotel=view.findViewById(R.id.img);
        restaurant=view.findViewById(R.id.img_1);
        shop=view.findViewById(R.id.img_2);
        attraction=view.findViewById(R.id.image4);
        head.setText("Welcome to\n"+myString);
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_listener_interface.Button_click("Hotel");
            }
        });

        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_listener_interface.Button_click("Restaurant");
            }
        });

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_listener_interface.Button_click("Shop");
            }
        });
        attraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_listener_interface.Button_click("Attraction");
            }
        });
    }
}

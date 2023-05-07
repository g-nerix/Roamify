package com.example.roamify;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class ExploreFragment extends Fragment
{
    private ImageButton hotel_button, shop_button, restaurant_button, attraction_button;
    public interface Button_click {
        void Button_click(String type);
    }

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
        return inflater.inflate(R.layout.fragment_explore, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hotel_button = view.findViewById(R.id.Hotel);
        shop_button = view.findViewById(R.id.Shop);
        restaurant_button = view.findViewById(R.id.Restaurant);
        attraction_button = view.findViewById(R.id.Attraction);
        hotel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                button_listener_interface.Button_click("Hotel");
            }
        });
        shop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                button_listener_interface.Button_click("Shop");
            }
        });
        restaurant_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                button_listener_interface.Button_click("Restaurant");
            }
        });
        attraction_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                button_listener_interface.Button_click("Attraction");
            }
        });
    }

}

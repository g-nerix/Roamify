package com.example.roamify;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FavouriteFragment extends Fragment
{
    public ArrayList<String> checked_names = new ArrayList<>();
    public ArrayList<Integer> checked_prices = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        checked_names = ((HomeScreen) getActivity()).checked_name_list;
        checked_prices = ((HomeScreen) getActivity()).checked_price_list;

        return inflater.inflate(R.layout.fragment_favourite, container, false);
    }
}
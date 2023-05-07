package com.example.roamify;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener
{
    SearchView search_view;
    public interface listener_text
    {
        void on_result(String search_string);
    }
    private listener_text listener_obj;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener_obj = (listener_text) context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        SearchView searchView = view.findViewById(R.id.Search_view);
        searchView.setOnQueryTextListener(this);
        return view;
    }
    @Override
    public boolean onQueryTextSubmit(String s)
    {
        ((HomeScreen) getActivity()).fragment_search = s;
        listener_obj.on_result(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s)
    {
        return false;
    }
}
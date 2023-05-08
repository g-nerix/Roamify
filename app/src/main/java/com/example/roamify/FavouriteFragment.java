package com.example.roamify;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class FavouriteFragment extends Fragment {
    public ArrayList<String> checked_names = new ArrayList<>();
    public ArrayList<Integer> checked_prices = new ArrayList<>();
    public Integer totalAmount =0;
    private RecyclerView mRecyclerView;
    private ButtonAdapter mAdapter;

    TextView amt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        checked_names = ((HomeScreen) getActivity()).checked_name_list;
        checked_prices = ((HomeScreen) getActivity()).checked_price_list;
        System.out.println(checked_prices);
        View view=inflater.inflate(R.layout.fragment_favourite, container, false);
        amt= (TextView) view.findViewById(R.id.totalAmount);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mAdapter = new FavouriteFragment.ButtonAdapter(checked_names);
        mRecyclerView.setAdapter(mAdapter);
        int totalAmount = 0;
        for(int i=0; i<checked_prices.size(); i++)
        {
            totalAmount += checked_prices.get(i);
        }
        amt.setText(String.valueOf(totalAmount));
        return view;
    }

    public class ButtonAdapter extends RecyclerView.Adapter<FavouriteFragment.ButtonAdapter.ViewHolder> {

        public ButtonAdapter(ArrayList<String> values) {
            checked_names = values;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_button, parent, false);
            return new FavouriteFragment.ButtonAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position)
        {
            System.out.println(holder);
            System.out.println(holder.att_txt);
            String value = checked_names.get(position);
            holder.att_txt.setText(value);
        }

        @Override
        public int getItemCount() {
            return checked_names.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder
        {
            public TextView att_txt;

            public ViewHolder(View itemView) {
                super(itemView);
                att_txt = itemView.findViewById(R.id.attractTxt);
            }
        }
    }
}
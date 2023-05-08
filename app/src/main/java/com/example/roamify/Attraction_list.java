package com.example.roamify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Attraction_list extends AppCompatActivity
{
    private ArrayList<String> types_options = new ArrayList<>();
    private ArrayList<String> loc_options = new ArrayList<>();
    private ArrayList<Double> lon_options = new ArrayList<>();
    private ArrayList<Double> lat_options = new ArrayList<>();
    private ArrayList<String> photo_url_options = new ArrayList<>();
    private ArrayList<Integer> price_options = new ArrayList<>();
    private ArrayList<Integer> rating_options = new ArrayList<>();
    private ArrayList<String> contact_options = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ButtonAdapter mAdapter;
    private Intent value_received_from_previous_activity;
    private ArrayList<Attraction_description> arr;
    private String place;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_list);

        mRecyclerView = findViewById(R.id.recView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ButtonAdapter(types_options);
        mRecyclerView.setAdapter(mAdapter);

        value_received_from_previous_activity = getIntent();
        arr = (ArrayList<Attraction_description>) value_received_from_previous_activity.getSerializableExtra("object_list");
        for (int i = 0; i < arr.size();i++)
        {
            types_options.add(arr.get(i).getName());
            lon_options.add((double) arr.get(i).getLongitude());
            lat_options.add((double) arr.get(i).getLatitude());
            photo_url_options.add(arr.get(i).getPhoto_url());
            price_options.add(arr.get(i).getPrice_range());
            rating_options.add((int) arr.get(i).getRating());
            contact_options.add(arr.get(i).getTelephone_number());
            loc_options.add(arr.get(i).getLocation());
        }
        System.out.println(types_options);
        System.out.println(photo_url_options);
        System.out.println(price_options);
        System.out.println(rating_options);
        System.out.println(contact_options);
        System.out.println(loc_options);
    }
    public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder> {

        public ButtonAdapter(ArrayList<String> values) {
            types_options = values;
        }

        @Override
        public ButtonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_button, parent, false);
            return new ButtonViewHolder(view);
        }
        @Override
        public void onBindViewHolder(ButtonViewHolder holder, int position) {
            ImageView img = findViewById(R.id.imageView);
            String value = types_options.get(position);
            holder.button.setText(value);

            //new LoadImage(img).execute(photo_url_options.get(position));

            holder.button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent explicit_intent = new Intent(getApplicationContext(), Attraction_page.class);
                    explicit_intent.putExtra("name", value);
                    explicit_intent.putExtra("URL",photo_url_options.get(types_options.indexOf(value)));
                    explicit_intent.putExtra("longitude", lon_options.get(types_options.indexOf(value)));
                    explicit_intent.putExtra("latitude", lat_options.get(types_options.indexOf(value)));
                    explicit_intent.putExtra("rating",rating_options.get(types_options.indexOf(value)));
                    explicit_intent.putExtra("contact",contact_options.get(types_options.indexOf(value)));
                    explicit_intent.putExtra("location",loc_options.get(types_options.indexOf(value)));
                    explicit_intent.putExtra("price",price_options.get(types_options.indexOf(value)));
                    explicit_intent.putExtra("place",arr.get(0).getPlace());
                    explicit_intent.putExtra("name_list",arr.get(0).getChecked_name_list());
                    explicit_intent.putExtra("price_list",arr.get(0).getChecked_price_list());
                    startActivity(explicit_intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return types_options.size();
        }
        public class ButtonViewHolder extends RecyclerView.ViewHolder {
            public Button button;

            public ButtonViewHolder(View itemView) {
                super(itemView);
                button = itemView.findViewById(R.id.button);
            }
        }
    }
}
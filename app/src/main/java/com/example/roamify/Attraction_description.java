package com.example.roamify;

import java.io.Serializable;

public class Attraction_description implements Serializable
{
    public String name, location, photo_url,features, telephone_number,price_range;
    public float latitude, longitude,rating;
    public Attraction_description(String name, String location, String photo_url, String price_range, float latitude, float longitude, float rating, String features, String telephone_number)
    {
        this.name = name;
        this.location= location;
        this.photo_url=photo_url;
        this.price_range = price_range;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rating = rating;
        this.features = features;
        this.telephone_number = telephone_number;
    }

    public String getName(){return name;}
    public String getLocation() { return location;}
    public  String getPhoto_url() {return photo_url;}
    public String getPrice_range() {return price_range;}
    public float getLatitude() {return latitude;}
    public float getLongitude() {return longitude;}
    public float getRating() {return rating;}
    public String getFeatures() {return features;}
    public String getTelephone_number() {return telephone_number;}
}
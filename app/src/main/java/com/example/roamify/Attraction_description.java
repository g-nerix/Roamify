package com.example.roamify;

import java.io.Serializable;

public class Attraction_description implements Serializable
{
    public String name, location, photo_url,features, telephone_number,place;
    public float latitude, longitude;
    public int price_range,rating;
    public Attraction_description(String name, String location, String photo_url, int price_range, float latitude, float longitude, int rating, String features, String telephone_number,String place)
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
        this.place = place;
    }

    public String getName(){return name;}
    public String getPlace(){return place;}
    public String getLocation() { return location;}
    public  String getPhoto_url() {return photo_url;}
    public Integer getPrice_range() {return price_range;}
    public float getLatitude() {return latitude;}
    public float getLongitude() {return longitude;}
    public Integer getRating() {return rating;}
    public String getFeatures() {return features;}
    public String getTelephone_number() {return telephone_number;}
    public void display()
    {
        System.out.println("name" + name);
        System.out.println("location" + location);
        System.out.println("photo_url"+ photo_url);
        System.out.println("price_range" + price_range);
        System.out.println("latitude" + latitude);
        System.out.println("longitude" + longitude);
        System.out.println("Rating" + rating);
        System.out.println("features" + features);
        System.out.println("telephone_number" + telephone_number);
    }
}
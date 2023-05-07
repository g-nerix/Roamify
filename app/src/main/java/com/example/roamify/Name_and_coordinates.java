package com.example.roamify;
import java.io.Serializable;

public class Name_and_coordinates implements Serializable
{
    private String name;
    private double latitude,longitude;
    public Name_and_coordinates(String name, double latitude,double longitude)
    {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public String getName()
    {
        return this.name;
    }
    public double getLatitude()
    {
        return this.latitude;
    }
    public double getLongitude()
    {
        return this.longitude;
    }
}
package com.example.weatherAPI;

public class City {

    private int lat;
    private int lon;
    private String name;

    public City(String name, int lat, int lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
}

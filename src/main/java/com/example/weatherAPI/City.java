package com.example.weatherAPI;

public class City {

    private String name;
    private double lat;
    private double lon;

    // init
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

    public double getLat(){
        return this.lat;
    }

    public void setLat(double lat){
        this.lat = lat;
    }

    public double getLon(){
        return this.lon;
    }

    public void setLon(double lon){
        this.lon = lon;
    }

}

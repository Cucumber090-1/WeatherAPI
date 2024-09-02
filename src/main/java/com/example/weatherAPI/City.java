package com.example.weatherAPI;

public class City {

    private String name;
    private double lat;
    private double lon;
    private int id;

    // init
    public City(String name, double lat, double lon, int id) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.id = id;
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

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

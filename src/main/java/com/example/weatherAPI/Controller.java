package com.example.weatherAPI;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashSet;

@RestController
public class Controller {

    public ArrayList<City> cities = new ArrayList<>();

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/get-cities")
    public String getCities(){
        String citiesStr = "";
        for (int i = 0; i < cities.size(); i++){
            citiesStr += cities.get(i).getName() + " ";
        }
        return citiesStr;
    }

    @PostMapping("/add-city")
    public String addCity(@RequestBody City city){
        if (cities.contains(city)){
            return "Error: city already exists";
        }
        else{
            cities.add(city);
            System.out.println("Город " + city.getName() + " успешно добавлен!");
            return "";
        }
    }

    @GetMapping("get-city-by-id")
    public String getCityByID(@RequestParam(value = "id", defaultValue = "0") int id){
        RestTemplate restTemplate = new RestTemplate();
        City city = cities.get(id);
        String resURL = String.format("https://api.openweathermap.org/data/2.5/weather?" +
                "lat=%f&lon=%f&appid=1c4dca6d2814215616c0da78bc4d02e3", city.getLat(), city.getLon());
        String result = restTemplate.getForObject(resURL, String.class);
        return result;
    }

}

package com.example.weatherAPI;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
public class Controller {

    public ArrayList<City> cities = new ArrayList<>();
    private Dotenv dotenv = Dotenv.load();
    private String app_id = dotenv.get("APP_ID");
    private final String patternURL = "https://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&appid=%s";

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/get-cities")
    public ArrayList getCities(){
        String citiesStr = "";
        for (int i = 0; i < cities.size(); i++){
            citiesStr += cities.get(i).getName() + " ";
        }
        return cities;
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
        System.out.println(app_id);
        String URL = String.format(patternURL, city.getLat(), city.getLon(), app_id);
        String result = restTemplate.getForObject(URL, String.class);
        return result;
    }

}

package com.example.weatherAPI;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
public class Controller {

    // init array for saving cities
    public ArrayList<City> cities = new ArrayList<>();

    // init and get app_id from .env file
    private Dotenv dotenv = Dotenv.load();
    private String app_id = dotenv.get("APP_ID");
    private int prevID = 0;

    // get url pattern
    private final String patternURL = "https://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&appid=%s";

    // test GET handle, returns hello
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    // get all saved cities data handle GET
    @GetMapping("/get-cities")
    public ArrayList getCities(){
        return cities;
    }

    // additing a city to array handle POST
    @PostMapping("/add-city")
    public String addCity(@RequestBody City city){
        if (city != null && city.getName() != null && city.getLat() >= -90 && city.getLat() <= 90
                && city.getLon() >= -180 && city.getLon() <= 180){
            try {
                city.setId(prevID + 1);
                cities.add(city);
                System.out.println(city.getName() + " city successfully added");
                return "Success";
            }
            catch (Exception e){
                System.err.println("Error while adding city: " + e.getMessage());
                return "Error while adding city";
            }
        }
        else{
            return "Error: Invalid city data";
        }
    }

    // get weather data from the curtain city by its ID, GET
    @GetMapping("get-city-by-id")
    public String getCityByID(@RequestParam(value = "id", defaultValue = "1") int id){
        if (cities.isEmpty()){
            return "Error: list is empty";
        }
        else{
            RestTemplate restTemplate = new RestTemplate();
            City city = cities.get(id - 1);

            // make URL using URL pattern
            String URL = String.format(patternURL, city.getLat(), city.getLon(), app_id);

            // make request and save data to result
            String result = restTemplate.getForObject(URL, String.class);
            return result;
        }
    }

}

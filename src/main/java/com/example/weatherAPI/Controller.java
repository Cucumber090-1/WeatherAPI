package com.example.weatherAPI;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RestController
public class Controller {

    // init array for saving cities
    public HashMap<Integer, City> cities = new HashMap<>();

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
    public HashMap getCities(){
        return cities;
    }

    // additing a city to array handle POST
    @PostMapping("/add-city")
    public String addCity(@RequestBody City city){
        if (!cities.containsValue(city)){
            try {
                prevID += 1;
                city.setId(prevID);
                cities.put(city.getId(), city);
                System.out.println(city.getName() + " city successfully added");
                return "Success";
            }
            catch (Exception e){
                System.err.println("Error while adding city: " + e.getMessage());
                return "Error while adding city";
            }
        }
        else{
            return "Error: city already exists";
        }
    }

    // get weather data from the curtain city by its ID, GET
    // first city in HashMap saved as default id
    @GetMapping("get-city-by-id")
    public String getCityByID(@RequestParam(value = "id", defaultValue = "1") int id){
        if (cities.isEmpty()){
            return "Error: list is empty";
        }
        else{
            RestTemplate restTemplate = new RestTemplate();
            City city = cities.get(id);

            // make URL using URL pattern
            String URL = String.format(patternURL, city.getLat(), city.getLon(), app_id);

            // make request and save data to result
            String result = restTemplate.getForObject(URL, String.class);
            return result;
        }
    }

}

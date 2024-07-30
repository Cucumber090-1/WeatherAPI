package com.example.weatherAPI;

import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
public class Controller {

    public HashSet<String> cities = new HashSet<>();

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/get-cities")
    public String getCities(){
        return cities.toString();
    }

    @PostMapping("/add-city")
    public City addCity(@RequestBody City city){
        cities.add(city.getName());
        System.out.println("Город " + city.getName() + " успешно добавлен!");
        return city;
    }

}

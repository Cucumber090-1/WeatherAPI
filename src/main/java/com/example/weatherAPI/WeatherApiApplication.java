package com.example.weatherAPI;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherApiApplication {

	public static void main(String[] args) {

		// init dotenv data and set properties in application
		Dotenv dotenv = Dotenv.load();
		System.setProperty("server.port", dotenv.get("SERVER_PORT", "8080"));
		System.setProperty("spring.application.name", dotenv.get("SPRING.APPLICATION.NAME", "weatherAPI"));

		// start app
		SpringApplication.run(WeatherApiApplication.class, args);

	}

}
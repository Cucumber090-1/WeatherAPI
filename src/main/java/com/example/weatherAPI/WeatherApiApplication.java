package com.example.weatherAPI;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherApiApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();
		System.setProperty("server.port", dotenv.get("SERVER_PORT", "8080"));
		System.setProperty("spring.application.name", dotenv.get("SPRING.APPLICATION.NAME", "weatherAPI"));
		SpringApplication.run(WeatherApiApplication.class, args);

	}

}
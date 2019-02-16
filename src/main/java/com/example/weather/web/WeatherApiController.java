package com.example.weather.web;

import com.example.weather.integration.weather.Weather;
import com.example.weather.integration.weather.WeatherService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
public class WeatherApiController {

	private final WeatherService weatherService;

	public WeatherApiController(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	//TestURL: http:localhost:8080//api/weather/zipCode?zipCode=80130 and response will provide 5 days weather data
	@RequestMapping(value = "/zipCode", produces = "application/json")
	public Weather getWeatherByZip(@RequestParam("zipCode") Integer zipCode) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		return this.weatherService.getWeather(zipCode, null);
	}

}

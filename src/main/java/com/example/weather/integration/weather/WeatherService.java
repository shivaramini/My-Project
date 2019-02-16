package com.example.weather.integration.weather;

import java.net.URI;

import com.example.weather.WeatherAppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

@Service
public class WeatherService {

	private static final String WEATHER_URL =
			"http://api.openweathermap.org/data/2.5/forecast?zip={zip code},{country code}&APPID={key}";

	//Default CountryCode to USA
	private static final String COUNTRY_CODE="us";

	private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

	private final RestTemplate restTemplate;

	private final String apiKey;

	public WeatherService(RestTemplateBuilder restTemplateBuilder,
			WeatherAppProperties properties) {
		this.restTemplate = restTemplateBuilder.build();
		this.apiKey = properties.getApi().getKey();
	}

	@Cacheable("weather")
	public Weather getWeather(Integer zipCode, String countryCode) {
		logger.info("Requesting current weather for {}/{}", zipCode, countryCode);
		URI url = new UriTemplate(WEATHER_URL).expand(zipCode, COUNTRY_CODE, this.apiKey);
		return invoke(url, Weather.class);
	}

	private <T> T invoke(URI url, Class<T> responseType) {
		RequestEntity<?> request = RequestEntity.get(url)
				.accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<T> exchange = this.restTemplate
				.exchange(request, responseType);
		return exchange.getBody();
	}

}

package com.example.weather.integration.ows;

import com.example.weather.integration.weather.Weather;
import com.example.weather.integration.weather.WeatherDetails;
import com.example.weather.integration.weather.WeatherService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

@RunWith(SpringRunner.class)
@RestClientTest(WeatherService.class)
@TestPropertySource(properties = "app.weather.api.key=test-ABC")
public class WeatherServiceTest {

	private static final String URL = "http://api.openweathermap.org/data/2.5/";

	@Autowired
	private WeatherService weatherService;

	@Autowired
	private MockRestServiceServer server;

	/*
	Success Test case returns 200 when called weather service
	 */
	@Test
	public void getWeather() {
		this.server.expect(
				requestTo(URL + "forecast?zip=80130,us&APPID=test-ABC"))
				.andRespond(withSuccess(
						new ClassPathResource("weather-us.json", getClass()),
						MediaType.APPLICATION_JSON));
		Weather forecast = this.weatherService.getWeather(80130, "us");
		assertThat(forecast.getStatus()).isEqualTo("success");
		List<WeatherDetails> details = forecast.getWeatherDetails();
		for(WeatherDetails dt : details){
			assertThat(dt.getDetails().getTemperature()).isEqualTo("51.51f");
		}
		this.server.verify();
	}

	/*
	Test for Null Check
	 */
	@Test
	public void getWeatherNullCheck() {
		this.server.expect(
				requestTo(URL + "forecast?zip=0,us&APPID=test-ABC"))
				.andRespond(withSuccess(
						new ClassPathResource("weather-us.json", getClass()),
						MediaType.APPLICATION_JSON));
		Weather forecast = this.weatherService.getWeather(0, "us");
		assertThatNullPointerException();
		this.server.verify();
	}

	/*
	We can also check for more tests using the data in the weather.json file like  "assertNotNull(desiredField)";
	 */
}

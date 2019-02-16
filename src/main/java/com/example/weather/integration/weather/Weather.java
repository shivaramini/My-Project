package com.example.weather.integration.weather;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

    @JsonAlias({"cod","status"})
    private String status;

    @JsonAlias({"list","weatherDetails"})
    private List<WeatherDetails> weatherDetails;

    public String getStatus() {
        if (status.equalsIgnoreCase("200"))
            return "success";
        else
            return "Failed to Access service";
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public List<WeatherDetails> getWeatherDetails() {
		return weatherDetails;
	}

	public void setWeatherDetails(List<WeatherDetails> weatherDetails) {
		this.weatherDetails = weatherDetails;
	}
}

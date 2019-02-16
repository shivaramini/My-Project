package com.example.weather.integration.weather;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Details {

    @JsonAlias({"temp","temperature"})
    private double temperature;

    @JsonAlias({"temp_min","temperatureMin"})
    private double temperatureMin;

    @JsonAlias({"temp_max","temperatureMax"})
    private double temperatureMax;

    public String getTemperature() {
        double fahrenheitTemp = (this.temperature * 1.8) - 459.67;
        return String.format("%4.2f", fahrenheitTemp)+"f";
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getTemperatureMin() {
        double fahrenheitTempMin = (this.temperatureMin * 1.8) - 459.67;
        return String.format("%4.2f", fahrenheitTempMin)+"f";
    }

    public void setTemperatureMin(double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public String getTemperatureMax() {
        double fahrenheitTempMax = (this.temperatureMax * 1.8) - 459.67;
        return String.format("%4.2f", fahrenheitTempMax)+"f";
    }

    public void setTemperatureMax(double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }
}

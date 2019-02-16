package com.example.weather.integration.weather;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDetails {


    @JsonAlias({"dt","date"})
    private Long date;

    @JsonAlias({"main","details"})
    private Details details;


    public String getDate() {
        DateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        Date dt = new Date(date);
        return sdf.format(dt);
    }

    public void setDate(long date) {
        this.date =  date * 1000;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }
}

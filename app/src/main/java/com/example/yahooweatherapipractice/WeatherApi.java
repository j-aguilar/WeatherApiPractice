package com.example.yahooweatherapipractice;

public class WeatherApi {
    public int getTempature() {
        return tempature;
    }

    public String getCondition() {
        return condition;
    }

    public String getLocation() {
        return location;
    }

    private int tempature;
    private String condition;
    private String location;
    private final String base = "https://api.openweathermap.org/data/2.5/weather?units=imperial&q=";
    private String query = "Tampa,US";
    private String jsonString;

    public String getUrl() {
        return base + query + Constants.getApiKey();
    }

}

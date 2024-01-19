package com.project.kkiaprj.service;


import com.fasterxml.jackson.core.JsonProcessingException;

public interface WeatherService  {
    String getStadiumWeatherDetails(String teamName, String lat, String lon) throws JsonProcessingException;
}

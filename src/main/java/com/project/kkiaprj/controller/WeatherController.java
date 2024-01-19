package com.project.kkiaprj.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.kkiaprj.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;


    @GetMapping("/weather")
    public ResponseEntity<String> getAllStadiumsWeatherDetails() throws JsonProcessingException {
        StringBuilder resultBuilder = new StringBuilder();

        String[][] stadiums = {
                {"LG", "37.5124", "127.0719"},
                {"KT", "37.3", "127.0097"},
                {"SSG", "37.4371", "126.6933"},
                {"NC", "35.2228", "128.5824"},
                {"두산", "37.5124", "127.0719"},
                {"KIA", "35.1685", "126.8883"},
                {"롯데", "35.1941", "129.0615"},
                {"삼성", "35.8411", "128.682"},
                {"한화", "36.3172", "127.4286"},
                {"키움", "37.4982", "126.86"}
        };

        for (String[] stadium : stadiums) {
            String teamName = stadium[0];
            String lat = stadium[1];
            String lon = stadium[2];

            String stadiumWeatherDetails = weatherService.getStadiumWeatherDetails(teamName, lat, lon);
            resultBuilder.append(stadiumWeatherDetails);
        }

        return ResponseEntity.ok(resultBuilder.toString());
    }
}





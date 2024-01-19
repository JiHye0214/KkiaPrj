package com.project.kkiaprj.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Value("${app.api.weatherKey}")
    private String weatherKey;
        @Override
        public String getStadiumWeatherDetails(String teamName, String lat, String lon) throws JsonProcessingException {
            String apiUrl = "http://api.openweathermap.org/data/2.5/onecall?"
                    + "lat=" + lat + "&lon=" + lon
                    + "&exclude=current,minutely,hourly&appid=" + weatherKey;

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(response.getBody());

             // 오전/오후 기온, 강수량 추출
            double minTempKelvin = Double.parseDouble(jsonResponse.path("daily").get(0).path("temp").path("min").asText());
            double maxTempKelvin = Double.parseDouble(jsonResponse.path("daily").get(0).path("temp").path("max").asText());
            String rainAmount = jsonResponse.path("daily").get(0).path("rain").asText();

            // 섭씨로 변환
            double minTempCelsius = minTempKelvin - 273.15;
            double maxTempCelsius = maxTempKelvin - 273.15;

            // 이미지 URL 추출
            String weatherIcon = jsonResponse.path("daily").get(0).path("weather").get(0).path("icon").asText();
            String imageUrl = "http://openweathermap.org/img/w/" + weatherIcon + ".png";

            return "팀: " + teamName + "\n"
                    + "최저기온: " + String.format("%.2f", minTempCelsius) + "°C\n"
                    + "최고기온: " + String.format("%.2f", maxTempCelsius) + "°C\n"
                    + "강수량: " + rainAmount + "mm\n"
                    + "Weather Icon: " + imageUrl + "\n\n";
        }
    }


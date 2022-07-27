package com.example.demolast.Service;

import com.example.demolast.Entity.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.math.BigDecimal;
import java.net.URI;

@Service
public class WeatherService {

    private static String weather_url = "https://api.openweathermap.org/data/2.5/weather?q={city},{country}&appid={apiKey}";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${apiKey}")
    private  String apiKey;


    public WeatherService(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }


    // Response Entity
    public Weather convert(ResponseEntity<String> response){
        try{
            JsonNode node = objectMapper.readTree(response.getBody());
            return new Weather(
                    BigDecimal.valueOf(node.path("main").path("temp").asDouble()),
                    BigDecimal.valueOf(node.path("main").path("feels_like").asDouble()),
                    node.path("weather").get(0).path("description").asText(),
                    node.path("sys").path("country").asText()
                    );
        }
        catch (JsonProcessingException e){
//            e.printStackTrace();
            throw new RuntimeException("Error parsing JSON" + e);
        }



    }


    //URI template
    public Weather getCurrentWeather(String city, String country){
        URI uriTemplate = null;

        uriTemplate = new UriTemplate(weather_url).expand(city,country,apiKey);

        ResponseEntity response = restTemplate.getForEntity(uriTemplate, String.class);

        return convert(response);


    }












}

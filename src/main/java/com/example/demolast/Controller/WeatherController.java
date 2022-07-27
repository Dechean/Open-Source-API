package com.example.demolast.Controller;

import com.example.demolast.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }


    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("currentWeather", weatherService.getCurrentWeather("Kathmandu","Nepal") );
        return "index";
    }








}

package com.taicw.learning.weatherreport.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "weather-city-data", fallback = WeatherCityDataClientFallback.class, qualifier = "weatherCityDataClient")
//@RequestMapping("/city")
public interface WeatherCityDataClient {

    @GetMapping("/city/list")
    List getCityList();
}

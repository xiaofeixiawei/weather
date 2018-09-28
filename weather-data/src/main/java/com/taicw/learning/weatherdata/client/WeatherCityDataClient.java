package com.taicw.learning.weatherdata.client;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "weather-city-data", fallback = WeatherCityDataClientFallback.class)
public interface WeatherCityDataClient {

    @GetMapping(value = "/city/list")
    List getCityList();
}

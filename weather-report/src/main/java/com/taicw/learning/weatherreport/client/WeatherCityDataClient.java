package com.taicw.learning.weatherreport.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "gateway", fallback = WeatherCityDataClientFallback.class, qualifier = "weatherCityDataClient")
//@RequestMapping("/city")
public interface WeatherCityDataClient {

    @GetMapping("/cityData/city/list")
    List getCityList();
}

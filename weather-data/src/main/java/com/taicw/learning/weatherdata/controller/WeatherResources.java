package com.taicw.learning.weatherdata.controller;

import com.taicw.learning.weatherdata.sevice.WeatherDataService;
import com.taicw.learning.weatherdata.vo.Weather;
import com.taicw.learning.weatherdata.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by taichangwei on 2018/9/1.
 */
@RestController
@RequestMapping("/weather")
public class WeatherResources {

    @Autowired
    private WeatherDataService weatherDataService;

    @GetMapping("/cityId/{cityId}")
    public Weather getWeatherByCityId(@PathVariable("cityId") String cityId){
        return weatherDataService.getDataByCityId(cityId).getData();
    }

    @GetMapping("/cityName/{cityName}")
    public Weather getWeatherByCityName(@PathVariable("cityName") String cityName){
        return weatherDataService.getDataByCityName(cityName).getData();
    }

    @GetMapping("/flush/{cityId}")
    public Map flushWeatherData(@PathVariable("cityId") String cityId){
        weatherDataService.syncDataByCityId(cityId);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "成功");
        return result;
    }
}

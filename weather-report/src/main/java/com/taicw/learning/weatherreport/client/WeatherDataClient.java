package com.taicw.learning.weatherreport.client;

import com.taicw.learning.weatherreport.vo.Weather;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by taichangwei on 2018/9/28.
 */
@FeignClient(value = "gateway", fallback = WeatherDataClientFallback.class, qualifier = "weatherDataClient")
public interface WeatherDataClient {

    @GetMapping("/weatherData/weather/cityId/{cityId}")
    Weather getDataByCityId(@PathVariable("cityId") String cityId);

    @GetMapping("/weatherData/weather/cityName/{cityName}")
    Weather getWeatherByCityName(@PathVariable("cityName") String cityName);


}

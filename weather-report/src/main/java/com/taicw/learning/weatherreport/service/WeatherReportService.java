package com.taicw.learning.weatherreport.service;

import com.taicw.learning.weatherreport.vo.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by taichangwei on 2018/9/1.
 */
@Service
public class WeatherReportService implements IWeatherReportService {


    @Autowired
    RestTemplate restTemplate;

    @Override
    public Weather getDataByCityId(String cityId) {
         return restTemplate.getForObject("http://localhost:8082/weather/cityId/{cityId}", Weather.class, cityId);
    }

    public Weather getDataByCityName(String name) {
        return restTemplate.getForObject("http://localhost:8082/weather/cityName/{cityName}", Weather.class, name);
    }

    @Override
    public List getCityList() {
        return restTemplate.getForObject("http://127.0.0.1:8081/city/list", List.class);
    }
}

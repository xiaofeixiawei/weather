package com.taicw.learning.weatherreport.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by taichangwei on 2018/9/28.
 */
@Component
public class WeatherCityDataClientFallback implements WeatherCityDataClient {
    private Logger logger = LoggerFactory.getLogger(WeatherCityDataClientFallback.class);

    @Override
    public List getCityList() {
        logger.error("weather-city-data微服务异常，被熔断");

        ArrayList<Map> cityList = new ArrayList<>();
        Map<String, String> city = new HashMap<>();
        city.put("cityName", "北京");
        city.put("cityId", "101010100");
        cityList.add(city);
        return cityList;
    }
}

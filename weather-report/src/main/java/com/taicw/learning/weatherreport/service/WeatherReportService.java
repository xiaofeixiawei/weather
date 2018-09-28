package com.taicw.learning.weatherreport.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.taicw.learning.weatherreport.properties.ServiceProperties;
import com.taicw.learning.weatherreport.vo.Forecast;
import com.taicw.learning.weatherreport.vo.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by taichangwei on 2018/9/1.
 */
@Service
public class WeatherReportService implements IWeatherReportService {

    private Logger logger = LoggerFactory.getLogger(WeatherReportService.class);


    @Autowired
    RestTemplate restTemplate;

//    @Autowired
//    ServiceProperties serviceProperties;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    @HystrixCommand(fallbackMethod = "getWeatherDataFallbackMethod")
    public Weather getDataByCityId(String cityId) {

        String serviceId = "weather-data";
        return restTemplate.getForObject("http://" + serviceId + "/weather/cityId/{cityId}", Weather.class, cityId);

        //return restTemplate.getForObject(getServiceUrl("weather-data") + "/weather/cityId/{cityId}", Weather.class, cityId);

        //return restTemplate.getForObject(serviceProperties.getWeatherDataUrl()+"/weather/cityId/{cityId}", Weather.class, cityId);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getWeatherDataFallbackMethod")
    public Weather getDataByCityName(String name) {

        String serviceId = "weather-data";
        return restTemplate.getForObject("http://" + serviceId + "/weather/cityName/{cityName}", Weather.class, name);

        //return restTemplate.getForObject(serviceProperties.getWeatherDataUrl()+"/weather/cityName/{cityName}", Weather.class, name);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getCityListFallbackMethod")
    public List getCityList() {

        String serviceId = "weather-city-data";
        return restTemplate.getForObject("http://" + serviceId + "/city/list", List.class);

        //return restTemplate.getForObject(serviceProperties.getCityDataUrl() + "/city/list", List.class);
    }


//    private String getServiceUrl(String serviceId){
//        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
//        ServiceInstance instance = instances.get(0);
//        return  "http://" + instance.getHost() + ":" + instance.getPort();
//    }


    public Weather getWeatherDataFallbackMethod(String cityId){

        logger.error("weather-data微服务异常，被熔断");

        Weather weather = new Weather();
        weather.setAqi("108");
        weather.setCity("北京");
        weather.setWendu("18");
        weather.setGanmao("昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。");

        Forecast forecast = new Forecast();
        forecast.setDate("1日星期一");
        forecast.setHigh("高温 22℃");
        forecast.setLow("低温 12℃");
        forecast.setFengxiang("西北风");
        forecast.setFengli("4-5级");
        forecast.setType("晴");
        ArrayList<Forecast> forecastArrayList = new ArrayList<>();
        forecastArrayList.add(forecast);

        weather.setForecast(forecastArrayList);

        return weather;
    }

    public List getCityListFallbackMethod(){

        logger.error("weather-city-data微服务异常，被熔断");

        ArrayList<Map> cityList = new ArrayList<>();
        Map<String, String> city = new HashMap<>();
        city.put("cityName", "北京");
        city.put("cityId", "101010100");
        cityList.add(city);
        return cityList;
    }

}



























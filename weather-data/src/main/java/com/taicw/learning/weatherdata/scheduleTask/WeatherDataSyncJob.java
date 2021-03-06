package com.taicw.learning.weatherdata.scheduleTask;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.taicw.learning.weatherdata.client.WeatherCityDataClient;
import com.taicw.learning.weatherdata.sevice.WeatherDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by taichangwei on 2018/9/7.
 */
@Component
public class WeatherDataSyncJob {

    private Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);

//    @Autowired
//    @Qualifier("restTemplate")
//    private RestTemplate restTemplate;

    @Autowired
    //@Qualifier("weatherCityDataClient") //这里ide提示有多个bean，实际上WeatherCityDataClient的代理类默认为primary
    private WeatherCityDataClient weatherCityDataClient;

    @Autowired
    private WeatherDataService weatherDataService;

//    @Value("${city.service.url}")
//    private String cityServiceUrl;

//    @Autowired
//    private DiscoveryClient discoveryClient;


    @Scheduled(fixedDelay = 2*60*60*1000)
    //@HystrixCommand(fallbackMethod = "doSyncWeatherDataFallbackMethod")
    public void doSyncWeatherData(){
//        List<ServiceInstance> instances = discoveryClient.getInstances("weather-city-data");
//        ServiceInstance instance = instances.get(0);
//        String cityServiceUrl = "http://" + instance.getHost() + ":" + instance.getPort();

//        String serviceId = "weather-city-data";
//        List cityList = restTemplate.getForObject("http://" + serviceId + "/city/list", List.class);

        List cityList = weatherCityDataClient.getCityList();

        logger.info("开始同步天气数据");
        for (Object c : cityList){
            Map city = (Map)c;
            weatherDataService.syncDataByCityId(city.get("cityId").toString());
            logger.info("同步【{}】天气数据", city.get("cityName").toString());
        }
        logger.info("同步天气数据结束");
    }

//    public void doSyncWeatherDataFallbackMethod(){
//        logger.error("地区服务异常，被熔断！");
//    }

}

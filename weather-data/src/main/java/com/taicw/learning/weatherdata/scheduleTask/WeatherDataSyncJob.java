package com.taicw.learning.weatherdata.scheduleTask;

import com.taicw.learning.weatherdata.sevice.WeatherDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by taichangwei on 2018/9/7.
 */
@Component
public class WeatherDataSyncJob {

    private Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WeatherDataService weatherDataService;


    @Scheduled(fixedDelay = 2*60*60*1000)
    public void doSyncWeatherData(){
        List cityList = restTemplate.getForObject("http://127.0.0.1:8081/city/list", List.class);

        logger.info("开始同步天气数据");
        for (Object c : cityList){
            Map city = (Map)c;
            weatherDataService.syncDataByCityId(city.get("cityId").toString());
            logger.info("同步【{}】天气数据", city.get("cityName").toString());
        }
        logger.info("同步天气数据结束");
    }

}

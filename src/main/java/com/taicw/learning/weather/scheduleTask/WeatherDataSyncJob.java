package com.taicw.learning.weather.scheduleTask;

import com.taicw.learning.weather.sevice.CityDataService;
import com.taicw.learning.weather.sevice.WeatherDataService;
import com.taicw.learning.weather.vo.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by taichangwei on 2018/9/7.
 */
@Component
public class WeatherDataSyncJob {

    private Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);

    @Autowired
    CityDataService cityDataService;

    @Autowired
    WeatherDataService weatherDataService;


    @Scheduled(fixedDelay = 2*60*60*1000)
    public void doSyncWeatherData() throws Exception {
        List<City> cityList = cityDataService.listCity();

        logger.info("开始同步天气数据");
        for (City city : cityList){
            weatherDataService.syncDataByCityId(city.getCityId());
            logger.info("同步【{}】天气数据", city.getCityName());
        }
        logger.info("同步天气数据结束");
    }

}

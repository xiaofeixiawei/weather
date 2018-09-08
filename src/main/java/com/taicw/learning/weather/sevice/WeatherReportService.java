package com.taicw.learning.weather.sevice;

import com.taicw.learning.weather.vo.Weather;
import com.taicw.learning.weather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by taichangwei on 2018/9/1.
 */
@Service
public class WeatherReportService implements IWeatherReportService {

    @Autowired
    private IWeatherDataService weatherDataService;

    @Override
    public Weather getDataByCityId(String cityId) {
        WeatherResponse weatherResponse = weatherDataService.getDataByCityId(cityId);
        return weatherResponse.getData();
    }

    public Weather getDataByCityName(String name) {
        WeatherResponse weatherResponse = weatherDataService.getDataByCityName(name);
        return weatherResponse.getData();
    }
}

package com.taicw.learning.weatherreport.client;

import com.taicw.learning.weatherreport.vo.Forecast;
import com.taicw.learning.weatherreport.vo.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by taichangwei on 2018/9/28.
 */
@Component
public class WeatherDataClientFallback implements WeatherDataClient {

    private Logger logger = LoggerFactory.getLogger(WeatherDataClientFallback.class);

    @Override
    public Weather getDataByCityId(String cityId) {
        return getWeather();
    }

    @Override
    public Weather getWeatherByCityName(String cityName) {
        return getWeather();
    }


    private Weather getWeather(){
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
}

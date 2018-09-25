package com.taicw.learning.weatherdata.sevice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taicw.learning.weatherdata.vo.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by taichangwei on 2018/9/1.
 */
@Service
public class WeatherDataService implements IWeatherDataService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherDataService.class);

    private static final long TIME_OUT = 1800L;

    @Value("${weather.api.uri}")
    private String weatherUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        return doGetWeather(weatherUrl + "?citykey=" + cityId);
    }

    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        return doGetWeather(weatherUrl + "?city=" + cityName);
    }

    @Override
    public void syncDataByCityId(String cityId) {
        saveWeatherData(weatherUrl + "?citykey=" + cityId);
    }

    private WeatherResponse doGetWeather(String uri){
        String bodyStr = stringRedisTemplate.opsForValue().get(uri);
        if (!StringUtils.isEmpty(bodyStr)){
            logger.info("redis中已有缓存数据，直接取缓存数据");
        } else {
            logger.info("redis中没有缓存数据，获取最新数据并缓存");
            // 缓存中没有，再调用服务接口来获取最新数据
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
            if(responseEntity.getStatusCode() == HttpStatus.OK){
                bodyStr = responseEntity.getBody();
                logger.info("成功获取天气数据：{}", bodyStr);
            } else {
                logger.error("获取天气数据失败，{}", responseEntity.getBody());
            }
            stringRedisTemplate.opsForValue().set(uri, bodyStr, TIME_OUT, TimeUnit.SECONDS);
        }

        WeatherResponse weatherResponse = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            weatherResponse = mapper.readValue(bodyStr, WeatherResponse.class);
        } catch (IOException e) {
            logger.error("天气数据反序列化出错", e);
        }

        return weatherResponse;
    }

    private void saveWeatherData(String uri){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK){
            stringRedisTemplate.opsForValue().set(uri, responseEntity.getBody(), TIME_OUT, TimeUnit.SECONDS);
        }
    }
}

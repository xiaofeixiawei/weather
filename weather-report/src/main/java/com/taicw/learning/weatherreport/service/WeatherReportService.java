package com.taicw.learning.weatherreport.service;

import com.taicw.learning.weatherreport.properties.ServiceProperties;
import com.taicw.learning.weatherreport.vo.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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

//    @Autowired
//    ServiceProperties serviceProperties;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public Weather getDataByCityId(String cityId) {

        String serviceId = "weather-data";
        return restTemplate.getForObject("http://" + serviceId + "/weather/cityId/{cityId}", Weather.class, cityId);

        //return restTemplate.getForObject(getServiceUrl("weather-data") + "/weather/cityId/{cityId}", Weather.class, cityId);

        //return restTemplate.getForObject(serviceProperties.getWeatherDataUrl()+"/weather/cityId/{cityId}", Weather.class, cityId);
    }

    public Weather getDataByCityName(String name) {

        String serviceId = "weather-data";
        return restTemplate.getForObject("http://" + serviceId + "/weather/cityName/{cityName}", Weather.class, name);

        //return restTemplate.getForObject(serviceProperties.getWeatherDataUrl()+"/weather/cityName/{cityName}", Weather.class, name);
    }

    @Override
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

}

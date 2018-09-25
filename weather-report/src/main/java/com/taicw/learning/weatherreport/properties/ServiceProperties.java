package com.taicw.learning.weatherreport.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by taichangwei on 2018/9/25.
 */
@Component
@ConfigurationProperties(prefix = "service.url")
public class ServiceProperties {

    private String cityDataUrl;

    private String weatherDataUrl;

    public String getCityDataUrl() {
        return cityDataUrl;
    }

    public void setCityDataUrl(String cityDataUrl) {
        this.cityDataUrl = cityDataUrl;
    }

    public String getWeatherDataUrl() {
        return weatherDataUrl;
    }

    public void setWeatherDataUrl(String weatherDataUrl) {
        this.weatherDataUrl = weatherDataUrl;
    }

}

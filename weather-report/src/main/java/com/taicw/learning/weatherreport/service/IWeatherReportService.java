package com.taicw.learning.weatherreport.service;

import com.taicw.learning.weatherreport.vo.Weather;

import java.util.List;

/**
 * Created by taichangwei on 2018/9/1.
 */
public interface IWeatherReportService {

    Weather getDataByCityId(String cityId);

    Weather getDataByCityName(String cityName);

    List getCityList();
}

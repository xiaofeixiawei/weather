package com.taicw.learning.weather.sevice;

import com.taicw.learning.weather.vo.Weather;

/**
 * Created by taichangwei on 2018/9/1.
 */
public interface IWeatherReportService {

    Weather getDataByCityId(String cityId);

    Weather getDataByCityName(String cityName);
}

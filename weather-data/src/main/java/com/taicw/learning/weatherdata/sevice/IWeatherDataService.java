package com.taicw.learning.weatherdata.sevice;

import com.taicw.learning.weatherdata.vo.WeatherResponse;

public interface IWeatherDataService {

    /**
     * 根据城市Id查询天气数据
     */
    WeatherResponse getDataByCityId(String cityId);

    /**
     * 根据城市名称查询天气数据
     */
    WeatherResponse getDataByCityName(String cityName);

    /**
     * 根据城市id同步天气数据
     */
    void syncDataByCityId(String cityId);

}

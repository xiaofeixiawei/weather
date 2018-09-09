package com.taicw.learning.weathercitydata.service;

import com.taicw.learning.weathercitydata.vo.City;

import java.util.List;

public interface ICityDataService {
    List<City> listCity() throws Exception;
}

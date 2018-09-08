package com.taicw.learning.weather.sevice;

import com.taicw.learning.weather.vo.City;

import java.io.IOException;
import java.util.List;

public interface ICityDataService {
    List<City> listCity() throws Exception;
}

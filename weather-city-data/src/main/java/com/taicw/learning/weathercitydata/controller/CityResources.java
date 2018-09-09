package com.taicw.learning.weathercitydata.controller;

import com.taicw.learning.weathercitydata.service.CityDataService;
import com.taicw.learning.weathercitydata.vo.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by taichangwei on 2018/9/8.
 */
@RestController
@RequestMapping("city")
public class CityResources {

    @Autowired
    CityDataService cityDataService;

    @GetMapping(value = "list")
    public List<City> getCityList(){
        return cityDataService.listCity();
    }
}

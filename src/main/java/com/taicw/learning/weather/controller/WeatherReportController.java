package com.taicw.learning.weather.controller;

import com.taicw.learning.weather.sevice.ICityDataService;
import com.taicw.learning.weather.sevice.IWeatherReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by taichangwei on 2018/9/2.
 */
@Controller
@RequestMapping("/report")
public class WeatherReportController {

    @Autowired
    private ICityDataService cityDataService;

    @Autowired
    private IWeatherReportService weatherReportService;



    @RequestMapping(value = {"/cityId/{cityId}", "/cityName/{cityName}", ""})
    public String getReportByCityId(@PathVariable(value = "cityId", required = false) String cityId, Model model) throws Exception {
        cityId = cityId == null ? "101280101"  : cityId;
        model.addAttribute("title", "小飞侠天气预报");
        model.addAttribute("cityId", cityId);
        model.addAttribute("cityList", cityDataService.listCity());
        model.addAttribute("report", weatherReportService.getDataByCityId(cityId));
        return "weather/report";
    }

}





























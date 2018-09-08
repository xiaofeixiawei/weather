package com.taicw.learning.weather.sevice;

import com.taicw.learning.weather.util.XmlBuilder;
import com.taicw.learning.weather.vo.City;
import com.taicw.learning.weather.vo.CityList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

/**
 * Created by taichangwei on 2018/9/1.
 */
@Service
public class CityDataService implements ICityDataService {

    @Override
    public List<City> listCity() throws Exception {
        Resource resource = new ClassPathResource("static/cityList.xml");
        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "utf-8"));
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            buffer.append(line);
        }
        br.close();

        CityList cityList = (CityList) XmlBuilder.xmlStrToObject(CityList.class, buffer.toString());
        return cityList.getCityList();
    }

}

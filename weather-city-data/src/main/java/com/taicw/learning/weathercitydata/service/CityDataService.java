package com.taicw.learning.weathercitydata.service;


import com.taicw.learning.weathercitydata.util.XmlBuilder;
import com.taicw.learning.weathercitydata.vo.City;
import com.taicw.learning.weathercitydata.vo.CityList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by taichangwei on 2018/9/1.
 */
@Service
public class CityDataService implements ICityDataService {

     private static Logger logger = LoggerFactory.getLogger(CityDataService.class);

    @Override
    public List<City> listCity() {
        try {
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
        } catch (Exception e){
            logger.error("获取城市列表非预期异常：{}", e);
            return null;
        }
    }

}

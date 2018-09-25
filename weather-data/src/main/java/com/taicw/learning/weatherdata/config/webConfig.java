package com.taicw.learning.weatherdata.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by taichangwei on 2018/9/25.
 */
@Configuration
@PropertySource("classpath:/properties/service_url.properties")
public class webConfig {
}

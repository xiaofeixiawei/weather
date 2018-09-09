package com.taicw.learning.weatherdata.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by taichangwei on 2018/9/8.
 */
@Configuration
@ComponentScan(basePackages = "com.taicw.learning.weatherdata.scheduleTask")
@EnableScheduling
public class ScheduleTaskConfig {
}

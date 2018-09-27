package com.taicw.learning.weatherdata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(WeatherDataApplication.class)
public class WeatherDataApplicationTests {

	@Autowired
	LoadBalancerClient loadBalancerClient;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testLoadBalancer(){

		for(int i = 0; i< 100; i++) {
			String serviceId = "weather-city-data";
			ServiceInstance instance = loadBalancerClient.choose(serviceId);
			System.out.println(instance.getHost() + ":" + instance.getPort());
		}

	}

}

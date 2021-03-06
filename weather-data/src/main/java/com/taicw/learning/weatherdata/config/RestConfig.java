package com.taicw.learning.weatherdata.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * Created by taichangwei on 2018/8/19.
 */
@Configuration
public class RestConfig {

//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate(){
//
//        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
//        restTemplate.getMessageConverters().clear();
//        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
//        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//
//        return restTemplate;
//    }

    //用于调用外部api, 如果使用负载均衡会在注册发现服务里找不到相应实例
    @Bean
    public RestTemplate externalRestTemplate(){

        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        restTemplate.getMessageConverters().clear();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        return restTemplate;
    }



//    @Autowired
//    private RestTemplateBuilder builder;
//
//    @Bean
//    public RestTemplate restTemplate() {
//        RestTemplate restTemplate =  builder.build();
//        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
//        List<MediaType> mediaTypeList = new ArrayList<>();
//        mediaTypeList.add(MediaType.ALL);
//        messageConverter.setSupportedMediaTypes(mediaTypeList);
//        restTemplate.getMessageConverters().add(messageConverter);
//        return restTemplate;
//    }

}

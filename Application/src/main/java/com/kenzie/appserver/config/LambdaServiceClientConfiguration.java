package com.kenzie.appserver.config;


import com.kenzie.capstone.service.TVShowService;
import com.kenzie.capstone.service.dao.TVShowDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LambdaServiceClientConfiguration {

    @Bean
    public TVShowService referralService() {
        return new TVShowService(new TVShowDao());
    }
}

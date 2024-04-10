package com.kenzie.appserver.config;


import com.kenzie.capstone.service.TVShowService;
import com.kenzie.capstone.service.client.TVShowLambdaServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LambdaServiceClientConfiguration {

    @Bean
    public TVShowLambdaServiceClient referralService() {
        return new TVShowLambdaServiceClient();
    }
}

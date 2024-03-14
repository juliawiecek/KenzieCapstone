package com.kenzie.capstone.service.dependency;

import com.kenzie.capstone.service.TVShowService;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {TVShowDaoModule.class, TVServiceModule.class})
public interface TVShowServiceComponent {
    TVShowService provideTVShowService();
}

package com.kenzie.capstone.service.dependency;


import com.kenzie.capstone.service.dao.TVShowDao;
import com.kenzie.capstone.service.service.TVShowService;
import dagger.Module;
import dagger.Provides;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Module(
        includes = DaoModule.class
)
public class TVServiceModule {

    @Singleton
    @Provides
    @Inject
    public TVShowService provideLambdaService(@Named("TVShowDao") TVShowDao tvShowDao) {
        return new TVShowService(tvShowDao);
    }
}

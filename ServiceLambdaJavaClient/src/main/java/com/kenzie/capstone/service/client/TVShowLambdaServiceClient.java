package com.kenzie.capstone.service.client;

import com.kenzie.capstone.service.dao.TVShowDao;

import java.util.Optional;

public class TVShowLambdaServiceClient {
    private TVShowDao tvShowDao;


    public TVShowLambdaServiceClient(TVShowDao tvShowDao){this.tvShowDao = tvShowDao;}

    public String getPopularShows(){
        return tvShowDao.getPopularShowsFromAPI();
    }
    public String getShow(String showId){
        return tvShowDao.getShowFromAPI(showId);
    }
    public String getShowInfo(String showId){
        return tvShowDao.getShowInfoFromAPI(showId);
    }
    public String getShowEpisodeList(String showId){
        return tvShowDao.getShowEpisodeListFromAPI(showId);
    }
}

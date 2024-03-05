package com.kenzie.capstone.service;

import com.kenzie.capstone.service.dao.TVShowDao;
import com.kenzie.capstone.service.model.EpisodeResponse;
import com.kenzie.capstone.service.model.ShowInfoResponse;
import com.kenzie.capstone.service.model.TVShowReponse;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

public class TVShowService {
    TVShowDao tvShowDao;
    @Inject
    public TVShowService(TVShowDao tvShowDao){
        this.tvShowDao = tvShowDao;
    }
    public List<TVShowReponse> getPopularShows(){
        String popularShows = tvShowDao.getPopularShowsFromAPI();
        // Convert to response, check problems, return response. Same for all methods
        return Collections.emptyList();
    }
    public TVShowReponse getShow(String query) {
        String requestedShow = tvShowDao.getShowFromAPI(query);
        return new TVShowReponse();
    }
    public ShowInfoResponse getShowInfo(String id) {
        String showinfo = tvShowDao.getShowInfoFromAPI(id);
        return new ShowInfoResponse();
    }
    public List<EpisodeResponse> getShowEpisodeList(String id){
        String episodeList = tvShowDao.getShowEpisodeListFromAPI(id);
        return Collections.emptyList();
    }
}

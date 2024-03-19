package com.kenzie.capstone.service;

import com.kenzie.capstone.service.converter.JsonStringToResponseConverter;
import com.kenzie.capstone.service.dao.TVShowDao;
import com.kenzie.capstone.service.model.EpisodeResponse;
import com.kenzie.capstone.service.model.ImageResponse;
import com.kenzie.capstone.service.model.ShowInfoResponse;


import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

public class TVShowService {
    TVShowDao tvShowDao;
    JsonStringToResponseConverter jsonStringToResponseConverter = new JsonStringToResponseConverter();
    @Inject
    public TVShowService(TVShowDao tvShowDao){
        this.tvShowDao = tvShowDao;
    }
    public List<ShowInfoResponse> getPopularShows(){
        String popularShows = tvShowDao.getPopularShowsFromAPI();
        // Convert to response, check problems, return response. Same for all methods
        return jsonStringToResponseConverter.convertToShowInfoListResponse(popularShows);
    }
    public ShowInfoResponse getShow(String id) {
        String requestedShow = tvShowDao.getShowFromAPI(id);
        return jsonStringToResponseConverter.convertToShowInfoResponse(requestedShow);
    }
    public ShowInfoResponse getShowInfo(String id) {
        String showInfo = tvShowDao.getShowInfoFromAPI(id);
        return jsonStringToResponseConverter.convertToShowInfoResponse(showInfo);
    }
    public List<EpisodeResponse> getShowEpisodesForSeason(String id){
        String episodeList = tvShowDao.getShowEpisodesForSeasonFromAPI(id);
        return Collections.emptyList();
    }
    public ImageResponse getShowImages(String id){
        String showImages = tvShowDao.getShowImagesFromAPI(id);
        return jsonStringToResponseConverter.convertToImageResponse(showImages);
    }
    public EpisodeResponse getShowSeasons(String id){
        String showSeasons = tvShowDao.getShowSeasonsFromAPI(id);
        return jsonStringToResponseConverter.convertToEpisodeResponse(showSeasons);
    }
}

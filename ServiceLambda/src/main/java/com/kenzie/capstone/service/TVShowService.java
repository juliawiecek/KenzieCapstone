package com.kenzie.capstone.service;

import com.kenzie.capstone.service.converter.JsonStringToResponseConverter;
import com.kenzie.capstone.service.dao.TVShowDao;
import com.kenzie.capstone.service.model.*;


import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TVShowService {
    private TVShowDao tvShowDao;
    private JsonStringToResponseConverter jsonStringToResponseConverter = new JsonStringToResponseConverter();
    @Inject
    public TVShowService(TVShowDao tvShowDao){
        this.tvShowDao = tvShowDao;
    }
    public List<ShowInfoResponse> getPopularShows(){
        String popularShows = tvShowDao.getPopularShowsFromAPI();
        List<ShowInfoResponse> response = jsonStringToResponseConverter.convertToShowInfoListResponse(popularShows);
        return response;
    }
    public List<ShowInfoResponse> getShow(String query) {
        String requestedShow = tvShowDao.getShowFromAPI(query);
        List<ShowInfoResponse> response = jsonStringToResponseConverter.queryConvertToShowInfoListResponse(requestedShow);
        return response;
    }
    public ShowInfoResponse getShowInfo(String id) {
        String showInfo = tvShowDao.getShowInfoFromAPI(id);
        ShowInfoResponse response = jsonStringToResponseConverter.convertToShowInfoResponse(showInfo);
        return response;
    }
    public List<EpisodeResponse> getShowEpisodesForSeason(String id){
        String episodeList = tvShowDao.getShowEpisodesForSeasonFromAPI(id);
        List<EpisodeResponse> response = jsonStringToResponseConverter.convertToEpisodeListResponse(episodeList);
        return response;
    }
    public List<ImageResponse> getShowImages(String id){
        String showImages = tvShowDao.getShowImagesFromAPI(id);
        List<ImageResponse> response = jsonStringToResponseConverter.convertToImageResponse(showImages);
        return response;
    }
    public List<SeasonsResponse> getShowSeasons(String id){
        String showSeasons = tvShowDao.getShowSeasonsFromAPI(id);
        List<SeasonsResponse> response = jsonStringToResponseConverter.convertToSeasonsListResponse(showSeasons);
        return response;
    }
}

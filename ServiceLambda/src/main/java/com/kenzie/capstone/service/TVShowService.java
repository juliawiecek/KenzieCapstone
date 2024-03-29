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
    public List<ShowInfoData> getPopularShows(){
        String popularShows = tvShowDao.getPopularShowsFromAPI();
        List<ShowInfoResponse> response = jsonStringToResponseConverter.convertToShowInfoListResponse(popularShows);
        return response.stream().map(ShowInfoResponse::getShowInfoData).collect(Collectors.toList());
    }
    public List<ShowInfoData> getShow(String query) {
        String requestedShow = tvShowDao.getShowFromAPI(query);
        List<ShowInfoResponse> response = jsonStringToResponseConverter.convertToShowInfoListResponse(requestedShow);
        return response.stream().map(ShowInfoResponse::getShowInfoData).collect(Collectors.toList());
    }
    public ShowInfoData getShowInfo(String id) {
        String showInfo = tvShowDao.getShowInfoFromAPI(id);
        ShowInfoResponse response = jsonStringToResponseConverter.convertToShowInfoResponse(showInfo);
        return new ShowInfoData(response.getName(), response.getGenres(), response.getRating(), response.getImage(),
                response.getSummary());
    }
    public List<EpisodeData> getShowEpisodesForSeason(String id){
        String episodeList = tvShowDao.getShowEpisodesForSeasonFromAPI(id);
        List<EpisodeResponse> response = jsonStringToResponseConverter.convertToEpisodeListResponse(episodeList);
        return response.stream().map(EpisodeResponse::getEpisodeData).collect(Collectors.toList());
    }
    public ImageData getShowImages(String id){
        String showImages = tvShowDao.getShowImagesFromAPI(id);
        ImageResponse response = jsonStringToResponseConverter.convertToImageResponse(showImages);
        return new ImageData(response.getId(), response.getType(), response.isMain(), response.getResolutions());
    }
    public EpisodeData getShowSeasons(String id){
        String showSeasons = tvShowDao.getShowSeasonsFromAPI(id);
        EpisodeResponse response = jsonStringToResponseConverter.convertToEpisodeResponse(showSeasons);
        return new EpisodeData(response.getName(), response.getEpisodeOrder(), response.getNumber(), response.getRuntime(),
                response.getImage(), response.getSummary());
    }
}

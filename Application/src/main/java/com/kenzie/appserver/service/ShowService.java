package com.kenzie.appserver.service;

import com.kenzie.appserver.service.model.Episode;
import com.kenzie.appserver.service.model.Image;
import com.kenzie.appserver.service.model.ShowInfo;
import com.kenzie.capstone.service.TVShowService;
import com.kenzie.capstone.service.client.TVShowServiceClient;
import com.kenzie.capstone.service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    private TVShowServiceClient tvShowServiceClient;
    public ShowService(TVShowServiceClient tvShowServiceClient){
        this.tvShowServiceClient = tvShowServiceClient;
    }

    public List<ShowInfo> convertToShowInfoList(List<ShowInfoData> showInfoDataList) {
        List<ShowInfo> showInfoList = new ArrayList<>();
        for (ShowInfoData showInfoData : showInfoDataList) {
            ShowInfo showInfo = new ShowInfo(showInfoData.getName(), showInfoData.getGenres(), showInfoData.getRating(),
                    showInfoData.getImage(), showInfoData.getSummary());
            showInfoList.add(showInfo);
        }
        return showInfoList;
    }

    public List<ShowInfo> getPopularShows(){
        return convertToShowInfoList(tvShowServiceClient.getPopularShows());
    }

    public List<ShowInfo> getShow(String query) {
        return convertToShowInfoList(tvShowServiceClient.searchShows(query));
    }

    public ShowInfo getShowInfo(String id) {
        ShowInfoData data = tvShowServiceClient.getShowInfo(id);
        return new ShowInfo(data.getName(), data.getGenres(), data.getRating(), data.getImage(), data.getSummary());
    }

    public List<Episode> convertToEpisodeList(List<EpisodeData> episodeDataList) {
        List<Episode> episodeList = new ArrayList<>();
        for (EpisodeData episodeData : episodeDataList) {
            Episode episode = new Episode(episodeData.getName(), episodeData.getEpisodeOrder(), episodeData.getNumber(),
                    episodeData.getRuntime(), episodeData.getImage(), episodeData.getSummary());
            episodeList.add(episode);
        }
        return episodeList;
    }

    public List<Episode> getShowEpisodesForSeason(String id) {
        return convertToEpisodeList(tvShowServiceClient.getShowEpisodeList(id));
    }
    public Image getShowImages(String id) {
        ImageData data = tvShowServiceClient.getShowImages(id);
        return new Image(data.getId(), data.getType(), data.isMain(), data.getResolutions());
    }
    public Episode getShowSeasons(String id) {
        EpisodeData data = tvShowServiceClient.getShowSeasons(id);
        return new Episode(data.getName(), data.getEpisodeOrder(), data.getNumber(), data.getRuntime(), data.getImage(),
                data.getSummary());
    }
}

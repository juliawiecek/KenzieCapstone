package com.kenzie.appserver.service;

import com.kenzie.appserver.Exceptions.ShowNotFoundException;
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
    @Autowired
    public ShowService(TVShowServiceClient tvShowServiceClient) {
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

    public List<Episode> convertToEpisodeList(List<EpisodeData> episodeDataList) {
        List<Episode> episodeList = new ArrayList<>();
        for (EpisodeData episodeData : episodeDataList) {
            Episode episode = new Episode(episodeData.getName(), episodeData.getEpisodeOrder(), episodeData.getNumber(),
                    episodeData.getRuntime(), episodeData.getImage(), episodeData.getSummary());
            episodeList.add(episode);
        }
        return episodeList;
    }
    public List<ShowInfo> getPopularShows() {
        List<ShowInfoData> showInfoDataList = tvShowServiceClient.getPopularShows();
        if (showInfoDataList.isEmpty()) {
            throw new ShowNotFoundException("No popular shows found.");
        }
        return convertToShowInfoList(showInfoDataList);
    }

    public List<ShowInfo> getShow(String query) {
        List<ShowInfoData> showInfoDataList = tvShowServiceClient.searchShows(query);
        if (showInfoDataList.isEmpty()) {
            throw new ShowNotFoundException("Show not found for query: " + query);
        }
        return convertToShowInfoList(showInfoDataList);
    }

    public ShowInfo getShowInfo(String id) {
        ShowInfoData data = tvShowServiceClient.getShowInfo(id);
        if (data == null) {
            throw new ShowNotFoundException("Show info not found for id: " + id);
        }
        return new ShowInfo(data.getName(), data.getGenres(), data.getRating(), data.getImage(), data.getSummary());
    }

    public List<Episode> getShowEpisodesForSeason(String id) {
        List<EpisodeData> episodeDataList = tvShowServiceClient.getShowEpisodeList(id);
        if (episodeDataList.isEmpty()) {
            throw new ShowNotFoundException("No episodes found for season id: " + id);
        }
        return convertToEpisodeList(episodeDataList);
    }

    public Image getShowImages(String id) {
        ImageData data = tvShowServiceClient.getShowImages(id);
        if (data == null) {
            throw new ShowNotFoundException("No images found for show id: " + id);
        }
        return new Image(data.getId(), data.getType(), data.isMain(), data.getResolutions());
    }

    public Episode getShowSeasons(String id) {
        EpisodeData data = tvShowServiceClient.getShowSeasons(id);
        if (data == null) {
            throw new ShowNotFoundException("No seasons found for show id: " + id);
        }
        return new Episode(data.getName(), data.getEpisodeOrder(), data.getNumber(), data.getRuntime(), data.getImage(),
                data.getSummary());
    }
}


package com.kenzie.appserver.service.model;

import com.kenzie.capstone.service.client.TVShowServiceClient;
import com.kenzie.capstone.service.model.EpisodeData;
import com.kenzie.capstone.service.model.ImageData;
import com.kenzie.capstone.service.model.ShowInfoData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {
    private TVShowServiceClient tvShowServiceClient;
    public ShowService(TVShowServiceClient tvShowServiceClient){
        this.tvShowServiceClient = tvShowServiceClient;
    }

    public List<ShowInfoData> getPopularShows(){
        return tvShowServiceClient.getPopularShows();
    }

    public ShowInfoData searchShows(String id){
        return tvShowServiceClient.searchShows(id);
    }

    public ShowInfoData getShowInfo(String id){
        return tvShowServiceClient.getShowInfo(id);
    }

    public ImageData getShowImage(String id){
        return tvShowServiceClient.getShowImages(id);
    }

    public EpisodeData getShowSeasons(String id){
        return tvShowServiceClient.getShowSeasons(id);
    }

    public List<EpisodeData> getShowEpisodeList(String id){
        return tvShowServiceClient.getShowEpisodeList(id);
    }
}

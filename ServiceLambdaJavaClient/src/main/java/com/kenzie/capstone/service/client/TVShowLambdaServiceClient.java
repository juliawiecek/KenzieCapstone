package com.kenzie.capstone.service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.capstone.service.model.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class TVShowLambdaServiceClient {

    private static final String GET_POPULAR_SHOWS_ENDPOINT = "/shows/popular";
    private static final String GET_SEARCHED_SHOWS_ENDPOINT = "/shows/search/{query}";
    private static final String GET_EPISODES_ENDPOINT = "/shows/episodes/{id}";
    private static final String GET_IMAGES_ENDPOINT = "/shows/images/{id}";
    private static final String GET_SHOW_INFO_ENDPOINT = "/shows/info/{id}";
    private static final String GET_SEASONS_ENDPOINT = "/shows/seasons/{id}";

    private ObjectMapper mapper;

    public TVShowLambdaServiceClient() {
        this.mapper = new ObjectMapper();
    }

    public List<ShowInfoData> getPopularShows() {
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.getEndpoint(GET_POPULAR_SHOWS_ENDPOINT);
        List<ShowInfoData> showInfoDataList;
        try {
            showInfoDataList = Arrays.asList(mapper.readValue(response, ShowInfoData[].class));
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return showInfoDataList;
    }

    public List<ShowInfoData> getSearchedShows(String query) {
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.getEndpoint(GET_SEARCHED_SHOWS_ENDPOINT.replace("{query}", query));
        List<ShowInfoData> showInfoDataList;
        try {
            showInfoDataList = Arrays.asList(mapper.readValue(response, ShowInfoData[].class));
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return showInfoDataList;
    }

    public List<EpisodeData> getEpisodes(String id) {
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.getEndpoint(GET_EPISODES_ENDPOINT.replace("{id}", id));
        List<EpisodeData> episodeDataList;
        try {
            episodeDataList = Arrays.asList(mapper.readValue(response, EpisodeData[].class));
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return episodeDataList;
    }

    public List<ImageData> getImages(String id) {
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.getEndpoint(GET_IMAGES_ENDPOINT.replace("{id}", id));
        List<ImageData> imagesDataList;
        try {
            imagesDataList = Arrays.asList(mapper.readValue(response, ImageData[].class));
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return imagesDataList;
    }

    public ShowInfoData getShowInfo(String id) {
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.getEndpoint(GET_SHOW_INFO_ENDPOINT.replace("{id}", id));
        ShowInfoData showInfoData;
        try {
            showInfoData = mapper.readValue(response, ShowInfoData.class);
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return showInfoData;
    }

    public List<SeasonsData> getSeasons(String id) {
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.getEndpoint(GET_SEASONS_ENDPOINT.replace("{id}", id));
        List<SeasonsData> seasonsDataList;
        try {
            seasonsDataList = Arrays.asList(mapper.readValue(response, SeasonsData[].class));
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return seasonsDataList;
    }
}

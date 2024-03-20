package com.kenzie.capstone.service.client;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.capstone.service.dao.TVShowDao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.capstone.service.dao.TVShowDao;
import com.kenzie.capstone.service.model.*;

import java.util.Arrays;
import java.util.List;


public class TVShowServiceClient {

    private static final String GET_POPULAR_SHOWS_ENDPOINT = "/shows";
    private static final String SEARCH_SHOWS_ENDPOINT = "/search/shows?q={query}";
    private static final String GET_SHOW_INFO_ENDPOINT = "/shows/{id}";
    private static final String GET_SHOW_IMAGES_ENDPOINT = "/shows/{id}/images";
    private static final String GET_SHOW_SEASONS_ENDPOINT = "/shows/{id}/seasons";
    private static final String GET_SHOW_EPISODE_LIST_ENDPOINT = "/seasons/{id}/episodes";

    private ObjectMapper mapper;

    public TVShowServiceClient() {
        this.mapper = new ObjectMapper();
    }

    public List<ShowInfoData> getPopularShows() {
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.getEndpoint(GET_POPULAR_SHOWS_ENDPOINT);
        ShowInfoData[] showInfoData;
        try {
            showInfoData = mapper.readValue(response, ShowInfoData[].class);
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return Arrays.asList(showInfoData);
    }

    public ShowInfoData searchShows(String query) {
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.getEndpoint(SEARCH_SHOWS_ENDPOINT.replace("{query}", query));
        ShowInfoData showInfoData;
        try {
            showInfoData = mapper.readValue(response, ShowInfoData.class);
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return showInfoData;
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

    public ImageData getShowImages(String id) {
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.getEndpoint(GET_SHOW_IMAGES_ENDPOINT.replace("{id}", id));
        ImageData imageData;
        try {
            imageData = mapper.readValue(response, ImageData.class);
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return imageData;
    }

    public EpisodeData getShowSeasons(String id) {
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.getEndpoint(GET_SHOW_SEASONS_ENDPOINT.replace("{id}", id));
        EpisodeData episodeData;
        try {
            episodeData = mapper.readValue(response, EpisodeData.class);
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return episodeData;
    }

    public List<EpisodeData> getShowEpisodeList(String id) {
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.getEndpoint(GET_SHOW_EPISODE_LIST_ENDPOINT.replace("{id}", id));
        EpisodeData[] episodeData;
        try {
            episodeData = mapper.readValue(response, EpisodeData[].class);
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return Arrays.asList(episodeData);
    }
}
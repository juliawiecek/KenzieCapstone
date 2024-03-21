
//package com.kenzie.capstone.service.client;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.kenzie.capstone.service.dao.TVShowDao;
//
//
//public class TVShowServiceClient {
//
//    private static final String GET_POPULAR_SHOWS_ENDPOINT = "/shows";
//    private static final String SEARCH_SHOWS_ENDPOINT = "/search/shows?q=";
//    private static final String GET_SHOW_INFO_ENDPOINT = "/shows/";
//    private static final String GET_SHOW_EPISODE_LIST_ENDPOINT = "/shows/";
//
//    private ObjectMapper mapper;
//    private TVShowDao tvShowDao;
//
//    public TVShowServiceClient(TVShowDao tvShowDao) {
//        this.tvShowDao = tvShowDao;
//        this.mapper = new ObjectMapper();
//    }
//
//    public TVShowData getPopularShows() {
//        String response = tvShowDao.makeApiRequest(GET_POPULAR_SHOWS_ENDPOINT);
//        return mapJsonToTVShowData(response);
//    }
//
//    public TVShowData searchShows(String query) {
//        String response = tvShowDao.makeApiRequest(SEARCH_SHOWS_ENDPOINT + query);
//        return mapJsonToTVShowData(response);
//    }
//
//    public TVShowData getShowInfo(String id) {
//        String response = tvShowDao.makeApiRequest(GET_SHOW_INFO_ENDPOINT + id + "?embed[]=episodes&embed[]=images");
//        return mapJsonToTVShowData(response);
//    }
//
//    public TVShowData getShowEpisodeList(String id) {
//        String response = tvShowDao.makeApiRequest(GET_SHOW_EPISODE_LIST_ENDPOINT + id + "/episodes");
//        return mapJsonToTVShowData(response);
//    }
//
//    private TVShowData mapJsonToTVShowData(String json) {
//        try {
//            return mapper.readValue(json, TVShowData.class);
//        } catch (Exception e) {
//            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
//        }
//    }
//}

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

    public ShowInfoResponse searchShows(String query) {
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.getEndpoint(SEARCH_SHOWS_ENDPOINT.replace("{query}", query));
        ShowInfoResponse showInfoResponse;
        try {
            showInfoResponse = mapper.readValue(response, ShowInfoResponse.class);
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return showInfoResponse;
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


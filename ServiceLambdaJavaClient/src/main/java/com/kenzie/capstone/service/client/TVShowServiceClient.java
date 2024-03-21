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
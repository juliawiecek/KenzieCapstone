package com.kenzie.capstone.service.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kenzie.capstone.service.dao.ApiGatewayException;
import com.kenzie.capstone.service.model.EpisodeResponse;
import com.kenzie.capstone.service.model.ImageResponse;
import com.kenzie.capstone.service.model.ShowInfoResponse;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JsonStringToResponseConverter {

        public ShowInfoResponse convertToShowInfoResponse(String body) {
            try {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                ShowInfoResponse showInfoResponse = gson.fromJson(body, ShowInfoResponse.class);
                return showInfoResponse;
            } catch (Exception e) {
                throw new ApiGatewayException("ShowInfoResponse could not be deserialized");
            }
        }

    public List<ShowInfoResponse> convertToShowInfoListResponse(String body) {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            ShowInfoResponse[] showInfoArray = gson.fromJson(body, ShowInfoResponse[].class);
            List<ShowInfoResponse> showInfoListResponse = Arrays.asList(showInfoArray);
            return showInfoListResponse;
        } catch (Exception e) {
            throw new ApiGatewayException("List of ShowInfoResponse could not be deserialized");
        }
    }

    public EpisodeResponse convertToEpisodeResponse(String body) {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            EpisodeResponse episodeResponse = gson.fromJson(body, EpisodeResponse.class);
            return episodeResponse;
        } catch (Exception e) {
            throw new ApiGatewayException("EpisodeResponse could not be deserialized");
        }
    }

    public ImageResponse convertToImageResponse(String body) {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            ImageResponse imageResponse = gson.fromJson(body, ImageResponse.class);
            return imageResponse;
        } catch (Exception e) {
            throw new ApiGatewayException("ImageResponse could not be deserialized");
        }
    }

}

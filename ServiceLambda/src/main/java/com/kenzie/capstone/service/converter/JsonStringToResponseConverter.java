package com.kenzie.capstone.service.converter;

import com.google.gson.*;
import com.kenzie.capstone.service.dao.ApiGatewayException;
import com.kenzie.capstone.service.model.EpisodeResponse;
import com.kenzie.capstone.service.model.ImageResponse;
import com.kenzie.capstone.service.model.SeasonsResponse;
import com.kenzie.capstone.service.model.ShowInfoResponse;

import java.util.*;
import java.util.stream.Collectors;

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

            List<ShowInfoResponse> showInfoListResponse = Arrays.stream(showInfoArray)
                    .filter(item -> item.getImage() != null && item.getRating() != null)
                    .map(item ->
                            new ShowInfoResponse(item.getName(), item.getGenres(), item.getRating(),
                            item.getImage(), item.getSummary(), item.getId()))
                    .collect(Collectors.toList());
            return showInfoListResponse;
        } catch (Exception e) {
            throw new ApiGatewayException("List of ShowInfoResponse could not be deserialized");
        }
    }

    public List<ShowInfoResponse> queryConvertToShowInfoListResponse(String body) {
    // the json response for query has objects nested within "show", so we have to get them manually
        try {
            Gson gson = new Gson();
            JsonElement[] jsonElements = gson.fromJson(body, JsonElement[].class);

            List<ShowInfoResponse> showInfoListResponse = new ArrayList<>();
            for (JsonElement element : jsonElements) {
                JsonObject tvShow = element.getAsJsonObject();
                JsonObject show = tvShow.getAsJsonObject("show");

                JsonArray genresJsonArray = show.getAsJsonArray("genres");
                List<String> genres = new ArrayList<>();
                for (JsonElement genreElement : genresJsonArray) {
                    genres.add(genreElement.getAsString());
                }

                String name = show.has("name") && !show.get("name").isJsonNull() ? show.get("name").getAsString() : null;
                JsonObject ratingJson = show.has("rating") && !show.get("rating").isJsonNull() ? show.get("rating").getAsJsonObject() : null;
                JsonObject imageJson = show.has("image") && !show.get("image").isJsonNull() ? show.get("image").getAsJsonObject() : null;
                String summary = show.has("summary") && !show.get("summary").isJsonNull() ? show.get("summary").getAsString() : null;
                Integer id = show.has("id") && !show.get("id").isJsonNull() ? show.get("id").getAsInt() : null;

                ShowInfoResponse.Rating ratingObject = null;
                ShowInfoResponse.Image imageObject = null;
                if (ratingJson != null && ratingJson.has("average") && !ratingJson.get("average").isJsonNull()) {
                    ratingObject = new ShowInfoResponse.Rating();
                    ratingObject.setAverage(ratingJson.get("average").getAsDouble());
                }
                if (imageJson != null && imageJson.has("medium") && imageJson.has("original") &&
                        !imageJson.get("medium").isJsonNull() && !imageJson.get("original").isJsonNull()) {
                    imageObject = new ShowInfoResponse.Image();
                    imageObject.setMedium(imageJson.get("medium").getAsString());
                    imageObject.setOriginal(imageJson.get("original").getAsString());
                }

                if (name != null && ratingObject != null && imageObject != null && summary != null && id != null) {
                    ShowInfoResponse showInfo = new ShowInfoResponse(name, genres, ratingObject, imageObject, summary, id);
                    showInfoListResponse.add(showInfo);
                }
            }

            return showInfoListResponse;
        } catch (Exception e) {
            throw new ApiGatewayException("List of ShowInfoResponse could not be deserialized");
        }
    }

    public List<SeasonsResponse> convertToSeasonsListResponse(String body) {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            SeasonsResponse[] seasonInfoArray = gson.fromJson(body, SeasonsResponse[].class);

            List<SeasonsResponse> seasonResponses = Arrays.stream(seasonInfoArray)
                    .map(item ->
                            new SeasonsResponse(item.getNumber(), item.getId()))
                    .collect(Collectors.toList());
            return seasonResponses;
        } catch (Exception e) {
            throw new ApiGatewayException("List of SeasonsResponse could not be deserialized");
        }
    }

    public List<EpisodeResponse> convertToEpisodeListResponse(String body) {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            EpisodeResponse[] episodeInfoArray = gson.fromJson(body, EpisodeResponse[].class);

            List<EpisodeResponse> episodeResponses = Arrays.stream(episodeInfoArray)
                    .map(item ->
                            new EpisodeResponse(item.getName(), item.getSeason(), item.getNumber(),
                            item.getRuntime(), item.getImage(), item.getSummary()))
                    .collect(Collectors.toList());
            return episodeResponses;
        } catch (Exception e) {
            throw new ApiGatewayException("List of EpisodeResponse could not be deserialized");
        }
    }

    public List<ImageResponse> convertToImageResponse(String body) {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            ImageResponse[] imageInfoArray = gson.fromJson(body, ImageResponse[].class);

            List<ImageResponse> imageResponses = Arrays.stream(imageInfoArray)
                    .map(item ->
                            new ImageResponse(item.getType(), item.getResolutions()))
                    .collect(Collectors.toList());
            return imageResponses;
        } catch (Exception e) {
            throw new ApiGatewayException("List of SeasonsResponse could not be deserialized");
        }
    }

}

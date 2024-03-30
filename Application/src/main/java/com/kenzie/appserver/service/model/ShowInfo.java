package com.kenzie.appserver.service.model;

import com.kenzie.capstone.service.model.ShowInfoData;
import com.kenzie.capstone.service.model.ShowInfoResponse;

import java.util.List;

public class ShowInfo {
    private String name;
    private List<String> genres;
    private Double rating;
    private List<String> image;
    private String summary;

    public ShowInfo(String name, List<String> genres, Double rating, List<String> image, String summary) {
        this.name = name;
        this.genres = genres;
        this.rating = rating;
        this.image = image;
        this.summary = summary;
    }

    public ShowInfoResponse getShowInfoResponse() {
        // Return the show info response
        return new ShowInfoResponse(name, genres, rating, image, summary);
    }

    public String getName() {
        return name;
    }

    public List<String> getGenres() {
        return genres;
    }

    public Double getRating() {
        return rating;
    }

    public List<String> getImage() {
        return image;
    }

    public String getSummary() {
        return summary;
    }
}